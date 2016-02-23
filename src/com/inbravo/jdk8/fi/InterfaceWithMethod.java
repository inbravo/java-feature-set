package com.inbravo.jdk8.fi;

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
