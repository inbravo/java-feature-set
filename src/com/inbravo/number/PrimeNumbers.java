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

		/* Check if its prime */
		primeUsingSqrt(number);
		printAllPrimes(number);
	}

	public static final void printAllPrimes(final double number) {

		if (number >= 1) {
			System.out.println("First " + number + " prime numbers are :-");
		}

		/* Print all prime numbers; starting '2' */
		for (int outer = 2; outer < number; outer++) {

			boolean isPrime = true;

			/* Check to see if the number is prime */
			for (int inner = 2; inner < outer; inner++) {

				/* If no remainder */
				if (outer % inner == 0) {

					/* It is not prime */
					isPrime = false;
					break;
				}
			}
			/* Print the number */
			if (isPrime) {
				System.out.print(outer + " ");
			}
		}
	}

	public static final void primeUsingSqrt(final double number) {

		boolean isPrime = true;

		/* Check to see if the number is prime */
		for (int counter = 2; counter < Math.sqrt(number); counter++) {

			/* If no remainder */
			if (number % counter == 0) {

				/* It is not prime */
				isPrime = false;
				break;
			}
		}
		System.out.println("Number " + number + " is: " + (isPrime == true ? "Prime" : "Not- Prime"));
	}
}