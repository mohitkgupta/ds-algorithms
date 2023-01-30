package com.vedantatree.psds.algo.sort;

public class SortAlgo {

	/**
	 * This approach is > keep comparing elements and swapping as long these are not
	 * in right order
	 * 
	 * "keep creating bubbles of iteration and swap"
	 *
	 * Keep iterating over array if current element is larger than next element,
	 * Swap these keep going in loops until none of the element is swapping
	 * 
	 * Time - O(n square), Best - O(n)
	 * Space - O(1)
	 * 
	 * @param array
	 * @return the sorted array
	 */
	public static int[] bubbleSort( int[] array ) {
		boolean swapped = true;
		int sortedElements = 0;

		// exit loop, if no element has been swapped in last iteration
		while( swapped ) {
			swapped = false;

			// each loop will move the largest element to the end, hence next iteration can leave the last element

			for( int currentIndex = 0; currentIndex < array.length - 1 - sortedElements; currentIndex++ ) {

				if( array[currentIndex] > array[currentIndex + 1] ) {
					int temp = array[currentIndex];
					array[currentIndex] = array[currentIndex + 1];
					array[currentIndex + 1] = temp;

					swapped = true;
				}
			}
			sortedElements++;
		}
		return array;
	}

	// for practice, stored array elements in local variables to avoid repeated
	// access. CPU over memory. Would JRE be doing this optimization automatically??
	public static int[] bubbleSort2( int[] array ) {

		boolean swapped = true;

		while( swapped ) {
			swapped = false;

			for( int i = 0; i < array.length - 1; i++ ) {

				int first = array[i];
				int second = array[i + 1];

				if( first > second ) {
					array[i] = second;
					array[i + 1] = first;
					swapped = true;
				}
			}
		}
		return array;
	}

	/**
	 * "Select the smallest and move to front, i.e. to current index"
	 * This approach is > to bring smallest number from rest of the array to current index
	 * 
	 * Pick current element in array
	 * Compare it with rest of the elements and
	 * bring the smallest at current element's place
	 * 
	 * Time: O(n^2) Space: O(1)
	 * 
	 * @param array
	 * @return sorted array < Not required. Sorting is in-place, in input array
	 */
	public static int[] selectionSort( int[] array ) {

		for( int leftCounter = 0; leftCounter < array.length; leftCounter++ ) {

			// pick current element
			// keep comparing it with rest of the array element
			// swap it if it is greater than any of the element
			// This will bring smallest element from right list to the current element's place

			for( int j = leftCounter; j < array.length; j++ ) {

				if( array[leftCounter] > array[j] ) {
					int temp = array[j];
					array[j] = array[leftCounter];
					array[leftCounter] = temp;
				}
			}
		}
		return array;
	}

	public static int[] selectionSortImproved( int[] array ) {

		for( int leftCounter = 0; leftCounter < array.length; leftCounter++ ) {

			int smallestElement = Integer.MAX_VALUE;
			int smallestElementIndex = -1;

			// find smallest element from right side list of current left counter
			for( int subArrayIndex = leftCounter; subArrayIndex < array.length; subArrayIndex++ ) {

				if( array[subArrayIndex] < smallestElement ) {
					smallestElement = array[subArrayIndex];
					smallestElementIndex = subArrayIndex;
				}
			}

			// swap smallest element, if any element is found smaller than left counter element
			if( smallestElementIndex != -1 && smallestElement != array[leftCounter] ) {

				int temp = array[leftCounter];
				array[leftCounter] = array[smallestElementIndex];
				array[smallestElementIndex] = temp;
			}

		}
		return array;
	}

	/**
	 * sort first element - consider this as sorted list
	 * pick second element - insert it in earlier sorted list at right place
	 * pick third element - insert it in earlier sorted list at right place
	 * pick fourth element - insert it in earlier sorted list at right place
	 * and so on..
	 * 
	 * Time Complexity - O(n^2), Best - O(n)
	 * Space Complexity - O(1)
	 * 
	 * @param array
	 * @return
	 */
	public static int[] insertionSort( int[] array ) {

		for( int leftCounter = 0; leftCounter < array.length; leftCounter++ ) {

			// pick ith element, compare it with left side sorted list
			// insert in left list at right place, if it is smaller than any element

			for( int sortCounter = leftCounter; sortCounter > 0
					&& array[sortCounter] < array[sortCounter - 1]; sortCounter-- ) {

				int temp = array[sortCounter];
				array[sortCounter] = array[sortCounter - 1];
				array[sortCounter - 1] = temp;
			}
		}
		return array;
	}

}
