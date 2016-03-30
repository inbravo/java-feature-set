package com.inbravo.pattern;

import java.util.Arrays;

/**
 * 
 * @author amit.dixit
 *
 */
public final class EnumSingletonTest {

  public static final void main(final String... args) {

    SingletonEnum.INSTANCE.printFavorites();
  }
}


/**
 * 
 * @author amit.dixit
 *
 */
enum SingletonEnum {

  /* Only available instance */
  INSTANCE;

  private final String[] favoriteSongs = {"The rainmaker", "Beyond the deap sea"};

  public void printFavorites() {
    System.out.println(Arrays.toString(favoriteSongs));
  }
}
