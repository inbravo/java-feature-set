package com.inbravo.string;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author amit.dixit
 *
 */
public final class FirstNonRepeatingChar {

  public static final void main(final String... args) {

    System.out.println("First non repeating char = " + getFirstNonRepeatingChar("adaasmedey"));
  }

  /**
   * 
   * @param word
   * @return
   */
  public static final char getFirstNonRepeatingChar(final String word) {

    /* List with unique and insertion order elements */
    final Set<Character> repeating = new LinkedHashSet<Character>();
    final List<Character> nonRepeating = new ArrayList<Character>();

    for (int i = 0; i < word.length(); i++) {

      final char letter = word.charAt(i);

      /* If char is repeating; ignore further processing */
      if (repeating.contains(letter)) {
        continue;
      }

      /* If char is found repeating */
      if (nonRepeating.contains(letter)) {

        nonRepeating.remove((Character) letter);
        repeating.add(letter);
      } else {
        nonRepeating.add(letter);
      }
    }

    return nonRepeating.get(0);
  }
}
