package com.inbravo.concurrency;

/**
 * Run this class several time; you will find many values of counter e.g. -5, 0,
 * 5, 10 etc. volatile/static does not gurantee the variable integrity during
 * concurrency
 * 
 * @author amit.dixit
 *
 */
public final class FailedConcurrency {

	private static volatile int counter = 0;

	private void concurrentMethodWrong() {

		counter = counter + 5;
		counter = counter - 5;
	}

	public static final void main(final String... args) {

		/* Create an instance */
		final FailedConcurrency fc = new FailedConcurrency();

		for (int j = 0; j < 1000; j++) {

			for (int i = 0; i < 1000; i++) {

				/* Create anonymous thread */
				new Thread() {
					public void run() {
						fc.concurrentMethodWrong();
					}
				}.start();
			}
			System.out.println(counter);
		}
	}
}
