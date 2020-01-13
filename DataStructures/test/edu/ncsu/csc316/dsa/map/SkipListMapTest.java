package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests the SkipListMap class
 * 
 * @author Gianna Mastrandrea
 *
 */
public class SkipListMapTest {

	private SkipListMap<Integer, String> map;
	private SkipListMap<String, Integer> map2;
	private SkipListMap<Student, Integer> studentMap;

	/**
	 * Sets up search table map and student search table map
	 */
	@Before
	public void setUp() {
		map = new SkipListMap<Integer, String>();
		map2 = new SkipListMap<String, Integer>();
		studentMap = new SkipListMap<Student, Integer>();
	}

	/**
	 * Tests put() method
	 */
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals("SkipListMap[3]", map.toString());
		assertEquals(1, map.size());

		assertNull(map.put(1, "string1"));
		assertEquals("SkipListMap[1, 3]", map.toString());
		assertEquals(2, map.size());

		assertNull(map.put(2, "string3"));
		assertEquals("SkipListMap[1, 2, 3]", map.toString());
		assertEquals(3, map.size());

		assertNull(map.put(5, "string5"));
		assertEquals("SkipListMap[1, 2, 3, 5]", map.toString());
		assertEquals(4, map.size());

		assertNull(map.put(4, "string4"));
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
		assertEquals(5, map.size());

		assertEquals("string4", map.put(4, "newstring4"));
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
		assertEquals(5, map.size());
		assertEquals("newstring4", map.get(4));

		// System.out.println(map.toFullString());

		map2.put("A", 1);
		map2.get("A");
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
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());

		assertEquals("string1", map.get(1));
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());

		assertEquals("string2", map.get(2));
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());

		assertEquals("string3", map.get(3));
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());

		assertEquals("string5", map.get(5));
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());

		assertEquals("string4", map.get(4));
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
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
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());

		assertNull(map.remove(0));
		assertEquals(5, map.size());
		assertFalse(map.isEmpty());
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());

		assertEquals("string3", map.remove(3));
		assertEquals(4, map.size());
		assertEquals("SkipListMap[1, 2, 4, 5]", map.toString());

		assertEquals("string1", map.remove(1));
		assertEquals(3, map.size());
		assertEquals("SkipListMap[2, 4, 5]", map.toString());

		assertEquals("string5", map.remove(5));
		assertEquals(2, map.size());
		assertEquals("SkipListMap[2, 4]", map.toString());

		assertEquals("string4", map.remove(4));
		assertEquals(1, map.size());
		assertEquals("SkipListMap[2]", map.toString());

		assertEquals("string2", map.remove(2));
		assertEquals(0, map.size());
		assertEquals("SkipListMap[]", map.toString());
		assertTrue(map.isEmpty());

	}

	/**
	 * Tests studentMap() method
	 */
	@Test
	public void testStudentMap() {
		Student s1 = new Student("J", "K", 1, 0, 0, "jk");
		Student s2 = new Student("J", "S", 2, 0, 0, "js");
		Student s3 = new Student("S", "H", 3, 0, 0, "sh");
		Student s4 = new Student("J", "J", 4, 0, 0, "jj");
		Student s5 = new Student("L", "B", 5, 0, 0, "lb");

		studentMap.put(s1, 100);
		assertEquals(1, studentMap.size());

		studentMap.put(s2, 200);
		studentMap.put(s3, 300);
		studentMap.put(s4, 400);
		studentMap.put(s5, 500);

		Iterator<Map.Entry<Student, Integer>> it = studentMap.entrySet().iterator();
		assertTrue(it.hasNext());
		Map.Entry<Student, Integer> entry = it.next();
		assertEquals(s5, entry.getKey());
		entry = it.next();
		assertEquals(s3, entry.getKey());
		entry = it.next();
		assertEquals(s4, entry.getKey());
		entry = it.next();
		assertEquals(s1, entry.getKey());
		entry = it.next();
		assertEquals(s2, entry.getKey());

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

		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
		assertTrue(it.next().compareTo(1) == 0);
		assertTrue(it.next().compareTo(2) == 0);
		assertTrue(it.next().compareTo(3) == 0);
		assertTrue(it.next().compareTo(4) == 0);
		assertTrue(it.next().compareTo(5) == 0);
		assertFalse(it.hasNext());

		try {
			it.next();
		} catch (Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}
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

		entry = it.next();
		assertEquals(2, (int) (entry.getKey()));
		assertEquals("string2", (String) (entry.getValue()));

		entry = it.next();
		assertEquals(3, (int) (entry.getKey()));
		assertEquals("string3", (String) (entry.getValue()));

		entry = it.next();
		assertEquals(4, (int) (entry.getKey()));
		assertEquals("string4", (String) (entry.getValue()));

		entry = it.next();
		assertEquals(5, (int) (entry.getKey()));
		assertEquals("string5", (String) (entry.getValue()));

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

		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
		assertEquals(it.next(), "string1");
		assertEquals(it.next(), "string2");
		assertEquals(it.next(), "string3");
		assertEquals(it.next(), "string4");
		assertEquals(it.next(), "string5");
		assertFalse(it.hasNext());

		try {
			it.next();
		} catch (Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}
	}

}
