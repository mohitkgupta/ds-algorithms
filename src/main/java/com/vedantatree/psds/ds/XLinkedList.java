package com.vedantatree.psds.ds;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;


/**
 * Custom implementation for LinkedList. It supports single, double or circular linked list.
 * Default > Single LinkedList
 * 
 * Good - This can act as single or double linkedlist.
 * Not so good - This generic implementation made it complex also, so just for practice.
 * Not so good - Moved setting of next and previous node logic to Node class, to handle single/double linkedlist
 * in same class
 * Not concurrent modification safe
 * 
 * TODO
 * - Compare function for given headnode
 * 
 * @author Mohit Gupta <mohit.gupta@vedantatree.com>
 */
public class XLinkedList<E> {

	private XLinkedListNode<E>	headNode;
	private XLinkedListNode<E>	lastNode;
	private int					size;
	private boolean				single	= true;

	public XLinkedList() {
	}

	public XLinkedList( boolean single ) {
		this.single = single;
	}

	public boolean isSingle() {
		return single;
	}

	public boolean isDouble() {
		return !isSingle();
	}

	private void assertIndexInBound( int index ) {
		if( index < 0 || index > size - 1 ) {
			throw new IndexOutOfBoundsException(
					"Given index is out of bound for list. index[" + index + "] current-size[" + size + "]" );
		}
	}

	/**
	 * method to assert sanity of list,
	 * - check size
	 * - and calculated size based on forward
	 * - - and backward navigation (for double)
	 */
	public void assertSanityOfSize() {

		XLinkedListNode<E> node = headNode;
		int forwardCount = 0;

		while( node != null ) {
			forwardCount++;
			node = node.getNext();
		} // forward count calculated

		if( forwardCount != size ) {
			printAllElements();
			throw new IllegalStateException(
					"Forward count is not matching. ForwardCount[" + forwardCount + "] size[" + size + "]" );
		}

		if( isDouble() ) {
			node = lastNode;

			int backwardCount = 0;
			while( node != null ) {
				backwardCount++;
				node = node.getPrevioius();
			} // backward count calculated

			if( backwardCount != size ) {
				printAllElements();
				printAllElementsBackwards();
				throw new IllegalStateException(
						"Backward count is not matching. Backward Count[" + backwardCount + "] size[" + size + "]" );
			}
		}

	}

	public void assertSanityForCircular() {
		HashSet<XLinkedListNode<E>> traversedNodes = new HashSet<>();

		XLinkedListNode<E> node = headNode;

		while( node != null ) {
			traversedNodes.add( node );
			node = node.getNext();

			if( traversedNodes.contains( node ) ) {
				if( !isCircular() ) {
					throw new IllegalStateException( "Nodes can not connect in circle, if list is not circular" );
				}
				break;
			}
		}
	}

	public int getSize() {
		return size;
	}

	private void refreshSize() {
		int refreshedSize = 0;

		if( circular ) {
			refreshedSize = calculateSizeOfCircularList();
		}
		else {

			XLinkedListNode<E> currentNode = headNode;

			while( currentNode != null ) {
				refreshedSize++;
				currentNode = currentNode.getNext();
			}
		}

		size = refreshedSize;

		// validate if all good
		assertSanityOfSize();
	}

	public boolean containsElement( E elementToSearch ) {
		XLinkedListNode<E> node = getNode( elementToSearch );
		return node != null;
	}

	public E getElement( int index ) {
		return getNode( index ).getData();
	}

	private XLinkedListNode<E> getNode( int index ) {
		if( index < 0 || index > size - 1 ) {
			throw new IndexOutOfBoundsException( "Given index[" + index + "] size[" + size + "]" );
		}

		XLinkedListNode<E> node = headNode;
		int counter = 0;
		while( node != null ) {
			if( counter == index ) {
				return node;
			}
			counter++;
			node = node.getNext();
		}
		return null;
	}

