package com.inbravo.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

public class DataManipulationSample2 {
	public static void main(String[] args) {
		String[] str = { "Tree Apple", "Food Masala Dosa", "Person Amit Khanna", "Animal Bull Dog", "Tree Mango", "Food Dal Rice",
				"Person Ajay Singh", "Animal Pussy Cat", "Tree Mango", "Food Sweet Biscuits", "Person Amit Khanna", "Animal Pussy Cat",
				"Food Bata shoe", "Person Indore MP" };
		List<String> listStr = new ArrayList<String>();
		listStr = Arrays.asList(str);
		TreeSet<String> treeSet = new TreeSet<String>(listStr);
		for (String s : treeSet)
			System.out.println(s);
		System.out.println("==================");
		/* Map<String,String> map = new HashMap<String,String>(); */
		Map<String, String> map = new TreeMap<String, String>();// Tree map==sorted..Food, Tree
		for (int i = 0; i < str.length; i++) {
			if (str[i].startsWith("Tree")) {
				if (map.containsKey("Tree"))
					map.put("Tree", map.get("Tree") + "," + str[i]);
				else
					map.put("Tree", str[i]);
			}
			if (str[i].startsWith("Food")) {
				if (map.containsKey("Food"))
					map.put("Food", map.get("Food") + "," + str[i]);
				else
					map.put("Food", str[i]);
			}
		}
		for (Entry<?, ?> e : map.entrySet()) {
			System.out.println(e.getKey() + " = " + e.getValue().toString().replaceAll("Tree", "").replaceAll("Food", ""));
		}
	}
}