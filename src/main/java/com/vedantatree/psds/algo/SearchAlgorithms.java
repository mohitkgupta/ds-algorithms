package com.vedantatree.psds.algo;

import static org.assertj.core.api.Assertions.assertThat;


public class SearchAlgorithms {

	/**
	 * Search the given target number in array using binary search
	 * 
	 * Time complexity - O(log(n))
	 * Space complexity - O(1)
	 * 
	 * @param array source array
	 * @param target number to search for
	 * @return index of matching position in array, or -1
	 */
	public static int binarySearch( int[] array, int target ) {
		return binarySearch( array, target, 0, array.length - 1 );
	}

	private static int binarySearch( int[] array, int target, int firstIndex, int lastIndex ) {
		if( array.length == 0 ) {
			return -1;
		}

		if( array[firstIndex] == target ) {
			return firstIndex;
		}
		else if( array[lastIndex] == target ) {
			return lastIndex;
		}
		else if( firstIndex == lastIndex ) {
			return -1;
		}

		// middle number from the array
		int middle = ( firstIndex + lastIndex ) / 2;

		if( array[middle] == target ) {
			return middle;
		}
		else if( target < array[middle] ) {
			return binarySearch( array, target, 0, middle - 1 );
		}
		else {
			return binarySearch( array, target, middle + 1, lastIndex );
		}
	}

	/**
	 * Time complexity O(n*m), Space Complexity - O(1)
	 * 
	 * @param matrix with sorted values in each row and column
	 * @param target element to search
	 * @return if target element is found, index of the target element, [-1, -1] otherwise
	 */
	public static int[] searchInSortedMatrix( int[][] matrix, int target ) {
		assertThat( matrix ).isNotNull();

		int row = matrix.length - 1;
		int column = 0;

		while( row >= 0 && column < matrix[row].length ) {
			int currentValue = matrix[row][column];

			if( currentValue == target ) {
				return new int[]
					{ row, column };
			}
			else if( currentValue > target ) {
				row--;
			}
			else {
				column++;
			}
		}

		return new int[]
			{ -1, -1 };
	}

	/**
	 * Time Complexity - O(n) for linear search in rows, O(logm) for binary search in column
	 * O(n + log(m))
	 * 
	 * Space Complexity - O(1)
	 * 
	 * @param matrix
	 * @param target
	 * @return
	 */
	public static int[] searchInSortedMatrixBinary( int[][] matrix, int target ) {
		assertThat( matrix ).isNotNull();

		int rows = matrix.length;

		for( int rowIndex = 0; rowIndex < rows; rowIndex++ ) {

			int startColumn = 0;
			int endColumn = matrix[rowIndex].length - 1;
			int currentValue = matrix[rowIndex][startColumn];

			if( currentValue == target ) {
				return new int[]
					{ rowIndex, startColumn };
			}
			else if( matrix[rowIndex][endColumn] == target ) {
				return new int[]
					{ rowIndex, endColumn };
			}
			else if( matrix[rowIndex][startColumn] > target || matrix[rowIndex][endColumn] < target ) {
				continue;
			}

			while( startColumn <= endColumn ) {

				int midColumn = startColumn + ( endColumn - startColumn ) / 2;
				currentValue = matrix[rowIndex][midColumn];

				if( currentValue == target ) {
					return new int[]
						{ rowIndex, midColumn };
				}
				else if( currentValue > target ) {
					endColumn = midColumn;
				}
				else {
					startColumn = midColumn + 1;
				}

			}

		}

		return new int[]
			{ -1, -1 };
	}

}
