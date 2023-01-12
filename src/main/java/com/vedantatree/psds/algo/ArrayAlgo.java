package com.vedantatree.psds.algo;

import java.util.ArrayList;
import java.util.List;

import com.vedantatree.psds.Utils;

public class ArrayAlgo {

	public static List<Integer> spiralTraverse(int[][] array) {
		int startingRow = 0;
		int endingRow = array.length - 1;
		int startingColumn = 0;
		int endingColumn = array[0].length - 1;

		List<Integer> sprialElements = new ArrayList<Integer>(array.length * array[0].length);

		while (startingColumn <= endingColumn && startingRow <= endingRow) {

			for (int i = startingColumn; i <= endingColumn; i++) {
				sprialElements.add(array[startingRow][i]);
			}
			for (int i = startingRow + 1; i <= endingRow; i++) {
				sprialElements.add(array[i][endingColumn]);
			}
			for (int i = endingColumn - 1; i >= startingColumn; i--) {
				if (startingRow == endingRow) // condition to manage when row are more, but columns are not
					break;
				sprialElements.add(array[endingRow][i]);
			}
			for (int i = endingRow - 1; i > startingRow; i--) {
				if (startingColumn == endingColumn)
					break;
				sprialElements.add(array[i][startingColumn]);
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
	public static boolean isMonotonic(int[] array) {
		if (array.length <= 1) {
			return true;
		}

		boolean increasing = false, decreasing = false;

		for (int i = 0; i < array.length - 1; i++) {

			if (array[i] < array[i + 1]) {
				if (decreasing) {
					return false;
				}
				increasing = true;
			} else if (array[i] > array[i + 1]) {
				if (increasing) {
					return false;
				}
				decreasing = true;
			} else {

			}
		}
		return increasing || decreasing || array.length > 1;
	}

	// minimum waiting time for given queries in array. Array contains execution
	// time for each query
	public static int minimumWaitingTime(int[] queries) {

		// sort the array, as minimum to maximum time will be optimum sequence to
		// minimize waiting time

		// [11, 1, 2, 8, 2, 6, 4]

		boolean swapped = true;

		while (swapped) {
			swapped = false;

			for (int i = 0; i < (queries.length - 1); i++) {
				int current = queries[i];
				int next = queries[i + 1];

				if (current > next) {
					queries[i] = next;
					queries[i + 1] = current;
					swapped = true;
				}
			}
		}

		Utils.printArray(queries);

		int previousWaitingTime = 0;
		int totalWait = 0;

		// [1, 2, 2, 4, 6, 8, 11]

		for (int j : queries) {
			totalWait += previousWaitingTime;
			previousWaitingTime += j;
		}

		return totalWait;

	}

	public static void main(String[] args) {
		ArrayAlgo.minimumWaitingTime(new int[] { 11, 1, 2, 8, 2, 6, 4 });
	}
}
