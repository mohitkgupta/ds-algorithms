package com.vedantatree.psds.algo.sort;

import static org.junit.Assert.assertNotNull;

import com.vedantatree.psds.Utils;


public class MergeSort
{

	public static void mergeSort( int[] array )
	{
		assertNotNull( array );

		int[] helper = new int[array.length];
		mergeSort( array, helper, 0, array.length - 1 );
	}

	private static void mergeSort( int[] array, int[] helper, int low, int high )
	{
		if( low < high )
		{
			int middle = low + ( high - low ) / 2;
			System.out.println( "mergeSort. low: " + low + " middle: " + middle + "  high:" + high );
			mergeSort( array, helper, low, middle ); // sort left half
			mergeSort( array, helper, middle + 1, high ); // sort right half
			merge( array, helper, low, middle, high );
		}
	}

	private static void merge( int[] array, int[] helper, int low, int middle, int high )
	{
		System.out.println( "merge. low[" + low + "] middle[" + middle + "] high[" + high + "]" );

		// copy array data in helper array
		// System.arraycopy( array, low, helper, low, high );
		for( int i = low; i <= high; i++ )
		{
			helper[i] = array[i];
		}

		int helperLeft = low;
		int helperRight = middle + 1;
		int current = low;

		System.out.println( "merge-start. left: " + helperLeft + " right: " + helperRight + "  current:" + current );
		while( helperLeft <= middle && helperRight <= high )
		{
			if( helper[helperLeft] <= helper[helperRight] )
			{
				System.out.println( "replacing. current: " + array[current] + " helper-left: " + helper[helperLeft] );
				array[current] = helper[helperLeft];
				helperLeft++;
			}
			else
			{
				System.out.println( "replacing. current: " + array[current] + " helper-right: " + helper[helperLeft] );
				array[current] = helper[helperRight];
				helperRight++;
			}
			current++;
		}

		int remaining = middle - helperLeft;
		System.out.println( "remaining: " + remaining );
		Utils.printArray( array );
		Utils.printArray( helper );
		for( int i = 0; i < remaining; i++ )
		{
			array[current + i] = helper[helperLeft + i];
		}

	}

	public static void main( String[] args )
	{
		int[] array = new int[]
		{ 5, 34, 42, 35 };
		// { 5, 34, 76, 35, 12, 87, 8, 36, 86, 57, 2, 12, 0, 98 };
		mergeSort( array );
		System.out.println( "\n \n Sorted Array >> " );
		Utils.printArray( array );

	}

}
