package com.inbravo.ds.queue;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author amit.dixit
 *
 */
public final class CircularQueue {

	/* Array for local storage */
	private long[] storage;

	/* Maximum size limit of queue */
	private int maxSize;

	/* Numbers of item in queue */
	private static final AtomicInteger currentItemsCount = new AtomicInteger(0);

	/* Front of queue */
	private static final AtomicInteger front = new AtomicInteger(0);

	/* Rear of queue */
	private static final AtomicInteger rear = new AtomicInteger(-1);

	public CircularQueue(final int maxSize) {

		/* Create array with given size of queue */
		storage = new long[maxSize];

		/* Set max size */
		this.maxSize = maxSize;
	}

	/**
	 * Insert at REAR ot queue
	 * 
	 * @param value
	 */
	public final void insert(final long value) {

		/* Insert only if Queue is not FULL */
		if (!isFull()) {

			/* Make the queue circular */
			if (rear.get() == (maxSize - 1)) {

				/* Reset the rear */
				rear.set(-1);
			}

			/* Set the value next to rear */
			storage[rear.incrementAndGet()] = value;

			/* Increment the current items count */
			currentItemsCount.incrementAndGet();
		} else {

			/* Throw error */
			throw new RuntimeException("Queue is full");
		}
	}

	/**
	 * Remove from FRONT of queue
	 * 
	 * @return
	 */
	public final long remove() {

		/* Remove only if Queue is not EMPTY */
		if (!isEmpty()) {

			/* Take value at front */
			final long removedValue = storage[front.get()];

			/* Make the queue circular */
			if (front.get() == maxSize) {

				/* Reset the front */
				front.set(0);
			}

			/* Decrement the current items count */
			currentItemsCount.getAndDecrement();

			/* Reset the current front value and increment */
			storage[front.getAndIncrement()] = 0;

			return removedValue;
		} else {

			/* Throw error */
			throw new RuntimeException("Queue is empty");
		}

	}

	public final long peekFront() {

		/* Return value at current index */
		return storage[front.get()];
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
		final Queue queue = new Queue(10);

		/* Check for full */
		System.out.println("Queue is full ? " + queue.isFull());

		/* Check for empty */
		System.out.println("Queue is empty ? " + queue.isEmpty());

		System.out.println("Before element insert : " + queue);

		for (int i = 0; i < 10; i++) {

			/* Insert items on queue */
			queue.insert(i + 10);
		}

		System.out.println("After element insert : " + queue);

		while (!queue.isEmpty()) {
			System.out.println("Removed " + queue.remove());
		}

		System.out.println("After element remove : " + queue);
	}
}
