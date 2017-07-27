package com.inbravo.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Test of CopyOnWriteArrayList (COWAL)
 * 
 * @author amit.dixit
 *
 */
public final class CowalTest {

  /**
   * 
   * @param args
   */
  public static final void main(final String... args) {

    withCowal();
    withoutCowal();
  }

  /**
	 * 
	 */
  public static final void withCowal() {
    System.out.println("No CMEx with Cowal >>>");

    /* Create new COWAL */
    final CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();

    /* Add elements in COWAL */
    list.add("vivek");
    list.add("kumar");

    final Iterator<String> i = list.iterator();

    while (i.hasNext()) {
      System.out.println(i.next());

      /* Change the list; it will not fail on next iteration */
      list.add("abhishek");
    }
  }

  /**
	 * 
	 */
  public static final void withoutCowal() {
    System.out.println("CMEx without Cowal >>>");
    final List<String> list = new ArrayList<String>();
    list.add("vivek");
    list.add("kumar");

    final Iterator<String> i = list.iterator();

    while (i.hasNext()) {
      System.out.println(i.next());

      /* Change the list; it will fail on next iteration */
      list.add("abhishek");
    }
  }
}
