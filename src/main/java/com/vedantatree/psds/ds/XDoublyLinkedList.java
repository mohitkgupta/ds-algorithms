package com.vedantatree.psds.ds;

public class XDoublyLinkedList<E> extends XLinkedList<E> {

	public XDoublyLinkedList() {
		super( false );
	}

	/**
	 * method to assert sanity of list,
	 * - check size
	 * - and calculated size based on forward
	 * - - and backward navigation (for double)
	 */
	public void assertSanityOfSize() {

		super.assertSanityOfSize();

		XLinkedListNode<E> node = getLastNode();

		int backwardCount = 0;
		while( node != null ) {
			backwardCount++;
			node = node.getPrevioius();
		} // backward count calculated

		if( backwardCount != getSize() ) {
			printAllElements();
			printAllElementsBackwards();
			throw new IllegalStateException(
					"Backward count is not matching. Backward Count[" + backwardCount + "] size[" + getSize() + "]" );
		}

	}

	protected boolean removeNodeInternal( XLinkedListNode<E> nodeToRemove ) {

		XLinkedListNode<E> nextNode = nodeToRemove.getNext();
		XLinkedListNode<E> previousNode = nodeToRemove.getPrevioius();

		if( previousNode != null ) {
			previousNode.setNext( nextNode );
		}
		else {
			setHeadNode( nextNode );
		}

		if( nextNode != null ) {
			nextNode.setPrevious( previousNode );
		}
		else {
			setLastNode( previousNode );
		}

		reduceSize();
		return true;

	}

}
