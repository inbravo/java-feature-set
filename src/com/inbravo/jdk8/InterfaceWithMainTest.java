package com.inbravo.jdk8;

/**
 * 
 * @author amit.dixit
 *
 */
public interface InterfaceWithMainTest {

  public default void run() {
    System.out.println("---do nothing---");
  }

  public static final class MainClass implements InterfaceWithMainTest {

    public static void main(final String... args) {

      final InterfaceWithMainTest test = new MainClass();

      test.run();
    }
  }
}
