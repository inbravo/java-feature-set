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
		if (inputString.equals(reverse(inputString))) {

			return true;
		} else {

			return false;
		}
	}

	/**
	 * 
	 * @param inputString
	 * @return
	 */
	private static final String reverse(final String inputString) {

		/* Check if string is palindrom using stack */
		final Stack stack = new Stack(inputString.length());

		/* Push the char in stack */
		for (int i = 0; i < inputString.length(); i++) {

			/* Get each char */
			final char ch = inputString.charAt(i);

			/* Push on stack */
			stack.push(ch);
		}

		/* Reverse of input string */
		String reverse = "";

		for (int i = 0; i < inputString.length(); i++) {

			/* Pop from stack */
			final char poppedValue = (char) stack.pop();

			/* Add this char in string */
			reverse = reverse.concat("" + poppedValue);
		}

		return reverse;
	}
}
