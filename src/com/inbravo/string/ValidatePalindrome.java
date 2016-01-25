package com.inbravo.string;

/**
 * 
 * @author amit.dixit
 *
 */
public class ValidatePalindrome {

	public static boolean isValidPalindrome(String s) {
		if (s == null || s.length() == 0)
			return false;

		s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();// toLowerCase is important

		System.out.println(s);

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
				return false;
			}
		}

		return true;
	}

	public static final void main(final String... args) {
		String str = "A man, a plan, a canal: Panama";

		System.out.println(isValidPalindrome(str));
	}
}