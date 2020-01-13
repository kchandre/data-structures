package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the ArrayBasedList class
 * 
 * @author Gianna Mastrandrea
 *
 */
public class ArrayBasedListTest {

	private List<String> list;

	/**
	 * Creates new empty array-based list
	 */
	@Before
	public void setUp() {
		list = new ArrayBasedList<String>();
	}

	/**
	 * Tests the add() method with given indexes
	 */
	@Test
	public void testAddIndex() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());

		try {
			list.add(15, "fifteen");
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}

		list.add(1, "two");
		list.add(2, "three");
		list.add(3, "four");
		list.add(4, "five");
		list.add(5, "six");
		list.add(6, "seven");
		list.add(7, "eight");
		list.add(8, "nine");
		list.add(9, "ten");
		// test resize operation
		list.add(0, "new one");

		assertEquals(11, list.size());
		assertEquals("new one", list.get(0));
		assertEquals("one", list.get(1));
		assertEquals("two", list.get(2));
		assertEquals("three", list.get(3));
		assertEquals("four", list.get(4));
		assertEquals("five", list.get(5));
		assertEquals("six", list.get(6));
		assertEquals("seven", list.get(7));
		assertEquals("eight", list.get(8));
		assertEquals("nine", list.get(9));
		assertEquals("ten", list.get(10));

		// test random indexes
		list.add(4, "random four");
		assertEquals("new one", list.get(0));
		assertEquals("one", list.get(1));
		assertEquals("two", list.get(2));
		assertEquals("three", list.get(3));
		assertEquals("random four", list.get(4));
		assertEquals("four", list.get(5));
		assertEquals("five", list.get(6));
		assertEquals("six", list.get(7));
		assertEquals("seven", list.get(8));
		assertEquals("eight", list.get(9));
		assertEquals("nine", list.get(10));
		assertEquals("ten", list.get(11));

	}

	/**
	 * Tests the addLast() method
	 */
	@Test
	public void testAddLast() {
		list = new ArrayBasedList<String>();
		list.add(0, "one");
		list.add(1, "two");
		list.add(2, "three");
		list.add(3, "four");
		list.add(4, "five");
		list.add(5, "six");
		list.add(6, "seven");
		list.add(7, "eight");
		list.add(8, "nine");
		list.add(9, "ten");

		list.addLast("end");

		assertEquals("one", list.get(0));
		assertEquals("two", list.get(1));
		assertEquals("three", list.get(2));
		assertEquals("four", list.get(3));
		assertEquals("five", list.get(4));
		assertEquals("six", list.get(5));
		assertEquals("seven", list.get(6));
		assertEquals("eight", list.get(7));
		assertEquals("nine", list.get(8));
		assertEquals("ten", list.get(9));
		assertEquals("end", list.get(10));
	}

	/**
	 * Tests the last() method
	 */
	@Test
	public void testLast() {
		list = new ArrayBasedList<String>();
		list.add(0, "one");
		list.add(1, "two");
		list.add(2, "three");
		assertEquals("three", list.last());
	}

	/**
	 * Tests the addFirst() method
	 */
	@Test
	public void testAddFirst() {
		list = new ArrayBasedList<String>();
		list.add(0, "one");
		list.add(1, "two");
		list.add(2, "three");

		list.addFirst("first!");
		assertEquals("first!", list.get(0));
		assertEquals("one", list.get(1));
		assertEquals("two", list.get(2));
		assertEquals("three", list.get(3));
	}

	/**
	 * Tests the first() method
	 */
	@Test
	public void testFirst() {
		list = new ArrayBasedList<String>();
		list.add(0, "one");
		list.add(1, "two");
		list.add(2, "three");

		assertEquals("one", list.first());
	}

	/**
	 * Tests the iterator() method
	 */
	@Test
	public void testIterator() {
		// Start with an empty list
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		// Create an iterator for the empty list
		Iterator<String> it = list.iterator();

		// Try different operations to make sure they work
		// as expected for an empty list (at this point)
		try {
			it.remove();
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
		assertFalse(it.hasNext());

		// Now add an element
		list.addLast("one");

		// Use accessor methods to check that the list is correct
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		assertEquals("one", list.get(0));

		// Create an iterator for the list that has 1 element
		it = list.iterator();

		// Try different iterator operations to make sure they work
		// as expected for a list that contains 1 element (at this point)
		assertTrue(it.hasNext());
		assertEquals("one", it.next());
		assertFalse(it.hasNext());
		try {
			it.next();
		} catch (Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}

		it = list.iterator();

		list.addLast("two");
		list.addLast("three");
		list.addLast("four");
		list.addLast("five");

		assertTrue(it.hasNext());
		assertEquals("one", it.next());
		assertEquals("two", it.next());
		it.remove();
		assertEquals("three", it.next());
		assertEquals("four", it.next());

		list = new ArrayBasedList<String>();
		list.addLast("one");
		list.addLast("two");
		list.addLast("three");
		it = list.iterator();

		it.next();
		it.remove();
		assertEquals(list.size(), 2);
		assertEquals(it.next(), "two");

	}

	/**
	 * Tests the remove() method at given index
	 */
	@Test
	public void testRemoveIndex() {
		list = new ArrayBasedList<String>();
		list.add(0, "one");
		list.add(1, "two");
		list.add(2, "three");
		list.add(3, "four");
		list.add(4, "five");
		list.add(5, "six");

		list.remove(2);
		assertEquals(5, list.size());
		assertEquals("one", list.get(0));
		assertEquals("two", list.get(1));
		assertEquals("four", list.get(2));
		assertEquals("five", list.get(3));
		assertEquals("six", list.get(4));
	}

	/**
	 * Tests the removeFirst() method
	 */
	@Test
	public void testRemoveFirst() {
		list = new ArrayBasedList<String>();
		list.add(0, "one");
		list.add(1, "two");
		list.add(2, "three");
		list.add(3, "four");
		list.add(4, "five");
		list.add(5, "six");

		assertEquals("one", list.removeFirst());
		assertEquals(5, list.size());
		assertEquals("two", list.get(0));
		assertEquals("three", list.get(1));
		assertEquals("four", list.get(2));
		assertEquals("five", list.get(3));
		assertEquals("six", list.get(4));
	}

	/**
	 * Tests the removeLast() method
	 */
	@Test
	public void testRemoveLast() {
		list = new ArrayBasedList<String>();
		list.add(0, "one");
		list.add(1, "two");
		list.add(2, "three");
		list.add(3, "four");
		list.add(4, "five");
		list.add(5, "six");

		assertEquals("six", list.removeLast());
		assertEquals(5, list.size());
		assertEquals("one", list.get(0));
		assertEquals("two", list.get(1));
		assertEquals("three", list.get(2));
		assertEquals("four", list.get(3));
		assertEquals("five", list.get(4));
	}

	/**
	 * Tests the set() method
	 */
	@Test
	public void testSet() {
		list = new ArrayBasedList<String>();
		list.add(0, "one");
		list.add(1, "two");
		list.add(2, "three");
		list.add(3, "four");
		list.add(4, "five");
		list.add(5, "six");

		assertEquals("one", list.set(0, "new one"));
		assertEquals(6, list.size());
		assertEquals("new one", list.get(0));
		assertEquals("two", list.get(1));
		assertEquals("three", list.get(2));
		assertEquals("four", list.get(3));
		assertEquals("five", list.get(4));
		assertEquals("six", list.get(5));
	}
}
