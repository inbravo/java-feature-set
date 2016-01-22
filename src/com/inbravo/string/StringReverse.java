package com.inbravo.string;

import java.io.FileNotFoundException;
import java.io.IOException;

public class StringReverse {

	public static void main(String args[]) throws FileNotFoundException, IOException {

		// original string
		String str = "Ashu";
		System.out.println("Original String: " + str);
		String reverseStr2;
		// reversed string using Stringbuffer
		String reverseStr = new StringBuffer(str).reverse().toString();
		System.out.println("Reverse String in Java using StringBuffer: " + reverseStr);

		// iterative method to reverse String in Java
		reverseStr = reverse(str);
		System.out.println("Reverse String in Java using Iteration: " + reverseStr);

		// recursive method to reverse String in Java
		reverseStr2 = reverseRecursively(str);
		System.out.println("Reverse String in Java using Recursion: " + reverseStr2);

	}

	public static String reverse(String str) {
		StringBuilder strBuilder = new StringBuilder();
		char[] strChars = str.toCharArray();

		for (int i = strChars.length - 1; i >= 0; i--) {
			strBuilder.append(strChars[i]);
		}

		return strBuilder.toString();
	}

	public static String reverseRecursively(String str) {

		// base case to handle one char string and empty string
		if (str.length() < 2) {
			// System.out.println("str="+str);
			return str;
		}
		// System.out.println(reverseRecursively(str.substring(1)) + str.charAt(0));
		return reverseRecursively(str.substring(1)) + str.charAt(0);

	}
}