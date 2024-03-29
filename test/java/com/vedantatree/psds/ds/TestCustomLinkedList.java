package com.vedantatree.psds.ds;

import java.util.ArrayList;

import junit.framework.TestCase;


/**
 * 
 * @author Mohit Gupta <mohitgupta.matrix@gmail.com>
 */
public class TestCustomLinkedList extends TestCase
{

	public TestCustomLinkedList()
	{
		// super.
	}

	public void testTestAddition()
	{
		testAddition( false );
	}

	// add items at head
	public void testAdditionAtHead()
	{

		XLinkedList<Integer> list = new XLinkedList<Integer>();

		for( int elementToAdd = 0; elementToAdd <= 100; elementToAdd++ )
		{
			list.addFirst( elementToAdd );

			list.printAllElements();
			assertTrue( list.containsElement( elementToAdd ) );
		}

	}

	// add items at tail
	private XLinkedList<Integer> testAddition( boolean single )
	{

		XLinkedList<Integer> list = new XLinkedList<Integer>( single );

		for( int elementToAdd = 0; elementToAdd <= 100; elementToAdd++ )
		{
			list.addElement( elementToAdd );

			list.printAllElements();
			assertTrue( list.containsElement( elementToAdd ) );
		}

		return list;
	}

	// Doubly - add items at specific index
	public void testAdditionAtIndex()
	{

		XLinkedList<Integer> list = new XLinkedList<>( false );
		list.addElement( 1 ).addElement( 2 ).addElement( 3 ).addElement( 4 ).addElement( 5 );

		list.insertElement( 3, 10 );
		assertTrue( list.getElement( 3 ) == 10 );

		list.insertElement( 5, 20 );
		list.printAllElements();

		assertTrue( list.getElement( 5 ) == 20 );

		list.insertElement( 0, -100 );
		assertTrue( list.getElement( 0 ) == -100 );
	}

	// Singly - add items at specific index
	public void testSingleAdditionAtIndex()
	{

		XLinkedList<Integer> list = new XLinkedList<>( true );
		list.addElement( 1 ).addElement( 2 ).addElement( 3 ).addElement( 4 ).addElement( 5 );

		list.insertElement( 3, 10 );
		assertTrue( list.getElement( 3 ) == 10 );

		list.insertElement( 5, 20 );
		list.printAllElements();

		assertTrue( list.getElement( 5 ) == 20 );

		list.insertElement( 0, -100 );
		assertTrue( list.getElement( 0 ) == -100 );
	}

	// get items by index
	public void testSingleGetElementByIndex()
	{
		XLinkedList<Integer> list = new XLinkedList<>( true );
		list.addElement( 1 ).addElement( 2 ).addElement( 3 ).addElement( 4 ).addElement( 5 );

		assertTrue( list.getElement( 2 ) == 3 );
		list.removeElement( 2 );
		assertFalse( list.getElement( 2 ) == 3 );
	}

	// remove given elements from the list
	public void testRemoveGivenElement()
	{
		XLinkedList<Integer> list = testAddition( false );

		for( int elementToRemove = 100; elementToRemove >= 0; elementToRemove-- )
		{
			assertTrue( list.removeElement( elementToRemove ) );

			list.printAllElements();
			assertFalse( list.containsElement( elementToRemove ) );
		}

		for( int elementToRemove = 100; elementToRemove >= 0; elementToRemove-- )
		{
			assertFalse( list.removeElement( elementToRemove ) );
		}
	}

	// remove given items from singly linkedlist
	public void testSingleRemoveGivenElement()
	{
		XLinkedList<Integer> list = testAddition( true );

		for( int elementToRemove = 100; elementToRemove >= 0; elementToRemove = elementToRemove - 2 )
		{
			assertTrue( list.removeElement( elementToRemove ) );

			list.printAllElements();
			assertFalse( list.containsElement( elementToRemove ) );
		}

	}

	// remove given items from singly linkedlist
	public void testSingleListRemoveFromHead()
	{
		XLinkedList<Integer> list = testAddition( true );

		for( int elementToRemove = 0; elementToRemove <= 100; elementToRemove++ )
		{
			assertTrue( list.removeElement( elementToRemove ) );

			list.printAllElements();
			assertFalse( list.containsElement( elementToRemove ) );
		}

		list.printAllElements();
	}

	// Singly - remove last item
	public void testSingleListRemoveLastNode()
	{
		XLinkedList<Integer> list = new XLinkedList<>( true );
		list.addElement( 1 ).addElement( 2 ).addElement( 3 ).addElement( 4 ).addElement( 5 ).addElement( 6 );

		assertTrue( list.removeElement( 6 ) );
		assertFalse( list.removeElement( 6 ) );

		list.printAllElementsBackwards();

		assertTrue( list.removeElement( 4 ) );
		list.printAllElementsBackwards();

		assertTrue( list.removeElement( 1 ) );

		list.printAllElements();
		list.printAllElementsBackwards();
	}

	// singly - get lagger node for a given element, lagging by given steps
	public void testSingleListGetLaggerNode()
	{
		XLinkedList<Integer> list = new XLinkedList<>( true );
		list.addElement( 1 ).addElement( 2 ).addElement( 3 ).addElement( 4 ).addElement( 5 );

		assertTrue( list.getLaggerNodeData( 4, 1 ) == 3 );
		assertTrue( list.getLaggerNodeData( 4, 2 ) == 2 );
		assertTrue( list.getLaggerNodeData( 5, 3 ) == 2 );
		assertTrue( list.getLaggerNodeData( 4, 4 ) == null );
		assertTrue( list.getLaggerNodeData( 8, 1 ) == null );
		assertTrue( list.getLaggerNodeData( 2, 2 ) == null );

	}

