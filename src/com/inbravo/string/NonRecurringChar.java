package com.inbravo.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class NonRecurringChar {
	public static void main(String args[]) {
		String str = "adaasmedey";
		System.out.println("str substring=" + str.substring(1, 3));
		System.out.println("char = " + getNonRecChar(str));
		System.out.println("char = " + getNonRecCharOptimize(str));
		System.out.println("string = " + getNonRecStringOptimize(str));
		System.out.println("string = " + getNonRecString2(str));
	}

	public static Character getNonRecChar(String str) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		// char[] charArr = str.toCharArray();
		char c = 0;
		System.out.println("char 0" + c);
		for (int i = 0; i < str.length(); i++) {
			char chr = str.charAt(i);
			if (map.containsKey(chr))
				map.put(chr, map.get(chr) + 1);
			else
				map.put(chr, 1);
		}
		for (int i = 0; i < str.length(); i++) {
			char chr = str.charAt(i);
			if (map.get(chr) == 1) {
				c = chr;
				break;
			}
		}
		return c;
	}

	public static Character getNonRecCharOptimize(String str) {
		HashMap<Character, Integer> map = new LinkedHashMap<Character, Integer>();
		// char[] charArr = str.toCharArray();
		char c = 0;
		System.out.println("char 0" + c);
		for (int i = 0; i < str.length(); i++) {
			char chr = str.charAt(i);
			if (map.containsKey(chr))
				map.put(chr, map.get(chr) + 1);
			else
				map.put(chr, 1);
		}
		/*
		 * for(int i=0; i<str.length();i++){ char chr = str.charAt(i); if(map.get(chr) == 1){ c = chr; break; } }
		 */

		for (Entry<Character, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 1) {
				c = entry.getKey();
				break;
			}
		}
		return c;
	}

	public static String getNonRecStringOptimize(String str) {
		HashMap<Character, Integer> map = new LinkedHashMap<Character, Integer>();
		// char[] charArr = str.toCharArray();
		String nonRecString = new String();
		// System.out.println("char 0" + c);
		for (int i = 0; i < str.length(); i++) {
			char chr = str.charAt(i);
			if (map.containsKey(chr))
				map.put(chr, map.get(chr) + 1);
			else
				map.put(chr, 1);
		}
		/*
		 * for(int i=0; i<str.length();i++){ char chr = str.charAt(i); if(map.get(chr) == 1){ c = chr; break; } }
		 */
		for (Entry<Character, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 1) {
				// c= entry.getKey();
				// break;
				nonRecString = nonRecString + entry.getKey();
			}
		}
		return nonRecString;
	}

	public static String getNonRecString2(String str) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		// char[] charArr = str.toCharArray();
		// char c = 0;
		// System.out.println("char 0" + c);
		String s = new String();
		for (int i = 0; i < str.length(); i++) {
			char chr = str.charAt(i);
			if (map.containsKey(chr))
				map.put(chr, map.get(chr) + 1);
			else
				map.put(chr, 1);
		}
		/*
		 * for(int i=0; i<str.length();i++){ char chr = str.charAt(i); if(map.get(chr) == 1){ c = chr; break; } }
		 */
		for (Entry<Character, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 1) {
				s = s + entry.getKey();
			}
		}
		return s;
	}

	public static char firstNonRepeatingChar(String word) {
		Set<Character> repeating = new HashSet<Character>();
		List<Character> nonRepeating = new ArrayList<Character>();
		for (int i = 0; i < word.length(); i++) {
			char letter = word.charAt(i);
			if (repeating.contains(letter)) {
				continue;
			}
			if (nonRepeating.contains(letter)) {
				// nonRepeating.remove((Character) letter);
				nonRepeating.remove(letter);
				repeating.add(letter);
			} else {
				nonRepeating.add(letter);
			}
		}
		return nonRepeating.get(0);
	}
}
