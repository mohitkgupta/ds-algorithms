package com.vedantatree.psds;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;


public class Utils
{

	public static void printArray( Object[] array )
	{
		if( array == null || array.length == 0 )
		{
			System.out.println( "\n Specified array is either null or of zero length." );
			return;
		}

		for( Object element : array )
		{
			System.out.print( element + " | " );
		}
		System.out.println( "" );
	}

	public static void printArray( int[] array )
	{
		if( array == null || array.length == 0 )
		{
			System.out.println( "\n Specified array is either null or of zero length." );
			return;
		}

		for( int element : array )
		{
			System.out.print( element + " | " );
		}
		System.out.println( "" );
	}

	public static void print2DimensionalArray( int[][] array )
	{
		if( array == null || array.length == 0 )
		{
			System.out.println( "\n Specified array is either null or of zero length." );
			return;
		}

		for( int i = 0; i < array.length; i++)
		{
			System.out.print( i + " >\t" );
			for( int element : array[i] )
			{
				System.out.print( element + "\t" );
			}
			System.out.println( "\n" );
		}
		System.out.println( "" );
	}

	public static void assertListEquals( List list1, List list2 )
	{
		if( list1 == null && list2 == null )
		{
			return;
		}
		TestCase.assertEquals( "Either of the list is null", false, ( list1 == null || list2 == null ) );
		TestCase.assertEquals( "List size is not matching", true, ( list1.size() == list2.size() ) );

		Iterator<Integer> list1Iterator = list1.iterator();
		Iterator<Integer> list2Iterator = list2.iterator();

		while( list2Iterator.hasNext() )
		{
			Integer value2 = list2Iterator.next();
			Integer value1 = list1Iterator.next();

			TestCase.assertEquals( "List value does not match", value1, value2 );
		}

	}

	public static void main( String[] args )
	{
		int[] array = new int[]
		{ 58, 98, 25, 63, 47, 4, 5, 8, 9, 2 };

		printArray( array );

		printArray( new int[] {} );

	}

}
