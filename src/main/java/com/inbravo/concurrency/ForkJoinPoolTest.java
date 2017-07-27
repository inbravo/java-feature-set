package com.inbravo.concurrency;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Example to demonstrate the performance impact of concurrency
 * 
 * @author amit.dixit
 *
 */
public class ForkJoinPoolTest extends RecursiveAction {

  private static final long serialVersionUID = 1L;

  private int[] list;
  public long result;

  public ForkJoinPoolTest(int[] array) {
    this.list = array;
  }

  @Override
  protected final void compute() {

    if (list.length == 1) {
      result = list[0];
    } else {

      /* Find a mid point */
      int midpoint = list.length / 2;

      /* Split the array into two sub arrays */
      final int[] subListOne = Arrays.copyOfRange(list, 0, midpoint);
      final int[] subListTwo = Arrays.copyOfRange(list, midpoint, list.length);

      /* Create two separate solver tasks for each sub array */
      final ForkJoinPoolTest taskOne = new ForkJoinPoolTest(subListOne);
      final ForkJoinPoolTest taskTwo = new ForkJoinPoolTest(subListTwo);

      /* Fork both sub tasks */
      taskOne.fork();
      taskTwo.fork();

      /* Join both sub tasks */
      taskOne.join();
      taskTwo.join();

      result = taskOne.result + taskTwo.result;
    }
  }

  public static void main(final String... args) {

    /* New problem class */
    final LargeInteger test = new LargeInteger();

    /* Check the number of available processors */
    int processorCores = Runtime.getRuntime().availableProcessors();

    System.out.println("Available processor cores: " + processorCores);

    final ForkJoinPoolTest mfj = new ForkJoinPoolTest(test.getList());

    long startTime = System.nanoTime();

    /* Create new fork-join pool */
    final ForkJoinPool pool = new ForkJoinPool(processorCores);
    pool.invoke(mfj);
    long result = mfj.result;

    final long multiThreadDuration = System.nanoTime() - startTime;
    final double multiThreadSeconds = ((double) multiThreadDuration / 1000000000);
    System.out.format("Multi thread solution time : %f seconds and result: %d ", multiThreadSeconds, result);

    long sum = 0;

    /* Check if the result was ok */
    for (int i = 0; i < test.getList().length; i++) {
      sum += test.getList()[i];
    }
    System.out.println();
    final long singleThreadDuration = System.nanoTime() - startTime;
    final double singleThreadSeconds = ((double) singleThreadDuration / 1000000000);
    System.out.format("Single thread solution time : %f seconds and result: %d ", singleThreadSeconds, sum);
    System.out.println();

    if (multiThreadSeconds < singleThreadSeconds) {
      System.out.println("Multi thread is faster");
    }
  }
}


final class LargeInteger {

  /* Array of integer */
  private final int[] list = new int[2000000];

  public LargeInteger() {

    final Random generator = new Random(19580427);

    for (int i = 0; i < list.length; i++) {
      list[i] = generator.nextInt(500000);
    }
  }

  public int[] getList() {
    return list;
  }
}
