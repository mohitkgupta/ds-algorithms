package com.vedantatree.psds.ds;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.vedantatree.psds.Utils;


/**
 * Class representing Graph and associated algorithms
 * 
 * @author Mohit Gupta
 *
 */
public class XGraph {

	private Node rootNode;

	public static class Node {

		String		name;
		List<Node>	children	= new ArrayList<Node>();

		public Node( String name ) {
			this.name = name;
		}
	}

	public static boolean isSrcToDestReachableUsingBFS( ArrayList<ArrayList<Integer>> adjacencyList, int source,
			int dest, int vertices, int predecessors[], int distances[] ) {

		LinkedList<Integer> traversalQueue = new LinkedList<Integer>();
		boolean visited[] = new boolean[vertices];

		for( int i = 0; i < vertices; i++ ) {
			visited[i] = false;
			predecessors[i] = -1;
			distances[i] = Integer.MAX_VALUE;
		}

		traversalQueue.add( source );
		visited[source] = true;
		distances[source] = 0;

		while( !traversalQueue.isEmpty() ) {
			int currentNode = traversalQueue.remove();
			ArrayList<Integer> currentNodeEdges = adjacencyList.get( currentNode );

			for( int i = 0; i < currentNodeEdges.size(); i++ ) {
				int neighbour = currentNodeEdges.get( i );

				if( !visited[neighbour] ) {

					visited[neighbour] = true;
					distances[neighbour] = distances[currentNode] + 1;
					predecessors[neighbour] = currentNode; // TODO: can't predecessor be more than one, multiple
															// incoming
					traversalQueue.add( neighbour );

					if( neighbour == dest ) {
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * Function to traverse and collect nodes of graph using BFS
	 * 
	 * @param adjacencyList The graph
	 * @param vertices number of vertices in graph
	 * @return List of all the traversed nodes
	 */
	public static ArrayList<Integer> bfsGraphTraversal( int[][] adjacencyList, int vertices ) {

		ArrayList<Integer> traversedNodes = new ArrayList<>( vertices );
		LinkedList<Integer> nodesToTraverse = new LinkedList<Integer>();

		// to track the visited nodes.
		// This is faster way, alternative is to search in traversed nodes list < which will be slower
		boolean visited[] = new boolean[vertices];

		nodesToTraverse.add( 0 );
		visited[0] = true;

		while( !nodesToTraverse.isEmpty() ) {

			int currentNode = nodesToTraverse.remove();
			traversedNodes.add( currentNode );

			// no edges for this node
			if( currentNode > adjacencyList.length - 1 ) {
				continue;
			}

			int[] currentNodeEdges = adjacencyList[currentNode];

			for( int i = 0; i < currentNodeEdges.length; i++ ) {

				int neighbour = currentNodeEdges[i];

				if( !visited[neighbour] ) {
					visited[neighbour] = true;
					nodesToTraverse.add( neighbour );
				}
			}
		}

		return traversedNodes;
	}

	/**
	 * Function to traverse and collect nodes of graph using BFS
	 * 
	 * @param adjacencyList The graph
	 * @param vertices number of vertices in graph
	 * @return List of all the traversed nodes
	 */
	public static ArrayList<Integer> dfsGraphTraversal( int[][] adjacencyList, int vertices ) {

		// list to collect the traversed nodes
		ArrayList<Integer> traversedNodes = new ArrayList<>( vertices );

		// list to collect nodes to traverse
		LinkedList<Integer> traversalQueue = new LinkedList<Integer>();

		// to track the visited nodes. This is faster way, alternative is to search in traversed nodes list < which will
		// be slower
		boolean visited[] = new boolean[vertices];

		traversalQueue.add( 0 );

		while( !traversalQueue.isEmpty() ) {

			int currentNode = traversalQueue.remove();
			traversedNodes.add( currentNode );

			// no further edges for this node
			if( currentNode > adjacencyList.length - 1 ) {
				continue;
			}

			int[] children = adjacencyList[currentNode];

			for( int i = children.length - 1; i >= 0; i-- ) {

				int neighbour = children[i];

				if( !visited[neighbour] ) {
					visited[neighbour] = true;
					traversalQueue.addFirst( neighbour );
				}
			}

		}
		return traversedNodes;
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
	public static boolean checkIfCyclic( int[][] graphAdjacencyList ) {
		assertNotNull( graphAdjacencyList );
		assertTrue( graphAdjacencyList.length > 0 );

		int graphVertexCount = graphAdjacencyList.length;

		// TODO do we need visited nodes and vertices in traversal both, or can one do??
		boolean[] visitedNodes = new boolean[graphVertexCount];
		boolean[] verticesInTraversal = new boolean[graphVertexCount];

		for( int vertexIndex = 0; vertexIndex < graphVertexCount; vertexIndex++ ) {

			if( visitedNodes[vertexIndex] ) {
				continue;
			}

			if( checkIfCyclicUsingDFS( graphAdjacencyList, vertexIndex, visitedNodes, verticesInTraversal ) ) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param graphAdjacentList The graph structure
	 * @param vertexIndex the vertex index > for which this method will check if cycle exists
	 * @param visitedVertices all nodes which are already visited
	 * @param verticesInTraversal all branches which are currently being traversed
	 * @return true if cycle exist, false otherwise
	 */
	private static boolean checkIfCyclicUsingDFS( int[][] graphAdjacentList, int vertexIndex, boolean[] visitedVertices,
			boolean[] verticesInTraversal ) {

		int[] connectedVertices = graphAdjacentList[vertexIndex];

		if( connectedVertices == null || connectedVertices.length == 0 ) {
			return false;
		}

		visitedVertices[vertexIndex] = true;
		verticesInTraversal[vertexIndex] = true;

		for( int neighbour : connectedVertices ) {

			if( !visitedVertices[neighbour] ) {
				if( checkIfCyclicUsingDFS( graphAdjacentList, neighbour, visitedVertices, verticesInTraversal ) ) {
					return true;
				}
			}
			// if already visited, and in traversal >> means cycle
			else if( verticesInTraversal[neighbour] ) {
				return true;
			}
		}

		// setting back once traversal is done
		verticesInTraversal[vertexIndex] = false;

		return false;
	}

	private static List<List<Integer>> createGraph() {
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

		for( int i = 0; i < verticesCount; i++ ) {
			graphAdjacencyList.add( new ArrayList<Integer>() );
		}

		addEdge( graphAdjacencyList, 0, 1 );
		addEdge( graphAdjacencyList, 2, 1 );

		addEdge( graphAdjacencyList, 2, 3 );
		addEdge( graphAdjacencyList, 3, 4 );
		addEdge( graphAdjacencyList, 4, 2 );

		System.out.println( graphAdjacencyList );

		return graphAdjacencyList;
	}

	private static void addEdge( List<List<Integer>> graphAdjacencyList, int sourceVertex, int targetVertex ) {
		graphAdjacencyList.get( sourceVertex ).add( targetVertex );
	}

}
