package com.inbravo.jdk8;

import java.util.HashMap;
import java.util.Map;

/**
 * The diamond operator, however, allows the right hand side of the assignment to be defined as a true generic instance with the same type parameters as the left side... 
 * Without having to type those parameters again. It allows you to keep the safety of generics with almost the same effort as using the raw type.
 * 
 * @author amit.dixit
 *
 */
public final class DiamondOperator {

	@SuppressWarnings("unused")
	public static final void main(final String... args) {

		/* 'DIAMOND OPERATOR'(<>) while creating instances of HashMap<>() */
		/* Generic types of HashMap can be 'INFERED' from Map */
		final Map<String, String> doMap = new HashMap<>();

		/* 'DIAMOND OPERATOR' will also work while passing through mathods */
		printHashMap(new HashMap<>());
	}

	private static final void printHashMap(final Map<String, String> doMap) {

		System.out.println(doMap);
	}
}
