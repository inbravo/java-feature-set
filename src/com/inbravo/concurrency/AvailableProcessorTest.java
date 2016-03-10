package com.inbravo.concurrency;

/**
 * 
 * @author amit.dixit
 *
 */
public final class AvailableProcessorTest {

	public static final void main(final String... args) {

		final int numProcessores = Runtime.getRuntime().availableProcessors();

		System.out.println("Processor has " + numProcessores + " cores");
	}
}
