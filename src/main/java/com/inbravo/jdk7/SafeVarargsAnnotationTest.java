package com.inbravo.jdk7;

import java.util.ArrayList;
import java.util.List;

/**
 * This class demonstrates the purpose of '@SafeVarargs'
 * 
 * @author amit.dixit
 */
public final class SafeVarargsAnnotationTest {

  public static void main(final String... args) {

    /* A list of integers */
    final List<Integer> listOfIntegers = new ArrayList<>();
    listOfIntegers.add(1);
    listOfIntegers.add(2);
    listOfIntegers.add(3);
    listOfIntegers.add(4);

    /* A list of floats */
    final List<Float> listOfFloats = new ArrayList<>();
    listOfFloats.add(3.0f);
    listOfFloats.add(1.0f);
    listOfFloats.add(2.0f);
    listOfFloats.add(3.2f);

    /* Scenario 1 - prior to Java 7, usage of code without '@SafeVarargs' */
    withoutSafeVarargsAnnotation(listOfIntegers, listOfFloats);

    /* Scenario 2 - post Java 7, usage of code with '@SafeVarargs' */
    withSafeVarargsAnnotation(listOfIntegers, listOfFloats);
  }

  /**
   * Method without '@SafeVarargs'
   */
  @SuppressWarnings("unchecked")
  private static void withoutSafeVarargsAnnotation(final List<Integer> listOfIntegers, final List<Float> listOfFloats) {

    /* Calling method without '@SafeVarargs' : It generates a warning */
    displayWithoutSafeVarargs(listOfIntegers, listOfFloats);
  }

  /**
   * Method without '@SafeVarargs'
   * 
   * Will show a warning to add '@SafeVarargs'
   */
  private static void withSafeVarargsAnnotation(final List<Integer> listOfIntegers, final List<Float> listOfFloats) {

    displayWithSafeVarargs(listOfIntegers, listOfFloats);
  }

  /* Without '@SafeVarargs' shows warning */
  /* Warning 'Type safety: Potential heap pollution via 'varargs' parameter lists' */
  @SuppressWarnings("unchecked")
  private static <T> void displayWithoutSafeVarargs(final T... lists) {

    /* Iterate on list */
    for (T element : lists) {

      System.out.println("Display of elements without @SafeVarargs Annotation");
      System.out.println(element.getClass().getName() + ": " + element);
    }
  }

  /* With usage of @SafeVarargs the Type Safety warning has been suppressed */
  @SafeVarargs
  private static <T> void displayWithSafeVarargs(final T... lists) {

    /* Iterate on list */
    for (T element : lists) {

      System.out.println("Display of elements with @SafeVarargs Annotation");
      System.out.println(element.getClass().getName() + ": " + element);
    }
  }
}
