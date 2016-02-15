package com.inbravo.memory;

import javax.management.*;
import java.lang.management.*;

/**
 * 
 * @author amit.dixit
 *
 */
public final class Memory {

	public static final long getThreadAllocatedBytes() {
		try {
			return (Long) ManagementFactory.getPlatformMBeanServer().invoke(new ObjectName(ManagementFactory.THREAD_MXBEAN_NAME),
					"getThreadAllocatedBytes", new Object[] { Thread.currentThread().getId() }, new String[] { long.class.getName() });
		} catch (final Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static void main(final String... args) {

	}
}
