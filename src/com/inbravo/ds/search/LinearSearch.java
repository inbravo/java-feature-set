package com.inbravo.ds.search;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author amit.dixit
 *
 */
public final class LinearSearch {

  private static long[] array;

  private static final AtomicInteger index = new AtomicInteger(0);

  public static final void createInstance(final int numberOfElements) {

    /* Create new array with given size */
    array = new long[numberOfElements];
  }

  public static final void add(final long value) {

    /* Update the array to add more elements */
    array = Arrays.copyOf(array, array.length + 1);

    /* Set the value at current index */
    array[index.incrementAndGet()] = value;
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

  private static final void populate(final int numberOfElements) {

    /* Create new array first */
    createInstance(numberOfElements);

    for (int i = 0; i < numberOfElements; i++) {

      array[i] = i;
    }

    /* Set array index */
    index.set(numberOfElements - 1);
  }

  /**
   * 
   * @param searchValue
   * @return
   */
  public static final int linearSearch(final long searchValue) {

    /* Iterate over array */
    for (int i = 0; i < array.length; i++) {

      /* Check for value */
      if (searchValue == array[i]) {
        return i;
      }
    }

    /* Could not find it */
    throw new RuntimeException("Cant find: '" + searchValue + "'");
  }

  public static void main(String[] args) {

    /* Create a array first */
    populate(100);
    System.out.println("Before searching : " + Arrays.toString(array));
    System.out.println(linearSearch(909));

  }
}
