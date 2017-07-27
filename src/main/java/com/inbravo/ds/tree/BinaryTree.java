package com.inbravo.ds.tree;

import java.util.Stack;

/**
 * Complexity = O(log N); Tree = Ordered Array + Linked List
 * 
 * @author amit.dixit
 *
 */
public final class BinaryTree {

  /* First node of tree */
  private TreeNode root;

  /* Constructor */
  public BinaryTree() {

    root = null;
  }

  /**
   * Find node with given key
   * 
   * @param key
   * @return
   */
  public final TreeNode find(final int key) {

    /* Start at root */
    TreeNode current = root;

    /* While no match, */
    while (current.iData != key) {

      /* Go left? */
      if (key < current.iData) {

        /* Take left child as current node */
        current = current.leftChild;
      }

      /* Or go right? */
      else {

        /* Take right child as current node */
        current = current.rightChild;
      }

      /* if no child */
      if (current == null) {

        /* Didn't find it */
        return null;
      }
    }

    /* found it */
    return current;
  }

  /**
   * 
   * @param id
   * @param dd
   */
  public final void insert(final int iData) {

    /* Make new node */
    final TreeNode newNode = new TreeNode(iData);

    /* No node in root */
    if (root == null) {

      /* New node will be at root */
      root = newNode;
    }
    /* If root is occupied */
    else {

      /* Start at root */
      TreeNode current = root;
      TreeNode parent = root;

      /* (Exits internally) */
      while (true) {

        /* Start from parent as current */
        parent = current;

        /* Go left? */
        if (iData < current.iData) {

          /* Take left child as current node */
          current = current.leftChild;

          /* if end of the line, */
          if (current == null) {

            /* Insert on left */
            parent.leftChild = newNode;
            return;
          }
        }
        /* Or go right? */
        else {

          /* Take left child as current node */
          current = current.rightChild;

          /* if end of the line, */
          if (current == null) {

            /* Insert on right */
            parent.rightChild = newNode;
            return;
          }
        }
      }
    }
  }

  /**
   * 
   * @param key
   */
  public final boolean delete(final int key) {

    /* Variable to check if left variable */
    boolean isLeftChild = false;

    /* Start at root */
    TreeNode current = root;
    TreeNode parent = root;

    /* Loop untill key is found */
    while (current.iData != key) {

      /* Start from parent as current */
      parent = current;

      /* Go left? */
      if (key < current.iData) {

        /* Take left child as current node */
        current = current.leftChild;

        /* Mark left variable as true */
        isLeftChild = true;
      }
      /* Or go right? */
      else {

        /* Take left child as current node */
        current = current.rightChild;
      }

      /* If end of the branch, */
      if (current == null) {

        return false;
      }
    }

    /* Case 1: If no children */
    if (current.leftChild == null && current.rightChild == null) {

      /* If tree is empty */
      if (current == root) {

        /* Delete the root */
        root = null;
      }
      /* If left child */
      else if (isLeftChild) {

        /* Disconnect left */
        parent.leftChild = null;
      } else {

        /* Disconnect right */
        parent.rightChild = null;
      }
    }
    /* Case 2: If no right child */
    else if (current.rightChild == null) {

      /* If tree is empty */
      if (current == root) {

        /* Attach left child as parent */
        parent = current.leftChild;
      }
      /* If left child */
      else if (isLeftChild) {

        /* Attach current left child as parent left child */
        parent.leftChild = current.leftChild;
      } else {

        /* Attach current right child as parent right child */
        parent.rightChild = current.rightChild;
      }
    }
    /* Case 3: If no left child */
    else if (current.leftChild == null) {

      /* If tree is empty */
      if (current == root) {

        /* Attach right child as parent */
        parent = current.rightChild;
      }
      /* If left child */
      else if (isLeftChild) {

        /* Attach current left child as parent left child */
        parent.leftChild = current.leftChild;
      } else {

        /* Attach current right child as parent right child */
        parent.rightChild = current.rightChild;
      }
    }

    /* Case 4: If both children present */
    else {

      /* Get successor of node to delete (current) */
      final TreeNode successor = this.getSuccessor(current);

      /* If tree is empty */
      if (current == root) {

        /* Connect parent of current to successor instead */
        root = successor;
      }
      /* If left child */
      else if (isLeftChild) {

        /* Attach successoras parent left child */
        parent.leftChild = successor;
      } else {

        /* Attach successoras parent right child */
        parent.rightChild = successor;
      }

      /* connect successor to current's left child */
      successor.leftChild = current.leftChild;
    }

    return true;
  }

