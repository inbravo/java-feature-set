package com.inbravo.ds.tree;

import java.util.Stack;

/**
 * Complexity = O(log N)
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
	public final void insert(final int iData, final double dData) {

		/* Make new node */
		final TreeNode newNode = new TreeNode(iData, dData);

		/* No node in root */
		if (root == null) {

			/* New node will be at root */
			root = newNode;
		}
		/* Root occupied */
		else {
			/* Start at root */
			TreeNode current = root;
			TreeNode parent;

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

	public static void main(String[] args) {

		final BinaryTree theTree = new BinaryTree();

		theTree.insert(50, 1.5);
		theTree.insert(25, 1.2);
		theTree.insert(75, 1.7);
		theTree.insert(12, 1.5);
		theTree.insert(37, 1.2);
		theTree.insert(43, 1.7);
		theTree.insert(30, 1.5);
		theTree.insert(33, 1.2);
		theTree.insert(87, 1.7);
		theTree.insert(93, 1.5);
		theTree.insert(97, 1.5);

		/* Display it */
		theTree.displayTree();
	}
}
