package com.vedantatree.psds.ds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * 
 * @author Mohit Gupta <mohit.gupta@vedantatree.com>
 */
public class XTreeNode<E>
{

	private E				data;
	private XTreeNode<E>	parent;
	private XTreeNode<E>	leftChild;
	private XTreeNode<E>	rightChild;

	public XTreeNode( XTreeNode<E> parent, E data )
	{
		this( parent, data, null, null );
	}

	public XTreeNode( XTreeNode<E> parent, E data, XTreeNode<E> leftChild, XTreeNode<E> rightChild )
	{
		setData( data );
		setParent( parent );
		setLeftChild( leftChild );
		setRightChild( rightChild );
	}

	void setData( E data )
	{
		this.data = data;
	}

	XTreeNode<E> getParent()
	{
		return parent;
	}

	void setParent( XTreeNode<E> parent )
	{
		this.parent = parent;
	}

	XTreeNode<E> setLeftChild( XTreeNode<E> leftChild )
	{
		this.leftChild = leftChild;
		return leftChild;
	}

	XTreeNode<E> setRightChild( XTreeNode<E> rightChild )
	{
		this.rightChild = rightChild;
		return rightChild;
	}

	E getData()
	{
		return data;
	}

	XTreeNode<E> getLeftChild()
	{
		return leftChild;
	}

	XTreeNode<E> getRightChild()
	{
		return rightChild;
	}

	// TODO fix it later
	public List<List<XTreeNode>> traverseLevels( boolean zigzag )
	{
		// list of all traversed nodes
		List<List<XTreeNode>> traversedLevels = new LinkedList();

		// list for each level
		List<XTreeNode> individualLevel = null;

		// list of nodes collected as children from previous level, to traverse in next iteration
		LinkedList<XTreeNode> childrenCollected = new LinkedList<>();
		childrenCollected.add( this );

		boolean leftToRight = true;
		boolean hasNextLevel = !childrenCollected.isEmpty();

		while( hasNextLevel )
		{

			if( zigzag )
			{
				leftToRight = !leftToRight;
			}

			if( !leftToRight )
			{
				Collections.reverse( childrenCollected );
			}

			// list for each level
			individualLevel = new ArrayList<>( childrenCollected.size() );
			individualLevel.addAll( childrenCollected );
			childrenCollected.clear();

			for( Object element : individualLevel )
			{
				XTreeNode currentNode = (XTreeNode) element;

				// collect children for next level traversal
				if( currentNode.getLeftChild() != null )
				{
					childrenCollected.add( currentNode.getLeftChild() );
				}
				if( currentNode.getRightChild() != null )
				{
					childrenCollected.add( currentNode.getRightChild() );
				}
			}

			traversedLevels.add( individualLevel );

			hasNextLevel = !childrenCollected.isEmpty();
		}

		return traversedLevels;
	}

	public int findMaxValue()
	{
		return findMaxValue( this );
	}

	private int findMaxValue( XTreeNode<E> rootNode )
	{
		if( rootNode == null )
		{
			return Integer.MIN_VALUE;
		}

		int rootValue = (Integer) rootNode.getData();
		int maxValue = rootValue;

		int leftValue = findMaxValue( rootNode.getLeftChild() );
		int rightValue = findMaxValue( rootNode.getRightChild() );

		if( leftValue > rootValue )
		{
			maxValue = leftValue;
		}
		if( rightValue > maxValue )
		{
			maxValue = rightValue;
		}
		return maxValue;
	}

	public int findMaxValueWithoutRecursion()
	{
		Queue<XTreeNode<Integer>> collectedNodes = new LinkedList<>();
		collectedNodes.add( (XTreeNode<Integer>) this );

		int maxValue = Integer.MIN_VALUE;
		while( !collectedNodes.isEmpty() )
		{
			XTreeNode<Integer> node = collectedNodes.poll();
			maxValue = node.getData() > maxValue ? node.getData() : maxValue;

			if( node.getLeftChild() != null )
			{
				collectedNodes.add( node.getLeftChild() );
			}
			if( node.getRightChild() != null )
			{
				collectedNodes.add( node.getRightChild() );
			}
		}
		return maxValue;
	}

	@Override
	public String toString()
	{
		return "this[" + data + "] left[" + leftChild + "] right[" + rightChild + "]";
		// return "TreeNode[" + data + "] hashcode[" + hashCode() + "]";
	}

}