	private XLinkedListNode<E> getNode( E elementToSearch ) {
		if( headNode == null ) {
			return null;
		}
		XLinkedListNode<E> node = headNode;
		while( node != null ) {
			if( elementToSearch == null && node.getData() == null ) {
				return node;
			}
			else if( elementToSearch.equals( node.getData() ) ) {
				return node;
			}
			node = node.getNext();
		}
		return null;
	}

	public void addAll( Collection<E> listOfElements ) {
		if( listOfElements == null ) {
			return;
		}
		for( Iterator<E> iterator = listOfElements.iterator(); iterator.hasNext(); ) {
			E e = (E) iterator.next();
			addElement( e );
		}
	}

	public XLinkedList<E> addElement( E newElement ) {
		return addLast( newElement );
	}

	public XLinkedList<E> addLast( E newElement ) {
		XLinkedListNode<E> newNode = new XLinkedListNode<E>( lastNode, newElement, null, single );
		if( lastNode != null ) {
			lastNode.setNext( newNode );
		}
		else {
			headNode = newNode;
		}
		lastNode = newNode;
		size++;

		return this;
	}

	public void addFirst( E newElement ) {
		XLinkedListNode<E> newNode = new XLinkedListNode<E>( null, newElement, headNode, single );
		if( headNode != null ) {
			newNode.setNext( headNode );
		}
		else {
			lastNode = newNode;
		}
		headNode = newNode;
		size++;
	}

	public void insertElement( int index, E newElement ) {
		if( index == 0 ) {
			addFirst( newElement );
			// commenting as even if it is last index, new element should be inserted before
			// last element
			// } else if (index == size - 1) {
			// addLast(newElement);
		}
		else {
			XLinkedListNode<E> previousNode = getNode( index - 1 );
			XLinkedListNode<E> newNode = new XLinkedListNode<E>( previousNode, newElement, previousNode.getNext(),
					single );
			size++;
		}
		assertSanityOfSize();
	}

	public boolean removeElement( E elementToRemove ) {
		try {
			XLinkedListNode<E> nodeToRemove = getNode( elementToRemove );
			if( nodeToRemove == null ) {
				return false;
			}

			// removing first node
			if( nodeToRemove == headNode ) {

				// if there is only one node in list
				if( lastNode == headNode ) {
					lastNode = null;
					headNode = null;
				}
				else {
					headNode = headNode.getNext();
				}
				size--; // TODO: should have a method to work with size. Updating everywhere is risk
				return true;
			}

			XLinkedListNode<E> nextNode = nodeToRemove.getNext();

			// TODO: should move to separate method - removeNodeForSingleList
			if( isSingle() ) {

				// if it is not last node
				if( nextNode != null ) {

					E nextNodeData = nextNode.getData();

					// This is done because, being single list, we don't know the previous node
					// we can find it using getLaggerNode method, but that will be like iterating the list
					// hence saving iteration

					nodeToRemove.setData( nextNodeData );
					nodeToRemove.setNext( nextNode.getNext() );

					// if next node was the last node, move the pointer
					// TODO: updating last node should be centralized. Updating imp variables like this, is risk
					if( nextNode.getNext() == null ) {
						lastNode = nodeToRemove;
					}
				}
				else {
					XLinkedListNode<E> previousNode = getLaggerNode( nodeToRemove, 1 );
					previousNode.setNext( null );

					lastNode = previousNode;
				}

				size--;
				return true;
			}

			// if double
			// TODO: move to separate method - removeNodeForDoublyList()

			XLinkedListNode<E> previousNode = nodeToRemove.getPrevioius();

			if( previousNode != null ) {
				previousNode.setNext( nextNode );
			}
			else {
				headNode = nextNode;
			}

			if( nextNode != null ) {
				nextNode.setPrevious( previousNode );
			}
			else {
				lastNode = previousNode;
			}

			size--;
			return true;
		}
		finally {
			assertSanityOfSize();
		}

	}

	// ------------------------------- Circular List ---------------------

	private boolean circular = false;

	public boolean isCircular() {
		return circular;
	}

