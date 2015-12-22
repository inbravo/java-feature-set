package com.inbravo.spark;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import scala.Tuple2;

/**
 * Count the number of words in text data received from a data server listening on a TCP socket
 * 
 * @author amit.dixit
 *
 */
public final class NetworkWordCount {

	public static final void main(final String[] args) {
		networkWordCount();
	}

	private static final void networkWordCount() {

		/* StreamingContext with two threads and batch interval of 1 second */
		final SparkConf conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount");
		final JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(1));

		/* Create a DStream that will connect to localhost:9999 */
		final JavaReceiverInputDStream<String> lines = jssc.socketTextStream("localhost", 9999);

		/* Split each line into words */
		final JavaDStream<String> words = lines.flatMap(new FlatMapFunction<String, String>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Iterable<String> call(String x) {
				return Arrays.asList(x.split(" "));
			}
		});

		/* Count each word in each batch */
		final JavaPairDStream<String, Integer> pairs = words.mapToPair(new PairFunction<String, String, Integer>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Tuple2<String, Integer> call(String s) {
				return new Tuple2<String, Integer>(s, 1);
			}
		});

		final JavaPairDStream<String, Integer> wordCounts = pairs.reduceByKey(new Function2<Integer, Integer, Integer>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Integer call(Integer i1, Integer i2) {
				return i1 + i2;
			}
		});

		/* Print the first ten elements of each RDD generated in this DStream to the console */
		wordCounts.print();

		jssc.start(); // Start the computation
		jssc.awaitTermination(); // Wait for the computation to terminate

		if (jssc != null) {
			jssc.close();
		}
	}
}
