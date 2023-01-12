package com.vedantatree.psds.algo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import junit.framework.TestCase;

/**
 * TODO
 * 
 * Rotate Matrix Replace row, column in matrix to zero
 * 
 * @author Mohit Gupta <mohit.gupta@vedantatree.com>
 */
public class StringAlgorithms extends TestCase {
	
	

	public String caesarCypherEncryptor(String str, int key) {

		StringBuilder encryptedString = new StringBuilder();

		for (char character : str.toCharArray()) {
			
			System.out.println("char > " + character + ". int > " + Character.getNumericValue(character));
			
			int encryptedInt =  Character.getNumericValue(character) + key;
			
			System.out.println(encryptedInt);
			
			if (encryptedInt > Character.getNumericValue('z')) {
				encryptedInt = encryptedInt - Character.getNumericValue('z');
			}
			
			System.out.println("-26 > " + ((char)encryptedInt));
			
//			encryptedString.append(Character.to);
		}

		return encryptedString.toString();
	}
	
	public void testCaesarCypherEncryptor()
	{
		assertTrue(caesarCypherEncryptor("abcabc", 2).equals("cdecde"));

	}

	/**
	 * Find first non repeating character from given string
	 * 
	 * @param string to find the first non repeating character
	 * @return index of first non repeating character. -1 if no non-repeating
	 *         character is found
	 */
	public int firstNonRepeatingCharacter(String string) {

		int[] characters = new int[128];

		for (char character : string.toCharArray()) {
			characters[character]++;
		}

		for (int i = 0; i < string.length(); i++) {
			char character = string.charAt(i);
			if (characters[character] == 1) {
				return i;
			}
		}
		return -1;
	}

	public void testFirstNonRepeatingCharacter() {
		assertFalse(generateDocument("abcabc", "aabbccc"));
		assertFalse(generateDocument("abcabc", "aaaaaaaaaa"));
		assertFalse(generateDocument("bbbbbbbbbbbb", "aabbccc"));
		assertFalse(generateDocument("", "aabbccc"));
		assertTrue(generateDocument("abcabc", ""));
		assertTrue(generateDocument("", ""));
		assertTrue(generateDocument("adzqprn", "anrzd"));
		assertTrue(generateDocument("azzqpii", "izzq"));
	}

	/**
	 * Can given document be generated from given characters Refer to test below for
	 * use cases
	 * 
	 * @param characters
	 * @param document
	 * @return
	 */
	public boolean generateDocument(String characters, String document) {

		HashMap<Character, Integer> characterStore = new HashMap<Character, Integer>(characters.length());

		for (int i = 0; i < characters.length(); i++) {
			Character character = characters.charAt(i);
			int occurence = 1;

			if (characterStore.get(character) != null) {
				occurence = characterStore.get(character);
				occurence++;
			}
			characterStore.put(character, occurence);
		}

		if (document.length() == 0) {
			return true;
		}

		for (int i = 0; i < document.length(); i++) {
			Character docChar = document.charAt(i);
			Integer occurence = characterStore.get(docChar);

			if (occurence != null && occurence > 0) {
				characterStore.put(docChar, occurence - 1);
			} else {
				return false;
			}
		}

		return true;
	}

	public void testGenerateDocument() {
		assertFalse(generateDocument("abcabc", "aabbccc"));
		assertFalse(generateDocument("abcabc", "aaaaaaaaaa"));
		assertFalse(generateDocument("bbbbbbbbbbbb", "aabbccc"));
		assertFalse(generateDocument("", "aabbccc"));
		assertTrue(generateDocument("abcabc", ""));
		assertTrue(generateDocument("", ""));
		assertTrue(generateDocument("adzqprn", "anrzd"));
		assertTrue(generateDocument("azzqpii", "izzq"));
	}

	public void testCompressString() {
		assertEquals("h1i2e3", compressString("hiieee"));
		assertEquals(" 1h1i2 1e3", compressString(" hii eee"));
		assertEquals("abc", compressString("abc"));
		assertEquals("abc ", compressString("abc "));
	}

