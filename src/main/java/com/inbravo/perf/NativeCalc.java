package com.inbravo.perf;

/**
 * To analyse how Shift Operator based calculation is faster than Mathmatical Operator based
 * Calculation
 * 
 * @author amit.dixit
 *
 */
public final class NativeCalc {

  public static final int doubleIt(final int value) {

    return (value * 2);
  }

  public static final int nativelyDoubleIt(final int value) {

    return (value << 1);
  }

  public static void main(String[] args) {

    long startTime = System.currentTimeMillis();

    long doubledValue = 0L;

    for (long i = 0; i < 100000; i++) {

      for (long j = 0; j < 100000; j++) {

        doubledValue = doubleIt(22222);
      }
    }

    long endTime = System.currentTimeMillis();

    System.out.println("Doubled Value = " + doubledValue + ", Time consumed in mathmatical operation = " + (endTime - startTime) + " msec(s)");

    startTime = System.currentTimeMillis();

    for (long i = 0; i < 100000; i++) {

      for (long j = 0; j < 100000; j++) {

        doubledValue = nativelyDoubleIt(22222);
      }
    }

    endTime = System.currentTimeMillis();

    System.out.println("Doubled Value = " + doubledValue + ", Time consumed in native operation = " + (endTime - startTime) + " msec(s)");
  }
}
