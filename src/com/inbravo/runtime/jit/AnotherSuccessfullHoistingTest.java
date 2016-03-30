package com.inbravo.runtime.jit;

import java.util.concurrent.TimeUnit;

/**
 * 
 * @author amit.dixit
 *
 */
public final class AnotherSuccessfullHoistingTest {

  private static boolean stopRequested;

  private static synchronized void requestStop() {
    stopRequested = true;
  }

  private static synchronized boolean stopRequested() {
    return stopRequested;
  }

  public static final void main(final String... args) throws InterruptedException {

    final Thread backgroundThread = new Thread(new Runnable() {

      public void run() {

        @SuppressWarnings("unused")
        int i = 0;

        while (!stopRequested()) {
          i++;
        }
      }
    });

    backgroundThread.start();

    TimeUnit.SECONDS.sleep(1);
    requestStop();
  }
}
