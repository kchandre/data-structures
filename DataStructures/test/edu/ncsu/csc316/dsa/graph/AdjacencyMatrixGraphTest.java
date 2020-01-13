package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;

/**
 * Tests the AdjacencyMatrixGraph class
 * 
 * @author Gianna Mastrandrea
 *
 */
public class AdjacencyMatrixGraphTest {

	private Graph<String, Integer> undirectedGraph;
	private Graph<String, Integer> directedGraph;

	/**
	 * Sets up graphs for testing
	 */
	@Before
	public void setUp() {
		undirectedGraph = new AdjacencyMatrixGraph<String, Integer>();
		directedGraph = new AdjacencyMatrixGraph<String, Integer>(true);
	}

	private void buildUndirectedSample() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");

		undirectedGraph.insertEdge(v1, v2, 5);
		undirectedGraph.insertEdge(v1, v3, 10);
		undirectedGraph.insertEdge(v1, v4, 15);
		undirectedGraph.insertEdge(v1, v5, 20);
		undirectedGraph.insertEdge(v2, v3, 25);
		undirectedGraph.insertEdge(v2, v4, 30);
		undirectedGraph.insertEdge(v2, v5, 35);
		undirectedGraph.insertEdge(v3, v4, 40);
		undirectedGraph.insertEdge(v3, v5, 45);
		undirectedGraph.insertEdge(v4, v5, 50);
	}

	private void buildDirectedSample() {
		Vertex<String> v1 = directedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = directedGraph.insertVertex("Asheville");
		Vertex<String> v3 = directedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = directedGraph.insertVertex("Durham");
		Vertex<String> v5 = directedGraph.insertVertex("Greenville");
		Vertex<String> v6 = directedGraph.insertVertex("Boone");

		directedGraph.insertEdge(v1, v2, 5);
		directedGraph.insertEdge(v1, v3, 10);
		directedGraph.insertEdge(v1, v4, 15);
		directedGraph.insertEdge(v1, v5, 20);
		directedGraph.insertEdge(v2, v3, 25);
		directedGraph.insertEdge(v2, v4, 30);
		directedGraph.insertEdge(v2, v5, 35);
		directedGraph.insertEdge(v3, v4, 40);
		directedGraph.insertEdge(v3, v5, 45);
		directedGraph.insertEdge(v4, v5, 50);
		directedGraph.insertEdge(v5, v6, 55);
	}

	/**
	 * Tests numVertices()
	 */
	@Test
	public void testNumVertices() {
		assertEquals(0, undirectedGraph.numVertices());
		buildUndirectedSample();
		assertEquals(5, undirectedGraph.numVertices());

		assertEquals(0, directedGraph.numVertices());
		buildDirectedSample();
		assertEquals(6, directedGraph.numVertices());

		Vertex<String> v0 = directedGraph.insertVertex("vertex0");
		assertEquals(7, directedGraph.numVertices());

		Vertex<String> v1 = undirectedGraph.insertVertex("vertex1");
		assertEquals(6, undirectedGraph.numVertices());

		directedGraph.removeVertex(v0);
		assertEquals(6, directedGraph.numVertices());
		directedGraph.removeVertex(v0);
		assertEquals(6, directedGraph.numVertices());

		undirectedGraph.removeVertex(v1);
		assertEquals(5, undirectedGraph.numVertices());
		undirectedGraph.removeVertex(v1);
		assertEquals(5, undirectedGraph.numVertices());
	}

	/**
	 * Tests vertices()
	 */
	@Test
	public void testVertices() {
		// We cannot call buildUndirectedSample() because
		// then we would not be able to reference specific edges
		// or vertices when testing
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		undirectedGraph.insertEdge(v1, v2, 5);
		undirectedGraph.insertEdge(v1, v3, 10);
		undirectedGraph.insertEdge(v1, v4, 15);
		undirectedGraph.insertEdge(v1, v5, 20);
		undirectedGraph.insertEdge(v2, v3, 25);
		undirectedGraph.insertEdge(v2, v4, 30);
		undirectedGraph.insertEdge(v2, v5, 35);
		undirectedGraph.insertEdge(v3, v4, 40);
		undirectedGraph.insertEdge(v3, v5, 45);
		undirectedGraph.insertEdge(v4, v5, 50);

		Iterator<Vertex<String>> it = undirectedGraph.vertices().iterator();
		assertTrue(it.hasNext());

		assertEquals(it.next(), v1);
		assertEquals(it.next(), v2);
		assertEquals(it.next(), v3);
		assertEquals(it.next(), v4);
		assertEquals(it.next(), v5);

		assertFalse(it.hasNext());

		// DIRECTED
		// We cannot call buildDirectedSample() because
		// then we would not be able to reference specific edges
		// or vertices when testing
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		Vertex<String> v6 = directedGraph.insertVertex("Boone");
		directedGraph.insertEdge(v1, v2, 5);
		directedGraph.insertEdge(v1, v3, 10);
		directedGraph.insertEdge(v1, v4, 15);
		directedGraph.insertEdge(v1, v5, 20);
		directedGraph.insertEdge(v2, v3, 25);
		directedGraph.insertEdge(v2, v4, 30);
		directedGraph.insertEdge(v2, v5, 35);
		directedGraph.insertEdge(v3, v4, 40);
		directedGraph.insertEdge(v3, v5, 45);
		directedGraph.insertEdge(v4, v5, 50);
		directedGraph.insertEdge(v5, v6, 55);

		it = directedGraph.vertices().iterator();
		assertTrue(it.hasNext());

		assertEquals(it.next(), v1);
		assertEquals(it.next(), v2);
		assertEquals(it.next(), v3);
		assertEquals(it.next(), v4);
		assertEquals(it.next(), v5);
		assertEquals(it.next(), v6);

		assertFalse(it.hasNext());

	}

	/**
	 * Tests numEdges()
	 */
	@Test
	public void testNumEdges() {
		// Undirected
		assertEquals(undirectedGraph.numEdges(), 0);
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		undirectedGraph.insertEdge(v1, v3, 10);
		undirectedGraph.insertEdge(v1, v4, 15);
		undirectedGraph.insertEdge(v1, v5, 20);
		undirectedGraph.insertEdge(v2, v3, 25);
		undirectedGraph.insertEdge(v2, v4, 30);
		undirectedGraph.insertEdge(v2, v5, 35);
		undirectedGraph.insertEdge(v3, v4, 40);
		undirectedGraph.insertEdge(v3, v5, 45);
		undirectedGraph.insertEdge(v4, v5, 50);
		assertEquals(undirectedGraph.numEdges(), 10);
		undirectedGraph.removeEdge(e1);
		assertEquals(undirectedGraph.numEdges(), 9);
		assertEquals(undirectedGraph.numVertices(), 5);
		undirectedGraph.removeEdge(e1);
		assertEquals(undirectedGraph.numEdges(), 9);

		// Directed
		assertEquals(directedGraph.numEdges(), 0);
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		Vertex<String> v6 = directedGraph.insertVertex("Boone");
		e1 = directedGraph.insertEdge(v1, v2, 5);
		directedGraph.insertEdge(v1, v3, 10);
		directedGraph.insertEdge(v1, v4, 15);
		directedGraph.insertEdge(v1, v5, 20);
		directedGraph.insertEdge(v2, v3, 25);
		directedGraph.insertEdge(v2, v4, 30);
		directedGraph.insertEdge(v2, v5, 35);
		directedGraph.insertEdge(v3, v4, 40);
		directedGraph.insertEdge(v3, v5, 45);
		directedGraph.insertEdge(v4, v5, 50);
		directedGraph.insertEdge(v5, v6, 55);
		assertEquals(directedGraph.numEdges(), 11);
		directedGraph.removeEdge(e1);
		assertEquals(directedGraph.numEdges(), 10);
		assertEquals(directedGraph.numVertices(), 6);
		directedGraph.removeEdge(e1);
		assertEquals(directedGraph.numEdges(), 10);
	}

	/**
	 * Tests edges()
	 */
	@Test
	public void testEdges() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
		Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
		Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
		Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
		Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
		Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
		Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
		Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
		Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);

		Iterator<Edge<Integer>> it = undirectedGraph.edges().iterator();
		assertTrue(it.hasNext());

		assertEquals(it.next(), e1);
		assertEquals(it.next(), e2);
		assertEquals(it.next(), e3);
		assertEquals(it.next(), e4);
		assertEquals(it.next(), e5);
		assertEquals(it.next(), e6);
		assertEquals(it.next(), e7);
		assertEquals(it.next(), e8);
		assertEquals(it.next(), e9);
		assertEquals(it.next(), e10);

		assertFalse(it.hasNext());

		// DIRECTED
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		Vertex<String> v6 = directedGraph.insertVertex("Boone");
		e1 = directedGraph.insertEdge(v1, v2, 5);
		e2 = directedGraph.insertEdge(v1, v3, 10);
		e3 = directedGraph.insertEdge(v1, v4, 15);
		e4 = directedGraph.insertEdge(v1, v5, 20);
		e5 = directedGraph.insertEdge(v2, v3, 25);
		e6 = directedGraph.insertEdge(v2, v4, 30);
		e7 = directedGraph.insertEdge(v2, v5, 35);
		e8 = directedGraph.insertEdge(v3, v4, 40);
		e9 = directedGraph.insertEdge(v3, v5, 45);
		e10 = directedGraph.insertEdge(v4, v5, 50);
		Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);

		it = directedGraph.edges().iterator();
		assertTrue(it.hasNext());

		assertEquals(it.next(), e1);
		assertEquals(it.next(), e2);
		assertEquals(it.next(), e3);
		assertEquals(it.next(), e4);
		assertEquals(it.next(), e5);
		assertEquals(it.next(), e6);
		assertEquals(it.next(), e7);
		assertEquals(it.next(), e8);
		assertEquals(it.next(), e9);
		assertEquals(it.next(), e10);
		assertEquals(it.next(), e11);

		assertFalse(it.hasNext());

	}

	/**
	 * Tests getEdge()
	 */
	@Test
	public void testGetEdge() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
		Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
		Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
		Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
		Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
		Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
		Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
		Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
		Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);

		assertEquals(e1, undirectedGraph.getEdge(v1, v2));
		assertEquals(e2, undirectedGraph.getEdge(v1, v3));
		assertEquals(e3, undirectedGraph.getEdge(v1, v4));
		assertEquals(e4, undirectedGraph.getEdge(v1, v5));
		assertEquals(e5, undirectedGraph.getEdge(v2, v3));
		assertEquals(e6, undirectedGraph.getEdge(v2, v4));
		assertEquals(e7, undirectedGraph.getEdge(v2, v5));
		assertEquals(e8, undirectedGraph.getEdge(v3, v4));
		assertEquals(e9, undirectedGraph.getEdge(v3, v5));
		assertEquals(e10, undirectedGraph.getEdge(v4, v5));

		// DIRECTED
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		v6 = directedGraph.insertVertex("Boone");
		e1 = directedGraph.insertEdge(v1, v2, 5);
		e2 = directedGraph.insertEdge(v1, v3, 10);
		e3 = directedGraph.insertEdge(v1, v4, 15);
		e4 = directedGraph.insertEdge(v1, v5, 20);
		e5 = directedGraph.insertEdge(v2, v3, 25);
		e6 = directedGraph.insertEdge(v2, v4, 30);
		e7 = directedGraph.insertEdge(v2, v5, 35);
		e8 = directedGraph.insertEdge(v3, v4, 40);
		e9 = directedGraph.insertEdge(v3, v5, 45);
		e10 = directedGraph.insertEdge(v4, v5, 50);
		Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);

		assertEquals(e1, directedGraph.getEdge(v1, v2));
		assertEquals(e2, directedGraph.getEdge(v1, v3));
		assertEquals(e3, directedGraph.getEdge(v1, v4));
		assertEquals(e4, directedGraph.getEdge(v1, v5));
		assertEquals(e5, directedGraph.getEdge(v2, v3));
		assertEquals(e6, directedGraph.getEdge(v2, v4));
		assertEquals(e7, directedGraph.getEdge(v2, v5));
		assertEquals(e8, directedGraph.getEdge(v3, v4));
		assertEquals(e9, directedGraph.getEdge(v3, v5));
		assertEquals(e10, directedGraph.getEdge(v4, v5));
		assertEquals(e11, directedGraph.getEdge(v5, v6));

	}

	/**
	 * Tests endVertices()
	 */
	@Test
	public void testEndVertices() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
		Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
		Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
		Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
		Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
		Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
		Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
		Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
		Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);

		assertEquals(v1, undirectedGraph.endVertices(e1)[0]);
		assertEquals(v2, undirectedGraph.endVertices(e1)[1]);
		assertEquals(v1, undirectedGraph.endVertices(e2)[0]);
		assertEquals(v3, undirectedGraph.endVertices(e2)[1]);
		assertEquals(v1, undirectedGraph.endVertices(e3)[0]);
		assertEquals(v4, undirectedGraph.endVertices(e3)[1]);
		assertEquals(v1, undirectedGraph.endVertices(e4)[0]);
		assertEquals(v5, undirectedGraph.endVertices(e4)[1]);
		assertEquals(v2, undirectedGraph.endVertices(e5)[0]);
		assertEquals(v3, undirectedGraph.endVertices(e5)[1]);
		assertEquals(v2, undirectedGraph.endVertices(e6)[0]);
		assertEquals(v4, undirectedGraph.endVertices(e6)[1]);
		assertEquals(v2, undirectedGraph.endVertices(e7)[0]);
		assertEquals(v5, undirectedGraph.endVertices(e7)[1]);
		assertEquals(v3, undirectedGraph.endVertices(e8)[0]);
		assertEquals(v4, undirectedGraph.endVertices(e8)[1]);
		assertEquals(v3, undirectedGraph.endVertices(e9)[0]);
		assertEquals(v5, undirectedGraph.endVertices(e9)[1]);
		assertEquals(v4, undirectedGraph.endVertices(e10)[0]);
		assertEquals(v5, undirectedGraph.endVertices(e10)[1]);

		// DIRECTED
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		v6 = directedGraph.insertVertex("Boone");
		e1 = directedGraph.insertEdge(v1, v2, 5);
		e2 = directedGraph.insertEdge(v1, v3, 10);
		e3 = directedGraph.insertEdge(v1, v4, 15);
		e4 = directedGraph.insertEdge(v1, v5, 20);
		e5 = directedGraph.insertEdge(v2, v3, 25);
		e6 = directedGraph.insertEdge(v2, v4, 30);
		e7 = directedGraph.insertEdge(v2, v5, 35);
		e8 = directedGraph.insertEdge(v3, v4, 40);
		e9 = directedGraph.insertEdge(v3, v5, 45);
		e10 = directedGraph.insertEdge(v4, v5, 50);
		Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);

		assertEquals(v1, directedGraph.endVertices(e1)[0]);
		assertEquals(v2, directedGraph.endVertices(e1)[1]);
		assertEquals(v1, directedGraph.endVertices(e2)[0]);
		assertEquals(v3, directedGraph.endVertices(e2)[1]);
		assertEquals(v1, directedGraph.endVertices(e3)[0]);
		assertEquals(v4, directedGraph.endVertices(e3)[1]);
		assertEquals(v1, directedGraph.endVertices(e4)[0]);
		assertEquals(v5, directedGraph.endVertices(e4)[1]);
		assertEquals(v2, directedGraph.endVertices(e5)[0]);
		assertEquals(v3, directedGraph.endVertices(e5)[1]);
		assertEquals(v2, directedGraph.endVertices(e6)[0]);
		assertEquals(v4, directedGraph.endVertices(e6)[1]);
		assertEquals(v2, directedGraph.endVertices(e7)[0]);
		assertEquals(v5, directedGraph.endVertices(e7)[1]);
		assertEquals(v3, directedGraph.endVertices(e8)[0]);
		assertEquals(v4, directedGraph.endVertices(e8)[1]);
		assertEquals(v3, directedGraph.endVertices(e9)[0]);
		assertEquals(v5, directedGraph.endVertices(e9)[1]);
		assertEquals(v4, directedGraph.endVertices(e10)[0]);
		assertEquals(v5, directedGraph.endVertices(e10)[1]);
		assertEquals(v5, directedGraph.endVertices(e11)[0]);
		assertEquals(v6, directedGraph.endVertices(e11)[1]);

	}

	/**
	 * Tests opposite()
	 */
	@Test
	public void testOpposite() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
		Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
		Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
		Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
		Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
		Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
		Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
		Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
		Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);

		assertEquals(v2, undirectedGraph.opposite(v1, e1));
		assertEquals(v1, undirectedGraph.opposite(v2, e1));
		assertEquals(v1, undirectedGraph.opposite(v3, e2));
		assertEquals(v3, undirectedGraph.opposite(v1, e2));
		assertEquals(v1, undirectedGraph.opposite(v4, e3));
		assertEquals(v4, undirectedGraph.opposite(v1, e3));
		assertEquals(v1, undirectedGraph.opposite(v5, e4));
		assertEquals(v5, undirectedGraph.opposite(v1, e4));
		assertEquals(v2, undirectedGraph.opposite(v3, e5));
		assertEquals(v3, undirectedGraph.opposite(v2, e5));
		assertEquals(v2, undirectedGraph.opposite(v4, e6));
		assertEquals(v4, undirectedGraph.opposite(v2, e6));
		assertEquals(v2, undirectedGraph.opposite(v5, e7));
		assertEquals(v5, undirectedGraph.opposite(v2, e7));
		assertEquals(v3, undirectedGraph.opposite(v4, e8));
		assertEquals(v4, undirectedGraph.opposite(v3, e8));
		assertEquals(v3, undirectedGraph.opposite(v5, e9));
		assertEquals(v5, undirectedGraph.opposite(v3, e9));
		assertEquals(v4, undirectedGraph.opposite(v5, e10));
		assertEquals(v5, undirectedGraph.opposite(v4, e10));

		// DIRECTED
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		v6 = directedGraph.insertVertex("Boone");
		e1 = directedGraph.insertEdge(v1, v2, 5);
		e2 = directedGraph.insertEdge(v1, v3, 10);
		e3 = directedGraph.insertEdge(v1, v4, 15);
		e4 = directedGraph.insertEdge(v1, v5, 20);
		e5 = directedGraph.insertEdge(v2, v3, 25);
		e6 = directedGraph.insertEdge(v2, v4, 30);
		e7 = directedGraph.insertEdge(v2, v5, 35);
		e8 = directedGraph.insertEdge(v3, v4, 40);
		e9 = directedGraph.insertEdge(v3, v5, 45);
		e10 = directedGraph.insertEdge(v4, v5, 50);
		Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);

		assertEquals(v2, directedGraph.opposite(v1, e1));
		assertEquals(v1, directedGraph.opposite(v2, e1));
		assertEquals(v1, directedGraph.opposite(v3, e2));
		assertEquals(v3, directedGraph.opposite(v1, e2));
		assertEquals(v1, directedGraph.opposite(v4, e3));
		assertEquals(v4, directedGraph.opposite(v1, e3));
		assertEquals(v1, directedGraph.opposite(v5, e4));
		assertEquals(v5, directedGraph.opposite(v1, e4));
		assertEquals(v2, directedGraph.opposite(v3, e5));
		assertEquals(v3, directedGraph.opposite(v2, e5));
		assertEquals(v2, directedGraph.opposite(v4, e6));
		assertEquals(v4, directedGraph.opposite(v2, e6));
		assertEquals(v2, directedGraph.opposite(v5, e7));
		assertEquals(v5, directedGraph.opposite(v2, e7));
		assertEquals(v3, directedGraph.opposite(v4, e8));
		assertEquals(v4, directedGraph.opposite(v3, e8));
		assertEquals(v3, directedGraph.opposite(v5, e9));
		assertEquals(v5, directedGraph.opposite(v3, e9));
		assertEquals(v4, directedGraph.opposite(v5, e10));
		assertEquals(v5, directedGraph.opposite(v4, e10));
		assertEquals(v6, directedGraph.opposite(v5, e11));
		assertEquals(v5, directedGraph.opposite(v6, e11));

	}

	/**
	 * Tests outDegree()
	 */
	@SuppressWarnings("unused")
	@Test
	public void testOutDegree() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
		Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
		Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
		Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
		Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
		Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
		Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
		Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
		Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);

		assertEquals(4, undirectedGraph.outDegree(v1));
		assertEquals(4, undirectedGraph.outDegree(v2));
		assertEquals(4, undirectedGraph.outDegree(v3));
		assertEquals(4, undirectedGraph.outDegree(v4));
		assertEquals(4, undirectedGraph.outDegree(v5));
		assertEquals(0, undirectedGraph.outDegree(v6));
		undirectedGraph.removeEdge(e10);
		assertEquals(4, undirectedGraph.outDegree(v1));
		assertEquals(4, undirectedGraph.outDegree(v2));
		assertEquals(4, undirectedGraph.outDegree(v3));
		assertEquals(3, undirectedGraph.outDegree(v4));
		assertEquals(3, undirectedGraph.outDegree(v5));
		assertEquals(0, undirectedGraph.outDegree(v6));
		undirectedGraph.removeEdge(e1);
		assertEquals(3, undirectedGraph.outDegree(v1));
		assertEquals(3, undirectedGraph.outDegree(v2));
		assertEquals(4, undirectedGraph.outDegree(v3));
		assertEquals(3, undirectedGraph.outDegree(v4));
		assertEquals(3, undirectedGraph.outDegree(v5));
		assertEquals(0, undirectedGraph.outDegree(v6));

		// DIRECTED
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		v6 = directedGraph.insertVertex("Boone");
		e1 = directedGraph.insertEdge(v1, v2, 5);
		e2 = directedGraph.insertEdge(v1, v3, 10);
		e3 = directedGraph.insertEdge(v1, v4, 15);
		e4 = directedGraph.insertEdge(v1, v5, 20);
		e5 = directedGraph.insertEdge(v2, v3, 25);
		e6 = directedGraph.insertEdge(v2, v4, 30);
		e7 = directedGraph.insertEdge(v2, v5, 35);
		e8 = directedGraph.insertEdge(v3, v4, 40);
		e9 = directedGraph.insertEdge(v3, v5, 45);
		e10 = directedGraph.insertEdge(v4, v5, 50);
		Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);

		assertEquals(4, directedGraph.outDegree(v1));
		assertEquals(3, directedGraph.outDegree(v2));
		assertEquals(2, directedGraph.outDegree(v3));
		assertEquals(1, directedGraph.outDegree(v4));
		assertEquals(1, directedGraph.outDegree(v5));
		assertEquals(0, directedGraph.outDegree(v6));
		directedGraph.removeEdge(e11);
		assertEquals(4, directedGraph.outDegree(v1));
		assertEquals(3, directedGraph.outDegree(v2));
		assertEquals(2, directedGraph.outDegree(v3));
		assertEquals(1, directedGraph.outDegree(v4));
		assertEquals(0, directedGraph.outDegree(v5));
		assertEquals(0, directedGraph.outDegree(v6));
	}

	/**
	 * Tests inDegree()
	 */
	@SuppressWarnings("unused")
	@Test
	public void testInDegree() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		assertEquals(undirectedGraph.inDegree(v1), 0);
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
		Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
		Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
		Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
		Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
		Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
		Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
		Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
		Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);

		assertEquals(4, undirectedGraph.inDegree(v1));
		assertEquals(4, undirectedGraph.inDegree(v2));
		assertEquals(4, undirectedGraph.inDegree(v3));
		assertEquals(4, undirectedGraph.inDegree(v4));
		assertEquals(4, undirectedGraph.inDegree(v5));
		assertEquals(0, undirectedGraph.inDegree(v6));
		undirectedGraph.removeEdge(e10);
		assertEquals(4, undirectedGraph.inDegree(v1));
		assertEquals(4, undirectedGraph.inDegree(v2));
		assertEquals(4, undirectedGraph.inDegree(v3));
		assertEquals(3, undirectedGraph.inDegree(v4));
		assertEquals(3, undirectedGraph.inDegree(v5));
		assertEquals(0, undirectedGraph.inDegree(v6));
		undirectedGraph.removeEdge(e1);
		assertEquals(3, undirectedGraph.inDegree(v1));
		assertEquals(3, undirectedGraph.inDegree(v2));
		assertEquals(4, undirectedGraph.inDegree(v3));
		assertEquals(3, undirectedGraph.inDegree(v4));
		assertEquals(3, undirectedGraph.inDegree(v5));
		assertEquals(0, undirectedGraph.inDegree(v6));

		// DIRECTED
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		v6 = directedGraph.insertVertex("Boone");
		e1 = directedGraph.insertEdge(v1, v2, 5);
		e2 = directedGraph.insertEdge(v1, v3, 10);
		e3 = directedGraph.insertEdge(v1, v4, 15);
		e4 = directedGraph.insertEdge(v1, v5, 20);
		e5 = directedGraph.insertEdge(v2, v3, 25);
		e6 = directedGraph.insertEdge(v2, v4, 30);
		e7 = directedGraph.insertEdge(v2, v5, 35);
		e8 = directedGraph.insertEdge(v3, v4, 40);
		e9 = directedGraph.insertEdge(v3, v5, 45);
		e10 = directedGraph.insertEdge(v4, v5, 50);
		Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);

		assertEquals(0, directedGraph.inDegree(v1));
		assertEquals(1, directedGraph.inDegree(v2));
		assertEquals(2, directedGraph.inDegree(v3));
		assertEquals(3, directedGraph.inDegree(v4));
		assertEquals(4, directedGraph.inDegree(v5));
		assertEquals(1, directedGraph.inDegree(v6));
		directedGraph.removeEdge(e11);
		assertEquals(0, directedGraph.inDegree(v1));
		assertEquals(1, directedGraph.inDegree(v2));
		assertEquals(2, directedGraph.inDegree(v3));
		assertEquals(3, directedGraph.inDegree(v4));
		assertEquals(4, directedGraph.inDegree(v5));
		assertEquals(0, directedGraph.inDegree(v6));

	}

	/**
	 * Tests outgoingEdges()
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testOutgoingEdges() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
		Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
		Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
		Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
		Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
		Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
		Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
		Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);

		// We can use a custom arrayContains() helper method to check that
		// an array *contains* a certain target edge.
		// This is helpful for testing graph ADT behaviors where an order
		// of edges cannot be guaranteed (such as .outgoingEdges or .incomingEdges
		// in adjacencyMaps, etc.)
		Edge<Integer>[] temp = (Edge<Integer>[]) (new Edge[4]);
		int count = 0;
		Iterator<Edge<Integer>> it = undirectedGraph.outgoingEdges(v1).iterator();
		assertTrue(it.hasNext());
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		assertFalse(it.hasNext());
		assertTrue(arrayContains(temp, e1));
		assertTrue(arrayContains(temp, e2));
		assertTrue(arrayContains(temp, e3));
		assertTrue(arrayContains(temp, e4));

		count = 0;
		it = undirectedGraph.outgoingEdges(v2).iterator();
		temp = (Edge<Integer>[]) (new Edge[4]);
		assertTrue(it.hasNext());
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		assertFalse(it.hasNext());
		assertTrue(arrayContains(temp, e1));
		assertTrue(arrayContains(temp, e5));
		assertTrue(arrayContains(temp, e6));
		assertTrue(arrayContains(temp, e7));

		count = 0;
		it = undirectedGraph.outgoingEdges(v3).iterator();
		temp = (Edge<Integer>[]) (new Edge[4]);
		assertTrue(it.hasNext());
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		assertFalse(it.hasNext());
		assertTrue(arrayContains(temp, e2));
		assertTrue(arrayContains(temp, e5));
		assertTrue(arrayContains(temp, e8));
		assertTrue(arrayContains(temp, e9));

		// DIRECTED
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		e1 = directedGraph.insertEdge(v1, v2, 5);
		e2 = directedGraph.insertEdge(v1, v3, 10);
		e3 = directedGraph.insertEdge(v1, v4, 15);
		e4 = directedGraph.insertEdge(v1, v5, 20);
		e5 = directedGraph.insertEdge(v2, v3, 25);
		e6 = directedGraph.insertEdge(v2, v4, 30);
		e7 = directedGraph.insertEdge(v2, v5, 35);
		e8 = directedGraph.insertEdge(v3, v4, 40);
		e9 = directedGraph.insertEdge(v3, v5, 45);

		temp = (Edge<Integer>[]) (new Edge[4]);
		count = 0;
		it = directedGraph.outgoingEdges(v1).iterator();
		assertTrue(it.hasNext());
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		assertFalse(it.hasNext());
		assertTrue(arrayContains(temp, e1));
		assertTrue(arrayContains(temp, e2));
		assertTrue(arrayContains(temp, e3));
		assertTrue(arrayContains(temp, e4));

		count = 0;
		it = directedGraph.outgoingEdges(v2).iterator();
		temp = (Edge<Integer>[]) (new Edge[4]);
		assertTrue(it.hasNext());
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		assertFalse(it.hasNext());
		assertTrue(arrayContains(temp, e5));
		assertTrue(arrayContains(temp, e6));
		assertTrue(arrayContains(temp, e7));

		count = 0;
		it = directedGraph.outgoingEdges(v3).iterator();
		assertTrue(it.hasNext());
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		assertFalse(it.hasNext());
		assertTrue(arrayContains(temp, e8));
		assertTrue(arrayContains(temp, e9));

	}

	// Helper method to check that an array contains a certain target.
	// This is helpful for testing graph ADT behaviors where an order
	// of edges cannot be guaranteed (such as .outgoingEdges or .incomingEdges)
	private boolean arrayContains(Edge<Integer>[] temp, Edge<Integer> target) {
		for (Edge<Integer> e : temp) {
			if (e == target) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Tests incomingEdges()
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testIncomingEdges() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
		Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
		Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
		Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
		Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
		Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
		Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
		Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);

		Edge<Integer>[] temp = (Edge<Integer>[]) (new Edge[4]);
		int count = 0;
		Iterator<Edge<Integer>> it = undirectedGraph.incomingEdges(v1).iterator();
		assertTrue(it.hasNext());
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		assertFalse(it.hasNext());
		assertEquals(temp[0].getElement(), e1.getElement());
		assertTrue(arrayContains(temp, e1));
		assertTrue(arrayContains(temp, e2));
		assertTrue(arrayContains(temp, e3));
		assertTrue(arrayContains(temp, e4));

		count = 0;
		it = undirectedGraph.incomingEdges(v2).iterator();
		temp = (Edge<Integer>[]) (new Edge[4]);
		assertTrue(it.hasNext());
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		assertFalse(it.hasNext());
		assertTrue(arrayContains(temp, e1));
		assertTrue(arrayContains(temp, e5));
		assertTrue(arrayContains(temp, e6));
		assertTrue(arrayContains(temp, e7));

		count = 0;
		it = undirectedGraph.incomingEdges(v3).iterator();
		temp = (Edge<Integer>[]) (new Edge[4]);
		assertTrue(it.hasNext());
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		assertFalse(it.hasNext());
		assertTrue(arrayContains(temp, e2));
		assertTrue(arrayContains(temp, e5));
		assertTrue(arrayContains(temp, e8));
		assertTrue(arrayContains(temp, e9));

		// DIRECTED
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		e1 = directedGraph.insertEdge(v1, v2, 5);
		e2 = directedGraph.insertEdge(v1, v3, 10);
		e3 = directedGraph.insertEdge(v1, v4, 15);
		e4 = directedGraph.insertEdge(v1, v5, 20);
		e5 = directedGraph.insertEdge(v2, v3, 25);
		e6 = directedGraph.insertEdge(v2, v4, 30);
		e7 = directedGraph.insertEdge(v2, v5, 35);
		e8 = directedGraph.insertEdge(v3, v4, 40);
		e9 = directedGraph.insertEdge(v3, v5, 45);

		temp = (Edge<Integer>[]) (new Edge[4]);
		count = 0;
		it = directedGraph.incomingEdges(v1).iterator();
		assertFalse(it.hasNext());
		it = directedGraph.incomingEdges(v2).iterator();
		assertTrue(it.hasNext());
		temp[count] = it.next();
		count++;
		assertFalse(it.hasNext());
		assertTrue(arrayContains(temp, e1));

		count = 0;
		it = directedGraph.incomingEdges(v3).iterator();
		temp = (Edge<Integer>[]) (new Edge[4]);
		assertTrue(it.hasNext());
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		assertFalse(it.hasNext());
		assertTrue(arrayContains(temp, e2));
		assertTrue(arrayContains(temp, e5));

		count = 0;
		it = directedGraph.incomingEdges(v4).iterator();
		assertTrue(it.hasNext());
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		assertFalse(it.hasNext());
		assertTrue(arrayContains(temp, e3));
		assertTrue(arrayContains(temp, e6));
		assertTrue(arrayContains(temp, e8));

	}

	/**
	 * Tests insertVertex()
	 */
	@Test
	public void testInsertVertex() {
		assertEquals(0, undirectedGraph.numVertices());
		Vertex<String> v1 = undirectedGraph.insertVertex("Fayetteville");
		assertEquals(1, undirectedGraph.numVertices());

		Iterator<Vertex<String>> it = undirectedGraph.vertices().iterator();
		assertTrue(it.hasNext());
		assertEquals(v1, it.next());
		assertFalse(it.hasNext());

		Vertex<String> v2 = undirectedGraph.insertVertex("vertex2");
		assertEquals(2, undirectedGraph.numVertices());
		Vertex<String> v3 = undirectedGraph.insertVertex("vertex3");
		assertEquals(3, undirectedGraph.numVertices());

		it = undirectedGraph.vertices().iterator();
		assertTrue(it.hasNext());
		assertEquals(v1, it.next());
		assertEquals(v2, it.next());
		assertEquals(v3, it.next());
		assertFalse(it.hasNext());

		assertEquals(0, directedGraph.numVertices());
		v1 = directedGraph.insertVertex("Fayetteville");
		assertEquals(1, directedGraph.numVertices());

		it = directedGraph.vertices().iterator();
		assertTrue(it.hasNext());
		assertEquals(v1, it.next());
		assertFalse(it.hasNext());

		v2 = directedGraph.insertVertex("vertex2");
		assertEquals(2, directedGraph.numVertices());
		v3 = directedGraph.insertVertex("vertex3");
		assertEquals(3, directedGraph.numVertices());

		it = directedGraph.vertices().iterator();
		assertTrue(it.hasNext());
		assertEquals(v1.getElement(), it.next().getElement());
		assertEquals(v2, it.next());
		assertEquals(v3, it.next());
		assertFalse(it.hasNext());

	}

	/**
	 * Tests insertEdge()
	 */
	@Test
	public void testInsertEdge() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");

		assertEquals(0, undirectedGraph.numEdges());
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 99);
		assertEquals(1, undirectedGraph.numEdges());
		Iterator<Edge<Integer>> it = undirectedGraph.edges().iterator();
		assertTrue(it.hasNext());
		assertEquals(e1, it.next());
		assertFalse(it.hasNext());

		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		assertEquals(0, directedGraph.numEdges());
		e1 = directedGraph.insertEdge(v1, v2, 99);
		assertEquals(1, directedGraph.numEdges());
		it = directedGraph.edges().iterator();
		assertTrue(it.hasNext());
		assertEquals(e1, it.next());
		assertFalse(it.hasNext());
	}

	/**
	 * Tests removeVertex()
	 */
	@SuppressWarnings("unused")
	@Test
	public void testRemoveVertex() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
		Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
		Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
		Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
		Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
		Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
		Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
		Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
		Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);

		assertEquals(5, undirectedGraph.numVertices());
		assertEquals(10, undirectedGraph.numEdges());

		assertEquals(v5, undirectedGraph.removeVertex(v5));
		assertEquals(4, undirectedGraph.numVertices());
		assertEquals(6, undirectedGraph.numEdges());

		undirectedGraph.removeVertex(v1);
		assertEquals(3, undirectedGraph.numVertices());
		assertEquals(3, undirectedGraph.numEdges());

		undirectedGraph.removeVertex(v2);
		assertEquals(2, undirectedGraph.numVertices());
		assertEquals(1, undirectedGraph.numEdges());

		undirectedGraph.removeVertex(v3);
		assertEquals(1, undirectedGraph.numVertices());
		assertEquals(0, undirectedGraph.numEdges());

		undirectedGraph.removeVertex(v4);
		assertEquals(0, undirectedGraph.numVertices());
		assertEquals(0, undirectedGraph.numEdges());

		// DIRECTED
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		Vertex<String> v6 = directedGraph.insertVertex("Boone");
		e1 = directedGraph.insertEdge(v1, v2, 5);
		e2 = directedGraph.insertEdge(v1, v3, 10);
		e3 = directedGraph.insertEdge(v1, v4, 15);
		e4 = directedGraph.insertEdge(v1, v5, 20);
		e5 = directedGraph.insertEdge(v2, v3, 25);
		e6 = directedGraph.insertEdge(v2, v4, 30);
		e7 = directedGraph.insertEdge(v2, v5, 35);
		e8 = directedGraph.insertEdge(v3, v4, 40);
		e9 = directedGraph.insertEdge(v3, v5, 45);
		e10 = directedGraph.insertEdge(v4, v5, 50);
		Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);

		assertEquals(6, directedGraph.numVertices());
		assertEquals(11, directedGraph.numEdges());

		assertEquals(v6, directedGraph.removeVertex(v6));
		assertEquals(5, directedGraph.numVertices());
		assertEquals(10, directedGraph.numEdges());

		assertEquals(v5, directedGraph.removeVertex(v5));
		assertEquals(4, directedGraph.numVertices());
		assertEquals(6, directedGraph.numEdges());

		directedGraph.removeVertex(v1);
		assertEquals(3, directedGraph.numVertices());
		assertEquals(3, directedGraph.numEdges());

		directedGraph.removeVertex(v2);
		assertEquals(2, directedGraph.numVertices());
		assertEquals(1, directedGraph.numEdges());

		directedGraph.removeVertex(v3);
		assertEquals(1, directedGraph.numVertices());
		assertEquals(0, directedGraph.numEdges());

		directedGraph.removeVertex(v4);
		assertEquals(0, directedGraph.numVertices());
		assertEquals(0, directedGraph.numEdges());
	}

	/**
	 * Tests removeEdge()
	 */
	@Test
	public void testRemoveEdge() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
		Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
		Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
		Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
		Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
		Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
		Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
		Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
		Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);

		assertEquals(5, undirectedGraph.numVertices());
		assertEquals(10, undirectedGraph.numEdges());

		assertEquals(e1, undirectedGraph.removeEdge(e1));
		assertEquals(5, undirectedGraph.numVertices());
		assertEquals(9, undirectedGraph.numEdges());

		assertEquals(e2, undirectedGraph.removeEdge(e2));
		assertEquals(5, undirectedGraph.numVertices());
		assertEquals(8, undirectedGraph.numEdges());

		assertEquals(e3, undirectedGraph.removeEdge(e3));
		assertEquals(5, undirectedGraph.numVertices());
		assertEquals(7, undirectedGraph.numEdges());

		assertEquals(e4, undirectedGraph.removeEdge(e4));
		assertEquals(5, undirectedGraph.numVertices());
		assertEquals(6, undirectedGraph.numEdges());

		assertEquals(e5, undirectedGraph.removeEdge(e5));
		assertEquals(5, undirectedGraph.numVertices());
		assertEquals(5, undirectedGraph.numEdges());

		assertEquals(e6, undirectedGraph.removeEdge(e6));
		assertEquals(5, undirectedGraph.numVertices());
		assertEquals(4, undirectedGraph.numEdges());

		assertEquals(e7, undirectedGraph.removeEdge(e7));
		assertEquals(5, undirectedGraph.numVertices());
		assertEquals(3, undirectedGraph.numEdges());

		assertEquals(e8, undirectedGraph.removeEdge(e8));
		assertEquals(5, undirectedGraph.numVertices());
		assertEquals(2, undirectedGraph.numEdges());

		assertEquals(e9, undirectedGraph.removeEdge(e9));
		assertEquals(5, undirectedGraph.numVertices());
		assertEquals(1, undirectedGraph.numEdges());

		assertEquals(e10, undirectedGraph.removeEdge(e10));
		assertEquals(5, undirectedGraph.numVertices());
		assertEquals(0, undirectedGraph.numEdges());

		// DIRECTED
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		e1 = directedGraph.insertEdge(v1, v2, 5);
		e2 = directedGraph.insertEdge(v1, v3, 10);
		e3 = directedGraph.insertEdge(v1, v4, 15);
		e4 = directedGraph.insertEdge(v1, v5, 20);
		e5 = directedGraph.insertEdge(v2, v3, 25);
		e6 = directedGraph.insertEdge(v2, v4, 30);
		e7 = directedGraph.insertEdge(v2, v5, 35);
		e8 = directedGraph.insertEdge(v3, v4, 40);
		e9 = directedGraph.insertEdge(v3, v5, 45);
		e10 = directedGraph.insertEdge(v4, v5, 50);

		assertEquals(5, directedGraph.numVertices());
		assertEquals(10, directedGraph.numEdges());

		assertEquals(e1, directedGraph.removeEdge(e1));
		assertEquals(5, directedGraph.numVertices());
		assertEquals(9, directedGraph.numEdges());

		assertEquals(e2, directedGraph.removeEdge(e2));
		assertEquals(5, directedGraph.numVertices());
		assertEquals(8, directedGraph.numEdges());

		assertEquals(e3, directedGraph.removeEdge(e3));
		assertEquals(5, directedGraph.numVertices());
		assertEquals(7, directedGraph.numEdges());

		assertEquals(e4, directedGraph.removeEdge(e4));
		assertEquals(5, directedGraph.numVertices());
		assertEquals(6, directedGraph.numEdges());

		assertEquals(e5, directedGraph.removeEdge(e5));
		assertEquals(5, directedGraph.numVertices());
		assertEquals(5, directedGraph.numEdges());

		assertEquals(e6, directedGraph.removeEdge(e6));
		assertEquals(5, directedGraph.numVertices());
		assertEquals(4, directedGraph.numEdges());

		assertEquals(e7, directedGraph.removeEdge(e7));
		assertEquals(5, directedGraph.numVertices());
		assertEquals(3, directedGraph.numEdges());

		assertEquals(e8, directedGraph.removeEdge(e8));
		assertEquals(5, directedGraph.numVertices());
		assertEquals(2, directedGraph.numEdges());

		assertEquals(e9, directedGraph.removeEdge(e9));
		assertEquals(5, directedGraph.numVertices());
		assertEquals(1, directedGraph.numEdges());

		assertEquals(e10, directedGraph.removeEdge(e10));
		assertEquals(5, directedGraph.numVertices());
		assertEquals(0, directedGraph.numEdges());

	}

}
