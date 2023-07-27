package com.vedantatree.psds.algo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.vedantatree.psds.Utils;

import junit.framework.TestCase;


/**
 * TODO
 * 
 * Rotate Matrix Replace row, column in matrix to zero
 * 
 * @author Mohit Gupta <mohit.gupta@vedantatree.com>
 */
public class StringAlgorithms extends TestCase {

	// TODO not working. Review and fix please
	public String caesarCypherEncryptor( String str, int key ) {

		StringBuilder encryptedString = new StringBuilder();

		for( char character : str.toCharArray() ) {

			System.out.println( "char > " + character + ". int > " + Character.getNumericValue( character ) );

			int encryptedInt = Character.getNumericValue( character ) + key;

			System.out.println( encryptedInt );

			// TODO: review this. Should be something like doing mode with max expected value % 26
			if( encryptedInt > Character.getNumericValue( 'z' ) ) {
				encryptedInt = encryptedInt - Character.getNumericValue( 'z' );
			}

			System.out.println( "-26 > " + ( (char) encryptedInt ) );

			// TODO: Why is this commented. Check it
			// encryptedString.append(Character.to);
		}

		return encryptedString.toString();
	}

	public void testCaesarCypherEncryptor() {
		assertTrue( caesarCypherEncryptor( "abcabc", 2 ).equals( "cdecde" ) );

	}

	/**
	 * Find first non repeating character from given string
	 * 
	 * @param string to find the first non repeating character
	 * @return index of first non repeating character. -1 if no non-repeating
	 *         character is found
	 */
	public int firstNonRepeatingCharacter( String string ) {

		int[] characters = new int[128];

		for( char character : string.toCharArray() ) {
			characters[character]++;
		}

		for( int i = 0; i < string.length(); i++ ) {
			char character = string.charAt( i );
			if( characters[character] == 1 ) {
				return i;
			}
		}
		return -1;
	}

	public void testFirstNonRepeatingCharacter() {
		assertFalse( generateDocument( "abcabc", "aabbccc" ) );
		assertFalse( generateDocument( "abcabc", "aaaaaaaaaa" ) );
		assertFalse( generateDocument( "bbbbbbbbbbbb", "aabbccc" ) );
		assertFalse( generateDocument( "", "aabbccc" ) );
		assertTrue( generateDocument( "abcabc", "" ) );
		assertTrue( generateDocument( "", "" ) );
		assertTrue( generateDocument( "adzqprn", "anrzd" ) );
		assertTrue( generateDocument( "azzqpii", "izzq" ) );
	}

	/**
	 * Can given document be generated from given characters Refer to test below for
	 * use cases
	 * 
	 * @param characters
	 * @param document
	 * @return
	 */
	public boolean generateDocument( String characters, String document ) {

		// nothing to do, if document is of zero length
		if( document.length() == 0 ) {
			return true;
		}

		// TODO can use character array too, store char as int and keep incrementing for occurences
		HashMap<Character, Integer> characterStore = new HashMap<Character, Integer>( characters.length() );

		// collect the occurences of each character in give string
		for( int i = 0; i < characters.length(); i++ ) {
			Character character = characters.charAt( i );
			int occurence = 1;

			if( characterStore.get( character ) != null ) {
				occurence = characterStore.get( character );
				occurence++;
			}
			characterStore.put( character, occurence );
		}

		for( int i = 0; i < document.length(); i++ ) {
			Character docChar = document.charAt( i );
			Integer occurence = characterStore.get( docChar );

			if( occurence != null && occurence > 0 ) {
				characterStore.put( docChar, occurence - 1 );
			}
			else {
				return false;
			}
		}

		return true;
	}

	public void testGenerateDocument() {
		assertFalse( generateDocument( "abcabc", "aabbccc" ) );
		assertFalse( generateDocument( "abcabc", "aaaaaaaaaa" ) );
		assertFalse( generateDocument( "bbbbbbbbbbbb", "aabbccc" ) );
		assertFalse( generateDocument( "", "aabbccc" ) );
		assertTrue( generateDocument( "abcabc", "" ) );
		assertTrue( generateDocument( "", "" ) );
		assertTrue( generateDocument( "adzqprn", "anrzd" ) );
		assertTrue( generateDocument( "azzqpii", "izzq" ) );
	}

