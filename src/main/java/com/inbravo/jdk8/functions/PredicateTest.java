package com.inbravo.jdk8.functions;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 
 * @author amit.dixit
 *
 */
public final class PredicateTest {

  public static final void main(final String... args) {

    /* Create a predicate to check if string is empty */
    final Predicate<String> stringNotEmpty = PredicateTest::isApple;

    /* Create a list of numberss */
    final List<String> strings = Arrays.asList("apple", "grape", "mango", "lichi", "");

    /* Filter all null strings */
    final List<String> filteredString = strings.stream().filter(stringNotEmpty).collect(Collectors.<String>toList());

    /* Print the list */
    System.out.println(filteredString);

    /* Create a predicate to return true only for numbers greater than 5 */
    final Predicate<Integer> atLeast5 = (x) -> x > 5;

    /* Create a list of numberss */
    final List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    /* Weedout integers which have value less than 5 */
    final List<Integer> filteredIntegers = integers.stream().filter(atLeast5).collect(Collectors.<Integer>toList());

    /* Print the list */
    System.out.println(filteredIntegers);
  }

  /* Serves the predicate */
  public static boolean isApple(final String fruit) {
    return "apple".equals(fruit);
  }
}
