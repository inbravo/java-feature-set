package com.inbravo.spark;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

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

    /* Collect all elements */
    System.out.println("-------Collect : " + rddOne.collect());

    /* Show union */
    System.out.println("---------Union : " + rddOne.union(rddTwo).collect());

    /* Show intersection */
    System.out.println("--Intersection : " + rddOne.intersection(rddTwo).collect());

    /* Apply subtract */
    System.out.println("------Subtract : " + rddOne.subtract(rddTwo).collect());

    /* Apply cartesian */
    System.out.println("-----Cartesian : " + rddOne.cartesian(rddTwo).collect());

    /* Count all elements in RDDs */
    System.out.println("---------Count : " + (rddOne.count() + rddTwo.count()));

    /* Count all element values */
    System.out.println("--CountByValue : " + rddOne.countByValue());

    /* Get third element */
    System.out.println("----------Take : " + rddOne.take(3));

    /* Get only values which are less than 3 */
    System.out.println("--------Filter : " + rddOne.filter(x -> x.intValue() < 3).collect());

    /* Show Reduce result */
    System.out.println("--------Reduce : " + rddOne.union(rddTwo).reduce((x, y) -> Math.max(x, y)));

    /* Show Map result */
    System.out.println("-----------Map : " + rddOne.map(x -> x * x).collect());

    /* FlatMap returns java.lang.Iterable */
    System.out.println("-------FlatMap : " + rddOne.flatMap(x -> Arrays.asList(x * x)).collect());

    /* MapToPair returns Tuple2 */
    final JavaPairRDD<Integer, Integer> jpRDD = rddOne.mapToPair(convertToKeyValue());

    System.out.println("-----MapToPair : " + jpRDD.collect());

    /* Keys returns JavaRDD */
    System.out.println("----------Keys : " + jpRDD.keys().collect());

    /* Values returns JavaRDD */
    System.out.println("--------Values : " + jpRDD.values().collect());

    /* Values returns JavaRDD */
    System.out.println("-----SortByKey : " + jpRDD.sortByKey().collect());

    /* Keys returns JavaRDD */
    System.out.println("----GroupByKey : " + jpRDD.groupByKey().collect());

    /* ReduceByKey returns Function2 */
    System.out.println("---ReduceByKey : " + jpRDD.reduceByKey((x, y) -> x + y).collect());

    /* ReduceByKey returns Function */
    System.out.println("-----MapValues : " + jpRDD.mapValues(x -> x * x).collect());

    /* ReduceByKey returns Function */
    System.out.println("-FlatMapValues : " + jpRDD.flatMapValues(x -> Arrays.asList(x * x * x)).collect());

    System.out.println("=========================================================================================================");

    /* Close spark context */
    jsc.close();
  }

  @SuppressWarnings("serial")
  private static final PairFunction<Integer, Integer, Integer> convertToKeyValue() {

    /**
     * Convert to key-value [key (integer) : value (integer * integer)]
     */
    return new PairFunction<Integer, Integer, Integer>() {

      @Override
      public final Tuple2<Integer, Integer> call(final Integer integer) throws Exception {

        /* Tuple : key (integer) : value (integer * integer) */
        return new Tuple2<Integer, Integer>(integer, integer * integer);
      }
    };
  }
}
