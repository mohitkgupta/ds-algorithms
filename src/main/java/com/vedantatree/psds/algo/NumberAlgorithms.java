package com.vedantatree.psds.algo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.vedantatree.psds.Utils;

import junit.framework.TestCase;


/**
 * This class contains various algorithms related to numbers.
 * 
 * Test Class: TestNumberAlgorithms
 * 
 * @author Mohit Gupta <mohit.gupta@vedantatree.com>
 */
public class NumberAlgorithms extends TestCase {

	// TODO : Fix this function
	public static List<List<Integer>> getPermutations( List<Integer> array ) {

		List<List<Integer>> permutations = new ArrayList<>();
		permutation( new ArrayList<>(), array, permutations );

		System.out.println( "unique permutations > " + permutations );
		return permutations;
	}

	private static void permutation( List<Integer> prefixList, List<Integer> seedList,
			List<List<Integer>> permutations ) {

		if( seedList == null || seedList.size() == 0 ) {
			permutations.add( prefixList );
		}
		else {
			List<Integer> alteredSeedList = new ArrayList<>( seedList );
			List<Integer> alteredPrefixList = new ArrayList<>( prefixList );

			for( int i = 0; i < alteredSeedList.size(); i++ ) {

				prefixList.add( alteredSeedList.get( i ) );
				alteredSeedList.remove( i );

				permutation( prefixList, alteredSeedList, permutations );
			}
		}
	}

	/**
	 * Function which takes in non-empty array of distinct integers and an integer
	 * representing the target sum.
	 * 
	 * Time Complexity - O(n^2)
	 * 
	 * @param array list of elements
	 * @param targetSum
	 * @return If any two number in the array sum up to the target sum, it will return those
	 *         in an array. If no element sum up to the target sum, it will return an empty
	 *         array.
	 */
	public static int[] twoNumberSum( int[] array, int targetSum ) {
		assertNotNull( array );

		// iterate over array, on each element
		for( int i = 0; i < array.length; i++ ) {

			// iterate over rest of the elements to check if it adds up to the above current for target sum
			for( int j = i + 1; j < array.length; j++ ) {

				if( array[i] + array[j] == targetSum ) {
					return new int[]
						{ array[i], array[j] };
				}
			}
		}

		return new int[] {};
	}

	/**
	 * Given array contains the list of numbers, which could be negative or positive
	 * integers. Find the sets of three numbers, whose sum is equal to targetSum.
	 * Return all such sets, triplets in an array or array
	 * 
	 * Time Complexity: O(n^2)
	 * Space Complexity: O(n)..? because we are collecting results, which in worst case could be all the elements
	 * 
	 */
	public static List<Integer[]> threeNumberSum( int[] array, int targetSum ) {

		Arrays.sort( array );

		List<Integer[]> triplets = new ArrayList<Integer[]>();

		for( int i = 0; i < array.length; i++ ) {

			int currentNumber = array[i];
			int left = i + 1;
			int right = array.length - 1;

			while( left < right ) {

				int currentSum = currentNumber + array[left] + array[right];

				if( currentSum == targetSum ) {

					triplets.add( new Integer[]
						{ currentNumber, array[left], array[right] } );
					left++;
					right--;
				}
				else if( currentSum < targetSum ) {
					left++;
				}
				else {
					right--;
				}
			}

		}
		return triplets;
	}

