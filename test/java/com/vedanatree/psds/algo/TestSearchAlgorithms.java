package com.vedanatree.psds.algo;

import static org.assertj.core.api.Assertions.assertThat;

import com.vedantatree.psds.algo.searchsort.SearchAlgorithms;

import junit.framework.TestCase;


public class TestSearchAlgorithms extends TestCase {

	// SearchAlgorithms.binarySearch
	public void testBinarySearch() {

		assertThat( SearchAlgorithms.binarySearch( new int[]
		{ 5, 7, 9, 12, 15 }, 7 ) ).isEqualTo( 1 );

		assertThat( SearchAlgorithms.binarySearch( new int[]
		{ 5, 7, 9, 12, 15 }, 9 ) ).isEqualTo( 2 );

		assertThat( SearchAlgorithms.binarySearch( new int[]
		{ 5, 7, 9, 12, 15 }, 12 ) ).isEqualTo( 3 );

		assertThat( SearchAlgorithms.binarySearch( new int[]
		{ 5, 7, 9, 12, 15 }, 10 ) ).isEqualTo( -1 );

		assertThat( SearchAlgorithms.binarySearch( new int[] {}, 10 ) ).isEqualTo( -1 );
	}

	// SearchAlgorithms.searchInSortedMatrix
	public void testSearchInSortedMatrix() {
		int[][] sortedMatrix = new int[][]
		{
				{ 1, 4, 7, 12, 15, 1000 },
				{ 2, 5, 19, 31, 32, 1001 },
				{ 3, 8, 24, 33, 35, 1002 },
				{ 40, 41, 42, 44, 45, 1003 },
				{ 99, 100, 103, 106, 128, 1004 } };

		int[] result = SearchAlgorithms.searchInSortedMatrix( sortedMatrix, 106 );

		assertThat( result ).isNotNull();
		assertThat( result.length ).isEqualTo( 2 );
		assertThat( result[0] ).isEqualTo( 4 );
		assertThat( result[1] ).isEqualTo( 3 );
	}

	// SearchAlgorithms.searchInSortedMatrixBinary
	public void testSearchInSortedMatrixBinary() {
		int[][] sortedMatrix = new int[][]
		{
				{ 1, 4, 7, 12, 15, 1000 },
				{ 2, 5, 19, 31, 32, 1001 },
				{ 3, 8, 24, 33, 35, 1002 },
				{ 40, 41, 42, 44, 45, 1003 },
				{ 99, 100, 103, 106, 128, 1004 } };

		int[] result = SearchAlgorithms.searchInSortedMatrixBinary( sortedMatrix, 106 );

		assertThat( result ).isNotNull();
		assertThat( result.length ).isEqualTo( 2 );
		assertThat( result[0] ).isEqualTo( 4 );
		assertThat( result[1] ).isEqualTo( 3 );

		sortedMatrix = new int[][]
		{
				{ 1, 4, 7, 12, 15, 1000 },
				{ 2, 5, 19, 31, 32, 1001 },
				{ 3, 8, 24, 33, 35, 1002 },
				{ 40, 41, 42, 44, 45, 1003 },
				{ 99, 100, 103, 106, 128, 1004 } };

		result = SearchAlgorithms.searchInSortedMatrixBinary( sortedMatrix, 1000 );

		assertThat( result ).isNotNull();
		assertThat( result.length ).isEqualTo( 2 );
		assertThat( result[0] ).isEqualTo( 0 );
		assertThat( result[1] ).isEqualTo( 5 );

	}
}
