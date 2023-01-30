package com.vedantatree.psds.algo;

import java.util.Stack;


public class LinkedListAlgorithms {

	public LinkedList getMergingLinkedLists( LinkedList linkedListOne, LinkedList linkedListTwo ) {
		if( linkedListOne == null || linkedListTwo == null ) {
			return null;
		}

		Stack<LinkedList> firstListStack = getNodeStackForList( linkedListOne );
		Stack<LinkedList> secondListStack = getNodeStackForList( linkedListTwo );

		// TODO why do we need stack. We can keep getting elements directly from linkedlist too
		while( !firstListStack.isEmpty() && !secondListStack.isEmpty() ) {
			
			LinkedList currentNode1 = firstListStack.pop();
			LinkedList currentNode2 = secondListStack.pop();

			if( currentNode1.value == currentNode2.value ) {
				return currentNode1;
			}
		}

		return null;
	}

	private Stack<LinkedList> getNodeStackForList( LinkedList linkedList ) {
		Stack<LinkedList> listNodesStack = new Stack<>();

		LinkedList currentNode = linkedList;
		while( currentNode != null ) {
			listNodesStack.push( currentNode );
			currentNode = currentNode.next;
		}

		return listNodesStack;
	}

	public static class LinkedList {

		public int			value;
		public LinkedList	next;

		public LinkedList( int value ) {
			this.value = value;
			this.next = null;
		}
	}

}
