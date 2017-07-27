package com.inbravo.jdk8.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author amit.dixit
 *
 */
public final class ComparatorTest {

  public static final void main(final String... args) {

    System.out.println(oldSort(Arrays.asList("peter", "anna", "mike", "xenia")));
    System.out.println(newSort(Arrays.asList("peter", "anna", "mike", "xenia")));
    System.out.println(Arrays.asList(newSort(new String[] {"peter", "anna", "mike", "xenia"})));
  }

  /**
   * Convention sort using anonymous class
   * 
   * @param inputList
   * @return
   */
  public static final List<String> oldSort(final List<String> inputList) {

    Collections.sort(inputList, new Comparator<String>() {

      @Override
      public int compare(final String a, final String b) {
        return a.compareTo(b);
      }
    });

    return inputList;
  }

  /**
   * Sort using LAMBDA and Collections
   * 
   * @param inputList
   * @return
   */
  public static final List<String> newSort(final List<String> inputList) {

    Collections.sort(inputList, (a, b) -> a.compareTo(b));

    return inputList;
  }

  /**
   * Sort using LAMBDA and Arrays
   * 
   * @param inputList
   * @return
   */
  public static final String[] newSort(final String... inputArray) {

    Arrays.sort(inputArray, String::compareToIgnoreCase);

    return inputArray;
  }
}