	/**
	 * TODO: Not a good solution. Should find better and easy reading solution
	 * 
	 * Function to find all quadruplets from given array, which can sum up to give target sum
	 * 
	 * @param array Non-empty array of distinct integers
	 * @param targetSum
	 * @return All quadruplets summing up to target sum, an empty array otherwise
	 */
	public static List<int[]> fourNumberSum( int[] array, int targetSum ) {

		HashMap<Integer, int[]> sumToPairsMap = new HashMap<>();

		for( int i = 0; i < array.length; i++ ) {

			for( int j = i + 1; j < array.length; j++ ) {

				int pairSum = array[i] + array[j];

				sumToPairsMap.put( pairSum, new int[]
					{ array[i], array[j] } );
			}
		}

		ArrayList<int[]> fourNumbers = new ArrayList<>();
		HashSet<Integer> addedPairSum = new HashSet<>();

		sumToPairsMap.forEach( ( pairSum, pair ) -> {
			int[] otherPair = sumToPairsMap.get( targetSum - pairSum );

			if( otherPair != null && !doesArraysContainAnySameElement( pair, otherPair )
					&& !addedPairSum.contains( pairSum ) && !addedPairSum.contains( targetSum - pairSum ) ) {

				System.out.println( "targetSum[" + targetSum + "] pairSum[" + pairSum + "]" );
				Utils.printArray( pair );
				Utils.printArray( otherPair );

				if( otherPair != null ) {
					fourNumbers.add( new int[]
						{ pair[0], pair[1], otherPair[0], otherPair[1] } );

					addedPairSum.add( pairSum );
					addedPairSum.add( targetSum - pairSum );
				}
			}
		} );

		// Write your code here.
		return fourNumbers;
	}

