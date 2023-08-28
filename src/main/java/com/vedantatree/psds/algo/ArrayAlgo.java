package com.vedantatree.psds.algo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import com.vedantatree.psds.Utils;


public class ArrayAlgo {

	/**
	 * Remove all the islands from given matrix
	 * Island = cell which has value 1, but it is not reachable from the edge.
	 * How to Reach = Traversal can be only through cells which are marked as 1.
	 * 
	 * @param matrix the input
	 * @return new matrix with result only
	 */
	public static int[][] removeIslands( int[][] matrix ) {

		assertThat( matrix ).isNotNull();
		assertThat( matrix[0] ).isNotNull();

		int[][] result = new int[matrix.length][matrix[0].length];

		int row = 0;
		int col = 0;

		for( ; col < matrix[row].length; col++ ) {
			markIslands( row, col, matrix, result );
		}

		col--; // reduce extra increment in last for loop - TODO: can find better way
		for( ; row < matrix.length; row++ ) {
			markIslands( row, col, matrix, result );
		}

		row--;
		for( ; col >= 0; col-- ) {
			markIslands( row, col, matrix, result );
		}

		col++;
		for( ; row > 0; row-- ) { // skip 0 index for row as that is already covered
			markIslands( row, col, matrix, result );
		}

		return result;
	}

	private static void markIslands( int row, int col, int[][] matrix, int[][] result ) {

		if( row < 0 || row > matrix.length - 1 || col < 0 || col > matrix[row].length - 1 ) {
			return; // out of bound.
		}

		if( matrix[row][col] != 1 ) {
			return; // input itself does not have 1
		}

		if( result[row][col] == 1 ) {
			return; // marked already // This logic may end up in this condition multiple times. TODO: can improve..?
		}

		result[row][col] = 1;

		markIslands( row - 1, col, matrix, result );
		markIslands( row + 1, col, matrix, result );
		markIslands( row, col - 1, matrix, result );
		markIslands( row, col + 1, matrix, result );
	}

	public static List<Integer> spiralTraverse( int[][] array ) {
		int rows = array.length;
		int cols = array[0].length;

		int startingRow = 0;
		int endingRow = rows - 1;
		int startingColumn = 0;
		int endingColumn = cols - 1;

		List<Integer> sprialElements = new ArrayList<Integer>( rows * cols );

		while( startingColumn <= endingColumn && startingRow <= endingRow ) {

			for( int col = startingColumn; col <= endingColumn; col++ ) {
				sprialElements.add( array[startingRow][col] );
			}
			for( int row = startingRow + 1; row <= endingRow; row++ ) {
				sprialElements.add( array[row][endingColumn] );
			}
			for( int col = endingColumn - 1; col >= startingColumn; col-- ) {
				// TODO move this out to wrap for loop
				if( startingRow == endingRow ) // condition to manage when row are more, but columns are not
					break;
				sprialElements.add( array[endingRow][col] );
			}
			for( int row = endingRow - 1; row > startingRow; row-- ) {
				// TODO move this out to wrap for loop
				if( startingColumn == endingColumn )
					break;
				sprialElements.add( array[row][startingColumn] );
			}

			startingColumn += 1;
			endingColumn -= 1;
			startingRow += 1;
			endingRow -= 1;
		}
		return sprialElements;
	}

	/*
	 * Array is monotonic if its elements are either increasing always or decreasing
	 * always. if two elements are equal = that will also count to monotonic
	 */
	public static boolean isMonotonic( int[] array ) {
		if( array.length <= 1 ) {
			return true;
		}

		boolean increasing = false, decreasing = false;

		for( int i = 0; i < array.length - 1; i++ ) {

			if( array[i + 1] > array[i] ) {
				if( decreasing ) {
					return false;
				}
				increasing = true;
			}
			else if( array[i + 1] < array[i] ) {
				if( increasing ) {
					return false;
				}
				decreasing = true;
			}
//			else {
				// if elements are equal - no change in increasing or decreasing
//			}
		}
		
		// return true if array's length is more than 1 at the end
		// TODO: What is the need of array.length > 1. increasing or decreasing flag would always cover the cases
		// TODO: Test and Document if there is a special case
		
		return increasing || decreasing || array.length > 1;
	}

	// minimum waiting time for given queries in array. 
	// Array contains execution time for each query
	public static int minimumWaitingTime( int[] queries ) {

		// sort the array, as minimum to maximum time will be optimum sequence to
		// minimize waiting time

		// [11, 1, 2, 8, 2, 6, 4]

		boolean swapped = true;

		while( swapped ) {
			swapped = false;

			for( int i = 0; i < ( queries.length - 1 ); i++ ) {
				int current = queries[i];
				int next = queries[i + 1];

				if( current > next ) {
					queries[i] = next;
					queries[i + 1] = current;
					swapped = true;
				}
			}
		}

		Utils.printArray( queries );

		// TODO think a better name for this variable, so logic becomes understandable easily
		int previousWaitingTime = 0;
		int totalWait = 0;

		// example: [1, 2, 2, 4, 6, 8, 11]

		// Total waiting time = waiting time for each of the query
		for( int j : queries ) {
			
			totalWait += previousWaitingTime;
			
			previousWaitingTime += j;
		}

		return totalWait;

	}

	public static void main( String[] args ) {
		int totalWaitTime = ArrayAlgo.minimumWaitingTime( new int[]
			{ 11, 1, 2, 8, 2, 6, 4 } );
		System.out.println( "Total Wait Time > " + totalWaitTime );
	}
}
