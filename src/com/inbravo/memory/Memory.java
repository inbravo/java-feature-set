package com.inbravo.memory;

import javax.management.*;
import java.lang.management.*;

/**
 * 
 * @author amit.dixit
 *
 */
public final class Memory {

	public static final long threadAllocatedBytes() {
		try {
			return (Long) ManagementFactory.getPlatformMBeanServer().invoke(new ObjectName(ManagementFactory.THREAD_MXBEAN_NAME),
					"getThreadAllocatedBytes", new Object[] { Thread.currentThread().getId() }, new String[] { long.class.getName() });
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
}
