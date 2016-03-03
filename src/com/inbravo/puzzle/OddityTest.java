package com.inbravo.puzzle;

/**
 * 
 * @author amit.dixit
 *
 */
public final class OddityTest {

	public static final void main(final String... args) {

		System.out.println("1 is odd?: " + isOdd(1));
		System.out.println("2 is odd?: " + isOdd(2));
		System.out.println("3 is odd?: " + isOdd(3));
		System.out.println("4 is odd?: " + isOdd(4));
		System.out.println("0 is odd?: " + isOdd(0));
		System.out.println("-1 is odd?: " + isOdd(-1));
		System.out.println("-2 is odd?: " + isOdd(-2));

		System.out.println("1 is odd?: " + isOddImproved(1));
		System.out.println("2 is odd?: " + isOddImproved(2));
		System.out.println("3 is odd?: " + isOddImproved(3));
		System.out.println("4 is odd?: " + isOddImproved(4));
		System.out.println("0 is odd?: " + isOddImproved(0));
		System.out.println("-1 is odd?: " + isOddImproved(-1));
		System.out.println("-2 is odd?: " + isOddImproved(-2));
	}

	/**
	 * Wrong: it wont work if i = negative
	 * 
	 * @param i
	 * @return
	 */
	public static boolean isOdd(final int i) {

		return i % 2 == 1;
	}

	/**
	 * Bitwise operation
	 * 
	 * @param i
	 * @return
	 */
	public static boolean isOddImproved(final int number) {

		System.out.println("[binary(" + number + ") = '" + Integer.toBinaryString(number) + "', binary(" + 1 + ") = '"
				+ Integer.toBinaryString(1) + "'], [" + number + " & 1 = " + (number & 1) + "]");
		return (number & 1) != 0;
	}
}