	/**
	 * Make this list circular at given index
	 * 
	 * if index == 0 > connect last node with head node
	 * if index == size - 1 | means last node > nothing to do
	 * if index != 0 > connect last node with node at given index
	 * 
	 * @param index - index of the node to connect last node with
	 */
	public void makeCircularAt( int index ) {
		assertIndexInBound( index );

		XLinkedListNode<E> nodeAtIndex = getNode( index );
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
	public int calculateSizeOfCircularList() {
		if( !circular ) {
			throw new IllegalStateException( "Not a circular list" );
		}

		XLinkedListNode<E> node = headNode;
		HashSet<XLinkedListNode<E>> traversedNodes = new HashSet<>();

		int size = 0;
		while( node != null && !traversedNodes.contains( node ) ) {
			size++;
			traversedNodes.add( node );
			node = node.getNext();
		}

		return size;
	}

	// --------------------------------------------------------------
	// --------------------------- Algo - most for single linked list------------------------
	// --------------------------------------------------------------

	/**
	 * Concept is to keep another pointer for lagging node, which will start updating the lagging node
	 * after iterating over number of nodes equal to lagging place.
	 * 
	 * @param elementToSearch Reference Node
	 * @param place number of places which target node is behind
	 * @return data for node lagging by given places, from given node data (For single linked list)
	 */
	public E getLaggerNodeData( E elementToSearch, int place ) {
		XLinkedListNode<E> nodeToSearch = getNode( elementToSearch );
		if( nodeToSearch == null ) {
			return null;
		}

		XLinkedListNode<E> laggerNode = getLaggerNode( nodeToSearch, place );
		return laggerNode == null ? null : laggerNode.getData();
	}

	public E getLaggerNodeDataFromEnd( int place ) {
		XLinkedListNode<E> laggerNode = getLaggerNode( null, place );
		return laggerNode == null ? null : laggerNode.getData();
	}

	private XLinkedListNode<E> getLaggerNode( XLinkedListNode<E> nodeToSearch, int place ) {
		if( headNode == null ) {
			return null;
		}

		XLinkedListNode<E> currentNode = headNode;
		XLinkedListNode<E> laggerNode = null;

		int laggingPointer = 0;

		while( currentNode != null ) {

			if( laggingPointer == place ) // first iteration
			{
				laggerNode = headNode;
			}
			else if( laggingPointer > place ) { // once found, start updating with iteration
				laggerNode = laggerNode.getNext();
			}

			// case like n place lagging than last node
			if( nodeToSearch == null && currentNode.getNext() == null ) {
				return laggerNode;
			}
			else if( nodeToSearch == currentNode ) {
				return laggerNode;
			}

			laggingPointer = laggingPointer + 1;
			currentNode = currentNode.getNext();
		}

		return null;
	}

	/**
	 * Remove all duplicate nodes
	 * 
	 * NOTE: Does not contain logic for double linked list. That makes it complex
	 */
	public void dedup() {
		if( headNode == null ) {
			return;
		}

		/*
		 * Keep adding all traversed nodes to hashset
		 * Check for each new element in traversed nodes set
		 * If already exist, remove this node by pointing last node to its next node
		 */

		XLinkedListNode<E> currentNode = headNode;
		XLinkedListNode<E> previoiusNode = null;

		Set traversedNode = new HashSet<>();

		while( currentNode != null ) {
			// if current node is already traversed, remove this from the list
			if( traversedNode.contains( currentNode.getData() ) ) {
				previoiusNode.setNext( currentNode.getNext() );
			}
			else // add current node to traversed node and update it as previous node for next iteration
			{
				traversedNode.add( currentNode.getData() );
				previoiusNode = currentNode;
			}
			currentNode = currentNode.getNext();
		}

		// refresh size as we are manipulating the next node directly
		refreshSize();
	}

	/**
	 * Partition list data in a way that all numbers smaller than given data are on left and larger are on right
	 * 
	 * @param data
	 * @return
	 */
	public XLinkedListNode<E> partition( E data ) {
		if( headNode == null ) {
			return null;
		}

		/*
		 * Create two lists, before for smaller than data and after for larger or equal to data
		 * Iterate over list
		 * Move all smaller element to before list
		 * Move all larger elements to after list
		 * Merge both lists and return
		 */

		XLinkedListNode<E> beforeStart = null;
		XLinkedListNode<E> beforeEnd = null;
		XLinkedListNode<E> afterStart = null;
		XLinkedListNode<E> afterEnd = null;

		XLinkedListNode<E> currentNode = headNode;

		while( currentNode != null ) {
			if( ( (Integer) currentNode.getData() ) < (Integer) data ) {
				if( beforeStart == null ) {
					beforeStart = currentNode;
					beforeEnd = beforeStart;
				}
				else {
					beforeEnd.setNext( currentNode );
					beforeEnd = currentNode;
				}
			}
			else {
				if( afterStart == null ) {
					afterStart = currentNode;
					afterEnd = afterStart;
				}
				else {
					afterEnd.setNext( currentNode );
					afterEnd = currentNode;
				}
			}

			currentNode = currentNode.getNext();
		}

		XLinkedListNode<E> newListHeadNode = null;
		if( beforeStart == null ) {
			newListHeadNode = afterStart;
		}
		else {
			newListHeadNode = beforeStart;
			beforeEnd.setNext( afterStart );
		}

		// assertSanityForCircular();
		// assertSanityOfSize();

		return newListHeadNode;
	}

	public XLinkedListNode<Integer> addAnotherList( XLinkedList<Integer> anotherList ) {
		XLinkedListNode<Integer> headNode1 = (XLinkedListNode<Integer>) headNode;
		XLinkedListNode<Integer> headNode2 = anotherList == null ? null : anotherList.headNode;

		if( headNode1 == null || headNode2 == null ) {
			return null;
		}

		/*
		 * iterate over both the lists
		 * add data of each node at same location
		 * take mod 10, and use result as data for node in new list at same index
		 * get divisor as carry for next place addition
		 * 
		 * Works only for single digit values, which is logical also
		 */

		return addLists( headNode1, headNode2, 0 );
	}

	private XLinkedListNode<Integer> addLists( XLinkedListNode<Integer> node1, XLinkedListNode<Integer> node2,
			int carry ) {
		System.out.println( "adding: node1[" + ( node1 == null ? null : node1.getData() ) + "] node2["
				+ ( node2 == null ? null : node2.getData() ) + "]" );

		if( node1 == null && node2 == null && carry == 0 ) {
			return null;
		}

		int resultValue = carry;
		int node1Value = node1 != null ? node1.getData() : 0;
		int node2Value = node1 != null ? node2.getData() : 0;

		assertTrue( "Node values must be in single digit. value1[" + node1Value + "] value2[" + node2Value + "]",
				( node1Value < 10 && node2Value < 10 ) );

		XLinkedListNode<Integer> resultNode = new XLinkedListNode<Integer>();

		resultValue += ( node1Value + node2Value );

		resultNode.setData( resultValue % 10 );
		carry = resultValue / 10;

		System.out.println( "data[" + resultValue % 10 + "] carry[" + resultValue / 10 + "]" );

		if( node1 != null || node2 != null ) {
			XLinkedListNode<Integer> nextNode = addLists( node1 == null ? null : node1.getNext(),
					node2 == null ? null : node2.getNext(), carry );
			resultNode.setNext( nextNode );
		}
		return resultNode;
	}

	/**
	 * @return true if list is palindrome ie first half is same of second half in reverse order. In case of odd length
	 *         list, we need to skip middle element
	 */
	public boolean isPalindrome() {

		if( headNode == null ) {
			return false;
		}

		/*
		 * Have a normal counter and a fast counter (+2)
		 * iterate over the list
		 * keep pushing items by normal/slow counter to a stack. Stop by the time fast counter hit the end
		 * (handle odd length list case)
		 * start popping item from the stack, start iterating by slow counter
		 * match the elements for result
		 */

		XLinkedListNode<Integer> fastPointer = (XLinkedListNode<Integer>) headNode;
		XLinkedListNode<Integer> slowPointer = fastPointer;

		Stack<XLinkedListNode<Integer>> firstHalfStack = new Stack<>();

		// prepare the stack
		while( fastPointer != null && fastPointer.getNext() != null ) {

			firstHalfStack.push( slowPointer );
			slowPointer = slowPointer.getNext();
			fastPointer = fastPointer.getNext().getNext();
		}

		// skip middle element, if list is odd. If fastpointer is still not null, it means it is odd size list
		if( fastPointer != null ) {
			slowPointer = slowPointer.getNext();
		}

		// compare stack with slow pointer. Slow pointer cross half list by now
		while( slowPointer != null ) {

			if( firstHalfStack.pop().getData().intValue() != slowPointer.getData().intValue() ) {
				return false;
			}
			slowPointer = slowPointer.getNext();
		}
		return true;
	}

	/**
	 * 
	 * TODO: Boundaries cases are not handled
	 * 
	 * @param anotherList
	 * @return The intersection of both the list (data of intersection node)
	 */
	public Object findIntersectionNodeData( XLinkedList<E> anotherList ) {
		LastNodeAndLength result1 = getLastNodeAndLength( this );
		LastNodeAndLength result2 = getLastNodeAndLength( anotherList );

		// if last node is not same, means no inetraction
		if( result1.lastNode != result2.lastNode ) {
			return null;
		}

		// TODO: Simplify it 
		XLinkedListNode<E> shorterListNode = result1.length < result2.length ? this.headNode : anotherList.headNode;
		XLinkedListNode<E> longerListNode = result1.length < result2.length ? anotherList.headNode : this.headNode;

		longerListNode = getNode( Math.abs( result1.length - result2.length ) );

		while( shorterListNode != longerListNode ) {
			shorterListNode = shorterListNode.getNext();
			longerListNode = longerListNode.getNext();
		}

		return shorterListNode.getData();
	}

	/*
	 * Return last node of the list, and the length of the list
	 */
	private LastNodeAndLength getLastNodeAndLength( XLinkedList<E> list ) {
		XLinkedListNode<E> currentNode = list == null ? null : list.headNode;
		int length = 0;
		while( currentNode != null ) {
			length++;
			currentNode = currentNode.getNext();
		}
		return new LastNodeAndLength( length, currentNode );
	}

	private class LastNodeAndLength {

		int				length;
		XLinkedListNode	lastNode;

		private LastNodeAndLength( int length, XLinkedListNode<E> lastNode ) {
			this.length = length;
			this.lastNode = lastNode;
		}
	}

	// --------------------------------------------------------------
	// --------------------------- Utility methods ------------------------
	// --------------------------------------------------------------

	public void printAllElements() {
		printAllElements( headNode, true );
	}

	public void printAllElements( XLinkedListNode<E> pHeadNode, boolean assertCircularSanity ) {
		StringBuffer toPrint = new StringBuffer();
		if( pHeadNode == null ) {
			toPrint.append( "null" );
		}
		else {
			XLinkedListNode<E> currentNode = pHeadNode;
			HashSet<XLinkedListNode<E>> traversedNodes = new HashSet<>();

			while( currentNode != null ) {
				toPrint.append( currentNode.getData() );
				toPrint.append( ", " );

				traversedNodes.add( currentNode );

				currentNode = currentNode.getNext();
				if( traversedNodes.contains( currentNode ) ) {
					if( !circular && assertCircularSanity ) {
						throw new IllegalStateException( "Node can not be found again if list is not circular" );
					}
					toPrint.append( "Circular Found at node[" + currentNode.getData() + "]" );
					break;
				}
			}
		}
		System.out.println( toPrint );
	}

	public void printAllElementsBackwards() {
		StringBuffer toPrint = new StringBuffer();
		if( lastNode == null ) {
			toPrint.append( "null" );
		}
		else {
			XLinkedListNode<E> node = lastNode;
			while( node != null ) {
				toPrint.append( node.getData() );
				toPrint.append( ", " );

				if( !single ) {
					node = node.getPrevioius();
				}
				else {
					node = getLaggerNode( node, 1 );
				}
			}
		}
		System.out.println( toPrint );
	}

}
