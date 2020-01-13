package edu.ncsu.csc316.dsa.set;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the TreeSet class
 * 
 * @author Gianna Mastrandrea
 *
 */
public class TreeSetTest {

	private Set<Integer> set;

	/**
	 * Sets up tree set for testing
	 */
	@Before
	public void setUp() {
		set = new TreeSet<Integer>();
	}

	/**
	 * Tests add()
	 */
	@Test
	public void testAdd() {
		assertTrue(set.isEmpty());
		assertEquals(0, set.size());

		set.add(5);
		assertEquals(1, set.size());
		assertFalse(set.isEmpty());

		set.add(4);
		assertFalse(set.isEmpty());
		assertEquals(2, set.size());
		assertTrue(set.contains(4));

		set.add(1);
		assertEquals(3, set.size());
		assertTrue(set.contains(1));
		assertTrue(set.contains(4));

		set.add(1);
		assertEquals(3, set.size());
		assertTrue(set.contains(1));
		assertTrue(set.contains(4));

		set.add(-1);
		assertEquals(4, set.size());
		assertTrue(set.contains(-1));
		assertTrue(set.contains(1));
		assertTrue(set.contains(4));
	}

	/**
	 * Tests contains()
	 */
	@Test
	public void testContains() {
		assertTrue(set.isEmpty());
		assertEquals(0, set.size());
		assertFalse(set.contains(0));
		set.add(5);
		set.add(10);
		set.add(15);
		set.add(20);
		set.add(25);
		assertFalse(set.isEmpty());
		assertEquals(5, set.size());

		assertFalse(set.contains(-1));

		assertTrue(set.contains(5));
		assertTrue(set.contains(10));
		assertTrue(set.contains(15));
		assertTrue(set.contains(20));
		assertTrue(set.contains(25));

		set.remove(5);
		assertFalse(set.contains(5));
	}

	/**
	 * Tests remove()
	 */
	@Test
	public void testRemove() {
		assertTrue(set.isEmpty());
		assertEquals(0, set.size());
		set.add(5);
		set.add(10);
		set.add(15);
		set.add(20);
		set.add(25);
		assertEquals(5, set.size());

		set.remove(3);
		assertEquals(5, set.size());
		assertFalse(set.contains(3));

		set.remove(5);
		assertEquals(4, set.size());
		assertFalse(set.contains(5));

		set.remove(10);
		assertEquals(3, set.size());
		assertFalse(set.contains(10));

		set.remove(15);
		assertEquals(2, set.size());
		assertFalse(set.contains(15));

		set.remove(20);
		assertEquals(1, set.size());
		assertFalse(set.contains(20));

		set.remove(25);
		assertTrue(set.isEmpty());
		assertFalse(set.contains(25));

		set.remove(25);
		assertTrue(set.isEmpty());
	}

	/**
	 * Tests retainAll()
	 */
	@Test
	public void testRetainAll() {
		assertTrue(set.isEmpty());
		assertEquals(0, set.size());
		set.add(5);
		set.add(10);
		set.add(15);
		set.add(20);
		set.add(25);
		assertEquals(5, set.size());

		Set<Integer> other = new TreeSet<Integer>();

		set.retainAll(other);
		assertTrue(set.isEmpty());

		setUp();

		set.add(3);
		set.add(5);
		set.add(2);
		set.add(4);
		set.add(1);
		set.add(6);

		other.add(3);
		other.add(5);
		other.add(2);
		other.add(4);
		other.add(1);
		other.add(6);

		set.retainAll(other);
		assertFalse(set.isEmpty());
		assertTrue(set.contains(3));
		assertTrue(set.contains(5));
		assertTrue(set.contains(2));
		assertTrue(set.contains(4));
		assertTrue(set.contains(1));
		assertTrue(set.contains(6));

		setUp();
		other = new TreeSet<Integer>();

		set.add(3);
		set.add(5);
		set.add(2);
		set.add(4);
		set.add(1);
		set.add(6);

		other.add(3);
		other.add(5);
		other.add(2);

		set.retainAll(other);
		assertFalse(set.isEmpty());
		assertTrue(set.contains(3));
		assertTrue(set.contains(5));
		assertTrue(set.contains(2));
		assertFalse(set.contains(4));
		assertFalse(set.contains(1));
		assertFalse(set.contains(6));
	}

