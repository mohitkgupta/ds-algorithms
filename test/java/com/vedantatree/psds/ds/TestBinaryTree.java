package com.vedantatree.psds.ds;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import com.vedantatree.psds.Utils;
import com.vedantatree.psds.ds.XBinaryTree.XTreeTraversalResult;

import junit.framework.TestCase;


/**
 * 
 * @author Mohit Gupta <mohit.gupta@vedantatree.com>
 */
public class TestBinaryTree extends TestCase
{

	private int treeNodesSize = 11;

	public void testCreateBST()
	{
		XBinaryTree<Integer> bst = new XBinaryTree<>();

		int[] data =
		{ 21, 1, 12, 20, 42, 3, 26, 18, 21, 37, 32, 0, 5, 71, 2, 4 };
		for( int element : data )
		{
			bst.insertBSTNode( element );
		}

		bst.printTreeInMatrix();

		XTreeNode<Integer> node = bst.searchNodeInBST( 32 );

		assertNotNull( "Node is not found, but it exists in tree", node );
		assertTrue( "Value of searched node should be 32, but it is " + node.getData(), node.getData() == 32 );

		node = bst.searchNodeInBST( 12 );
		assertNotNull( "Node is not found, but it exists in tree", node );
		assertTrue( "Value of searched node should be 12, but it is " + node.getData(), node.getData() == 12 );

		node = bst.searchNodeInBST( 100 );
		assertNull( "Node is found, but it does not exist in tree", node );
	}

	private XBinaryTree<Integer> createBinaryTree()
	{
		return createBinaryTree( treeNodesSize );
	}

	private XBinaryTree<Integer> createBinaryTree( int nodesSize )
	{
		Stack<Integer> dataSet = new Stack<>();
		for( int i = 0; i < nodesSize; i++ )
		{
			dataSet.push( i );
		}

		return createBinaryTree( dataSet );
	}

	private XBinaryTree<Integer> createBinaryTree( int[] dataArray )
	{
		Stack<Integer> dataSet = new Stack<>();
		for( int element : dataArray )
		{
			dataSet.push( element );
		}

		return createBinaryTree( dataSet );
	}

	private XBinaryTree<Integer> createBinaryTree( Stack<Integer> dataSet )
	{
		XTreeNode<Integer> rootNode = createChildNodes( dataSet, new XTreeNode<>( null, dataSet.pop() ) );
		return new XBinaryTree<>( rootNode );
	}

	// take one list.. add root, add left, add right, then pass half of the list to
	// left and half to right to create their children

	private XTreeNode<Integer> createChildNodes( Stack<Integer> dataSet, XTreeNode<Integer> root )
	{
		if( dataSet.isEmpty() || root == null )
		{
			return root;
		}

		List<Integer> dataList = dataSet.subList( 0, dataSet.size() );

		// divide the list in half, half +1 if length is odd number, exactly half if it is even

		List<Integer> subDataList = dataList.subList( 0,
				dataList.size() % 2 == 0 ? dataList.size() / 2 : dataList.size() / 2 + 1 );
		createTreeNode( listToStack( subDataList ), root, true );

		// no element for second half if data list size is 1 only, that should have been picked in left node creation in
		// previous step

		if( dataList.size() >= 2 )
		{
			subDataList = dataList.subList( ( dataList.size() / 2 ), dataList.size() );
			createTreeNode( listToStack( subDataList ), root, false );
		}

		return root;
	}

	private XTreeNode<Integer> createTreeNode( Stack<Integer> dataSet, XTreeNode<Integer> root, boolean left )
	{

		if( dataSet.isEmpty() || root == null )
		{
			return root;
		}

		Integer data = dataSet.pop();
		XTreeNode<Integer> childNode = new XTreeNode<>( root, data );

		if( left )
		{
			root.setLeftChild( childNode );
		}
		else
		{
			root.setRightChild( childNode );
		}
		createChildNodes( dataSet, childNode );

		return childNode;
	}

	public void testCreateBSTFromSortedArray()
	{
		Integer[] sortedArray = new Integer[]
		{ 2, 5, 8, 12, 13, 15, 19, 33, 45, 46, 50, 51 };

		XBinaryTree<Integer> bst = new XBinaryTree<>();
		bst.populateBSTFromSortedArray( sortedArray );

		bst.printLevelWise();
		bst.printTree();
		bst.printTreeInMatrix();

		List<Integer> preOrderValidation = Arrays.asList( 15, 8, 2, 5, 12, 13, 45, 19, 33, 50, 46, 51 );
		List<Integer> preOrderCollected = bst.preorderTraversal( true );
		System.out.println( preOrderCollected );

		assertEquals( "node size is different", preOrderValidation.size(), preOrderCollected.size() );

		Utils.assertListEquals( preOrderValidation, preOrderCollected );
	}

