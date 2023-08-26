package com.vedantatree.psds.ds;

import java.util.EmptyStackException;


/**
 * Stack Implementation, which can act on multiple stacks based on given stack number.
 * The key is that underlying data structure is one array only.
 * 
 * For implementation, based on each stack capacity, define the array length and then mark start and end based on each
 * stack capacity.
 * 
 * @author Mohit Gupta <mohitgupta.matrix@gmail.com>
 */
public class StacksOnOneArray
{

	private int		numberOfStacks;

	// capacity of each stack
	private int		stackCapacity;

	// actual size of each stack, populated capacity
	private int[]	sizes;

	// array containing values for all three stack
	private int[]	values;

	public StacksOnOneArray( int numberOfStacks, int stackCapacity )
	{
		this.numberOfStacks = numberOfStacks;
		this.stackCapacity = stackCapacity;

		values = new int[stackCapacity * numberOfStacks];
		sizes = new int[numberOfStacks];
	}

	public int getStackCapacity()
	{
		return stackCapacity;
	}

	public int getNumberOfStacks()
	{
		return numberOfStacks;
	}

	/**
	 * @param stackNumber Stack number from which we want to pop the element
	 * @param value to push to stack
	 * @return Instance of this, for ease of programming
	 */
	public StacksOnOneArray push( int stackNumber, int value )
	{
		if( isFull( stackNumber ) )
		{
			// can have custom exception
			throw new IllegalStateException( "Stack is already full" );
		}

		int topIndexForStack = topIndexForStack( stackNumber );
		System.out.println( "index: " + topIndexForStack + " stackNumber: " + stackNumber + " value: " + value );

		values[topIndexForStack] = value;
		incrementSize( stackNumber );

		return this;
	}

	/**
	 * @param stackNumber Stack number from which we want to pop the element
	 * @return remove element from top of the stack and return it
	 */
	public int pop( int stackNumber )
	{
		if( isEmpty( stackNumber ) )
		{
			throw new EmptyStackException();
		}

		int topIndexForStack = topIndexForStack( stackNumber );
		int returnValue = values[topIndexForStack];

		values[topIndexForStack] = 0;
		decrementSize( stackNumber );

		return returnValue;
	}

	/**
	 * @param stackNumber Stack number from which we want to peek the element
	 * @return return element from top of the stack
	 */
	public int peek( int stackNumber )
	{
		if( isEmpty( stackNumber ) )
		{
			throw new EmptyStackException();
		}

		return values[topIndexForStack( stackNumber )];
	}

	public boolean isEmpty( int stackNumber )
	{
		return getSize( stackNumber ) == 0;
	}

	public boolean isFull( int stackNumber )
	{
		return getSize( stackNumber ) == stackCapacity;
	}

	/**
	 * @return Next index of given stack number, ie the index where next element should be pushed
	 */
	private int topIndexForStack( int stackNumber )
	{
		int top = startOfStack( stackNumber ) + getSize( stackNumber );
		return stackNumber == 0 ? top : top - 1;
		// return top;
	}

	/**
	 * @return Starting index of given stack number
	 */
	private int startOfStack( int stackNumber )
	{
		return stackCapacity * stackNumber;
	}

	private int getSize( int stackNumber )
	{
		return sizes[stackNumber];
	}

	private void incrementSize( int stackNumber )
	{
		sizes[stackNumber]++;
	}

	private void decrementSize( int stackNumber )
	{
		sizes[stackNumber]--;
	}

	public void printStack()
	{
		for( int i = 0; i < values.length; i++ )
		{
			System.out.print( values[i] + " " );
		}
	}
}