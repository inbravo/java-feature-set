package com.inbravo.number;

/**
 * 
 * @author amit.dixit
 *
 */
public final class HexNumberTest {

  public static final void main(final String... args) {

    /* All valid HEX numbers: starting 0 to 15 */
    System.out.println("0x0: " + 0x0);
    System.out.println("0x1: " + 0x1);
    System.out.println("0x2: " + 0x2);
    System.out.println("0x3: " + 0x3);
    System.out.println("0x4: " + 0x4);
    System.out.println("0x5: " + 0x5);
    System.out.println("0x6: " + 0x6);
    System.out.println("0x7: " + 0x7);
    System.out.println("0x8: " + 0x8);
    System.out.println("0x9: " + 0x9);
    System.out.println("0xA: " + 0xA);
    System.out.println("0xB: " + 0xB);
    System.out.println("0xC: " + 0xC);
    System.out.println("0xD: " + 0xD);
    System.out.println("0xE: " + 0xE);
    System.out.println("0xF: " + 0xF);
    System.out.println("0xFF: " + 0xFF);
    System.out.println("0xFFF: " + 0xFFF);
    System.out.println("0xFFFF: " + 0xFFFF);
    System.out.println("0xFFFFF: " + 0xFFFFF);

    System.out.println("Binary(17): " + decToBinray(17) + ", Decimal(17): " + 17 + ", Hexadecimal(17): " + decToHex(17));
    System.out.println("Binary(27): " + decToBinray(27) + ", Decimal(27): " + 27 + ", Hexadecimal(27): " + decToHex(27));

    /* 0xFF makes one Byte(Each digit consumes 4 digits (0000 -> 1111)) */
  }

  /**
   * 
   * @param decimal
   * @return
   */
  public final static String decToHex(final int decimal) {

    return Integer.toHexString(decimal);
  }

  /**
   * 
   * @param decimal
   * @return
   */
  public final static String decToBinray(final int decimal) {

    return Integer.toBinaryString(decimal);
  }
}
