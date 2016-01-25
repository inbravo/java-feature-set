package com.inbravo.number;

import java.util.Scanner;

/**
 * 
 * @author amit.dixit
 *
 */
public final class FloydsTriangle {

	public static final void main(final String... arg) {

		@SuppressWarnings("resource")
		final Scanner in = new Scanner(System.in);

		System.out.println("Enter the number of rows of floyd's triangle you want");
		final int number = in.nextInt();

		System.out.println("Floyd's triangle :-");

		int num = 1;

		for (int outer = 1; outer <= number; outer++) {

			for (int inner = 1; inner <= outer; inner++) {

				System.out.print(num + " ");
				num++;
			}

			System.out.println();
		}
	}
}