package com.inbravo.rmi;

import java.math.BigInteger;
import java.rmi.RemoteException;

/**
 * 
 * @author amit.dixit
 *
 */
public interface PowerService extends java.rmi.Remote {

  /* Calculate the square of a number */
  public BigInteger square(int number) throws RemoteException;

  /* Calculate the power of a number */
  public BigInteger power(int num1, int num2) throws RemoteException;
}
