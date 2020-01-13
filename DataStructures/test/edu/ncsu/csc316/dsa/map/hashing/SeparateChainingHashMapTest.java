package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map;

/**
 * Tests the SeparateChainingHashMap class
 * 
 * @author Gianna Mastrandrea
 *
 */
public class SeparateChainingHashMapTest {

	private Map<Integer, String> map;

	/**
	 * Sets up separate chaining hash map for testing
	 */
	@Before
	public void setUp() {
		// Use the "true" flag to indicate we are testing.
		// Remember that (when testing) alpha = 1, beta = 1, and prime = 7
		// based on our AbstractHashMap constructor.
		// That means you can draw the hash table by hand
		// if you use integer keys, since Integer.hashCode() = the integer value, itself
		// Finally, apply compression. For example:
		// for key = 1: h(1) = ( (1 * 1 + 1) % 7) % 7 = 2
		// for key = 2: h(2) = ( (1 * 2 + 1) % 7) % 7 = 3
		// for key = 3: h(3) = ( (1 * 3 + 1) % 7) % 7 = 4
		// for key = 4: h(4) = ( (1 * 4 + 1) % 7) % 7 = 5
		// for key = 5: h(5) = ( (1 * 5 + 1) % 7) % 7 = 6
		// for key = 6: h(6) = ( (1 * 6 + 1) % 7) % 7 = 0
		// etc.
		// Remember that our secondary map (an AVL tree) is a search
		// tree, which means the entries should be sorted in order within
		// that tree
		map = new SeparateChainingHashMap<Integer, String>(7, true);
	}