	private static boolean doesArraysContainAnySameElement( int[] array1, int[] array2 ) {

		for( int array1Ele : array1 ) {
			for( int array2Ele : array2 ) {
				if( array1Ele == array2Ele ) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * This function checks if given sequence array elements are found in same sequence
	 * in given array
	 * 
	 * Time Complexity: O(n*m), n = length of main array, m = length of sequence array
	 * 
	 * @param array Source list of elements to check for the presence of numbers in sequence
	 * @param sequence contains elements sequence to check
	 * @return true if given sequence array elements are found in source array in
	 *         same sequence
	 */
	public static boolean isValidSubsequence( List<Integer> array, List<Integer> sequence ) {
		assertNotNull( array );
		assertNotNull( sequence );

		Iterator<Integer> sequenceIterator = sequence.iterator();
		Iterator<Integer> arrayIterator = array.iterator();

		while( sequenceIterator.hasNext() ) {
			int sequenceNumber = sequenceIterator.next();

			while( arrayIterator.hasNext() ) {

				if( arrayIterator.next() == sequenceNumber ) {
					if( !sequenceIterator.hasNext() ) { // return true if no more element left in sequence array
						return true;
					}

					break; // break from main array loop and pick next element from sequence to check
				}
			}
		}

		return false;
	}

	/**
	 * Function to return the array of square values for given array
	 * 
	 * Complexity comes by negative values in array. Because square of negative integers will be positive
	 * and then it can be larger than other elements in sequence. Sorting order will be changed after square.
	 * 
	 * One solution is to do square and then sort the array again. Which has higher time complexity.
	 * Other solution is to pick the largest number
	 * > keep comparing absolute of numbers from the starting and from the end of the array
	 * > pick largest out of these
	 * > Do square and fill it from tail end of the output array
	 * 
	 * @param array The input array. Sorted. Can contain negative values also.
	 * @return array with square value for each element in input array, sorted
	 */
	public static int[] sortedSquaredArray( int[] array ) {

		assertNotNull( array );
		assertTrue( array.length > 0 );

		int[] squaredArray = new int[array.length];
		int sqPointer = array.length - 1; // will be filled from back, from largest square

		int left = 0;
		int right = array.length - 1;
		int largestNumberToSquare;

		while( left <= right ) {

			int leftNumber = Math.abs( array[left] );
			int rightNumber = Math.abs( array[right] );

			if( leftNumber > rightNumber ) {
				largestNumberToSquare = leftNumber;
				left++;
			}
			else {
				largestNumberToSquare = rightNumber;
				right--;
			}

			squaredArray[sqPointer] = largestNumberToSquare * largestNumberToSquare;
			sqPointer--;
		}

		return squaredArray;
	}

	/**
	 * Find the winner team
	 * 
	 * Time Complexity: O(n)
	 * Space Complexity: O(n)
	 * 
	 * @param competitions The pair of teams. In pair, first team is termed as Home team
	 *        and other as Away team. Name is of max 30 char.
	 * @param results The array of winners. Values will be 1 for home team, and 0
	 *        for away team. Winning team gets 3 score. Loosing team gets zero.
	 * @return The winner team
	 */
	public static String tournamentWinner( ArrayList<ArrayList<String>> competitions, ArrayList<Integer> results ) {

		assertNotNull( competitions );
		assertNotNull( results );
		assertTrue( "Compeitions and results size must be same", competitions.size() == results.size() );

		HashMap<String, Integer> teamResults = new HashMap<String, Integer>();

		Integer highestScore = Integer.MIN_VALUE;
		String highestScorer = null;

		for( int resultIndex = 0; resultIndex < results.size(); resultIndex++ ) {

			String winningTeam = results.get( resultIndex ) == 1 ? competitions.get( resultIndex ).get( 0 )
					: competitions.get( resultIndex ).get( 1 );

			Integer winningTeamScore = teamResults.get( winningTeam );

			winningTeamScore = winningTeamScore == null ? Integer.valueOf( 3 )
					: Integer.valueOf( winningTeamScore + 3 );

			teamResults.put( winningTeam, winningTeamScore );

			if( winningTeamScore > highestScore ) {
				highestScore = winningTeamScore;
				highestScorer = winningTeam;
			}
		}

		return highestScorer;
		// return findTopScorerTeam( teamResults );
	}

	/**
	 * Given two arrays of integers, find the pair (one from each array) whose
	 * difference is the smallest. Consider that values can be negative also and
	 * difference will be in absolute terms.
	 * 
	 * Example:
	 * { -1, 5, 10, 20, 28, 3 }
	 * { 26, 134, 135, 15, 17 }
	 * 
	 * { -1, 3, 5, 10, 20, 28 }
	 * { 15, 17, 26, 134, 135 }
	 * 
	 * -1, 15 = 14
	 * 3, 15 = 12
	 * 5, 15 = 10
	 * 10, 15 = 5
	 * 20, 15 = 5
	 * 20, 17 = 3
	 * 20, 26 = 6
	 * 28, 26 = 2
	 * Return 2
	 */
	public static int[] smallestDifference( int[] arrayOne, int[] arrayTwo ) {

		assertNotNull( arrayOne );
		assertNotNull( arrayTwo );

		Arrays.sort( arrayOne );
		Arrays.sort( arrayTwo );

		int indexOne = 0;
		int indexTwo = 0;

		int[] smallestPair = new int[] {};
		int smallestDiff = Integer.MAX_VALUE;

		while( indexOne < arrayOne.length && indexTwo < arrayTwo.length ) {

			int valueOne = arrayOne[indexOne];
			int valueTwo = arrayTwo[indexTwo];
			int difference = Math.abs( valueOne - valueTwo );

			// if values are same - this is definitely the smallest difference pair
			if( difference == 0 ) {
				return new int[]
					{ valueOne, valueTwo };
			}

			// if first array value is lesser than second, smaller difference can be on right
			if( valueOne < valueTwo ) {
				indexOne++;
			}
			// if second array value is smaller, can reduce difference by picking next item from second array
			else {
				indexTwo++;
			}

			if( difference < smallestDiff ) {
				smallestPair = new int[]
					{ valueOne, valueTwo };

				smallestDiff = difference;
			}

		}
		return smallestPair;
	}

	/**
	 * Solution without division
	 * Time Complexity: O(n^2)
	 * Space: O(n)
	 * 
	 * @param array The input array of integers
	 * @return an array where each element is = to the product of all the elements in input array, other than element at
	 *         current location
	 */
	public static int[] arrayOfProducts( int[] array ) {

		assertNotNull( array );

		int[] results = new int[array.length];

		int product = 1;

		for( int index1 = 0; index1 < array.length; index1++ ) {

			for( int index2 = 0; index2 < array.length; index2++ ) {
				if( index2 == index1 ) {
					continue;
				}
				product = product * array[index2];
				if( product == 0 ) { // total product will always be zero
					break;
				}
			}

			// TODO if more than one zero elements, can break as products will always be zero then
			results[index1] = product;
			product = 1; // reset
		}

		return results;
	}

	/**
	 * Solution 2 > Product for any index = product of all left integers * product of all right integers
	 * Time Complexity: O(n)
	 * Space: O(n)
	 * 
	 * @param array The input array of integers
	 * @return an array where each element is = to the product of all the elements in input array, other than element at
	 *         current location
	 */
	public static int[] arrayOfProducts2( int[] array ) {
		assertNotNull( array );

		if( array.length == 0 ) {
			return new int[] {};
		}

		int[] results = new int[array.length];
		int[] leftProducts = new int[array.length];
		int[] rightProducts = new int[array.length];

		leftProducts[0] = 1;
		rightProducts[array.length - 1] = 1;

		for( int i = 1; i < array.length; i++ ) {
			leftProducts[i] = leftProducts[i - 1] * array[i - 1];
		}

		for( int i = array.length - 2; i >= 0; i-- ) {
			rightProducts[i] = rightProducts[i + 1] * array[i + 1];
		}

		for( int i = 0; i < array.length; i++ ) {
			results[i] = leftProducts[i] * rightProducts[i];
		}

		return results;
	}

	public int productSum( List<Object> array ) {
		return productSum( array, 1 );
	}

	private int productSum( List<Object> array, int level ) {
		int sum = 0;
		for( Object obj : array ) {
			sum += ( obj instanceof List ) ? productSum( (List) obj, level + 1 ) : (int) obj;
		}
		return sum * level;
	}

	public static int[] findThreeLargestNumbers( int[] array ) {

		int largestNumber = Integer.MIN_VALUE;
		int secondLargest = Integer.MIN_VALUE;
		int thirdLargest = Integer.MIN_VALUE;

		for( int element : array ) {

			if( element > largestNumber ) {
				thirdLargest = secondLargest;
				secondLargest = largestNumber;
				largestNumber = element;
			}
			else if( element > secondLargest ) {
				thirdLargest = secondLargest;
				secondLargest = element;
			}
			else if( element > thirdLargest ) {
				thirdLargest = element;
			}
		}

		return new int[]
			{ thirdLargest, secondLargest, largestNumber };
	}

	public static int squareRoot( int number ) {

		// TODO: can we avoid double multiplication
		for( int trial = 1; trial * trial <= number; trial++ ) {

			if( trial * trial == number ) {
				return trial;
			}
		}
		return -1;
	}

	public static int squareRootByBinaryGuessing( int number ) {
		if( number == 1 ) {
			return 1;
		}

		int trial = number / 2;

		while( true ) {
			if( trial < 1 ) {
				return -1;
			}
			if( trial * trial == number ) {
				return trial;
			}
			if( trial * trial > number ) {
				trial = trial / 2;
			}
			else if( trial * trial < number ) {
				trial = trial + 1;
			}
		}
	}

	public static long sumOfDigits( long number ) {
		long sum = 0;
		while( number > 0 ) {
			sum += number % 10;
			number = number / 10;
		}
		return sum;
	}

	public static void printPrimeNumber( int start, int end ) {
		int i = 1;
		for( ; start <= end; start++ ) {

			if( isPrimeNumber( start ) ) {
				System.out.println( i + ") " + start );
				i++;
			}
		}
	}

	public static boolean isPrimeNumber( int number ) {

		for( int i = 2; i * i <= number; i++ ) {

			if( number % i == 0 ) {
				return false;
			}
		}
		return true;
	}

	public static int calculateFactorial( int number ) {
		if( number == 0 ) {
			return 1;
		}
		return number * calculateFactorial( number - 1 );
	}

	public static void printAllFibnocci( int startIndex, int endIndex ) {
		for( ; startIndex <= endIndex; startIndex++ ) {
			System.out.println( "number[" + startIndex + "] fibnocci[" + fibnocciNumberAtIndex( startIndex ) + "]" );
		}
	}

	public static int fibnocciNumberAtIndex( int number ) {
		if( number <= 0 ) {
			return 0;
		}
		else if( number == 1 ) {
			return 1;
		}
		return fibnocciNumberAtIndex( number - 1 ) + fibnocciNumberAtIndex( number - 2 );
	}

	public static void printAllFibnocciMemoization( int startIndex, int endIndex ) {
		int[] memo = new int[( endIndex - startIndex ) + 1];

		for( ; startIndex <= endIndex; startIndex++ ) {
			System.out.println(
					"number[" + startIndex + "] fibnocci[" + printAllFibnocciMemoization( startIndex, memo ) + "]" );
		}
	}

	public static int printAllFibnocciMemoization( int number, int[] memo ) {
		if( number <= 0 ) {
			return 0;
		}
		else if( number == 1 ) {
			return 1;
		}
		else if( memo[number] > 0 ) {
			return memo[number];
		}

		memo[number] = printAllFibnocciMemoization( number - 1, memo )
				+ printAllFibnocciMemoization( number - 2, memo );
		return memo[number];
	}

	/**
	 * Function to return the first integer who has a duplicate value and the duplicate value has smallest index in
	 * array
	 * 
	 * As each value is between 1 to n
	 * We can use value of each element as indicator of duplicate
	 * 
	 * While reading each element, use its value as index and update the value of corresponding element as -ve
	 * When iterator reached to any element which has negative value, this indicates that element is duplicate
	 * Return this element
	 * 
	 * @param array of Integer from 1 to n
	 * @return the index of element, who has duplicate value in array, and the index of duplicate element is the
	 *         smallest out of all other duplicate values of all elements
	 *         -1 if array is of zero size, or if there is no duplicate
	 */
	public static int firstDuplicateValue( int[] array ) {
		assertThat( array ).isNotNull();

		if( array.length == 0 ) {
			return -1;
		}

		for( int index = 0; index < array.length; index++ ) {

			// absolute value as there could be negative value stored in earlier passes
			int valueIndex = Math.abs( array[index] ) - 1;

			if( array[valueIndex] < 0 ) {
				// increasing value before returning, because we decreased above to deduce the index
				return valueIndex + 1;
			}

			array[valueIndex] = array[valueIndex] * -1;
		}

		return -1; // no duplicate
	}

	/**
	 * Retrun true if given array has any subarray which has sum == 0
	 * subarray means, a sub-group of elements in sequence
	 * 
	 * @param nums Array of integers
	 * @return true if any sub-array has sum equal to zero
	 *         false otherwise
	 */
	public static boolean zeroSumSubarray( int[] nums ) {
		assertThat( nums ).isNotNull();
		assertThat( nums.length > 0 );

		/*
		 * keep calculating sum of all previous elements + current and store in hashset
		 * if this sum is already present in hashset, it means we already seen this and intermediate sequence of
		 * elements were amount to zero
		 * If any number is zero, or current some itself is zero > that also means we have a zero sum sub array
		 */

		HashSet<Integer> sums = new HashSet<>();
		int currentSum = 0;

		for( int number : nums ) {
			currentSum += number;
			if( number == 0 || currentSum == 0 || !sums.add( currentSum ) ) {
				return true;
			}
		}
		return false;
	}

	public static void main( String[] args ) {
		NumberAlgorithms na = new NumberAlgorithms();
		// ma.printPrimeNumber( 0, 1000 );

		// System.out.println( "factorial of 5 > " + ma.calculateFactorial( 5 ) );

		na.printAllFibnocci( 0, 10 );
		na.printAllFibnocciMemoization( 0, 10 );
	}

}
