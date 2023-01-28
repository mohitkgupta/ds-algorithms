package com.vedantatree.psds.algo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


public class GreedyAlgorithms
{

	/**
	 * Function to calculate the max or min possible speed of tandem cycle players
	 * 
	 * @param redShirtPlayersSpeed Array of Speeds of red shirt player
	 * @param blueShirtPlayersSpeed Array of Speeds of blue shirt player
	 * @param fastest > true if function should calculate maximum possible speed, false if minimum possible speed
	 * @return Maximum or minimum possible speed, based on the fastest input parameter
	 */
	public static int findMaxTotalSpeedOfTandemBicycles( int[] redShirtPlayersSpeed, int[] blueShirtPlayersSpeed,
			boolean fastest )
	{
		assertThat( redShirtPlayersSpeed ).isNotNull();
		assertThat( blueShirtPlayersSpeed ).isNotNull();
		assertThat( redShirtPlayersSpeed.length ).isEqualTo( blueShirtPlayersSpeed.length );

		Arrays.sort( redShirtPlayersSpeed );
		Arrays.sort( blueShirtPlayersSpeed );

		int totalSpeed = 0;
		int numberOfPlayers = redShirtPlayersSpeed.length;

		for( int i = 0; i < numberOfPlayers; i++ )
		{
			totalSpeed += Math.max( redShirtPlayersSpeed[i],
					( fastest ? blueShirtPlayersSpeed[numberOfPlayers - ( i + 1 )] : blueShirtPlayersSpeed[i] ) );
		}

		return totalSpeed;
	}

	/**
	 * @param k number of workers
	 * @param tasks list of tasks duration, it is double of number of workers i.e. k * 2
	 * @return list of pairs of tasks indices from tasks list, which if each worker does, then all the tasks can be
	 *         finished earliest.
	 */
	public ArrayList<ArrayList<Integer>> taskAssignment( int k, ArrayList<Integer> tasks )
	{
		if( k == 0 )
		{
			return null;
		}

		assertThat( k ).isGreaterThan( 0 );
		assertThat( tasks ).isNotNull();
		assertThat( tasks.size() ).isEqualTo( k * 2 );

		ArrayList<Integer> originalIndices = new ArrayList<>( k * 2 );
		for( int i = 0; i < k * 2; i++ )
		{
			originalIndices.add( i );
		}

		Collections.sort( originalIndices, Comparator.comparingInt( tasks::get ) );

		ArrayList<ArrayList<Integer>> assignedTasks = new ArrayList<>();
		int frontCounter = 0;
		int rearCounter = tasks.size() - 1;

		while( frontCounter < rearCounter )
		{
			ArrayList<Integer> workerTasks = new ArrayList<>();
			workerTasks.add( originalIndices.get( frontCounter ) );
			workerTasks.add( originalIndices.get( rearCounter ) );

			assignedTasks.add( workerTasks );
			frontCounter++;
			rearCounter--;
		}

		return assignedTasks;
	}

}
