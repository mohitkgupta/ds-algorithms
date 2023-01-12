package com.vedantatree.psds.algo;

import junit.framework.TestCase;

public class SearchAlgorithms extends TestCase {

	/**
	 * Search the given target number in array using binary search
	 * 
	 * @param array  source array
	 * @param target number to search for
	 * @return index of matching position in array, or -1
	 */
	public int binarySearch(int[] array, int target) {
		return binarySearch(array, target, 0, array.length - 1);
	}

	private int binarySearch(int[] array, int target, int firstIndex, int lastIndex) {
		if (array.length == 0) {
			return -1;
		}
		
		if (array[firstIndex] == target) {
			return firstIndex;
		} else if (array[lastIndex] == target) {
			return lastIndex;
		} else if (firstIndex == lastIndex) {
			return -1;
		}

		// middle number from the array
		int middle = (firstIndex + lastIndex) / 2;

		if (array[middle] == target) {
			return middle;
		} else if (target < array[middle]) {
			return binarySearch(array, target, 0, middle - 1);
		} else {
			return binarySearch(array, target, middle + 1, lastIndex);
		}
	}

	public void testBinarySearch() {
		assertTrue(binarySearch(new int[] { 5, 7, 9, 12, 15 }, 7) == 1);
		assertTrue(binarySearch(new int[] { 5, 7, 9, 12, 15 }, 9) == 2);
		assertTrue(binarySearch(new int[] { 5, 7, 9, 12, 15 }, 12) == 3);
		assertTrue(binarySearch(new int[] { 5, 7, 9, 12, 15 }, 10) == -1);
		assertTrue(binarySearch(new int[] {}, 10) == -1);
	}

}
