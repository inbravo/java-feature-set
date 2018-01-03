package com.inbravo.jdk9;

/**
 * JDK 9 allows you to create private methods on Interfaces. For more details
 * :https://www.journaldev.com/12850/java-9-private-methods-interfaces
 * 
 * @author amit.dixit
 *
 */
public class InterfaceWithPrivateMethodTest {

  private static void run() {
    System.out.println("---private method---");
  }

  public static void main(final String... args) {

    InterfaceWithPrivateMethodTest.run();
  }
}
