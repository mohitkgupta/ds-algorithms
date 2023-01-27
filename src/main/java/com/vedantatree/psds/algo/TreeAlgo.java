package com.vedantatree.psds.algo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.vedantatree.psds.ds.XBinaryTree;
import com.vedantatree.psds.ds.XTreeNode;


public class TreeAlgo
{

	public static int findClosestValueInBST( XTreeNode<Integer> root, int target )
	{
		assertNotNull( root );
		return Integer.MIN_VALUE;
	}

	private static int findClosestValueInBST( XTreeNode<Integer> root, int target, int previousNearest )
	{
		int data = root.getData();
		if( target == data )
		{
			return data;
		}
		else if( target < data )
		{
			return findClosestValueInBST( root.getLeftChild(), target );
		}
		else
		{
			return findClosestValueInBST( root.getRightChild(), target );
		}

	}

	/**
	 * Get diameter of given binary tree
	 * 
	 * @param tree The binary tree
	 * @return the diameter > max length of any path on left + max length of any path on right
	 */
	public static int getDiameterOfBinaryTree( XBinaryTree tree )
	{
		if( tree.getRootNode() == null )
		{
			return 0;
		}

		int values[] = getDiameterOfBinaryTreeInternal( tree.getRootNode(), 0 );

		return values[1];
	}

	/**
	 * The implementation can be much simpler if we store maxDiameter as global parameter.
	 * But that is not a right programming model, not self contained code.
	 * 
	 * @param node the node for which diameter needs to be figured out
	 * @param maxDiameter the max diameter collected so far
	 * @return the array.
	 *         First element is the count of edges on child hierarchy on one path.
	 *         Second element is the max diameter we found so far.
	 */
	private static int[] getDiameterOfBinaryTreeInternal( XTreeNode<Integer> node, int maxDiameter )
	{
		// array's first element is max count of either of the branch under specified node
		// array's second element is maxDiameter from this branch

		int[] leftValues = node.getLeftChild() == null ? null
				: getDiameterOfBinaryTreeInternal( node.getLeftChild(), maxDiameter );

		int[] rightValues = node.getRightChild() == null ? null
				: getDiameterOfBinaryTreeInternal( node.getRightChild(), maxDiameter );

		int leftCount = 0;
		int rightCount = 0;

		if( leftValues != null )
		{
			leftCount = leftValues[0] + 1;
			maxDiameter = leftValues[1] > maxDiameter ? leftValues[1] : maxDiameter;
		}
		if( rightValues != null )
		{
			rightCount = rightValues[0] + 1;
			maxDiameter = rightValues[1] > maxDiameter ? rightValues[1] : maxDiameter;
		}

		maxDiameter = leftCount + rightCount > maxDiameter ? leftCount + rightCount : maxDiameter;

		return new int[]
		{ Math.max( leftCount, rightCount ), maxDiameter };
	}

	// return nodes of the tree in breadth first search
	public static List<Integer> breadthFirstSearch( XBinaryTree tree )
	{
		assertNotNull( tree );
		assertNotNull( tree.getRootNode() );

		XTreeNode<Integer> node = tree.getRootNode();

		ArrayList<XTreeNode> nodesToTraverse = new ArrayList<>();
		nodesToTraverse.add( node );

		ArrayList<Integer> traversedNodes = new ArrayList<Integer>();
		traversedNodes.add( node.getData() );

		for( int i = 0; i < nodesToTraverse.size(); i++ )
		{
			node = nodesToTraverse.get( i );

			if( node.getLeftChild() != null )
			{
				traversedNodes.add( node.getLeftChild().getData() );
				nodesToTraverse.add( node.getLeftChild() );
			}
			if( node.getRightChild() != null )
			{
				traversedNodes.add( node.getRightChild().getData() );
				nodesToTraverse.add( node.getRightChild() );
			}

		}

		return traversedNodes;
	}

	// return nodes of the tree in depthFirstSearch (inorder)
	public List<Integer> depthFirstSearch( BinaryTree node )
	{
		// Write your code here.
		return depthFirstSearch( new ArrayList<Integer>(), node );
	}

	private List<Integer> depthFirstSearch( List<Integer> array, BinaryTree node )
	{
		if( node != null )
		{
			array.add( node.value );
			depthFirstSearch( array, node.left );
			depthFirstSearch( array, node.right );
		}

		return array;
	}

