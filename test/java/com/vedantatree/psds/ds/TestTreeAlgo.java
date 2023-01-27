package com.vedantatree.psds.ds;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import com.vedantatree.psds.Utils;
import com.vedantatree.psds.algo.TreeAlgo;

import junit.framework.TestCase;


public class TestTreeAlgo extends TestCase
{

	public void testBreadthFirstSearch()
	{
		Integer[] sortedArray = new Integer[]
		{ 2, 5, 8, 12, 13, 15, 19, 33, 45, 46, 50, 51 };

		XBinaryTree<Integer> bst = new XBinaryTree<>();
		bst.populateBinaryTreeFromArray( sortedArray );

		bst.printTreeInMatrix();

		List<Integer> traversedNodes = TreeAlgo.breadthFirstSearch( bst );
		Utils.printArray( traversedNodes.toArray() );
	}

	public void testGetDiameterOfBinaryTree()
	{
		Integer[] array = new Integer[]
		{ 8, 1, 5, 2, 9, 7, 12, 6, 0, 14, 3, 14, 6, 6, 6, 6, 6, 6, 6, 6 };

		XBinaryTree<Integer> bst = new XBinaryTree<>();
		bst.populateBSTFromArray( array );

		int diameter = TreeAlgo.getDiameterOfBinaryTree( bst );

		assertEquals( 16, diameter );

		// 2
		Integer[] sortedArray = new Integer[]
		{ 2, 5, 8, 12, 13, 15, 19, 33, 45, 46, 50, 51, 55, 65, 71, 74, 81, 89, 100 };

		bst = new XBinaryTree<>();
		bst.populateBinaryTreeFromArray( sortedArray );

		diameter = TreeAlgo.getDiameterOfBinaryTree( bst );
		assertEquals( 8, diameter );
	}

	public void testFindSuccessorInOrder()
	{
		Integer[] sortedArray = new Integer[]
		{ 2, 5, 8, 9, 10, 11, 12, 13, 15, 19, 33, 45, 46, 50, 51 };

		XBinaryTree<Integer> bst = new XBinaryTree<>();
		bst.populateBinaryTreeFromArray( sortedArray );

		bst.printTreeInMatrix();

		XTreeNode successor = TreeAlgo.findSuccessorInOrder( bst.getRootNode(), Integer.valueOf( 33 ) );
		assertNotNull( successor );
		assertEquals( 45, successor.getData() );

		successor = TreeAlgo.findSuccessorInOrder( bst.getRootNode(), 2 );
		assertNotNull( successor );
		assertEquals( 5, successor.getData() );

		successor = TreeAlgo.findSuccessorInOrder( bst.getRootNode(), 12 );
		assertNotNull( successor );
		assertEquals( 13, successor.getData() );

		successor = TreeAlgo.findSuccessorInOrder( bst.getRootNode(), 13 );
		assertNotNull( successor );
		assertEquals( 15, successor.getData() );

		successor = TreeAlgo.findSuccessorInOrder( bst.getRootNode(), 15 );
		assertNotNull( successor );
		assertEquals( 19, successor.getData() );

		successor = TreeAlgo.findSuccessorInOrder( bst.getRootNode(), 51 );
		assertEquals( null, successor );

		successor = TreeAlgo.findSuccessorInOrder( bst.getRootNode(), 46 );
		assertNotNull( successor );
		assertEquals( 50, successor.getData() );

		successor = TreeAlgo.findSuccessorInOrder( bst.getRootNode(), 9 );
		assertNotNull( successor );
		assertEquals( 10, successor.getData() );
	}

	// TreeAlgo.mergeBinaryTrees
	// TODO: not complete test - for that need to pick specific node from tree and assert on its value
	// bit of hard coding
	public void testmergeBinaryTrees()
	{
		Integer[] sortedArray = new Integer[]
		{ 2, 5, 8, 9, 10, 11, 12, 13, 15, 19, 33, 45, 46, 50, 51 };

		// first tree
		XBinaryTree<Integer> bst1 = new XBinaryTree<>();
		bst1.populateBinaryTreeFromArray( sortedArray );

		bst1.printTreeInMatrix();

		sortedArray = new Integer[]
		{ 5, 8, 98, 57, 2, 21 };

		// second tree
		XBinaryTree<Integer> bst2 = new XBinaryTree<>();
		bst2.populateBinaryTreeFromArray( sortedArray );

		bst2.printTreeInMatrix();

		// call test method to merge
		XTreeNode<Integer> newRootNode = TreeAlgo.mergeBinaryTrees( bst1.getRootNode(), bst2.getRootNode() );
		assertNotNull( newRootNode );

		XBinaryTree<Integer> mergedTree = new XBinaryTree<>( newRootNode );

		// printing merged tree
		mergedTree.printTreeInMatrix();
	}

