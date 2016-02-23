package com.inbravo.jdk8.funcinterf;

/**
 * 
 * @author amit.dixit
 *
 */
public interface InterfaceWithMethod {

	double calculate(int a);

	default double sqrt(final int a) {
		return Math.sqrt(a);
	}
}
