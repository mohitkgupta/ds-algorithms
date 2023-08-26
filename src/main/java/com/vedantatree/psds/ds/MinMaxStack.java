package com.vedantatree.psds.ds;

/**
 * Implementation of a Stack which can return largest and smallest element from stack at any point of time
 * 
 * Iterating through elements is one brute force solution.
 * Implementing with right data structure and logic is desired.
 */

public class MinMaxStack {

	private StackNode top;

	public int peek() {
		return top != null ? top.value : Integer.MIN_VALUE;
	}

	public int pop() {
		if( top != null ) {
			StackNode topNode = top;
			top = topNode.prevNode;
			return topNode.value;
		}
		return Integer.MIN_VALUE;
	}

	public void push( Integer number ) {
		this.top = new StackNode( number, top );
		top.minValue = top.prevNode != null ? Math.min( number, top.prevNode.minValue ) : number;
		top.maxValue = top.prevNode != null ? Math.max( number, top.prevNode.maxValue ) : number;
	}

	public int getMin() {
		return top != null ? top.minValue : Integer.MIN_VALUE;
	}

	public int getMax() {
		return top != null ? top.maxValue : Integer.MAX_VALUE;
	}

	static class StackNode {

		int			value;
		int			maxValue;
		int			minValue;

		StackNode	prevNode;

		StackNode( int value ) {
			this.value = value;
		}

		StackNode( int value, StackNode prevNode ) {
			this.value = value;
			this.prevNode = prevNode;
		}
	}

}
