package com.vedantatree.psds.ds;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Class representing Graph and associated algorithms
 * 
 * @author Mohit Gupta
 *
 */
public class XGraph
{

	public static boolean bfsGraph( ArrayList<ArrayList<Integer>> adjacencyList, int source, int dest, int vertices,
			int predecessors[], int distances[] )
	{

		LinkedList<Integer> traversalQueue = new LinkedList<Integer>();
		boolean visited[] = new boolean[vertices];

		for( int i = 0; i < vertices; i++ )
		{
			visited[i] = false;
			predecessors[i] = -1;
			distances[i] = Integer.MAX_VALUE;
		}

		traversalQueue.add( source );
		visited[source] = true;
		distances[source] = 0;

		while( !traversalQueue.isEmpty() )
		{
			int currentNode = traversalQueue.remove();
			ArrayList<Integer> currentNodeEdges = adjacencyList.get( currentNode );

			for( int i = 0; i < currentNodeEdges.size(); i++ )
			{
				int neighbour = currentNodeEdges.get( i );

				if( !visited[neighbour] )
				{
					visited[neighbour] = true;
					distances[neighbour] = distances[currentNode] + 1;
					predecessors[neighbour] = currentNode;
					traversalQueue.add( neighbour );

					if( neighbour == dest )
					{
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * Example of graph
	 * 0 > 1
	 * 2 > 1
	 * > 3
	 * 3 > 4
	 * 4 > 2 //cycle
	 * 
	 * @param graphAdjacencyList The graph structure,
	 *        main array elements are the nodes/vertices
	 *        sub-array are the neighbors/connections vertex/vertices
	 * @return true if a cycle is found in graph, false otherwise
	 */
	public static boolean checkIfCyclic( int[][] graphAdjacencyList )
	{
		assertNotNull( graphAdjacencyList );
		assertTrue( graphAdjacencyList.length > 0 );

		int graphVertexCount = graphAdjacencyList.length;

		// list collecting all nodes which are already visited
		boolean[] visitedNodes = new boolean[graphVertexCount];

		// all vertices in traversal as source node
		boolean[] verticesInTraversal = new boolean[graphVertexCount];

		// iterate through all the nodes one by one
		// if it is not visited yet, check for presence of cycle
		for( int vertexIndex = 0; vertexIndex < graphVertexCount; vertexIndex++ )
		{

			if( !visitedNodes[vertexIndex] )
			{
				if( checkIfCyclicUsingDFS( graphAdjacencyList, vertexIndex, visitedNodes, verticesInTraversal ) )
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @param graphAdjacentList The graph structure
	 * @param vertexIndex the vertex index > for which this method will check if cycle exists
	 * @param visitedVertices all nodes which are already visited
	 * @param verticesInTraversal all branches which are currently being traversed
	 * @return true if cycle exist, false otherwise
	 */
	private static boolean checkIfCyclicUsingDFS( int[][] graphAdjacentList, int vertexIndex, boolean[] visitedVertices,
			boolean[] verticesInTraversal )
	{

		// get vertex > array of all connected vertices
		int[] connectedVertices = graphAdjacentList[vertexIndex];

		// no connection for current vertex
		if( connectedVertices == null || connectedVertices.length == 0 )
		{
			return false;
		}

		// set flag for source vertex as visited, so we don't traverse it again if found.
		visitedVertices[vertexIndex] = true;

		// Setting source vertex as in traversal> indicating that this node is in traversal
		// If this is being picked again for traversal, it means a cycle
		verticesInTraversal[vertexIndex] = true;

		for( int neighbour : connectedVertices )
		{
			// if not visited yet, traverse it
			if( !visitedVertices[neighbour] )
			{
				if( checkIfCyclicUsingDFS( graphAdjacentList, neighbour, visitedVertices, verticesInTraversal ) )
				{
					return true;
				}
			}
			// otherwise if already visited, check if it is already in traversal i.e. source of this branch
			// which mean cycle
			else if( verticesInTraversal[neighbour] )
			{
				return true;
			}
		}

		// setting back to false, denoting that traversal is done
		verticesInTraversal[vertexIndex] = false;

		return false;
	}

	private static List<List<Integer>> createGraph()
	{
		int verticesCount = 5;

		/*
		 * list of graph nodes and edges
		 * Main list > index indicates the source
		 * Sub list at each index > has all the target vertex/nodes
		 * 
		 * 0 > 1
		 * 2 > 1
		 * > 3
		 * 3 > 4
		 * 4 > 2
		 */
		List<List<Integer>> graphAdjacencyList = new ArrayList<>();

		for( int i = 0; i < verticesCount; i++ )
		{
			graphAdjacencyList.add( new ArrayList<Integer>() );
		}

		addEdge( graphAdjacencyList, 0, 1 );
		addEdge( graphAdjacencyList, 2, 1 );

		addEdge( graphAdjacencyList, 2, 3 );
		addEdge( graphAdjacencyList, 3, 4 );
		addEdge( graphAdjacencyList, 4, 2 );

		return graphAdjacencyList;
	}

	private static void addEdge( List<List<Integer>> graphAdjacencyList, int sourceVertex, int targetVertex )
	{
		graphAdjacencyList.get( sourceVertex ).add( targetVertex );
	}

}
