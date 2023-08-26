package com.vedantatree.psds.algo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


public class StackAlgorithms {

	/**
	 * Refer to test cases for use cases.
	 * 
	 * @param str with open, closed brackets. It may contain other characters also, other than brackets
	 * 
	 * @return true if brackets in given string are balanced.
	 *         Balanced == all opening brackets has corresponding closing brackets.
	 *         Closing brackets can not be before opening bracket
	 *         sequence of opening and closing brackets should be matched
	 */
	public static boolean balancedBrackets( String str ) {

		assertThat( str ).isNotNull();

		if( str.length() == 0 )
			return true;

		Stack<Character> brackets = new Stack<>();

		for( Character strChar : str.toCharArray() ) {

			if( strChar == '(' || strChar == '{' || strChar == '[' ) {
				brackets.push( strChar );
			}
			else if( strChar == ')' || strChar == '}' || strChar == ']' ) {

				// found closing bracket before opening
				if( brackets.isEmpty() )
					return false;

				Character popChar = brackets.pop();
				switch( strChar )
					{
						case ')':
							if( popChar != '(' )
								return false;
							break;
						case '}':
							if( popChar != '{' )
								return false;
							break;
						case ']':
							if( popChar != '[' )
								return false;
							break;
					}
			}
			else { // ignore other characters
				continue;
			}
		}

		return brackets.isEmpty();
	}

	/**
	 * Sort the given stack
	 * without using any other data structure
	 * Sorting should be in place
	 * Recursion - best suited
	 * 
	 * Time - O(n)
	 * Space - O(1)
	 * 
	 * @param stack
	 * @return sorted stack
	 */
	public static List<Integer> sortStack( List<Integer> stack ) {

		assertThat( stack ).isNotNull();

		if( stack.size() <= 1 )
			return stack;

		int top = stack.remove( stack.size() - 1 );
		sortStack( stack );
		insertInSortedStack( stack, top );

		return stack;
	}

	private static void insertInSortedStack( List<Integer> stack, int elementToInsert ) {

		if( stack.size() == 0 ) {
			stack.add( elementToInsert );
			return;
		}

		int top = stack.get( stack.size() - 1 );

		// keeping largest element at the top, i.e. at the end of the array
		if( top > elementToInsert ) {

			int temp = top;
			stack.remove( stack.size() - 1 );

			// simple add wont work as any element later in the stack can be smaller than earlier one
			insertInSortedStack( stack, elementToInsert );
			stack.add( temp );
		}
		else {
			stack.add( elementToInsert );
		}
	}

	/**
	 * Time Complexity - O(n) >> or O(n^2)
	 * Space Complexity - O(n)
	 * 
	 * TODO: Test cases
	 * 
	 * @param array
	 * @return array where each element is replaced with next greater element than it.
	 *         If not element greater than it, replace it with -1
	 *         Array should be considered as cyclic,
	 *         means if any element at beginning is greater than the last element, replace last element with that
	 */
	public int[] nextGreaterElement( int[] array ) {
		assertThat( array ).isNotNull();

		// we can do in-place update in array also.
		// However, case of updating element which does not have larger element with -1 is tricky then
		int[] greaterElementArray = new int[array.length];
		Arrays.fill( greaterElementArray, -1 );

		Stack<Integer> stack = new Stack<>();

		// doubled the length, so loop can go twice over list - this will take care of any element towards the end
		// which may have larger element towards beginning of array
		for( int i = 0; i < array.length * 2; i++ ) {

			// mod to keep index within array's length
			int index = i % array.length;
			int currentElement = array[index];

			while( !stack.isEmpty() && currentElement > array[stack.peek()] ) {
				greaterElementArray[stack.pop()] = currentElement;
			}

			stack.push( index );
		}

		return greaterElementArray;
	}

}
