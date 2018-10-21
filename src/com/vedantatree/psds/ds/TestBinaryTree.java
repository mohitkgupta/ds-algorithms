package com.vedantatree.psds.ds;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import junit.framework.TestCase;


public class TestBinaryTree extends TestCase
{

	private int treeNodesSize = 11;

	public void testMaxValue()
	{
		int[] data =
		{ 1, 2, 88, 92, 3, 9999, 4, 51, 21, 5, 65 };
		BinaryTree<Integer> tree = createBinaryTree( data );
		tree.printTreeInMatrix();

		assertTrue( "Maximum value is not matching > " + tree.findMaximumValue( false ),
				tree.findMaximumValue( false ) == 9999 );
		assertTrue( "Maximum value is not matching > " + tree.findMaximumValue( false ),
				tree.findMaximumValue( true ) == 9999 );
	}

	public void testEqualTree()
	{
		BinaryTree<Integer> tree1 = createBinaryTree( 5 );
		BinaryTree<Integer> tree2 = createBinaryTree( 6 );
		BinaryTree<Integer> tree3 = createBinaryTree( 5 );

		assertTrue( "Tree must be equal, but found unequal", tree1.isEqualTree( tree3 ) );
		assertFalse( "Tree must be unequal, but found equal", tree1.isEqualTree( tree2 ) );
	}

	public void testCreateBST()
	{
		BinaryTree<Integer> bst = new BinaryTree<>();

		int[] data =
		{ 21, 1, 12, 20, 42, 3, 26, 18, 21, 37, 32, 0, 5, 71, 2, 4 };
		for( int i = 0; i < data.length; i++ )
		{
			bst.insertBSTNode( data[i] );
		}

		bst.printTreeInMatrix();

		TreeNode<Integer> node = bst.searchInTree( 32 );

		assertNotNull( "node should not be null", node );
		assertTrue( "value of searched node should be 32", node.getData() == 32 );

		node = bst.searchInTree( 12 );
		assertNotNull( "node should not be null", node );
		assertTrue( "value of searched node should be 12", node.getData() == 12 );

		node = bst.searchInTree( 100 );
		assertNull( "node should be null", node );
	}

	private BinaryTree<Integer> createBinaryTree()
	{
		return createBinaryTree( treeNodesSize );
	}

	private BinaryTree<Integer> createBinaryTree( int nodesSize )
	{
		Stack<Integer> dataSet = new Stack<>();
		for( int i = 0; i < nodesSize; i++ )
		{
			dataSet.push( i );
		}

		return createBinaryTree( dataSet );
	}

	private BinaryTree<Integer> createBinaryTree( int[] dataArray )
	{
		Stack<Integer> dataSet = new Stack<>();
		for( int i = 0; i < dataArray.length; i++ )
		{
			dataSet.push( dataArray[i] );
		}

		return createBinaryTree( dataSet );
	}

	private BinaryTree<Integer> createBinaryTree( Stack<Integer> dataSet )
	{
		TreeNode<Integer> rootNode = createChildNodes( dataSet, new TreeNode<Integer>( null, dataSet.pop() ) );
		return new BinaryTree<>( rootNode );
	}

	// take one list.. add root, add left, add right, then pass half of the list to
	// left and half to right to create their children

	private TreeNode<Integer> createChildNodes( Stack<Integer> dataSet, TreeNode<Integer> root )
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

	private TreeNode<Integer> createTreeNode( Stack<Integer> dataSet, TreeNode<Integer> root, boolean left )
	{

		if( dataSet.isEmpty() || root == null )
		{
			return root;
		}

		Integer data = dataSet.pop();
		TreeNode<Integer> childNode = new TreeNode<Integer>( root, data );

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

	private Stack<Integer> listToStack( List<Integer> dataList )
	{
		if( dataList == null )
		{
			return null;
		}
		Stack<Integer> dataSet = new Stack<>();

		for( Iterator<Integer> iterator = dataList.iterator(); iterator.hasNext(); )
		{
			dataSet.push( (Integer) iterator.next() );
		}

		return dataSet;
	}

	public static void main( String[] args )
	{
		BinaryTree<Integer> binaryTree = new TestBinaryTree().createBinaryTree();

		System.out.println( "hight of the tree[" + binaryTree.getHeight() + "]" );

		binaryTree.printTreeInMatrix();

		// System.out.println( "in order traversal > " + binaryTree.inorderTraversal( true ) );
		// System.out.println( "pre order traversal > " + binaryTree.preorderTraversal( false ) );
		// System.out.println( "post order traversal > " + binaryTree.postorderTraversal( true ) );
		System.out.println( "level traversal > " + binaryTree.traverseLevels( false ) );
		System.out.println( "level traversal > " + binaryTree.traverseLevels( true ) );
	}

}
