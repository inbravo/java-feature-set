package com.inbravo.runtime.jit;

/**
 * Run with "-XX:+PrintCompilation -XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining"
 * 
 * @author amit.dixit
 *
 */
public final class MethodInlineTest {

  private static int result;

  public static final void main(final String... args) throws InterruptedException {

    /* Following method call is apt to be inlined */
    result = and(0, 1);

    System.out.println("result: " + result);
  }

  private static final int and(final int first, final int second) {

    return first & second;
  }
}
