package com.inbravo.number;

public final class RandomTest {

  static {
    System.out.println("Main not required to print this");
    System.exit(0);
  }
  
  static final int MAX_SEGMENTS = 1 << 16;

  public static final void main(final String... args) {
    System.out.println(MAX_SEGMENTS);
  }
}
