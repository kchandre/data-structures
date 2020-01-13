package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the UnorderedLinkedMap class
 * 
 * @author Gianna Mastrandrea
 *
 */
public class UnorderedLinkedMapTest {

	private Map<Integer, String> map;

	/**
	 * Sets up unordered linked map
	 */
	@Before
	public void setUp() {
		map = new UnorderedLinkedMap<Integer, String>();
	}

	/**
	 * Tests put() method
	 */
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals("UnorderedLinkedMap[3]", map.toString());
		assertEquals(1, map.size());
		assertNull(map.put(5, "string5"));
		assertEquals("UnorderedLinkedMap[5, 3]", map.toString());
		assertEquals(2, map.size());

		assertFalse(map.isEmpty());
		assertNull(map.put(6, "string6"));
		assertEquals("UnorderedLinkedMap[6, 5, 3]", map.toString());
		assertEquals(3, map.size());

		assertEquals("string6", map.put(6, "newstring6"));
		assertEquals("UnorderedLinkedMap[6, 5, 3]", map.toString());
		assertEquals(3, map.size());
		assertEquals("newstring6", map.get(6));

	}

	/**
	 * Tests get() method
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
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());

		assertEquals("string1", map.get(1));
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
		assertEquals(5, map.size());

		assertEquals("string2", map.get(2));
		assertEquals("UnorderedLinkedMap[2, 1, 4, 5, 3]", map.toString());
		assertEquals(5, map.size());

		assertEquals("string3", map.get(3));
		assertEquals("UnorderedLinkedMap[3, 2, 1, 4, 5]", map.toString());
		assertEquals(5, map.size());

		assertEquals("string4", map.get(4));
		assertEquals("UnorderedLinkedMap[4, 3, 2, 1, 5]", map.toString());
		assertEquals(5, map.size());

		assertEquals("string5", map.get(5));
		assertEquals("UnorderedLinkedMap[5, 4, 3, 2, 1]", map.toString());
		assertEquals(5, map.size());

		assertEquals("string4", map.put(4, "newstring4"));
		assertEquals("UnorderedLinkedMap[4, 5, 3, 2, 1]", map.toString());
		assertEquals(5, map.size());

	}

	/**
	 * Tests remove() method
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
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());

		assertNull(map.remove(0));
		assertEquals(5, map.size());
		assertFalse(map.isEmpty());
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());

		assertEquals("string3", map.remove(3));
		assertEquals(4, map.size());
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5]", map.toString());

		assertEquals("string1", map.remove(1));
		assertEquals(3, map.size());
		assertEquals("UnorderedLinkedMap[4, 2, 5]", map.toString());

		assertNull(map.remove(3));
		assertEquals(3, map.size());
		assertEquals("UnorderedLinkedMap[4, 2, 5]", map.toString());

		assertEquals("string2", map.remove(2));
		assertEquals(2, map.size());
		assertEquals("UnorderedLinkedMap[4, 5]", map.toString());

		assertEquals("string5", map.remove(5));
		assertEquals(1, map.size());
		assertEquals("UnorderedLinkedMap[4]", map.toString());

		assertEquals("string4", map.remove(4));
		assertEquals(0, map.size());
		assertEquals("UnorderedLinkedMap[]", map.toString());
		assertTrue(map.isEmpty());

	}

	/**
	 * Tests iterator() method
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

		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
		assertEquals(it.next().compareTo(1), 0);
		assertEquals(it.next().compareTo(4), 0);
		assertEquals(it.next().compareTo(2), 0);
		assertEquals(it.next().compareTo(5), 0);
		assertEquals(it.next().compareTo(3), 0);

	}

	/**
	 * Tests entrySet() method
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
		Map.Entry<Integer, String> entry = it.next();
		assertEquals(1, (int) (entry.getKey()));
		assertEquals("string1", (String) (entry.getValue()));

		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
		entry = it.next();
		assertEquals(4, (int) (entry.getKey()));
		assertEquals("string4", (String) (entry.getValue()));

		entry = it.next();
		assertEquals(2, (int) (entry.getKey()));
		assertEquals("string2", (String) (entry.getValue()));

		entry = it.next();
		assertEquals(5, (int) (entry.getKey()));
		assertEquals("string5", (String) (entry.getValue()));

		entry = it.next();
		assertEquals(3, (int) (entry.getKey()));
		assertEquals("string3", (String) (entry.getValue()));

	}

	/**
	 * Tests values() method
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

		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
		assertEquals("string1", it.next());
		assertEquals("string4", it.next());
		assertEquals("string2", it.next());
		assertEquals("string5", it.next());
		assertEquals("string3", it.next());

	}

}