	public void testSingleListGetLaggerNodeFromLast()
	{
		XLinkedList<Integer> list = new XLinkedList<>( true );
		list.addElement( 1 ).addElement( 2 ).addElement( 3 ).addElement( 4 ).addElement( 5 ).addElement( 6 )
				.addElement( 7 );

		assertEquals( Integer.valueOf( 4 ), list.getLaggerNodeDataFromEnd( 3 ) );
		assertEquals( Integer.valueOf( 2 ), list.getLaggerNodeDataFromEnd( 5 ) );
		assertEquals( null, list.getLaggerNodeDataFromEnd( 7 ) );
		assertEquals( Integer.valueOf( 7 ), list.getLaggerNodeDataFromEnd( 0 ) );
	}

	// singly - get center node
	public void testSingleListGetCenterNode()
	{
		XLinkedList<Integer> list = new XLinkedList<>( true );
		list.addElement( 1 ).addElement( 2 ).addElement( 3 ).addElement( 4 ).addElement( 5 ).addElement( 6 );

		assertTrue( list.getLaggerNodeData( 6, list.getSize() / 2 ) == 3 );

	}

	// test adding element at head
	public void testAddAll()
	{
		XLinkedList<Integer> list = testAddition( false );

		ArrayList<Integer> listToAdd = new ArrayList<>();
		for( int element = 100; element >= 0; element-- )
		{
			listToAdd.add( element );
		}

		list.addAll( listToAdd );

		for( int element = 100; element >= 0; element-- )
		{
			assertTrue( list.containsElement( element ) );
		}
	}

	// --------------------------- Circular List cases

	public void testCircularCountNode()
	{
		XLinkedList<Integer> list = new XLinkedList<>( true );
		list.addElement( 1 ).addElement( 2 ).addElement( 3 ).addElement( 4 ).addElement( 5 );

		list.makeCircularAt( 0 );
		list.printAllElements();

		assertTrue( "size of circular list is not matching", list.calculateSizeOfCircularList() == 5 );
	}

	// ------------------- More LinkedList algorithms

	public void testDedup()
	{
		XLinkedList<Integer> list = new XLinkedList<Integer>();
		list.addElement( 5 ).addElement( 1 ).addElement( 2 ).addElement( 5 ).addElement( 3 ).addElement( 1 )
				.addElement( 4 ).addElement( 2 );

		list.dedup();
		list.printAllElements();

		assertEquals( "Size of list is not matching after dedup", 5, list.getSize() );
	}

	// add proper assertion
	public void testPartition()
	{
		XLinkedList<Integer> list = new XLinkedList<Integer>();
		list.addElement( 2 ).addElement( 5 ).addElement( 16 ).addElement( 1 ).addElement( 2 ).addElement( 5 )
				.addElement( 8 ).addElement( 7 );

		list.printAllElements();
		XLinkedListNode<Integer> newNode = list.partition( 5 );
		list.printAllElements( newNode, false );

		list = new XLinkedList<Integer>();
		list.addElement( 5 ).addElement( 5 ).addElement( 10 ).addElement( 1 ).addElement( 2 ).addElement( 1 )
				.addElement( 10 ).addElement( 5 );

		list.printAllElements();
		newNode = list.partition( 5 );
		list.printAllElements( newNode, false );

		list = new XLinkedList<Integer>();
		list.addElement( 5 ).addElement( 1 ).addElement( 10 );

		list.printAllElements();
		newNode = list.partition( 5 );
		list.printAllElements( newNode, false );
	}

	// TODO: Add proper assertion
	public void testAddAnotherList()
	{
		XLinkedList<Integer> list = new XLinkedList<Integer>();
		list.addElement( 5 ).addElement( 1 ).addElement( 2 );

		XLinkedList<Integer> anotherList = new XLinkedList<Integer>();
		anotherList.addElement( 9 ).addElement( 9 ).addElement( 5 );

		XLinkedListNode<Integer> resultNode = list.addAnotherListNumerically( anotherList );

		System.out.print( "Result: " );
		list.printAllElements( resultNode, false );

	}

	public void testIsPalindrome()
	{
		XLinkedList<Integer> list = new XLinkedList<Integer>();
		list.addElement( 5 ).addElement( 1 ).addElement( 2 ).addElement( 2 ).addElement( 1 ).addElement( 5 );

		assertTrue( "List is palindrom, but method returns false", list.isPalindrome() );

		list = new XLinkedList<Integer>();
		list.addElement( 5 ).addElement( 1 ).addElement( 2 ).addElement( 10 ).addElement( 2 ).addElement( 1 )
				.addElement( 5 );

		assertTrue( "List is palindrom, but method returns false", list.isPalindrome() );

		list = new XLinkedList<Integer>();
		list.addElement( 1 ).addElement( 1 ).addElement( 2 ).addElement( 10 ).addElement( 2 ).addElement( 1 )
				.addElement( 5 );

		assertTrue( "List is not palindrom, but method returns true", !list.isPalindrome() );
	}

	public void testFindIntersectionNodeData()
	{
		// TODO: For later, as it needs some tweak in data access or redesign
	}

}
