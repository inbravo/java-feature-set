package com.inbravo.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 
 * @author amit.dixit
 *
 */
public final class CyclicBarrierTest {

  public static final void main(final String... args) throws InterruptedException {

    /* First barrier action */
    final Runnable barrierOneAction = () -> {
      System.out.println("barrierOneAction executed ");
    };

    /* Second barrier action */
    final Runnable barrierTwoAction = () -> {
      System.out.println("barrierTwoAction executed ");
    };

    /* Create new CyclicBarriers which can withhold 2 threads at max */
    final CyclicBarrier CyclicBarrierOne = new CyclicBarrier(2, barrierOneAction);
    final CyclicBarrier CyclicBarrierTwo = new CyclicBarrier(2, barrierTwoAction);

    /* Create new runnable behaviour */
    final Runnable runnable = new CyclicBarrierDoubleRunnable(CyclicBarrierOne, CyclicBarrierTwo);

    /* Create two anonymous threads on CyclicBarrierRunnable */
    new Thread(runnable).start();
    new Thread(runnable).start();

    /*
     * Third thread will never pass from CyclicBarrier because of number of allowed parties is 2
     * only
     */
    // new Thread(runnable).start();
  }
}


/**
 * 
 * @author amit.dixit
 *
 */
final class CyclicBarrierDoubleRunnable implements Runnable {

  private final CyclicBarrier cyclicBarrierOne;
  private final CyclicBarrier cyclicBarrierTwo;

  public CyclicBarrierDoubleRunnable(final CyclicBarrier cyclicBarrierOne, final CyclicBarrier cyclicBarrierTwo) {

    /* Final variables can be assigned by constructor */
    this.cyclicBarrierOne = cyclicBarrierOne;
    this.cyclicBarrierTwo = cyclicBarrierTwo;
  }

  public void run() {
    try {
      Thread.sleep(1000);

      System.out.println(Thread.currentThread().getName() + " waiting at barrier One");
      this.cyclicBarrierOne.await();

      Thread.sleep(1000);
      System.out.println(Thread.currentThread().getName() + " waiting at barrier Two");
      this.cyclicBarrierTwo.await();

      System.out.println(Thread.currentThread().getName() + " done!");

    } catch (final InterruptedException e) {
      e.printStackTrace();
    } catch (final BrokenBarrierException e) {
      e.printStackTrace();
    }
  }
}
