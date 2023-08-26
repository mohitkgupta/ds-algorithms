package com.vedanatree.psds.algo;

import java.util.ArrayList;


public class TestClass {

	public static int calPoints( String[] ops ) {
		int result = 0;

		ArrayList<Integer> results = new ArrayList();

		for( int i = 0; i < ops.length; i++ ) {
			if( ops[i].equals( "C" ) ) {
				results.remove( results.size() - 1 );
			}
			else if( ops[i].equals( "D" ) ) {
				int lastResult = results.get( results.size() - 1 );
				results.add( lastResult * 2 );
			}
			else if( ops[i].equals( "+" ) ) {
				results.add( results.get( results.size() - 1 ) + results.get( results.size() - 2 ) );
			}
			else {
				results.add( Integer.parseInt( ops[i] ) );
			}
			System.out.println( results );
		}

		for( int res : results ) {
			System.out.println( res );
			result += res;
		}

		return result;
	}

	// variable names need to be improved. Not easy to understand, not supporting reading
	// we should add comments to make it easily understandable by reader.
	public static void main( String[] args ) {

		int result = calPoints( new String[]
			{ "5", "2", "C", "D", "+" } );
		System.out.println( result );

		// int array[][] = new int[3][3];
		//
		// int dp[][] = new int[3][3];
		//
		// int obs[][] = new int[3][3];
		//
		// for( int i = 1; i < 3; i++ ) {
		//
		// for( int j = 1; j < 3; j++ ) {
		// // obs value is not updated anywhere, so it will always go to first if condition
		// // updating values in dp to zero
		// if( obs[i][j] == 0 ) {
		// dp[i][j] = 0;
		// }
		// else // updating value in dp to sum of same column in previous row + previous column in same row
		// {
		// dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
		// }
		//
		// }
		// }
		//
		// // output should be zero
		// System.out.println( dp[2][2] );
	}

	// private MoneyAsNotes prepareVault(BigDecimal amount, MoneyAsNotes remainingVault) {
	//
	// System.out.println( "PreparingVault. amount[" + amount + "] remainingVault[" + remainingVault + "]" );
	//
	// if (amount.compareTo(BigDecimal.ZERO) < 0) {
	// return null;
	// }
	//
	// BigDecimal remainingAmount = amount;
	// MoneyAsNotes preparedVault = MoneyAsNotes.createEmpty();
	//
	// for (Denomination denomination : Denomination.valuesDescending()) {
	//
	// System.out.println( "remainingAmount[" + remainingAmount + "] new-denom[" + denomination.value() + "]" );
	//
	// if (remainingAmount.compareTo(denomination.value()) >= 0) {
	//
	// int denominationCount = remainingAmount.divide( denomination.value()).intValue();
	// System.out.println( "remainingAmount[" + remainingAmount + "] denomValue[" + denomination.value() + "]
	// denomCount[" + denominationCount + "]" );
	//
	// // can improve this logic by checking count in remainVault and removing those many
	// while (denominationCount > 0 && remainingVault.get(denomination) > 0)
	// {
	// remainingVault = remainingVault.remove( denomination, 1 );
	// preparedVault = preparedVault.add( denomination, 1 );
	//
	// denominationCount--;
	// remainingAmount = remainingAmount.subtract(denomination.value());
	// System.out.println( "remainingAmount[" + remainingAmount + "] denomValue[" + denomination.value() + "]
	// denomCount[" + denominationCount + "]" );
	// }
	//
	// }
	// }
	// System.out.println( "PREPARED-Vault[" + preparedVault + "] amountWas[" + amount + "]");
	// return preparedVault;
	// }

}
