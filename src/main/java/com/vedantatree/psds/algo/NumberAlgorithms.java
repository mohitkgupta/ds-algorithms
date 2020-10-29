package com.vedantatree.psds.algo;

import junit.framework.TestCase;


/**
 * 
 * @author Mohit Gupta <mohit.gupta@vedantatree.com>
 */
public class NumberAlgorithms extends TestCase
{

	public void testSquareRoot()
	{
		assertTrue( squareRoot( 64 ) == 8 );
		assertTrue( squareRoot( 25 ) == 5 );
		assertTrue( squareRoot( 1522756 ) == 1234 );
		assertTrue( squareRoot( 32959081 ) == 5741 );
		assertTrue( squareRoot( 9 ) == 3 );
		assertTrue( squareRoot( 1 ) == 1 );
		assertTrue( squareRoot( 0 ) == -1 );
	}

	public int squareRoot( int number )
	{
		for( int trial = 1; trial * trial <= number; trial++ )
		{
			if( trial * trial == number )
			{
				return trial;
			}
		}
		return -1;
	}

	public void testSquareRootByBinaryTrial()
	{
		assertTrue( squareRootByBinaryGuessing( 64 ) == 8 );
		assertTrue( squareRootByBinaryGuessing( 25 ) == 5 );
		assertTrue( squareRootByBinaryGuessing( 1522756 ) == 1234 );
		assertTrue( squareRootByBinaryGuessing( 32959081 ) == 5741 );
		assertTrue( squareRootByBinaryGuessing( 9 ) == 3 );
		assertTrue( squareRootByBinaryGuessing( 1 ) == 1 );
		assertTrue( squareRootByBinaryGuessing( 0 ) == -1 );
	}

	public int squareRootByBinaryGuessing( int number )
	{
		if( number == 1 )
		{
			return 1;
		}

		int trial = number / 2;
		while( true )
		{
			if( trial < 1 )
			{
				return -1;
			}
			if( trial * trial == number )
			{
				return trial;
			}
			if( trial * trial > number )
			{
				trial = trial / 2;
			}
			else if( trial * trial < number )
			{
				trial = trial + 1;
			}
		}
	}

	public void testSumOfDigits()
	{
		assertTrue( sumOfDigits( 15432 ) == 15 );
		assertTrue( sumOfDigits( 6894365 ) == 41 );
		assertTrue( sumOfDigits( 9425752 ) == 34 );
		assertTrue( sumOfDigits( Long.valueOf( "55459875621789" ) ) == 81 );
		assertTrue( sumOfDigits( 524 ) == 11 );
	}

	public long sumOfDigits( long number )
	{
		long sum = 0;
		while( number > 0 )
		{
			sum += number % 10;
			number = number / 10;
		}
		return sum;
	}

	public void testPrimeNumber()
	{
		assertTrue( "Number 11 is prime", isPrimeNumber( 11 ) );
		assertFalse( "Number 12 is not prime", isPrimeNumber( 12 ) );
		assertFalse( "Number 96 is not prime", isPrimeNumber( 96 ) );
		assertTrue( "Number 113 is prime", isPrimeNumber( 113 ) );
	}

	public void printPrimeNumber( int start, int end )
	{
		int i = 1;
		for( ; start <= end; start++ )
		{
			if( isPrimeNumber( start ) )
			{
				System.out.println( i + ") " + start );
				i++;
			}
		}
	}

	public boolean isPrimeNumber( int number )
	{
		for( int i = 2; i * i <= number; i++ )
		{
			if( number % i == 0 )
			{
				return false;
			}
		}
		return true;
	}

	public void testFactorial()
	{
		int number = 872;
		int loopFactorial = 1;
		for( int i = 1; i < number; i++ )
		{
			loopFactorial = loopFactorial * i;
		}

		assertTrue( loopFactorial == calculateFactorial( number ) );
	}

	public int calculateFactorial( int number )
	{
		if( number == 0 )
		{
			return 1;
		}
		return number * calculateFactorial( number - 1 );
	}

	public void printAllFibnocci( int startIndex, int endIndex )
	{
		for( ; startIndex <= endIndex; startIndex++ )
		{
			System.out.println( "number[" + startIndex + "] fibnocci[" + fibnocciNumberAtIndex( startIndex ) + "]" );
		}
	}

	public int fibnocciNumberAtIndex( int number )
	{
		if( number <= 0 )
		{
			return 0;
		}
		else if( number == 1 )
		{
			return 1;
		}
		return fibnocciNumberAtIndex( number - 1 ) + fibnocciNumberAtIndex( number - 2 );
	}

	public void printAllFibnocciMemoization( int startIndex, int endIndex )
	{
		int[] memo = new int[( endIndex - startIndex ) + 1];

		for( ; startIndex <= endIndex; startIndex++ )
		{
			System.out.println(
					"number[" + startIndex + "] fibnocci[" + printAllFibnocciMemoization( startIndex, memo ) + "]" );
		}
	}

	public int printAllFibnocciMemoization( int number, int[] memo )
	{
		if( number <= 0 )
		{
			return 0;
		}
		else if( number == 1 )
		{
			return 1;
		}
		else if( memo[number] > 0 )
		{
			return memo[number];
		}

		memo[number] = printAllFibnocciMemoization( number - 1, memo )
				+ printAllFibnocciMemoization( number - 2, memo );
		return memo[number];
	}

	public static void main( String[] args )
	{
		NumberAlgorithms na = new NumberAlgorithms();
		// ma.printPrimeNumber( 0, 1000 );

		// System.out.println( "factorial of 5 > " + ma.calculateFactorial( 5 ) );

		na.printAllFibnocci( 0, 10 );
		na.printAllFibnocciMemoization( 0, 10 );
	}

}
