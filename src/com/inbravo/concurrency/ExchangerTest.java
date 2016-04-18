package com.inbravo.concurrency;

import java.util.concurrent.Exchanger;

/**
 * 
 * @author amit.dixit
 *
 */
public final class ExchangerTest {

  public static void main(final String... args) {

    final Exchanger<String> exchanger = new Exchanger<String>();

    /* Start new threads */
    new Thread(new ExchangerRunnable(exchanger, "A"), "One").start();
    new Thread(new ExchangerRunnable(exchanger, "B"), "Two").start();

    /* Minimum two threads required for exchanger */
    new Thread(new ExchangerRunnable(exchanger, "C"), "Three").start();
    new Thread(new ExchangerRunnable(exchanger, "D"), "Four").start();
  }
}


/* New runnable test */
class ExchangerRunnable implements Runnable {

  Exchanger<String> exchanger;
  String latest;

  public ExchangerRunnable(final Exchanger<String> exchanger, final String object) {
    this.exchanger = exchanger;
    this.latest = object;
  }

  public void run() {
    try {

      /* Save latest object as previous */
      final String previous = this.latest;

      /* Get latest by exchanging */
      this.latest = this.exchanger.exchange(this.latest);

      System.out.println(Thread.currentThread().getName() + " exchanged " + previous + " for " + this.latest);

    } catch (final InterruptedException e) {
      e.printStackTrace();
    }
  }
}
