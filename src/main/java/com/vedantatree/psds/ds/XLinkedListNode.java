package com.vedantatree.psds.ds;

class XLinkedListNode<E>
{

	private boolean				single	= false;
	private E					data;
	private XLinkedListNode<E>	next;
	private XLinkedListNode<E>	previous;

	XLinkedListNode()
	{
	}

	XLinkedListNode( XLinkedListNode<E> previous, E data, XLinkedListNode<E> next, boolean single )
	{
		setSingle( single );
		setData( data );
		setNext( next );

		if( !single )
		{
			setPrevious( previous );
		}
		else // if single
		{
			if( previous != null )
			{
				previous.setNext( this );
			}

		}
	}
	
	boolean isDouble()
	{
		return !single;
	}

	boolean isSingle()
	{
		return single;
	}

	void setSingle( boolean single )
	{
		this.single = single;
	}

	E getData()
	{
		return data;
	}

	void setData( E data )
	{
		this.data = data;
	}

	XLinkedListNode<E> getNext()
	{
		return next;
	}

	XLinkedListNode<E> setNext( XLinkedListNode<E> newNextNode )
	{
		XLinkedListNode<E> currentNextNode = getNext();
		
		this.next = newNextNode;
		
		// if doubly linked list, set this node as previous to next node
		if( newNextNode != null && isDouble())
		{
			newNextNode.setPrevious( this );
		}
		
		return currentNextNode;
	}

	XLinkedListNode<E> getPrevioius()
	{
		return previous;
	}

	XLinkedListNode<E> setPrevious( XLinkedListNode<E> newPreviousNode )
	{
		if( isSingle() )
		{
			throw new IllegalStateException( "Previous can be not set on Single linked list" );
		}

		XLinkedListNode<E> currentPreviousNode = getPrevioius();
		this.previous = newPreviousNode;

		// to avoid loop
		if( newPreviousNode != null && newPreviousNode.getNext() != this )
		{
			newPreviousNode.setNext( this );
		}
		return currentPreviousNode;
	}

	// TODO: Implement hashcode as well
	// public boolean equals( XLinkedListNode<E> node )
	// {
	// return this.getData() == null && node.getData() == null ? null : this.getData().equals( node.getData() );
	// }

}
