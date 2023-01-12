package com.vedantatree.psds.algo.sort;

public class SortAlgo {

	/**
	 * This approach is > keep comparing elements and swapping as long these are not
	 * in right order "keep creating bubbles of iteration and swap"
	 *
	 * keep iterating over array if current element is larger than next element,
	 * swap these keep going in loops until none of the element is swapping
	 * 
	 * O(n square)
	 * 
	 * @param array
	 * @return
	 */
	public int[] bubbleSort(int[] array) {
		boolean swap = true;

		// keep going until any of element has been swapped in previous iteration
		while (swap) {
			swap = false;

			// iterate over the array
			// if current element is larger than next, swap these
			for (int i = 0; i < array.length - 1; i++) {
				if (array[i] > array[i + 1]) {
					int temp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = temp;
					swap = true;
				}
			}
		}
		return array;

	}

	/**
	 * "Select the smallest and move to front, i.e. to current index" This approach
	 * is > to bring smallest number from rest of the array to current index
	 * 
	 * Pick current element in array Compare it with rest of the elements and bring
	 * the smallest at its place
	 * 
	 * @param array
	 * @return
	 */
	public int[] selectionSort(int[] array) {

		// iterate over array
		for (int i = 0; i < array.length; i++) {

			// pick current element
			// keep comparing it with rest of the array element
			// swap it if it is greater than any of the element

			for (int j = i; j < array.length; j++) {
				if (array[i] > array[j]) {
					int temp = array[j];
					array[j] = array[i];
					array[i] = temp;
				}
			}
		}
		return array;
	}

	/**
	 * This approach is > pick next element, insert it in list so far at right
	 * place, keep going to next element As elements are inserted for sorted, hence
	 * insertion sort
	 * 
	 * Start picking elements from beginning Assume n and earlier elements as one
	 * list, sort this list sorting means - inserting n+1 element in the list at
	 * right place thats why it is called insertion sort and keep moving next doing
	 * same as above
	 * 
	 * sort first element - consider this as sorted list pick second element -
	 * insert it in earlier sorted list at right place pick third element - insert
	 * it in earlier sorted list at right place pick fourth element - insert it in
	 * earlier sorted list at right place and so on..
	 * 
	 * @param array
	 * @return
	 */
	public static int[] insertionSort(int[] array) {
		// iterate over main array
		for (int i = 0; i < array.length; i++) {

			// start from current main array index, pick that element
			// go backward, compare the picked element with every element to insert at right
			// place
			// list before current picked element is already sorted with previous iterations
			for (int j = i; j > 0 && array[j] < array[j - 1]; j--) {
				int temp = array[j];
				array[j] = array[j - 1];
				array[j - 1] = temp;
			}
		}
		return array;
	}

}
