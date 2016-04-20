package com.inbravo.number;

/**
 * 
 * @author amit.dixit
 *
 */
public final class FibonacciNumber {

  public static final void main(String... args) {

    /* Take a value */
    final int i = 10;

    /* Find its fibonacci */
    System.out.println(fibonacci(i));
  }

  private static final int fibonacci(final int i) {

    if (i <= 0) {
      return 0;
    } else if (i == 1) {
      return 1;
    } else {
      return (fibonacci(i - 1) + fibonacci(i - 2));
    }
  }
}
