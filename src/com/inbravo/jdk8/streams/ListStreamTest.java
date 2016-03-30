package com.inbravo.jdk8.streams;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author amit.dixit
 *
 */
public final class ListStreamTest {

  public static final void main(final String... args) {

    final List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");

    /* Print only those strings which starts with char 'c' in sorted order */
    myList.stream().filter(s -> s.startsWith("c")).map(String::toUpperCase).sorted().forEach(System.out::println);

    /* Count number of elements. Method count is known as 'EAGER' */
    System.out.println("Number of elements: " + myList.parallelStream().filter(s -> s.startsWith("c")).map(String::toUpperCase).sorted().count());
  }
}