	/**
	 * This method will compress the given string. Ex: hiieee will be changed to
	 * h1i2e3
	 * 
	 * @param str String to compress
	 * @return compressed string
	 */
	public String compressString(String str) {
		if (!isWorkableString(str))
			return str;

		int consecutiveCount = 0;
		boolean consecutiveFound = false;
		StringBuilder compressedString = new StringBuilder(); // StringBuilder is good to save time

		int stringLength = str.length();
		for (int i = 0; i < stringLength; i++) {
			consecutiveCount++;

			if (i + 1 >= stringLength || str.charAt(i) != str.charAt(i + 1)) {
				compressedString.append(str.charAt(i));
				compressedString.append(consecutiveCount);
				consecutiveFound = consecutiveCount > 1; // setting true
				consecutiveCount = 0;
			}
		}
		return consecutiveFound ? compressedString.toString() : str;
	}

	public void testPermutationOfPalindrome() {
		assertTrue(isPermutationOfPalindrome("hellohello") == true);
		assertTrue(isPermutationOfPalindrome("ahellohello") == true);
		assertTrue(isPermutationOfPalindrome("ahellohelloc") == false);
		assertTrue(isPermutationOfPalindrome("ahe ll oh e l lo c") == false);
	}

	/**
	 * Check if given string is a permutation of a palindrome string
	 * 
	 * concept is > check if we have all the characters in even number, if yes, that
	 * means these characters can be repeated twice or in pair if there is any
	 * character counting odd, it can not be more than once. I.e. in the center of
	 * the palindrome. If these conditions are matching, we can assume that string
	 * is permutation of palindrome
	 * 
	 * @param str string to check
	 * @return true if string is permutation of palindrome, false otherwise
	 */
	public boolean isPermutationOfPalindrome(String str) {
		if (!isWorkableString(str))
			return false;

		int oddCount = 0;
		int[] characterTable = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];

