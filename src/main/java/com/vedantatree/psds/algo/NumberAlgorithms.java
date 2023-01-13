package com.vedantatree.psds.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.vedantatree.psds.Utils;

import junit.framework.TestCase;

/**
 * This class contains various algorithms related to numbers.
 * Test Class: TestNumberAlgorithms
 * 
 * @author Mohit Gupta <mohit.gupta@vedantatree.com>
 */
public class NumberAlgorithms extends TestCase {

	/**
	 * Function which takes in non-empty array of distinct integers and an integer
	 * representing the target sum.
	 * 
	 * If any two number in the array sum up to the target sum, it will return those
	 * in an array If no element sum up to the target sum, it will return an empty
	 * array.
	 * 
	 * @param array     list of elements
	 * @param targetSum
	 * @return
	 */
	public static int[] twoNumberSum(int[] array, int targetSum) {

		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] + array[j] == targetSum) {
					return new int[] { array[i], array[j] };
				}
			}
		}

		return new int[] {};
	}

	/**
	 * This function checks if given sequence array elements are found in first
	 * parameter array in same sequence
	 * 
	 * @param array    Source array to check for the elements
	 * @param sequence contains elements sequence to check
	 * @return true if given sequence array elements are found in source array in
	 *         same sequence
	 */
	public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {

		Iterator<Integer> sequenceIterator = sequence.iterator();
		Iterator<Integer> arrayIterator = array.iterator();

		int sequenceNumber;

		// iterate over sequence. For each element, check if it present in array
		while (sequenceIterator.hasNext()) {
			sequenceNumber = sequenceIterator.next();

			while (arrayIterator.hasNext()) {
				if (arrayIterator.next() == sequenceNumber) {
					if (!sequenceIterator.hasNext()) {
						// return true if no more element left in sequence array to check
						return true;
					}

					// break and pick next element to check
					break;
				}
			}
		}

		return false;
	}

	/**
	 * Function to return the array of square values for given array
	 * 
	 * @param array input array, sorted
	 * @return array with square value for each element in input array, sorted
	 */
	public static int[] sortedSquaredArray(int[] array) {

		assertNotNull(array);
		assertTrue(array.length > 0);

		// array to collect the squared value
		int[] squaredArray = new int[array.length];

		// pointer from front of the array
		int smallestPointer = 0;

		// pointer from rear end of the array
		int largestPointer = array.length - 1;

		// pointer to fill in the square in new array
		int squaredArrayPointer = array.length - 1;

		int largestNumberToSquare;
		int smallestNumber;
		int largestNumber;

		/*
		 * compare the absolute values of first and last item in the array Motive is to
		 * find the largest item in the array, do the square and fill in the new array
		 * 
		 * Why the first item can be larger when array is sorted This is because there
		 * could be negative values
		 */
		while (smallestPointer <= largestPointer) {
			smallestNumber = Math.abs(array[smallestPointer]);
			largestNumber = Math.abs(array[largestPointer]);

			if (smallestNumber > largestNumber) {
				largestNumberToSquare = smallestNumber;
				smallestPointer++;
			} else {
				largestNumberToSquare = largestNumber;
				largestPointer--;
			}

			squaredArray[squaredArrayPointer] = largestNumberToSquare * largestNumberToSquare;
			squaredArrayPointer--;
		}

		return squaredArray;
	}

	/**
	 * @param competitions pair of teams. In pair, first team is termed as Home team
	 *                     and other as Away team. Name is of max 30 char
	 * @param results      array of winners. Values will be 1 for home team, and 0
	 *                     for away team.
	 * @return The winner team
	 */
	public static String tournamentWinner(ArrayList<ArrayList<String>> competitions, ArrayList<Integer> results) {

		assertNotNull(competitions);
		assertNotNull(results);

		assertTrue("Compeitions and results size must be same", competitions.size() == results.size());

		HashMap<String, Integer> teamResults = new HashMap<String, Integer>();

		// collect the score of each team in a map.
		// later iterate over the map and find the team with highest score

		for (int resultIndex = 0; resultIndex < results.size(); resultIndex++) {
			
			String winningTeam = results.get(resultIndex) == 1 ? competitions.get(resultIndex).get(0)
					: competitions.get(resultIndex).get(1);

			Integer winningTeamScore = teamResults.get(winningTeam);
			winningTeamScore = winningTeamScore == null ? Integer.valueOf(1) : Integer.valueOf(winningTeamScore + 1);

			teamResults.put(winningTeam, winningTeamScore);
		}

		return findTopScorerTeam(teamResults);
	}

	/**
	 * Return the top scorer team
	 * 
	 * @param teamsScore scores of teams, where key is the team name and value is
	 *                   the total score
	 * @return team with highest score
	 */
	private static String findTopScorerTeam(HashMap<String, Integer> teamsScore) {
		System.out.println(teamsScore);

		Iterator<Entry<String, Integer>> iterator = teamsScore.entrySet().iterator();

		String highestScorer = null;
		Integer highestScore = 0;

		while (iterator.hasNext()) {
			Entry<String, Integer> teamScore = iterator.next();

			Integer score = teamScore.getValue();

			if (score > highestScore) {
				highestScore = score;
				highestScorer = teamScore.getKey();
			}
		}
		return highestScorer;
	}

	/**
	 * Given two arrays of integers, find the pair (one from each array) whose
	 * difference is the smallest. Consider that values can be negative also and
	 * difference will be in absolute terms.
	 */
	public static int[] smallestDifference(int[] arrayOne, int[] arrayTwo) {

		// sort the array, as it will optimized the logic to look for pair forward or
		// backward

		Arrays.sort(arrayOne);
		Arrays.sort(arrayTwo);

		int indexOne = 0;
		int indexTwo = 0;

		int[] smallestPair = new int[] {};
		int smallestDiff = Integer.MAX_VALUE;

		// iterate over both the arrays
		while (indexOne < arrayOne.length && indexTwo < arrayTwo.length) {

			int valueOne = arrayOne[indexOne];
			int valueTwo = arrayTwo[indexTwo];
			int difference = Math.abs(valueOne - valueTwo);

			// if values are same - this is definitely the smallest difference pair
			if (difference == 0) {
				return new int[] { valueOne, valueTwo };
			}
			// if first array value is lesser than second, we should be able to get smaller
			// difference by moving towards next element in first array
			if (valueOne < valueTwo) {
				indexOne++;
			}
			// if second array value is smaller, the way to reduce the difference would be
			// to pick next item ie larger value from second array
			else {
				indexTwo++;
			}

			// keep storing the smallest diff and pair so far
			if (difference < smallestDiff) {
				smallestPair = new int[] { valueOne, valueTwo };
				smallestDiff = difference;
			}

		}
		return smallestPair;
	}

	/**
	 * Given array contains the list of numbers, which could be negative or positive
	 * integers. Find the sets of three numbers, whose sum is equal to targetSum.
	 * Return all such sets, triplets in an array or array
	 * 
	 */
	public static List<Integer[]> threeNumberSum(int[] array, int targetSum) {

		Arrays.sort(array);
		List<Integer[]> triplets = new ArrayList<Integer[]>();

		for (int i = 0; i < array.length; i++) {

			int currentNumber = array[i];
			int left = i + 1;
			int right = array.length - 1;

			while (left < right) {
				int currentSum = currentNumber + array[left] + array[right];
				if (currentSum == targetSum) {
					triplets.add(new Integer[] { currentNumber, array[left], array[right] });
					left++;
					right--;
				} else if (currentSum < targetSum) {
					left++;
				} else {
					right--;
				}
			}

		}

		return triplets;
	}

	public int productSum(List<Object> array) {
		return productSum(array, 1);
	}

	private int productSum(List<Object> array, int level) {
		int sum = 0;
		for (Object obj : array) {
			sum += (obj instanceof List) ? productSum((List) obj, level + 1) : (int) obj;
		}
		return sum * level;
	}

	public static int[] findThreeLargestNumbers(int[] array) {

		int largestNumber = Integer.MIN_VALUE;
		int secondLargest = Integer.MIN_VALUE;
		int thirdLargest = Integer.MIN_VALUE;

		for (int element : array) {

			if (element > largestNumber) {
				thirdLargest = secondLargest;
				secondLargest = largestNumber;
				largestNumber = element;
			} else if (element > secondLargest) {
				thirdLargest = secondLargest;
				secondLargest = element;
			} else if (element > thirdLargest) {
				thirdLargest = element;
			}

			System.out.println("i > " + element + ", largest > " + largestNumber + ", second > " + secondLargest
					+ ". third > " + thirdLargest);
		}

		return new int[] { thirdLargest, secondLargest, largestNumber };
	}

	public static int squareRoot(int number) {
		for (int trial = 1; trial * trial <= number; trial++) {
			if (trial * trial == number) {
				return trial;
			}
		}
		return -1;
	}

	public static int squareRootByBinaryGuessing(int number) {
		if (number == 1) {
			return 1;
		}

		int trial = number / 2;
		while (true) {
			if (trial < 1) {
				return -1;
			}
			if (trial * trial == number) {
				return trial;
			}
			if (trial * trial > number) {
				trial = trial / 2;
			} else if (trial * trial < number) {
				trial = trial + 1;
			}
		}
	}

	public static long sumOfDigits(long number) {
		long sum = 0;
		while (number > 0) {
			sum += number % 10;
			number = number / 10;
		}
		return sum;
	}

	public static void printPrimeNumber(int start, int end) {
		int i = 1;
		for (; start <= end; start++) {
			if (isPrimeNumber(start)) {
				System.out.println(i + ") " + start);
				i++;
			}
		}
	}

	public static boolean isPrimeNumber(int number) {
		for (int i = 2; i * i <= number; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static int calculateFactorial(int number) {
		if (number == 0) {
			return 1;
		}
		return number * calculateFactorial(number - 1);
	}

	public static void printAllFibnocci(int startIndex, int endIndex) {
		for (; startIndex <= endIndex; startIndex++) {
			System.out.println("number[" + startIndex + "] fibnocci[" + fibnocciNumberAtIndex(startIndex) + "]");
		}
	}

	public static int fibnocciNumberAtIndex(int number) {
		if (number <= 0) {
			return 0;
		} else if (number == 1) {
			return 1;
		}
		return fibnocciNumberAtIndex(number - 1) + fibnocciNumberAtIndex(number - 2);
	}

	public static void printAllFibnocciMemoization(int startIndex, int endIndex) {
		int[] memo = new int[(endIndex - startIndex) + 1];

		for (; startIndex <= endIndex; startIndex++) {
			System.out.println(
					"number[" + startIndex + "] fibnocci[" + printAllFibnocciMemoization(startIndex, memo) + "]");
		}
	}

	public static int printAllFibnocciMemoization(int number, int[] memo) {
		if (number <= 0) {
			return 0;
		} else if (number == 1) {
			return 1;
		} else if (memo[number] > 0) {
			return memo[number];
		}

		memo[number] = printAllFibnocciMemoization(number - 1, memo) + printAllFibnocciMemoization(number - 2, memo);
		return memo[number];
	}

	public static void main(String[] args) {
		NumberAlgorithms na = new NumberAlgorithms();
		// ma.printPrimeNumber( 0, 1000 );

		// System.out.println( "factorial of 5 > " + ma.calculateFactorial( 5 ) );

		na.printAllFibnocci(0, 10);
		na.printAllFibnocciMemoization(0, 10);
	}

}