	// find sum of depths of all the nodes in tree
	public static int nodeDepths( BinaryTree root )
	{
		// Write your code here.
		return nodeDepths( root, 0, 0 );
	}

	public static int nodeDepths( BinaryTree root, int level, int depthSum )
	{
		if( root == null )
		{
			return depthSum;
		}

		depthSum += level;

		depthSum = nodeDepths( root.left, level + 1, depthSum );
		depthSum = nodeDepths( root.right, level + 1, depthSum );

		return depthSum;
	}

	// find sum of nodes in each branch, in order of left to right
	public static List<Integer> branchSums( BinaryTree root )
	{
		// traverse tree in inorder
		// once hit the last node in a branch, add the sum to the list
		// return list

		List<Integer> sums = new ArrayList<Integer>();
		branchSumsInternal( root, 0, sums );
		return sums;
	}

	private static void branchSumsInternal( BinaryTree root, int parentSum, List<Integer> sums )
	{
		parentSum += root.value;

		if( root.left == null && root.right == null )
		{
			sums.add( parentSum );
			return;
		}

		if( root.left != null )
			branchSumsInternal( root.left, parentSum, sums );
		if( root.right != null )
			branchSumsInternal( root.right, parentSum, sums );
	}

	/**
	 * @param currentNode the root node mostly
	 * @param nodeToFindSuccessor the node for which this function will return the successor
	 * @return successor for give node, considering in-order traversal
	 */
	public static XTreeNode findSuccessorInOrder( XTreeNode currentNode, Object nodeToFindSuccessor )
	{
		if( currentNode == null )
		{
			return null;
		}

		if( currentNode.getData() == nodeToFindSuccessor )
		{
			return findSuccessorInOrderInternal( currentNode );
		}

		XTreeNode successor = findSuccessorInOrder( currentNode.getLeftChild(), nodeToFindSuccessor );
		if( successor != null )
		{
			return successor;
		}

		return findSuccessorInOrder( currentNode.getRightChild(), nodeToFindSuccessor );
	}

	private static XTreeNode findSuccessorInOrderInternal( XTreeNode currentNode )
	{
		if( currentNode == null )
		{
			return null;
		}

		// if current node is the root node
		if( currentNode.getRightChild() != null )
		{
			return findLeftMostNode( currentNode.getRightChild() );
		}

		// else if return the parent, when current child is the left node of parent > null otherwise
		else if( currentNode.getParent() != null )
		{
			XTreeNode parentNode = currentNode.getParent();

			while( parentNode != null && currentNode == parentNode.getRightChild() )
			{
				currentNode = parentNode;
				parentNode = parentNode.getParent();
			}

			return parentNode;
		}
		return null;

	}

	private static XTreeNode findLeftMostNode( XTreeNode node )
	{
		while( node != null && node.getLeftChild() != null )
		{
			node = node.getLeftChild();
		}
		return node;
	}

	// TODO not correct logic. Revisit and fix it with fresh mind
	// Simplify if required using a object to carry children unbalance state
	public boolean heightBalancedBinaryTree( XBinaryTree tree )
	{
		assertNotNull( tree );
		assertNotNull( tree.getRootNode() );

		int height = countHeight( tree.getRootNode() );
		return height > 0 && height <= 1;
	}

	private int countHeight( XTreeNode rootNode )
	{
		if( rootNode == null )
			return 0;

		int leftTreeHeight = rootNode.getLeftChild() == null ? 0 : countHeight( rootNode.getLeftChild() );
		if( leftTreeHeight < 0 )
			return leftTreeHeight;
		else
			leftTreeHeight++;

		int rightTreeHeight = rootNode.getRightChild() == null ? 0 : countHeight( rootNode.getRightChild() );
		if( rightTreeHeight < 0 )
			return rightTreeHeight;
		else
			rightTreeHeight++;

		if( Math.abs( leftTreeHeight - rightTreeHeight ) > 1 )
		{
			return -1;
		}

		return Math.max( leftTreeHeight, rightTreeHeight );
	}

