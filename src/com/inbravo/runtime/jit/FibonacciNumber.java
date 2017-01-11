package com.inbravo.runtime.jit;

/**
 * Run this program with linux 'time' command with and without '-Xint' to see how it works without
 * JIT
 * 
 * 'time java -Xint -XX:+PrintCompilation -cp inbravo.jar com.inbravo.number.FibonacciNumber 40'
 * 
 * @author amit.dixit
 *
 */
public final class FibonacciNumber {

  public static final void main(String... args) {

    /* Convert the user argument to int */
    final int i = Integer.valueOf(args[0]);

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
