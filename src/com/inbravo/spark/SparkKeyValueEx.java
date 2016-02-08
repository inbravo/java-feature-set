package com.inbravo.spark;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;

/**
 * 
 * @author amit.dixit
 *
 */
public final class SparkKeyValueEx {

	public final static void main(final String... args) {

		/* Spark context for java */
		final JavaSparkContext jsc = new JavaSparkContext(new SparkConf().setAppName("SparkKeyValueEx").setMaster("local"));

		/* Create RDDs of integer array */
		final JavaRDD<Integer> rddOne = jsc.parallelize(Arrays.asList(1, 2, 3, 3, 4));
		final JavaRDD<Integer> rddTwo = jsc.parallelize(Arrays.asList(3, 4, 4, 5, 6));

		System.out.println("========================================================================");
		System.out.println("---------Various Transformation on RDD " + rddOne.collect() + "} & RDD {" + rddTwo.collect() + "}---------");

		/* Apply union */
		final JavaRDD<Integer> unionResult = rddOne.union(rddTwo);

		/* Show result */
		System.out.println("----------Union : " + unionResult.collect());

		/* Apply intersection */
		final JavaRDD<Integer> intersectionResult = rddOne.intersection(rddTwo);

		/* Show result */
		System.out.println("---Intersection : " + intersectionResult.collect());

		/* Apply subtract */
		final JavaRDD<Integer> subtractResult = rddOne.subtract(rddTwo);

		/* Show result */
		System.out.println("-------Subtract : " + subtractResult.collect());

		/* Apply cartesian */
		final JavaPairRDD<Integer, Integer> cartesianResult = rddOne.cartesian(rddTwo);

		/* Show result */
		System.out.println("------Cartesian : " + cartesianResult.collect());

		System.out.println("-----------------------------------------------------------------------");
		System.out.println("---------Various Action on RDD " + rddOne.collect() + "} & RDD {" + rddTwo.collect() + "}---------");

		/* Get all elements */
		System.out.println("-------Collect : " + rddOne.collect());

		/* Count all elements */
		System.out.println("---------Count : " + (rddOne.count() + rddTwo.count()));

		/* Count all values */
		System.out.println("--CountByValue : " + rddOne.countByValue());

		/* Get third element */
		System.out.println("----------Take : " + rddOne.take(3));

		/* Filter some element */
		System.out.println("------Filter : " + rddOne.filter(x -> x.intValue() > 4));

		/* Apply reduce */
		final Integer reduceResult = rddOne.reduce(reduce());

		/* Show result */
		System.out.println("--------Reduce : " + reduceResult.intValue());

		/* Apply Map */
		final JavaRDD<Integer> squareResult = rddOne.map(square());

		/* Show result */
		System.out.println("-----------Map : " + squareResult.collect());
		System.out.println("========================================================================");

		/* Close spark context */
		jsc.close();
	}

	@SuppressWarnings("serial")
	private static final Function<Integer, Integer> square() {

		/**
		 * Return Square value
		 */
		return new Function<Integer, Integer>() {

			@Override
			public final Integer call(final Integer value) throws Exception {

				return value * value;
			}
		};
	}

	@SuppressWarnings("serial")
	private static final Function2<Integer, Integer, Integer> reduce() {

		/**
		 * Return higher value
		 */
		return new Function2<Integer, Integer, Integer>() {

			@Override
			public final Integer call(final Integer first, final Integer second) throws Exception {

				return Math.max(first, second);
			}
		};
	}
}