	/**
	 * 
	 * @param tree1Node root node of first tree, could be of subtree too when called recursively
	 * @param tree2Node root node of second tree, could be of subtree too when called recursively
	 * @return a root node with merged data from both trees.
	 *         Every node at same location will be sum of nodes from same location of both trees
	 *         If a node does not exist in one of the tree, resultant tree will have node with one side value only
	 */
	public static XTreeNode<Integer> mergeBinaryTrees( XTreeNode<Integer> tree1Node, XTreeNode<Integer> tree2Node )
	{
		if( tree1Node == null && tree2Node == null )
		{
			return null;
		}

		Integer data = ( tree1Node == null ? 0 : tree1Node.getData() )
				+ ( tree2Node == null ? 0 : tree2Node.getData() );

		XTreeNode<Integer> newNode = new XTreeNode<Integer>( null, data );

		newNode.setLeftChild( mergeBinaryTrees( tree1Node != null ? tree1Node.getLeftChild() : null,
				tree2Node != null ? tree2Node.getLeftChild() : null ) );

		newNode.setRightChild( mergeBinaryTrees( tree1Node != null ? tree1Node.getRightChild() : null,
				tree2Node != null ? tree2Node.getRightChild() : null ) );

		return newNode;
	}

	/**
	 * @param tree tree to check
	 * @return true if passed tree is symmetrical
	 *         which means that left and right children are mirror image of each other
	 */
	public static boolean isSymmetricalTree( XBinaryTree<Integer> tree )
	{
		return symmetricalTreeNodes( tree.getRootNode().getLeftChild(), tree.getRootNode().getRightChild() );
	}

	private static boolean symmetricalTreeNodes( XTreeNode<Integer> leftTreeNode, XTreeNode<Integer> rightTreeNode )
	{
		// true, if both are null
		if( leftTreeNode == rightTreeNode )
		{
			return true;
		}

		// false, if either of the node is null
		if( ( leftTreeNode == null && rightTreeNode != null ) || ( leftTreeNode != null && rightTreeNode == null ) )
		{
			return false;
		}

		// false, if data mismatch
		if( leftTreeNode.getData() != rightTreeNode.getData() )
		{
			return false;
		}

		// false, if left>left and right>right data mismatch
		// false, if left>right and right>left data mismatch
		return symmetricalTreeNodes( leftTreeNode.getLeftChild(), rightTreeNode.getRightChild() )
				&& symmetricalTreeNodes( leftTreeNode.getRightChild(), rightTreeNode.getLeftChild() );
	}

	public static List<Integer> findNodesDistanceK( XTreeNode<Integer> rootNode, int targetNodeValue,
			int searchDistance )
	{
		assertNotNull( rootNode );
		assertTrue( searchDistance >= 0 );

		BinaryTree tree = BinaryTree.convertToBinaryTree( rootNode );

		// updating each parent with distances from its children, including nested children in hierarchy
		markChildToParentDistances( tree );

		// updating each node with distances from all other nodes in the tree - Utilizing the distances collected in
		// each parent node with previous pass
		// Optimization > This pass will also collect all nodes distance maps in one map. Which will help optimizing the
		// search for any node & distance combination

		HashMap<Integer, Map<Integer, Integer>> nodeToDistancesMap = new HashMap<>();
		markParentToChildDistances( tree, nodeToDistancesMap );

		// debugging prints
		// BinaryTree.printBinaryTree( tree, " " );
		// System.out.println( nodeToDistancesMap );

		return findNodesAtKDistance( nodeToDistancesMap, targetNodeValue, searchDistance );
	}

	private static void markChildToParentDistances( BinaryTree tree )
	{
		if( tree == null )
			return;

		if( tree.left != null )
		{
			markChildToParentDistances( tree.left );
			updateChildToParentDistances( tree, tree.left );
		}
		if( tree.right != null )
		{
			markChildToParentDistances( tree.right );
			updateChildToParentDistances( tree, tree.right );
		}

		tree.distances.put( tree.value, 0 );
	}

	private static void updateChildToParentDistances( BinaryTree parent, BinaryTree child )
	{
		child.distances.forEach( ( nodeValue, distance ) -> {
			parent.distances.put( nodeValue, distance + 1 );
		} );
	}

