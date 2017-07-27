package com.inbravo.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 
 * @author amit.dixit
 *
 */
public final class PowerServiceClient {

  public static final void main(final String... args) throws Exception {

    /* Get local RMI registry */
    final Registry registry = LocateRegistry.getRegistry(1099);

    /* Get PowerService from local RMI registry */
    final PowerService service = (PowerService) registry.lookup("PowerService");

    /* Call remote method */
    System.out.println("service : " + service);
    System.out.println("Square : " + service.square(10));
    System.out.println("Power : " + service.power(10, 10));
  }
}
