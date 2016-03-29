package com.inbravo.runtime.hoisting;

import java.util.concurrent.TimeUnit;

/**
 * 
 * @author amit.dixit
 *
 */
public final class SuccessfulHoistingTest {

  private static volatile boolean stopRequested;

  public static final void main(final String... args) throws InterruptedException {

    final Thread backgroundThread = new Thread(new Runnable() {

      public void run() {

        @SuppressWarnings("unused")
        int i = 0;

        /* Background thread will stop if stop is requested */
        while (!stopRequested) {
          i++;
        }
      }
    });

    backgroundThread.start();

    TimeUnit.SECONDS.sleep(1);

    /* Main thread is changing the status to true */
    stopRequested = true;
  }
}
