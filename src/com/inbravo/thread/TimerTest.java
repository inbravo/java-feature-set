package com.inbravo.thread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * @author amit.dixit
 *
 */
public final class TimerTest {

	public final static void main(String[] args) {

		/* Create new timer */
		final Timer timer = new Timer();

		/* Add new timer task */
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				System.out.println("------");
			}

		}, 0, 3000);
	}
}
