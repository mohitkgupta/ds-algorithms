package com.vedantatree.psds.algo.searchsort;

public class QuickSort {

	/**
	 * Time Complexity - n(log(n)), worst - n^2
	 * Space Complexity - O(1) - In-place sorting
	 * Better space efficient than merge sort
	 * 
	 * @param arrayToSort
	 * @return
	 */
	public static int[] quickSort( int[] arrayToSort ) {
		quickSort( arrayToSort, 0, arrayToSort.length - 1 );
		return arrayToSort;
	}

	private static void quickSort( int[] arrayToSort, int startIndex, int endIndex ) {
		if( startIndex >= endIndex ) {
			return;
		}

		int pivotIndex = partition( arrayToSort, startIndex, endIndex );

		// pivot index element is already at right index, sort rest of the list on left and right
		quickSort( arrayToSort, startIndex, pivotIndex - 1 );
		quickSort( arrayToSort, pivotIndex + 1, endIndex );
	}

	/**
	 * The function selects the last element as pivot element, places that pivot element correctly in the array in such
	 * a way that all the elements to the left of the pivot are lesser than the pivot and all the elements to the right
	 * of pivot are greater than it.
	 * 
	 * @Parameters: arrayToSort, starting index and ending index
	 * @Returns: index of pivot element after placing it correctly in sorted array
	 */
	private static int partition( int[] arrayToSort, int startIndex, int endIndex ) {

		int pivotElement = arrayToSort[endIndex];

		// TODO confusing to set this index as startIndex-1. Initially, it will be -1. Improve it
		// tracking the index to put the elements smaller than pivot at the beginning of array, towards left of pivot
		int smallerElementIndex = startIndex - 1;

		for( int currentIndex = startIndex; currentIndex < endIndex; currentIndex++ ) {

			if( arrayToSort[currentIndex] <= pivotElement ) {

				// if current element is smaller than pivot, put it in the beginning of array
				smallerElementIndex++;
				swapArrayElements( arrayToSort, smallerElementIndex, currentIndex );
			}

		}
		// move pivot element at the the current smaller element index
		// after this move, all elements on left side of pivot will be smaller than pivot
		// all elements on right side will be larger
		// elements will be only smaller and larger than pivot element > not entirely sorted
		swapArrayElements( arrayToSort, smallerElementIndex + 1, endIndex );

		return smallerElementIndex + 1;
	}

	private static void swapArrayElements( int[] array, int firstIndex, int secondIndex ) {

		int temp = array[firstIndex];
		array[firstIndex] = array[secondIndex];
		array[secondIndex] = temp;
	}

}
