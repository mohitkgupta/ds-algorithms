package com.vedanatree.psds.algo;

import static org.assertj.core.api.Assertions.assertThat;

import com.vedantatree.psds.algo.StringAlgorithms;

import junit.framework.TestCase;


public class TestStringAlgorithm extends TestCase {

	public static void testIsPatternMatch() {
		assertTrue( StringAlgorithms.isPatternMatchDP( "AXY", "ADXCPY" ) );

		assertTrue( StringAlgorithms.isPatternMatchDP( "ADXCPY", "AXY" ) );
	}

	public static void testIsPatternMatch2() {
		assertTrue( StringAlgorithms.isPatternMatch2( "AXY", "ADXCPY" ) );
		assertTrue( StringAlgorithms.isPatternMatch2( "ADXCPY", "AXY" ) );

		assertFalse( StringAlgorithms.isPatternMatch2( "AXYC", "ADXCPY" ) );
		assertTrue( StringAlgorithms.isPatternMatch2( "AXC", "ADXCPY" ) );

		assertFalse( StringAlgorithms.isPatternMatch2( "AYC", "ADXCPY" ) );

	}

	// StringAlgorithm.isPatternMatchesPartially
	public static void testIsPatternMatchPartially() {
		assertThat( StringAlgorithms.isPatternMatchesPartially( "AXY", "ADXCPY" ) ).isTrue();
		assertThat( StringAlgorithms.isPatternMatchesPartially( "ADCB", "KDBM" ) ).isTrue();
	}

	// StringAlgorithm.findLongestCommonSubsequenceDP
	public static void testFindLongestCommonSubsequenceDP() {
		String lcs = StringAlgorithms.findLongestCommonSubsequenceDP( "ADCBD", "KDBMCD" );
		assertThat( lcs ).isEqualTo( "DCD" );

		lcs = StringAlgorithms.findLongestCommonSubsequenceDP( "ADCB", "KDBMCD" );
		assertThat( lcs ).isEqualTo( "DC" );

		lcs = StringAlgorithms.findLongestCommonSubsequenceDP( "ADCBDKMC", "KDBM" );
		assertThat( lcs ).isEqualTo( "DBM" );
	}

	// StringAlgorithms.oneEditAwayOrSame
	public void testOneEditAwayOrSame() {
		assertEquals( true, StringAlgorithms.oneEditAwayOrSame( "abc", "abc" ) );
		assertEquals( true, StringAlgorithms.oneEditAwayOrSame( "abc", "acc" ) );
		assertEquals( true, StringAlgorithms.oneEditAwayOrSame( "abc", "abd" ) );
		assertEquals( true, StringAlgorithms.oneEditAwayOrSame( "abcd", "acd" ) );
		assertEquals( true, StringAlgorithms.oneEditAwayOrSame( "abc", "abcd" ) );
		assertEquals( true, StringAlgorithms.oneEditAwayOrSame( "abcd", "abc" ) );
		assertEquals( false, StringAlgorithms.oneEditAwayOrSame( "abc", "abcde" ) );
	}

	public void testLongestPalindromicSubstring() {

		assertThat( StringAlgorithms.longestPalindromicSubstring( "abaxyzzyxf" ) ).isEqualTo( "xyzzyx" );
		assertThat( StringAlgorithms.longestPalindromicSubstring( "noon high it is" ) ).isEqualTo( "noon" );
		assertThat( StringAlgorithms.longestPalindromicSubstring( "abcdefgfedcba" ) ).isEqualTo( "abcdefgfedcba" );
		assertThat( StringAlgorithms.longestPalindromicSubstring( "zzzzzzz2345abbbba5432zzbbababa" ) )
				.isEqualTo( "zz2345abbbba5432zz" );
		assertThat( StringAlgorithms.longestPalindromicSubstring( "z234a5abbba54a32z" ) ).isEqualTo( "5abbba5" );
		assertThat( StringAlgorithms.longestPalindromicSubstring( "aca" ) ).isEqualTo( "aca" );

	}

	public void testReverseWordsInString() {
		assertThat( StringAlgorithms.reverseWordsInString( "Algo is the best" ) ).isEqualTo( "best the is Algo" );
		assertThat( StringAlgorithms.reverseWordsInString( "..H,, hello 678" ) ).isEqualTo( "678 hello ..H,," );
		assertThat( StringAlgorithms.reverseWordsInString( "1 12 23 34 56" ) ).isEqualTo( "56 34 23 12 1" );
		assertThat( StringAlgorithms.reverseWordsInString( "this-is-one-word" ) ).isEqualTo( "this-is-one-word" );
		assertThat( StringAlgorithms.reverseWordsInString( "ab" ) ).isEqualTo( "ab" );
		assertThat( StringAlgorithms.reverseWordsInString( "words, separated, by, commas" ) )
				.isEqualTo( "commas by, separated, words," );
		assertThat( StringAlgorithms.reverseWordsInString( "a ab a" ) ).isEqualTo( "a ab a" );
		assertThat( StringAlgorithms.reverseWordsInString( " " ) ).isEqualTo( " " );
	}

	public void testMinimumCharactersForWords() {
		assertThat( StringAlgorithms.minimumCharactersForWords( new String[]
			{ "this", "that", "did", "deed", "them!", "a" } ) ).isEqualTo( new char[]
			{ 't', 'h', 'i', 's', 'a', 't', 'd', 'd', 'e', 'e', 'm', '!' } );
		assertThat( StringAlgorithms.minimumCharactersForWords( new String[]
			{ "cta", "cat", "tca", "tac", "a", "c", "t" } ) ).isEqualTo( new char[]
			{ 'c', 't', 'a' } );
		assertThat( StringAlgorithms.minimumCharactersForWords( new String[]
			{ "cta", "cat", "tca", "tac", "a", "c", "t" } ) ).isEqualTo( new char[]
			{ 'c', 't', 'a' } );
		assertThat( StringAlgorithms.minimumCharactersForWords( new String[]
			{ "a" } ) ).isEqualTo( new char[]
			{ 'a' } );
	}
}
