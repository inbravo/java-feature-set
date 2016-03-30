package com.inbravo.string;

import java.util.Arrays;

/**
 * 
 * If two strings are made of same chars. A word, phrase, or name formed by rearranging the letters
 * of another, such as spar, formed from rasp.
 * 
 * @author amit.dixit
 *
 */
public final class Anagram {

  public static final void main(final String... args) {

    final String firstString = "KATAK";
    final String secondString = "TAKAK";

    /* Check if both strings are anagram */
    if (checkIfAnagram(firstString, secondString)) {

      System.out.println("Strings '" + firstString + "' & '" + secondString + "' are Anagrams");
    } else {

      System.out.println("Strings '" + firstString + "' & '" + secondString + "' are not Anagrams");
    }
  }

  /**
   * O(2n log n) using Dual Pivot sorting
   * 
   * @param firstString
   * @param secondString
   * @return
   */
  private static final boolean checkIfAnagram(final String firstString, final String secondString) {

    /* Sort all chars in both string */
    final char[] firstStringChars = firstString.toLowerCase().toCharArray();
    final char[] secondStringChars = secondString.toLowerCase().toCharArray();

    /* Apply Dual-Pivot Quicksort by Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch */
    Arrays.sort(firstStringChars);
    Arrays.sort(secondStringChars);

    /* Check if same */
    if (String.valueOf(firstStringChars).equals(String.valueOf(secondStringChars))) {

      return true;
    } else {

      return false;
    }
  }
}