	/**
	 * Tests removeAll()
	 */
	@Test
	public void testRemoveAll() {
		assertTrue(set.isEmpty());
		assertEquals(0, set.size());
		set.add(5);
		set.add(10);
		set.add(15);
		set.add(20);
		set.add(25);
		assertEquals(5, set.size());

		Set<Integer> other = new TreeSet<Integer>();

		set.removeAll(other);
		assertEquals(5, set.size());
		assertFalse(set.isEmpty());
		assertTrue(set.contains(5));
		assertTrue(set.contains(10));
		assertTrue(set.contains(15));
		assertTrue(set.contains(20));
		assertTrue(set.contains(25));

		other.add(5);
		other.add(10);
		other.add(15);

		set.removeAll(other);
		assertEquals(2, set.size());
		assertFalse(set.isEmpty());
		assertFalse(set.contains(5));
		assertFalse(set.contains(10));
		assertFalse(set.contains(15));
		assertTrue(set.contains(20));
		assertTrue(set.contains(25));

		setUp();
		other = new TreeSet<Integer>();

		set.add(3);
		set.add(5);
		set.add(2);
		set.add(4);
		set.add(1);
		set.add(6);

		other.add(3);
		other.add(5);
		other.add(2);
		other.add(4);
		other.add(1);
		other.add(6);

		set.removeAll(other);
		assertTrue(set.isEmpty());
		assertFalse(set.contains(3));
		assertFalse(set.contains(5));
		assertFalse(set.contains(2));
		assertFalse(set.contains(4));
		assertFalse(set.contains(1));
		assertFalse(set.contains(6));
	}

	/**
	 * Tests addAll()
	 */
	@Test
	public void testAddAll() {
		assertTrue(set.isEmpty());
		assertEquals(0, set.size());
		set.add(5);
		set.add(10);
		set.add(15);
		set.add(20);
		set.add(25);
		assertEquals(5, set.size());

		Set<Integer> other = new TreeSet<Integer>();
		set.addAll(other);
		assertEquals(5, set.size());

		other.add(30);
		other.add(40);
		other.add(50);

		set.addAll(other);
		assertEquals(8, set.size());
		assertTrue(set.contains(5));
		assertTrue(set.contains(10));
		assertTrue(set.contains(15));
		assertTrue(set.contains(20));
		assertTrue(set.contains(25));
		assertTrue(set.contains(30));
		assertTrue(set.contains(40));
		assertTrue(set.contains(50));

		other.remove(30);
		set.addAll(other);
		assertEquals(8, set.size());
		assertTrue(set.contains(5));
		assertTrue(set.contains(10));
		assertTrue(set.contains(15));
		assertTrue(set.contains(20));
		assertTrue(set.contains(25));
		assertTrue(set.contains(30));
		assertTrue(set.contains(40));
		assertTrue(set.contains(50));

	}

	/**
	 * Tests iterator()
	 */
	@Test
	public void testIterator() {
		assertTrue(set.isEmpty());
		assertEquals(0, set.size());
		set.add(5);
		set.add(10);
		set.add(15);
		set.add(20);
		set.add(25);
		assertEquals(5, set.size());

		Iterator<Integer> it = set.iterator();
		assertTrue(it.hasNext());
		assertEquals(5, (int) it.next());
		assertEquals(10, (int) it.next());
		assertEquals(15, (int) it.next());
		assertEquals(20, (int) it.next());
		assertEquals(25, (int) it.next());
		assertFalse(it.hasNext());
	}

}
