package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.disjoint_set.DisjointSetForest;
import edu.ncsu.csc316.dsa.disjoint_set.UpTreeDisjointSetForest;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.priority_queue.AdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapAdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;

/**
 * Implements methods used in generating minimum spanning trees
 * 
 * @author Gianna Mastrandrea
 *
 */
public class MinimumSpanningTreeUtil {

	/**
	 * Implements Kruskal's minimum spanning tree algorithm
	 * 
	 * @param g graph
	 * @param   <V> vertex
	 * @param   <E> edge
	 * @return collection of edges in minimum spanning tree
	 */
	public static <V, E extends Weighted> PositionalList<Edge<E>> kruskal(Graph<V, E> g) {
		// Use a list to store the edges in the MST
		PositionalList<Edge<E>> t = new PositionalLinkedList<>();

		// Use a priority queue to order the edges
		AdaptablePriorityQueue<Integer, Edge<E>> q = new HeapAdaptablePriorityQueue<>();

		// Use a disjoint set to help with cycle detection
		DisjointSetForest<Vertex<V>> d = new UpTreeDisjointSetForest<>();

		// Insert edges into priority queue, partially ordered according to edge weight
		for (Edge<E> e : g.edges())
			q.insert(e.getElement().getWeight(), e);

		// Initialize each vertex into its own disjoint set
		for (Vertex<V> v : g.vertices())
			d.makeSet(v);

		int components = g.numVertices();

		// Main loop
		while (components > 1) {
			// Process edges in order of increasing weight
			Edge<E> e = q.deleteMin().getValue();
			Position<Vertex<V>> u = d.find(g.endVertices(e)[0]);
			Position<Vertex<V>> v = d.find(g.endVertices(e)[1]);
			if (!u.getElement().equals(v.getElement())) {
				d.union(u, v);
				t.addLast(e);
				components--;
			}
		}
		return t;
	}

	/**
	 * Implements Prim-Jarnik's minimum spanning tree algorithm
	 * 
	 * @param g graph
	 * @param   <V> vertex
	 * @param   <E> edge
	 * @return collection of edges in minimum spanning tree
	 */
	public static <V, E extends Weighted> PositionalList<Edge<E>> primJarnik(Graph<V, E> g) {
		AdaptablePriorityQueue<Integer, Vertex<V>> q = new HeapAdaptablePriorityQueue<>();
		Map<Vertex<V>, Integer> weights = new LinearProbingHashMap<>();
		Set<Vertex<V>> known = new HashSet<>();
		Map<Vertex<V>, Entry<Integer, Vertex<V>>> pqEntries = new LinearProbingHashMap<>();
		Map<Vertex<V>, Edge<E>> connectingEdges = new LinearProbingHashMap<>();

		PositionalList<Edge<E>> tree = new PositionalLinkedList<>();

		Vertex<V> src = g.vertices().iterator().next();

		for (Vertex<V> v : g.vertices()) {
			if (v == src) {
				weights.put(v, 0);
			} else {
				weights.put(v, Integer.MAX_VALUE);
			}
			pqEntries.put(v, q.insert(weights.get(v), v));
		}
		while (!q.isEmpty()) {
			Entry<Integer, Vertex<V>> entry = q.deleteMin();
			Vertex<V> u = entry.getValue();
			if (connectingEdges.get(u) != null) {
				tree.addLast(connectingEdges.get(u));
			}
			known.add(u);
			for (Edge<E> e : g.outgoingEdges(u)) {
				Vertex<V> z = g.opposite(u, e);
				int r = e.getElement().getWeight();
				if (!known.contains(z) && r < weights.get(z)) {
					weights.put(z, r);
					connectingEdges.put(z, e);
					q.replaceKey(pqEntries.get(z), r);
				}
			}
		}
		return tree;
	}

}
