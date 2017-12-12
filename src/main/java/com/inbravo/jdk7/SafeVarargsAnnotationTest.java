/*
 * MIT License
 * 
 * The Java '@SafeVarargs' annotation
 *
 * Copyright (c) 2017 Onwards Amit Dixit (github.com/inbravo)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.inbravo.jdk7;

import java.util.ArrayList;
import java.util.List;

/**
 * This class demonstrates the purpose of '@SafeVarargs'
 * 
 * @author amit.dixit
 */
public final class SafeVarargsAnnotationTest {

  public static final void main(final String... args) {

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
