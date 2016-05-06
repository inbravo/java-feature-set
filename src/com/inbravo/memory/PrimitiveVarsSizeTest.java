package com.inbravo.memory;

import static java.lang.System.out;

import java.util.concurrent.TimeUnit;

import objectexplorer.MemoryMeasurer;

/**
 * Add VM arguments before running: -javaagent:lib/object-explorer.jar -XX:+UseG1GC
 * -XX:ConcGCThreads=2 -XX:+UnlockExperimentalVMOptions -verbosegc -XX:G1LogLevel=finest
 * 
 * @author amit.dixit
 */

public final class PrimitiveVarsSizeTest {

  /**
   * 
   * @param args
   * @throws InterruptedException
   */
  public static void main(final String... args) throws InterruptedException {

    out.println("==========================================================================================================================");
    out.println("[*] Total Object Memory: [OBJECT META INFO] + [OBJECT DATA]");
    out.println("[*] [OBJECT META DATA]: [CLASS INFO = 4 bytes] + [FLAGS = 4 bytes] + [LOCK INFO = 4 bytes]");
    out.println("[*] Total Object Memory: [12 bytes] + [OBJECT DATA]");
    out.println("[*] [OBJECT DATA]: Size of all fields in Object");
    out.println("[*] Default Size: 'byte': 1 byte, 'char': 2 bytes, 'int'/'float': 4 bytes, 'long'/'double': 8 bytes");
    out.println("[*] Even if the data member is a byte, it will still take up 4 bytes!");
    out.println("[*] JVM takes minimum 4 bytes by default");
    out.println("--------------------------------------------------------------------------------------------------------------------------");

    final ByteClass byteClass = new ByteClass("0".getBytes()[0]);
    final BooleanClass booleanClass = new BooleanClass(true);
    final ShortClass shortClass = new ShortClass("0".getBytes()[0]);
    final MultipleByteClass multipleByteClass = new MultipleByteClass("0".getBytes()[0]);
    final CharClass charClass = new CharClass('0');
    final IntClass intClass = new IntClass(5);
    final LongClass longClass = new LongClass(5);
    final FloatClass floatClass = new FloatClass(5);
    final DoubleClass doubleClass = new DoubleClass(5);

    out.println("[*] Size of 'byte': 					" + MemoryMeasurer.measureBytes(byteClass) + " bytes");
    out.println("[*] Size of 'boolean':                                  " + MemoryMeasurer.measureBytes(booleanClass) + " bytes");
    out.println("[*] Size of 'short':                                    " + MemoryMeasurer.measureBytes(shortClass) + " bytes");
    out.println("[*] Size of multiple 'byte': 				" + MemoryMeasurer.measureBytes(multipleByteClass) + " bytes");
    out.println("[*] Size of 'char':					" + MemoryMeasurer.measureBytes(charClass) + " bytes");
    out.println("[*] Size of 'int': 					" + MemoryMeasurer.measureBytes(intClass) + " bytes");
    out.println("[*] Size of 'long': 					" + MemoryMeasurer.measureBytes(longClass) + " bytes");
    out.println("[*] Size of 'float': 					" + MemoryMeasurer.measureBytes(floatClass) + " bytes");
    out.println("[*] Size of 'double': 					" + MemoryMeasurer.measureBytes(doubleClass) + " bytes");
    out.println("==========================================================================================================================");

    while (true) {

      /* Sleep for 10 seconds */
      TimeUnit.SECONDS.sleep(10);

      /* Call GC */
      System.gc();
    }
  }

  /* Actual memory consumption = 16 bytes (12 bytes meta info + 4 byte for a 'char') */
  static class CharClass {

    /* 2 bytes */
    char ch;

    public CharClass(final char ch) {
      this.ch = ch;
    }
  }

  /* Actual memory consumption = 16 bytes (12 bytes meta info + 4 byte for a 'boolean') */
  static class BooleanClass {

    /* 1 byte */
    boolean ch;

    public BooleanClass(final boolean ch) {
      this.ch = ch;
    }
  }

  /* Actual memory consumption = 16 bytes (12 bytes meta info + 4 byte for a 'byte') */
  static class ByteClass {

    byte number;

    public ByteClass(final byte number) {
      this.number = number;
    }
  }

  /* Actual memory consumption = 16 bytes (12 bytes meta info + 4 byte for a 'byte') */
  static class MultipleByteClass {

    byte numberOne;
    byte numberTwo;
    byte numberThree;
    byte numberFour;

    public MultipleByteClass(final byte number) {
      this.numberOne = number;
      this.numberTwo = number;
      this.numberThree = number;
      this.numberFour = number;
    }
  }

  /* Actual memory consumption = 16 bytes (12 bytes meta info + 4 byte for a 'short') */
  static class ShortClass {

    short number;

    public ShortClass(final byte number) {
      this.number = number;
    }
  }

  /* Actual memory consumption = 16 bytes (12 bytes meta info + 4 byte for a 'int') */
  static class IntClass {

    int number;

    public IntClass(final int number) {
      this.number = number;
    }
  }

  /* Actual memory consumption = 24 bytes (12 bytes meta info + 12 byte for a 'long') */
  static class LongClass {

    long number;

    public LongClass(final long number) {
      this.number = number;
    }
  }

  /* Actual memory consumption = 16 bytes (12 bytes meta info + 4 byte for a 'float') */
  static class FloatClass {

    float number;

    public FloatClass(final float number) {
      this.number = number;
    }
  }

  /* Memory consumption = 24 bytes (12 bytes meta info + 12 byte for a 'double') */
  static class DoubleClass {

    double number;

    public DoubleClass(final int number) {
      this.number = number;
    }
  }
}
