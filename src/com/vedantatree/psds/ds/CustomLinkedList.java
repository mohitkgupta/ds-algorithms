package com.vedantatree.psds.ds;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;


// linkedList - can act as single and double both - but that made it complex. Ok for practice only
// moved setting of next and previous node logic to Node class itself, to handle single/double linkedlist in same class - not a good impl

public class CustomLinkedList<E>
{

	private LinkedListNode<E>	headNode;
	private LinkedListNode<E>	lastNode;
	private int					size;
	private boolean				single	= false;

	public CustomLinkedList()
	{

	}

	public CustomLinkedList( boolean single )
	{
		this.single = single;
	}

	private void assertIndexInBound( int index )
	{
		if( index < 0 || index > size - 1 )
		{
			throw new IndexOutOfBoundsException(
					"Given index is out of bound for list. index[" + index + "] current-size[" + size + "]" );
		}
	}

	/**
	 * method to assert sanity of list, check size and calculated size based on
	 * forward and backward navigation (for double)
	 */
	public void assertSanity()
	{
		int forwardCount = 0;
		LinkedListNode<E> node = headNode;
		while( node != null )
		{
			forwardCount++;
			node = node.getNext();
		}
		if( forwardCount != size )
		{
			printAllElements();
			throw new IllegalStateException(
					"Forward count is not matching. ForwardCount[" + forwardCount + "] size[" + size + "]" );
		}
		else
		{
			System.out.println( "Forward count matches" );
		}

		if( !single )
		{
			int backwardCount = 0;
			node = lastNode;
			while( node != null )
			{
				backwardCount++;
				node = node.getPrevioius();
			}
			if( backwardCount != size )
			{
				printAllElements();
				printAllElementsBackwards();
				throw new IllegalStateException(
						"Backward count is not matching. Backward Count[" + backwardCount + "] size[" + size + "]" );
			}
			else
			{
				System.out.println( "Backward count matches" );
			}
		}

	}

	public void addAll( Collection<E> listOfElements )
	{
		if( listOfElements == null )
		{
			return;
		}
		for( Iterator<E> iterator = listOfElements.iterator(); iterator.hasNext(); )
		{
			E e = (E) iterator.next();
			addElement( e );
		}
	}

	public int getSize()
	{
		return size;
	}

	public CustomLinkedList<E> addElement( E newElement )
	{
		return addLast( newElement );
	}

	public boolean containsElement( E elementToSearch )
	{
		LinkedListNode<E> node = getNode( elementToSearch );
		return node != null;
	}

	public void printAllElements()
	{
		StringBuffer toPrint = new StringBuffer();
		if( headNode == null )
		{
			toPrint.append( "null" );
		}
		else
		{
			LinkedListNode<E> node = headNode;
			HashSet<LinkedListNode<E>> traversedNodes = new HashSet<>();

			while( node != null )
			{
				toPrint.append( node.getData() );
				toPrint.append( ", " );

				traversedNodes.add( node );

				node = node.getNext();
				if( traversedNodes.contains( node ) )
				{
					if( !circular )
					{
						throw new IllegalStateException( "Node can not be found again if list is not circular" );
					}
					toPrint.append( "Circular Found" );
					break;
				}
			}
		}
		System.out.println( toPrint );
	}

	public void printAllElementsBackwards()
	{
		StringBuffer toPrint = new StringBuffer();
		if( lastNode == null )
		{
			toPrint.append( "null" );
		}
		else
		{
			LinkedListNode<E> node = lastNode;
			while( node != null )
			{
				toPrint.append( node.getData() );
				toPrint.append( ", " );

				if( !single )
				{
					node = node.getPrevioius();
				}
				else
				{
					node = getLaggerNode( node, 1 );
				}
			}
		}
		System.out.println( toPrint );
	}

	public CustomLinkedList<E> addLast( E newElement )
	{
		LinkedListNode<E> newNode = new LinkedListNode<E>( lastNode, newElement, null, single );
		if( lastNode != null )
		{
			lastNode.setNext( newNode );
		}
		else
		{
			headNode = newNode;
		}
		lastNode = newNode;
		size++;

		return this;
	}

	public void addFirst( E newElement )
	{
		LinkedListNode<E> newNode = new LinkedListNode<E>( null, newElement, headNode, single );
		if( headNode != null )
		{
			newNode.setNext( headNode );
		}
		else
		{
			lastNode = newNode;
		}
		headNode = newNode;
		size++;
	}

	public void insertElement( int index, E newElement )
	{
		if( index == 0 )
		{
			addFirst( newElement );
			// commenting as even if it is last index, new element should be inserted before
			// last element
			// } else if (index == size - 1) {
			// addLast(newElement);
		}
		else
		{
			LinkedListNode<E> previousNode = getNode( index - 1 );
			LinkedListNode<E> newNode = new LinkedListNode<E>( previousNode, newElement, previousNode.getNext(),
					single );
			size++;
		}
		assertSanity();
	}

	public E getElement( int index )
	{
		return getNode( index ).getData();
	}

	private LinkedListNode<E> getNode( int index )
	{
		if( index < 0 || index > size - 1 )
		{
			throw new IndexOutOfBoundsException( "Given index[" + index + "] size[" + size + "]" );
		}

		LinkedListNode<E> node = headNode;
		int counter = 0;
		while( node != null )
		{
			if( counter == index )
			{
				return node;
			}
			counter++;
			node = node.getNext();
		}
		return null;
	}

