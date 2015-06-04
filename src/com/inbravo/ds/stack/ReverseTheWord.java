package com.inbravo.ds.stack;

/**
 * 
 * @author amit.dixit
 *
 */
public final class ReverseTheWord {

	public static final void main(final String... args) {

		/* Create new stack */
		final Stack stack = new Stack(20);
		final String wordToReverse = "inbravo";

		/* Push the char in stack */
		for (int i = 0; i < wordToReverse.length(); i++) {

			/* Get each char */
			final char ch = wordToReverse.charAt(i);

			/* Push on stack */
			stack.push(ch);
		}

		for (int i = 0; i < wordToReverse.length(); i++) {

			/* Push on stack */
			System.out.println((char) stack.pop());
		}
	}
}
