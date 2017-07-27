package com.inbravo.number;

import java.util.*;

/**
 * 
 * @author amit.dixit
 *
 */
public final class RandomNumbers {

  public static final void main(final String... args) {

    /* Instance of Random classs */
    final Random random = new Random();

    /* random integers in [0, 100] */
    for (int c = 1; c <= 10; c++) {

      System.out.println(random.nextInt(100));
      System.out.println(random.nextFloat());
    }
  }
}
