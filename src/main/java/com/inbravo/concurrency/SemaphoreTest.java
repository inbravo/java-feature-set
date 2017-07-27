package com.inbravo.concurrency;

import java.util.concurrent.Semaphore;

/**
 * 
 * @author amit.dixit
 *
 */
public final class SemaphoreTest {

  /* Create new Semaphore object in class */
  final private Semaphore lock = new Semaphore(1);

  /* Change this mode before running the program */
  final private static SafetyMode _MODE = SafetyMode.SAFE;

  public static final void main(final String... args) {

    /* Create new instance of lock test */
    final SemaphoreTest lockTest = new SemaphoreTest();

    /* Start first anonymous thread */
    new Thread("First-Thread") {

      @Override
      public void run() {

        if (SafetyMode.SAFE.equals(_MODE)) {

          lockTest.iAmThreadSafe();
        } else {

          lockTest.iAmNotThreadSafe();
        }
      }
    }.start();

    /* Start second anonymous thread */
    new Thread("Second-Thread") {

      @Override
      public void run() {

        if (SafetyMode.SAFE.equals(_MODE)) {

          lockTest.iAmThreadSafe();
        } else {

          lockTest.iAmNotThreadSafe();
        }
      }
    }.start();
  }

  /**
   * First thread is still in critical section and second thread also enters
   * 
   * Output:
   * 
   * First-Thread is inside critical section Second-Thread is inside critical section Second-Thread
   * is out of critical section First-Thread is out of critical section
   */
  private final void iAmNotThreadSafe() {

    try {

      /* Print current thread info */
      System.out.println(Thread.currentThread().getName() + " is inside critical section");

      /* Sleep this thread so that another thread can do the same operation */
      Thread.sleep(1000);

    } catch (final InterruptedException e) {
      e.printStackTrace();
    } finally {
      System.out.println(Thread.currentThread().getName() + " is out of critical section");
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
  private final void iAmThreadSafe() {

    try {

      /* Acquire the lock */
      lock.acquire();

      /* Print current thread info */
      System.out.println(Thread.currentThread().getName() + " is inside critical section");

      /* Sleep this thread so that another thread can do the same operation */
      Thread.sleep(1000);

    } catch (final InterruptedException e) {
      e.printStackTrace();
    } finally {

      /* Release the lock */
      lock.release();
      System.out.println(Thread.currentThread().getName() + " is out of critical section");
    }
  }

  private enum SafetyMode {
    SAFE, UNSAFE;
  }
}
