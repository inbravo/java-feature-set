package com.inbravo.jdk8.lambda.perf;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.function.IntBinaryOperator;

/**
 * Code id a reference from StackOverFlow
 * 
 * @author amit.dixit
 *
 */
public final class TestMethodPerf {

	/* JDK 7 allows Underscore in literals */
	private static final int ITERATIONS = 50_000_000;
	private static final int WARM_UP = 10;

	public static final void main(final String... args) throws Throwable {

		// hold result to prevent too much optimizations
		final int[] dummy = new int[4];

		/* Get method using reflection */
		final Method reflected = TestMethodPerf.class.getDeclaredMethod("myMethod", int.class, int.class);

		/* Get method using MH */
		final MethodHandles.Lookup lookup = MethodHandles.lookup();
		final MethodHandle methodHandle = lookup.unreflect(reflected);

		/* Get method using Lambda Meta Factory */
		final IntBinaryOperator lambda = (IntBinaryOperator) LambdaMetafactory
				.metafactory(lookup, "applyAsInt", MethodType.methodType(IntBinaryOperator.class), methodHandle.type(), methodHandle,
						methodHandle.type()).getTarget().invokeExact();

		/* Warmup before actual test of all types of method access */
		for (int i = 0; i < WARM_UP; i++) {

			/* Call direct access */
			dummy[0] += testDirect(dummy[0]);

			/* Call lambda access */
			dummy[1] += testLambda(dummy[1], lambda);

			/* Call method handle access */
			dummy[2] += testMethodHandle(dummy[1], methodHandle);

			/* Call reflection access */
			dummy[3] += testReflection(dummy[2], reflected);
		}

		/* Curret system time */
		final long t0 = System.nanoTime();

		/* Call direct access */
		dummy[0] += testDirect(dummy[0]);
		final long t1 = System.nanoTime();

		/* Call direct access */
		dummy[1] += testLambda(dummy[1], lambda);
		final long t2 = System.nanoTime();

		/* Call direct access */
		dummy[2] += testMethodHandle(dummy[1], methodHandle);
		final long t3 = System.nanoTime();

		/* Call direct access */
		dummy[3] += testReflection(dummy[2], reflected);
		final long t4 = System.nanoTime();

		/* Devide by 1e-9 (means 10^-9 : 1 nano second) to convert to second */
		/* Calculate the processing time */
		System.out.printf("direct: %.2fs, lambda: %.2fs, method handel: %.2fs, reflection: %.2fs%n", (t1 - t0) * 1e-9, (t2 - t1) * 1e-9,
				(t3 - t2) * 1e-9, (t4 - t3) * 1e-9);
	}

	/**
	 * 
	 * @param v
	 * @param mh
	 * @return
	 * @throws Throwable
	 */
	private final static int testMethodHandle(int v, final MethodHandle mh) throws Throwable {
		for (int i = 0; i < ITERATIONS; i++) {
			v += (int) mh.invokeExact(1000, v);
		}
		return v;
	}

	/**
	 * 
	 * @param v
	 * @param mh
	 * @return
	 * @throws Throwable
	 */
	private final static int testReflection(int v, final Method mh) throws Throwable {
		for (int i = 0; i < ITERATIONS; i++) {
			v += (int) mh.invoke(null, 1000, v);
		}
		return v;
	}

	/**
	 * 
	 * @param v
	 * @return
	 */
	private final static int testDirect(int v) {
		for (int i = 0; i < ITERATIONS; i++) {
			v += myMethod(1000, v);
		}
		return v;
	}

	/**
	 * 
	 * @param v
	 * @param accessor
	 * @return
	 */
	private final static int testLambda(int v, final IntBinaryOperator accessor) {
		for (int i = 0; i < ITERATIONS; i++) {
			v += accessor.applyAsInt(1000, v);
		}
		return v;
	}

	/* This method of subject of experiment */
	private final static int myMethod(final int a, final int b) {
		return a < b ? a : b;
	}
}
