package com.inbravo.string;

/**
 * 
 * @author amit.dixit
 *
 */
public final class LevenshteinDistanceTest {

  /**
   * <p>
   * Find the Levenshtein distance between two Strings.
   * </p>
   * 
   * @param s the first String, must not be null
   * @param t the second String, must not be null
   * @return result distance
   * @throws IllegalArgumentException if either String input <code>null</code>
   */
  public static final int getLevenshteinDistance(final CharSequence lhs, final CharSequence rhs) {

    /* Two dimensional array to save the distance */
    final int[][] distance = new int[lhs.length() + 1][rhs.length() + 1];


    for (int i = 0; i <= lhs.length(); i++) {

      distance[i][0] = i;
    }

    for (int j = 1; j <= rhs.length(); j++) {

      distance[0][j] = j;
    }

    for (int i = 1; i <= lhs.length(); i++) {

      for (int j = 1; j <= rhs.length(); j++) {

        distance[i][j] =
            minimum(distance[i - 1][j] + 1, distance[i][j - 1] + 1, distance[i - 1][j - 1] + ((lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1));
      }
    }

    return distance[lhs.length()][rhs.length()];
  }

  private final static int minimum(final int a, final int b, final int c) {

    return Math.min(Math.min(a, b), c);
  }


  public static void main(final String... args) {

    System.out.println(getLevenshteinDistance("aaapppp", ""));
    System.out.println(getLevenshteinDistance("", "a"));
    System.out.println(getLevenshteinDistance("", ""));
    System.out.println(getLevenshteinDistance("frog", "fog"));
    System.out.println(getLevenshteinDistance("fly", "ant"));
    System.out.println(getLevenshteinDistance("elephant", "hippo"));
    System.out.println(getLevenshteinDistance("hello", "hello"));
    System.out.println(getLevenshteinDistance("hello", "hallo"));
    System.out.println(getLevenshteinDistance("hippo", "elephant"));
    System.out.println(getLevenshteinDistance("hippo", "zzzzzzzz"));
  }
}
