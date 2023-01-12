package com.vedantatree.psds.algo;

import java.util.ArrayList;
import java.util.List;

public class TreeAlgo {
	
	
	// return nodes of the tree in depthFirstSearch (inorder)
	public List<Integer> depthFirstSearch(BinaryTree node) {
	      // Write your code here.
	      return depthFirstSearch(new ArrayList<Integer>(), node);
	}
	
	private List<Integer> depthFirstSearch (List<Integer> array, BinaryTree node)
	{
		if (node != null)
		{
			array.add(node.value);
			depthFirstSearch(array, node.left);
			depthFirstSearch(array, node.right);
		}
		
		
		return array;
	}
	
	// find sum of depths of all the nodes in tree
	public static int nodeDepths(BinaryTree root) {
	    // Write your code here.
	    return nodeDepths(root, 0, 0);
	  }
	
	public static int nodeDepths (BinaryTree root, int level, int depthSum)
	{
		if (root == null)
		{
          return depthSum;
		}
      
        depthSum += level;		
		
        depthSum = nodeDepths (root.left, level + 1, depthSum);
		depthSum = nodeDepths (root.right, level + 1, depthSum);
		
		return depthSum;
	}

	// find sum of nodes in each branch, in order of left to right
	public static List<Integer> branchSums(BinaryTree root) {
		// traverse tree in inorder
		// once hit the last node in a branch, add the sum to the list
		// return list

		List<Integer> sums = new ArrayList<Integer>();		
		branchSumsInternal(root, 0, sums);		
		return sums;
	}

	private static void branchSumsInternal(BinaryTree root, int parentSum, List<Integer> sums) {
		parentSum += root.value;

      if (root.left == null && root.right == null)
    	{
			sums.add(parentSum);
            return;
		}
		
        if (root.left != null) 
            branchSumsInternal(root.left, parentSum, sums);
    	if (root.right != null)	
          branchSumsInternal(root.right, parentSum, sums);
	}

	// This is the class of the input root. Do not edit it.
	static class BinaryTree {
		int value;
		BinaryTree left;
		BinaryTree right;

		BinaryTree(int value) {
			this.value = value;
			this.left = null;
			this.right = null;
		}
	}

}
