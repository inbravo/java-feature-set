package com.inbravo.ds.string;

public final class StringTest {

	public static final void main(final String[] args) {

		String a = "A";

		String b = a + "B";

		String c = "AB";
		String d = "AB";

		System.out.println("a location : " + Integer.toHexString(a.hashCode()));
		System.out.println("b location : " + Integer.toHexString(b.hashCode()));
		System.out.println("c location : " + Integer.toHexString(c.hashCode()));
		System.out.println(b == c);
		System.out.println(c == d);
	}
}
