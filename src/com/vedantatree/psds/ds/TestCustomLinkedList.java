package com.vedantatree.psds.ds;

import java.util.ArrayList;

import junit.framework.TestCase;


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

		CustomLinkedList<Integer> list = new CustomLinkedList<Integer>();

		for( int elementToAdd = 0; elementToAdd <= 100; elementToAdd++ )
		{
			list.addFirst( elementToAdd );

			list.printAllElements();
			assertTrue( list.containsElement( elementToAdd ) );
		}

	}

	// add items at tail
	private CustomLinkedList<Integer> testAddition( boolean single )
	{

		CustomLinkedList<Integer> list = new CustomLinkedList<Integer>( single );

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

		CustomLinkedList<Integer> list = new CustomLinkedList<>( false );
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

		CustomLinkedList<Integer> list = new CustomLinkedList<>( true );
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
		CustomLinkedList<Integer> list = new CustomLinkedList<>( true );
		list.addElement( 1 ).addElement( 2 ).addElement( 3 ).addElement( 4 ).addElement( 5 );

		assertTrue( list.getElement( 2 ) == 3 );
		list.removeElement( 2 );
		assertFalse( list.getElement( 2 ) == 3 );
	}

	// remove given elements from the list
	public void testRemoveGivenElement()
	{
		CustomLinkedList<Integer> list = testAddition( false );

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
		CustomLinkedList<Integer> list = testAddition( true );

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
		CustomLinkedList<Integer> list = testAddition( true );

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
		CustomLinkedList<Integer> list = new CustomLinkedList<>( true );
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
		CustomLinkedList<Integer> list = new CustomLinkedList<>( true );
		list.addElement( 1 ).addElement( 2 ).addElement( 3 ).addElement( 4 ).addElement( 5 );

		assertTrue( list.getLaggerNodeData( 4, 1 ) == 3 );
		assertTrue( list.getLaggerNodeData( 4, 2 ) == 2 );
		assertTrue( list.getLaggerNodeData( 5, 3 ) == 2 );
		assertTrue( list.getLaggerNodeData( 4, 4 ) == null );
		assertTrue( list.getLaggerNodeData( 8, 1 ) == null );

	}

	// singly - get center node
	public void testSingleListGetCenterNode()
	{
		CustomLinkedList<Integer> list = new CustomLinkedList<>( true );
		list.addElement( 1 ).addElement( 2 ).addElement( 3 ).addElement( 4 ).addElement( 5 ).addElement( 6 );

		assertTrue( list.getLaggerNodeData( 6, list.getSize() / 2 ) == 3 );

	}

	// test adding element at head
	public void testAddAll()
	{
		CustomLinkedList<Integer> list = testAddition( false );

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
		CustomLinkedList<Integer> list = new CustomLinkedList<>( true );
		list.addElement( 1 ).addElement( 2 ).addElement( 3 ).addElement( 4 ).addElement( 5 );

		list.makeCircularAt( 0 );
		list.printAllElements();

		assertTrue( "size of circular list is not matching", list.calculateSizeOfCircularList() == 5 );

	}

}