	// TreeAlgo.symmetricalTree
	public void testSymmetricalTree()
	{
		Integer[] sortedArray = new Integer[]
		{ 2, 5, 8, 9, 10, 11, 12, 13, 12, 11, 10, 9, 8, 5, 2 };

		XBinaryTree<Integer> bst = new XBinaryTree<>();
		bst.populateBinaryTreeFromArray( sortedArray );

		bst.printTreeInMatrix();

		assertTrue( TreeAlgo.isSymmetricalTree( bst ) );

		sortedArray = new Integer[]
		{ 2, 5, 8, 9, 10, 11, 12, 13, 2, 5, 8, 9, 10, 11, 12 };

		bst = new XBinaryTree<>();
		bst.populateBinaryTreeFromArray( sortedArray );

		bst.printTreeInMatrix();

		assertFalse( TreeAlgo.isSymmetricalTree( bst ) );
	}

	public void testSameBST()
	{
		List<Integer> arrayOne = Arrays.asList( 10, 15, 8, 12, 94, 81, 5, 2, 11 );
		List<Integer> arrayTwo = Arrays.asList( 10, 8, 5, 15, 2, 12, 11, 94, 81 );

		XBinaryTree<Integer> bst = new XBinaryTree<>();
		bst.populateBSTFromArray( new Integer[]
		{ 10, 15, 8, 12, 94, 81, 5, 2, 11 } );
		bst.printTreeInMatrix();

		bst = new XBinaryTree<>();
		bst.populateBSTFromArray( new Integer[]
		{ 10, 8, 5, 15, 2, 12, 11, 94, 81 } );
		bst.printTreeInMatrix();

		assertTrue( TreeAlgo.isSameBSTs( arrayOne, arrayTwo ) );

		arrayOne = Arrays.asList( 10, 15, 8, 12, 94, 81, 5, 2, -1, 101, 45, 12, 8, -1, 8, 2, -34 );
		arrayTwo = Arrays.asList( 10, 8, 5, 15, 2, 12, 94, 81, -1, -1, -34, 8, 2, 8, 12, 45, 100 );

		bst = new XBinaryTree<>();
		bst.populateBSTFromArray( new Integer[]
		{ 10, 15, 8, 12, 94, 81, 5, 2, -1, 101, 45, 12, 8, -1, 8, 2, -34 } );
		bst.printTreeInMatrix();

		bst = new XBinaryTree<>();
		bst.populateBSTFromArray( new Integer[]
		{ 10, 8, 5, 15, 2, 12, 94, 81, -1, -1, -34, 8, 2, 8, 12, 45, 100 } );
		bst.printTreeInMatrix();

		assertTrue( !TreeAlgo.isSameBSTs( arrayOne, arrayTwo ) );

		arrayOne = Arrays.asList( 5, 2, -1, 100, 45, 12, 8, -1, 8, 10, 15, 8, 12, 94, 81, 2, -34 );
		arrayTwo = Arrays.asList( 5, 8, 10, 15, 2, 8, 12, 45, 100, 2, 12, 94, 81, -1, -1, -34, 8 );

		bst = new XBinaryTree<>();
		bst.populateBSTFromArray( new Integer[]
		{ 5, 2, -1, 100, 45, 12, 8, -1, 8, 10, 15, 8, 12, 94, 81, 2, -34 } );
		bst.printTreeInMatrix();

		bst = new XBinaryTree<>();
		bst.populateBSTFromArray( new Integer[]
		{ 5, 8, 10, 15, 2, 8, 12, 45, 100, 2, 12, 94, 81, -1, -1, -34, 8 } );
		bst.printTreeInMatrix();

		assertTrue( !TreeAlgo.isSameBSTs( arrayOne, arrayTwo ) );
	}

