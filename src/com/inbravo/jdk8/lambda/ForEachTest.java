package com.inbravo.jdk8.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author amit.dixit
 *
 */
public final class ForEachTest {

	public static final void main(final String... args) {

		/* Create an integer array */
		final List<Integer> intArray = Arrays.asList(1, 2, 3, 4, 5);

		/* Pass a Lambda in forEach : Type of 'x' is 'INFERED' by Compiler */
		intArray.forEach(x -> System.out.println(x));

		/* Or just pass a sysout in forEach : Method Referencing */
		intArray.forEach(System.out::println);
	}
}
