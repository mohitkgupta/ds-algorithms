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
		else
		{
			if( previous != null )
			{
				previous.setNext( this );
			}

		}
	}

	void setSingle( boolean single )
	{
		this.single = single;
	}

	boolean getSingle()
	{
		return single;
	}

	E getData()
	{
		return data;
	}

	void setData( E data )
	{
		this.data = data;
	}

	XLinkedListNode getNext()
	{
		return next;
	}

	XLinkedListNode setNext( XLinkedListNode newNextNode )
	{
		XLinkedListNode currentNextNode = getNext();
		this.next = newNextNode;
		if( newNextNode != null && !single )
		{
			newNextNode.setPrevious( this );
		}
		return currentNextNode;
	}

	XLinkedListNode getPrevioius()
	{
		return previous;
	}

	XLinkedListNode setPrevious( XLinkedListNode newPreviousNode )
	{
		if( single )
		{
			throw new IllegalStateException( "Previous can be not set on Single linked list" );
		}

		XLinkedListNode currentPreviousNode = getPrevioius();
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
