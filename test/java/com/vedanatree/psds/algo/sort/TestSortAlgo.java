package com.vedanatree.psds.algo.sort;

import com.vedantatree.psds.Utils;
import com.vedantatree.psds.algo.sort.SortAlgo;

import junit.framework.TestCase;

public class TestSortAlgo extends TestCase {

	public void testBubbleSort() {
		int[] array = new int[] { 45, 6, 32, 67675, 33, 54, -1, 20, -4344 };
		int size = array.length;

		array = SortAlgo.bubbleSort(array);
		Utils.printArray(array);

		assertNotNull(array);
		assertTrue(size == array.length);

		assertTrue(array[0] == -4344);
		assertTrue(array[1] == -1);
		assertTrue(array[2] == 6);
		assertTrue(array[3] == 20);
		assertTrue(array[4] == 32);
		assertTrue(array[5] == 33);
		assertTrue(array[6] == 45);
		assertTrue(array[7] == 54);
		assertTrue(array[8] == 67675);
		
		// test 2 - negative values only
		array = new int[] { -1, -4344 };
		size = array.length;

		array = SortAlgo.bubbleSort(array);
		Utils.printArray(array);

		assertNotNull(array);
		assertTrue(size == array.length);

		assertTrue(array[0] == -4344);
		assertTrue(array[1] == -1);

		// test 3 - one value only
		array = new int[] { -1 };
		size = array.length;

		array = SortAlgo.bubbleSort(array);
		Utils.printArray(array);

		assertNotNull(array);
		assertTrue(size == array.length);

		assertTrue(array[0] == -1);
	}

	public void testBubbleSort2() {
		int[] array = new int[] { 45, 6, 32, 67675, 33, 54, -1, 20, -4344 };
		int size = array.length;

		array = SortAlgo.bubbleSort2(array);
		Utils.printArray(array);

		assertNotNull(array);
		assertTrue(size == array.length);

		assertTrue(array[0] == -4344);
		assertTrue(array[1] == -1);
		assertTrue(array[2] == 6);
		assertTrue(array[3] == 20);
		assertTrue(array[4] == 32);
		assertTrue(array[5] == 33);
		assertTrue(array[6] == 45);
		assertTrue(array[7] == 54);
		assertTrue(array[8] == 67675);
	}
}
