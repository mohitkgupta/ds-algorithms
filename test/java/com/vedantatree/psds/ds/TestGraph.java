package com.vedantatree.psds.ds;

import com.vedantatree.psds.Utils;

import junit.framework.TestCase;


public class TestGraph extends TestCase
{

	public void testCheckIfCyclic()
	{
		/* 
		 * 0 > 1
		 * 2 > 1
		 * > 3
		 * 3 > 4
		 * 4 > 2
		 */
		int[][] graphAdjacentList = new int[][] {
			new int[] {1}, 
			new int[] {},
			new int[] {1, 3},
			new int[] {4},
			new int[] {2}
		};
		
		assertTrue( XGraph.checkIfCyclic( graphAdjacentList));
		
		graphAdjacentList = new int[][] {
			new int[] {1, 4, 5}, 
			new int[] {2, 3},
			new int[] {1, 3},
		};
		
		Utils.print2DimensionalArray( graphAdjacentList );
		assertTrue( XGraph.checkIfCyclic( graphAdjacentList));
		
		graphAdjacentList = new int[][] {
			new int[] {1, 4, 5}, 
			new int[] {2, 3},
			new int[] {3},
			new int[] {},
			new int[] {},
			new int[] {}
		};
		
		Utils.print2DimensionalArray( graphAdjacentList );
		assertFalse( XGraph.checkIfCyclic( graphAdjacentList));

		graphAdjacentList = new int[][] {
			new int[] {1, 4, 5}, 
			new int[] {2, 3},
			new int[] {3},
			new int[] {5},
			new int[] {3},
			new int[] {1},

		};
		
		Utils.print2DimensionalArray( graphAdjacentList );
		assertTrue( XGraph.checkIfCyclic( graphAdjacentList));
}

}
