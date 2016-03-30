package com.inbravo.concurrency;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * @author amit.dixit
 *
 */
public final class TimerTest {

  public final static void main(final String... args) {

    /* Create new timer */
    final Timer timer = new Timer();

    /* Add new timer task */
    timer.schedule(new TimerTask() {

      @Override
      public final void run() {
        System.out.println("------");
      }

    }, 0, 3000);
  }
}
