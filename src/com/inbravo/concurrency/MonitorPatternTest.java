package com.inbravo.concurrency;

import java.util.concurrent.Semaphore;


/**
 * 
 * @author amit.dixit
 *
 */
public final class MonitorPatternTest {

  /* Create new Semaphore objects in class */
  final private Semaphore firstLock = new Semaphore(1);
  final private Semaphore secondLock = new Semaphore(1);

  /**
   * 
   * @param args
   */
  public static final void main(final String... args) {

    /* Create new instance of lock test */
    final MonitorPatternTest lockTest = new MonitorPatternTest();

    /* Number of threads */
    for (int i = 0; i < 1000; i++) {

      /* Create first anonymous thread */
      new Thread() {

        public void run() {

          lockTest.threadSafeMethodOne();
        }
      }.start();

      /* Create second anonymous thread */
      new Thread() {

        public void run() {

          lockTest.threadSafeMethodTwo();
        }
      }.start();
    }
  }

  /**
   * First thread is still in critical section and second thread also enters
   * 
   * Output:
   * 
   * First-Thread is inside critical section Second-Thread is inside critical section Second-Thread
   * is out of critical section First-Thread is out of critical section
   */
  private final void threadSafeMethodOne() {

    try {

      /* Acquire the lock */
      firstLock.acquire();

      /* Print current thread info */
      System.out.println(Thread.currentThread().getName() + " is inside critical section of object:" + System.identityHashCode(this)
          + "'s method threadSafeMethodOne at time:[" + System.currentTimeMillis() + "]");

      /* Sleep this thread so that another thread can do the same operation */
      Thread.sleep(1000);

    } catch (final InterruptedException e) {
      e.printStackTrace();
    } finally {

      /* Release the lock */
      firstLock.release();
      System.out.println(Thread.currentThread().getName() + " is out of critical section of object:" + System.identityHashCode(this)
          + "'s method threadSafeMethodOne at time:[" + System.currentTimeMillis() + "]");
    }
  }

  /**
   * Second thread only enters critical section only when first thread is out
   * 
   * Output:
   * 
   * First-Thread is inside critical section First-Thread is out of critical section Second-Thread
   * is inside critical section Second-Thread is out of critical section
   */
  private final void threadSafeMethodTwo() {

    try {

      /* Acquire the lock */
      secondLock.acquire();

      /* Print current thread info */
      System.out.println(Thread.currentThread().getName() + " is inside critical section of object:" + System.identityHashCode(this)
          + "'s method threadSafeMethodTwo at time:[" + System.currentTimeMillis() + "]");

      /* Sleep this thread so that another thread can do the same operation */
      Thread.sleep(1000);

    } catch (final InterruptedException e) {
      e.printStackTrace();
    } finally {

      /* Release the lock */
      secondLock.release();
      System.out.println(Thread.currentThread().getName() + " is out of critical section of object:" + System.identityHashCode(this)
          + "'s method threadSafeMethodTwo at time:[" + System.currentTimeMillis() + "]");
    }
  }
}
