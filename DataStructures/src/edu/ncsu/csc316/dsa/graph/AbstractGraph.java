package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;

/**
 * Defines and implements methods necessary for all graph types
 * 
 * @author Gianna Mastrandrea
 *
 * @param <V> vertex
 * @param <E> edge
 */
public abstract class AbstractGraph<V, E> implements Graph<V, E> {

	private boolean isDirected;

	/**
	 * Creates new abstract graph
	 * 
	 * @param directed true if graph is directed
	 */
	public AbstractGraph(boolean directed) {
		setDirected(directed);
	}

	private void setDirected(boolean directed) {
		isDirected = directed;
	}

	/**
	 * Determines if graph is directed
	 * 
	 * @return true if graph is directed
	 */
	public boolean isDirected() {
		return isDirected;
	}

	@Override
	public Vertex<V>[] endVertices(Edge<E> edge) {
		return validate(edge).getEndpoints();
	}

	@Override
	public Vertex<V> opposite(Vertex<V> vertex, Edge<E> edge) {
		GraphEdge temp = validate(edge);
		Vertex<V>[] ends = temp.getEndpoints();
		if (ends[0] == vertex) {
			return ends[1];
		}
		if (ends[1] == vertex) {
			return ends[0];
		}
		throw new IllegalArgumentException("Vertex is not incident on this edge.");
	}

	/**
	 * Defines and implements necessary methods for all graph vertices
	 * 
	 * @author Gianna Mastrandrea
	 *
	 */
	protected class GraphVertex implements Vertex<V> {
		private V element;
		private Position<Vertex<V>> position;

		/**
		 * Creates new graph vertex
		 * 
		 * @param element vertex element
		 */
		public GraphVertex(V element) {
			setElement(element);
		}

		/**
		 * Sets element of vertex
		 * 
		 * @param element element to set
		 */
		public void setElement(V element) {
			this.element = element;
		}

		/**
		 * Gets element of vertex
		 * 
		 * @return element of vertex
		 */
		public V getElement() {
			return element;
		}

		/**
		 * Gets position of vertex
		 * 
		 * @return position of vertex
		 */
		public Position<Vertex<V>> getPosition() {
			return position;
		}

		/**
		 * Sets position of vertex
		 * 
		 * @param pos position to set
		 */
		public void setPosition(Position<Vertex<V>> pos) {
			position = pos;
		}
	}

	/**
	 * Defines and implements necessary methods for all graph edges
	 * 
	 * @author Gianna Mastrandrea
	 *
	 */
	protected class GraphEdge implements Edge<E> {
		private E element;
		private Vertex<V>[] endpoints;
		private Position<Edge<E>> position;

		/**
		 * Creates new graph edge with given vertices and data
		 * 
		 * @param element edge value
		 * @param v1      first vertex
		 * @param v2      second vertex
		 */
		@SuppressWarnings("unchecked")
		public GraphEdge(E element, Vertex<V> v1, Vertex<V> v2) {
			setElement(element);
			endpoints = (Vertex<V>[]) new Vertex[] { v1, v2 };
		}

		/**
		 * Sets element of edge
		 * 
		 * @param element element to set
		 */
		public void setElement(E element) {
			this.element = element;
		}

		/**
		 * Gets element of edge
		 * 
		 * @return element of edge
		 */
		public E getElement() {
			return element;
		}

		/**
		 * Gets end points of edge
		 * 
		 * @return array of end points of edge
		 */
		public Vertex<V>[] getEndpoints() {
			return endpoints;
		}

		/**
		 * Gets position of edge
		 * 
		 * @return position of edge
		 */
		public Position<Edge<E>> getPosition() {
			return position;
		}

		/**
		 * Sets position of edge
		 * 
		 * @param pos position to set
		 */
		public void setPosition(Position<Edge<E>> pos) {
			position = pos;
		}

		@Override
		public String toString() {
			return "Edge[element=" + element + "]";
		}
	}

	/**
	 * Validates edge of graph
	 * 
	 * @param e edge to validate
	 * @return true if edge is valid
	 */
	protected GraphEdge validate(Edge<E> e) {
		if (!(e instanceof AbstractGraph.GraphEdge)) {
			throw new IllegalArgumentException("Edge is not a valid graph edge.");
		}
		return (GraphEdge) e;
	}
}
