package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the SplayTreeMap class
 * 
 * @author Gianna Mastrandrea
 *
 */
public class SplayTreeMapTest {

	private BinarySearchTreeMap<Integer, String> tree;

	/**
	 * Sets up tree for testing
	 */
	@Before
	public void setUp() {
		tree = new SplayTreeMap<Integer, String>();
	}

	/**
	 * Tests put()
	 */
	@Test
	public void testPut() {
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());

		assertNull(tree.put(1, "one"));
		assertEquals(1, tree.size());
		assertEquals(1, (int) tree.root().getElement().getKey());

		// ZIG
		assertNull(tree.put(3, "three"));
		assertEquals(2, tree.size());
		assertEquals(3, (int) tree.root().getElement().getKey());
		assertEquals(1, (int) tree.left(tree.root()).getElement().getKey());

		// ZIG-ZAG
		assertNull(tree.put(2, "two"));
		assertEquals(3, tree.size());
		assertEquals(2, (int) tree.root().getElement().getKey());
		assertEquals(1, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(3, (int) tree.right(tree.root()).getElement().getKey());

		// ZIG-ZIG
		assertNull(tree.put(4, "four"));
		assertEquals(4, tree.size());
		assertEquals(4, (int) tree.root().getElement().getKey());
		assertEquals(3, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(2, (int) tree.left(tree.left(tree.root())).getElement().getKey());
		assertEquals(1, (int) tree.left(tree.left(tree.left(tree.root()))).getElement().getKey());
	}

	/**
	 * Tests get()
	 */
	@Test
	public void testGet() {
		assertTrue(tree.isEmpty());
		tree.put(4, "four");
		tree.put(7, "seven");

		assertEquals(tree.get(4), "four");
		assertEquals((int) tree.root().getElement().getKey(), 4);
		assertEquals(tree.get(7), "seven");
		assertEquals((int) tree.root().getElement().getKey(), 7);

		tree.put(12, "twelve");
		tree.put(15, "fifteen");
		tree.put(3, "three");
		tree.put(5, "five");
		tree.put(14, "fourteen");
		tree.put(18, "eighteen");

		assertEquals(tree.size(), 8);
		assertEquals(tree.get(4), "four");
		assertEquals(tree.put(4, "newFour"), "four");
		assertEquals(tree.get(4), "newFour");

		assertEquals(tree.get(7), "seven");
		assertEquals(tree.get(12), "twelve");
		assertEquals(tree.get(15), "fifteen");
		assertEquals(tree.get(3), "three");
		assertEquals(tree.get(5), "five");
		assertEquals(tree.get(14), "fourteen");
		assertEquals(tree.get(18), "eighteen");
	}

	/**
	 * Tests remove()
	 */
	@Test
	public void testRemove() {
		assertTrue(tree.isEmpty());

		tree.put(5, "five");
		tree.put(11, "eleven");
		tree.put(6, "six");
		tree.put(10, "ten");
		tree.put(4, "four");
		tree.put(3, "three");
		tree.put(8, "eight");
		tree.put(7, "seven");

		tree.remove(7);
		assertNull(tree.get(7));
		assertEquals(7, tree.size());
		assertEquals(6, (int) tree.root().getElement().getKey());
		assertEquals(3, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(8, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(10, (int) tree.right(tree.right(tree.root())).getElement().getKey());
		assertEquals(11, (int) tree.right(tree.right(tree.right(tree.root()))).getElement().getKey());
		assertEquals(4, (int) tree.right(tree.left(tree.root())).getElement().getKey());
		assertEquals(5, (int) tree.right(tree.right(tree.left(tree.root()))).getElement().getKey());

		setUp();

		tree.put(3, "string3");
		tree.put(1, "string1");
		tree.put(2, "string2");
		tree.put(4, "string4");
		tree.put(5, "string5");

		tree.put(6, "string6");
		tree.put(7, "string7");
		tree.put(8, "string8");
		tree.put(3, "string3");
		tree.put(5, "string5");
		tree.put(2, "string2");
		tree.put(4, "string4");
		tree.put(1, "string1");

		tree.remove(0);
		tree.remove(1);
		tree.remove(2);
		tree.remove(3);
		tree.remove(4);
		tree.remove(5);
		tree.remove(6);
		tree.remove(7);
		tree.remove(8);

	}
}
