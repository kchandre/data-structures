package edu.ncsu.csc316.dsa.set;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the HashSet class
 * 
 * @author Gianna Mastrandrea
 *
 */
public class HashSetTest {

	private Set<Integer> set;
	private Set<Integer> testSet;

	/**
	 * Sets up hash sets for testing
	 */
	@Before
	public void setUp() {
		// Will use a hash map with random alpha, beta values
		set = new HashSet<Integer>();

		// Will use our hash map for testing, which uses alpha=1, beta=1, prime=7
		testSet = new HashSet<Integer>(true);
	}

	/**
	 * Tests add()
	 */
	@Test
	public void testAdd() {
		assertTrue(set.isEmpty());
		assertEquals(0, set.size());

		set.add(4);
		assertFalse(set.isEmpty());
		assertEquals(1, set.size());
		assertTrue(set.contains(4));

		set.add(1);
		assertEquals(2, set.size());
		assertTrue(set.contains(1));
		assertTrue(set.contains(4));

		set.add(1);
		assertEquals(2, set.size());
		assertTrue(set.contains(1));
		assertTrue(set.contains(4));

		set.add(-1);
		assertEquals(3, set.size());
		assertTrue(set.contains(-1));
		assertTrue(set.contains(1));
		assertTrue(set.contains(4));

	}

	/**
	 * Tests contains()
	 */
	@Test
	public void testContains() {
		assertTrue(testSet.isEmpty());
		assertFalse(testSet.contains(3));
		testSet.add(3);
		assertTrue(testSet.contains(3));
		testSet.add(5);
		testSet.add(2);
		testSet.add(4);
		testSet.add(1);
		testSet.add(6);
		assertEquals(6, testSet.size());
		assertFalse(testSet.isEmpty());

		assertTrue(testSet.contains(5));
		assertTrue(testSet.contains(2));
		assertTrue(testSet.contains(4));
		assertTrue(testSet.contains(1));
		assertTrue(testSet.contains(6));

		testSet.remove(3);
		assertFalse(testSet.contains(3));
		assertEquals(5, testSet.size());
	}

	/**
	 * Tests remove()
	 */
	@Test
	public void testRemove() {
		assertTrue(testSet.isEmpty());
		testSet.add(3);
		testSet.add(5);
		testSet.add(2);
		testSet.add(4);
		testSet.add(1);
		testSet.add(6);
		assertEquals(6, testSet.size());
		assertFalse(testSet.isEmpty());

		testSet.remove(0);
		assertEquals(6, testSet.size());

		testSet.remove(3);
		assertEquals(5, testSet.size());
		assertFalse(testSet.contains(3));

		testSet.remove(5);
		assertEquals(4, testSet.size());
		assertFalse(testSet.contains(5));

		testSet.remove(6);
		assertEquals(3, testSet.size());
		assertFalse(testSet.contains(6));

		testSet.remove(1);
		assertEquals(2, testSet.size());
		assertFalse(testSet.contains(1));

		testSet.remove(4);
		assertEquals(1, testSet.size());
		assertFalse(testSet.contains(4));

		testSet.remove(2);
		assertFalse(testSet.contains(2));
		assertTrue(testSet.isEmpty());
	}

	/**
	 * Tests retainAll()
	 */
	@Test
	public void testRetainAll() {
		assertTrue(testSet.isEmpty());
		testSet.add(3);
		testSet.add(5);
		testSet.add(2);
		testSet.add(4);
		testSet.add(1);
		testSet.add(6);
		assertEquals(6, testSet.size());
		assertFalse(testSet.isEmpty());

		testSet.retainAll(set);
		assertTrue(testSet.isEmpty());

		setUp();

		testSet.add(3);
		testSet.add(5);
		testSet.add(2);
		testSet.add(4);
		testSet.add(1);
		testSet.add(6);

		set.add(3);
		set.add(5);
		set.add(2);
		set.add(4);
		set.add(1);
		set.add(6);

		testSet.retainAll(set);
		assertFalse(testSet.isEmpty());
		assertTrue(testSet.contains(3));
		assertTrue(testSet.contains(5));
		assertTrue(testSet.contains(2));
		assertTrue(testSet.contains(4));
		assertTrue(testSet.contains(1));
		assertTrue(testSet.contains(6));

		setUp();

		testSet.add(3);
		testSet.add(5);
		testSet.add(2);
		testSet.add(4);
		testSet.add(1);
		testSet.add(6);

		set.add(3);
		set.add(5);
		set.add(2);

		testSet.retainAll(set);
		assertFalse(testSet.isEmpty());
		assertTrue(testSet.contains(3));
		assertTrue(testSet.contains(5));
		assertTrue(testSet.contains(2));
		assertFalse(testSet.contains(4));
		assertFalse(testSet.contains(1));
		assertFalse(testSet.contains(6));
	}

