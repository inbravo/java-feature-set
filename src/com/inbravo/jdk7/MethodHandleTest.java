package com.inbravo.jdk7;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * 
 * @author amit.dixit
 *
 */
public final class MethodHandleTest {

	public static final void main(final String... args) throws Throwable {

		/* Create new default lookup */
		final MethodHandles.Lookup lookup = MethodHandles.lookup();

		/* Create new method handle for hello method of MethodHandleTest class */
		final MethodHandle mh = lookup.findStatic(MethodHandleTest.class, "hello", MethodType.methodType(void.class));

		/* Invoke the method */
		mh.invokeExact();
	}

	@SuppressWarnings("unused")
	private static final void hello() {
		System.out.println("hello");
	}
}
