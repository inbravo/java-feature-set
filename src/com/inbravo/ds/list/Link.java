package com.inbravo.ds.list;

/**
 * 
 * @author amit.dixit
 *
 */
public final class Link {

	public int iData;
	public double dData;

	/* Self referencing Class */
	public Link next;

	public Link(final int iData, final double dData) {
		this.iData = iData;
		this.dData = dData;
	}

	@Override
	public final String toString() {
		return "Link [iData=" + iData + ", dData=" + dData + "]";
	}
}
