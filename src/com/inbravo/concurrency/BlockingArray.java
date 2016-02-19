package com.inbravo.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author amit.dixit
 *
 */
public final class BlockingArray {

	/* Create a new lock in Composition manner */
	final private Lock lock = new ReentrantLock();

	/* Array is NOT FULL */
	final private Condition notFull = lock.newCondition();

	/* Array is NOT EMPTY */
	final private Condition notEmpty = lock.newCondition();

	/* Size of array */
	final private int maxSize;

	/* Array of items */
	final private Object[] items;

	/* Current counter */
	private int currentIndex;

	public BlockingArray(final int size) {

		this.maxSize = size;
		this.items = new Object[size];
	}

	/**
	 * 
	 * @param x
	 * @throws InterruptedException
	 */
	public final void deposit(final Object newObject) throws InterruptedException {

		System.out.println("[Thread: " + Thread.currentThread().getName() + "] Depositing: " + newObject);

		/* Acquire lock */
		lock.lock();

		try {
			/* If array is full */
			while (this.currentIndex == this.maxSize) {

				System.out.println("[Thread: " + Thread.currentThread().getName() + "] Array is Full!");

				/* WAIT ... Array is not empty */
				notFull.await();
			}

			/* Put the value in array after incrementing the current index */
			items[currentIndex] = newObject;

			/* Increment the current index */
			currentIndex++;

			/* Send signal to all NOT-EMPTY waiting threads */
			notEmpty.signal();
		} finally {

			/* Release lock */
			lock.unlock();
		}
	}

	/**
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public final Object fetch() throws InterruptedException {

		System.out.println("[Thread: " + Thread.currentThread().getName() + "] Fetching");

		/* Acquire lock */
		lock.lock();

		try {
			/* If array is empty */
			while (this.currentIndex == 0) {

				System.out.println("[Thread: " + Thread.currentThread().getName() + "] Array is Empty!");

				/* WAIT ... Array is empty */
				notEmpty.await();
			}

			/* Put the value in array */
			final Object existingObject = items[currentIndex];

			/* Decrement the current index */
			currentIndex--;

			/* Send signal to all NOT-FULL waiting threads */
			notFull.signal();

			return existingObject;
		} finally {

			/* Release lock */
			lock.unlock();
		}
	}

	public static final void main(final String... args) {

		/* Create new instance of lock test */
		final BlockingArray array = new BlockingArray(100);

		/* Start second anonymous thread */
		new Thread("Fetcher") {

			@Override
			public void run() {

				for (int i = 0; i < 100; i++) {
					try {

						/* Fetch from array */
						array.fetch();
					} catch (final InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();

		/* Start first anonymous thread */
		new Thread("Depositor") {

			@Override
			public void run() {

				for (int i = 0; i < 100; i++) {
					try {
						/* Put in array */
						array.deposit("Object-" + i);
					} catch (final InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}
