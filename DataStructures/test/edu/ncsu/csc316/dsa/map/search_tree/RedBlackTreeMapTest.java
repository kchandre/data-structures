package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests RedBlackTreeMap class
 * 
 * @author Gianna Mastrandrea
 *
 */
public class RedBlackTreeMapTest {

	private BinarySearchTreeMap<Integer, String> tree;

	/**
	 * Sets up tree for testing
	 */
	@Before
	public void setUp() {
		tree = new RedBlackTreeMap<Integer, String>();
	}

	/**
	 * Tests put()
	 */
	@Test
	public void testPut() {
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());

		assertNull(tree.put(4, "four"));
		assertEquals(1, tree.size());
		assertFalse(tree.isEmpty());
		assertEquals(4, (int) tree.root().getElement().getKey());

		assertNull(tree.put(7, "seven"));
		assertEquals(2, tree.size());
		assertEquals(4, (int) tree.root().getElement().getKey());
		assertEquals(7, (int) tree.right(tree.root()).getElement().getKey());

		assertNull(tree.put(12, "twelve"));
		assertEquals(3, tree.size());
		assertEquals(7, (int) tree.root().getElement().getKey());
		assertEquals(4, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(12, (int) tree.right(tree.root()).getElement().getKey());

		assertNull(tree.put(15, "fifteen"));
		assertEquals(4, tree.size());
		assertEquals(7, (int) tree.root().getElement().getKey());
		assertEquals(4, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(12, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(15, (int) tree.right(tree.right(tree.root())).getElement().getKey());

		assertNull(tree.put(3, "three"));
		assertEquals(5, tree.size());
		assertEquals(7, (int) tree.root().getElement().getKey());
		assertEquals(4, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(3, (int) tree.left(tree.left(tree.root())).getElement().getKey());
		assertEquals(12, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(15, (int) tree.right(tree.right(tree.root())).getElement().getKey());

		assertNull(tree.put(5, "five"));
		assertEquals(6, tree.size());
		assertEquals(7, (int) tree.root().getElement().getKey());
		assertEquals(4, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(3, (int) tree.left(tree.left(tree.root())).getElement().getKey());
		assertEquals(5, (int) tree.right(tree.left(tree.root())).getElement().getKey());
		assertEquals(12, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(15, (int) tree.right(tree.right(tree.root())).getElement().getKey());

		assertNull(tree.put(14, "fourteen"));
		assertEquals(7, tree.size());
		assertEquals(7, (int) tree.root().getElement().getKey());
		assertEquals(4, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(3, (int) tree.left(tree.left(tree.root())).getElement().getKey());
		assertEquals(5, (int) tree.right(tree.left(tree.root())).getElement().getKey());
		assertEquals(14, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(12, (int) tree.left(tree.right(tree.root())).getElement().getKey());
		assertEquals(15, (int) tree.right(tree.right(tree.root())).getElement().getKey());

		assertNull(tree.put(18, "eighteen"));
		assertEquals(8, tree.size());
		assertEquals(7, (int) tree.root().getElement().getKey());
		assertEquals(4, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(3, (int) tree.left(tree.left(tree.root())).getElement().getKey());
		assertEquals(5, (int) tree.right(tree.left(tree.root())).getElement().getKey());
		assertEquals(14, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(12, (int) tree.left(tree.right(tree.root())).getElement().getKey());
		assertEquals(15, (int) tree.right(tree.right(tree.root())).getElement().getKey());
		assertEquals(18, (int) tree.right(tree.right(tree.right(tree.root()))).getElement().getKey());

		assertNull(tree.put(16, "sixteen"));
		assertEquals(9, tree.size());
		assertEquals(4, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(3, (int) tree.left(tree.left(tree.root())).getElement().getKey());
		assertEquals(5, (int) tree.right(tree.left(tree.root())).getElement().getKey());
		assertEquals(14, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(12, (int) tree.left(tree.right(tree.root())).getElement().getKey());
		assertEquals(16, (int) tree.right(tree.right(tree.root())).getElement().getKey());
		assertEquals(15, (int) tree.left(tree.right(tree.right(tree.root()))).getElement().getKey());
		assertEquals(18, (int) tree.right(tree.right(tree.right(tree.root()))).getElement().getKey());

		assertNull(tree.put(17, "seventeen"));
		assertEquals(10, tree.size());
		assertEquals(14, (int) tree.root().getElement().getKey());
		assertEquals(7, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(4, (int) tree.left(tree.left(tree.root())).getElement().getKey());
		assertEquals(12, (int) tree.right(tree.left(tree.root())).getElement().getKey());
		assertEquals(3, (int) tree.left(tree.left(tree.left(tree.root()))).getElement().getKey());
		assertEquals(5, (int) tree.right(tree.left(tree.left(tree.root()))).getElement().getKey());
		assertEquals(16, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(15, (int) tree.left(tree.right(tree.root())).getElement().getKey());
		assertEquals(18, (int) tree.right(tree.right(tree.root())).getElement().getKey());
		assertEquals(17, (int) tree.left(tree.right(tree.right(tree.root()))).getElement().getKey());

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
		assertEquals((int) tree.root().getElement().getKey(), 4);

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
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());

		tree.put(4, "four");

		assertFalse(tree.isEmpty());
		assertEquals(1, tree.size());

		tree.put(7, "seven");
		tree.put(12, "twelve");
		tree.put(15, "fifteen");
		tree.put(3, "three");
		tree.put(5, "five");
		tree.put(14, "fourteen");
		tree.put(18, "eighteen");
		tree.put(16, "sixteen");
		tree.put(17, "seventeen");

		tree.remove(3);
		assertNull(tree.get(3));

		assertEquals(14, (int) tree.root().getElement().getKey());
		assertEquals(7, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(4, (int) tree.left(tree.left(tree.root())).getElement().getKey());
		assertEquals(12, (int) tree.right(tree.left(tree.root())).getElement().getKey());
		assertEquals(null, tree.left(tree.left(tree.left(tree.root()))).getElement());
		assertEquals(5, (int) tree.right(tree.left(tree.left(tree.root()))).getElement().getKey());
		assertEquals(16, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(15, (int) tree.left(tree.right(tree.root())).getElement().getKey());
		assertEquals(18, (int) tree.right(tree.right(tree.root())).getElement().getKey());
		assertEquals(17, (int) tree.left(tree.right(tree.right(tree.root()))).getElement().getKey());

		tree.remove(12);
		assertNull(tree.get(12));

		assertEquals(14, (int) tree.root().getElement().getKey());
		assertEquals(5, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(4, (int) tree.left(tree.left(tree.root())).getElement().getKey());
		assertEquals(7, (int) tree.right(tree.left(tree.root())).getElement().getKey());
		assertEquals(null, tree.left(tree.left(tree.left(tree.root()))).getElement());
		assertEquals(null, tree.right(tree.left(tree.left(tree.root()))).getElement());
		assertEquals(16, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(15, (int) tree.left(tree.right(tree.root())).getElement().getKey());
		assertEquals(18, (int) tree.right(tree.right(tree.root())).getElement().getKey());
		assertEquals(17, (int) tree.left(tree.right(tree.right(tree.root()))).getElement().getKey());

		tree.remove(17);
		assertNull(tree.get(17));

		assertEquals(14, (int) tree.root().getElement().getKey());
		assertEquals(5, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(4, (int) tree.left(tree.left(tree.root())).getElement().getKey());
		assertEquals(7, (int) tree.right(tree.left(tree.root())).getElement().getKey());
		assertEquals(16, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(15, (int) tree.left(tree.right(tree.root())).getElement().getKey());
		assertEquals(18, (int) tree.right(tree.right(tree.root())).getElement().getKey());
		assertEquals(null, tree.left(tree.right(tree.right(tree.root()))).getElement());

		tree.remove(18);
		assertNull(tree.get(18));

		assertEquals(14, (int) tree.root().getElement().getKey());
		assertEquals(5, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(4, (int) tree.left(tree.left(tree.root())).getElement().getKey());
		assertEquals(7, (int) tree.right(tree.left(tree.root())).getElement().getKey());
		assertEquals(16, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(15, (int) tree.left(tree.right(tree.root())).getElement().getKey());
		assertEquals(null, tree.right(tree.right(tree.root())).getElement());

		tree.remove(15);
		assertNull(tree.get(15));

		assertEquals(14, (int) tree.root().getElement().getKey());
		assertEquals(5, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(4, (int) tree.left(tree.left(tree.root())).getElement().getKey());
		assertEquals(7, (int) tree.right(tree.left(tree.root())).getElement().getKey());
		assertEquals(16, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(null, tree.left(tree.right(tree.root())).getElement());

		tree.remove(16);
		assertNull(tree.get(16));

		assertEquals(5, (int) tree.root().getElement().getKey());
		assertEquals(4, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(14, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(7, (int) tree.left(tree.right(tree.root())).getElement().getKey());

		tree.remove(7);
		assertNull(tree.get(7));

		assertEquals(5, (int) tree.root().getElement().getKey());
		assertEquals(4, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(14, (int) tree.right(tree.root()).getElement().getKey());
		assertEquals(null, tree.left(tree.right(tree.root())).getElement());

		tree.remove(4);
		assertNull(tree.get(4));

		assertEquals(5, (int) tree.root().getElement().getKey());
		assertEquals(null, tree.left(tree.root()).getElement());
		assertEquals(14, (int) tree.right(tree.root()).getElement().getKey());

		tree.remove(5);
		assertNull(tree.get(5));

		assertEquals(14, (int) tree.root().getElement().getKey());

		tree.remove(14);
		assertNull(tree.get(14));
		assertNull(tree.root().getElement());
		assertTrue(tree.isEmpty());
		assertEquals(0, tree.size());

	}

}
