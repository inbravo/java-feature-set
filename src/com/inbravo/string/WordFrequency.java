package com.inbravo.string;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author amit.dixit
 *
 */
public final class WordFrequency {

	public static final void main(final String... args) {

		/* This is the original string to be tested for word frequency */
		final String origString = "dog jumping again jumping again jumping again jumping over dog";

		/* Map to hold the word count */
		final Map<String, AtomicInteger> wordFreqMap = new TreeMap<String, AtomicInteger>(String.CASE_INSENSITIVE_ORDER);

		/* Convert String to array */
		final String[] origStringAsArr = origString.split("\\s+");

		System.out.println("Array length : " + origStringAsArr.length);

		/* Iterate over string array */
		for (int i = 0; i < origStringAsArr.length; i++) {

			/* Only for valid string */
			if (origStringAsArr[i] != null && !"".equalsIgnoreCase(origStringAsArr[i])) {

				/* Increment the count of string, if found in Map */
				if (wordFreqMap.containsKey(origStringAsArr[i].trim())) {

					/* Increment the value on key */
					wordFreqMap.get(origStringAsArr[i].trim()).incrementAndGet();
				}
				/* Store '1' count of string, if not found in Map */
				else {

					/* Increment the value on key */
					wordFreqMap.put(origStringAsArr[i].trim(), new AtomicInteger(1));
				}
			} else {

				System.err.println("Ignored invalid string '" + origStringAsArr[i] + "' at position '" + i + "'");
			}
		}

		System.out.println("wordFreqMap : " + wordFreqMap);
	}
}