  /**
   * 
   * Returns node with next-highest value after delNode goes to right child, then right child's left
   * descendents
   */
  private final TreeNode getSuccessor(final TreeNode delNode) {

    TreeNode successorParent = delNode;
    TreeNode successor = delNode;

    /* Go to right child */
    TreeNode current = delNode.rightChild;

    /* Until no more */
    while (current != null) {

      /* left children */
      successorParent = successor;
      successor = current;

      /* Go to right child */
      current = current.leftChild;
    }

    /* If successor not right child */
    if (successor != delNode.rightChild) {

      /* Make connections */
      successorParent.leftChild = successor.rightChild;
      successor.rightChild = delNode.rightChild;
    }

    return successor;
  }

  /**
   * 
   * @param root
   */
  public final void preOrder(final TreeNode root) {

    if (root != null) {

      System.out.println(root);

      /* Traverse left child/branch */
      preOrder(root.leftChild);

      /* Traverse right child/branch */
      preOrder(root.rightChild);
    }
  }


  /**
   * 
   * @param root
   */
  public final void inOrder(final TreeNode root) {

    if (root != null) {

      /* Traverse left child/branch */
      inOrder(root.leftChild);

      System.out.println(root);

      /* Traverse right child/branch */
      inOrder(root.rightChild);
    }
  }

  /**
   * 
   * @param root
   */
  public final void postOrder(final TreeNode root) {

    if (root != null) {

      /* Traverse left child/branch */
      postOrder(root.leftChild);

      /* Traverse right child/branch */
      postOrder(root.rightChild);

      System.out.println(root);
    }
  }


  /**
	 * 
	 */
  public final void displayTree() {

    /* Global stack to hold tree nodes */
    final Stack<TreeNode> globalStack = new Stack<TreeNode>();

    /* Push root node on stack */
    globalStack.push(root);

    int nBlanks = 32;

    boolean isRowEmpty = false;

    System.out.println("......................................................");

    /* Untill the row is not empty */
    while (isRowEmpty == false) {

      /* Local loop stack to hold tree nodes */
      final Stack<TreeNode> localStack = new Stack<TreeNode>();

      isRowEmpty = true;

      for (int j = 0; j < nBlanks; j++) {
        System.out.print(' ');
      }

      /* Untill the global stack is not empty */
      while (globalStack.isEmpty() == false) {

        /* Pop from global stack */
        final TreeNode temp = (TreeNode) globalStack.pop();

        /* If root is not null */
        if (temp != null) {

          System.out.print(temp.iData);

          /* Push on local stack */
          localStack.push(temp.leftChild);
          localStack.push(temp.rightChild);

          /* Check if next row is empty */
          if (temp.leftChild != null || temp.rightChild != null) {
            isRowEmpty = false;
          }
        }
        /* Push null on local stack */
        else {
          System.out.print("--");

          localStack.push(null);
          localStack.push(null);
        }
        for (int j = 0; j < nBlanks * 2 - 2; j++) {
          System.out.print(' ');
        }
      } /* End while globalStack not empty */

      System.out.println();
      nBlanks /= 2;

      while (localStack.isEmpty() == false) {
        globalStack.push(localStack.pop());
      }
    }
    System.out.println("......................................................");
  }

  public static void main(final String... args) {

    /* Create new binary tree */
    final BinaryTree theTree = new BinaryTree();

    /* Insert some tlements */
    theTree.insert(60);
    theTree.insert(70);
    theTree.insert(20);
    theTree.insert(10);
    theTree.insert(40);
    theTree.insert(30);
    theTree.insert(50);

    /* Display it */
    theTree.displayTree();

    System.out.println("Preorder: ");
    theTree.preOrder(theTree.root);
    System.out.println("Inorder: ");
    theTree.inOrder(theTree.root);
    System.out.println("Postorder: ");
    theTree.postOrder(theTree.root);
  }
}
