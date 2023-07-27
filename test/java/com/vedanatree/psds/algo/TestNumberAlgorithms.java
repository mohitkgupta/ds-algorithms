package com.vedanatree.psds.algo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vedantatree.psds.Utils;
import com.vedantatree.psds.algo.NumberAlgorithms;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;


public class TestNumberAlgorithms extends TestCase {

	// NumberAlgorithms.twoNumberSum
	public void testTwoNumberSum() {
		int[] input = new int[]
			{ 3, 5, -4, 8, 11, 1, -1, 6 };
		int targetSum = 10;

		int[] result = NumberAlgorithms.twoNumberSum( input, targetSum );

		assertNotNull( result );
		assertTrue( "Result size should be 2, as two numbers are summing up to 10", result.length == 2 );
		assertTrue( result[0] == 11 );
		assertTrue( result[1] == -1 );

		input = new int[]
			{ -3, -5, -4, -8, -11, -1, -1, -6 };
		targetSum = -10;

		result = NumberAlgorithms.twoNumberSum( input, targetSum );

		assertNotNull( result );
		assertTrue( "Result size should be 2, as two numbers are summing up to -10", result.length == 2 );
		assertTrue( result[0] == -4 );
		assertTrue( result[1] == -6 );

		input = new int[]
			{ -3, -5, -4, -8, -11, -1, -1, -6 };
		targetSum = -1;

		result = NumberAlgorithms.twoNumberSum( input, targetSum );

		assertNotNull( result );
		assertEquals( "Result size should be 0, as no number is summing upto -13", 0, result.length );

		input = new int[] {};
		targetSum = -13;

		result = NumberAlgorithms.twoNumberSum( input, targetSum );

		assertNotNull( result );
		assertEquals( "Result size should be 0, as no number is summing upto -13", 0, result.length );

		input = null;
		targetSum = Integer.MIN_VALUE;

		boolean assertionTriggered = false;
		try {
			result = NumberAlgorithms.twoNumberSum( input, targetSum );
		}
		catch( AssertionFailedError afe ) {
			assertionTriggered = true;
		}

		assertTrue( "Assertion failure is expected due to Illegal arguments", assertionTriggered );
	}

	public void testFourNumberSum() {
		int[] input = new int[]
			{ 7, 6, 4, -1, 1, 2 };
		int targetSum = 16;

		List<Integer[]> quadruplets = NumberAlgorithms.fourNumberSum( input, targetSum );

		assertThat( quadruplets ).isNotNull();
		assertThat( quadruplets.size() ).isEqualTo( 2 );
		assertThat( quadruplets.get( 0 ).length ).isEqualTo( 4 );
		assertThat( quadruplets.get( 1 ).length ).isEqualTo( 4 );

		assertThat( Arrays.compare( quadruplets.get( 0 ), new Integer[]
			{ 7, 6, 4, -1 } ) ).isZero();
		assertThat( Arrays.compare( quadruplets.get( 1 ), new Integer[]
			{ 7, 6, 1, 2 } ) ).isZero();
	}

	// NumberAlgorithms.isValidSubsequence
	public void testIsValidSubsequence() {
		List<Integer> array = Arrays.asList( 5, 1, 22, 25, 6, -1, 8, 10 );
		List<Integer> sequence = Arrays.asList( 1, 6, -1, 10 );

		boolean result = NumberAlgorithms.isValidSubsequence( array, sequence );

		assertTrue( "Expected result > true", result );

		array = Arrays.asList( 5, 22, 25, 6, -1, 8, 10 );
		sequence = Arrays.asList( 1, 6, -1, 10 );

		result = NumberAlgorithms.isValidSubsequence( array, sequence );

		assertTrue( "Expected result > false", !result );

		boolean assertionTriggered = false;
		try {
			result = NumberAlgorithms.isValidSubsequence( null, null );
		}
		catch( AssertionFailedError ex ) {
			assertionTriggered = true;
		}

		assertTrue( "Assertion failure is expected due to Illegal arguments", assertionTriggered );
	}

	// NumberAlgorithms.sortedSquaredArray
	public void testSortedSquaredArray() {
		int[] arrayToSquare = new int[]
			{ -5, 3, 6, 8, 10 };

		int[] squaredArray = NumberAlgorithms.sortedSquaredArray( arrayToSquare );

		Utils.printArray( squaredArray );

		assertNotNull( "squared array must not be null", squaredArray );
		assertTrue( squaredArray.length == arrayToSquare.length );

		assertTrue( squaredArray[0] == 9 );
		assertTrue( squaredArray[1] == 25 );
		assertTrue( squaredArray[2] == 36 );
		assertTrue( squaredArray[3] == 64 );
		assertTrue( squaredArray[4] == 100 );

		boolean assertionTriggered = false;
		try {
			NumberAlgorithms.sortedSquaredArray( null );
		}
		catch( AssertionFailedError ex ) {
			assertionTriggered = true;
		}

		assertTrue( "Assertion failure is expected due to Illegal arguments", assertionTriggered );

	}

