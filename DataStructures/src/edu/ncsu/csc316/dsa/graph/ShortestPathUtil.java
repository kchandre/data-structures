package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.priority_queue.AdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapAdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;

/**
 * Implements methods used for shortest path traversals of graphs
 * 
 * @author Gianna Mastrandrea
 *
 */
public class ShortestPathUtil {

	/**
	 * Implements Dijkstra's algorithm for shortest path costs
	 * 
	 * @param g   graph
	 * @param src source vertex
	 * @param     <V> vertex
	 * @param     <E> edge
	 * @return shortest path costs from starting vertex to every other vertex
	 */
	public static <V, E extends Weighted> Map<Vertex<V>, Integer> dijkstra(Graph<V, E> g, Vertex<V> src) {
		// Use a map to track the shortest path cost to each vertex
		Map<Vertex<V>, Integer> m = new LinearProbingHashMap<>();

		// Use an adaptable priority queue to order vertices by weight
		AdaptablePriorityQueue<Integer, Vertex<V>> q = new HeapAdaptablePriorityQueue<>();

		Map<Vertex<V>, Entry<Integer, Vertex<V>>> pqEntries = new LinearProbingHashMap<>();

		// Use set to track known vertices
		Set<Vertex<V>> known = new HashSet<>();

		// Initialize information for each vertex
		for (Vertex<V> v : g.vertices()) {
			if (v == src)
				m.put(v, 0);
			else
				m.put(v, Integer.MAX_VALUE);
			pqEntries.put(v, q.insert(m.get(v), v));
		}

		// Main loop
		while (!q.isEmpty()) {
			Vertex<V> u = q.deleteMin().getValue();
			// At this point, we know the shortest path to vertex u
			// so we can add this cost to our shortest path cost map
			m.put(u, m.get(u));
			known.add(u);
			for (Edge<E> e : g.outgoingEdges(u)) {
				Vertex<V> z = g.opposite(u, e);
				int r = e.getElement().getWeight() + m.get(u);
				if (!known.contains(z) && r < m.get(z)) {
					m.put(z, r);
					q.replaceKey(pqEntries.get(z), r);
				}
			}
		}
		return m;
	}

	/**
	 * Helper function that will provide map of edges in the shortest path tree
	 * 
	 * @param g         graph
	 * @param s         starting vertex
	 * @param distances map of shortest path distances
	 * @param           <V> vertex
	 * @param           <E> edge
	 * @return map of edges in shortest path tree
	 */
	public static <V, E extends Weighted> Map<Vertex<V>, Edge<E>> shortestPathTree(Graph<V, E> g, Vertex<V> s,
			Map<Vertex<V>, Integer> distances) {
		// Create a map to store edges in the shortest path tree
		Map<Vertex<V>, Edge<E>> m = new LinearProbingHashMap<>();

		for (Vertex<V> v : distances) {
			if (!v.equals(s)) {
				for (Edge<E> e : g.incomingEdges(v)) {
					Vertex<V> u = g.opposite(v, e);
					if (distances.get(v).equals(distances.get(u) + e.getElement().getWeight())) {
						m.put(v, e);
					}
				}
			}
		}
		return m;
	}
}
