package com.vedantatree.psds.algo.sort;

import static org.junit.Assert.assertNotNull;

import com.vedantatree.psds.Utils;


public class MergeSort {

	/**
	 * Time Complexity - n(log(n))
	 * - log n > for dividing in the smaller subset
	 * - n for conquering i.e. merging the sorted array. Most work happens here
	 * 
	 * Space Complexity - o(n) > as we are dividing the main array in sub-arrays
	 * - Hence not memory efficient
	 * 
	 * @param array to sort
	 */
	public static int[] mergeSort( int[] array ) {
		assertNotNull( array );
		Utils.printArray( array );

		// other approach is that each merge function creates its own array,
		// and later all sub-sorted-arrays are merged to create main array
		// Current approach looks better. It is not creating many arrays, but updating same array.
		int[] helperArray = new int[array.length];

		mergeSort( array, helperArray, 0, array.length - 1 );

		return array;
	}

	private static void mergeSort( int[] array, int[] helperArray, int low, int high ) {
		if( low >= high ) {
			return;
		}

		int middle = low + ( high - low ) / 2;

		mergeSort( array, helperArray, low, middle ); // divide left half
		mergeSort( array, helperArray, middle + 1, high ); // divide right half

		mergeSortedHalves( array, helperArray, low, middle, high ); // merge both halves in sorted order
	}

	private static void mergeSortedHalves( int[] array, int[] helperArray, int low, int middle, int high ) {

		// copy array data in helper array
		// System.arraycopy( array, low, helper, low, high );
		for( int i = low; i <= high; i++ ) {
			helperArray[i] = array[i];
		}

		int helperLeft = low;
		int helperRight = middle + 1;
		int mainArrayIndex = low;

		while( helperLeft <= middle && helperRight <= high ) {

			if( helperArray[helperLeft] <= helperArray[helperRight] ) {
				array[mainArrayIndex] = helperArray[helperLeft];
				helperLeft++;
			}
			else {
				array[mainArrayIndex] = helperArray[helperRight];
				helperRight++;
			}
			mainArrayIndex++;
		}

		// copy pending items from left or right array.
		// This can happen as one of the array might have been copied already
		// Consider that these left and right are already sorted in itself from previous passes

		// following both logics are working. Can use either one.
		// difference is just in readability. 1st one looks simpler for eyes

		while( helperLeft <= middle ) {
			array[mainArrayIndex] = helperArray[helperLeft];
			helperLeft++;
			mainArrayIndex++;
		}

		while( helperRight < high ) {
			array[mainArrayIndex] = helperArray[helperRight];
			helperRight++;
			mainArrayIndex++;
		}

		// boolean remainingInLeft = helperLeft <= middle;
		// int remaining = remainingInLeft ? middle - helperLeft : high - helperRight;
		// int helperIndex = remainingInLeft ? helperLeft : helperRight;
		//
		// for( int i = 0; i <= remaining; i++ )
		// {
		// array[mainArrayCurrent + i] = helper[helperIndex + i];
		// }

	}
}
