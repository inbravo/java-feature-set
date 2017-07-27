package com.inbravo.ds.stack;

/**
 * 
 * @author amit.dixit
 *
 */
public final class ReverseTheWord {

  public static final void main(final String... args) {

    System.out.println(reverse("inbravo"));
  }

  public static final String reverse(final String inputString) {

    /* Check if string is palindrome using stack */
    final Stack stack = new Stack(inputString.length());

    /* Push the char in stack */
    for (int i = 0; i < inputString.length(); i++) {

      /* Get each char */
      final char ch = inputString.charAt(i);

      /* Push on stack */
      stack.push(ch);
    }

    /* Reverse of input string */
    String reverse = "";

    for (int i = 0; i < inputString.length(); i++) {

      /* Pop from stack */
      final char poppedValue = (char) stack.pop();

      /* Add this char in string */
      reverse = reverse.concat("" + poppedValue);
    }

    return reverse;
  }
}
