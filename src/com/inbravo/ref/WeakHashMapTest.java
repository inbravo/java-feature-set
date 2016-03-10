package com.inbravo.ref;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * 
 * @author amit.dixit
 *
 */
public final class WeakHashMapTest {

	public static final void main(final String... args) {

		normalHashMap(args);
		weakHashMap(args);
	}

	public static final void weakHashMap(final String... args) {

		/* Create new weak hash map */
		final WeakHashMap<String, String> aMap = new WeakHashMap<String, String>();

		/* New strings */
		String emp = new String("Vinoth");
		final String val = new String("Programmer");

		aMap.put(emp, val);

		emp = null;

		System.gc();
		int count = 0;

		while (0 != aMap.size()) {
			++count;
			System.gc();
		}
		System.out.println("Took " + count + " calls to System.gc() to result in weakHashMap size of : " + aMap.size());
	}

	public static final void normalHashMap(final String... args) {

		final HashMap<String, String> aMap = new HashMap<String, String>();

		String emp = new String("Vinoth");
		final String val = new String("Programmer");

		aMap.put(emp, val);

		emp = null;

		System.gc();
		System.out.println("Size of Map: " + aMap.size());
	}
}
