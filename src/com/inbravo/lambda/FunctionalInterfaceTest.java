package com.inbravo.lambda;

/**
 * 
 * @author amit.dixit
 *
 */
public final class FunctionalInterfaceTest {

	public static final void main(final String... args) {

		final Converter<String, Integer> converter = (from) -> Integer.valueOf(from);

		System.out.println(converter.convert("123"));
	}
}

/**
 * FunctionalInterface can only contain one abstract method
 * 
 * @author amit.dixit
 *
 * @param <From>
 * @param <To>
 */
@FunctionalInterface
interface Converter<From, To> {

	To convert(final From from);
}
