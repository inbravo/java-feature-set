package com.inbravo.string;

import java.math.BigInteger;

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

		System.out.println(IdentityHashCode.toHex("amit"));
	}

	/**
	 * 
	 * @param arg
	 * @return
	 */
	private static final String toHex(final String arg) {
		return String.format("%040x", new BigInteger(1, arg.getBytes(/* YOUR_CHARSET? */)));
	}
}
