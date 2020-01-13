package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map;

/**
 * Tests the LinearProbingHashMap class
 * 
 * @author Gianna Mastrandrea
 *
 */
public class LinearProbingHashMapTest {

	private Map<Integer, String> map;

	/**
	 * Sets up linear probing hash map for testing
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
		map = new LinearProbingHashMap<Integer, String>(7, true);
	}

	/**
	 * Tests put()
	 */
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals(1, map.size());
		assertFalse(map.isEmpty());

		// Since our entrySet method returns the entries in the table
		// from left to right, we can use the entrySet to check
		// that our values are in the correct order in the hash table.
		// Alternatively, you could implement a toString() method if you
		// want to check that the exact index of each entry is correct
		Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
		assertEquals(3, (int) it.next().getKey()); // should be index 4

		assertNull(map.put(4, "string4"));
		assertEquals(2, map.size());
		assertFalse(map.isEmpty());
		it = map.entrySet().iterator();
		assertEquals(3, (int) it.next().getKey()); // should be index 4
		assertEquals(4, (int) it.next().getKey()); // should be index 5
		assertEquals(map.put(4, "newstring4"), "string4");
		assertEquals(map.get(4), "newstring4");
		assertNull(map.put(5, "string5"));
		assertNull(map.put(6, "string6"));

		map = new LinearProbingHashMap<Integer, String>(true); // new map with default capacity for testing
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals(1, map.size());
		assertFalse(map.isEmpty());

		Iterator<Map.Entry<Integer, String>> it2 = map.entrySet().iterator();
		assertEquals(3, (int) it2.next().getKey()); // should be index 4

		assertNull(map.put(4, "string4"));
		assertEquals(2, map.size());
		assertFalse(map.isEmpty());
		it2 = map.entrySet().iterator();
		assertEquals(3, (int) it2.next().getKey()); // should be index 4
		assertEquals(4, (int) it2.next().getKey()); // should be index 5

		map = new LinearProbingHashMap<Integer, String>(7); // new map, not for testing
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals(1, map.size());
		assertFalse(map.isEmpty());

		Iterator<Map.Entry<Integer, String>> it3 = map.entrySet().iterator();
		assertEquals(3, (int) it3.next().getKey());

		assertNull(map.put(4, "string4"));
		assertEquals(2, map.size());
		assertFalse(map.isEmpty());
		it3 = map.entrySet().iterator();
		assertTrue(it3.hasNext());

		map = new LinearProbingHashMap<Integer, String>(); // new map with default capacity, not for testing
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals(1, map.size());
		assertFalse(map.isEmpty());

		Iterator<Map.Entry<Integer, String>> it4 = map.entrySet().iterator();
		assertEquals(3, (int) it4.next().getKey());

		assertNull(map.put(4, "string4"));
		assertEquals(2, map.size());
		assertFalse(map.isEmpty());
		it4 = map.entrySet().iterator();
		assertTrue(it4.hasNext());
	}

	/**
	 * Tests get()
	 */
	@Test
	public void testGet() {
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals(1, map.size());
		assertEquals(map.get(3), "string3");
		map.remove(3);
		assertTrue(map.isEmpty());
		assertNull(map.get(3));

		assertNull(map.put(4, "string4"));
		assertEquals(map.put(4, "secondstring4"), "string4");
		assertNull(map.put(0, "string0"));
		assertEquals(map.get(0), "string0");
		assertEquals(map.get(4), "secondstring4");
		assertEquals(map.remove(4), "secondstring4");
		assertNull(map.get(4));
		assertNull(map.remove(4));
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
		assertEquals(1, (int) it.next()); // should be index 2
		assertTrue(it.hasNext());
		assertEquals(2, (int) it.next()); // should be index 3
		assertEquals(3, (int) it.next()); // should be index 4
		assertEquals(4, (int) it.next()); // should be index 5
		assertEquals(5, (int) it.next()); // should be index 6
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
		assertNull(map.put(6, "string6"));

		Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
		assertTrue(it.hasNext());
		assertEquals(6, (int) it.next().getKey()); // should be index 0
		assertTrue(it.hasNext());
		assertEquals(1, (int) it.next().getKey()); // should be index 2
		assertEquals(2, (int) it.next().getKey()); // should be index 3
		assertEquals(3, (int) it.next().getKey()); // should be index 4
		assertEquals(4, (int) it.next().getKey()); // should be index 5
		assertEquals(5, (int) it.next().getKey()); // should be index 6
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
		assertNull(map.put(6, "string6"));

		Iterator<String> it = map.values().iterator();
		assertTrue(it.hasNext());
		assertEquals("string6", it.next());
		assertEquals("string1", it.next());
		assertEquals("string2", it.next());
		assertEquals("string3", it.next());
		assertEquals("string4", it.next());
		assertEquals("string5", it.next());
		assertFalse(it.hasNext());
	}

}
