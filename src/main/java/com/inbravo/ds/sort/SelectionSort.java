package com.inbravo.ds.sort;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Average execution time = O(N has a power of 2)
 * 
 * @author amit.dixit
 * 
 *         http://mathbits.com/MathBits/Java/arrays/SelectionSort.htm
 */
public final class SelectionSort {

  private static long[] array;

  private static final AtomicInteger index = new AtomicInteger(0);

  public static final void createInstance(final int numberOfElements) {

    /* Create new array with given size */
    array = new long[numberOfElements];
  }

  public static final void add(final long... values) {

    /* Update the array to add more elements */
    final long[] updatedArray = Arrays.copyOf(array, array.length + values.length);

    for (int i = 0; i < values.length; i++) {

      /* Set the value at current index */
      updatedArray[index.incrementAndGet()] = values[i];
    }

    array = updatedArray;
  }

  public static final void add(final long value) {

    /* Update the array to add more elements */
    array = Arrays.copyOf(array, array.length + 1);

    /* Set the value at current index */
    array[index.incrementAndGet()] = value;
  }

  /**
   * Selection Sort
   * 
   * @return
   */
  public static final long[] sort() {

    /* Length of array */
    final int lengthOfArr = array.length - 1;

    /* Index of temporary minimum value */
    int minimum;

    /* Outer Loop (++): Start from start of array */
    for (int outer = 0; outer < lengthOfArr; outer++) {

      /* Temporary minimum = outer loop counter */
      minimum = outer;

      /* Inner Loop (++): Start from Outer+1 untill end of array */
      for (int inner = outer + 1; inner < lengthOfArr; inner++) {

        /* change to > for ascending sort */
        if (array[inner] < array[minimum]) {

          /* Swap the elements */
          minimum = inner;
        }
      }

      /* Swap the elements */
      swapValue(outer, minimum);
    }

    return array;
  }

  private static final void swapValue(final int fromIndex, final int toIndex) {

    /* Store value in temp */
    final long tempVar = array[fromIndex];

    /* Store new value at index */
    array[fromIndex] = array[toIndex];

    /* Store updated value at second location */
    array[toIndex] = tempVar;
  }

  public static void main(String[] args) {

    /* Create a array first */

    array = new long[] {10, 7, 4, 9, 1, 3, 8, 5, 2, 6, 12, 11, 14, 13};

    System.out.println("Before sorting : " + Arrays.toString(array));

    sort();

    System.out.println("After sorting : " + Arrays.toString(array));
  }
}
