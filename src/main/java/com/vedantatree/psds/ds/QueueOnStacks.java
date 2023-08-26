package com.vedantatree.psds.ds;

import java.util.Stack;


/**
 * Class implementing Queue using two stacks
 * 
 * Concept:
 * Using one stack to queue elements
 * use another stack to dequeue elements
 * >> if this stack is empty
 * >>>> move all elements from queue stack
 * 
 * @author Mohit Gupta <mohitgupta.matrix@gmail.com>
 */
public class QueueOnStacks<E>
{

	private Stack<E>	queueStack;
	private Stack<E>	dequeueStack;

	public QueueOnStacks()
	{
		queueStack = new Stack<>();
		dequeueStack = new Stack<>();
	}

	public void queue( E value )
	{
		queueStack.push( value );
	}

	public E dequeue()
	{
		shiftStackItems();
		return dequeueStack.pop();
	}

	public int getSize()
	{
		return queueStack.size() + dequeueStack.size();
	}

	private void shiftStackItems()
	{
		if( dequeueStack.isEmpty() )
		{
			while( !queueStack.isEmpty() )
			{
				dequeueStack.push( queueStack.pop() );
			}
		}
	}

}
