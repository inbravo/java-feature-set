package com.inbravo.string;

public final class StringTest {

	public static final void main(final String[] args) {

		final String a = "A";

		final String b = a + "B";

		final String c = "AB";
		final String d = "A";

		System.out.println(System.identityHashCode(a));
		System.out.println(System.identityHashCode(b));
		System.out.println(System.identityHashCode(c));
		System.out.println(System.identityHashCode(d));
	}
}
