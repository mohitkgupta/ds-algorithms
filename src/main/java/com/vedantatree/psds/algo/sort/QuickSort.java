package com.vedantatree.psds.algo.sort;

public class QuickSort
{

	public void quickSort( int[] arrayToSort )
	{
		quickSort( arrayToSort, 0, arrayToSort.length - 1 );
	}

	public void quickSort( int[] arrayToSort, int startIndex, int endIndex )
	{
		if( startIndex >= endIndex )
		{
			return;
		}

		int pivotIndex = partition( arrayToSort, startIndex, endIndex );

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
	private int partition( int[] arrayToSort, int startIndex, int endIndex )
	{
		int pivotElement = arrayToSort[endIndex];

		int smallerElementIndex = startIndex - 1;

		for( int iteratorIndex = startIndex; iteratorIndex < endIndex; iteratorIndex++ )
		{
			// if current element is smaller than pivot, put it in the beginning of array
			if( arrayToSort[iteratorIndex] <= pivotElement )
			{
				smallerElementIndex++;

				swapElementInArray( arrayToSort, smallerElementIndex, iteratorIndex );
			}

		}
		// put pivot element at the the current smaller element index
		swapElementInArray( arrayToSort, smallerElementIndex + 1, endIndex );

		return smallerElementIndex + 1;
	}

	private void swapElementInArray( int[] array, int firstIndex, int secondIndex )
	{
		int temp = array[firstIndex];

		array[firstIndex] = array[secondIndex];
		array[secondIndex] = temp;
	}

}
