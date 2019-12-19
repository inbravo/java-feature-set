package com.inbravo.jdk8;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 
 * @author amit.dixit
 *
 */
public final class StreamTest {

  public static final void main(final String... args) {

    /* The map parameter predicate(IntUnaryOperator) returns the square (a -> a * a) of number passed */
    System.out.println(Arrays.stream(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}).map(a -> a * a).boxed().collect(Collectors.toList()));

    /* The current predicate [(a, b) -> (a > b) ? a : b)] returns the greater of two passed numbers */
    System.out.println(Arrays.stream(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}).reduce((a, b) -> (a > b) ? a : b).getAsInt());
  }
}
