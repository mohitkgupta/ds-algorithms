package com.vedanatree.psds.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vedantatree.psds.Utils;
import com.vedantatree.psds.algo.NumberAlgorithms;

import junit.framework.TestCase;

public class TestNumberAlgorithms extends TestCase {

	private static final NumberAlgorithms numberAlgorithms = new NumberAlgorithms();

	public void testTwoNumberSum() {
		int[] input = new int[] { 3, 5, -4, 8, 11, 1, -1, 6 };
		int targetSum = 10;

		int[] result = NumberAlgorithms.twoNumberSum(input, targetSum);

		assertNotNull(result);
		assertTrue("Result size should be 2, as two numbers are summing up to 10", result.length == 2);
		assertTrue(result[0] == 11);
		assertTrue(result[1] == -1);

		input = new int[] { -3, -5, -4, -8, -11, -1, -1, -6 };
		targetSum = -10;

		result = NumberAlgorithms.twoNumberSum(input, targetSum);

		assertNotNull(result);
		assertTrue("Result size should be 2, as two numbers are summing up to -10", result.length == 2);
		assertTrue(result[0] == -4);
		assertTrue(result[1] == -6);

		input = new int[] { -3, -5, -4, -8, -11, -1, -1, -6 };
		targetSum = -1;

		result = NumberAlgorithms.twoNumberSum(input, targetSum);

		assertNotNull(result);
		assertEquals("Result size should be 0, as no number is summing upto -13", 0, result.length);

		input = new int[] {};
		targetSum = -13;

		result = NumberAlgorithms.twoNumberSum(input, targetSum);

		assertNotNull(result);
		assertEquals("Result size should be 0, as no number is summing upto -13", 0, result.length);
	}

	public void testIsValidSubsequence() {
		List<Integer> array = Arrays.asList(5, 1, 22, 25, 6, -1, 8, 10);
		List<Integer> sequence = Arrays.asList(1, 6, -1, 10);

		boolean result = NumberAlgorithms.isValidSubsequence(array, sequence);

		assertTrue("Expected result > true", result);

		array = Arrays.asList(5, 22, 25, 6, -1, 8, 10);
		sequence = Arrays.asList(1, 6, -1, 10);

		result = NumberAlgorithms.isValidSubsequence(array, sequence);

		assertTrue("Expected result > false", !result);
	}

	public void testSortedSquaredArray() {
		int[] arrayToSquare = new int[] { -5, 3, 6, 8, 10 };

		int[] squaredArray = NumberAlgorithms.sortedSquaredArray(arrayToSquare);

		Utils.printArray(squaredArray);

		assertNotNull("squared array must not be null", squaredArray);
		assertTrue(squaredArray.length == arrayToSquare.length);

		assertTrue(squaredArray[0] == 9);
		assertTrue(squaredArray[1] == 25);
		assertTrue(squaredArray[2] == 36);
		assertTrue(squaredArray[3] == 64);
		assertTrue(squaredArray[4] == 100);
	}

	public void testTournamentWinner() {
		ArrayList<ArrayList<String>> competitions = new ArrayList<>();

		ArrayList<String> competingTeams = new ArrayList<>();

		competingTeams.add("A");
		competingTeams.add("B");
		competitions.add(competingTeams);

		competingTeams = new ArrayList<>();
		competingTeams.add("B");
		competingTeams.add("C");
		competitions.add(competingTeams);

		competingTeams = new ArrayList<>();
		competingTeams.add("C");
		competingTeams.add("A");
		competitions.add(competingTeams);

		competingTeams = new ArrayList<>();
		competingTeams.add("B");
		competingTeams.add("A");
		competitions.add(competingTeams);

		competingTeams = new ArrayList<>();
		competingTeams.add("A");
		competingTeams.add("C");
		competitions.add(competingTeams);

		competingTeams = new ArrayList<>();
		competingTeams.add("C");
		competingTeams.add("A");
		competitions.add(competingTeams);

		ArrayList<Integer> results = new ArrayList<>();
		results.add(0);
		results.add(0);
		results.add(1);
		results.add(1);
		results.add(0);
		results.add(1);

		String winningTeam = NumberAlgorithms.tournamentWinner(competitions, results);

		assertEquals("Winning team must be C", "C", winningTeam);
	}

	public void testSmallestDifference() {
		int[] pair = NumberAlgorithms.smallestDifference(new int[] { -1, 5, 10, 20, 28, 3 },
				new int[] { 26, 134, 135, 15, 17 });
		System.out.println(pair == null || pair.length == 0 ? "Null or Empty" : pair[0] + ", " + pair[1]);
	}

	public void testThreeLargestNumbers() {
		assertNotNull(NumberAlgorithms.findThreeLargestNumbers(new int[] { 5, 8, 9, 23, 34, 34, 56 })[2] == 56);
	}

	public void testSquareRoot() {
		assertTrue(NumberAlgorithms.squareRoot(64) == 8);
		assertTrue(NumberAlgorithms.squareRoot(25) == 5);
		assertTrue(NumberAlgorithms.squareRoot(1522756) == 1234);
		assertTrue(NumberAlgorithms.squareRoot(32959081) == 5741);
		assertTrue(NumberAlgorithms.squareRoot(9) == 3);
		assertTrue(NumberAlgorithms.squareRoot(1) == 1);
		assertTrue(NumberAlgorithms.squareRoot(0) == -1);
	}

	public void testSquareRootByBinaryTrial() {
		assertTrue(NumberAlgorithms.squareRootByBinaryGuessing(64) == 8);
		assertTrue(NumberAlgorithms.squareRootByBinaryGuessing(25) == 5);
		assertTrue(NumberAlgorithms.squareRootByBinaryGuessing(1522756) == 1234);
		assertTrue(NumberAlgorithms.squareRootByBinaryGuessing(32959081) == 5741);
		assertTrue(NumberAlgorithms.squareRootByBinaryGuessing(9) == 3);
		assertTrue(NumberAlgorithms.squareRootByBinaryGuessing(1) == 1);
		assertTrue(NumberAlgorithms.squareRootByBinaryGuessing(0) == -1);
	}

	public void testSumOfDigits() {
		assertTrue(NumberAlgorithms.sumOfDigits(15432) == 15);
		assertTrue(NumberAlgorithms.sumOfDigits(6894365) == 41);
		assertTrue(NumberAlgorithms.sumOfDigits(9425752) == 34);
		assertTrue(NumberAlgorithms.sumOfDigits(Long.valueOf("55459875621789")) == 81);
		assertTrue(NumberAlgorithms.sumOfDigits(524) == 11);
	}

	public void testPrimeNumber() {
		assertTrue("Number 11 is prime", NumberAlgorithms.isPrimeNumber(11));
		assertFalse("Number 12 is not prime", NumberAlgorithms.isPrimeNumber(12));
		assertFalse("Number 96 is not prime", NumberAlgorithms.isPrimeNumber(96));
		assertTrue("Number 113 is prime", NumberAlgorithms.isPrimeNumber(113));
	}

	public void testFactorial() {
		int number = 872;
		int loopFactorial = 1;
		for (int i = 1; i < number; i++) {
			loopFactorial = loopFactorial * i;
		}

		assertTrue(loopFactorial == NumberAlgorithms.calculateFactorial(number));
	}

}
