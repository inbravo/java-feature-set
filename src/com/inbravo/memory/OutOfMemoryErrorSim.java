package com.inbravo.memory;

import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

import javax.management.ObjectName;

/**
 * Add as VM args: -Xmx512m -XX:+HeapDumpOnOutOfMemoryError
 * 
 * @author Pierre-Hugues Charbonneau
 *
 */
public final class OutOfMemoryErrorSim {

	/* Number of default iterations */
	private final static int NB_ITERATIONS = 500000;

	/* ~1 KB data footprint */
	private final static String LEAKING_DATA_PREFIX = "datadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadat"
			+ "adatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatada"
			+ "tadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatada"
			+ "tadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadat"
			+ "adatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadata";

	/* Map used to stored our leaking String instances */
	private static Map<String, String> leakingMap;

	static {
		leakingMap = new HashMap<String, String>();
	}

	/**
	 * @param args
	 */
	public static final void main(final String... args) {

		System.out.println("JVM OutOfMemoryError Simulator 1.0");
		System.out.println("http://javaeesupportpatterns.blogspot.com/");

		try {

			for (int i = 0; i < NB_ITERATIONS; i++) {

				final String data = LEAKING_DATA_PREFIX + i;

				/* Add data to our leaking Map data structure... */
				leakingMap.put(data, data);
				System.out.println("Thread bytes : " + getThreadAllocatedBytes());
			}

		} catch (final Throwable any) {

			/* If error is OOM */
			if (any instanceof java.lang.OutOfMemoryError) {
				System.out.println("OutOfMemoryError triggered! " + any.getMessage() + " [" + any + "]");

			} else {
				System.out.println("Unexpected Exception! " + any.getMessage() + " [" + any + "]");
			}
		}

		System.out.println("simulator done!");
	}

	public static final long getThreadAllocatedBytes() {
		try {
			return (Long) ManagementFactory.getPlatformMBeanServer().invoke(new ObjectName(ManagementFactory.THREAD_MXBEAN_NAME),
					"getThreadAllocatedBytes", new Object[] { Thread.currentThread().getId() }, new String[] { long.class.getName() });
		} catch (final Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
}
