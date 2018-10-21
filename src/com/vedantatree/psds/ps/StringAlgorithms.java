package com.vedantatree.psds.ps;

import java.util.HashSet;


public class StringAlgorithms
{

	// TODO explore more
	public void permutation( String seedString )
	{
		HashSet<String> uniquePermutations = new HashSet<>();
		int numberOfPermutation = permutation( "", seedString, uniquePermutations );

		System.out.println( "unique permutations > " + uniquePermutations );
		System.out.println( "number of permutations. iterations[" + numberOfPermutation + "] unique["
				+ uniquePermutations.size() + "]" );
	}

	int recursion = 1;

	private int permutation( String prefix, String seedString, HashSet<String> uniquePermutations )
	{
		recursion += 1;
		System.out.println( "recursion[" + recursion + "] prefix[" + prefix + "] seed[" + seedString + "]" );
		
		int numberOfPermutation = 0;
		if( seedString.length() == 0 )
		{
			uniquePermutations.add( prefix );
			numberOfPermutation++;

			System.out.println( prefix );
		}
		else
		{
			for( int i = 0; i < seedString.length(); i++ )
			{
				String alteredSeed = seedString.substring( 0, i ) + seedString.substring( i + 1 );

				System.out.println( "recursion[" + recursion + "] sub1[" + seedString.substring( 0, i ) + "] sub2["
						+ seedString.substring( i + 1 ) + "] alteredSeed[" + alteredSeed + "]" );
				
				numberOfPermutation += permutation( prefix + seedString.charAt( i ), alteredSeed, uniquePermutations );
			}
		}
		return numberOfPermutation;
	}

	public static void main( String[] args )
	{
		StringAlgorithms sa = new StringAlgorithms();
		sa.permutation( "snow" );
	}

}
