package com.inbravo.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
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

		final CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
		list.add("vivek");
		list.add("kumar");

		final Iterator<String> i = list.iterator();

		while (i.hasNext()) {
			System.out.println(i.next());

			/* Change the list; it will not fail on next iteration */
			list.add("abhishek");
		}

		System.out.println("After modification:");

		final Iterator<String> i2 = list.iterator();

		while (i2.hasNext()) {
			System.out.println(i2.next());
		}
	}

	/**
	 * 
	 */
	public static final void withoutCowal() {

		final List<String> list = new ArrayList<String>();
		list.add("vivek");
		list.add("kumar");

		final Iterator<String> i = list.iterator();

		while (i.hasNext()) {
			System.out.println(i.next());

			/* Change the list; it will fail on next iteration */
			list.add("abhishek");
		}

		System.out.println("After modification:");

		final Iterator<String> i2 = list.iterator();

		while (i2.hasNext()) {
			System.out.println(i2.next());
		}
	}
}
