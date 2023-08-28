package com.vedantatree.psds.ds;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Class representing Graph and associated algorithms
 * 
 * Adjacency List -
 * An adjacency list is a simple way to represent a graph as a list of vertices,
 * where each vertex has a list of its adjacent vertices.
 * The index of list (first dimension of the array) will always give the vertex.
 * The second dimension of the array will have the vertices which are connected with above vertex.
 * 
 * Here's an example of an adjacency list for an undirected graph with 4 vertices.
 * 0: 1 3
 * 1: 0 2
 * 2: 1 3
 * 3: 0 2
 * 
 * In this example, vertex 0 has 1 and 3 as adjacent vertices, vertex 1 has 0 and 2 as adjacent vertices, and so on.
 * 
 * Adjacency Matrix -
 * An adjacency matrix is a two-dimensional array that represents the graph
 * by storing a 1 at position (i,j), if there is an edge from vertex i to vertex j,
 * and 0 otherwise.
 * 
 * Here's an example of an adjacency matrix for the same undirected graph:
 * - 0 1 2 3
 * 0 0 1 0 1
 * 1 1 0 1 0
 * 2 0 1 0 1
 * 3 1 0 1 0
 * 
 * In this example, there is an edge from vertex 0 to vertex 1 (represented by a 1 in position (0,1)),
 * an edge from vertex 1 to vertex 0 (represented by a 1 in position (1,0)), and so on.
 * 
 * TODO: Implement shortest path algorithms. A*, Dijkstra, Bellman ford, Floyd Warshall
 * TODO: There is one more approach to have graph in memory. Add that too.
 * TODO: Add algos for adjacency matrix. Currently, all are using adjacency list.
 * 
 * @author Mohit Gupta
 *
 */
public class XGraph {

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

		// faster way to track visited nodes, alternative = search in traversed list < which will be slower
		boolean visited[] = new boolean[vertices];

		// TODO why assume that first vertex is 0
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

			for( int neighbour : currentNodeEdges ) {

				if( !visited[neighbour] ) {
					visited[neighbour] = true;
					nodesToTraverse.add( neighbour );
				}
			}
		}

		return traversedNodes;
	}

	/**
	 * Function to traverse and collect nodes of graph using DFS
	 * 
	 * @param adjacencyList The graph
	 * @param vertices number of vertices in graph
	 * @return List of all the traversed nodes
	 */
	public static ArrayList<Integer> dfsGraphTraversal( int[][] adjacencyList, int vertices ) {

		// list to collect the traversed nodes
		ArrayList<Integer> traversedNodes = new ArrayList<>( vertices );

		// list to collect nodes to traverse
		LinkedList<Integer> nodesToTraverse = new LinkedList<Integer>();

		// to track the visited nodes.
		// This is faster way, alternative is to search in traversed nodes list < which will be slower
		boolean visited[] = new boolean[vertices];

		nodesToTraverse.add( 0 );

		while( !nodesToTraverse.isEmpty() ) {

			int currentNode = nodesToTraverse.remove();
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
					nodesToTraverse.addFirst( neighbour );
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
	 * @param adjacencyList The graph structure,
	 *        main array elements are the nodes/vertices
	 *        sub-array are the neighbors/connections vertex/vertices
	 * @return true if a cycle is found in graph, false otherwise
	 */
	public static boolean checkIfCyclic( int[][] adjacencyList ) {
		assertNotNull( adjacencyList );
		assertTrue( adjacencyList.length > 0 );

		int vertexCount = adjacencyList.length;

		// TODO do we need visited nodes and vertices in traversal both, or can one do??
		boolean[] visitedVertices = new boolean[vertexCount];
		boolean[] verticesInTraversal = new boolean[vertexCount];

		for( int vertexIndex = 0; vertexIndex < vertexCount; vertexIndex++ ) {

			if( visitedVertices[vertexIndex] ) {
				continue;
			}

			if( checkIfCyclicUsingDFS( adjacencyList, vertexIndex, visitedVertices, verticesInTraversal ) ) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param adjacencyList The graph structure
	 * @param vertexIndex the vertex index > for which this method will check if cycle exists
	 * @param visitedVertices all nodes which are already visited
	 * @param verticesInTraversal all branches which are currently being traversed
	 * @return true if cycle exist, false otherwise
	 */
	private static boolean checkIfCyclicUsingDFS( int[][] adjacencyList, int vertexIndex, boolean[] visitedVertices,
			boolean[] verticesInTraversal ) {

		int[] connectedVertices = adjacencyList[vertexIndex];

		if( connectedVertices == null || connectedVertices.length == 0 ) {
			return false;
		}

		visitedVertices[vertexIndex] = true;
		verticesInTraversal[vertexIndex] = true;

		for( int neighbour : connectedVertices ) {

			if( !visitedVertices[neighbour] ) {
				if( checkIfCyclicUsingDFS( adjacencyList, neighbour, visitedVertices, verticesInTraversal ) ) {
					return true;
				}
			}
			// if already in traversal >> means it is being traversed again
			else if( verticesInTraversal[neighbour] ) {
				return true;
			}
		}

		// setting back once traversal is done
		verticesInTraversal[vertexIndex] = false;

		return false;
	}

	// TODO: No use of predecessors and distances in current logic. Remove these

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

// private Node rootNode;
//
// private static class Node {
//
// private String name;
// private List<Node> children = new ArrayList<Node>();
//
// public Node( String name ) {
// this.name = name;
// }
// }
