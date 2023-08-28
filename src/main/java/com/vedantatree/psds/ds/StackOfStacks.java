package com.vedantatree.psds.ds;

import static org.junit.Assert.assertFalse;

import java.util.EmptyStackException;
import java.util.Stack;

import junit.framework.TestCase;


/**
 * Stack implementation having multiple stacks internally in a way that one stack should not grow beyond given capacity
 * 'stack of plates to avoid fall'
 * 
 * Have an stack to track internal stacks
 * Keep adding element to top stack, till it does not hit the capacity
 * once it hit the capacity, create new stack and push to stack and start using this
 * On pop, pop element from top stack, Remove this stack if size become zero after pop
 * 
 * @author Mohit Gupta <mohitgupta.matrix@gmail.com>
 */
public class StackOfStacks<E> {

	private int				perStackCapacity;
	private int				size;
	private Stack<E>		currentStack;
	private Stack<Stack<E>>	multiStack	= new Stack<>();

	// just for test validation
	private int				numberOfStacks;

	public StackOfStacks( int perStackCapacity ) {
		this.perStackCapacity = perStackCapacity;
	}

	public void push( E value ) {
		if( ( currentStack == null ) || ( currentStack.size() == perStackCapacity ) ) {
			currentStack = new Stack<>();
			multiStack.push( currentStack );
			numberOfStacks++;
		}
		currentStack.push( value );
		size++;
	}

	public E pop() {
		// TODO can add some assertions for sanity
		if( currentStack == null || currentStack.size() == 0 ) {
			throw new EmptyStackException();
		}

		E value = currentStack.pop();
		size--;

		if( currentStack.size() == 0 ) {
			multiStack.pop();
			numberOfStacks--;

			// if overall stack is not empty, set the next stack as current
			if( size != 0 ) {
				currentStack = multiStack.peek();
			}
		}

		return value;
	}

	public int getSize() {
		assertFalse( "size must not be less than zero", size < 0 );

		return size;
	}

	public int getPerStackCapacity() {
		return perStackCapacity;
	}

	public int getNumberOfStacks() {
		TestCase.assertEquals( "Number of stacks", numberOfStacks, getSize() / perStackCapacity );

		return numberOfStacks;
	}

}