	/**
	 * This method will compress the given string.
	 * Ex: hiieee will be changed to h1i2e3
	 * 
	 * @param str String to compress
	 * @return compressed string
	 */
	public String compressString( String str ) {
		if( !isWorkableString( str ) ) {
			return str;
		}

		int consecutiveCount = 0;
		boolean consecutiveFound = false;
		StringBuilder compressedString = new StringBuilder(); // StringBuilder is good to save time

		int stringLength = str.length();
		for( int i = 0; i < stringLength; i++ ) {
			consecutiveCount++;

			if( i + 1 >= stringLength || str.charAt( i ) != str.charAt( i + 1 ) ) {
				compressedString.append( str.charAt( i ) );
				compressedString.append( consecutiveCount );

				consecutiveFound = consecutiveCount > 1; // setting true
				consecutiveCount = 0;
			}
		}
		return consecutiveFound ? compressedString.toString() : str;
	}

	public void testCompressString() {
		assertEquals( "h1i2e3", compressString( "hiieee" ) );
		assertEquals( " 1h1i2 1e3", compressString( " hii eee" ) );
		assertEquals( "abc", compressString( "abc" ) );
		assertEquals( "abc ", compressString( "abc " ) );
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
	public boolean isPermutationOfPalindrome( String str ) {
		if( !isWorkableString( str ) )
			return false;

		int oddCount = 0;
		int[] characterTable = new int[Character.getNumericValue( 'z' ) - Character.getNumericValue( 'a' ) + 1];

		for( char character : str.toCharArray() ) {
			int charValue = getCharNumber( character );

			if( charValue != -1 ) {
				characterTable[charValue]++;
				oddCount = characterTable[charValue] % 2 == 0 ? oddCount - 1 : oddCount + 1;
			}
		}

		Utils.printArray( characterTable );

		return oddCount <= 1;
	}

	public void testPermutationOfPalindrome() {
		assertTrue( isPermutationOfPalindrome( "hellohello" ) == true );
		assertTrue( isPermutationOfPalindrome( "ahellohello" ) == true );
		assertTrue( isPermutationOfPalindrome( "ahellohelloc" ) == false );
		assertTrue( isPermutationOfPalindrome( "ahe ll oh e l lo c" ) == false );
	}

	/**
	 * Retrun true if given string is a palindrome, false otherwise. A single
	 * character string is also a palindrome "a" > is a palindrome "abcdcba" is a
	 * palindrome "abccba" is a palindrome
	 * 
	 * @param str string to check
	 * @return
	 */
	public boolean isPalindrome( String str ) {

		int stringLength = str.length();
		if( stringLength == 1 )
			return true;

		for( int i = 0, j = stringLength - ( i + 1 ); i < stringLength; i++, j-- ) {
			if( i == j )
				return true;
			if( str.charAt( i ) != str.charAt( j ) )
				return false;
		}

		return true;
	}

	public static boolean isPalindromRecrusive( String str ) {
		return isPalindromRecrusive( str, 0, str.length() - 1 );
	}

	private static boolean isPalindromRecrusive( String str, int left, int right ) {
		if( left >= right ) {
			return true;
		}
		if( str.charAt( left ) != str.charAt( right ) ) {
			return false;
		}
		return isPalindromRecrusive( str, left + 1, right - 1 );
	}

	public void testIsPalindrome() {
		assertTrue( isPalindrome( "" ) );
		assertTrue( isPalindrome( "c" ) );
		assertTrue( isPalindrome( "abcba" ) );
		assertTrue( isPalindrome( "abccba" ) );
		assertTrue( isPalindrome( "abcdcba" ) );
		assertFalse( isPalindrome( "abd" ) );
		assertFalse( isPalindrome( "abcdefedcab" ) );
	}

	public void testIsPalindromRecursive() {

		assertTrue( isPalindromRecrusive( "" ) );
		assertTrue( isPalindromRecrusive( "c" ) );
		assertTrue( isPalindromRecrusive( "abcba" ) );
		assertTrue( isPalindromRecrusive( "abccba" ) );
		assertTrue( isPalindromRecrusive( "abcdcba" ) );
		assertFalse( isPalindromRecrusive( "abd" ) );
		assertFalse( isPalindromRecrusive( "abcdefedcab" ) );

	}

	/**
	 * Map each character to a number. Like a -> 0, b -> 1, c -> 2, etc. This is
	 * case insensitive. Non-letter characters map to -1.
	 */
	public int getCharNumber( Character c ) {
		int a = Character.getNumericValue( 'a' );
		int z = Character.getNumericValue( 'z' );
		int val = Character.getNumericValue( c );
		if( a <= val && val <= z ) {
			return val - a;
		}
		return -1;
	}

	/**
	 * Return urlify string for given string Urlify means - replacing every space
	 * with %20
	 * 
	 * @param str String to urlify, assumed that it contains extra space for
	 *        %20 at the end
	 * @param trueLength actual length of string which contains the content, leaving
	 *        trailing spaces for %20
	 * @return Urlify String
	 */
	public String urlifyString( String str, int trueLength ) {
		if( !isWorkableString( str ) )
			return str;

		int spaceCount = countSpaces( str, trueLength );

		int urlifyStrIndex = trueLength + spaceCount * 2 - 1;

		char[] urlifyChar = str.toCharArray();

		for( int index = trueLength - 1; index >= 0; index-- ) {
			if( urlifyChar[index] == ' ' ) {
				urlifyChar[urlifyStrIndex] = '0';
				urlifyChar[urlifyStrIndex - 1] = '2';
				urlifyChar[urlifyStrIndex - 2] = '%';
				urlifyStrIndex -= 3;
			}
			else {
				urlifyChar[urlifyStrIndex] = urlifyChar[index];
				urlifyStrIndex--;
			}
		}
		return new String( urlifyChar );
	}

	public void testUrlifyString() {
		assertEquals( "Hello%20Hello", urlifyString( "Hello Hello  ", 11 ) );
		assertEquals( "Hello%20Hello%20", urlifyString( "Hello Hello     ", 12 ) );
		assertEquals( "%20Hello%20Hello", urlifyString( " Hello Hello    ", 12 ) );
		assertEquals( "Hel%20lo%20Hello", urlifyString( "Hel lo Hello    ", 12 ) );
	}

	public void testCountSpaces() {
		assertTrue( countSpaces( "Hello Hello Hello" ) == 2 );
		assertTrue( countSpaces( "Hello Hello Hello" ) != 3 );
		assertTrue( countSpaces( " Hello He llo Hello " ) == 5 );
		assertTrue( countSpaces( "HelloHelloHello" ) == 0 );
		assertTrue( countSpaces( "" ) == -1 );
		assertTrue( countSpaces( null ) == -1 );
	}

	public int countSpaces( String str ) {
		return countSpaces( str, -1 );
	}

	/**
	 * @param str String for which we need to calculate the spaces
	 * @param trueStringLength Given true length of string, excluding trailing
	 *        spaces if any (trailing space to accommodate urlify
	 *        logic)
	 * @return no of spaces in the given string
	 */
	public int countSpaces( String str, int trueStringLength ) {
		if( !isWorkableString( str ) )
			return -1;

		int spaces = 0;
		trueStringLength = trueStringLength == -1 ? str.length() : trueStringLength;

		for( int index = 0; index < trueStringLength; index++ ) {
			if( str.charAt( index ) == ' ' ) {
				spaces++;
			}
		}

		return spaces;
	}

	/**
	 * @param str String to verify
	 * @return true if string is not null and also not empty, false otherwise
	 */
	public boolean isWorkableString( String str ) {
		return str != null && str.trim().length() > 0;
	}

	public void testUniqueString() {
		assertTrue( isUniqueString( "singapore" ) );
		assertFalse( isUniqueString( "india" ) );
		assertFalse( isUniqueString( "america" ) );
		assertTrue( isUniqueString( "bhutan" ) );
	}

	/**
	 * Check if given string contains all unique character only
	 * Assumption - Ascii code based
	 * 
	 * @param stringToEval String to evaluate
	 * @return true if specific string has all unique character
	 */
	public boolean isUniqueString( String stringToEval ) {
		if( stringToEval == null )
			return false;

		int stringLength = stringToEval.length();

		// TODO why string length check for 128? and response should be true on length == 0 also
		if( stringLength == 0 || stringLength > 128 ) {
			return false;
		}

		boolean[] charTraversed = new boolean[128];

		for( int i = 0; i < stringLength; i++ ) {
			int val = stringToEval.charAt( i );
			if( charTraversed[val] ) {
				return false;
			}
			charTraversed[val] = true;
		}
		return true;
	}

	public void testPermutation() {
		assertTrue( isPermutation( "apple", "palpe" ) );
		assertTrue( isPermutation( "banana", "abnnaa" ) );
		assertTrue( isPermutation( "orange", "gareon" ) );
		assertTrue( isPermutation( "watermelon", "laeotrnwem" ) );

		assertFalse( isPermutation( "apple", "paape" ) );
		assertFalse( isPermutation( "banana", "arnnaa" ) );
		assertFalse( isPermutation( "orange", "gaeon" ) );
		assertFalse( isPermutation( "watermelon", "llaeotrnwem" ) );
	}

	public boolean isPermutation( String string1, String string2 ) {
		if( string1.length() != string2.length() ) {
			return false;
		}
		return sortString( string1 ).equals( sortString( string2 ) );
	}

	public String sortString( String stringToSort ) {
		assertNotNull( stringToSort );

		char[] contents = stringToSort.toCharArray();
		Arrays.sort( contents );
		return new String( contents );
	}

	public void testPermutationOptimized() {
		assertTrue( isPermutationOptimized( "apple", "palpe" ) );
		assertTrue( isPermutationOptimized( "banana", "abnnaa" ) );
		assertTrue( isPermutationOptimized( "orange", "gareon" ) );
		assertTrue( isPermutationOptimized( "watermelon", "laeotrnwem" ) );

		assertFalse( isPermutationOptimized( "apple", "paape" ) );
		assertFalse( isPermutationOptimized( "banana", "arnnaa" ) );
		assertFalse( isPermutationOptimized( "orange", "gaeon" ) );
		assertFalse( isPermutationOptimized( "watermelon", "llaeotrnwem" ) );
	}

	/**
	 * @return true if both strings are permutation of each other
	 */
	public boolean isPermutationOptimized( String string1, String string2 ) {
		// assuming not null
		if( string1.length() != string2.length() ) {
			return false;
		}

		int[] letters = new int[128];
		char[] string1Contents = string1.toCharArray();

		/*
		 * store char count of first string in an array
		 * iterate over second string
		 * check for each character in array,
		 * if it is found > reduce count by 1.
		 * if remainder count is less than zero, it means count from first string were less than second string
		 * > return false
		 */
		for( int i = 0; i < string1Contents.length; i++ ) {
			char character = string1Contents[i];
			letters[character]++;
		}

		int stringLength = string2.length();
		for( int i = 0; i < stringLength; i++ ) {
			char character = string2.charAt( i );
			letters[character]--;
			if( letters[character] < 0 ) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @return true if both string are same, or both string can be same by
	 *         replacing/adding one of the character in either
	 */
	public static boolean oneEditAwayOrSame( String str1, String str2 ) {
		if( Math.abs( str1.length() - str2.length() ) > 1 ) {
			return false;
		}

		if( str1.length() == str2.length() ) {
			return oneReplaceAway( str1, str2 );
		}

		if( str1.length() + 1 == str2.length() ) {
			return oneInsertAway( str2, str1 );
		}

		if( str1.length() == str2.length() + 1 ) {
			return oneInsertAway( str1, str2 );
		}

		return false;
	}

	public static boolean oneReplaceAway( String str1, String str2 ) {
		boolean foundDifferrence = false;

		for( int i = 0; i < str1.length(); i++ ) {
			if( str1.charAt( i ) != str2.charAt( i ) ) {
				if( foundDifferrence ) {
					return false;
				}
				foundDifferrence = true;
			}
		}
		return true;
	}

	public static boolean oneInsertAway( String str1, String str2 ) {
		int str1Index = 0;
		int str2Index = 0;

		while( str1Index < str1.length() && str2Index < str2.length() ) {

			if( str1.charAt( str1Index ) != str2.charAt( str2Index ) ) {

				// means difference has been found already
				if( str1Index != str2Index ) {
					return false;
				}
				str1Index++;
			}
			else {
				str1Index++;
				str2Index++;
			}
		}
		return true;
	}

	// TODO explore more
	public Collection<String> permutation( String seedString ) {

		HashSet<String> uniquePermutations = new HashSet<>();
		permutation( "", seedString, uniquePermutations );

		System.out.println( "unique permutations > " + uniquePermutations );

		return uniquePermutations;
	}

	int recursion = 1;

	private void permutation( String prefix, String seedString, HashSet<String> uniquePermutations ) {
		recursion += 1;
		System.out.println( "recursion[" + recursion + "] prefix[" + prefix + "] seed[" + seedString + "]" );

		if( seedString.length() == 0 ) {
			uniquePermutations.add( prefix );

			System.out.println( prefix );
		}
		else {
			for( int i = 0; i < seedString.length(); i++ ) {
				String alteredSeed = seedString.substring( 0, i ) + seedString.substring( i + 1 );

				System.out.println( "recursion[" + recursion + "]  prefix[" + prefix + "] seed[" + seedString
						+ "] charAtI[" + seedString.charAt( i ) + "] sub1[" + seedString.substring( 0, i ) + "] sub2["
						+ seedString.substring( i + 1 ) + "] alteredSeed[" + alteredSeed + "]" );

				permutation( prefix + seedString.charAt( i ), alteredSeed, uniquePermutations );
			}
		}
	}

	/**
	 * @param string1 String1 to compare the pattern
	 * @param string2 String2 to compare the pattern
	 * @return true if string one character are present in string 2 in same sequence OR vice versa
	 */
	public static boolean isPatternMatchDP( String string1, String string2 ) {
		int stringOneLength = string1.length();
		int stringTwoLength = string2.length();

		// array matrix of length + 1
		int dpMatrix[][] = new int[stringOneLength + 1][stringTwoLength + 1];

		// iterating over string one
		for( int row = 1; row < stringOneLength + 1; row++ ) {

			// iterating over string 2
			for( int col = 1; col < stringTwoLength + 1; col++ ) {

				// compare character from both the strings for current respective index
				if( string1.charAt( row - 1 ) == string2.charAt( col - 1 ) ) {
					// if character in both string matched -
					// fill in array matrix >> by increasing value from previous matching outcome
					// which is stored in currentRow - 1 and currentColumn -1 position

					dpMatrix[row][col] = 1 + dpMatrix[row - 1][col - 1];
				}
				else {
					// if characters are not matching
					// fill in current matrix position by finding max of previous column or row
					// which simply means carry forward the last max match count
					dpMatrix[row][col] = Math.max( dpMatrix[row][col - 1], dpMatrix[row - 1][col] );
				}

			}

		}

		// return true if the last matrix value matches with string one or string two lengths
		// which means that either of these string's character are matched in other string

		return dpMatrix[stringOneLength][stringTwoLength] == stringOneLength
				|| dpMatrix[stringOneLength][stringTwoLength] == stringTwoLength;
	}

	/**
	 * @param string1 String1 to compare the pattern
	 * @param string2 String2 to compare the pattern
	 * @return true if string one character are present in string 2 in same sequence OR vice versa
	 */
	public static boolean isPatternMatch2( String string1, String string2 ) {
		int stringOneLength = string1.length();
		int stringTwoLength = string2.length();

		if( stringOneLength > stringTwoLength ) {
			String tempString = string1;
			string1 = string2;
			string2 = tempString;

			stringOneLength = string1.length();
			stringTwoLength = string2.length();
		}

		int string2Index = 0;

		// iterating over string one
		for( int string1Index = 0; string1Index < stringOneLength; string1Index++ ) {

			char string1Char = string1.charAt( string1Index );

			// iterating over string 2
			for( ; string2Index < stringTwoLength; string2Index++ ) {
				// compare str character starting from 0 to length
				if( string1Char == string2.charAt( string2Index ) ) {
					if( string1Index == string1.length() - 1 ) {
						return true;
					}
					break;
				}
				else if( string2Index == string2.length() - 1 ) {
					return false;
				}
			}
		}

		return false;
	}

	public static boolean isPatternMatchesPartially( String string1, String string2 ) {
		int stringOneLength = string1.length();
		int stringTwoLength = string2.length();

		int string2Index = 0;
		boolean string2Reset = false;
		boolean charMatch = false;

		for( int string1Index = 0; string1Index < stringOneLength; string1Index++ ) {
			char string1Char = string1.charAt( string1Index );

			for( ; string2Index < stringTwoLength; string2Index++ ) {
				if( string1Char == string2.charAt( string2Index ) ) {
					charMatch = true;
					if( string2Reset ) {
						return false;
					}
					if( string1Index == string1.length() - 1 ) {
						return true;
					}
					break;
				}
				else if( !string2Reset && string2Index == string2.length() - 1 ) {
					string2Index = 0;
					string2Reset = true;
				}
			}
		}
		return charMatch == true;
	}

	/**
	 * @param str1 String1 to compare the pattern
	 * @param str2 String2 to compare the pattern
	 * @return true if string one character are present in string 2 in same sequence OR vice versa
	 */
	public static String findLongestCommonSubsequenceDP( String str1, String str2 ) {
		int stringOneLength = str1.length();
		int stringTwoLength = str2.length();

		// array matrix of length + 1
		String dpMatrix[][] = new String[stringOneLength + 1][stringTwoLength + 1];

		// fill in the dpMatrix with blank string, otherwise
		for( String[] strArr : dpMatrix ) {
			for( int i = 0; i < strArr.length; i++ ) {
				strArr[i] = "";
			}
		}

		// iterating over string one
		for( int row = 1; row < stringOneLength + 1; row++ ) {

			// iterating over string 2
			for( int col = 1; col < stringTwoLength + 1; col++ ) {

				// compare character from both the strings for current respective index
				if( str1.charAt( row - 1 ) == str2.charAt( col - 1 ) ) {
					// if character in both string matched -
					// fill in array matrix >> by increasing value from previous matching outcome
					// which is stored in currentRow - 1 and currentColumn -1 position

					dpMatrix[row][col] = dpMatrix[row - 1][col - 1] + str1.charAt( row - 1 );
				}
				else {
					// if characters are not matching
					// fill in current matrix position by finding max of previous column or row
					// which simply means carry forward the last max match count
					dpMatrix[row][col] = dpMatrix[row][col - 1].length() > dpMatrix[row - 1][col].length()
							? dpMatrix[row][col - 1]
							: dpMatrix[row - 1][col];
				}

			}

		}

		// return true if the last matrix value matches with string one or string two lengths
		// which means that either of these string's character are matched in other string

		return dpMatrix[stringOneLength][stringTwoLength];
	}

	/**
	 * @param str1 String1 to compare the pattern
	 * @param str2 String2 to compare the pattern
	 * @return true if string one character are present in string 2 in same sequence OR vice versa
	 */
	public static String findLongestCommonSubsequence( String str1, String str2 ) {
		int stringOneLength = str1.length();
		int stringTwoLength = str2.length();

		int prevMatchedIndex = -1;

		for( int row = 0; row < stringOneLength; row++ ) {
			char string1Char = str1.charAt( row );
			// if found in map, replace index

			for( int col = 0; col < stringTwoLength; col++ ) {
				if( string1Char == str2.charAt( col ) ) {
					if( col < prevMatchedIndex ) {
						// not in sequence
						return null;
					}
					prevMatchedIndex = col;
				}
			}
		}

		return null;
	}

	/**
	 * TODO: Add tests
	 * 
	 * @param words list of unique strings
	 * @return list of pairs containing one string and its Semordnilap
	 *         Semordnilap == reverse of any given string
	 */
	public ArrayList<ArrayList<String>> semordnilap( String[] words ) {

		assertThat( words ).isNotNull();

		ArrayList<ArrayList<String>> semordinlapPairs = new ArrayList<>();
		HashSet<String> uniqueWords = new HashSet<>( words.length );

		for( String word : words ) {

			String reverseWord = new StringBuilder( word ).reverse().toString();

			if( uniqueWords.contains( reverseWord ) ) {
				ArrayList<String> semordinlaps = new ArrayList<>();
				semordinlaps.add( word );
				semordinlaps.add( reverseWord );
				semordinlapPairs.add( semordinlaps );

				uniqueWords.remove( word );
			}
			else {
				uniqueWords.add( word );
			}
		}

		return semordinlapPairs;
	}

	/**
	 * Time Complexity - O(n^2)
	 * Space Complexity - O(1)
	 * 
	 * Algorithm:
	 * Pick a char. Start expanding on both sides and compare.
	 * Expand from same char, and also from char + 1. Because palindrome could have both cases.
	 * Store the longest palindrome out of above two scans.
	 * Keep storing longest palindrome in each iteration.
	 * 
	 * @param str
	 * @return the longest palindrome substring from given string.
	 *         Assumption: There will be only one longest palindrome.
	 */
	public static String longestPalindromicSubstring( String str ) {

		String longestPalindrome = "";

		for( int index = 0; index < str.length(); index++ ) {

			String oddPalindrome = getPalindromSubstring( str, index, index );
			String evenPalindrome = getPalindromSubstring( str, index, index + 1 );

			String longerPalindrome = oddPalindrome.length() > evenPalindrome.length() ? oddPalindrome : evenPalindrome;

			if( longerPalindrome.length() > longestPalindrome.length() ) {
				longestPalindrome = longerPalindrome;
			}
		}

		return longestPalindrome;
	}

	private static String getPalindromSubstring( String str, int left, int right ) {

		while( ( left >= 0 && right < str.length() ) && str.charAt( left ) == str.charAt( right ) ) {
			left--;
			right++;
		}

		// left +1 because left was already decreased in loop
		return str.substring( left + 1, right );
	}

	/**
	 * Function to find the pairs of anagrams from given list of string
	 * Anagram == words which are made of same characters, irrespective of sequence
	 * 
	 * Algorithm:
	 * For each word > create hash for each character, and store in char array > and then to string
	 * Use above hash as key in hash map
	 * Keep adding every string with same hash to this map, against same key
	 * Return all collected pairs
	 * 
	 * Time Complexity - O(n*m), n = number of words, m = number of character in longest word
	 * Space Complexity - O(n)
	 * 
	 * TODO: Write test cases
	 * 
	 * @param words
	 * @return
	 */
	public static List<List<String>> groupAnagrams( List<String> words ) {

		assertThat( words ).isNotNull();

		Map<String, List<String>> anagramsMap = new HashMap<>();

		for( String word : words ) {
			char[] wordHash = new char[26];
			for( Character c : word.toCharArray() ) {
				wordHash[c - 'a']++;
			}

			String mapKey = new String( wordHash );
			anagramsMap.computeIfAbsent( mapKey, key -> new ArrayList<String>() ).add( word );
		}

		return new ArrayList( anagramsMap.values() );
	}

	/**
	 * Function to reverse the sequence of words in the given string.
	 * Word == set of characters without space, or set of space without characters
	 * 
	 * Algorithms:
	 * Parse the string based on space
	 * keep collecting the words in reverse order
	 * 
	 * Time Complexity - O(n)
	 * Space Complexity - O(n)
	 * 
	 * @param string
	 * @return String with words in reverse sequence, while maintaining the spaces also
	 */
	public static String reverseWordsInString( String string ) {

		assertThat( string ).isNotNull();

		StringBuilder reverseString = new StringBuilder();
		StringBuilder word = new StringBuilder();
		Character prevChar = null;

		for( Character currChar : string.toCharArray() ) {

			if( prevChar != null ) {
				boolean isSpaceToNonSpace = Character.isWhitespace( prevChar ) && !Character.isWhitespace( currChar );
				boolean isNonSpaceToSpace = !Character.isWhitespace( prevChar ) && Character.isWhitespace( currChar );

				if( isSpaceToNonSpace || isNonSpaceToSpace ) {
					reverseString.insert( 0, word.toString() );
					word = new StringBuilder();
				}
			}
			word.append( currChar );
			prevChar = currChar;
		}

		// inserting last word
		reverseString.insert( 0, word.toString() );

		return reverseString.toString();
	}

	/**
	 * Algorithm:
	 * keep collecting character in hash form, keep incrementing the occurence for a word
	 * Keep collecting characters, based on max of (occurence in current word, and previous)
	 * 
	 * Time Complexity - O(n*m), n = number of words, m = number of characters in longest word
	 * Space Complexity - O(n)
	 * 
	 * @param words list of words
	 * @return array of minimum number of characters required to form all the words
	 *         Consider, deed needs d twice, hence minimum number is 2
	 *         Consider, do and deed >> do needs d once, and deed needs twice. So minimum number is 2
	 */
	public static char[] minimumCharactersForWords( String[] words ) {
		assertThat( words ).isNotNull();

		if( words.length == 0 )
			return new char[] {};

		StringBuffer sb = new StringBuffer();
		char[] charMap = new char[255];

		for( String word : words ) {

			char[] tempCharMap = new char[255];
			for( Character c : word.toCharArray() ) {
				tempCharMap[c]++;
				if( tempCharMap[c] > charMap[c] ) {
					sb.append( c );
					charMap[c] = tempCharMap[c];
				}
			}

		}

		return sb.toString().toCharArray();
	}

	public static void main( String[] args ) {
		StringAlgorithms sa = new StringAlgorithms();
		sa.permutation( "sow" );
	}

}
