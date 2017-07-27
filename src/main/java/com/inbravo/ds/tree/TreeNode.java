package com.inbravo.ds.tree;

/**
 * 
 * @author amit.dixit
 *
 */
public final class TreeNode {

  /* Data item (key) */
  public int iData;

  /* This node's left child */
  public TreeNode leftChild;

  /* This node's right child */
  public TreeNode rightChild;

  public TreeNode(final int iData) {
    this.iData = iData;
  }

  @Override
  public final String toString() {
    return "TreeNode [iData=" + iData + "]";
  }
}
