package com.vedantatree.psds.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


public class BinaryTree<E>
{

	private TreeNode<E> rootNode;

	public BinaryTree()
	{
	}

	public BinaryTree( TreeNode<E> rootNode )
	{
		setRootNode( rootNode );
	}

	public TreeNode<E> getRootNode()
	{
		return rootNode;
	}

	public void setRootNode( TreeNode<E> rootNode )
	{
		this.rootNode = rootNode;
	}

	/**
	 * insert node for given data in sorted order in tree
	 * 
	 * @param data to insert
	 */
	public void insertBSTNode( E data )
	{
		TreeNode<E> node = insertBSTNode( getRootNode(), data );
		if( getRootNode() == null )
		{
			setRootNode( node );
		}
	}

	private TreeNode<E> insertBSTNode( TreeNode<E> root, E data )
	{
		if( root == null )
		{
			root = new TreeNode<E>( null, data );
			return root;
		}

		if( compareData( data, root.getData() ) < 0 )
		{
			root.setLeftChild( insertBSTNode( root.getLeftChild(), data ) );
		}
		else
		{
			root.setRightChild( insertBSTNode( root.getRightChild(), data ) );
		}
		return root;
	}

	private int compareData( E data1, E data2 )
	{
		Integer intData1 = (Integer) data1;
		Integer intData2 = (Integer) data2;
		if( intData1 == intData2 )
		{
			return 0;
		}
		else if( intData1 < intData2 )
		{
			return -1;
		}
		else
		{
			return 1;
		}
	}

	/**
	 * search given data in the tree and return the node
	 * 
	 * @param data to search
	 * @return matching node
	 */
	public TreeNode<E> searchInTree( E data )
	{
		return searchNode( getRootNode(), data );
	}

	private TreeNode<E> searchNode( TreeNode<E> rootNode, E data )
	{
		if( rootNode == null )
		{
			return null;
		}
		int comparisonResult = compareData( rootNode.getData(), data );
		if( comparisonResult == 0 )
		{
			return rootNode;
		}
		else if( comparisonResult < 0 )
		{
			return searchNode( rootNode.getRightChild(), data );
		}
		else
		{
			return searchNode( rootNode.getLeftChild(), data );
		}

	}

	/**
	 * @return list of tree nodes collected in inorder traversal
	 */
	public List<E> inorderTraversal( boolean recursion )
	{
		List<E> traversedNodes = new ArrayList<>();
		inorderTraversal( getRootNode(), traversedNodes, true );
		return traversedNodes;
	}

	private List<E> inorderTraversal( TreeNode<E> rootNode, List<E> traversedNodes, boolean recursion )
	{
		if( rootNode != null )
		{
			if( recursion )
			{
				inorderTraversal( rootNode.getLeftChild(), traversedNodes, recursion );
				traversedNodes.add( rootNode.getData() );
				inorderTraversal( rootNode.getRightChild(), traversedNodes, recursion );
			}
			else
			{
				TreeNode<E> currentNode = rootNode;
				Stack<TreeNode> nodeStack = new Stack<>();

				while( currentNode != null || !nodeStack.isEmpty() )
				{

					// collecting all left node in stack and reaching to left most in current path
					while( currentNode != null )
					{
						nodeStack.push( currentNode );
						currentNode = currentNode.getLeftChild();
					}

					// get top node, add its data, try checking right node. If present, will add its
					// data or will get next left node from stack in next loop
					currentNode = nodeStack.pop();
					traversedNodes.add( currentNode.getData() );
					currentNode = currentNode.getRightChild();
				}
			}
		}

		return traversedNodes;
	}

	/**
	 * @return list of tree nodes collected in preorder traversal
	 */
	public List<E> preorderTraversal( boolean recursion )
	{
		List<E> traversedNodes = new ArrayList<>();
		preorderTraversal( getRootNode(), traversedNodes, recursion );
		return traversedNodes;
	}

