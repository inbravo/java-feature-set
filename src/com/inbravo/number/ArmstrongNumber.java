package com.inbravo.number;

import java.util.*;

public class ArmstrongNumber {
	public static void main(String args[]) {
		int n, sum = 0, temp, r;

		System.out.println("Enter a number to check if it is an armstrong number");
		@SuppressWarnings("resource")
		final Scanner in = new Scanner(System.in);
		n = in.nextInt();

		temp = n;

		while (temp != 0) {
			r = temp % 10;
			sum = sum + r * r * r;
			temp = temp / 10;
		}

		if (n == sum)
			System.out.println("Entered number is an armstrong number.");
		else
			System.out.println("Entered number is not an armstrong number.");
	}
}