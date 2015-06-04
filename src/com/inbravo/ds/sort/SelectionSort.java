package com.inbravo.ds.sort;

import java.util.Arrays;

/**
 * 
 * @author amit.dixit
 * 
 */
public final class SelectionSort extends AbstractSort {

	public SelectionSort(final long[] array) {
		super(array);
	}

	public final long[] sort() {

		/* Length of array */
		final int lengthOfArr = super.array.length - 1;

		/* Start from end of array */
		for (int outer = lengthOfArr; outer > 0; outer--) {

			for (int inner = 0; inner < outer; inner++) {

				/* change to > for ascending sort */
				if (super.array[inner] < super.array[inner + 1]) {

					/* Swap the elements */
					super.swapValue(inner, inner + 1);
				}
			}
		}

		return super.array;
	}

	public static void main(final String[] args) {

		/* Create new sort type object */
		final AbstractSort sort = new SelectionSort(new long[] { 10, 7, 4, 9,
				1, 3, 8, 5, 2, 6, 12, 11, 14, 13 });

		System.out.println("After sorting : " + Arrays.toString(sort.sort()));
	}
}
