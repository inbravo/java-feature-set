package com.inbravo.ds.stack;

import java.util.Arrays;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Average execution time = O(1)
 * https://github.com/inbravo/ds/blob/master/src/com/inbravo/ds/stack/Stack.java
 * 
 * Java: class, constructor, static and instance variables, methods, Arrays and
 * AtomicInteger
 * 
 * @author amit.dixit
 *
 */
public final class Stack {

	/* Array for local storage */
	private long[] storage;

	/* Current index stack */
	private static final AtomicInteger currentIndex = new AtomicInteger(-1);

	/* Maximum size limit of Stack */
	private int maxSize;

	/**
	 * Constructor
	 * 
	 * @param maxSize
	 */
	public Stack(final int maxSize) {

		/* Create array with given size of stack */
		storage = new long[maxSize];

		/* Set max size */
		this.maxSize = maxSize;
	}

	/**
	 * Method or API
	 * 
	 * @param value
	 */
	public final void push(final long value) {

		storage[currentIndex.incrementAndGet()] = value;
	}

	/**
	 * Method or API
	 * 
	 * @param value
	 */
	public final long pop() {

		/* Return value at current index */
		final long poppedValue = storage[currentIndex.get()];

		/* Reset the current index */
		storage[currentIndex.getAndDecrement()] = 0;

		return poppedValue;
	}

	/**
	 * Method or API
	 * 
	 * @param value
	 */
	public final long peek() {

		/* Return value at current index */
		return storage[currentIndex.get()];
	}

	/**
	 * Method or API
	 * 
	 * @param value
	 */
	public final boolean isFull() {

		return (currentIndex.get() == (maxSize - 1));
	}

	/**
	 * Method or API
	 * 
	 * @param value
	 */
	public final boolean isEmpty() {

		return (currentIndex.get() == -1);
	}

	@Override
	public final String toString() {

		return (Arrays.toString(storage));
	}

	/**
	 * Method or API
	 * 
	 * @param value
	 */
	public static final void main(final String[] args) {

		/* Create new stack */
		final Stack stack = new Stack(10);

		/* Check for full */
		System.out.println("Stack is full ? " + stack.isFull());

		/* Check for empty */
		System.out.println("Stack is empty ? " + stack.isEmpty());

		System.out.println("Before element push : " + stack);

		for (int i = 0; i < 10; i++) {

			/* Push items on stack */
			stack.push(i + 10);
		}

		System.out.println("After element push : " + stack);

		while (!stack.isEmpty()) {
			System.out.println("Popped " + stack.pop());
		}

		System.out.println("After element pop : " + stack);
	}
}
