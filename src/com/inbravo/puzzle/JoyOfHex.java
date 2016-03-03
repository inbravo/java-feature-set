package com.inbravo.puzzle;

/**
 * 
 * @author amit.dixit
 *
 */
public final class JoyOfHex {

	public static final void main(final String... args) {

		/* Avoid mixed type conputations; '0xcafebabe' is not a long */
		System.out.println(Long.toHexString(0x100000000L + 0xcafebabe));

		/* Both operands are long now */
		System.out.println(Long.toHexString(0x100000000L + 0xcafebabeL));
	}
}
