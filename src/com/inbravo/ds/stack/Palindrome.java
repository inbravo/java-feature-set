package com.inbravo.ds.stack;

/**
 * 
 * @author amit.dixit
 *
 */
public final class Palindrome {

	public static final void main(final String... args) {

		final String inputString = "KATAK";

		/* Check if reverse of string is palindrom */
		if (checkIfPalindrome(inputString)) {

			System.out.println("User string '" + inputString + "' is found to be a Palindrom");
		} else {

			System.out.println("User string '" + inputString + "' is not a Palindrom");
		}
	}

	/**
	 * 
	 * @param inputString
	 * @return
	 */
	private static final boolean checkIfPalindrome(final String inputString) {

		/* Check if reverse of string is palindrom */
		if (inputString.equals(ReverseTheWord.reverse(inputString))) {

			return true;
		} else {

			return false;
		}
	}
}
