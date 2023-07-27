package com.vedantatree.psds.algo;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;


public class LinkedListAlgorithms {

	/**
	 * Function to merge two linkedlist in sorted order
	 * 
	 * @return the head of merged list
	 */
	public static LinkedList mergeLinkedLists( LinkedList headOne, LinkedList headTwo ) {

		if( headOne == null ) {
			return headTwo;
		}
		else if( headTwo == null ) {
			return headOne;
		}
		else if( headOne.value < headTwo.value ) {
			headOne.next = mergeLinkedLists( headOne.next, headTwo );
			return headOne;
		}
		else {
			headTwo.next = mergeLinkedLists( headOne, headTwo.next );
			return headTwo;
		}
	}

	/**
	 * Reverse the given singly linkedlist, in place
	 * i.e. without creating a new list or using any other data structure
	 * 
	 * @param head of the Singly Linkedlist to reverse
	 * @return head of reversed linkedlist
	 */
	public static LinkedList reverseLinkedList( LinkedList head ) {

		LinkedList currentNode = head;
		LinkedList previousNode = null;

		while( currentNode != null ) {
			LinkedList tempCurrentNode = currentNode.next;

			currentNode.next = previousNode;
			previousNode = currentNode;
			currentNode = tempCurrentNode;
		}

		return previousNode;
	}

	/**
	 * Space Complexity - O(n), Time Complexity - O(n)
	 * 
	 * @param head Singly linkedlist to check for loop
	 * @return the node where given singly linkedlist is forming a loop, null otherwise
	 */
	public static LinkedList findLoop( LinkedList head ) {

		if( head == null || head.next == null ) {
			return null;
		}

		Set<LinkedList> nodes = new HashSet<>();

		LinkedList currentNode = head;

		while( currentNode != null ) {

			if( nodes.contains( currentNode ) ) {
				return currentNode;
			}
			nodes.add( currentNode );
			currentNode = currentNode.next;
		}

		return null;
	}

	/**
	 * Space Complexity - O(1), Time Complexity - O(n)
	 * 
	 * @param head Singly linkedlist to check for loop
	 * @return the node where given singly linkedlist is forming a loop, null otherwise
	 */
	public static LinkedList findLoopWithSeenProperty( LinkedList head ) {

		if( head == null || head.next == null ) {
			return null;
		}

		LinkedList currentNode = head;

		while( currentNode != null ) {

			if( currentNode.seen ) {
				return currentNode;
			}
			currentNode.seen = true;
			currentNode = currentNode.next;
		}

		return null;
	}

	/**
	 * Given two lists, find the node where these two lists intersect.
	 * 
	 * @param linkedListOne First list to check
	 * @param linkedListTwo Second list to check
	 * @return The node where both lists are merging, or null otherwise
	 */
	public LinkedList getMergingLinkedLists( LinkedList linkedListOne, LinkedList linkedListTwo ) {

		if( linkedListOne == null || linkedListTwo == null ) {
			return null;
		}

		Stack<LinkedList> firstListStack = getNodeStackForList( linkedListOne );
		Stack<LinkedList> secondListStack = getNodeStackForList( linkedListTwo );
		LinkedList previousNode = null;

		while( !firstListStack.isEmpty() && !secondListStack.isEmpty() ) {

			LinkedList stack1Node = firstListStack.pop();
			LinkedList stack2Node = secondListStack.pop();

			if( stack1Node.value != stack2Node.value ) {
				break;
			}
			else {
				previousNode = stack1Node;
			}
		}

		return previousNode;
	}

	private Stack<LinkedList> getNodeStackForList( LinkedList linkedList ) {

		Stack<LinkedList> listNodesStack = new Stack<>();

		while( linkedList != null ) {
			listNodesStack.push( linkedList );
			linkedList = linkedList.next;
		}

		return listNodesStack;
	}

	/**
	 * Each input linkedlist represent a number.
	 * First node in input linkedlists is most insignificant digit of the number
	 * 
	 * @param linkedListOne first linkedlist to sum
	 * @param linkedListTwo second linkedlist to sum
	 * @return linkedlist containing sum of both the input linkedlist
	 */
	public LinkedList sumOfLinkedLists( LinkedList linkedListOne, LinkedList linkedListTwo ) {

		int carryValue = 0;
		LinkedList sumListHead = null;
		LinkedList sumListNode = null;

		while( linkedListOne != null || linkedListTwo != null || carryValue != 0 ) {

			int sum = 0;

			if( linkedListOne != null ) {
				sum += linkedListOne.value;
				linkedListOne = linkedListOne.next;
			}

			if( linkedListTwo != null ) {
				sum += linkedListTwo.value;
				linkedListOne = linkedListTwo.next;
			}

			int nodeValue = sum % 10;

			LinkedList tempListNode = new LinkedList( nodeValue + carryValue );
			if( sumListNode != null ) {
				sumListNode.next = tempListNode;
			}
			else {
				sumListHead = tempListNode;
			}

			sumListNode = tempListNode;
			carryValue = sum / 10;
		}

		return sumListHead;
	}

	/**
	 * Algorithm:
	 * Get mid pointer of the list using slow, and fast pointer
	 * reverse the list from mid point
	 * compare reversed half and later half of the list
	 * if both are same - it is palindrome
	 * 
	 * Time Complexity - O(n) >> 3*n/2 (finding mid point, reversing, comparing)
	 * Space Complexity = O(1) >> nothing was stored
	 * 
	 * @param head of linkedlist
	 * @return True if given linkedlist represents a palindrome, false otherwise
	 */
	public boolean linkedListPalindrome( LinkedList head ) {

		LinkedList slowPointer = head;
		LinkedList fastPointer = head;

		while( fastPointer != null && fastPointer.next != null ) {

			slowPointer = slowPointer.next;
			fastPointer = fastPointer.next.next;
		}

		LinkedList reversedHalf = reverseLinkedList( slowPointer );

		while( reversedHalf != null ) {
			if( reversedHalf.value != head.value ) {
				return false;
			}
			reversedHalf = reversedHalf.next;
			head = head.next;
		}

		return true;
	}

	// TODO fix it
	public static LinkedList rearrangeLinkedList( LinkedList head, int k ) {

		LinkedList sortPointer = head;
		LinkedList currPointer = head;
		LinkedList prevCurrPointer = null;
		LinkedList headPointer = null;

		while( currPointer != null ) {

			if( currPointer.value > k ) {

			}
			else {
				if( prevCurrPointer != null ) {
					prevCurrPointer.next = currPointer.next;
				}

				LinkedList tempSortPointerNext = sortPointer.next;
				sortPointer = currPointer;
				sortPointer.next = tempSortPointerNext;

				if( headPointer == null )
					headPointer = sortPointer;

			}
			prevCurrPointer = currPointer;
			currPointer = currPointer.next;
		}

		return headPointer;
	}

	public static class LinkedList {

		public int			value;
		public boolean		seen;
		public LinkedList	next;

		public LinkedList( int value ) {
			this.value = value;
			this.next = null;
		}
	}

	public static void main( String[] args ) {
		System.out.println( 23 / 10 );
		System.out.println( 23 % 10 );
	}

}
