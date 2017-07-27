package com.inbravo.ds.queue;

import com.inbravo.ds.list.DoubleEndedLinkList;
import com.inbravo.ds.list.Link;

/**
 * 
 * @author amit.dixit
 *
 */
public final class LinkedQueue {

  /* Double Ended Link List local storage */
  private DoubleEndedLinkList linkList;

  public LinkedQueue() {

    /* Create Double Ended Link List */
    linkList = new DoubleEndedLinkList();
  }

  /**
   * Insert at REAR of queue
   * 
   * @param value
   */
  public final void insert(final int value) {

    /* Insert at last position always */
    linkList.insertLast(new Link(value));
  }

  /**
   * Remove from FRONT of queue
   * 
   * @return
   */
  public final long remove() {

    /* Remove only if Queue is not EMPTY */
    if (!isEmpty()) {

      /* Delete from first position always */
      final Link removedValue = linkList.deleteFirst();

      return removedValue.iData;
    } else {

      /* Throw error */
      throw new RuntimeException("Queue is empty");
    }

  }

  public final boolean isEmpty() {

    return linkList.isEmpty();
  }

  @Override
  public final String toString() {

    return linkList.toString();
  }

  public static final void main(final String[] args) {

    /* Create new queue */
    final LinkedQueue queue = new LinkedQueue();

    /* Check for empty */
    System.out.println("Queue is empty ? " + queue.isEmpty());

    System.out.println("Before element insert : " + queue);

    for (int i = 0; i < 10; i++) {

      /* Insert items on queue */
      queue.insert(i + 10);
    }

    System.out.println("After element insert : " + queue);

    while (!queue.isEmpty()) {
      System.out.println("Removed " + queue.remove());
    }

    System.out.println("After element remove : " + queue);
  }
}
