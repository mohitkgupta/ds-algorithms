package com.vedantatree.psds.ds;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;

import com.vedantatree.psds.Utils;

import junit.framework.TestCase;


public class TestGraph extends TestCase {

	// XGraph.bfsGraphTraversal
	public void testBFSGraphTraversal() {

		int[][] graphAdjacentList = new int[][]
			{ new int[]
						{ 1, 4, 5 }, new int[]
						{ 2, 3 }, new int[]
						{ 3 }, new int[]
						{ 5, 6, 7 }, new int[]
						{ 6, 7 }, new int[]
						{ 7, 8, 9, 10 } };

		Integer[] traversedNodes = XGraph.bfsGraphTraversal( graphAdjacentList, 11 ).toArray( new Integer[0] );
		Utils.printArray( traversedNodes );

		Integer[] checkList = new Integer[]
			{ 0, 1, 4, 5, 2, 3, 6, 7, 8, 9, 10 };

		assertThat( Arrays.compare( traversedNodes, checkList ) ).isZero();

	}

	// XGraph.dfsGraphTraversal
	public void testDFSGraphTraversal() {

		int[][] graphAdjacentList = new int[][]
			{ new int[]
						{ 1, 4, 5 }, new int[]
						{ 2, 3 }, new int[]
						{ 3 }, new int[]
						{ 5, 6, 7 }, new int[]
						{ 6, 7 }, new int[]
						{ 7, 8, 9, 10 } };

		Integer[] traversedNodes = XGraph.dfsGraphTraversal( graphAdjacentList, 11 ).toArray( new Integer[0] );
		Utils.printArray( traversedNodes );

		Integer[] checkList = new Integer[]
			{ 0, 1, 2, 3, 6, 7, 4, 5, 8, 9, 10 };

		assertThat( Arrays.compare( traversedNodes, checkList ) ).isZero();

	}

	public void testCheckIfCyclic() {
		/*
		 * 0 > 1
		 * 2 > 1
		 * > 3
		 * 3 > 4
		 * 4 > 2
		 */
		int[][] graphAdjacentList = new int[][]
			{ new int[]
						{ 1 }, new int[] {}, new int[]
						{ 1, 3 }, new int[]
						{ 4 }, new int[]
						{ 2 } };

		assertTrue( XGraph.checkIfCyclic( graphAdjacentList ) );

		graphAdjacentList = new int[][]
			{ new int[]
						{ 1, 4, 5 }, new int[]
						{ 2, 3 }, new int[]
						{ 1, 3 }, };

		Utils.print2DimensionalArray( graphAdjacentList );
		assertTrue( XGraph.checkIfCyclic( graphAdjacentList ) );

		graphAdjacentList = new int[][]
			{ new int[]
						{ 1, 4, 5 }, new int[]
						{ 2, 3 }, new int[]
						{ 3 }, new int[] {}, new int[] {}, new int[] {} };

		Utils.print2DimensionalArray( graphAdjacentList );
		assertFalse( XGraph.checkIfCyclic( graphAdjacentList ) );

		graphAdjacentList = new int[][]
			{ new int[]
						{ 1, 4, 5 }, new int[]
						{ 2, 3 }, new int[]
						{ 3 }, new int[]
						{ 5 }, new int[]
						{ 3 }, new int[]
						{ 1 },

			};

		Utils.print2DimensionalArray( graphAdjacentList );
		assertTrue( XGraph.checkIfCyclic( graphAdjacentList ) );
	}

}
