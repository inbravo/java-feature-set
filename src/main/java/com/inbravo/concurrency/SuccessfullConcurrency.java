package com.inbravo.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Run this class several time. you will find only single value of counter 0. AtomicInteger gurantee
 * the variable integrity.
 * 
 * @author amit.dixit
 *
 */
public class SuccessfullConcurrency {

  private static volatile AtomicInteger counter = new AtomicInteger(0);

  private void concurrentMethodCorrect() {

    counter.getAndAdd(5);
    counter.getAndAdd(-5);
  }

  public static final void main(final String... args) {

    /* Create an instance */
    final SuccessfullConcurrency sc = new SuccessfullConcurrency();

    for (int j = 0; j < 1000; j++) {

      for (int i = 0; i < 1000; i++) {

        /* Create anonymous thread */
        new Thread() {
          public void run() {
            sc.concurrentMethodCorrect();
          }
        }.start();
      }
      System.out.println(counter);
    }
  }
}
