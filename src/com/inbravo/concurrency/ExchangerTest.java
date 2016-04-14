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
    new Thread(new ExchangerRunnable(exchanger, "A")).start();
    new Thread(new ExchangerRunnable(exchanger, "B")).start();

    /* Minimum two threads required for exchanger */
    new Thread(new ExchangerRunnable(exchanger, "C")).start();
    new Thread(new ExchangerRunnable(exchanger, "D")).start();
  }
}


/* New runnable test */
class ExchangerRunnable implements Runnable {

  Exchanger<String> exchanger;
  String object;

  public ExchangerRunnable(final Exchanger<String> exchanger, final String object) {
    this.exchanger = exchanger;
    this.object = object;
  }

  public void run() {
    try {

      final String previous = this.object;

      this.object = this.exchanger.exchange(this.object);

      System.out.println(Thread.currentThread().getName() + " exchanged " + previous + " for " + this.object);

    } catch (final InterruptedException e) {
      e.printStackTrace();
    }
  }
}