	public void testPreOrderTraversal()
	{
		Integer[] sortedArray = new Integer[]
		{ 2, 5, 8, 12, 13, 15, 19, 33, 45, 46, 50, 51 };

		XBinaryTree<Integer> bst = new XBinaryTree<>();
		bst.populateBSTFromSortedArray( sortedArray );

		bst.printLevelWise();
		bst.printTree();
		bst.printTreeInMatrix();

		List<Integer> preOrderValidation = Arrays.asList( 15, 8, 2, 5, 12, 13, 45, 19, 33, 50, 46, 51 );
		List<Integer> preOrderCollected = bst.preorderTraversalWithoutRecursioin();
		
		System.out.println( "\npre-order collected >> " + preOrderCollected );

		assertEquals( "node size is different", preOrderValidation.size(), preOrderCollected.size() );

		Utils.assertListEquals( preOrderValidation, preOrderCollected );
	}

	public void testCheckHeight()
	{
		int numberOfNodes = 21;
		XBinaryTree<Integer> bst = createBinaryTree( numberOfNodes );

		// TODO check formula to calculate height for balanced tree, this is not right
		System.out.println( Math.log( numberOfNodes + 1 ) );

		bst.printTreeInMatrix();

		int treeHeight = bst.checkHeightAndBalanced();

		assertEquals( "Height of balanced tree is not as expected", 4, treeHeight );
	}

	public void testMaxValue()
	{
		int[] data =
		{ 1, 2, 88, 92, 3, 9999, 4, 51, 21, 5, 65 };
		XBinaryTree<Integer> tree = createBinaryTree( data );
		tree.printTreeInMatrix();

		assertTrue( "Maximum value is not matching > " + tree.findMaximumValue( false ),
				tree.findMaximumValue( false ) == 9999 );
		assertTrue( "Maximum value is not matching > " + tree.findMaximumValue( true ),
				tree.findMaximumValue( true ) == 9999 );
	}

	public void testEqualTree()
	{
		XBinaryTree<Integer> tree1 = createBinaryTree( 5 );
		XBinaryTree<Integer> tree2 = createBinaryTree( 6 );
		XBinaryTree<Integer> tree3 = createBinaryTree( 5 );

		assertTrue( "Tree must be equal, but found unequal", tree1.isEqualTree( tree3 ) );
		assertFalse( "Tree must be unequal, but found equal", tree1.isEqualTree( tree2 ) );
	}

	public void testInorderTraversalAndBSTValidation()
	{
		// test with BST tree
		Integer[] nodeDataArray = new Integer[]
		{ 2, 5, 8, 12, 13, 15, 19, 21, 45, 46, 50, 51, 99, 101, 134, 232, 345, 456, 567, 678, 789, 897, 908, 1011 };

		XBinaryTree<Integer> bst = createBinaryTreeFromArray( nodeDataArray, false );

		XTreeTraversalResult<Integer> traversalResult = bst.inorderTraversalAndValidate( true, true );
		assertEquals( "Tree is BST, but result shows it otherwise", true, traversalResult.isBST() );

		traversalResult = bst.inorderTraversalAndValidate( false, true );
		assertEquals( "Tree is BST, but result shows it otherwise", true, traversalResult.isBST() );

		// test with non-BST tree
		nodeDataArray = new Integer[]
		{ 2, 5, 12, 768, 5, 98, 1023, 13, 15, 19, 33, 45, 46, 50, 51, 99, 101, 134, 232, 345, 456, 567, 678, 789, 897,
				908, 1011 };

		bst = createBinaryTreeFromArray( nodeDataArray, false );

		traversalResult = bst.inorderTraversalAndValidate( true, true );
		assertEquals( "Tree is not BST, but result shows it BST", false, traversalResult.isBST() );

		traversalResult = bst.inorderTraversalAndValidate( false, true );
		assertEquals( "Tree is not BST, but result shows it BST", false, traversalResult.isBST() );
	}

