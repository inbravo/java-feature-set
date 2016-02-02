package com.inbravo.lambda;

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
