package com.inbravo.rmi;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * 
 * @author amit.dixit
 *
 */
public final class PowerServiceImpl implements PowerService {

  public PowerServiceImpl() throws RemoteException {
    super();
  }

  /*
   * Calculate the square of a number
   * 
   * @see com.inbravo.rmi.PowerService#square(int)
   */
  public final BigInteger square(final int number) throws RemoteException {

    System.out.println("Inside square operation");
    final String numrep = String.valueOf(number);

    /* Square the number */
    return new BigInteger(numrep).multiply(new BigInteger(numrep));
  }

  /*
   * Calculate the power of a number
   * 
   * @see com.inbravo.rmi.PowerService#power(int)
   */
  public final BigInteger power(final int num1, final int num2) throws RemoteException {

    System.out.println("Inside power operation");
    final String numrep = String.valueOf(num1);

    /* Call native power operation of BigInteger */
    return new BigInteger(numrep).pow(num2);
  }

  /**
   * 
   * @param args
   * @throws Exception
   */
  public static final void main(final String... args) throws Exception {

    /* Create an instance of our power service implementation */
    final PowerService stub = (PowerService) UnicastRemoteObject.exportObject(new PowerServiceImpl(), 1099);

    /* Get local RMI registry */
    final Registry registry = LocateRegistry.createRegistry(1099);

    /* Bind the object in registry */
    registry.rebind("PowerService", stub);

    System.out.println("Service is ready!....");
  }
}
