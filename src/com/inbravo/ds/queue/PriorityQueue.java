package com.inbravo.ds.queue;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author amit.dixit
 *
 */
public final class PriorityQueue {

	/* Array for local storage */
	private long[] storage;

	/* Maximum size limit of queue */
	private int maxSize;

	/* Numbers of item in queue */
	private static final AtomicInteger currentItemsCount = new AtomicInteger(0);

	public PriorityQueue(final int maxSize) {

		/* Create array with given size of queue */
		storage = new long[maxSize];

		/* Set max size */
		this.maxSize = maxSize;
	}

	/**
	 * Insert at a suitable position of queue
	 * 
	 * @param value
	 */
	public final void insert(final long value) {

		/* If internal storage is empty */
		if (currentItemsCount.get() == 0) {

			/* Store the value at first position */
			storage[currentItemsCount.getAndIncrement()] = value;
		}
		/* Fit at correct position */
		else {
			int i;

			/* Iterate of existing elemens array */
			for (i = currentItemsCount.get() - 1; i >= 0; i--) {

				/* Check if insertion value is greater then current index value */
				if (value < storage[i]) {

					/* Shift elements to one index higher */
					storage[i + 1] = storage[i];
				} else {
					break;
				}
			}

			/* Put insertion value at cavity created by last loop */
			storage[i + 1] = value;

			/* Increment the current items count */
			currentItemsCount.incrementAndGet();
		}
	}

	/**
	 * Remove from FRONT of queue
	 * 
	 * @return
	 */
	public final long remove() {

		/* Reset the current front value and increment */
		return storage[currentItemsCount.decrementAndGet()];
	}

	public final long peekFront() {

		/* Return value at current index */
		return storage[currentItemsCount.get()];
	}

	public final boolean isFull() {

		return (currentItemsCount.get() == maxSize);
	}

	public final boolean isEmpty() {

		return (currentItemsCount.get() == 0);
	}

	@Override
	public final String toString() {

		return (Arrays.toString(storage));
	}

	public static final void main(final String[] args) {

		/* Create new queue */
		final PriorityQueue queue = new PriorityQueue(10);

		/* Check for full */
		System.out.println("Queue is full ? " + queue.isFull());

		/* Check for empty */
		System.out.println("Queue is empty ? " + queue.isEmpty());

		System.out.println("Before element insert : " + queue);

		/* Insert items on queue */
		queue.insert(1);
		System.out.println("After element inserting value '1' : " + queue);
		queue.insert(3);
		System.out.println("After element inserting value '3' : " + queue);
		queue.insert(4);
		System.out.println("After element inserting value '4' : " + queue);
		queue.insert(2);
		System.out.println("After element inserting value '2' : " + queue);
	}
}
