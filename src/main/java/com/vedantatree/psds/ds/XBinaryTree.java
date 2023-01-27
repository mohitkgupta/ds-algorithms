package com.vedantatree.psds.ds;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import com.vedantatree.psds.ds.XBinaryTree.XTreeTraversalResult;


/**
 * 
 * @author Mohit Gupta <mohit.gupta@vedantatree.com>
 */
public class XBinaryTree<E extends Comparable<E>>
{

	private XTreeNode<E> rootNode;

	public XBinaryTree()
	{
	}

	public XBinaryTree( XTreeNode<E> rootNode )
	{
		setRootNode( rootNode );
	}

	public XTreeNode<E> getRootNode()
	{
		return rootNode;
	}

	public void setRootNode( XTreeNode<E> rootNode )
	{
		this.rootNode = rootNode;
	}

	/**
	 * Create this binary tree from values given in array of integer Ensure that
	 * hight should be minimum
	 * 
	 * Algo: Hight can be optimum only if both left and right are properly utilized
	 * which can be done by picking center value of the array and then creating
	 * left, right node from left right subsection of array in similar fashion
	 * 
	 * This will create BST if given array is sorted
	 * 
	 * @param sortedArray
	 */
	public void populateBinaryTreeFromArray( E[] sortedArray )
	{
		setRootNode( populateBinaryTreeFromArray( null, sortedArray, 0, sortedArray.length - 1 ) );
	}

	private XTreeNode populateBinaryTreeFromArray( XTreeNode<E> parentNode, E[] sortedArray, int start, int end )
	{
		if( end < start )
		{
			return null;
		}
		int mid = ( start + end ) / 2;
		XTreeNode rootNode = new XTreeNode( parentNode, sortedArray[mid] );

		rootNode.setLeftChild( populateBinaryTreeFromArray( rootNode, sortedArray, start, mid - 1 ) );
		rootNode.setRightChild( populateBinaryTreeFromArray( rootNode, sortedArray, mid + 1, end ) );

		return rootNode;
	}

	public void populateBSTFromArray( E[] array )
	{
		assertNotNull( array );

		for( E element : array )
		{
			insertBSTNode( element );
		}
	}

	/**
	 * Insert node for given data in sorted order in tree
	 * 
	 * Algo: If given data is smaller than root node > call to add on left side
	 * recursively If given data is bigger than root node > call to add on right
	 * side recursively
	 * 
	 * @param data to insert
	 */
	public void insertBSTNode( E data )
	{
		XTreeNode<E> node = insertBSTNode( getRootNode(), data );
		if( getRootNode() == null )
		{
			setRootNode( node );
		}
	}