	// NumberAlgorithms.tournamentWinner
	public void testTournamentWinner() {
		ArrayList<ArrayList<String>> competitions = new ArrayList<>();

		ArrayList<String> competingTeams = new ArrayList<>();

		competingTeams.add( "A" );
		competingTeams.add( "B" );
		competitions.add( competingTeams );

		competingTeams = new ArrayList<>();
		competingTeams.add( "B" );
		competingTeams.add( "C" );
		competitions.add( competingTeams );

		competingTeams = new ArrayList<>();
		competingTeams.add( "C" );
		competingTeams.add( "A" );
		competitions.add( competingTeams );

		competingTeams = new ArrayList<>();
		competingTeams.add( "B" );
		competingTeams.add( "A" );
		competitions.add( competingTeams );

		competingTeams = new ArrayList<>();
		competingTeams.add( "A" );
		competingTeams.add( "C" );
		competitions.add( competingTeams );

		competingTeams = new ArrayList<>();
		competingTeams.add( "C" );
		competingTeams.add( "A" );
		competitions.add( competingTeams );

		ArrayList<Integer> results = new ArrayList<>();
		results.add( 0 );
		results.add( 0 );
		results.add( 1 );
		results.add( 1 );
		results.add( 0 );
		results.add( 1 );

		String winningTeam = NumberAlgorithms.tournamentWinner( competitions, results );

		assertEquals( "Winning team must be C", "C", winningTeam );

		// test with null parameter
		boolean assertionTriggered = false;
		try {
			winningTeam = NumberAlgorithms.tournamentWinner( null, results );
		}
		catch( AssertionFailedError ex ) {
			assertionTriggered = true;
		}

		assertTrue( "Assertion failure is expected due to Illegal arguments", assertionTriggered );

		// test with unequal input arrays
		assertionTriggered = false;
		try {
			results.remove( 0 );
			winningTeam = NumberAlgorithms.tournamentWinner( competitions, results );
		}
		catch( AssertionFailedError ex ) {
			assertionTriggered = true;
		}

		assertTrue( "Assertion failure is expected due to Illegal arguments", assertionTriggered );

	}

	// NumberAlgorithms.smallestDifference
	public void testSmallestDifference() {
		int[] pair = NumberAlgorithms.smallestDifference( new int[]
			{ -1, 5, 10, 20, 28, 3 }, new int[]
			{ 26, 134, 135, 15, 17 } );

		assertTrue( pair[0] == 28 && pair[1] == 26 );

		pair = NumberAlgorithms.smallestDifference( new int[]
			{ -1, 5, 10, 20, 28, 3 }, new int[] {} );
		assertTrue( pair.length == 0 );
	}

	public void testThreeNumberSum() {
		List<Integer[]> results = NumberAlgorithms.threeNumberSum( new int[]
			{ 12, 3, 1, 2, -6, 5, -8, 6 }, 0 );

		assertNotNull( results );
		assertTrue( results.size() == 3 );

		assertTrue( results.get( 0 ).length == 3 );
		assertTrue( results.get( 0 )[0] == -8 && results.get( 0 )[1] == 2 && results.get( 0 )[2] == 6 );

		assertTrue( results.get( 1 ).length == 3 );
		assertTrue( results.get( 1 )[0] == -8 && results.get( 1 )[1] == 3 && results.get( 1 )[2] == 5 );

		assertTrue( results.get( 2 ).length == 3 );
		assertTrue( results.get( 2 )[0] == -6 && results.get( 2 )[1] == 1 && results.get( 2 )[2] == 5 );

		results = NumberAlgorithms.threeNumberSum( new int[]
			{ 12, -8, -4 }, 0 );

		assertNotNull( results );
		assertTrue( results.size() == 1 );

		assertTrue( results.get( 0 ).length == 3 );
		assertTrue( results.get( 0 )[0] == -8 && results.get( 0 )[1] == -4 && results.get( 0 )[2] == 12 );
	}

	// NumberAlgorithms.arrayOfProducts
	public void testArrayOfProducts() {
		int[] results = NumberAlgorithms.arrayOfProducts( new int[]
			{ 5, 1, 4, 2 } );

		assertNotNull( results );
		assertTrue( results.length == 4 );

		Utils.printArray( results );

		assertTrue( results[0] == 8 && results[1] == 40 && results[2] == 10 && results[3] == 20 );

		results = NumberAlgorithms.arrayOfProducts( new int[] {} );

		assertNotNull( results );
		assertTrue( results.length == 0 );
	}

	// NumberAlgorithms.arrayOfProducts2
	public void testArrayOfProducts2() {
		int[] results = NumberAlgorithms.arrayOfProducts2( new int[]
			{ 5, 1, 4, 2 } );

		assertNotNull( results );
		assertTrue( results.length == 4 );

		Utils.printArray( results );

		assertTrue( results[0] == 8 && results[1] == 40 && results[2] == 10 && results[3] == 20 );

		results = NumberAlgorithms.arrayOfProducts2( new int[] {} );

		assertNotNull( results );
		assertTrue( results.length == 0 );
	}

