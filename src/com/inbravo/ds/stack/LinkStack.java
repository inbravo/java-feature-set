package com.inbravo.ds.stack;

import com.inbravo.ds.list.Link;
import com.inbravo.ds.list.LinkList;

/**
 * 
 * @author amit.dixit
 *
 */
public final class LinkStack {

  /* Link List for local storage */
  private LinkList linkList;

  public LinkStack() {

    /* Create new Link List */
    linkList = new LinkList();
  }

  /**
   * 
   * @param value
   */
  public final void push(final int value) {

    /* Insert at first position always */
    linkList.insertFirst(new Link(value, 0D));
  }

  /**
   * 
   * @return
   */
  public final int pop() {

    /* Delete from first position always */
    final Link poppedValue = linkList.deleteFirst();

    return poppedValue.iData;
  }

  /**
   * 
   * @return
   */
  public final boolean isEmpty() {

    return linkList.isEmpty();
  }

  @Override
  public final String toString() {

    return linkList.toString();
  }

  public static final void main(final String[] args) {

    /* Create new stack */
    final LinkStack stack = new LinkStack();

    /* Check for empty */
    System.out.println("Stack is empty ? " + stack.isEmpty());

    System.out.println("Before element push : " + stack);

    for (int i = 0; i < 10; i++) {

      /* Push items on stack */
      stack.push(i + 10);
    }

    System.out.println("After element push : " + stack);

    while (!stack.isEmpty()) {
      System.out.println("Popped " + stack.pop());
    }

    System.out.println("After element pop : " + stack);
  }
}