	private List<E> preorderTraversal( TreeNode<E> rootNode, List<E> traversedNodes, boolean recursion )
	{
		if( rootNode != null )
		{
			if( recursion )
			{
				traversedNodes.add( rootNode.getData() );
				preorderTraversal( rootNode.getLeftChild(), traversedNodes, recursion );
				preorderTraversal( rootNode.getRightChild(), traversedNodes, recursion );
			}
			else
			{
				TreeNode<E> currentNode = rootNode;
				Stack<TreeNode> nodeStack = new Stack<>();

				while( currentNode != null )
				{
					traversedNodes.add( currentNode.getData() );
					nodeStack.push( currentNode );
					currentNode = currentNode.getLeftChild();
					while( currentNode == null && !nodeStack.empty() )
					{
						currentNode = nodeStack.pop().getRightChild();
					}
				}
			}
		}

		return traversedNodes;
	}

	/**
	 * @return list of tree nodes collected in postorder traversal
	 */
	public List<E> postorderTraversal( boolean recursion )
	{
		List<E> traversedNodes = new ArrayList<>();
		postorderTraversal( getRootNode(), traversedNodes, recursion );
		return traversedNodes;
	}

	private List<E> postorderTraversal( TreeNode<E> rootNode, List<E> traversedNodes, boolean recursion )
	{
		if( rootNode != null )
		{
			if( recursion )
			{
				postorderTraversal( rootNode.getLeftChild(), traversedNodes, recursion );
				postorderTraversal( rootNode.getRightChild(), traversedNodes, recursion );
				traversedNodes.add( rootNode.getData() );
			}
			else
			{
				TreeNode<E> currentNode = rootNode;

				Stack<TreeNode> nodeStack = new Stack<>();
				nodeStack.push( currentNode );

				while( currentNode != null )
				{
					// fill all left child to stack
					while( true )
					{
						currentNode = currentNode.getLeftChild();
						if( currentNode == null )
						{
							break;
						}
						nodeStack.push( currentNode );
					}

					// get left most node
					currentNode = nodeStack.pop();

					// if left most node has right child, then it becomes a center node
					if( currentNode.getRightChild() != null )
					{
						nodeStack.push( currentNode );
						currentNode = currentNode.getRightChild();
					}
					else
					{
						traversedNodes.add( currentNode.getData() );
					}
				}
			}
		}
		return traversedNodes;
	}

	/**
	 * Traverse tree level wise
	 * 
	 * @return list of nodes structured by each level
	 */
	public List<List<TreeNode>> traverseLevels( boolean zigzag )
	{
		if( getRootNode() == null )
		{
			return Collections.emptyList();
		}
		return getRootNode().traverseLevels( zigzag );

	}

	/**
	 * @param tree Tree to compare with this tree
	 * @return true if specific true has similar data and structure as of this tree
	 */
	public boolean isEqualTree( BinaryTree tree )
	{
		if( tree == null )
		{
			return false;
		}
		return isEqualNode( getRootNode(), tree.getRootNode() );

	}

	private boolean isEqualNode( TreeNode<E> treeNode1, TreeNode<E> treeNode2 )
	{
		if( treeNode1 != null && treeNode2 != null )
		{
			if( !isEqualNode( treeNode1.getLeftChild(), treeNode2.getLeftChild() ) )
			{
				return false;
			}
			if( !isEqualNode( treeNode1.getRightChild(), treeNode2.getRightChild() ) )
			{
				return false;
			}
		}

		E node1Data = treeNode1 == null ? null : treeNode1.getData();
		E node2Data = treeNode2 == null ? null : treeNode2.getData();

		if( node1Data == node2Data )
		{
			return true;
		}
		if( node1Data != null && node1Data.equals( node2Data ) )
		{
			return true;
		}

		return false;
	}

	public int findMaximumValue(boolean recursion)
	{
		return recursion ? getRootNode().findMaxValue() : getRootNode().findMaxValueWithoutRecursion();
	}

