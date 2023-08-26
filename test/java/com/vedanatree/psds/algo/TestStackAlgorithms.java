package com.vedanatree.psds.algo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.vedantatree.psds.Utils;
import com.vedantatree.psds.algo.StackAlgorithms;

import junit.framework.TestCase;


public class TestStackAlgorithms extends TestCase {

	public void testBalanceBrackets() {
		String str = "([])(){}(())()()";
		assertThat( StackAlgorithms.balancedBrackets( str ) ).isTrue();

		str = "()[]{}{";
		assertThat( StackAlgorithms.balancedBrackets( str ) ).isFalse();

		str = "(((((({{{{{[[[[[([)])]]]]]}}}}}))))))";
		assertThat( StackAlgorithms.balancedBrackets( str ) ).isFalse();

		str = "()()[{()})]";
		assertThat( StackAlgorithms.balancedBrackets( str ) ).isFalse();

		str = "(()())((()()()))";
		assertThat( StackAlgorithms.balancedBrackets( str ) ).isTrue();

		str = "{}()";
		assertThat( StackAlgorithms.balancedBrackets( str ) ).isTrue();

		str = "()([])";
		assertThat( StackAlgorithms.balancedBrackets( str ) ).isTrue();

		str = "((){{{{[]}}}})";
		assertThat( StackAlgorithms.balancedBrackets( str ) ).isTrue();

		str = "(((((([[[[[[{{{{{{{{{{{{()}}}}}}}}}}}}]]]]]]))))))((([])({})[])[])[]([]){}(())";
		assertThat( StackAlgorithms.balancedBrackets( str ) ).isTrue();

		str = ")[]}";
		assertThat( StackAlgorithms.balancedBrackets( str ) ).isFalse();

		str = "(a)";
		assertThat( StackAlgorithms.balancedBrackets( str ) ).isTrue();

		str = "(a(";
		assertThat( StackAlgorithms.balancedBrackets( str ) ).isFalse();

		str = "(141[])(){waga}((51afaw))()hh()";
		assertThat( StackAlgorithms.balancedBrackets( str ) ).isTrue();

		str = "aafwgaga()[]a{}{gggg";
		assertThat( StackAlgorithms.balancedBrackets( str ) ).isFalse();

		str = "(((((({{{{{safaf[[[[[([)]safsafsa)]]]]]}}}gawga}}))))))";
		assertThat( StackAlgorithms.balancedBrackets( str ) ).isFalse();

		str = "(agwgg)([ghhheah%&@Q])";
		assertThat( StackAlgorithms.balancedBrackets( str ) ).isTrue();
	}

	public void testSortStack() {

		List<Integer> stack = new ArrayList<>( Arrays.asList( -5, 2, -2, 4, 3, 1 ) );
		List<Integer> sortedStack = StackAlgorithms.sortStack( stack );

		Utils.assertListEquals( sortedStack, Arrays.asList( -5, -2, 1, 2, 3, 4 ) );

		stack = new ArrayList<>( Arrays.asList( 0, -2, 3, 4, 1, -9, 8 ) );
		sortedStack = StackAlgorithms.sortStack( stack );

		Utils.assertListEquals( sortedStack, Arrays.asList( -9, -2, 0, 1, 3, 4, 8 ) );

		stack = new ArrayList<>( Arrays.asList() );
		sortedStack = StackAlgorithms.sortStack( stack );

		Utils.assertListEquals( sortedStack, Arrays.asList() );

		stack = new ArrayList<>( Arrays.asList( 2, 33, 44, 2, -9, -7, -5, -2, -2, -2, 0 ) );
		sortedStack = StackAlgorithms.sortStack( stack );

		Utils.assertListEquals( sortedStack, Arrays.asList( -9, -7, -5, -2, -2, -2, 0, 2, 2, 33, 44 ) );

		stack = new ArrayList<>( Arrays.asList( 3, 4, 5, 1, 2, 2, 2, 1, 3, 4, 5, 3, 1, 3, -1, 2, 3 ) );
		sortedStack = StackAlgorithms.sortStack( stack );

		Utils.assertListEquals( sortedStack, Arrays.asList( -1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 5, 5 ) );

	}

}