	/**
	 * Tests put()
	 */
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));

		// Since our entrySet method returns the entries in the table
		// from left to right, we can use the entrySet to check
		// that our values are in the correct order in the hash table.
		// Alternatively, you could implement a toString() method if you
		// want to check that the exact index/map of each bucket is correct
		Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
		assertEquals(3, (int) it.next().getKey()); // should be in a map in index 4

		assertNull(map.put(4, "string4"));
		assertEquals(2, map.size());
		assertFalse(map.isEmpty());
		it = map.entrySet().iterator();
		assertEquals(3, (int) it.next().getKey()); // should be in a map in index 4
		assertEquals(4, (int) it.next().getKey()); // should be in a map in index 5

		assertNull(map.put(5, "string5"));
		assertNull(map.put(6, "string6"));
		assertNull(map.put(7, "string7"));
		assertNull(map.put(8, "string8"));
		assertNull(map.put(9, "string9"));
		assertNull(map.put(10, "string10"));

		assertEquals(8, map.size());
		assertFalse(map.isEmpty());
		it = map.entrySet().iterator();
		assertEquals(6, (int) it.next().getKey()); // should be in a map in index 0
		assertEquals(7, (int) it.next().getKey()); // should be in a map in index 1
		assertEquals(8, (int) it.next().getKey()); // should be in a map in index 2
		assertEquals(9, (int) it.next().getKey()); // should be in a map in index 3
		assertEquals(3, (int) it.next().getKey()); // should be in a map in index 4
		assertEquals(10, (int) it.next().getKey()); // should be in a map in index 5
		assertEquals(4, (int) it.next().getKey()); // should be in a map in index 6
		assertEquals(5, (int) it.next().getKey()); // should be in a map in index 7
	}

	/**
	 * Tests get()
	 */
	@Test
	public void testGet() {
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		assertFalse(map.isEmpty());

		assertEquals(map.get(3), "string3");
		assertEquals(map.put(3, "newstring3"), "string3");
		assertEquals(map.get(3), "newstring3");
		assertEquals(map.get(5), "string5");
		assertEquals(map.get(2), "string2");
		assertEquals(map.get(4), "string4");
		assertEquals(map.get(1), "string1");

		map.remove(3);
		assertNull(map.get(3));
		assertEquals(map.size(), 4);

		assertNull(map.put(3, "secondstring3"));
		assertEquals(map.get(3), "secondstring3");

		map = new SeparateChainingHashMap<Integer, String>(); // map with default capacity not for testing
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		assertFalse(map.isEmpty());

		assertEquals(map.get(3), "string3");
		assertEquals(map.put(3, "newstring3"), "string3");
		assertEquals(map.get(3), "newstring3");
		assertEquals(map.get(5), "string5");
		assertEquals(map.get(2), "string2");
		assertEquals(map.get(4), "string4");
		assertEquals(map.get(1), "string1");

		map.remove(3);
		assertNull(map.get(3));
		assertEquals(map.size(), 4);

		assertNull(map.put(3, "secondstring3"));
		assertEquals(map.get(3), "secondstring3");

		map = new SeparateChainingHashMap<Integer, String>(2); // map with small capacity not for testing
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		assertFalse(map.isEmpty());

		assertEquals(map.get(3), "string3");
		assertEquals(map.put(3, "newstring3"), "string3");
		assertEquals(map.get(3), "newstring3");
		assertEquals(map.get(5), "string5");
		assertEquals(map.get(2), "string2");
		assertEquals(map.get(4), "string4");
		assertEquals(map.get(1), "string1");

		map.remove(3);
		assertNull(map.get(3));
		assertEquals(map.size(), 4);

		assertNull(map.put(3, "secondstring3"));
		assertEquals(map.get(3), "secondstring3");
	}

	/**
	 * Tests remove()
	 */
	@Test
	public void testRemove() {
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		assertFalse(map.isEmpty());

		assertEquals(map.remove(3), "string3");
		assertEquals(map.size(), 4);
		assertNull(map.get(3));
		assertEquals(map.remove(1), "string1");
		assertEquals(map.size(), 3);
		assertNull(map.get(1));
		assertEquals(map.remove(5), "string5");
		assertEquals(map.size(), 2);
		assertNull(map.get(5));
		assertEquals(map.remove(4), "string4");
		assertEquals(map.size(), 1);
		assertNull(map.get(4));
		assertEquals(map.remove(2), "string2");
		assertEquals(map.size(), 0);
		assertNull(map.get(2));
		assertNull(map.remove(2));
		assertEquals(map.size(), 0);

	}

	/**
	 * Tests iterator()
	 */
	@Test
	public void testIterator() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));

		Iterator<Integer> it = map.iterator();
		assertTrue(it.hasNext());

		assertEquals(1, (int) it.next());
		assertTrue(it.hasNext());
		assertEquals(2, (int) it.next());
		assertEquals(3, (int) it.next());
		assertEquals(4, (int) it.next());
		assertEquals(5, (int) it.next());
		assertFalse(it.hasNext());

		map = new SeparateChainingHashMap<Integer, String>(true); // map with default capacity, for testing
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));

		it = map.iterator();
		assertTrue(it.hasNext());

		assertEquals(1, (int) it.next());
		assertTrue(it.hasNext());
		assertEquals(2, (int) it.next());
		assertEquals(3, (int) it.next());
		assertEquals(4, (int) it.next());
		assertEquals(5, (int) it.next());
		assertFalse(it.hasNext());
	}

	/**
	 * Tests entrySet()
	 */
	@Test
	public void testEntrySet() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));

		Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
		assertTrue(it.hasNext());

		assertEquals(1, (int) it.next().getKey());
		assertEquals(2, (int) it.next().getKey());
		assertEquals(3, (int) it.next().getKey());
		assertEquals(4, (int) it.next().getKey());
		assertEquals(5, (int) it.next().getKey());
		assertFalse(it.hasNext());
	}

	/**
	 * Tests values()
	 */
	@Test
	public void testValues() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));

		Iterator<String> it = map.values().iterator();
		assertTrue(it.hasNext());

		assertEquals("string1", it.next());
		assertEquals("string2", it.next());
		assertEquals("string3", it.next());
		assertEquals("string4", it.next());
		assertEquals("string5", it.next());
	}

}
