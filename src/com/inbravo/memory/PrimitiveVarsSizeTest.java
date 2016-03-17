package com.inbravo.memory;

import static java.lang.System.out;

import java.util.concurrent.TimeUnit;

import objectexplorer.MemoryMeasurer;

/**
 * Add VM arguments before running: -javaagent:lib/object-explorer.jar
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
		out.println("[*] Total Obejct Memory:				[OBJECT META INFO] + [OBJECT DATA]");
		out.println("[*] [OBJECT META DATA]:					[CLASS INFO = 4 bytes] + [FLAGS = 4 bytes] + [LOCK INFO = 4 bytes]");
		out.println("[*] Total Obejct Memory:				[12 bytes] + [OBJECT DATA]");
		out.println("[*] [OBJECT DATA]:					Size of all fields in Object");
		out.println("--------------------------------------------------------------------------------------------------------------------------");

		final ByteClass byteClass = new ByteClass("0".getBytes()[0]);
		final CharClass charClass = new CharClass('0');
		final IntClass intClass = new IntClass(5);
		final LongClass longClass = new LongClass(5);
		final FloatClass floatClass = new FloatClass(5);
		final DoubleClass doubleClass = new DoubleClass(5);

		out.println("[*] Default Size: 'byte': 1 byte, 'char': 2 bytes, 'int'/'float': 4 bytes, 'long'/'double': 8 bytes");
		out.println("[*] Even if the data member is a byte, it will still take up 4 bytes!");
		out.println("[*] Actual size of 'byte': 					" + MemoryMeasurer.measureBytes(byteClass) + " bytes");
		out.println("[*] Actual size of 'char':					" + MemoryMeasurer.measureBytes(charClass) + " bytes");
		out.println("[*] Actual size of 'int': 					" + MemoryMeasurer.measureBytes(intClass) + " bytes");
		out.println("[*] Actual size of 'long': 					" + MemoryMeasurer.measureBytes(longClass) + " bytes");
		out.println("[*] Actual size of 'float': 					" + MemoryMeasurer.measureBytes(floatClass) + " bytes");
		out.println("[*] Actual size of 'double': 					" + MemoryMeasurer.measureBytes(doubleClass) + " bytes");
		out.println("==========================================================================================================================");

		while (true) {

			/* Sleep for 10 seconds */
			TimeUnit.SECONDS.sleep(10);
		}
	}

	/**
	 * Total consumed memory = 14 bytes (12 bytes meta info + 2 byte char)
	 */
	static class CharClass {

		/* 1 bytes each */
		char ch;

		public CharClass(final char ch) {
			this.ch = ch;
		}
	}

	/* Expected memory consumption = 13 bytes (12 bytes meta info + 1 byte for a 'byte') */
	/* Actual memory consumption = 16 bytes (12 bytes meta info + 4 byte for a 'byte') */
	static class ByteClass {

		byte number;

		public ByteClass(final byte number) {
			this.number = number;
		}
	}

	/* Expected memory consumption = 16 bytes (12 bytes meta info + 4 byte for a 'int') */
	/* Actual memory consumption = 16 bytes (12 bytes meta info + 4 byte for a 'int') */
	static class IntClass {

		int number;

		public IntClass(final int number) {
			this.number = number;
		}
	}

	/* Expected memory consumption = 20 bytes (12 bytes meta info + 8 byte for a 'long') */
	/* Actual memory consumption = 20 bytes (12 bytes meta info + 8 byte for a 'long') */
	static class LongClass {

		long number;

		public LongClass(final long number) {
			this.number = number;
		}
	}

	/* Expected memory consumption = 16 bytes (12 bytes meta info + 4 byte for a 'float') */
	/* Actual memory consumption = 16 bytes (12 bytes meta info + 4 byte for a 'float') */
	static class FloatClass {

		float number;

		public FloatClass(final float number) {
			this.number = number;
		}
	}

	/* Expected memory consumption = 20 bytes (12 bytes meta info + 8 byte for a 'double') */
	/* Actual memory consumption = 20 bytes (12 bytes meta info + 8 byte for a 'double') */
	static class DoubleClass {

		double number;

		public DoubleClass(final int number) {
			this.number = number;
		}
	}
}
