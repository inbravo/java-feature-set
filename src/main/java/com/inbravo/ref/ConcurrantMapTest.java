package com.inbravo.ref;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.MapMaker;

/**
 * 
 * @author amit.dixit
 *
 */
public final class ConcurrantMapTest {

  public final static void main(final String... args) {

    final int numProcessores = Runtime.getRuntime().availableProcessors();

    System.out.println("Processor has " + numProcessores + " cores");

    /* Start the test */
    test();
  }

  /**
   * 
   * @param memoryBean
   * @param cache
   */
  public static final void test() {

    /* Create JMX bean */
    final MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

    /* Create new concurrent map with weak keys and soft values */
    final ConcurrentMap<Integer, TestValue> cache = new MapMaker().weakKeys().softValues().makeMap();

    int i = 0;

    /* Infinite loop */
    while (true) {

      System.out.println("No of Entries:[" + cache.size() + "], heap:[ " + memoryBean.getHeapMemoryUsage() + "], non-heap:["
          + memoryBean.getNonHeapMemoryUsage() + "]");

      for (int j = 0; j < 1000; j++) {

        i++;
        cache.put(i, new TestValue(i));
      }
      try {
        /* Wait for 1/10 seconds */
        Thread.sleep(100);

      } catch (final InterruptedException ex) {
        System.out.println("Interrupted");
      }
    }
  }
}


final class TestValue {

  @SuppressWarnings("unused")
  private final int id;

  @SuppressWarnings("unused")
  private final int[] data = new int[100000];

  public TestValue(final int id) {
    this.id = id;
  }

  @Override
  protected void finalize() throws Throwable {
    super.finalize();
    System.out.println("finalized");
  }
}