	/**
	 * Tests removeAll()
	 */
	@Test
	public void testRemoveAll() {
		assertTrue(testSet.isEmpty());
		testSet.add(3);
		testSet.add(5);
		testSet.add(2);
		testSet.add(4);
		testSet.add(1);
		testSet.add(6);
		assertEquals(6, testSet.size());
		assertFalse(testSet.isEmpty());

		testSet.removeAll(set);
		assertEquals(6, testSet.size());
		assertFalse(testSet.isEmpty());
		assertTrue(testSet.contains(3));
		assertTrue(testSet.contains(5));
		assertTrue(testSet.contains(2));
		assertTrue(testSet.contains(4));
		assertTrue(testSet.contains(1));
		assertTrue(testSet.contains(6));

		set.add(3);
		set.add(5);
		set.add(2);

		testSet.removeAll(set);
		assertEquals(3, testSet.size());
		assertFalse(testSet.isEmpty());
		assertFalse(testSet.contains(3));
		assertFalse(testSet.contains(5));
		assertFalse(testSet.contains(2));
		assertTrue(testSet.contains(4));
		assertTrue(testSet.contains(1));
		assertTrue(testSet.contains(6));

		setUp();

		testSet.add(3);
		testSet.add(5);
		testSet.add(2);
		testSet.add(4);
		testSet.add(1);
		testSet.add(6);

		set.add(3);
		set.add(5);
		set.add(2);
		set.add(4);
		set.add(1);
		set.add(6);

		testSet.removeAll(set);
		assertTrue(testSet.isEmpty());
		assertFalse(testSet.contains(3));
		assertFalse(testSet.contains(5));
		assertFalse(testSet.contains(2));
		assertFalse(testSet.contains(4));
		assertFalse(testSet.contains(1));
		assertFalse(testSet.contains(6));

	}

	/**
	 * Tests addAll()
	 */
	@Test
	public void testAddAll() {
		assertTrue(testSet.isEmpty());
		testSet.add(3);
		testSet.add(5);
		testSet.add(2);
		testSet.add(4);
		testSet.add(1);
		testSet.add(6);
		assertEquals(6, testSet.size());
		assertFalse(testSet.isEmpty());

		testSet.addAll(set);
		assertFalse(testSet.isEmpty());
		assertEquals(6, testSet.size());

		set.add(7);
		set.add(8);
		set.add(9);
		set.add(10);
		set.add(11);
		set.add(12);

		testSet.addAll(set);
		assertEquals(12, testSet.size());
		assertTrue(testSet.contains(3));
		assertTrue(testSet.contains(5));
		assertTrue(testSet.contains(2));
		assertTrue(testSet.contains(4));
		assertTrue(testSet.contains(1));
		assertTrue(testSet.contains(6));
		assertTrue(testSet.contains(7));
		assertTrue(testSet.contains(8));
		assertTrue(testSet.contains(9));
		assertTrue(testSet.contains(10));
		assertTrue(testSet.contains(11));
		assertTrue(testSet.contains(12));
	}

	// Since our hash map uses random numbers, it can
	// be difficult to test that our hash set iterator returns
	// values in a certain order.
	// We could approach this problem with a few different options:
	// (1) instead of checking the specific order of entries
	// visited by the iterator, we could save each
	// iterator.next() into an array, then check that the
	// array *contains* the entry, regardless of its exact location
	//
	// (2) implement an isTesting flag for HashSet, similar to HashtestSet.
	// This is the more appropriate option since we can control the
	// entire execution and know exactly which values should appear
	// in the set and in the correct expected order from using the iterator
	//
	// Revisit your test cases for HashMap iterator -- those tests can be adapted
	// to work here, too, since you have already worked out the details
	// of where entries with certain keys will be stored and in what order
	// they will be stored
	/**
	 * Tests iterator()
	 */
	@Test
	public void testIterator() {
		assertTrue(testSet.isEmpty());
		testSet.add(3);
		testSet.add(5);
		testSet.add(2);
		testSet.add(4);
		testSet.add(1);
		testSet.add(6);
		assertEquals(6, testSet.size());
		assertFalse(testSet.isEmpty());

		Iterator<Integer> it = testSet.iterator();
		assertTrue(it.hasNext());
		assertEquals(6, (int) it.next()); // should be index 0
		assertEquals(1, (int) it.next()); // should be index 2
		assertEquals(2, (int) it.next()); // should be index 3
		assertEquals(3, (int) it.next()); // should be index 4
		assertEquals(4, (int) it.next()); // should be index 5
		assertEquals(5, (int) it.next()); // should be index 6
		assertFalse(it.hasNext());
	}
}
