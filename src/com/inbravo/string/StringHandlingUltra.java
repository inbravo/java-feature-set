package com.inbravo.string;

import java.util.*;
import java.util.Map.Entry;

public class StringHandlingUltra {
	public static void main(String args[]) {
		String str = "My name. is AnubhAvvvV. ";
		// System.out.println(handleString(str));
		handleString(str);
	}

	public static void handleString(String str) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		// String output = "";
		for (int i = 0; i < str.length(); i++) {
			if (map.containsKey(str.charAt(i)))
				map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
			else
				map.put(str.charAt(i), 1);
		}
		/*
		 * List<Integer> list = new ArrayList<Integer>(); list = (List<Integer>) map.values(); Collections.sort(list);
		 */
		// Collections.sort(map, new CustomComparator());
		/*
		 * for(Integer i : list){ Entry<Character,Integer> entry System.out.println(); }
		 */
		Map<Integer, List<Character>> map2 = new TreeMap<Integer, List<Character>>();
		for (Entry<Character, Integer> entry : map.entrySet()) {
			List<Character> list = new ArrayList<Character>();
			if (map2.containsKey(entry.getValue())) {
				list = map2.get(entry.getValue());
			}
			list.add(entry.getKey());
			map2.put(entry.getValue(), list);
		}

		// output = map2.toString();

		// Set<Integer> keySet = map2.keySet();
		// Integer[] arr = (Integer[])keySet.toArray();

		// Integer[] arr = (Integer[]) map2.keySet().toArray();
		// Arrays.sort(arr,Collections.reverseOrder());

		// Integer[] arr = map2.keySet().toArray();

		Integer[] arr = new Integer[map2.size()];
		int i = 0;
		for (Integer intValue : map2.keySet()) {
			arr[i] = intValue;
			i++;
		}
		Arrays.sort(arr, Collections.reverseOrder());
		for (int j = 0; j < arr.length; j++) {
			List<Character> charList = map2.get(arr[j]);
			for (Character c : charList) {
				System.out.println(c + " : " + arr[j]);
			}
		}
		// return output;
	}
}
/*
 * class CustomComparator implements Comparator{
 * 
 * @SuppressWarnings("unchecked") public int compare(Object o1, Object o2) { Entry<Integer,List<Character>> entry1 = (Entry<Integer,List<Character>>) o1;
 * Entry<Integer,List<Character>> entry2 = (Entry<Integer,List<Character>>) o2; return (entry1.getKey()).compareTo(entry2.getKey()); } }
 */
