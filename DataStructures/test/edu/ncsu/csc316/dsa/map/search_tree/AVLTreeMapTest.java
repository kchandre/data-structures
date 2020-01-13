package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the AVLTreeMap class
 * 
 * @author Gianna Mastrandrea
 *
 */
public class AVLTreeMapTest {

	private BinarySearchTreeMap<Integer, String> tree;

	/**
	 * Sets up AVL tree map for testing
	 */
	@Before
	public void setUp() {
		tree = new AVLTreeMap<Integer, String>();
	}

	/**
	 * Tests put()
	 */
	@Test
	public void testPut() {
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());

		assertNull(tree.put(5, "five"));
		assertEquals(1, tree.size());
		assertEquals(5, (int) tree.root().getElement().getKey());
		assertNull(tree.left(tree.root()).getElement());

		assertNull(tree.put(10, "ten"));
		assertEquals(5, (int) tree.root().getElement().getKey());
		assertNull(tree.left(tree.root()).getElement());
		assertEquals(10, (int) tree.right(tree.root()).getElement().getKey());
		assertNull(tree.left(tree.right(tree.root())).getElement());
		assertNull(tree.right(tree.right(tree.root())).getElement());
		assertEquals(2, tree.size());

		assertNull(tree.put(15, "fifteen"));
		assertEquals(10, (int) tree.root().getElement().getKey());
		assertEquals(5, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(15, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(3, tree.size());

		assertNull(tree.put(8, "eight"));
		assertEquals(10, (int) tree.root().getElement().getKey());
		assertEquals(5, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(8, (int) tree.right(tree.left(tree.root())).getElement().getKey());
		assertEquals(15, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(4, tree.size());

		assertNull(tree.put(16, "sixteen"));
		assertEquals(10, (int) tree.root().getElement().getKey());
		assertEquals(5, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(8, (int) tree.right(tree.left(tree.root())).getElement().getKey());
		assertEquals(15, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(16, (int) tree.right(tree.right(tree.root())).getElement().getKey());
		assertEquals(5, tree.size());

		assertNull(tree.put(17, "seventeen"));
		assertEquals(10, (int) tree.root().getElement().getKey());
		assertEquals(5, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(8, (int) tree.right(tree.left(tree.root())).getElement().getKey());
		assertEquals(16, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(17, (int) tree.right(tree.right(tree.root())).getElement().getKey());
		assertEquals(15, (int) tree.left(tree.right(tree.root())).getElement().getKey());
		assertEquals(6, tree.size());

	}

	/**
	 * Tests get()
	 */
	@Test
	public void testGet() {
		assertTrue(tree.isEmpty());
		assertNull(tree.put(3, "three"));
		assertFalse(tree.isEmpty());

		assertEquals("three", tree.get(3));
		assertEquals(null, tree.get(6));
		assertEquals(null, tree.get(0));

		tree.remove(3);
		assertTrue(tree.isEmpty());
		tree.put(4, "four");
		tree.put(7, "seven");

		assertEquals(tree.get(4), "four");
		assertEquals((int) tree.root().getElement().getKey(), 4);
		assertEquals(tree.get(7), "seven");
		assertEquals((int) tree.root().getElement().getKey(), 4);

		tree.put(12, "twelve");
		tree.put(15, "fifteen");
		tree.put(3, "three");
		tree.put(5, "five");
		tree.put(14, "fourteen");
		tree.put(18, "eighteen");

		assertEquals(tree.size(), 7);
		assertEquals(tree.get(4), "four");
		assertEquals(tree.put(4, "newFour"), "four");
		assertEquals(tree.get(4), "newFour");
		assertEquals(tree.left(tree.root()).getElement().getValue(), "newFour");

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
		assertNull(tree.put(1, "one"));
		assertNull(tree.put(2, "two"));
		assertNull(tree.put(3, "three"));
		assertNull(tree.put(4, "four"));
		assertNull(tree.put(5, "five"));
		assertNull(tree.put(6, "six"));
		assertNull(tree.put(7, "seven"));
		assertEquals(7, tree.size());
		assertFalse(tree.isEmpty());

		assertNull(tree.remove(0));
		assertEquals(7, tree.size());
		assertFalse(tree.isEmpty());
		assertEquals(4, (int) tree.root().getElement().getKey());
		assertEquals(2, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(1, (int) tree.left(tree.left(tree.root())).getElement().getKey());
		assertEquals(3, (int) tree.right(tree.left(tree.root())).getElement().getKey());
		assertEquals(6, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(5, (int) tree.left(tree.right(tree.root())).getElement().getKey());
		assertEquals(7, (int) tree.right(tree.right(tree.root())).getElement().getKey());

		tree.remove(4);
		assertNull(tree.get(4));
		assertEquals(6, tree.size());
		assertEquals(5, (int) tree.root().getElement().getKey());
		assertEquals(2, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(1, (int) tree.left(tree.left(tree.root())).getElement().getKey());
		assertEquals(3, (int) tree.right(tree.left(tree.root())).getElement().getKey());
		assertEquals(6, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(null, tree.left(tree.right(tree.root())).getElement());
		assertEquals(7, (int) tree.right(tree.right(tree.root())).getElement().getKey());

		tree.remove(5);
		assertNull(tree.get(5));
		assertEquals(5, tree.size());
		assertEquals(6, (int) tree.root().getElement().getKey());
		assertEquals(2, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(1, (int) tree.left(tree.left(tree.root())).getElement().getKey());
		assertEquals(3, (int) tree.right(tree.left(tree.root())).getElement().getKey());
		assertEquals(7, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(null, tree.right(tree.right(tree.root())).getElement());

		tree.remove(7);
		assertNull(tree.get(7));
		assertEquals(4, tree.size());
		assertEquals(2, (int) tree.root().getElement().getKey());
		assertEquals(1, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(null, tree.left(tree.left(tree.root())).getElement());
		assertEquals(null, tree.right(tree.left(tree.root())).getElement());
		assertEquals(6, (int) tree.right(tree.root()).getElement().getKey());

		tree.remove(6);
		assertNull(tree.get(6));
		assertEquals(3, tree.size());
		assertEquals(2, (int) tree.root().getElement().getKey());
		assertEquals(1, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(3, (int) tree.right(tree.root()).getElement().getKey());

		tree.remove(2);
		assertNull(tree.get(2));
		assertEquals(2, tree.size());
		assertEquals(3, (int) tree.root().getElement().getKey());
		assertEquals(1, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(null, tree.right(tree.root()).getElement());

		tree.remove(3);
		assertNull(tree.get(3));
		assertEquals(1, tree.size());
		assertEquals(1, (int) tree.root().getElement().getKey());
		assertEquals(null, tree.left(tree.root()).getElement());
		assertEquals(null, tree.right(tree.root()).getElement());

		tree.remove(1);
		assertNull(tree.get(1));
		assertEquals(0, tree.size());
		assertEquals(null, tree.root().getElement());
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());

	}

}
