package com.vedantatree.psds.algo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

/**
 * 
 * Dynamic programming is an algorithmic technique for solving problems by breaking them down 
 * into smaller subproblems and using the solutions to those subproblems to solve the original problem. 
 * Dynamic programming is a powerful technique that can be used to solve a wide variety of problems, 
 * including optimization problems, scheduling problems, and game theory problems.
 * 
 * Dynamic programming algorithms typically work by storing the solutions to subproblems in a table. 
 * This table is called a memoization table or lookup table. The table is initialized with the solutions 
 * to the base cases, and then the solutions to the recursive calls are stored in the table as they are computed.
 * 
 * When a recursive call is made, the algorithm first checks the memoization table to see 
 * if the solution to the subproblem has already been computed. If it has, the algorithm returns 
 * the solution from the table. If the solution has not been computed, the algorithm computes 
 * the solution and stores it in the table.
 * 
 * The use of memoization allows dynamic programming algorithms to avoid recomputing the same subproblems multiple times. 
 * This can lead to significant speedups for problems with overlapping subproblems.
 * 
 * @author Mohit Gupta
 */
public class DPAlgorithms {

	/**
	 * Time Complexity - O(n)
	 * Space Complexity - O(n)
	 *
	 * TODO write test cases
	 * 
	 * @param width of the graph
	 * @param height of the graph
	 * @return number of ways to reach to the bottom right corner
	 */
	public int numberOfWaysToTraverseGraph( int width, int height ) {

		int[][] graph = new int[height][width];

		// first column cells will have only one way to reach them, thats from up
		for( int row = 0; row < height; row++ ) {
			graph[row][0] = 1;
		}

		// first row cells will have only one way to reach them, thats from left
		for( int col = 0; col < width; col++ ) {
			graph[0][col] = 1;
		}

		// rest of the cells
		// number of ways to reach = number of ways to reach cell in previous row + in previous col
		for( int row = 1; row < height; row++ ) {
			for( int col = 1; col < width; col++ ) {

				// better in reading
				int previousRow = row - 1;
				int previousCol = col - 1;

				graph[row][col] = graph[previousRow][col] + graph[row][previousCol];
			}
		}

		return graph[height - 1][width - 1];
	}

	/**
	 * Levenshtein Distance
	 * 
	 * Write a function to calculate number of edit/delete/replace operation required
	 * to make str1 == str2
	 * 
	 * Algorithm >>
	 * Build two dimensional edit matrix for combinations of str1 and str2
	 * Fill each cell with
	 * - if char in both string at given cell location is equal = value of previous diagonal cell
	 * - - i.e. edit required up to previous char in both string
	 * - if char is not equal = minimum of (previous diagonal cell, previous row cell, previous col cell) + 1
	 * - - Pick minimum edit required up to last char in 1st str, or in 2nd str, or in both str + Increment by one
	 * At the end, the value in last cell will represent the total number of operation required
	 * 
	 * Example abc, yabd
	 * 
	 * = = 0 1 2 3
	 * = = ''a b c
	 * 0 ''0 1 2 3
	 * 1 y 1 2 3 4
	 * 2 a 2 1 2 3
	 * 3 b 3 2 1 2
	 * 4 d 4 3 2 2
	 * 
	 * answer == 2
	 *
	 * TODO write test cases
	 * 
	 * @param str1
	 * @param str2
	 * @return number of edit/delete/replace operations to make str1 == str2
	 */
	public static int numberOfModificationsApart( String str1, String str2 ) {

		assertThat( str1 ).isNotNull();
		assertThat( str2 ).isNotNull();

		if( str1.equals( str2 ) )
			return 0;

		int rows = str1.length() + 1;
		int cols = str2.length() + 1;

		int[][] editMatrix = new int[rows][cols];

		// This makes code more readable
		int prevRow;
		int prevCol;

		for( int row = 1; row < rows; row++ ) {
			prevRow = row - 1;
			editMatrix[row][0] = 1 + editMatrix[prevRow][0];
		}
		for( int col = 1; col < cols; col++ ) {
			prevCol = col - 1;
			editMatrix[0][col] = 1 + editMatrix[0][prevCol];
		}

		for( int row = 1; row < rows; row++ ) {
			for( int col = 1; col < cols; col++ ) {

				// more readable
				prevRow = row - 1;
				prevCol = col - 1;

				if( str1.charAt( prevRow ) == str2.charAt( prevCol ) ) {
					editMatrix[row][col] = editMatrix[prevRow][prevCol];
				}
				else {
					editMatrix[row][col] = 1
							+ Math.min( Math.min( editMatrix[prevRow][prevCol], editMatrix[prevRow][col] ),
									editMatrix[row][prevCol] );
				}
			}
		}

		return editMatrix[rows - 1][cols - 1];
	}

