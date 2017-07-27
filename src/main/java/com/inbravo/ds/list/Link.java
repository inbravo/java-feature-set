package com.inbravo.ds.list;

/**
 * 
 * @author amit.dixit
 *
 */
public final class Link {

  public int iData;

  /* Self referencing Class */
  public Link next;

  public Link(final int iData) {
    this.iData = iData;
  }

  @Override
  public final String toString() {
    return "Link [iData=" + iData + "]";
  }
}
