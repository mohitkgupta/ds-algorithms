package com.vedantatree.psds.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

/**
 * 
 * @author Mohit Gupta <mohit.gupta@vedantatree.com>
 */
public class NumberAlgorithms extends TestCase {

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

	public void testSmallestDifference() {
		int[] pair = smallestDifference(new int[] { -1, 5, 10, 20, 28, 3 }, new int[] { 26, 134, 135, 15, 17 });
		System.out.println(pair == null || pair.length == 0 ? "Null or Empty" : pair[0] + ", " + pair[1]);
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

	public int[] findThreeLargestNumbers(int[] array) {

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

	public void testThreeLargestNumbers() {
		assertNotNull(findThreeLargestNumbers(new int[] { 5, 8, 9, 23, 34, 34, 56 })[2] == 56);
	}

	public void testSquareRoot() {
		assertTrue(squareRoot(64) == 8);
		assertTrue(squareRoot(25) == 5);
		assertTrue(squareRoot(1522756) == 1234);
		assertTrue(squareRoot(32959081) == 5741);
		assertTrue(squareRoot(9) == 3);
		assertTrue(squareRoot(1) == 1);
		assertTrue(squareRoot(0) == -1);
	}

	public int squareRoot(int number) {
		for (int trial = 1; trial * trial <= number; trial++) {
			if (trial * trial == number) {
				return trial;
			}
		}
		return -1;
	}

	public void testSquareRootByBinaryTrial() {
		assertTrue(squareRootByBinaryGuessing(64) == 8);
		assertTrue(squareRootByBinaryGuessing(25) == 5);
		assertTrue(squareRootByBinaryGuessing(1522756) == 1234);
		assertTrue(squareRootByBinaryGuessing(32959081) == 5741);
		assertTrue(squareRootByBinaryGuessing(9) == 3);
		assertTrue(squareRootByBinaryGuessing(1) == 1);
		assertTrue(squareRootByBinaryGuessing(0) == -1);
	}

	public int squareRootByBinaryGuessing(int number) {
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

	public void testSumOfDigits() {
		assertTrue(sumOfDigits(15432) == 15);
		assertTrue(sumOfDigits(6894365) == 41);
		assertTrue(sumOfDigits(9425752) == 34);
		assertTrue(sumOfDigits(Long.valueOf("55459875621789")) == 81);
		assertTrue(sumOfDigits(524) == 11);
	}

	public long sumOfDigits(long number) {
		long sum = 0;
		while (number > 0) {
			sum += number % 10;
			number = number / 10;
		}
		return sum;
	}

	public void testPrimeNumber() {
		assertTrue("Number 11 is prime", isPrimeNumber(11));
		assertFalse("Number 12 is not prime", isPrimeNumber(12));
		assertFalse("Number 96 is not prime", isPrimeNumber(96));
		assertTrue("Number 113 is prime", isPrimeNumber(113));
	}

	public void printPrimeNumber(int start, int end) {
		int i = 1;
		for (; start <= end; start++) {
			if (isPrimeNumber(start)) {
				System.out.println(i + ") " + start);
				i++;
			}
		}
	}

	public boolean isPrimeNumber(int number) {
		for (int i = 2; i * i <= number; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

	public void testFactorial() {
		int number = 872;
		int loopFactorial = 1;
		for (int i = 1; i < number; i++) {
			loopFactorial = loopFactorial * i;
		}

		assertTrue(loopFactorial == calculateFactorial(number));
	}

	public int calculateFactorial(int number) {
		if (number == 0) {
			return 1;
		}
		return number * calculateFactorial(number - 1);
	}

	public void printAllFibnocci(int startIndex, int endIndex) {
		for (; startIndex <= endIndex; startIndex++) {
			System.out.println("number[" + startIndex + "] fibnocci[" + fibnocciNumberAtIndex(startIndex) + "]");
		}
	}

	public int fibnocciNumberAtIndex(int number) {
		if (number <= 0) {
			return 0;
		} else if (number == 1) {
			return 1;
		}
		return fibnocciNumberAtIndex(number - 1) + fibnocciNumberAtIndex(number - 2);
	}

	public void printAllFibnocciMemoization(int startIndex, int endIndex) {
		int[] memo = new int[(endIndex - startIndex) + 1];

		for (; startIndex <= endIndex; startIndex++) {
			System.out.println(
					"number[" + startIndex + "] fibnocci[" + printAllFibnocciMemoization(startIndex, memo) + "]");
		}
	}

	public int printAllFibnocciMemoization(int number, int[] memo) {
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