	private static void markParentToChildDistances( BinaryTree root,
			HashMap<Integer, Map<Integer, Integer>> nodeToDistancesMap )
	{
		nodeToDistancesMap.put( root.value, root.distances );

		if( root.left != null )
			updateParentToChildrenDistances( root, root.left, nodeToDistancesMap );
		if( root.right != null )
			updateParentToChildrenDistances( root, root.right, nodeToDistancesMap );
	}

	private static void updateParentToChildrenDistances( BinaryTree parent, BinaryTree child,
			HashMap<Integer, Map<Integer, Integer>> nodeToDistancesMap )
	{
		parent.distances.forEach( ( nodeValue, distance ) -> {
			if( !child.distances.containsKey( nodeValue ) )
			{
				child.distances.put( nodeValue, distance + 1 );
			}
		} );

		markParentToChildDistances( child, nodeToDistancesMap );
	}

	private static List<Integer> findNodesAtKDistance( HashMap<Integer, Map<Integer, Integer>> nodeToDistancesMap,
			int targetNodeValue, int distanceToSearch )
	{
		ArrayList<Integer> collectedNodes = new ArrayList<>();
		Map<Integer, Integer> targetNodeDistances = nodeToDistancesMap.get( targetNodeValue );

		targetNodeDistances.forEach( ( node, nodeDistance ) -> {
			if( nodeDistance == distanceToSearch )
			{
				collectedNodes.add( node );
			}
		} );

		return collectedNodes;
	}

	/**
	 * A path is collection of connected nodes in a tree, where no node is connected to more than two other nodes
	 * A path sum is the sum of the values of the nodes in a particular path
	 * 
	 * @param rootNode The root node of a BinaryTree
	 * @return Max Path Sum. Return 0 if root node is null
	 */
	public static int getMaxSumPath( XBinaryTree<Integer> tree )
	{
		assertThat( tree ).isNotNull();

		if( tree.getRootNode() == null )
		{
			return 0;
		}

		BinaryTree rootNode = BinaryTree.convertToBinaryTree( tree.getRootNode() );

		int[] result = new int[]
		{ Integer.MIN_VALUE };
		calculateMaxSumPath( rootNode, result );
		return result[0];
	}

	/*
	 * For each node
	 * Calculate sum of left and right path recursively
	 * The path sum = node.value + left sum + right sum
	 * If this node path sum is > max path (passed in result)
	 * replace max path
	 * return max path of this node to its parent for further consideration
	 * max path of a node, for parent, is > node's value + max of left | right path sum
	 */
	private static int calculateMaxSumPath( BinaryTree rootNode, int[] result )
	{
		if( rootNode == null )
			return 0;

		int leftPathSum = calculateMaxSumPath( rootNode.left, result );
		int rightPathSum = calculateMaxSumPath( rootNode.right, result );

		result[0] = Math.max( result[0], rootNode.value + leftPathSum + rightPathSum );
		return rootNode.value + Math.max( leftPathSum, rightPathSum );
	}

	/**
	 * Compare two tree for leaf traversal
	 * Leaf traversal will be from left to right
	 * 
	 * @param tree1 First tree to compare
	 * @param tree2 Second tree to compare
	 * @return true if leaf traversal from both tree are same, ie same leaf nodes in same sequence
	 */
	public static boolean compareLeafTraversalX( XBinaryTree<Integer> tree1, XBinaryTree<Integer> tree2 )
	{
		return compareLeafTraversal( BinaryTree.convertToBinaryTree( tree1.getRootNode() ),
				BinaryTree.convertToBinaryTree( tree2.getRootNode() ) );
	}

	public static boolean compareLeafTraversal( BinaryTree tree1, BinaryTree tree2 )
	{
		ArrayList<Integer> tree1Leaves = new ArrayList<>();
		compareLeaveTraversalHelper( tree1, tree1Leaves );

		ArrayList<Integer> tree2Leaves = new ArrayList<>();
		compareLeaveTraversalHelper( tree2, tree2Leaves );

		return Arrays.equals( tree1Leaves.toArray(), tree2Leaves.toArray() );
	}

	private static void compareLeaveTraversalHelper( BinaryTree tree, ArrayList<Integer> leaveList )
	{
		if( tree == null )
			return;
		if( tree.left == null && tree.right == null )
			leaveList.add( tree.value );
		else
		{
			compareLeaveTraversalHelper( tree.left, leaveList );
			compareLeaveTraversalHelper( tree.right, leaveList );
		}
	}

