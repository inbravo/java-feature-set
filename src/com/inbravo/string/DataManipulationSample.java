package com.inbravo.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataManipulationSample {
	public static void main(String[] args) {
		String[] str = { "Tree Apple", "Food Masala Dosa", "Person Amit Khanna", "Animal Bull Dog", "Tree Mango", "Food Dal Rice",
				"Person Ajay Singh", "Animal Pussy Cat", "Tree Mango", "Food Sweet Biscuits", "Person Amit Khanna", "Animal Pussy Cat",
				"Food Bata shoe", "Person Indore MP" };
		List<String> listStr = new ArrayList<String>();
		Set<String> setStr = new HashSet<String>();

		listStr = Arrays.asList(str);
		Collections.sort(listStr);
		Collections.sort(listStr, Collections.reverseOrder());
		str = (String[]) listStr.toArray();
		System.out.println(str.toString());
		for (int i = 0; i < listStr.size() - 1; i++)
			System.out.println(str[i]);
		setStr.addAll(listStr);
		System.out.println("set:");
		/*
		 * Iterator itr = setStr.iterator();
		 * 
		 * //No ordering in set while(itr.hasNext()){ System.out.println(itr.next().toString()); }
		 */
		for (String s : setStr) {
			System.out.println(s);
		}

		// use public static final for constants
		/*
		 * int count =0; List <String> ls = new ArrayList<String>(); for(String s : listStr){ if(s.startsWith("Tree")) ls.add(s); }
		 */
		List<String> treeList = new ArrayList<String>();
		for (int i = 0; i < str.length - 1; i++) {
			if (str[i].startsWith("Tree"))
				treeList.add(str[i]);
		}
		System.out.println("Tree List:");
		for (String s : treeList) {
			System.out.println(s);// string += s;
		}
	}
}
