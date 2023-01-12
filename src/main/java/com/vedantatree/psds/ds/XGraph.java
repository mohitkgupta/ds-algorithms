package com.vedantatree.psds.ds;

import java.util.ArrayList;
import java.util.LinkedList;

public class XGraph {

	public static boolean bfsGraph(ArrayList<ArrayList<Integer>> adjacentList, int source, int dest, int vortexes,
			int predecessors[], int distances[]) {

		LinkedList<Integer> traversalQueue = new LinkedList<Integer>();
		boolean visited[] = new boolean[vortexes];
		
		for (int i = 0; i < vortexes; i++)
		{
			visited[i] = false;
			predecessors[i] = -1;
			distances[i] = Integer.MAX_VALUE;
		}
		
		traversalQueue.add(source);
		visited[source] = true;
		distances[source] = 0;
		
		while (!traversalQueue.isEmpty())
		{
			int currentNode = traversalQueue.remove();
			ArrayList<Integer> currentNodeEdges = adjacentList.get(currentNode);
			
			for (int i = 0; i < currentNodeEdges.size(); i++) 
			{
				int neighbour = currentNodeEdges.get(i);
				
				if (!visited[neighbour])
				{
					visited[neighbour] = true;
					distances[neighbour] = distances[currentNode] + 1;
					predecessors[neighbour] = currentNode;
					traversalQueue.add(neighbour);
					
					if (neighbour == dest)
					{
						return true;
					}
				}
			}
		}
		
		return false;
	}

}
