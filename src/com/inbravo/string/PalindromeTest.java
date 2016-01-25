package com.inbravo.string;

import java.util.*;

/**
 * 
 * @author amit.dixit
 *
 */
public final class PalindromeTest {

	public static final void main(final String... args) {

		String original, reverse = "";

		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);

		System.out.println("Enter a string to check if it is a palindrome");

		original = in.nextLine();

		int length = original.length();

		for (int i = length - 1; i >= 0; i--)
			reverse = reverse + original.charAt(i);

		if (original.equals(reverse)) {
			System.out.println("Entered string is a palindrome.");
		} else {
			System.out.println("Entered string is not a palindrome.");
		}
	}
}