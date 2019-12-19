package com.inbravo.jdk8.lambda;

/**
 * 
 * @author amit.dixit
 *
 */
public final class ThreadSleepTest {

  public static final void main(final String... args) {

    /* Runnable is a functional interface now (java.lang.FunctionalInterface) */
    final Runnable sleeper = () -> {

      try {
        for (int i = 0; i < 100; i++) {

          System.out.println("Zzz");
          Thread.sleep(1000);
        }
      } catch (final InterruptedException e) {

        e.printStackTrace();
      }
    };

    /* Start new thread */
    new Thread(sleeper).start();
  }
}
