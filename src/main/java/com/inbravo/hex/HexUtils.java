package com.inbravo.hex;

/**
 * 
 * @author amit.dixit
 *
 */
public final class HexUtils {

  public static final String toHex(final int intValue) {

    return Integer.toHexString(intValue);
  }

  public static final int toDecimal(final String intValue) {

    return Integer.parseInt(intValue, 16);
  }

  public static void main(String[] args) {

    for (int i = 0; i < 15; i++) {
      System.out.println(i + " in hex representation = " + toHex(i));
    }

    System.out.println("100 in hex representation = " + toHex(100));
    System.out.println("64 in decimal representation = " + toDecimal("" + 64));
  }
}
