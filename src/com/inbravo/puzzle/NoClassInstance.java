package com.inbravo.puzzle;

/**
 * 
 * @author amit.dixit
 *
 */
public final class NoClassInstance {

  public static final void main(final String... args) {
    System.out.println(TestClass.noClassConstant);
  }
}


/* This class will not be instantiated */
final class TestClass {

  public static final int noClassConstant = 100;

  /* This method will never be called */
  static {
    System.out.println("--TestClass loaded--");
  }
}
