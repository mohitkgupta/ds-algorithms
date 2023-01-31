package com.vedantatree.psds.ds;

import java.util.Stack;

import junit.framework.TestCase;


/**
 * Class implemented a sorted stack
 * 
 * Algorithm:
 * Have two stack, S1 for push and S2 for Pop
 * Keep pushing elements to S1, push stack
 * On pop, prepare S2, pop stack - in sort order
 * Return from S2, the pop stack
 * 
 * Not an optimized way for storing sorted elements or for getting sorted list
 * But for practice only
 * 
 * 
 * @author Mohit Gupta <mohit.gupta@vedantatree.com>
 */
public class SortedStack {

	private Stack<Integer>	pushStack;
	private Stack<Integer>	popStack;

	public SortedStack() {
		pushStack = new Stack<>();
		popStack = new Stack<>();
	}

	public void push( Integer element ) {
		pushStack.push( element );
	}

	public Integer pop() {
		sortStack(); // ensure the smallest element is at the top of popStack
		return popStack.pop();
	}

	private void sortStack() {

		while( !pushStack.isEmpty() ) {
			Integer elementToSort = pushStack.pop();

			while( !popStack.isEmpty() ) {
				Integer topElement = popStack.peek();

				if( elementToSort <= topElement ) {
					popStack.push( elementToSort );
					break;
				}
				else {
					pushStack.push( popStack.pop() );
				}
			}

			if( popStack.isEmpty() ) {
				popStack.push( elementToSort );
			}
		}
	}

	public static void main( String[] args ) {
		SortedStack sortedStack = new SortedStack();
		sortedStack.push( 5 );
		sortedStack.push( 1 );
		sortedStack.push( 23 );
		sortedStack.push( 56 );

		sortedStack.push( 98 );
		sortedStack.push( 3 );
		sortedStack.push( 65 );
		sortedStack.push( -2 );

		TestCase.assertEquals( -2, sortedStack.pop().intValue() );
		TestCase.assertEquals( 1, sortedStack.pop().intValue() );
		TestCase.assertEquals( 3, sortedStack.pop().intValue() );
		TestCase.assertEquals( 5, sortedStack.pop().intValue() );
		TestCase.assertEquals( 23, sortedStack.pop().intValue() );
		TestCase.assertEquals( 56, sortedStack.pop().intValue() );
		TestCase.assertEquals( 65, sortedStack.pop().intValue() );
		TestCase.assertEquals( 98, sortedStack.pop().intValue() );
	}
}