	public void testIsBST()
	{
		// test with BST tree
		Integer[] nodeDataArray = new Integer[]
		{ 2, 5, 8, 12, 13, 15, 19, 21, 45, 46, 50, 51, 99, 101, 134, 232, 345, 456, 567, 678, 789, 897, 908, 1011 };

		XBinaryTree<Integer> binaryTree = createBinaryTreeFromArray( nodeDataArray, false );

		assertEquals( "Tree is BST, but result shows it otherwise", true, binaryTree.isBST() );

		// test with non-BST tree
		nodeDataArray = new Integer[]
		{ 2, 5, 12, 768, 5, 98, 1023, 13, 15, 19, 33, 45, 46, 50, 51, 99, 101, 134, 232, 345, 456, 567, 678, 789, 897,
				908, 1011 };

		binaryTree = createBinaryTreeFromArray( nodeDataArray, false );

		assertEquals( "Tree is not BST, but result shows it BST", false, binaryTree.isBST() );

	}

	public void testSearchInTree()
	{
		Integer[] nodeDataArray = new Integer[]
		{ 2, 5, 8, 12, 13, 15, 19, 21, 45, 46, 50, 51, 99, 101, 134, 232, 345, 456, 567, 678, 789, 897, 908, 1011 };

		XBinaryTree<Integer> binaryTree = createBinaryTreeFromArray( nodeDataArray, false );
		XTreeNode<Integer> searchedNode = binaryTree.searchNodeInBST( 51 );

		assertNotNull( "Searched Node found null, but it exist in tree", searchedNode );
		assertEquals( "Search node value does not match with expected", Integer.valueOf( 51 ), searchedNode.getData() );

		// test with non-BST tree
		nodeDataArray = new Integer[]
		{ 2, 5, 12, 768, 5, 98, 1023, 13, 15, 19, 33, 45, 46, 50, 51, 99, 101, 134, 232, 345, 456, 567, 678, 789, 897,
				908, 1011 };
		binaryTree = createBinaryTreeFromArray( nodeDataArray, false );

		// search wrongly placed node from BST perspective
		searchedNode = binaryTree.searchNodeInBST( 1023 );
		assertNull( "Searched Node found, but it is not expected to be searched as tree is not BST", searchedNode );

		// earch wrongly placed node but by search in binary tree method, without BST assumption
		searchedNode = binaryTree.searchNodeInBinaryTree( 1023 );
		assertNotNull( "Searched Node is not found, but it is in tree", searchedNode );

	}

	public void testGetInOrderSuccessor()
	{
		Integer[] nodeDataArray = new Integer[]
		{ 2, 5, 8, 12, 13, 15, 19, 21, 45, 46, 50, 51, 99, 101, 134, 232, 345, 456, 567, 678, 789, 897, 908, 1011 };

		XBinaryTree<Integer> binaryTree = createBinaryTreeFromArray( nodeDataArray, false );
		XTreeNode<Integer> inOrderSuccessorNode = binaryTree.getInOrderSuccessor( 51 );

		assertNotNull( "Searched Node found null, but it exist in tree", inOrderSuccessorNode );
		assertEquals( "Search node value does not match with expected", Integer.valueOf( 99 ),
				inOrderSuccessorNode.getData() );

		inOrderSuccessorNode = binaryTree.getInOrderSuccessor( 15 );

		assertNotNull( "Searched Node found null, but it exist in tree", inOrderSuccessorNode );
		assertEquals( "Search node value does not match with expected", Integer.valueOf( 19 ),
				inOrderSuccessorNode.getData() );

		inOrderSuccessorNode = binaryTree.getInOrderSuccessor( 50 );

		assertNotNull( "Searched Node found null, but it exist in tree", inOrderSuccessorNode );
		assertEquals( "Search node value does not match with expected", Integer.valueOf( 51 ),
				inOrderSuccessorNode.getData() );

	}

	public void testFindCommonAncestor()
	{
		Integer[] nodeDataArray = new Integer[]
		{ 2, 5, 8, 12, 13, 15, 191, 21, 45, 46, 50, 51, 99, 101, 134, 232, 345, 456, 567, 678, 789, 897, 908, 1011 };

		XBinaryTree<Integer> binaryTree = createBinaryTreeFromArray( nodeDataArray, false );

		// same tree, one is another's child on left subtree
		XTreeNode<Integer> commonAncestor = binaryTree.findCommonAncestor( 15, 2 );
		assertNotNull( "Common ancestor is found null", commonAncestor );
		assertEquals( "Common ancestor is not matching", Integer.valueOf( 15 ), commonAncestor.getData() );

		// same tree, one is another's child on right subtree
		commonAncestor = binaryTree.findCommonAncestor( 8, 13 );
		assertNotNull( "Common ancestor is found null", commonAncestor );
		assertEquals( "Common ancestor is not matching", Integer.valueOf( 8 ), commonAncestor.getData() );

		// both are refering to same node, root
		commonAncestor = binaryTree.findCommonAncestor( 51, 51 );
		assertNotNull( "Common ancestor is found null", commonAncestor );
		assertEquals( "Common ancestor is not matching", Integer.valueOf( 51 ), commonAncestor.getData() );

		// no node for first data
		try
		{
			commonAncestor = binaryTree.findCommonAncestor( 34323, 51 );
			assertNotNull( "This should have thrown assertion error", commonAncestor );
		}
		catch( AssertionError e )
		{
		}

		// nodes in left <> right subtree of root
		commonAncestor = binaryTree.findCommonAncestor( 13, 232 );
		assertNotNull( "Common ancestor is found null", commonAncestor );
		assertEquals( "Common ancestor is not matching", Integer.valueOf( 51 ), commonAncestor.getData() );

	}

