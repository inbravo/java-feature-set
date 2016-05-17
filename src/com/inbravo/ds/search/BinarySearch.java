package com.inbravo.ds.search;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Average execution time = O(Log N)
 * 
 * @author amit.dixit
 * 
 */
public final class BinarySearch {

  private static final AtomicInteger index = new AtomicInteger(0);

  private static long[] array;

  private static int loopCount = 0;

  private BinarySearch() {

  }

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

  public static final void delete(final long value) {

    /* Length of array */
    final int lengArr = array.length;

    boolean elementFound = false;

    /* First find the value in array */
    for (int i = 0; i < lengArr; i++) {

      /* Compare search value with each array value */
      if (value == array[i]) {

        elementFound = true;

        for (int j = i; j < lengArr - 1; j++) {

          /* Delete the value and shift the value one level up */
          array[j] = array[j + 1];
        }
      }
    }

    if (elementFound) {

      /* Update the array to add more elements */
      array = Arrays.copyOf(array, array.length - 1);

      /* Set array index */
      index.set(array.length - 1);
    } else {

      throw new RuntimeException("Cant find: '" + value + "'");
    }
  }

  /**
   * 
   * @param searchValue
   * @return
   */
  public static final int binarySearch(final long searchValue) {

    int searchStart = 0;
    int searchEnd = array.length - 1;
    int searchIndex = 0;

    while (true) {

      /* Increment the loop count */
      loopCount++;

      /* Calculate current index each time */
      searchIndex = (searchStart + searchEnd) / 2;

      /* If search is completed */
      if (searchStart > searchEnd) {

        /* Could not find it */
        throw new RuntimeException("Cant find: '" + searchValue + "'");
      }

      // ===========================================
      // If search value is at MID of Array
      // ===========================================
      if (searchValue == array[searchIndex]) {

        return searchIndex;
      }
      // ===========================================
      // If search value is at UPPER Array
      // ===========================================
      else if (searchValue > array[searchIndex]) {

        searchStart = searchIndex + 1;
      }
      // ===========================================
      // If search value is at LOWER Array
      // ===========================================
      else if (searchValue < array[searchIndex]) {

        searchEnd = searchIndex - 1;
      }
    }
  }


  /**
   * 
   * @param searchValue
   * @param start
   * @param end
   * @return
   */
  public static final int recursiveBinarySearch(final long searchValue, final int start, final int end) {

    /* Check if search value is less than highest value in array */
    if (searchValue > array[array.length - 1]) {

      /* Could not find it */
      throw new RuntimeException("Cant find: '" + searchValue + "'");
    }

    /* Calculate current index each time */
    int currentIndex = (start + end) / 2;

    /* Increment the loop count */
    loopCount++;

    if (searchValue == array[currentIndex]) {
      return currentIndex;

    } else if (searchValue > array[currentIndex]) {

      return recursiveBinarySearch(searchValue, currentIndex + 1, end);
    } else if (searchValue < array[currentIndex]) {

      return recursiveBinarySearch(searchValue, start, currentIndex - 1);
    } else {

      /* Could not find it */
      throw new RuntimeException("Cant find: '" + searchValue + "'");
    }
  }

  /**
   * 
   * @param numberOfElements
   */
  private static final void populate(final int numberOfElements) {

    /* Create new array first */
    createInstance(numberOfElements);

    for (int i = 0; i < numberOfElements; i++) {

      array[i] = i + 1;
    }

    /* Set array index */
    index.set(numberOfElements - 1);
  }

  public static void main(final String... args) {

    /* Create a array first */
    populate(100);
    System.out.println("Before searching : " + Arrays.toString(array));
    System.out.println("Index of item: " + binarySearch(89));
    System.out.println("Number of comparisions for this search: " + loopCount);
  }
}
