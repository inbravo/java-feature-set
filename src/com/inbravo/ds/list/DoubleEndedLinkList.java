package com.inbravo.ds.list;

/**
 * 
 * @author amit.dixit
 *
 */
public final class DoubleEndedLinkList {

	private Link first;
	private Link last;

	public DoubleEndedLinkList() {
		first = null;
		last = null;
	}

	/**
	 * 
	 * @param newLink
	 */
	public final void insertFirst(final Link newLink) {

		if (isEmpty()) {

			/* Put new link in last position */
			last = newLink;
		} else {

			/* New link will map to first link */
			newLink.next = first;
		}

		/* Put new link in first position */
		first = newLink;
	}

	/**
	 * 
	 * @param newLink
	 */
	public final void insertLast(final Link newLink) {

		if (isEmpty()) {

			/* Put new link in first position */
			first = newLink;
		} else {

			/* New link will map to next of last link */
			last.next = newLink;
		}

		/* Put new link in last position */
		last = newLink;
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

	/**
	 * 
	 * @param key
	 * @return
	 */
	public final Link find(final int key) {

		/* Start from first as current link */
		Link current = first;

		/* Loop until key is not found */
		while (current.iData != key) {

			if (current.next == null) {

				return null;
			} else {

				/* Make next node as current */
				current = current.next;
			}
		}

		return current;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public final Link delete(final int key) {

		/* Start from first as current link */
		Link current = first;
		Link previous = first;

		/* Loop until key is not found */
		while (current.iData != key) {

			if (current.next == null) {

				return null;
			} else {

				/* Go to Previous Link of Current Link */
				previous = current;

				/* Make Current's Next Link as Current Link */
				current = current.next;
			}
		}

		/* If Current Link is First Link */
		if (current == first) {

			/* Change First */
			first = first.next;
		} else {

			/* Bypass Current Link Now */
			previous.next = current.next;
		}
		return current;
	}

	@Override
	public final String toString() {

		String output = "";

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
		final DoubleEndedLinkList linkList = new DoubleEndedLinkList();

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

		System.out.println("Check if Link(70, 700.007) is existing in List : " + linkList.find(70));

		System.out.println("Delete Link(70, 700.007)" + linkList.delete(70));
		System.out.println("After Deletion : " + linkList);

		/* Delete first link */
		final Link deletedLink = linkList.deleteFirst();
		System.out.println("After Deleting First Link(" + deletedLink + ") : " + linkList);

		/* Insert at last */
		linkList.insertLast(new Link(100, 100.0001));
		System.out.println("After Adding Link at Last: " + linkList);

		while (!linkList.isEmpty()) {

			/* Delete first link */
			final Link deleted = linkList.deleteFirst();
			System.out.println("Deleted : " + deleted + "\n");
		}
	}
}
