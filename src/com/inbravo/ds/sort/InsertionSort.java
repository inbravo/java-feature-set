package com.inbravo.ds.sort;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * Average execution time = O(N has a power of 2) but faster than bubble/selection
 * 
 * @author amit.dixit
 * 
 *         http://mathbits.com/MathBits/Java/arrays/InsertionSort.htm
 */
public final class InsertionSort {

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
   * Insertion Sort
   */
  public static final long[] sort() {

    int outer, inner;

    /* Index of key value */
    long key;

    /* Outer Loop (++): Start from '1' */
    for (outer = 1; outer < array.length; outer++) {

      /* Temporary key = outer loop value */
      key = array[outer];

      /* Inner Loop (--): Start from Outer-1 untill end of array */
      for (inner = outer - 1; (inner >= 0) && (array[inner] < key); inner--) {

        /* Shift the elements */
        array[inner + 1] = array[inner];
      }

      /* Shjift the key */
      array[inner + 1] = key;
    }

    return array;
  }

  public static final void main(final String... args) {

    /* Create a array first */
    array = new long[] {10, 7, 4, 9, 1, 3, 8, 5, 2, 6, 12, 11, 14, 13};

    System.out.println("Before sorting : " + Arrays.toString(array));

    sort();

    System.out.println("After sorting : " + Arrays.toString(array));
  }
}
