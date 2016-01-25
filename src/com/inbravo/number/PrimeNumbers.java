package com.inbravo.number;

import java.util.*;

/**
 * 
 * @author amit.dixit
 *
 */
public final class PrimeNumbers {

	public static final void main(final String... args) {

		@SuppressWarnings("resource")
		final Scanner in = new Scanner(System.in);

		System.out.println("Enter the number of prime numbers you want");

		/* Get input as int value from scanner */
		final int number = in.nextInt();

		if (number >= 1) {
			System.out.println("First " + number + " prime numbers are :-");
		}

		/* Print all prime numbers; starting '2' */
		for (int outer = 2; outer < number; outer++) {

			boolean isPrime = true;

			/* check to see if the number is prime */
			for (int inner = 2; inner < outer; inner++) {

				/* If no remainder */
				if (outer % inner == 0) {

					/* It is prime */
					isPrime = false;
					break;
				}
			}
			/* print the number */
			if (isPrime) {
				System.out.print(outer + " ");
			}
		}
	}
}