	public int getHeight()
	{
		return getHeight( getRootNode() );
	}

	private int getHeight( TreeNode<E> root )
	{
		if( root == null )
		{
			return 0;
		}
		return 1 + Math.max( getHeight( root.getLeftChild() ), getHeight( root.getRightChild() ) );
	}

	// TODO
	public int getWidth()
	{
		int leftWidth = 0;
		int rigthWidth = 0;

		return 0;
	}

	/**
	 * Print tree in Matrix form Most readable form However, alignment gets
	 * disturbed as numbers get bigger We can fix it by filling max space in empty
	 * place, and equal width for number, but that will make the logic bit complex.
	 * Hence leaving for now
	 */
	public void printTreeInMatrix()
	{
		TreeNode<E> root = getRootNode();
		int height = getHeight( root );

		String[][] resultMatrix = new String[height][( 1 << height ) - 1];
		System.out.println( "width of matrix[" + ( 1 << height ) + "]" );

		for( String[] rowArray : resultMatrix )
		{
			Arrays.fill( rowArray, "  " );
		}

		fillMatrix( resultMatrix, root, 0, 0, resultMatrix[0].length );

		for( String[] rowArray : resultMatrix )
		{
			System.out.println( Arrays.asList( rowArray ) );
		}
	}

	private void fillMatrix( String[][] resultMatrix, TreeNode rootNode, int row, int columnStartIndex,
			int columnEndIndex )
	{
		if( rootNode == null )
		{
			return;
		}
		resultMatrix[row][( columnStartIndex + columnEndIndex ) / 2] = "" + rootNode.getData();

		fillMatrix( resultMatrix, rootNode.getLeftChild(), row + 1, columnStartIndex,
				( columnStartIndex + columnEndIndex ) / 2 );

		fillMatrix( resultMatrix, rootNode.getRightChild(), row + 1, ( columnStartIndex + columnEndIndex + 1 ) / 2,
				columnEndIndex );
	}

	/**
	 * Print tree level wise i.e. print each level in one row
	 */
	public void printLevelWise()
	{
		List<List<TreeNode>> levels = traverseLevels( false );

		if( levels == null || levels.isEmpty() )
		{
			System.out.println( "no root node found" );
			return;
		}

		for( List<TreeNode> level : levels )
		{
			for( TreeNode node : level )
			{
				System.out.print( node.getData() + " " );
			}
			System.out.println();
		}
	}

	/**
	 * Print Tree Show parent > Left > Right and so on for each parent child
	 * combination Not very readable format
	 */
	public void printTree()
	{
		StringBuffer dataToPrint = new StringBuffer();

		TreeNode<E> rootNode = getRootNode();
		printTree( rootNode, dataToPrint, 0 );

		System.out.println( dataToPrint );
	}

	private void printTree( TreeNode<E> node, StringBuffer dataToPrint, int direction )
	{
		if( node == null )
		{
			return;
		}
		else
		{

			dataToPrint.append( direction == 0 ? "center" : ( direction == -1 ? "left" : "right" ) );
			dataToPrint.append( " > " );

			printNode( node, dataToPrint, 0 );
			printNode( node.getLeftChild(), dataToPrint, -1 );
			printNode( node.getRightChild(), dataToPrint, 1 );

			dataToPrint.append( "\n" );

			printTree( node.getLeftChild(), dataToPrint, -1 );
			printTree( node.getRightChild(), dataToPrint, 1 );
		}
	}

	private void printNode( TreeNode<E> node, StringBuffer dataToPrint, int direction )
	{
		if( node == null )
		{
			return;
		}

		if( direction == 0 )
		{
			dataToPrint.append( "parent" );
			dataToPrint.append( "[" + node.getData() + "]" );
		}
		else
		{
			dataToPrint.append( direction == -1 ? "left" : "right" );
			dataToPrint.append( "[" + node.getData() + "]" );
		}

	}

}