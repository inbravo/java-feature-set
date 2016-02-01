package com.inbravo.spark;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

import com.google.common.base.Optional;

/**
 * 
 * @author amit.dixit
 *
 */
public final class UserTransactionCount {

	public static final void main(final String... args) throws Exception {

		final JavaSparkContext sc = new JavaSparkContext(new SparkConf().setAppName("UserTransactionCount").setMaster("local"));

		/* Process input files */
		final JavaPairRDD<String, String> outputRDD = UserTransactionCount.getUserTransactions(sc, args[0], args[1]);

		/* Save output on HDFS */
		outputRDD.saveAsHadoopFile(args[2], String.class, String.class, TextOutputFormat.class);

		/* Close spark context */
		sc.close();
	}

	@SuppressWarnings("serial")
	public static final JavaPairRDD<String, String> getUserTransactions(final JavaSparkContext sc, final String transaction,
			final String user) {

		/* Step 1: Read transactions data */
		final JavaRDD<String> transactionInputFile = sc.textFile(transaction);

		/* Step 2: Transaform transactions data into key-value pairs */
		final JavaPairRDD<Integer, Integer> transactionPairs = transactionInputFile.mapToPair(new PairFunction<String, Integer, Integer>() {

			public Tuple2<Integer, Integer> call(final String transaction) {

				System.out.println("Transaction : " + transaction);
				final String[] transactionSplit = transaction.split("\t");

				return new Tuple2<Integer, Integer>(Integer.valueOf(transactionSplit[2]), Integer.valueOf(transactionSplit[1]));
			}
		});

		/* Step 3: Read user data */
		final JavaRDD<String> userInputFile = sc.textFile(user);

		/* Step 4: Transaform users data into key-value pairs */
		final JavaPairRDD<Integer, String> userPairs = userInputFile.mapToPair(new PairFunction<String, Integer, String>() {

			public Tuple2<Integer, String> call(final String user) {

				System.out.println("User : " + user);
				final String[] customerSplit = user.split("\t");

				return new Tuple2<Integer, String>(Integer.valueOf(customerSplit[0]), customerSplit[3]);
			}
		});

		/* Step 5: Apply join on users and transactions */
		final JavaRDD<Tuple2<Integer, Optional<String>>> userTransactions = joinData(transactionPairs, userPairs);

		/* Step 6: Modify data, convert to key-value pairs */
		final JavaPairRDD<Integer, String> userTransactionsModified = modifyData(userTransactions);

		/* Step 7: Count the result */
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
