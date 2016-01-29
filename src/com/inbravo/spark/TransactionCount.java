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
public final class TransactionCount {

	public static final void main(final String... args) throws Exception {

		final JavaSparkContext sc = new JavaSparkContext(new SparkConf().setAppName("TransactionCount").setMaster("local"));

		/* Create new RDD */
		final JavaPairRDD<String, String> output_rdd = TransactionCount.run(sc, args[0], args[1]);

		/* Save output on HDFS */
		output_rdd.saveAsHadoopFile(args[2], String.class, String.class, TextOutputFormat.class);

		/* Close spark context */
		sc.close();
	}

	@SuppressWarnings("serial")
	public static JavaPairRDD<String, String> run(final JavaSparkContext sc, final String transaction, final String user) {

		/* Step 1 : Read/transaform transactions data */
		final JavaRDD<String> transactionInputFile = sc.textFile(transaction);

		final JavaPairRDD<Integer, Integer> transactionPairs = transactionInputFile.mapToPair(new PairFunction<String, Integer, Integer>() {

			public Tuple2<Integer, Integer> call(final String transaction) {

				System.out.println("Transaction : " + transaction);
				final String[] transactionSplit = transaction.split("\t");

				return new Tuple2<Integer, Integer>(Integer.valueOf(transactionSplit[2]), Integer.valueOf(transactionSplit[1]));
			}
		});

		/* Step 2 : Read/transaform user data */
		final JavaRDD<String> customerInputFile = sc.textFile(user);

		final JavaPairRDD<Integer, String> customerPairs = customerInputFile.mapToPair(new PairFunction<String, Integer, String>() {

			public Tuple2<Integer, String> call(final String user) {

				System.out.println("User : " + user);
				final String[] customerSplit = user.split("\t");

				return new Tuple2<Integer, String>(Integer.valueOf(customerSplit[0]), customerSplit[3]);
			}
		});

		/* Step 3 : Count the results after modification to key-value */
		final Map<Integer, Object> result = countData(modifyData(joinData(transactionPairs, customerPairs)));

		final List<Tuple2<String, String>> output = new ArrayList<>();

		/* Convert the result to Tuples */
		for (final Entry<Integer, Object> entry : result.entrySet()) {

			output.add(new Tuple2<>(entry.getKey().toString(), String.valueOf((long) entry.getValue())));
		}

		/* Create final RDD */
		final JavaPairRDD<String, String> output_rdd = sc.parallelizePairs(output);

		return output_rdd;
	}

	/**
	 * 
	 */
	@SuppressWarnings("serial")
	public static final PairFunction<Tuple2<Integer, Optional<String>>, Integer, String> KEY_VALUE_PAIRER = new PairFunction<Tuple2<Integer, Optional<String>>, Integer, String>() {

		public final Tuple2<Integer, String> call(final Tuple2<Integer, Optional<String>> tuple) throws Exception {

			return new Tuple2<Integer, String>(tuple._1, tuple._2.get());
		}
	};

	/**
	 * Left Outer Join of transactions on users
	 * 
	 * @param transactions
	 * @param users
	 * @return
	 */
	public static final JavaRDD<Tuple2<Integer, Optional<String>>> joinData(final JavaPairRDD<Integer, Integer> transactions,
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
	public static final JavaPairRDD<Integer, String> modifyData(JavaRDD<Tuple2<Integer, Optional<String>>> data) {

		return data.mapToPair(KEY_VALUE_PAIRER);
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	public static final Map<Integer, Object> countData(final JavaPairRDD<Integer, String> data) {

		final Map<Integer, Object> result = data.countByKey();
		return result;
	}
}