	public void testFindCommonAncestorWithoutParentLink()
	{
		Integer[] nodeDataArray = new Integer[]
		{ 2, 5, 8, 12, 13, 15, 191, 21, 45, 46, 50, 51, 99, 101, 134, 232, 345, 456, 567, 678, 789, 897, 908, 1011 };

		XBinaryTree<Integer> binaryTree = createBinaryTreeFromArray( nodeDataArray, false );

		// same tree, one is another's child on left subtree
		XTreeNode<Integer> commonAncestor = binaryTree.findCommonAncestorWithoutParentLink( 15, 2 );
		assertNotNull( "Common ancestor is found null", commonAncestor );
		assertEquals( "Common ancestor is not matching", Integer.valueOf( 15 ), commonAncestor.getData() );

		// same tree, one is another's child on right subtree
		commonAncestor = binaryTree.findCommonAncestorWithoutParentLink( 8, 13 );
		assertNotNull( "Common ancestor is found null", commonAncestor );
		assertEquals( "Common ancestor is not matching", Integer.valueOf( 8 ), commonAncestor.getData() );

		// both are refering to same node, root
		commonAncestor = binaryTree.findCommonAncestorWithoutParentLink( 51, 51 );
		assertNotNull( "Common ancestor is found null", commonAncestor );
		assertEquals( "Common ancestor is not matching", Integer.valueOf( 51 ), commonAncestor.getData() );

		// no node for first data
		try
		{
			commonAncestor = binaryTree.findCommonAncestorWithoutParentLink( 34323, 51 );
			assertNotNull( "This should have thrown assertion error", commonAncestor );
		}
		catch( AssertionError e )
		{
		}

		// nodes in left <> right subtree of root
		commonAncestor = binaryTree.findCommonAncestorWithoutParentLink( 13, 232 );
		assertNotNull( "Common ancestor is found null", commonAncestor );
		assertEquals( "Common ancestor is not matching", Integer.valueOf( 51 ), commonAncestor.getData() );

	}

	public void testCreateFromArray()
	{
		Integer[] nodeDataArray = new Integer[]
		{ 1, 2, 3, 4, 5 };

		XBinaryTree<Integer> binaryTree = createBinaryTreeFromArray( nodeDataArray, true );

	}

	/*
	 * Create binary tree from given array
	 * It will create BST if given array is sorted
	 */

	private XBinaryTree<Integer> createBinaryTreeFromArray( Integer[] nodeDataArray, boolean bst )
	{
		XBinaryTree<Integer> binaryTree = new XBinaryTree<>();
		if( bst )
		{
			binaryTree.populateBSTFromArray( nodeDataArray );
		}
		else
		{
			binaryTree.populateBSTFromSortedArray( nodeDataArray );
		}
		binaryTree.printTreeInMatrix();

		return binaryTree;
	}

	private Stack<Integer> listToStack( List<Integer> dataList )
	{
		if( dataList == null )
		{
			return null;
		}
		Stack<Integer> dataSet = new Stack<>();

		for( Integer integer : dataList )
		{
			dataSet.push( integer );
		}

		return dataSet;
	}

	public static void main( String[] args )
	{
		XBinaryTree<Integer> binaryTree = new TestBinaryTree().createBinaryTree();

		System.out.println( "hight of the tree[" + binaryTree.getHeight() + "]" );

		binaryTree.printTreeInMatrix();

		// System.out.println( "in order traversal > " + binaryTree.inorderTraversal( true ) );
		// System.out.println( "pre order traversal > " + binaryTree.preorderTraversal( false ) );
		// System.out.println( "post order traversal > " + binaryTree.postorderTraversal( true ) );
		System.out.println( "level traversal > " + binaryTree.traverseLevels( false ) );
		System.out.println( "level traversal > " + binaryTree.traverseLevels( true ) );
	}

}
