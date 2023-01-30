package com.vedanatree.psds.algo.sort;

import com.vedantatree.psds.Utils;
import com.vedantatree.psds.algo.sort.MergeSort;
import com.vedantatree.psds.algo.sort.QuickSort;
import com.vedantatree.psds.algo.sort.SortAlgo;

import junit.framework.TestCase;


public class TestSortAlgo extends TestCase {

	private static final int	BUBBLE_SORT				= 0;
	private static final int	BUBBLE_SORT_IMPROVED	= 10;
	private static final int	SELECTION_SORT			= 1;
	private static final int	SELECTION_SORT_IMPROVED	= 11;
	private static final int	INSERTION_SORT			= 2;
	private static final int	MERGE_SORT				= 3;
	private static final int	QUICK_SORT				= 4;

	public void testBubbleSort() {
		testSort( BUBBLE_SORT );
	}

	public void testBubbleSort2() {
		testSort( BUBBLE_SORT_IMPROVED );
	}

	public void testSelectionSort() {
		testSort( SELECTION_SORT );
	}

	// SortAlgo.selectionSortImproved
	public void testSelectionSortImproved() {
		testSort( SELECTION_SORT_IMPROVED );
	}

	public void testInsertionSort() {
		testSort( INSERTION_SORT );
	}

	public void testMergeSort() {
		testSort( MERGE_SORT );
	}

	public void testQuickSort() {
		testSort( QUICK_SORT );
	}

	// TODO: add more edge cases
	private void testSort( int sortStrategyToTest ) {
		int[] array = new int[]
			{ 45, 6, 32, 67675, 33, 54, -1, 20, -4344 };
		int size = array.length;

		array = sortArray( array, sortStrategyToTest );
		Utils.printArray( array );

		assertNotNull( array );
		assertTrue( size == array.length );

		// TODO can use Arrays.compare - will be more readable and easy
		assertTrue( array[0] == -4344 );
		assertTrue( array[1] == -1 );
		assertTrue( array[2] == 6 );
		assertTrue( array[3] == 20 );
		assertTrue( array[4] == 32 );
		assertTrue( array[5] == 33 );
		assertTrue( array[6] == 45 );
		assertTrue( array[7] == 54 );
		assertTrue( array[8] == 67675 );

		// test 2 - negative values only
		array = new int[]
			{ -1, -4344 };
		size = array.length;

		array = sortArray( array, sortStrategyToTest );
		Utils.printArray( array );

		assertNotNull( array );
		assertTrue( size == array.length );

		assertTrue( array[0] == -4344 );
		assertTrue( array[1] == -1 );

		// test 3 - one value only
		array = new int[]
			{ -1 };
		size = array.length;

		array = sortArray( array, sortStrategyToTest );
		Utils.printArray( array );

		assertNotNull( array );
		assertTrue( size == array.length );

		assertTrue( array[0] == -1 );

		// test 4 - even numbers to sort
		array = new int[]
			{ 67675, -4344, -43234, -3, 8, 23, 765, 1000 };
		size = array.length;

		array = sortArray( array, sortStrategyToTest );
		Utils.printArray( array );

		assertNotNull( array );
		assertTrue( size == array.length );

		// TODO can use Arrays.compare - will be more readable and easy
		assertTrue( array[0] == -43234 );
		assertTrue( array[1] == -4344 );
		assertTrue( array[2] == -3 );
		assertTrue( array[3] == 8 );
		assertTrue( array[4] == 23 );
		assertTrue( array[5] == 765 );
		assertTrue( array[6] == 1000 );
		assertTrue( array[7] == 67675 );

	}

	private int[] sortArray( int[] array, int sortStrategy ) {
		switch( sortStrategy )
			{
				case BUBBLE_SORT:
					return SortAlgo.bubbleSort( array );
				case BUBBLE_SORT_IMPROVED:
					return SortAlgo.bubbleSort2( array );
				case SELECTION_SORT:
					return SortAlgo.selectionSort( array );
				case SELECTION_SORT_IMPROVED:
					return SortAlgo.selectionSortImproved( array );
				case INSERTION_SORT:
					return SortAlgo.insertionSort( array );
				case MERGE_SORT:
					return MergeSort.mergeSort( array );
				case QUICK_SORT:
					QuickSort.quickSort( array );
					return array;
				default:
					throw new UnsupportedOperationException( "Given sort strategy is not supported > " + sortStrategy );
			}
	}
}
