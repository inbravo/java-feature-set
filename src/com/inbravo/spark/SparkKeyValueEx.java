package com.inbravo.spark;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
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

		System.out.println("=========================================================================================================");
		System.out.println("---------Various Trans/actions on RDD " + rddOne.collect() + "} & RDD {" + rddTwo.collect() + "}---------");

		/* Apply union */
		final JavaRDD<Integer> unionResult = rddOne.union(rddTwo);

		/* Show result */
		System.out.println("----------Union : " + unionResult.collect());

		/* Show intersection */
		System.out.println("---Intersection : " + rddOne.intersection(rddTwo).collect());

		/* Apply subtract */
		System.out.println("-------Subtract : " + rddOne.subtract(rddTwo).collect());

		/* Apply cartesian */
		System.out.println("------Cartesian : " + rddOne.cartesian(rddTwo).collect());

		/* Get all elements */
		System.out.println("-------Collect : " + rddOne.collect());

		/* Count all elements in RDDs */
		System.out.println("---------Count : " + (rddOne.count() + rddTwo.count()));

		/* Count all element values */
		System.out.println("--CountByValue : " + rddOne.countByValue());

		/* Get third element */
		System.out.println("----------Take : " + rddOne.take(3));

		/* Get only values which are less than 3 */
		System.out.println("--------Filter : " + rddOne.filter(x -> x.intValue() < 3).collect());

		/* Show Reduce result */
		System.out.println("--------Reduce : " + rddOne.reduce(reduce()));

		/* Show Map result */
		System.out.println("-----------Map : " + rddOne.map(x -> x * x).collect());

		/* FlatMap returns list */
		System.out.println("-------FlatMap : " + rddOne.flatMap(x -> Arrays.asList(x * x)).collect());
		System.out.println("=========================================================================================================");

		/* Close spark context */
		jsc.close();
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
