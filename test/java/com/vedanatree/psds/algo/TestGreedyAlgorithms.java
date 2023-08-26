package com.vedanatree.psds.algo;

import static org.assertj.core.api.Assertions.assertThat;

import com.vedantatree.psds.algo.GreedyAlgorithms;

import junit.framework.TestCase;


public class TestGreedyAlgorithms extends TestCase
{

	// GreedyAlgorithms.findMaxTotalSpeedOfTandemBicycles
	public void testFindMaxTotalSpeedOfTandemBicycles()
	{
		assertThat( GreedyAlgorithms.findMaxTotalSpeedOfTandemBicycles( new int[]
		{ 3, 6, 7, 2, 1 }, new int[]
		{ 5, 5, 3, 9, 2 }, true ) ).isEqualTo( 32 );

		assertThat( GreedyAlgorithms.findMaxTotalSpeedOfTandemBicycles( new int[]
		{ 3, 3, 4, 6, 1, 2 }, new int[]
		{ 1, 2, 1, 9, 12, 3 }, false ) ).isEqualTo( 30 );

		assertThat( GreedyAlgorithms.findMaxTotalSpeedOfTandemBicycles( new int[]
		{ 3, 3, 4, 6, 1, 2, 5, 6, 34, 256, 123, 32 }, new int[]
		{ 1, 2, 1, 9, 12, 3, 1, 54, 21, 231, 32, 1 }, true ) ).isEqualTo( 816 );

		assertThat( GreedyAlgorithms.findMaxTotalSpeedOfTandemBicycles( new int[]
		{ 5 }, new int[]
		{ 1 }, false ) ).isEqualTo( 5 );

		assertThat( GreedyAlgorithms.findMaxTotalSpeedOfTandemBicycles( new int[]
		{ 3, 6, 7, 2, 1 }, new int[]
		{ 5, 5, 3, 9, 2 }, true ) ).isEqualTo( 32 );

		assertThat( GreedyAlgorithms.findMaxTotalSpeedOfTandemBicycles( new int[] {}, new int[] {}, true ) )
				.isEqualTo( 0 );
	}

	// GreedyAlgorithms.taskAssignment
	public void testTaskAssignment()
	{
		// TODO
	}

}
