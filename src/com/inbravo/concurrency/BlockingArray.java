package com.inbravo.concurrency;

import java.util.Arrays;
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
	final private Condition waitingToDeposit = lock.newCondition();

	/* Array is NOT EMPTY */
	final private Condition waitingToFetch = lock.newCondition();

	/* Size of array */
	final private int maxSize;

	/* Array of items */
	final private Object[] items;

	/* Current counter */
	private int currentIndex;

	/* Array counter */
	private static int counter;

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

				/* Force this thread to await untill a fetch */
				waitingToDeposit.await();
			}

			/* Put the value in array after incrementing the current index */
			items[currentIndex] = newObject;

			/* Increment the current index */
			currentIndex++;

			/* Send signal to threads; waiting to fetch */
			waitingToFetch.signal();
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

				/* Force this thread to await untill a deposit */
				waitingToFetch.await();
			}

			/* Put the value in array */
			final Object existingObject = items[currentIndex];

			/* Decrement the current index */
			currentIndex--;

			/* Send signal to threads; waiting to deposit */
			waitingToDeposit.signal();

			return existingObject;
		} finally {

			/* Release lock */
			lock.unlock();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BlockingArray [items=" + Arrays.toString(items) + "]";
	}

	public static final void main(final String... args) throws InterruptedException {

		/* Create new instance of lock test */
		final BlockingArray bArray = new BlockingArray(1000);

		for (counter = 0; counter < 1000; counter++) {

			/* Start first anonymous thread */
			new Thread("Fetcher") {

				@Override
				public void run() {

					try {

						/* Fetch from array */
						bArray.fetch();
					} catch (final InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();

			/* Start second anonymous thread */
			new Thread("Depositor") {

				@Override
				public void run() {

					try {
						/* Put in array */
						bArray.deposit("Object-" + counter);
					} catch (final InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
	}
}
