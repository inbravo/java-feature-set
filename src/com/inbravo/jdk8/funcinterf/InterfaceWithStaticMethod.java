package com.inbravo.jdk8.funcinterf;

/**
 * 
 * @author amit.dixit
 *
 */
public interface InterfaceWithStaticMethod {

	static double calculate(int a) {

		System.out.println("InterfaceWithMethod.calculate");
		return a * a;
	}

	default double sqrt(final int a) {
		System.out.println("InterfaceWithMethod.sqrt");
		return Math.sqrt(a);
	}

	public static void main(final String... args) {
		InterfaceWithStaticMethod.calculate(1);
	}
}
