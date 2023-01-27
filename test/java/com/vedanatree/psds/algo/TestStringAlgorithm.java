package com.vedanatree.psds.algo;

import static org.assertj.core.api.Assertions.assertThat;

import com.vedantatree.psds.algo.StringAlgorithms;

import junit.framework.TestCase;


public class TestStringAlgorithm extends TestCase
{

	public static void testIsPatternMatch()
	{
		assertTrue( StringAlgorithms.isPatternMatchDP( "AXY", "ADXCPY" ) );

		assertTrue( StringAlgorithms.isPatternMatchDP( "ADXCPY", "AXY" ) );
	}

	public static void testIsPatternMatch2()
	{
		assertTrue( StringAlgorithms.isPatternMatch2( "AXY", "ADXCPY" ) );
		assertTrue( StringAlgorithms.isPatternMatch2( "ADXCPY", "AXY" ) );

		assertFalse( StringAlgorithms.isPatternMatch2( "AXYC", "ADXCPY" ) );
		assertTrue( StringAlgorithms.isPatternMatch2( "AXC", "ADXCPY" ) );

		assertFalse( StringAlgorithms.isPatternMatch2( "AYC", "ADXCPY" ) );

	}

	// StringAlgorithm.isPatternMatchesPartially
	public static void testIsPatternMatchPartially()
	{
		assertThat( StringAlgorithms.isPatternMatchesPartially( "AXY", "ADXCPY" ) ).isTrue();
		assertThat( StringAlgorithms.isPatternMatchesPartially( "ADCB", "KDBM" ) ).isTrue();
	}

	// StringAlgorithm.findLongestCommonSubsequenceDP
	public static void testFindLongestCommonSubsequenceDP()
	{
		String lcs = StringAlgorithms.findLongestCommonSubsequenceDP( "ADCBD", "KDBMCD" );
		assertThat( lcs ).isEqualTo( "DCD" );
		
		lcs = StringAlgorithms.findLongestCommonSubsequenceDP( "ADCB", "KDBMCD" );
		assertThat( lcs ).isEqualTo( "DC" );
		
		lcs = StringAlgorithms.findLongestCommonSubsequenceDP( "ADCBDKMC", "KDBM" );
		assertThat( lcs ).isEqualTo( "DBM" );
	}
}