	// TODO for later - either use recursive array approach as in algo expert
	// or build muscle to solve it using nested iteration
	public static boolean isSameBSTs( List<Integer> arrayOne, List<Integer> arrayTwo )
	{

		assertNotNull( arrayOne );
		assertNotNull( arrayTwo );

		if( arrayOne.size() != arrayTwo.size() )
			return false;

		if( arrayOne.get( 0 ) != arrayTwo.get( 0 ) )
			return false;

		int rootElement = arrayOne.get( 0 );
		int previousElement = rootElement;
		boolean isLarger = false;
		boolean elementMatched = false;

		for( int firstArrayElement : arrayOne )
		{
			isLarger = firstArrayElement >= previousElement;

			for( int secondArrayElement : arrayTwo )
			{
				if( firstArrayElement == secondArrayElement )
				{
					elementMatched = true;
					break;
				}
				else
				{
					if( isLarger && ( secondArrayElement < firstArrayElement && secondArrayElement > previousElement ) )
						return false;
					if( !isLarger
							&& ( secondArrayElement > firstArrayElement && secondArrayElement < previousElement ) )
						return false;
				}
			}
			if( !elementMatched ) // if first array element is not found in second array
				return false;

			previousElement = firstArrayElement;
			elementMatched = false;
		}
		return true;
	}

	// This is the class of the input root. Do not edit it.
	static class BinaryTree
	{

		int						value;
		BinaryTree				left;
		BinaryTree				right;
		Map<Integer, Integer>	distances	= new HashMap<>();

		BinaryTree( int value )
		{
			this.value = value;
			this.left = null;
			this.right = null;
		}

		static BinaryTree convertToBinaryTree( XTreeNode<Integer> rootNode )
		{
			if( rootNode == null )
			{
				return null;
			}

			BinaryTree btNode = new BinaryTree( rootNode.getData() );
			btNode.left = convertToBinaryTree( rootNode.getLeftChild() );
			btNode.right = convertToBinaryTree( rootNode.getRightChild() );

			return btNode;
		}

		static void printBinaryTree( BinaryTree node, String space )
		{
			System.out.println( space + node.value + " >> " + node.distances );
			if( node.left != null )
				printBinaryTree( node.left, space + "    " );
			if( node.right != null )
				printBinaryTree( node.right, space + "    " );
		}
	}

}

// private static void findNodesAtKDistance( BinaryTree currentNode, int targetNodeValue, int searchDistance,
// ArrayList<Integer> collectedNodes )
// {
// if( currentNode == null )
// {
// return;
// }
//
// // if current node is target node
// if( currentNode.value == targetNodeValue )
// {
// currentNode.distances.forEach( ( nodeValue, nodeDistance ) -> {
// if( nodeDistance == searchDistance )
// {
// collectedNodes.add( nodeValue );
// System.out.println( "target == current node > adding : " + nodeValue );
//
// }
// } );
// }
// else
// {
// // see if current node has mapping for target node in children distances map
// Integer targetDistance = currentNode.distances.get( targetNodeValue );
// if( targetDistance != null )
// {
//
// // if current node is at desired distance, add this to collectedNodes
// if( targetDistance == searchDistance )
// {
// System.out.println( "Child-map case >> target distance equal > adding : " + currentNode.value );
// collectedNodes.add( currentNode.value );
// }
// else if( targetDistance < searchDistance )
// {
// // TODO : find nodes at desiredDistance - targetDistance on opposite side of the target node
// int additionalDistance = searchDistance - targetDistance;
//
// currentNode.distances.forEach( ( nodeValue, nodeDistance ) -> {
// if( nodeDistance == additionalDistance && nodeValue != targetNodeValue )
// {
// collectedNodes.add( nodeValue );
// System.out.println( "Child-map case >> target distance lesser > adding : " + nodeValue );
// }
// } );
// }
// else // if targetDistance > desiredDistance >> go in child hierarchy - will find later
// {
// }
// }
// }
//
// findNodesAtKDistance( currentNode.left, targetNodeValue, searchDistance, collectedNodes );
// findNodesAtKDistance( currentNode.right, targetNodeValue, searchDistance, collectedNodes );
// }
//
