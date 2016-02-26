package com.inbravo.jdk8.funcinterf;

/**
 * 
 * @author amit.dixit
 *
 */
public final class ClassWithDefaultMethodUsage implements SubInterfaceWithMethod {

	@Override
	public double calculate(int a) {
		System.out.println("ClassWithDefaultMethodUsage.sqrt");
		return a * a;
	}

	public static final void main(final String... args) {

		/* Create all possible instances */
		final InterfaceWithMethod interfaceWithMethod = new ClassWithDefaultMethodUsage();
		final SubInterfaceWithMethod subInterfaceWithMethod = new ClassWithDefaultMethodUsage();
		final ClassWithDefaultMethodUsage classWithDefaultMethodUsage = new ClassWithDefaultMethodUsage();

		/* Call same method from all objects */
		interfaceWithMethod.sqrt(1);
		subInterfaceWithMethod.sqrt(1);
		classWithDefaultMethodUsage.sqrt(1);
	}
}

/**
 * 
 * @author amit.dixit
 *
 */
interface SubInterfaceWithMethod extends InterfaceWithMethod {

	/**
	 * This method will replace the super interface method
	 */
	default double sqrt(final int a) {
		System.out.println("SubInterfaceWithMethod.sqrt");
		return Math.sqrt(a);
	}
}

/**
 * 
 * @author amit.dixit
 *
 */
interface InterfaceWithMethod {

	double calculate(int a);

	default double sqrt(final int a) {
		System.out.println("InterfaceWithMethod.sqrt");
		return Math.sqrt(a);
	}
}
