package com.inbravo.spark;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

import com.google.common.base.Optional;
import com.inbravo.hadoop.utils.HDFSUtils;
import com.inbravo.log.LogFactory;

/**
 * 
 * @author amit.dixit
 *
 */
public final class ProductsCountryCount {

	/** The Constant LOG. */
	private static final Logger logger = LogFactory.getLogger(ProductsCountryCount.class);

	private static final String HDFS_OUTPUT_DIR = "/output";
	private static final String HDFS_TRANS_OUTPUT_DIR = "/product-country";

	/**
	 * 
	 * @throws IOException
	 */
	private static final void setup(final String outputRoot) throws IOException {

		HDFSUtils.removeFileStructureFromHDFS(outputRoot + HDFS_OUTPUT_DIR);
		HDFSUtils.removeFileStructureFromHDFS(outputRoot + HDFS_TRANS_OUTPUT_DIR);
	}

	public static final void main(final String... args) throws Exception {

		/* First setup */
		setup(args[2]);

		final JavaSparkContext sc = new JavaSparkContext(new SparkConf().setAppName("ProductsCountryCount").setMaster("local"));

		/* Process input files and calculate transaction count of users */
		final JavaPairRDD<String, String> outputRDD = ProductsCountryCount.getUserTransactionCount(sc, args[0], args[1], args[2]);

		/* Save output on HDFS */
		outputRDD.saveAsHadoopFile(args[2] + HDFS_OUTPUT_DIR, String.class, String.class, TextOutputFormat.class);

		/* Close spark context */
		sc.close();
	}

	/**
	 * This API will tell Number of Countries where each sold Product
	 * 
	 * @param sc
	 * @param transactionFilePath
	 * @param userFilePath
	 * @param outputRoot
	 * @return
	 */
	private static final JavaPairRDD<String, String> getUserTransactionCount(final JavaSparkContext sc, final String transactionFilePath,
			final String userFilePath, final String outputRoot) {

		/* Step 1: Read transactions data */
		final JavaRDD<String> transactionInputFile = sc.textFile(transactionFilePath);

		/* Step 2: Transaform transactions data into key (user-id) and value (product-id) pairs */
		final JavaPairRDD<Integer, Integer> transactionPairs = transactionDataAsKeyValue(transactionInputFile);

		/* Step 3: Read user data */
		final JavaRDD<String> userInputFile = sc.textFile(userFilePath);

		/* Step 4: Convert users data into key-value pairs */
		final JavaPairRDD<Integer, String> userPairs = userDataAsKeyValue(userInputFile);

		/* Step 5: Get Product-Id <--> Country-Id on the basis of User-Id */
		final JavaRDD<Tuple2<Integer, Optional<String>>> userTransactions = joinData(transactionPairs, userPairs);

		/* Step 6: Simplify the RDD into Integer (Product-Id) <--> String pairs */
		final JavaPairRDD<Integer, String> userTransactionsModified = modifyData(userTransactions);

		/* Save transaction info for debug purpose */
		userTransactionsModified.saveAsTextFile(outputRoot + HDFS_TRANS_OUTPUT_DIR);

		logger.debug("Product-ID <----> Country-Id mapping saved at : " + outputRoot + HDFS_TRANS_OUTPUT_DIR);

		/* Step 7: Find number of Countries for each Product */
		final Map<Integer, Object> result = countData(userTransactionsModified);

		final List<Tuple2<String, String>> output = new ArrayList<>();

		/* Convert the result to Tuples */
		for (final Entry<Integer, Object> entry : result.entrySet()) {

			output.add(new Tuple2<>(entry.getKey().toString(), String.valueOf((long) entry.getValue())));
		}

		/* Step 8: Create final RDD */
		final JavaPairRDD<String, String> outputRDD = sc.parallelizePairs(output);

		return outputRDD;
	}

	@SuppressWarnings("serial")
	private static final JavaPairRDD<Integer, Integer> transactionDataAsKeyValue(final JavaRDD<String> transactionInputFile) {

		return transactionInputFile.mapToPair(new PairFunction<String, Integer, Integer>() {

			public Tuple2<Integer, Integer> call(final String transaction) {

				logger.debug("Transaction : " + transaction);
				final String[] transactionSplit = transaction.split("\t");

				/* Tuple : key (user-id) : value (product-id) */
				return new Tuple2<Integer, Integer>(Integer.valueOf(transactionSplit[2]), Integer.valueOf(transactionSplit[1]));
			}
		});
	}

	@SuppressWarnings("serial")
	private static final JavaPairRDD<Integer, String> userDataAsKeyValue(final JavaRDD<String> userInputFile) {

		/* Left Outer Join of transactions on users */
		return userInputFile.mapToPair(new PairFunction<String, Integer, String>() {

			public Tuple2<Integer, String> call(final String user) {

				logger.debug("User : " + user);
				final String[] userSplit = user.split("\t");

				/* Tuple : key (user-id) : value (country) */
				return new Tuple2<Integer, String>(Integer.valueOf(userSplit[0]), userSplit[3]);
			}
		});
	}

	/**
	 * Left Outer Join of transactions on users
	 * 
	 * @param transactions
	 * @param users
	 * @return
	 */
	private static final JavaRDD<Tuple2<Integer, Optional<String>>> joinData(final JavaPairRDD<Integer, Integer> transactions,
			final JavaPairRDD<Integer, String> users) {

		/* Left Outer Join of transactions on users */
		final JavaRDD<Tuple2<Integer, Optional<String>>> leftJoinOutput = transactions.leftOuterJoin(users).values().distinct();

		return leftJoinOutput;
	}

	/**
	 * Modify data, convert to key-value pairs
	 * 
	 * @param data
	 * @return
	 */
	@SuppressWarnings("serial")
	private static final JavaPairRDD<Integer, String> modifyData(final JavaRDD<Tuple2<Integer, Optional<String>>> data) {

		/* Convert a Tuple to key-value of integer-string */
		return data.mapToPair(new PairFunction<Tuple2<Integer, Optional<String>>, Integer, String>() {

			public final Tuple2<Integer, String> call(final Tuple2<Integer, Optional<String>> tuple) throws Exception {

				return new Tuple2<Integer, String>(tuple._1, tuple._2.get());
			}
		});
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	private static final Map<Integer, Object> countData(final JavaPairRDD<Integer, String> data) {

		final Map<Integer, Object> result = data.countByKey();
		return result;
	}
}
