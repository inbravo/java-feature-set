package com.inbravo.string;

/**
 * 
 * @author amit.dixit
 *
 */
public final class IdentityHashCode {

	public final static void main(final String... args) {

		/* Has code will be different */
		System.out.println(System.identityHashCode(new String("1")));
		System.out.println(System.identityHashCode(new String("2")));
		System.out.println(System.identityHashCode(new String("1")));

		/* Has code will be same */
		System.out.println(System.identityHashCode("1"));
		System.out.println(System.identityHashCode("1"));
	}
}
