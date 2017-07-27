package com.inbravo.runtime.jit;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * How to get Unsafe memory from JVM
 * 
 * @author amit.dixit
 *
 */
public class UnsafeObject {

  private final static long SIZE_IN_BYTES = 4;

  private final long startIndex;

  private static Unsafe unsafe;

  static {

    try {

      /* This is the technique to get object of Unsafe */
      final Field field = Unsafe.class.getDeclaredField("theUnsafe");
      field.setAccessible(true);
      unsafe = (Unsafe) field.get(null);

    } catch (final NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
      System.err.println(e);
    }
  }

  /* Constructor */
  public UnsafeObject(final long size) {
    startIndex = unsafe.allocateMemory(size * SIZE_IN_BYTES);

    /* Allocate memory for the object */
    unsafe.setMemory(startIndex, size * SIZE_IN_BYTES, (byte) 0);
  }

  public final void setValue(final long index, final byte value) {

    /* Set at integer value */
    unsafe.putByte(index(index), value);
  }

  public final int getValue(final long index) {

    /* Get at integer value */
    return unsafe.getInt(index(index));
  }

  private final long index(final long offset) {
    return startIndex + offset * SIZE_IN_BYTES;
  }

  public final void destroy() {

    /* Disallocate memory for the object */
    unsafe.freeMemory(startIndex);
  }

  public static final void main(final String... args) throws Exception {

    long maximum = Byte.MAX_VALUE + 1L;

    /* Create new unsafe object */
    final UnsafeObject unsafeObject = new UnsafeObject(maximum);

    /* Set some values in object */
    unsafeObject.setValue(0L, (byte) 10);
    unsafeObject.setValue(maximum, (byte) 20);

    /* Print the values */
    System.out.println(unsafeObject.getValue(0L));
    System.out.println(unsafeObject.getValue(maximum));

    /* Destroy the object */
    unsafeObject.destroy();
  }
}
