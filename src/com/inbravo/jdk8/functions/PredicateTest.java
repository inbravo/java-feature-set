package com.inbravo.jdk8.functions;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 
 * @author amit.dixit
 *
 */
public final class PredicateTest {

	public static final void main(final String... args) {

		/* Create a predicate to return true only for numbers greater than 5 */
		final Predicate<Integer> atLeast5 = (x) -> x > 5;

		/* Create a list of numberss */
		final List<Integer> integers = Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });

		/* Weedout integers which has value less than 5 */
		final List<Integer> filteredIntegers = integers.stream().filter(atLeast5).collect(Collectors.<Integer> toList());

		System.out.println(filteredIntegers);
	}
}
