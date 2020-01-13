package edu.ncsu.csc316.dsa.graph;

/**
 * Defines methods necessary for all graphs to implement
 * 
 * @author Gianna Mastrandrea
 *
 * @param <V> vertex
 * @param <E> edge
 */
public interface Graph<V, E> {

	/**
	 * Determines if graph is directed
	 * 
	 * @return true if graph is directed
	 */
	boolean isDirected();

	/**
	 * Gets number of vertices in graph
	 * 
	 * @return number of vertices in graph
	 */
	int numVertices();

	/**
	 * Gets list of all vertices in graph
	 * 
	 * @return iterable of all vertices in graph
	 */
	Iterable<Vertex<V>> vertices();

	/**
	 * Gets number of edges in graph
	 * 
	 * @return number of edges in graph
	 */
	int numEdges();

	/**
	 * Gets list of all edges in graph
	 * 
	 * @return iterable of all edges in graph
	 */
	Iterable<Edge<E>> edges();

	/**
	 * Gets edge between two given vertices, if one exists
	 * 
	 * @param vertex1 first vertex
	 * @param vertex2 second vertex
	 * @return edge between two vertices
	 */
	Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2);

	/**
	 * Gets vertices connected by the given edge
	 * 
	 * @param edge target edge
	 * @return end vertices of edge
	 */
	Vertex<V>[] endVertices(Edge<E> edge);

	/**
	 * Gets vertex at opposite end of the edge
	 * 
	 * @param vertex target vertex
	 * @param edge   target edge
	 * @return vertex at opposite end of edge
	 */
	Vertex<V> opposite(Vertex<V> vertex, Edge<E> edge);

	/**
	 * Gets number of outgoing edges from given vertex
	 * 
	 * @param vertex target vertex
	 * @return number of outgoing edges
	 */
	int outDegree(Vertex<V> vertex);

	/**
	 * Gets number of incoming edges from given vertex
	 * 
	 * @param vertex target vertex
	 * @return number of incoming edges
	 */
	int inDegree(Vertex<V> vertex);

	/**
	 * Gives list of outgoing edges of a vertex
	 * 
	 * @param vertex target vertex
	 * @return iterable of outgoing edges
	 */
	Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex);

	/**
	 * Gives list of incoming edges of a vertex
	 * 
	 * @param vertex target vertex
	 * @return iterable of incoming edges
	 */
	Iterable<Edge<E>> incomingEdges(Vertex<V> vertex);

	/**
	 * Inserts vertex with given data
	 * 
	 * @param vertexData data of vertex
	 * @return vertex inserted
	 */
	Vertex<V> insertVertex(V vertexData);

	/**
	 * Inserts edge between two vertices
	 * 
	 * @param v1       first vertex
	 * @param v2       second vertex
	 * @param edgeData data edge contains
	 * @return inserted edge
	 */
	Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData);

	/**
	 * Removes vertex and consequent edges from graph
	 * 
	 * @param vertex target vertex to remove
	 * @return removed vertex
	 */
	Vertex<V> removeVertex(Vertex<V> vertex);

	/**
	 * Removes edge from graph
	 * 
	 * @param edge target edge to remove
	 * @return edge removed
	 */
	Edge<E> removeEdge(Edge<E> edge);

	/**
	 * Defines necessary methods for all edges to implement
	 * 
	 * @author Gianna Mastrandrea
	 *
	 * @param <E> generic object
	 */
	interface Edge<E> {
		/**
		 * Gets edge element
		 * 
		 * @return edge element
		 */
		E getElement();
	}

	/**
	 * Defines necessary methods for all vertices to implement
	 * 
	 * @author Gianna Mastrandrea
	 *
	 * @param <V> vertex
	 */
	interface Vertex<V> {
		/**
		 * Gets element at vertex
		 * 
		 * @return vertex element
		 */
		V getElement();
	}
}