	/**
	 * Example:
	 * input - [75, 105, 120, 75, 90, 135]
	 * output - 330 (75 + 120 + 135)
	 * 
	 * Algorithm -
	 * Fill in the maxSubsetNoAdjacent sum for every element in array, start from left to right
	 * Sum at 1st index = 1st element > because there is nothing on the left
	 * Sum at 2nd index = greater of 1st and 2nd element
	 * Sum at rest every index == Max of following
	 * > Sum at previous index
	 * > Sum at i-2 + current element
	 * 
	 * @param array inputs
	 * @return The maximum sum which can be created by non-adjacent array elements
	 */
	public static int maxSubsetSumNoAdjacent( int[] array ) {
		assertThat( array ).isNotNull();

		if( array.length == 0 )
			return 0;
		if( array.length == 1 )
			return array[0];

		array[1] = array[0] > array[1] ? array[0] : array[1];

		for( int i = 2; i < array.length; i++ ) {
			int prevIndexSum = array[i - 1];
			int currentIdxSum = array[i - 2] + array[i];

			array[i] = prevIndexSum > currentIdxSum ? prevIndexSum : currentIdxSum;
		}

		return array[array.length - 1];
	}

	/**
	 * These kind of questions are not that easy to solve without practice, or if you don't know the pattern
	 * Creating pattern on the feet in interview is not that easy.
	 * 
	 * Pattern -
	 * Populate ways to make every amount from 0 to targetAmount by given denoms
	 * 
	 * TODO: Add test case
	 * TODO: Describe the algorithm in simple words - so a school going can also understand
	 * 
	 * @param targetAmount The target amount
	 * @param denoms array of denomination available to form target amount
	 * @return number of ways in which given denom can be used to form target amount
	 */
	public static int numberOfWaysToMakeChange( int targetAmount, int[] denoms ) {
		assertThat( denoms ).isNotNull();
		assertThat( targetAmount ).isGreaterThan( 0 );

		if( denoms.length == 0 )
			return 0;

		int[] ways = new int[targetAmount + 1];
		Arrays.fill( ways, 0 );
		ways[0] = 1; // there can be only 1 way to make 0, which is not using any denom

		for( int denom : denoms ) {
			for( int amount = 1; amount <= targetAmount; amount++ ) {
				
				if( denom <= amount ) {
					ways[amount] += ways[amount - denom];
				}
			}
		}

		return ways[targetAmount];
	}

	/**
	 * Kadane's algorithm
	 * Time Complexity - O(n)
	 * Space Complexity - O(1)
	 * 
	 * Algorithm:
	 * Maximum sum at any given index = Maximum of (previous index maximum sum + current element <> and current element)
	 * 
	 * @param array The input array
	 * @return maximum sum which can be obtained by adding any number of adjacent elements
	 */
	public static int maxSumOfAdjancentElementsSubArray( int[] array ) {
		assertThat( array ).isNotNull();

		int arrLength = array.length;

		if( arrLength == 0 )
			return 0;
		if( arrLength == 1 )
			return array[0];

		int maximumSum = array[0];
		for( int i = 1; i < arrLength; i++ ) {

			array[i] = Math.max( array[i - 1] + array[i], array[i] );
			maximumSum = maximumSum > array[i] ? maximumSum : array[i];
		}

		return maximumSum;
	}

}
