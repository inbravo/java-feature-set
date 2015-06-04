package com.inbravo.ds.sort;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author amit.dixit
 * 
 */
public abstract class AbstractSort {

	protected long[] array;

	private final AtomicInteger index = new AtomicInteger(0);

	public AbstractSort(final long[] array) {

		/* Create new array */
		this.createArray();
		this.add(array);
	}

	protected final void createArray() {

		/* Create new array with given size */
		array = new long[0];
	}

	protected final void add(final long... values) {

		/* Update the array to add more elements */
		final long[] updatedArray = Arrays.copyOf(array, array.length
				+ values.length);

		for (int i = 0; i < values.length; i++) {

			/* Set the value at current index */
			updatedArray[index.incrementAndGet()] = values[i];
		}

		array = updatedArray;
	}

	protected final void add(final long value) {

		/* Update the array to add more elements */
		array = Arrays.copyOf(array, array.length + 1);

		/* Set the value at current index */
		array[index.incrementAndGet()] = value;
	}

	protected abstract long[] sort();

	protected final void swapValue(final int fromIndex, final int toIndex) {

		/* Store value in temp */
		final long tempVar = array[fromIndex];

		/* Store new value at index */
		array[fromIndex] = array[toIndex];

		/* Store updated value at second location */
		array[toIndex] = tempVar;
	}
}
