package com.inbravo.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author amit.dixit
 *
 */
public final class DuplicateChars {

  public static final void main(final String... args) {

    if (checkIfDuplicateChars("amitdixitamitdixit")) {

      System.out.println("Duplicate chars found in string");
    } else {

      System.out.println("Duplicate chars not found in string");
    }
  }

  /**
   * 
   * @param inputString
   * @return
   */
  private static final boolean checkIfDuplicateChars(final String inputString) {

    /* Get char count for each char in string */
    final Map<Character, AtomicInteger> charsCount = getCharCount(inputString);

    for (final Entry<Character, AtomicInteger> charsCountEntry : charsCount.entrySet()) {

      /* If any char entry has value greater than 1 */
      if (charsCountEntry.getValue().get() > 1) {

        return true;
      }
    }

    return false;
  }

  /**
   * 
   * @param inputString
   * @return
   */
  private static final Map<Character, AtomicInteger> getCharCount(final String inputString) {

    /* Map to hold the chars count */
    final Map<Character, AtomicInteger> charsCount = new HashMap<Character, AtomicInteger>();

    /* Push the char in stack */
    for (int i = 0; i < inputString.length(); i++) {

      /* Get each char */
      final Character ch = (Character) inputString.charAt(i);

      /* Put in map; if only its new */
      if (charsCount.containsKey(ch)) {

        /* Increment the value of char count */
        charsCount.get(ch).getAndIncrement();
      } else {

        charsCount.put(ch, new AtomicInteger(1));
      }
    }

    System.out.println("Count of each char string [" + inputString + "] is " + charsCount);
    return charsCount;
  }
}