	// TreeAlgo.findNodesDistanceK
	public void testFindNodesDistanceKOnlyLeftStraightBranch()
	{
		// case of only left branch
		XTreeNode<Integer> node = new XTreeNode<Integer>( null, 1 );
		node.setLeftChild( new XTreeNode<>( node, 2 ) ).setLeftChild( new XTreeNode<>( node, 3 ) )
				.setLeftChild( new XTreeNode<>( node, 4 ) ).setLeftChild( new XTreeNode<>( node, 5 ) );

		XBinaryTree<Integer> tree = new XBinaryTree<>( node );
		tree.printTreeInMatrix();

		List<Integer> nodes = TreeAlgo.findNodesDistanceK( node, 2, 3 );

		System.out.println( nodes );

		assertNotNull( nodes );
		assertThat( nodes.size() ).isGreaterThan( 0 );
		assertThat( nodes.get( 0 ) ).isEqualTo( 5 );
	}

	// TreeAlgo.findNodesDistanceK
	public void testFindNodesDistanceKFullTree()
	{
		XBinaryTree<Integer> bst = new XBinaryTree<>();

		bst.populateBSTFromArray( new Integer[]
		{ 5, 2, -1, 100, 45, 12, 94, -34, 3, 102, 101 } );
		bst.printTreeInMatrix();

		List<Integer> nodes = TreeAlgo.findNodesDistanceK( bst.getRootNode(), 94, 3 );

		System.out.println( nodes );

		assertNotNull( nodes );
		assertThat( nodes.size() ).isEqualTo( 2 );
		assertThat( nodes.get( 0 ) ).isEqualTo( 5 );
		assertThat( nodes.get( 1 ) ).isEqualTo( 102 );

		// case of child node having more distance from root, than search distance
		nodes = TreeAlgo.findNodesDistanceK( bst.getRootNode(), -34, 2 );

		System.out.println( nodes );

		assertNotNull( nodes );
		assertThat( nodes.size() ).isEqualTo( 1 );
		assertThat( nodes.get( 0 ) ).isEqualTo( 2 );

		// case of search distance spanning from left to right
		nodes = TreeAlgo.findNodesDistanceK( bst.getRootNode(), -34, 6 );

		System.out.println( nodes );

		assertNotNull( nodes );
		assertThat( nodes.size() ).isEqualTo( 3 );
		assertThat( nodes.get( 0 ) ).isEqualTo( 101 );
		assertThat( nodes.get( 1 ) ).isEqualTo( 12 );
		assertThat( nodes.get( 2 ) ).isEqualTo( 94 );
	}

	public static void testGetMaxSumPath()
	{
		XBinaryTree<Integer> bst = new XBinaryTree<>();

		bst.populateBSTFromArray( new Integer[]
		{ 5, 2, -1, 100, 45, 12, 94, -34, 3, 102, 101 } );
		bst.printTreeInMatrix();

		int maxSumPath = TreeAlgo.getMaxSumPath( bst );

		assertThat( maxSumPath ).isGreaterThan( Integer.MIN_VALUE );
		assertThat( maxSumPath ).isEqualTo( 442 );

		bst = new XBinaryTree<>();

		bst.populateBSTFromArray( new Integer[] {} );
		bst.printTreeInMatrix();

		maxSumPath = TreeAlgo.getMaxSumPath( bst );

		assertThat( maxSumPath ).isGreaterThan( Integer.MIN_VALUE );
		assertThat( maxSumPath ).isEqualTo( 0 );

		bst.populateBSTFromArray( new Integer[]
		{ -5 - 343, -2, -54, -58, -90, -6 } );
		bst.printTreeInMatrix();

		maxSumPath = TreeAlgo.getMaxSumPath( bst );

		assertThat( maxSumPath ).isGreaterThan( Integer.MIN_VALUE );
		assertThat( maxSumPath ).isEqualTo( -6 );
	}

	// TreeAlgo.compareLeafTraversal
	public static void testCompareLeafTraversal()
	{
		XBinaryTree<Integer> bst1 = new XBinaryTree<>();
		bst1.populateBSTFromArray( new Integer[]
		{ 10, 15, 8, 12, 94, 81, 5, 2, 11 } );
		bst1.printTreeInMatrix();

		XBinaryTree<Integer> bst2 = new XBinaryTree<>();
		bst2.populateBSTFromArray( new Integer[]
		{ 10, 8, 5, 15, 2, 12, 11, 94, 81 } );
		bst2.printTreeInMatrix();

		boolean equal = TreeAlgo.compareLeafTraversalX( bst1, bst2 );

		assertThat( equal ).isTrue();

	}
}
