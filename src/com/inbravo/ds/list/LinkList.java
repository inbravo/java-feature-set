package com.inbravo.ds.list;

/**
 * 
 * @author amit.dixit
 *
 */
public final class LinkList {

	private Link first;

	public LinkList() {
		first = null;
	}

	/**
	 * 
	 * @param newLink
	 */
	public final void insertFirst(final Link newLink) {

		/* New link will map to first link */
		newLink.next = first;

		/* Put new link in first position */
		first = newLink;
	}

	/**
	 * 
	 * @return
	 */
	public final Link deleteFirst() {

		/* Take first link in temp */
		final Link temp = first;

		first = first.next;
		return temp;
	}

	@Override
	public final String toString() {

		String output = "List (first ---> last)\n";

		/* Start from first link */
		Link current = first;

		while (current != null) {

			/* Add current link info */
			output = output + current + "\n";

			/* Get next as current */
			current = current.next;
		}

		return output;
	}

	public final boolean isEmpty() {
		return (first == null);
	}

	public static final void main(final String[] args) {

		/* Create new LinkList */
		final LinkList linkList = new LinkList();

		/* Add Links */
		linkList.insertFirst(new Link(10, 100.001));
		linkList.insertFirst(new Link(20, 200.002));
		linkList.insertFirst(new Link(30, 300.003));
		linkList.insertFirst(new Link(40, 400.004));
		linkList.insertFirst(new Link(50, 500.005));
		linkList.insertFirst(new Link(60, 600.006));
		linkList.insertFirst(new Link(70, 700.007));
		linkList.insertFirst(new Link(80, 800.008));
		linkList.insertFirst(new Link(90, 900.009));

		System.out.println(linkList);

		while (!linkList.isEmpty()) {

			/* Delete first link */
			final Link deleted = linkList.deleteFirst();
			System.out.println("Deleted : " + deleted + "\n");
		}
	}
}
