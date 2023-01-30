package com.vedanatree.psds.algo;

import static org.assertj.core.api.Assertions.assertThat;

import com.vedantatree.psds.Utils;
import com.vedantatree.psds.algo.ArrayAlgo;

import junit.framework.TestCase;


/**
 * @author Mohit Gupta <mohit.gupta@vedantatree.com>
 */
public class TestArrayAlgo extends TestCase {

	// ArrayAlgo.removeIslands
	public void testRemoveIslands() {

		int[][] matrix = new int[][]
			{
					{ 1, 0, 0, 0, 0, 0 },
					{ 0, 1, 0, 1, 1, 1 },
					{ 0, 0, 1, 0, 1, 0 },
					{ 1, 1, 0, 0, 1, 0 },
					{ 1, 0, 1, 1, 0, 0 },
					{ 1, 0, 0, 0, 0, 1 } };

		int[][] expectedResult = new int[][]
			{
					{ 1, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 1, 1, 1 },
					{ 0, 0, 0, 0, 1, 0 },
					{ 1, 1, 0, 0, 1, 0 },
					{ 1, 0, 0, 0, 0, 0 },
					{ 1, 0, 0, 0, 0, 1 } };

		testRemoveIslandsHelper( matrix, expectedResult );

		matrix = new int[][]
			{
					{ 1, 1, 1, 1, 1 },
					{ 1, 0, 0, 0, 1 },
					{ 1, 0, 1, 0, 1 },
					{ 1, 0, 0, 0, 1 },
					{ 1, 1, 1, 1, 1 }, };

		expectedResult = new int[][]
			{
					{ 1, 1, 1, 1, 1 },
					{ 1, 0, 0, 0, 1 },
					{ 1, 0, 0, 0, 1 },
					{ 1, 0, 0, 0, 1 },
					{ 1, 1, 1, 1, 1 }, };

		testRemoveIslandsHelper( matrix, expectedResult );
	}

	private void testRemoveIslandsHelper( int[][] matrix, int[][] expectedResult ) {

		Utils.print2DimensionalArray( matrix );
		int[][] result = ArrayAlgo.removeIslands( matrix );
		Utils.print2DimensionalArray( result );

		assertThat( result ).isNotNull();
		assertThat( result.length ).isEqualTo( matrix.length );
		assertThat( result[0] ).isNotNull();
		assertThat( result[0].length ).isEqualTo( matrix[0].length );

		for( int row = 0; row < result.length; row++ ) {
			for( int col = 0; col < result[0].length; col++ ) {
				assertThat( result[row][col] ).isEqualTo( expectedResult[row][col] );
			}
		}
	}

}