	private XTreeNode<E> insertBSTNode( XTreeNode<E> root, E data )
	{
		if( root == null )
		{
			root = new XTreeNode<>( null, data );
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
		return data1.compareTo( data2 );
	}

	/**
	 * search given data in BST and return the node
	 * 
	 * @param data to search
	 * @return matching node
	 */
	public XTreeNode<E> searchNodeInBST( E data )
	{
		return searchNodeInBST( getRootNode(), data );
	}

	private XTreeNode<E> searchNodeInBST( XTreeNode<E> rootNode, E data )
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
			return searchNodeInBST( rootNode.getRightChild(), data );
		}
		else
		{
			return searchNodeInBST( rootNode.getLeftChild(), data );
		}
	}

	/**
	 * Search node in this binary tree (not BST)
	 * 
	 * @param dataToSearch data to search
	 * @return matching node if found
	 */
	public XTreeNode<E> searchNodeInBinaryTree( E dataToSearch )
	{
		return searchNodeInBinaryTree( getRootNode(), dataToSearch );
	}

	private XTreeNode<E> searchNodeInBinaryTree( XTreeNode<E> rootNode, E dataToSearch )
	{
		if( rootNode == null || compareData( rootNode.getData(), dataToSearch ) == 0 )
		{
			return rootNode;
		}

		XTreeNode<E> searchedNode = searchNodeInBinaryTree( rootNode.getLeftChild(), dataToSearch );
		if( searchedNode == null )
		{
			searchedNode = searchNodeInBinaryTree( rootNode.getRightChild(), dataToSearch );
		}

		return searchedNode;
	}

	public XTreeNode<E> getInOrderSuccessor( E nodeData )
	{
		if( nodeData == null )
		{
			return null;
		}

		XTreeNode<E> currentNode = searchNodeInBinaryTree( nodeData );
		if( currentNode == null )
		{
			return null;
		}

		// if right tree exists, find the left most node in right sub-tree
		if( currentNode.getRightChild() != null )
		{
			return getLeftMostChild( currentNode.getRightChild() );
		}

		// else walk through parent hierarchy till current sub-tree become left sub-tree
		// if current sub-tree is already left, then next parent node will be the
		// successor
		// otherwise, keep traversing
		else
		{
			XTreeNode<E> parentNode = currentNode.getParent();

			while( parentNode != null )
			{
				if( parentNode.getLeftChild() == currentNode )
				{
					return parentNode;
				}

				currentNode = parentNode;
				parentNode = parentNode.getParent();
			}
		}
		return null;

	}

	/**
	 * @param node Root node for search
	 * @return left most child of given node
	 */
	public XTreeNode<E> getLeftMostChild( XTreeNode<E> node )
	{
		if( node == null )
		{
			return null;
		}

		while( node.getLeftChild() != null )
		{
			node = node.getLeftChild();
		}

		return node;
	}

	/**
	 * @param node Root node for search
	 * @return right most child of given node
	 */
	public XTreeNode<E> getRightMostChild( XTreeNode<E> node )
	{
		if( node == null )
		{
			return null;
		}

		while( node.getRightChild() != null )
		{
			node = node.getRightChild();
		}

		return node;
	}

	public XTreeNode<E> findCommonAncestorWithoutParentLink( E node1Data, E node2Data )
	{
		assertNotNull( "node 1 data is null", node1Data );
		assertNotNull( "node2 data is null", node2Data );

		XTreeNode<E> node1 = searchNodeInBinaryTree( node1Data );

		// if both data are same, it means both are referring to same node. Return first
		// node found as intersection
		if( node1Data.compareTo( node2Data ) == 0 )
		{
			return node1;
		}

		XTreeNode<E> node2 = searchNodeInBinaryTree( node2Data );
		assertFalse( "No node is found either for node1data or for node2data", ( node1 == null || node2 == null ) );

		return findCommonAncestorWithoutParentLink( getRootNode(), node1, node2 );
	}

	private XTreeNode<E> findCommonAncestorWithoutParentLink( XTreeNode<E> rootNode, XTreeNode<E> node1,
			XTreeNode<E> node2 )
	{
		if( rootNode == null || rootNode == node1 | rootNode == node2 )
		{
			return rootNode;
		}

		boolean isNode1OnLeft = searchNodeInBinaryTree( rootNode.getLeftChild(), node1.getData() ) != null;
		boolean isNode2OnLeft = searchNodeInBinaryTree( rootNode.getLeftChild(), node2.getData() ) != null;

		if( isNode1OnLeft != isNode2OnLeft )
		{
			return rootNode;
		}

		rootNode = isNode1OnLeft ? rootNode.getLeftChild() : rootNode.getRightChild();
		return findCommonAncestorWithoutParentLink( rootNode, node1, node2 );
	}

	public XTreeNode<E> findCommonAncestor( E node1Data, E node2Data )
	{
		assertNotNull( "node 1 data is null", node1Data );
		assertNotNull( "node2 data is null", node2Data );

		XTreeNode<E> node1 = searchNodeInBinaryTree( node1Data );

		// if both data are same, it means both are referring to same node. Return first
		// node found as intersection
		if( node1Data.compareTo( node2Data ) == 0 )
		{
			return node1;
		}

		XTreeNode<E> node2 = searchNodeInBinaryTree( node2Data );

		assertFalse( "No node is found either for node1data or for node2data", ( node1 == null || node2 == null ) );

		/*
		 * get depth of both the nodes get delta For deeper node, go up by delta, so we
		 * have both node at same depth Now start iterating towards parent node and keep
		 * comparing the nodes Return wherever we find match
		 */
		int node1Depth = getDepth( node1 );
		int node2Depth = getDepth( node2 );
		int delta = node1Depth - node2Depth;

		XTreeNode<E> shallowerNode = delta > 0 ? node2 : node1;
		XTreeNode<E> deeperNode = delta > 0 ? node1 : node2;
		deeperNode = goUpBy( deeperNode, Math.abs( delta ) );

		// nodes can be null if these are not related to same tree - a boundary case
		while( shallowerNode != deeperNode && shallowerNode != null && deeperNode != null )
		{
			shallowerNode = shallowerNode.getParent();
			deeperNode = deeperNode.getParent();
		}

		// if any of the node become node, which means these are not related to same
		// tree - return null, which means no
		// intersection
		return shallowerNode == null || deeperNode == null ? null : shallowerNode;
	}

	/*
	 * Go up by delta specified Return value can be null too if specified delta is
	 * more than node's own depth
	 */
	private XTreeNode<E> goUpBy( XTreeNode<E> node, int delta )
	{
		if( node == null || delta <= 0 )
		{
			return node;
		}

		while( node != null && delta > 0 )
		{
			node = node.getParent();
			delta--;
		}
		return node;
	}

	/**
	 * @return list of tree nodes collected in inorder traversal
	 */
	public XTreeTraversalResult<E> inorderTraversal( boolean recursion )
	{
		return inorderTraversalAndValidate( recursion, false );
	}

	public XTreeTraversalResult<E> inorderTraversalAndValidate( boolean recursion, boolean checkBST )
	{
		XTreeTraversalResult<E> traversalResult = new XTreeTraversalResult<>( XTreeTraversalResult.IN_ORDER, checkBST );
		inorderTraversal( getRootNode(), traversalResult, true, checkBST );
		return traversalResult;
	}

	private void inorderTraversal( XTreeNode<E> rootNode, XTreeTraversalResult<E> traversalResult, boolean recursion,
			boolean validateBST )
	{
		if( rootNode != null )
		{
			if( recursion )
			{
				inorderTraversal( rootNode.getLeftChild(), traversalResult, recursion, validateBST );
				traversalResult.addTraversedNodeData( rootNode.getData() );
				inorderTraversal( rootNode.getRightChild(), traversalResult, recursion, validateBST );

			}
			else
			{
				XTreeNode<E> currentNode = rootNode;
				Stack<XTreeNode<E>> nodeStack = new Stack<>();

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

					traversalResult.addTraversedNodeData( currentNode.getData() );

					currentNode = currentNode.getRightChild();
				}
			}
		}

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

	private List<E> preorderTraversal( XTreeNode<E> rootNode, List<E> traversedNodes, boolean recursion )
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
				XTreeNode<E> currentNode = rootNode;
				Stack<XTreeNode> nodeStack = new Stack<>();

				while( currentNode != null )
				{
					// add parent/current node data to traversed
					traversedNodes.add( currentNode.getData() );

					// push current node to stack
					nodeStack.push( currentNode );

					// go to left node for further traversal
					currentNode = currentNode.getLeftChild();

					// will come in action once no more left node left
					// then pop collected left nodes from stack and start traversing their right child tree
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
	 * Pre-order nodes traversal implementation without using recursion
	 * 
	 * @return list of this tree nodes in pre-order
	 */
	public List<E> preorderTraversalWithoutRecursioin()
	{
		XTreeTraversalResult traversalResult = new XTreeTraversalResult( XTreeTraversalResult.PRE_ORDER, false );

		XTreeNode<E> currentNode = getRootNode();

		// stack to collect the nodes to traverse
		Stack<XTreeNode<E>> nodeStack = new Stack<>();
		nodeStack.push( currentNode );

		while( !nodeStack.isEmpty() )
		{
			currentNode = nodeStack.pop();

			traversalResult.addTraversedNodeData( currentNode.getData() );

			if( currentNode.getRightChild() != null )
			{
				nodeStack.push( currentNode.getRightChild() );
			}
			if( currentNode.getLeftChild() != null )
			{
				nodeStack.push( currentNode.getLeftChild() );
			}
		}

		return traversalResult.getTraversedNodesData();
	}

	/**
	 * Post order nodes traversal implementation without using recursion
	 * 
	 * @return list of this tree nodes in post order
	 */
	public List<E> postorderTraversalWithoutRecursioin()
	{
		// store of traversed nodes
		XTreeTraversalResult traversalResult = new XTreeTraversalResult( XTreeTraversalResult.POST_ORDER, false );

		XTreeNode<E> currentNode = getRootNode();

		// stack to collect nodes in traversal order
		Stack<XTreeNode<E>> nodesToTraverseStack = new Stack<>();
		nodesToTraverseStack.push( currentNode );

		// traversed nodes but in reverse order, i.e. collecting last order first
		Stack<XTreeNode<E>> trarversedNodeStack = new Stack<>();

		while( !nodesToTraverseStack.isEmpty() )
		{
			currentNode = nodesToTraverseStack.pop();

			// push to traversed node stack
			trarversedNodeStack.push( currentNode );

			if( currentNode.getLeftChild() != null )
			{
				nodesToTraverseStack.push( currentNode.getLeftChild() );
			}
			if( currentNode.getRightChild() != null )
			{
				nodesToTraverseStack.push( currentNode.getRightChild() );
			}
		}

		//
		while( !trarversedNodeStack.isEmpty() )
		{
			traversalResult.addTraversedNodeData( trarversedNodeStack.pop().getData() );
		}

		return traversalResult.getTraversedNodesData();
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

	private List<E> postorderTraversal( XTreeNode<E> rootNode, List<E> traversedNodes, boolean recursion )
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
				XTreeNode<E> currentNode = rootNode;

				Stack<XTreeNode<E>> nodeStack = new Stack<>();
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

					// if current node has right child, make that current node
					// push it to stack and continue loop with this
					if( currentNode.getRightChild() != null )
					{
						nodeStack.push( currentNode );
						currentNode = currentNode.getRightChild();
					}
					else // otherwise traverse the current node
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
	public List<List<XTreeNode>> traverseLevels( boolean zigzag )
	{
		if( getRootNode() == null )
		{
			return Collections.emptyList();
		}
		return getRootNode().traverseLevels( zigzag );

	}

	/**
	 * Check if this tree is BST or not
	 * 
	 * @return true if BST, false otherwise
	 */
	public boolean isBST()
	{
		return isBST( getRootNode(), null, null );
	}

	/*
	 * Algo: In BST, whole tree on the left should be smaller than root node, and
	 * should be larger than root node on the right This concept applies to each
	 * node in tree While traversing, we can pass root node value down to compare
	 * whether child node is smaller if it is left, or larger if it is right
	 */
	private boolean isBST( XTreeNode<E> rootNode, E minValue, E maxValue )
	{
		if( rootNode == null )
		{
			return true;
		}

		if( ( maxValue != null && rootNode.getData().compareTo( maxValue ) >= 0 )
				|| ( minValue != null && rootNode.getData().compareTo( minValue ) < 0 ) )
		{
			return false;
		}

		if( !isBST( rootNode.getLeftChild(), null, rootNode.getData() )
				|| !isBST( rootNode.getRightChild(), rootNode.getData(), null ) )
		{
			return false;
		}

		return true;
	}

	/**
	 * @param tree Tree to compare with this tree
	 * @return true if specific true has similar data and structure as of this tree
	 */
	public boolean isEqualTree( XBinaryTree tree )
	{
		if( tree == null )
		{
			return false;
		}
		return isEqualNode( getRootNode(), tree.getRootNode() );

	}

	private boolean isEqualNode( XTreeNode<E> treeNode1, XTreeNode<E> treeNode2 )
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

	public int findMaximumValue( boolean recursion )
	{
		return recursion ? getRootNode().findMaxValue() : getRootNode().findMaxValueWithoutRecursion();
	}

	public int getHeight()
	{
		return getHeight( getRootNode() );
	}

	private int getHeight( XTreeNode<E> root )
	{
		if( root == null )
		{
			return 0;
		}
		return 1 + Math.max( getHeight( root.getLeftChild() ), getHeight( root.getRightChild() ) );
	}

	private int getDepth( XTreeNode<E> node )
	{
		assertNotNull( node );

		int depth = 0;
		while( node != null )
		{
			depth++;
			node = node.getParent();
		}
		return depth;
	}

	/**
	 * Check and return the height
	 * Also check if tree is balanced or not
	 * 
	 * When is a BST balanced
	 * when both left and right node should not have height difference of more than one
	 * Check this recursively
	 * If difference is more,
	 * return error code
	 * 
	 * @return Integer.MIN_VALUE as error code if tree is not balance, tree height
	 *         otherwise
	 */
	public int checkHeightAndBalanced()
	{
		return checkHeightAndBalanced( getRootNode() );
	}

	public int checkHeightAndBalanced( XTreeNode<E> root )
	{
		if( root == null )
		{
			return -1;
		}

		int leftHeight = checkHeightAndBalanced( root.getLeftChild() );
		if( leftHeight == Integer.MIN_VALUE )
		{
			return leftHeight;
		}

		int rightHeight = checkHeightAndBalanced( root.getRightChild() );
		// TODO why is this MAX_VALUE. It is not set anywhere Should be min_value
		if( rightHeight == Integer.MAX_VALUE )
		{
			return rightHeight;
		}

		int heightDiff = leftHeight - rightHeight;
		if( Math.abs( heightDiff ) > 1 )
		{
			return Integer.MIN_VALUE;
		}
		else
		{
			return Math.max( leftHeight, rightHeight ) + 1;
		}
	}

	// TODO: pending
	public int getWidth()
	{
		int leftWidth = 0;
		int rigthWidth = 0;

		return 0;
	}

	/**
	 * In given tree (root node), find node which has value closest to the given
	 * target value
	 * 
	 * @param rootNode Tree root node
	 * @param targetValue the target value whose closest node this method needs to
	 *        find
	 * @return closest node to given target value
	 */
	public int findNearest( XTreeNode<Integer> rootNode, Integer targetValue )
	{
		return findNearest( rootNode, targetValue, Integer.MAX_VALUE );
	}

	private Integer findNearest( XTreeNode<Integer> currentNode, Integer targetValue, Integer closest )
	{
		if( currentNode == null )
		{
			return closest;
		}
		if( currentNode.getData() == targetValue )
		{
			return currentNode.getData();
		}

		if( Math.abs( closest - targetValue ) > Math.abs( currentNode.getData() - targetValue ) )
		{
			closest = currentNode.getData();
		}

		if( targetValue < currentNode.getData() )
		{
			return findNearest( currentNode.getLeftChild(), targetValue, closest );
		}
		else
		{
			return findNearest( currentNode.getRightChild(), targetValue, closest );
		}

	}

	/**
	 * Print tree in Matrix form Most readable form
	 * 
	 * However, alignment gets disturbed as numbers get bigger
	 * We can fix it by filling max space in empty place, and equal width for number, but that will
	 * make the logic bit complex. Hence leaving for now
	 */
	public void printTreeInMatrix()
	{
		XTreeNode<E> root = getRootNode();
		if (root == null) return;
		
		int height = getHeight( root );
		if (height == 0) return;

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

	private void fillMatrix( String[][] resultMatrix, XTreeNode rootNode, int row, int columnStartIndex,
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
		List<List<XTreeNode>> levels = traverseLevels( false );

		if( levels == null || levels.isEmpty() )
		{
			System.out.println( "no root node found" );
			return;
		}

		for( List<XTreeNode> level : levels )
		{
			for( XTreeNode node : level )
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

		XTreeNode<E> rootNode = getRootNode();
		printTree( rootNode, dataToPrint, 0 );

		System.out.println( dataToPrint );
	}

	private void printTree( XTreeNode<E> node, StringBuffer dataToPrint, int direction )
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

	private void printNode( XTreeNode<E> node, StringBuffer dataToPrint, int direction )
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

	/**
	 * Structure to contain result of tree traversal and more information like BST
	 * check if applicable
	 * 
	 * TODO: In use for InOrder traversal only as of now, we can expand to others
	 * 
	 * @param <E>
	 */
	public static class XTreeTraversalResult<E extends Comparable>
	{

		public static final int	PRE_ORDER			= -1;
		public static final int	IN_ORDER			= 0;
		public static final int	POST_ORDER			= 1;

		private List<E>			traversedNodesData	= new ArrayList<>();

		private boolean			isBST				= true;
		private boolean			validateBST;
		private int				traversalOrder;

		// temp variable to store previous traversed data
		private E				previousData;

		public XTreeTraversalResult( int traversalOrder, boolean validateBST )
		{
			this.traversalOrder = traversalOrder;
			this.validateBST = validateBST;
		}

		public void addTraversedNodeData( E nodeData )
		{
			traversedNodesData.add( nodeData );
			if( validateBST )
			{
				checkBST( nodeData, traversalOrder );
				setPreviousData( nodeData );
			}
		}

		private void checkBST( E currentNodeData, int traversalOrder )
		{
			if( previousData != null )
			{
				switch( traversalOrder )
				{
					// case PRE_ORDER:
					// break;
					case IN_ORDER:
						if( previousData.compareTo( currentNodeData ) >= 0 )
						{
							setBST( false );
						}
						break;
					// case POST_ORDER:
					// break;
					default:
						throw new UnsupportedOperationException( "Specified traversal order is not supported!" );
				}
			}
		}

		List<E> getTraversedNodesData()
		{
			return traversedNodesData;
		}

		private void setBST( boolean isBST )
		{
			this.isBST = isBST;
		}

		boolean isBST()
		{
			return isBST;
		}

		private void setPreviousData( E previousData )
		{
			this.previousData = previousData;
		}

	}

}