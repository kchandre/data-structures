package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Tests the BinarySearchTree class
 * 
 * @author Gianna Mastrandrea
 *
 */
public class BinarySearchTreeMapTest {

	BinarySearchTreeMap<Integer, String> tree;

	/**
	 * Sets up binary search tree for testing
	 */
	@Before
	public void setUp() {
		tree = new BinarySearchTreeMap<Integer, String>();
	}

	/**
	 * Tests put()
	 */
	@Test
	public void testPut() {
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());
		tree.put(1, "one");
		assertEquals(1, tree.size());
		assertFalse(tree.isEmpty());
		assertEquals(1, (int) tree.root().getElement().getKey());

		assertEquals(null, tree.put(2, "two"));
		assertEquals(2, tree.size());
		assertEquals(2, (int) tree.right(tree.root()).getElement().getKey());

		assertEquals("two", tree.put(2, "newtwo"));
		assertEquals(2, tree.size());
		assertEquals(2, (int) tree.right(tree.root()).getElement().getKey());

		tree.put(0, "zero");
		assertEquals(3, tree.size());
		assertEquals(0, (int) tree.left(tree.root()).getElement().getKey());

		Iterator<Entry<Integer, String>> it = tree.entrySet().iterator();
		assertTrue(it.hasNext());
		assertEquals(it.next().getValue(), "zero");
		assertEquals(it.next().getValue(), "one");
		assertEquals(it.next().getValue(), "newtwo");
		assertFalse(it.hasNext());

		Iterator<Position<Entry<Integer, String>>> it2 = tree.children(tree.root()).iterator();
		assertTrue(it2.hasNext());
		assertEquals(it2.next().getElement().getValue(), "zero");
		assertEquals(it2.next().getElement().getValue(), "newtwo");
		assertFalse(it2.hasNext());

		Iterator<Position<Entry<Integer, String>>> it3 = tree.levelOrder().iterator();
		assertTrue(it3.hasNext());
		assertEquals(it3.next().getElement().getValue(), "one");
		assertEquals(it3.next().getElement().getValue(), "zero");
		assertEquals(it3.next().getElement().getValue(), "newtwo");
		assertFalse(it3.hasNext());
		
		Iterator<Position<Entry<Integer, String>>> it4 = tree.inOrder().iterator();
		assertTrue(it4.hasNext());
		assertEquals(it4.next().getElement().getValue(), "zero");
		assertEquals(it4.next().getElement().getValue(), "one");
		assertEquals(it4.next().getElement().getValue(), "newtwo");
		assertFalse(it4.hasNext());
		
		Iterator<Position<Entry<Integer, String>>> it5 = tree.preOrder().iterator();
		assertTrue(it5.hasNext());
		assertEquals(it5.next().getElement().getValue(), "one");
		assertEquals(it5.next().getElement().getValue(), "zero");
		assertEquals(it5.next().getElement().getValue(), "newtwo");
		assertFalse(it5.hasNext());
		
		Iterator<Position<Entry<Integer, String>>> it6 = tree.postOrder().iterator();
		assertTrue(it6.hasNext());
		assertEquals(it6.next().getElement().getValue(), "zero");
		assertEquals(it6.next().getElement().getValue(), "newtwo");
		assertEquals(it6.next().getElement().getValue(), "one");
		assertFalse(it6.hasNext());
		
		assertEquals(tree.numChildren(tree.root()), 2);

		setUp();
		
		// removing with inorder successor
		tree.put(3, "three");
		tree.put(1, "one");
		tree.put(0, "zero");
		tree.put(2, "two");
		tree.put(4, "four");
		tree.put(5, "five");

		assertEquals("one", tree.remove(1));
		assertEquals(5, tree.size());
		assertEquals("two", tree.left(tree.root()).getElement().getValue());

	}

	/**
	 * Tests get()
	 */
	@Test
	public void testGet() {
		tree.put(1, "one");
		assertEquals(1, tree.size());

		tree.put(2, "two");
		assertEquals(2, tree.size());

		tree.put(3, "three");
		assertEquals(3, tree.size());

		tree.put(4, "four");
		assertEquals(4, tree.size());

		assertEquals("one", tree.get(1));
		assertEquals("two", tree.get(2));
		assertEquals("three", tree.get(3));
		assertEquals("four", tree.get(4));
		assertFalse(tree.toString().equals(""));
	}

	/**
	 * Tests remove()
	 */
	@Test
	public void testRemove() {
		tree.put(1, "one");
		assertEquals(1, tree.size());

		assertNull(tree.remove(10));
		assertEquals(1, tree.size());

		// removing root
		assertEquals("one", tree.remove(1));
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());
		assertEquals(null, tree.root().getElement());

		// removing from node with only right child
		tree.put(1, "one");
		tree.put(2, "two");
		tree.put(3, "three");
		assertEquals("two", tree.remove(2));
		assertEquals(2, tree.size());
		assertEquals("three", tree.right(tree.root()).getElement().getValue());

		tree = new BinarySearchTreeMap<Integer, String>();

		// removing from node with only left child
		tree.put(2, "two");
		tree.put(1, "one");
		tree.put(0, "zero");
		assertEquals("one", tree.remove(1));
		assertEquals(2, tree.size());
		assertEquals("zero", tree.left(tree.root()).getElement().getValue());

		// removing from node with both children
		tree.put(3, "three");
		tree.put(1, "one");
		tree.put(0, "zero");
		tree.put(2, "two");

		assertEquals("one", tree.remove(1));
		assertEquals(3, tree.size());
		assertEquals("zero", tree.left(tree.root()).getElement().getValue());

	}

}