		for (char character : str.toCharArray()) {
			int charValue = getCharNumber(character);
			if (charValue != -1) {
				characterTable[charValue]++;
				oddCount = characterTable[charValue] % 2 == 0 ? oddCount - 1 : oddCount + 1;
			}
		}
		return oddCount <= 1;
	}

	/**
	 * Retrun true if given string is a palindrome, false otherwise. A single
	 * character string is also a palindrome "a" > is a palindrome "abcdcba" is a
	 * palindrome "abccba" is a palindrome
	 * 
	 * @param str string to check
	 * @return
	 */
	public boolean isPalindrome(String str) {

		int stringLength = str.length();
		if (stringLength == 1)
			return true;

		for (int i = 0, j = stringLength - (i + 1); i < stringLength; i++, j--) {
			if (i == j)
				return true;
			if (str.charAt(i) != str.charAt(j))
				return false;
		}

		return true;
	}

	public void testIsPalindrome() {
		assertTrue(isPalindrome(""));
		assertTrue(isPalindrome("c"));
		assertTrue(isPalindrome("abcba"));
		assertTrue(isPalindrome("abccba"));
		assertTrue(isPalindrome("abcdcba"));
		assertFalse(isPalindrome("abd"));
		assertFalse(isPalindrome("abcdefedcab"));
	}

	/**
	 * Map each character to a number. Like a -> 0, b -> 1, c -> 2, etc. This is
	 * case insensitive. Non-letter characters map to -1.
	 */
	public int getCharNumber(Character c) {
		int a = Character.getNumericValue('a');
		int z = Character.getNumericValue('z');
		int val = Character.getNumericValue(c);
		if (a <= val && val <= z) {
			return val - a;
		}
		return -1;
	}

	public void testUrlifyString() {
		assertEquals("Hello%20Hello", urlifyString("Hello Hello  ", 11));
		assertEquals("Hello%20Hello%20", urlifyString("Hello Hello     ", 12));
		assertEquals("%20Hello%20Hello", urlifyString(" Hello Hello    ", 12));
		assertEquals("Hel%20lo%20Hello", urlifyString("Hel lo Hello    ", 12));
	}

	/**
	 * Return urlify string for given string Urlify means - replacing every space
	 * with %20
	 * 
	 * @param str        String to urlify, assumed that it contains extra space for
	 *                   %20 at the end
	 * @param trueLength actual length of string which contains the content, leaving
	 *                   trailing spaces for %20
	 * @return Urlify String
	 */
	public String urlifyString(String str, int trueLength) {
		if (!isWorkableString(str))
			return str;

		int spaceCount = countSpaces(str, trueLength);

		int urlifyStrIndex = trueLength + spaceCount * 2 - 1;

		char[] urlifyChar = str.toCharArray();

		for (int index = trueLength - 1; index >= 0; index--) {
			if (urlifyChar[index] == ' ') {
				urlifyChar[urlifyStrIndex] = '0';
				urlifyChar[urlifyStrIndex - 1] = '2';
				urlifyChar[urlifyStrIndex - 2] = '%';
				urlifyStrIndex -= 3;
			} else {
				urlifyChar[urlifyStrIndex] = urlifyChar[index];
				urlifyStrIndex--;
			}
		}
		return new String(urlifyChar);
	}

	public void testCountSpaces() {
		assertTrue(countSpaces("Hello Hello Hello") == 2);
		assertTrue(countSpaces("Hello Hello Hello") != 3);
		assertTrue(countSpaces(" Hello He llo Hello ") == 5);
		assertTrue(countSpaces("HelloHelloHello") == 0);
		assertTrue(countSpaces("") == -1);
		assertTrue(countSpaces(null) == -1);
	}

	public int countSpaces(String str) {
		return countSpaces(str, -1);
	}

	/**
	 * @param str              String for which we need to calculate the spaces
	 * @param trueStringLength Given true length of string, excluding trailing
	 *                         spaces if any (trailing space to accommodate urlify
	 *                         logic)
	 * @return no of spaces in the given string
	 */
	public int countSpaces(String str, int trueStringLength) {
		if (!isWorkableString(str))
			return -1;

		int spaces = 0;
		trueStringLength = trueStringLength == -1 ? str.length() : trueStringLength;

		for (int index = 0; index < trueStringLength; index++) {
			if (str.charAt(index) == ' ') {
				spaces++;
			}
		}

		return spaces;
	}

	/**
	 * @param str String to verify
	 * @return true if string is not null and also not empty, false otherwise
	 */
	public boolean isWorkableString(String str) {
		return str != null && str.trim().length() > 0;
	}

	public void testUniqueString() {
		assertTrue(isUniqueString("singapore"));
		assertFalse(isUniqueString("india"));
		assertFalse(isUniqueString("america"));
		assertTrue(isUniqueString("bhutan"));
	}

	/**
	 * Check if given string contains all unique character only Assumption - Ascii
	 * code based
	 * 
	 * @param stringToEval String to evaluate
	 * @return true if specific string has all unique character
	 */
	public boolean isUniqueString(String stringToEval) {
		if (stringToEval == null)
			return false;

		int stringLength = stringToEval.length();
		if (stringLength == 0 || stringLength > 128) {
			return false;
		}

		boolean[] charTraversed = new boolean[128];

		for (int i = 0; i < stringLength; i++) {
			int val = stringToEval.charAt(i);
			if (charTraversed[val]) {
				return false;
			}
			charTraversed[val] = true;
		}
		return true;
	}

	public void testPermutation() {
		assertTrue(isPermutation("apple", "palpe"));
		assertTrue(isPermutation("banana", "abnnaa"));
		assertTrue(isPermutation("orange", "gareon"));
		assertTrue(isPermutation("watermelon", "laeotrnwem"));

		assertFalse(isPermutation("apple", "paape"));
		assertFalse(isPermutation("banana", "arnnaa"));
		assertFalse(isPermutation("orange", "gaeon"));
		assertFalse(isPermutation("watermelon", "llaeotrnwem"));
	}

	public boolean isPermutation(String string1, String string2) {
		if (string1.length() != string2.length()) {
			return false;
		}
		return sortString(string1).equals(sortString(string2));
	}

	public String sortString(String stringToSort) {
		assertNotNull(stringToSort);

		char[] contents = stringToSort.toCharArray();
		Arrays.sort(contents);
		return new String(contents);
	}

	public void testPermutationOptimized() {
		assertTrue(isPermutationOptimized("apple", "palpe"));
		assertTrue(isPermutationOptimized("banana", "abnnaa"));
		assertTrue(isPermutationOptimized("orange", "gareon"));
		assertTrue(isPermutationOptimized("watermelon", "laeotrnwem"));

		assertFalse(isPermutationOptimized("apple", "paape"));
		assertFalse(isPermutationOptimized("banana", "arnnaa"));
		assertFalse(isPermutationOptimized("orange", "gaeon"));
		assertFalse(isPermutationOptimized("watermelon", "llaeotrnwem"));
	}

	/**
	 * @return true if both strings are permutation of each other
	 */
	public boolean isPermutationOptimized(String string1, String string2) {
		// assuming not null
		if (string1.length() != string2.length()) {
			return false;
		}
		int[] letters = new int[128];
		char[] string1Contents = string1.toCharArray();

		/*
		 * store char count of first string in an array iterate over second string check
		 * for each character in array, if it is found > reduce count by 1. if remainder
		 * count is less than zero, it means count from first string were less than
		 * second string > return false
		 */
		for (int i = 0; i < string1Contents.length; i++) {
			char character = string1Contents[i];
			letters[character]++;
		}

		int stringLength = string2.length();
		for (int i = 0; i < stringLength; i++) {
			char character = string2.charAt(i);
			letters[character]--;
			if (letters[character] < 0) {
				return false;
			}
		}
		return true;
	}

	public void testOneEditAwayOrSame() {
		assertEquals(true, oneEditAwayOrSame("abc", "abc"));
		assertEquals(true, oneEditAwayOrSame("abc", "acc"));
		assertEquals(true, oneEditAwayOrSame("abc", "abd"));
		assertEquals(true, oneEditAwayOrSame("abcd", "acd"));
		// assertEquals( true, oneEditAwayOrSame( "abc", "abcd"));
		assertEquals(true, oneEditAwayOrSame("abcd", "abc"));
		assertEquals(false, oneEditAwayOrSame("abc", "abcde"));
	}

	/**
	 * @return true if both string are same, or both string can be same by
	 *         replacing/adding one of the character in either
	 */
	public boolean oneEditAwayOrSame(String str1, String str2) {
		if (Math.abs(str1.length() - str2.length()) > 1) {
			return false;
		}
		if (str1.length() == str2.length()) {
			return oneReplaceAway(str1, str2);
		}

		if (str1.length() + 1 == str2.length()) {
			return oneInsertAway(str2, str1);
		}

		if (str1.length() == str2.length() + 1) {
			return oneInsertAway(str1, str2);
		}

		return false;
	}

	public boolean oneReplaceAway(String str1, String str2) {
		boolean foundDifferrence = false;

		for (int i = 0; i < str1.length(); i++) {
			if (str1.charAt(i) != str2.charAt(i)) {
				if (foundDifferrence) {
					return false;
				}
				foundDifferrence = true;
			}
		}
		return true;
	}

	public boolean oneInsertAway(String str1, String str2) {
		int index1 = 0;
		int index2 = 0;

		while (index1 < str1.length() && index2 < str2.length()) {
			if (str1.charAt(index1) != str2.charAt(index2)) {
				if (index1 != index2) {
					return false;
				}
				index1++;
			} else {
				index1++;
				index2++;
			}
		}
		return true;
	}

	// TODO explore more
	public void permutation(String seedString) {
		HashSet<String> uniquePermutations = new HashSet<>();
		int numberOfPermutation = permutation("", seedString, uniquePermutations);

		System.out.println("unique permutations > " + uniquePermutations);
		System.out.println("number of permutations. iterations[" + numberOfPermutation + "] unique["
				+ uniquePermutations.size() + "]");
	}

	int recursion = 1;

	private int permutation(String prefix, String seedString, HashSet<String> uniquePermutations) {
		recursion += 1;
		System.out.println("recursion[" + recursion + "] prefix[" + prefix + "] seed[" + seedString + "]");

		int numberOfPermutation = 0;
		if (seedString.length() == 0) {
			uniquePermutations.add(prefix);
			numberOfPermutation++;

			System.out.println(prefix);
		} else {
			for (int i = 0; i < seedString.length(); i++) {
				String alteredSeed = seedString.substring(0, i) + seedString.substring(i + 1);

				System.out.println("recursion[" + recursion + "]  prefix[" + prefix + "] seed[" + seedString
						+ "] charAtI[" + seedString.charAt(i) + "] sub1[" + seedString.substring(0, i) + "] sub2["
						+ seedString.substring(i + 1) + "] alteredSeed[" + alteredSeed + "]");

				numberOfPermutation += permutation(prefix + seedString.charAt(i), alteredSeed, uniquePermutations);
			}
		}
		return numberOfPermutation;
	}

	public static void main(String[] args) {
		StringAlgorithms sa = new StringAlgorithms();
		sa.permutation("sow");
	}

}
