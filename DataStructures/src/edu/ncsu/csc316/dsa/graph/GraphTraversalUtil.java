package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;
import edu.ncsu.csc316.dsa.queue.Queue;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;

/**
 * Implements methods necessary for traversing a graph
 * 
 * @author Gianna Mastrandrea
 *
 */
public class GraphTraversalUtil {

	/**
	 * Traverses a graph using depth first search
	 * 
	 * @param graph target graph
	 * @param start starting vertex
	 * @param       <V> vertex
	 * @param       <E> edge
	 * @return all vertices in graph
	 */
	public static <V, E> Map<Vertex<V>, Edge<E>> depthFirstSearch(Graph<V, E> graph, Vertex<V> start) {
		// Use a set to keep track of visited vertices
		Set<Vertex<V>> s = new HashSet<>();

		// Use a map to keep track of the forest of discovery edges that discover each
		// vertex
		Map<Vertex<V>, Edge<E>> m = new LinearProbingHashMap<>();

		dfsHelper(graph, start, s, m);
		return m;

	}

	private static <V, E> void dfsHelper(Graph<V, E> graph, Vertex<V> u, Set<Vertex<V>> known,
			Map<Vertex<V>, Edge<E>> forest) {
		known.add(u);
		for (Edge<E> e : graph.outgoingEdges(u)) {
			Vertex<V> v = graph.opposite(u, e);
			if (!known.contains(v)) {
				forest.put(v, e);
				dfsHelper(graph, v, known, forest);
			}
		}
	}

	/**
	 * Traverses a graph using breadth first search
	 * 
	 * @param graph target graph
	 * @param start starting vertex
	 * @param       <V> vertex
	 * @param       <E> edge
	 * @return all vertices in graph
	 */
	public static <V, E> Map<Vertex<V>, Edge<E>> breadthFirstSearch(Graph<V, E> graph, Vertex<V> start) {
		// Use a set to keep track of visited vertices
		Set<Vertex<V>> s = new HashSet<Vertex<V>>();

		// Use a map to keep track of the forest of discovery edges that discover each
		// vertex
		Map<Vertex<V>, Edge<E>> m = new LinearProbingHashMap<>();

		// Use a queue to keep track of reachable vertices
		Queue<Vertex<V>> q = new ArrayBasedQueue<Vertex<V>>();

		s.add(start);
		q.enqueue(start);

		while (!q.isEmpty()) {
			Vertex<V> u = q.dequeue();
			for (Edge<E> e : graph.outgoingEdges(u)) {
				Vertex<V> w = graph.opposite(u, e);
				if (!s.contains(w)) {
					s.add(w);
					m.put(w, e);
					q.enqueue(w);
				}
			}
		}
		return m;
	}

}