	public void testThreeLargestNumbers() {
		assertNotNull( NumberAlgorithms.findThreeLargestNumbers( new int[]
			{ 5, 8, 9, 23, 34, 34, 56 } )[2] == 56 );
	}

	public void testSquareRoot() {
		assertTrue( NumberAlgorithms.squareRoot( 64 ) == 8 );
		assertTrue( NumberAlgorithms.squareRoot( 25 ) == 5 );
		assertTrue( NumberAlgorithms.squareRoot( 1522756 ) == 1234 );
		assertTrue( NumberAlgorithms.squareRoot( 32959081 ) == 5741 );
		assertTrue( NumberAlgorithms.squareRoot( 9 ) == 3 );
		assertTrue( NumberAlgorithms.squareRoot( 1 ) == 1 );
		assertTrue( NumberAlgorithms.squareRoot( 0 ) == -1 );
	}

	public void testSquareRootByBinaryTrial() {
		assertTrue( NumberAlgorithms.squareRootByBinaryGuessing( 64 ) == 8 );
		assertTrue( NumberAlgorithms.squareRootByBinaryGuessing( 25 ) == 5 );
		assertTrue( NumberAlgorithms.squareRootByBinaryGuessing( 1522756 ) == 1234 );
		assertTrue( NumberAlgorithms.squareRootByBinaryGuessing( 32959081 ) == 5741 );
		assertTrue( NumberAlgorithms.squareRootByBinaryGuessing( 9 ) == 3 );
		assertTrue( NumberAlgorithms.squareRootByBinaryGuessing( 1 ) == 1 );
		assertTrue( NumberAlgorithms.squareRootByBinaryGuessing( 0 ) == -1 );
	}

	public void testSumOfDigits() {
		assertTrue( NumberAlgorithms.sumOfDigits( 15432 ) == 15 );
		assertTrue( NumberAlgorithms.sumOfDigits( 6894365 ) == 41 );
		assertTrue( NumberAlgorithms.sumOfDigits( 9425752 ) == 34 );
		assertTrue( NumberAlgorithms.sumOfDigits( Long.valueOf( "55459875621789" ) ) == 81 );
		assertTrue( NumberAlgorithms.sumOfDigits( 524 ) == 11 );
	}

	public void testPrimeNumber() {
		assertTrue( "Number 11 is prime", NumberAlgorithms.isPrimeNumber( 11 ) );
		assertFalse( "Number 12 is not prime", NumberAlgorithms.isPrimeNumber( 12 ) );
		assertFalse( "Number 96 is not prime", NumberAlgorithms.isPrimeNumber( 96 ) );
		assertTrue( "Number 113 is prime", NumberAlgorithms.isPrimeNumber( 113 ) );
	}

	public void testFactorial() {
		int number = 872;
		int loopFactorial = 1;
		for( int i = 1; i < number; i++ ) {
			loopFactorial = loopFactorial * i;
		}

		assertTrue( loopFactorial == NumberAlgorithms.calculateFactorial( number ) );
	}

	// NumberAlgorithms.firstDuplicateValue
	public void testFirstDuplicateValue() {
		int[] array = new int[]
			{ 2, 1, 5, 2, 3, 3, 4 };
		int firstDupicate = NumberAlgorithms.firstDuplicateValue( array );
		assertThat( firstDupicate ).isEqualTo( 2 );

		array = new int[]
			{ 2, 1, 5, 3, 3, 2, 4 };
		firstDupicate = NumberAlgorithms.firstDuplicateValue( array );
		assertThat( firstDupicate ).isEqualTo( 3 );

		array = new int[]
			{ 1, 1, 2, 3, 3, 2, 2 };
		firstDupicate = NumberAlgorithms.firstDuplicateValue( array );
		assertThat( firstDupicate ).isEqualTo( 1 );

		array = new int[]
			{ 3, 1, 3, 1, 1, 4, 4 };
		firstDupicate = NumberAlgorithms.firstDuplicateValue( array );
		assertThat( firstDupicate ).isEqualTo( 3 );

		array = new int[] {};
		firstDupicate = NumberAlgorithms.firstDuplicateValue( array );
		assertThat( firstDupicate ).isEqualTo( -1 );

		array = new int[]
			{ 1 };
		firstDupicate = NumberAlgorithms.firstDuplicateValue( array );
		assertThat( firstDupicate ).isEqualTo( -1 );

		array = new int[]
			{ 1, 1 };
		firstDupicate = NumberAlgorithms.firstDuplicateValue( array );
		assertThat( firstDupicate ).isEqualTo( 1 );

		array = new int[]
			{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10 };
		firstDupicate = NumberAlgorithms.firstDuplicateValue( array );
		assertThat( firstDupicate ).isEqualTo( 10 );

		array = new int[]
			{ 2, 1, 1 };
		firstDupicate = NumberAlgorithms.firstDuplicateValue( array );
		assertThat( firstDupicate ).isEqualTo( 1 );

		array = new int[]
			{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
		firstDupicate = NumberAlgorithms.firstDuplicateValue( array );
		assertThat( firstDupicate ).isEqualTo( 2 );

	}

}
