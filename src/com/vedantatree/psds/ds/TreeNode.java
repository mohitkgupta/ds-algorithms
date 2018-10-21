package com.vedantatree.psds.ds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class TreeNode<E>
{

	private E			data;
	private TreeNode<E>	parent;
	private TreeNode<E>	leftChild;
	private TreeNode<E>	rightChild;

	public TreeNode( TreeNode<E> parent, E data )
	{
		this( parent, data, null, null );
	}

	public TreeNode( TreeNode<E> parent, E data, TreeNode<E> leftChild, TreeNode<E> rightChild )
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

	TreeNode<E> getParent()
	{
		return parent;
	}

	void setParent( TreeNode<E> parent )
	{
		this.parent = parent;
	}

	TreeNode<E> setLeftChild( TreeNode<E> leftChild )
	{
		this.leftChild = leftChild;
		return leftChild;
	}

	TreeNode<E> setRightChild( TreeNode<E> rightChild )
	{
		this.rightChild = rightChild;
		return rightChild;
	}

	E getData()
	{
		return data;
	}

	TreeNode<E> getLeftChild()
	{
		return leftChild;
	}

	TreeNode<E> getRightChild()
	{
		return rightChild;
	}

	// TODO fix it later
	public List<List<TreeNode>> traverseLevels( boolean zigzag )
	{
		// list of all traversed nodes
		List<List<TreeNode>> traversedLevels = new LinkedList();

		// list for each level
		List<TreeNode> individualLevel = null;

		// list of nodes collected as children from previous level, to traverse in next iteration
		LinkedList<TreeNode> childrenCollected = new LinkedList<>();
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
			individualLevel = new ArrayList<TreeNode>( childrenCollected.size() );
			individualLevel.addAll( childrenCollected );
			childrenCollected.clear();

			for( Iterator iterator = individualLevel.iterator(); iterator.hasNext(); )
			{
				TreeNode currentNode = (TreeNode) iterator.next();

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

	private int findMaxValue( TreeNode<E> rootNode )
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
		Queue<TreeNode<Integer>> collectedNodes = new LinkedList<>();
		collectedNodes.add( (TreeNode<Integer>) this );

		int maxValue = Integer.MIN_VALUE;
		while( !collectedNodes.isEmpty() )
		{
			TreeNode<Integer> node = collectedNodes.poll();
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

	public String toString()
	{
		return data + "";
		// return "TreeNode[" + data + "] hashcode[" + hashCode() + "]";
	}

}
