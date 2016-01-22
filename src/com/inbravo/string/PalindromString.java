package com.inbravo.string;

public class PalindromString {
	public static void main(String args[]) {
		String str = "saNITInas";
		str = str.toLowerCase();
		char[] ch = str.toCharArray();
		boolean flag = true;
		// int i;
		int j = str.length() - 1;
		for (int i = 0; i < str.length(); i++) {
			if (ch[i] != ch[j])
				flag = false;
			j--;
		}
		if (flag)
			System.out.println("Str is palindrome");
		else
			System.out.println("Str is not palindrome");
	}
}