	private LinkedListNode<E> getNode( E elementToSearch )
	{
		if( headNode == null )
		{
			return null;
		}
		LinkedListNode<E> node = headNode;
		while( node != null )
		{
			if( elementToSearch == null && node.getData() == null )
			{
				return node;
			}
			else if( elementToSearch.equals( node.getData() ) )
			{
				return node;
			}
			node = node.getNext();
		}
		return null;
	}

	public boolean removeElement( E elementToRemove )
	{
		try
		{
			LinkedListNode<E> nodeToRemove = getNode( elementToRemove );
			if( nodeToRemove == null )
			{
				return false;
			}

			// removing first node
			if( nodeToRemove == headNode )
			{
				// if there is only one node in list
				if( lastNode == headNode )
				{
					lastNode = null;
					headNode = null;
				}
				else
				{
					headNode = headNode.getNext();
				}
				size--;
				return true;
			}

			LinkedListNode<E> nextNode = nodeToRemove.getNext();

			// if single linked list

			if( single )
			{

				// if it is not last node
				if( nextNode != null )
				{
					E nextNodeData = nextNode.getData();
					nodeToRemove.setData( nextNodeData );
					nodeToRemove.setNext( nextNode.getNext() );

					// if next node was the last node, move the pointer
					if( nextNode.getNext() == null )
					{
						lastNode = nodeToRemove;
					}
				}
				else
				{
					LinkedListNode<E> previousNode = getLaggerNode( nodeToRemove, 1 );
					previousNode.setNext( null );

					lastNode = previousNode;
				}
				size--;
				return true;
			}

			// go ahead if double

			LinkedListNode<E> previousNode = nodeToRemove.getPrevioius();

			if( previousNode != null )
			{
				previousNode.setNext( nextNode );
			}
			else
			{
				headNode = nextNode;
			}

			if( nextNode != null )
			{
				nextNode.setPrevious( previousNode );
			}
			else
			{
				lastNode = previousNode;
			}

			size--;
			return true;
		}
		finally
		{
			assertSanity();
		}

	}

	// return data for node lagging by given place, from given node data. For single
	// linked list
	public E getLaggerNodeData( E elementToSearch, int place )
	{
		LinkedListNode<E> laggerNode = getLaggerNode( getNode( elementToSearch ), place );
		return laggerNode == null ? null : laggerNode.getData();
	}

	private LinkedListNode<E> getLaggerNode( LinkedListNode<E> nodeToSearch, int place )
	{
		if( headNode == null || nodeToSearch == null )
		{
			return null;
		}
		LinkedListNode<E> currentNode = headNode;
		LinkedListNode<E> laggerNode = null;

		int laggingPointer = 0;

		while( currentNode != null )
		{

			if( laggingPointer == place )
			{
				laggerNode = headNode;
			}
			if( laggingPointer > place )
			{
				laggerNode = laggerNode.getNext();
			}

			if( nodeToSearch == currentNode )
			{
				return laggerNode;
			}

			laggingPointer = laggingPointer + 1;
			currentNode = currentNode.getNext();
		}

		return null;
	}

	// ------------------------------- Circular List ---------------------

	private boolean circular = false;

	/**
	 * Make this list circular at given index
	 * 
	 * if index == 0 > connect last node with head node if index == size - 1 >
	 * nothing to do if index > 0 > connect last node with node at given index
	 * 
	 * @param index - index of the node to connect last node with
	 */
	public void makeCircularAt( int index )
	{
		assertIndexInBound( index );

		LinkedListNode<E> nodeAtIndex = getNode( index );
		lastNode.setNext( nodeAtIndex );

		circular = true;
	}

	/**
	 * Calculate the size of circular list
	 * 
	 * It uses Hashset internally to keep track of traversed nodes
	 * 
	 * @return size
	 */
	public int calculateSizeOfCircularList()
	{
		if( !circular )
		{
			throw new IllegalStateException( "Not a circular list" );
		}
		LinkedListNode<E> node = headNode;
		HashSet<LinkedListNode<E>> traversedNodes = new HashSet<>();

		int size = 0;
		while( node != null && !traversedNodes.contains( node ) )
		{
			size++;
			traversedNodes.add( node );
			node = node.getNext();
		}

		return size;
	}

	private static class LinkedListNode<E>
	{

		private boolean				single	= false;
		private E					data;
		private LinkedListNode<E>	next;
		private LinkedListNode<E>	previous;

		LinkedListNode( LinkedListNode<E> previous, E data, LinkedListNode<E> next, boolean single )
		{
			this.single = single;
			this.data = data;
			setNext( next );

			if( !single )
			{
				setPrevious( previous );
			}
			else
			{
				if( previous != null )
				{
					previous.setNext( this );
				}

			}
		}

		public E getData()
		{
			return data;
		}

		public void setData( E data )
		{
			this.data = data;
		}

		public LinkedListNode getNext()
		{
			return next;
		}

		public LinkedListNode setNext( LinkedListNode next )
		{
			LinkedListNode currentNextNode = getNext();
			this.next = next;
			if( next != null && !single )
			{
				next.setPrevious( this );
			}
			return currentNextNode;
		}

		public LinkedListNode getPrevioius()
		{
			return previous;
		}

		public LinkedListNode setPrevious( LinkedListNode previous )
		{
			if( single )
			{
				throw new IllegalStateException( "Previous can be not set on Single linked list" );
			}
			LinkedListNode currentPreviousNode = getPrevioius();
			this.previous = previous;

			// to avoid loop
			if( previous != null && previous.getNext() != this )
			{
				previous.setNext( this );
			}
			return currentPreviousNode;
		}

	}

}
