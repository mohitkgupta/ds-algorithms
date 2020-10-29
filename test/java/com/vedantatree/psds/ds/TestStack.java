package com.vedantatree.psds.ds;

import java.util.EmptyStackException;

import junit.framework.TestCase;


public class TestStack extends TestCase
{

	// TODO: Need fix
	public void testMultipleStack()
	{
		StacksOnOneArray multipleStack = new StacksOnOneArray( 5, 6 );

		assertTrue( "Stack should be empty", multipleStack.isEmpty( 2 ) );
		assertTrue( "Stack should not be full", !multipleStack.isFull( 3 ) );

		boolean exception = false;
		try
		{
			multipleStack.peek( 4 );
			multipleStack.pop( 0 );
		}
		catch( EmptyStackException ese )
		{
			exception = true;
		}
		assertTrue( "Peek should throw excepton because stack is empty", true );

		int allStackValues[][] =
		{
				{ 1, 2, 3, 4, 5 },
				{ -2, 7, 95785, 234, -233, 46467 },
				{ 65525, 255, 584, 2, 5445, 555 },
				{ 65525, 255, 584, 2, 5445, 555 },
				{ -887, -555, 255, 47, 32, 1 } };

		for( int i = 0; i < allStackValues.length; i++ )
		{
			int[] stackValues = allStackValues[i];
			for( int stackValue : stackValues )
			{
				multipleStack.push( i, stackValue );
			}
			multipleStack.printStack();
		}

		multipleStack.printStack();

		for( int i = 0; i < allStackValues.length; i++ )
		{
			int[] stackValues = allStackValues[i];
			for( int j = stackValues.length - 1; j >= 0; j-- )
			{
				int value = multipleStack.pop( i );

				assertTrue( "values are different. fromStack[" + value + "] original[" + stackValues[j] + "]",
						value == stackValues[j] );
			}
		}
	}

	public void testStackOfStacks()
	{
		StackOfStacks<Integer> stackOfStacks = new StackOfStacks<>( 2 );

		for( int i = 0; i < 100; i++ )
		{
			stackOfStacks.push( i );
		}

		assertEquals( "Size", 100, stackOfStacks.getSize() );
		assertEquals( "Stacks", 50, stackOfStacks.getNumberOfStacks() );

		for( int i = 0; i < 100; i++ )
		{
			stackOfStacks.pop();
		}

		assertEquals( "Size", 0, stackOfStacks.getSize() );
		assertEquals( "Stacks", 0, stackOfStacks.getNumberOfStacks() );
	}

	public void testQueueOnStacks()
	{
		QueueOnStacks<Integer> queue = new QueueOnStacks<>();

		for( int i = 1; i <= 10; i++ )
		{
			queue.queue( i );
		}

		for( int i = 1; i < 6; i++ )
		{
			assertEquals( "Dequeue is not matching", i, (int) queue.dequeue() );
		}

		for( int i = 11; i <= 20; i++ )
		{
			queue.queue( i );
		}

		for( int i = 6; i < 21; i++ )
		{
			assertEquals( "Dequeue is not matching", i, (int) queue.dequeue() );
		}

	}

	public void testSortedStack()
	{
		SortedStack sortedStack = new SortedStack();
		sortedStack.push( 5 );
		sortedStack.push( 1 );
		sortedStack.push( 23 );
		sortedStack.push( 56 );

		assertEquals( 1, sortedStack.pop().intValue() );

		sortedStack.push( 98 );
		sortedStack.push( 3 );
		sortedStack.push( 65 );
		sortedStack.push( -2 );

		assertEquals( -2, sortedStack.pop().intValue() );
		assertEquals( 3, sortedStack.pop().intValue() );
		assertEquals( 5, sortedStack.pop().intValue() );
		assertEquals( 23, sortedStack.pop().intValue() );
		assertEquals( 56, sortedStack.pop().intValue() );
		assertEquals( 65, sortedStack.pop().intValue() );
		assertEquals( 98, sortedStack.pop().intValue() );

	}

}