package com.inbravo.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * 
 * @author amit.dixit
 *
 */
public final class CountDownLatchTest {

	public static final void main(final String... args) throws InterruptedException {

		/* A latch with 3 wait count */
		final CountDownLatch latch = new CountDownLatch(3);

		final Waiter waiter = new Waiter(latch);
		final Decrementer decrementer = new Decrementer(latch);

		/* Start anonymous threads */
		new Thread(waiter).start();
		new Thread(decrementer).start();

		Thread.sleep(4000);
	}
}

/**
 * Inner class to perform work of Waiting Thread
 * 
 * @author amit.dixit
 *
 */
final class Waiter implements Runnable {

	CountDownLatch latch = null;

	public Waiter(final CountDownLatch latch) {
		this.latch = latch;
	}

	public void run() {
		try {

			/* All threads will wait here untill latch count down 3 times */
			latch.await();

			System.out.println("Waiter Released");
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}
}

/**
 * Inner class to perform work of Decrementing Thread
 * 
 * @author amit.dixit
 *
 */
final class Decrementer implements Runnable {

	CountDownLatch latch = null;

	public Decrementer(final CountDownLatch latch) {
		this.latch = latch;
	}

	public void run() {

		try {
			Thread.sleep(1000);

			/* First countdown */
			this.latch.countDown();

			Thread.sleep(1000);

			/* Second countdown */
			this.latch.countDown();

			Thread.sleep(1000);

			/* Third countdown */
			this.latch.countDown();

		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}
}
