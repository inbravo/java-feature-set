package com.inbravo.runtime.hoisting;

import java.util.concurrent.TimeUnit;

/**
 * Hoisting means that you have pulled some operation out of a loop because the loop itself does not
 * affect the result of the operation. In your case, you are hoisting the conditional test out of
 * the while loop.
 * 
 * @author amit.dixit
 *
 */
public final class FailedHoistingTest {

  private static boolean stopRequested;

  public static final void main(final String... args) throws InterruptedException {

    final Thread backgroundThread = new Thread(new Runnable() {

      public void run() {

        @SuppressWarnings("unused")
        int i = 0;

        /*
         * In that case, since the loop doesn't change the value of done, its value can be
         * effectively ignored, and the compiler can hoist the evaluation of that variable outside
         * the loop, preventing it from being evaluated in the "hot" part of the loop. This makes
         * the loop run faster because it has to do less work... jvm converts the code into .. if
         * (!done) { while(true) i++ }
         */
        while (!stopRequested) {
          i++;
        }
      }
    });

    backgroundThread.start();

    TimeUnit.SECONDS.sleep(1);
    stopRequested = true;
  }
}
