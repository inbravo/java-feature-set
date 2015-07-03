package com.inbravo.ds.string;

public class StringHashTest {

	public static void main(final String[] args) {
		System.out.println(System.identityHashCode(new String("1")));
		System.out.println(System.identityHashCode(new String("2")));
		System.out.println(System.identityHashCode(new String("1")));
		System.out.println(System.identityHashCode("1"));
		System.out.println(System.identityHashCode("1"));
	}
}
