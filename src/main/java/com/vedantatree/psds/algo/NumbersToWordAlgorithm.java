package com.vedantatree.psds.algo;

import java.util.HashMap;
import java.util.Stack;


/**
 * TODO: Has a bug beyond Lakhs
 * 
 * 
 * @author Mohit Gupta <mohitgupta.matrix@gmail.com>
 */
public class NumbersToWordAlgorithm {

	private HashMap<Integer, String>	numberMap;
	private HashMap<Integer, String>	tensMap;

	public NumbersToWordAlgorithm() {
		initialNumberToWordsMapping();
	}

	public String convertNumberToWords( long number ) {
		System.out.println( "\n number to convert > " + number );

		int decimalPoint = 0;
		long removedNumber;

		Stack<String> collectedWords = new Stack<>();
		StringBuffer numberInWord = new StringBuffer();

		while( number >= 1 ) {
			int decimalToPass = decimalPoint;

			// System.out.println( "loop-start. decimal[" + decimalPoint + "] number[" + number + "] numberMap["
			// + numberMap.containsKey( (int) number ) );

			if( numberMap.containsKey( (int) number ) ) {
				removedNumber = number;
				number = 0;
			}
			else if( number > 10 && number < 100 || decimalPoint == 2 ) {
				removedNumber = number % 10;
				number = number / 10;
				decimalPoint = decimalPoint + 1;
			}
			else {
				removedNumber = number % 100;
				number = number / 100;
				decimalPoint = decimalPoint + 2;
			}

			collectedWords.push( denominationToWords( removedNumber, decimalToPass ) );

			// System.out.println( "Loop-End. number[" + number + "] decimal[" + decimalPoint + "] mod[" + removedNumber
			// + "] decimalPassed[" + decimalToPass + "] collectedWords[" + collectedWords + "]" );
		}

		while( !collectedWords.isEmpty() ) {
			numberInWord.append( collectedWords.pop() + " " );
		}

		System.out.println( "\n converted number> " + numberInWord );
		return numberInWord.toString();
	}

	private String numberToWord( int number ) {
		String numberInWord = numberMap.get( number );
		if( numberInWord == null ) {
			numberInWord = convertNumberToWords( number );
			if( numberInWord == null ) {

				throw new IllegalStateException( "Number not found in cache[" + number + "] numberMap > " + numberMap );
			}
		}
		return numberInWord;
	}

	private String tensToWord( int number ) {
		String numberInWord = tensMap.get( number );
		if( numberInWord == null ) {
			throw new IllegalStateException( "Number not found in cache[" + number + "] tensMap > " + tensMap );
		}
		return numberInWord;
	}

	// using Indian numbering system for test
	private String denominationToWords( long number, int decimal ) {
		String numberString;
		switch( decimal )
			{
				case 0:
					numberString = numberToWord( (int) number );
					break;
				case 1:
					numberString = tensToWord( (int) number );
					break;
				case 2:
					numberString = numberToWord( (int) number ) + " Hundred";
					break;
				case 3:
				case 4:
					numberString = numberToWord( (int) number ) + " Thousand";
					break;
				case 5:
				case 6:
					numberString = numberToWord( (int) number ) + " Lakh";
					break;
				case 7:
				case 8:
					numberString = numberToWord( (int) number ) + " Crore";
					break;
				case 9:
				case 10:
					numberString = numberToWord( (int) number ) + " Arab";
					break;
				case 11:
				case 12:
					numberString = numberToWord( (int) number ) + " Kharab";
					break;
				case 13:
				case 14:
					numberString = numberToWord( (int) number ) + " Neel";
					break;
				case 15:
				case 16:
					numberString = numberToWord( (int) number ) + " Padh";
					break;
				case 17:
				case 18:
					numberString = numberToWord( (int) number ) + " Shankh";
					break;
				default:
					throw new UnsupportedOperationException( "this denominator not supported > " + decimal );
			}
		return numberString;
	}

	private void initialNumberToWordsMapping() {
		numberMap = new HashMap<>();

		numberMap.put( 0, "Zero" );
		numberMap.put( 1, "One" );
		numberMap.put( 2, "Two" );
		numberMap.put( 3, "Three" );
		numberMap.put( 4, "Four" );
		numberMap.put( 5, "Five" );
		numberMap.put( 6, "Six" );
		numberMap.put( 7, "Seven" );
		numberMap.put( 8, "Eight" );
		numberMap.put( 9, "Nine" );

		numberMap.put( 10, "Ten" );

		numberMap.put( 11, "Eleven" );
		numberMap.put( 12, "Twelve" );
		numberMap.put( 13, "Thirteen" );
		numberMap.put( 14, "Fourteen" );
		numberMap.put( 15, "Fifteen" );
		numberMap.put( 16, "Sixteen" );
		numberMap.put( 17, "Seventeen" );
		numberMap.put( 18, "Eighteen" );
		numberMap.put( 19, "Nineteen" );

		numberMap.put( 20, "Twenty" );
		numberMap.put( 30, "Thirty" );
		numberMap.put( 40, "Fourty" );
		numberMap.put( 50, "Fifty" );
		numberMap.put( 60, "Sixty" );
		numberMap.put( 70, "Seventy" );
		numberMap.put( 80, "Eighty" );
		numberMap.put( 90, "Ninty" );

		tensMap = new HashMap<>();

		tensMap.put( 2, "Twenty" );
		tensMap.put( 3, "Thirty" );
		tensMap.put( 4, "Fourty" );
		tensMap.put( 5, "Fifty" );
		tensMap.put( 6, "Sixty" );
		tensMap.put( 7, "Seventy" );
		tensMap.put( 8, "Eighty" );
		tensMap.put( 9, "Ninty" );
	}

	public static void main( String[] args ) {

		NumbersToWordAlgorithm nwa = new NumbersToWordAlgorithm();
		nwa.convertNumberToWords( 113 );
		nwa.convertNumberToWords( 1457 );
		nwa.convertNumberToWords( 12313 );
		nwa.convertNumberToWords( 134123 );
		nwa.convertNumberToWords( 1587123 );
		nwa.convertNumberToWords( 91587123 );
		nwa.convertNumberToWords( 491587123 ); // TODO fix for double digit crore value
	}

}
