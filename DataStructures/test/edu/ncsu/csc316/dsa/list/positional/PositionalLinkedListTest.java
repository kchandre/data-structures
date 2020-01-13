package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Tests the PositionalLinkedList class
 * 
 * @author Gianna Mastrandrea
 *
 */
public class PositionalLinkedListTest {

	private PositionalList<String> list;

	/**
	 * Sets up list for testing
	 */
	@Before
	public void setUp() {
		list = new PositionalLinkedList<String>();
	}

	/**
	 * Tests the first() method
	 */
	@Test
	public void testFirst() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		try {
			list.first();
		} catch (Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}

		Position<String> first = list.addFirst("one");
		assertEquals(1, list.size());
		assertEquals(first, list.first());

		Position<String> first2 = list.addFirst("new one");
		assertEquals(2, list.size());
		assertEquals(first2, list.first());
		assertEquals(first, list.last());
	}

	/**
	 * Tests the last() method
	 */
	@Test
	public void testLast() {
		list = new PositionalLinkedList<String>();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		try {
			list.last();
		} catch (Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}

		Position<String> last = list.addLast("one");
		assertEquals(1, list.size());
		assertEquals(last, list.last());

		Position<String> last2 = list.addLast("two");
		assertEquals(2, list.size());
		assertEquals(last2, list.last());
		assertEquals(last, list.first());
	}

	/**
	 * Tests the addFirst() method
	 */
	@Test
	public void testAddFirst() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addFirst("one");
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());

		assertEquals(first, list.first());

		Position<String> newFirst = list.addFirst("new one");
		assertEquals(2, list.size());
		assertEquals(newFirst, list.first());
	}

	/**
	 * Tests the addLast() method
	 */
	@Test
	public void testAddLast() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addLast("one");
		assertEquals(1, list.size());

		assertEquals(first, list.last());

		Position<String> last2 = list.addLast("two");
		assertEquals(2, list.size());
		assertEquals(last2, list.last());
		assertEquals(first, list.first());
	}

	/**
	 * Tests the addBefore() method
	 */
	@Test
	public void testAddBefore() {
		Position<String> one = list.addLast("one");
		assertEquals(1, list.size());
		assertEquals(one, list.first());

		Position<String> beforeOne = list.addBefore(one, "beforeOne");
		assertEquals(beforeOne, list.first());

		Position<String> two = list.addLast("two");
		Position<String> three = list.addLast("three");
		Position<String> beforeTwo = list.addBefore(two, "beforeTwo");
		assertEquals(beforeTwo, list.before(two));
		assertEquals(three, list.last());
	}

	/**
	 * Tests the addAfter() method
	 */
	@Test
	public void testAddAfter() {
		Position<String> one = list.addLast("one");
		assertEquals(1, list.size());
		assertEquals(one, list.first());
		list.addLast("two");

		Position<String> afterOne = list.addAfter(one, "afterOne");
		assertEquals(afterOne, list.after(list.first()));

		Position<String> three = list.addLast("three");
		Position<String> afterThree = list.addAfter(three, "afterThree");
		assertEquals(afterThree, list.last());
	}

	/**
	 * Tests the before() method
	 */
	@Test
	public void testBefore() {
		Position<String> one = list.addLast("one");
		Position<String> two = list.addLast("two");
		Position<String> three = list.addLast("three");
		assertEquals(one, list.first());
		assertEquals(two, list.after(one));
		assertEquals(one, list.before(two));
		assertEquals(two, list.before(three));
		assertEquals(three, list.last());
		assertEquals(three, list.after(two));
	}

	/**
	 * Tests the after() method
	 */
	@Test
	public void testAfter() {
		Position<String> one = list.addLast("one");
		Position<String> two = list.addLast("two");
		Position<String> three = list.addLast("three");
		assertEquals(one, list.first());
		assertEquals(two, list.after(one));
		assertEquals(one, list.before(two));
		assertEquals(two, list.before(three));
		assertEquals(three, list.last());
		assertEquals(three, list.after(two));
	}

	/**
	 * Tests the set() method
	 */
	@Test
	public void testSet() {
		Position<String> one = list.addLast("one");
		list.addLast("two");
		Position<String> three = list.addLast("three");
		list.addLast("four");

		assertEquals("one", list.set(one, "newOne"));
		assertEquals("newOne", list.first().getElement());
		assertEquals("three", list.set(three, "newThree"));
		assertEquals("newThree", list.before(list.last()).getElement());
	}

	/**
	 * Tests the remove() method
	 */
	@Test
	public void testRemove() {
		Position<String> one = list.addLast("one");
		Position<String> two = list.addLast("two");
		Position<String> three = list.addLast("three");
		Position<String> four = list.addLast("four");

		assertEquals("four", list.remove(four));
		assertEquals(three, list.last());
		assertEquals(list.size(), 3);

		Position<String> newFour = list.addLast("newFour");
		list.remove(two);
		assertEquals(one, list.first());
		assertEquals(three, list.after(one));
		assertEquals(newFour, list.after(three));
		assertEquals(one, list.before(three));
		assertFalse(two == list.before(three));
		assertEquals(newFour, list.last());
		assertEquals(list.size(), 3);
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
		// list.addLast("two");

		// Use accessor methods to check that the list is correct
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		assertEquals("one", list.first().getElement());

		// Create an iterator for the list that has 1 element
		it = list.iterator();

		// Try different iterator operations to make sure they work
		// as expected for a list that contains 1 element (at this point)
		assertTrue(it.hasNext());
		assertEquals("one", it.next());
		assertFalse(it.hasNext());

		// it.remove();
		try {
			it.next();
		} catch (Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}

		list.addLast("two");
		list.addLast("three");
		list.addLast("four");
		list.addLast("five");

		it = list.iterator();

		assertTrue(it.hasNext());
		assertEquals("one", it.next());
		assertEquals("two", it.next());
		it.remove();
		assertEquals("three", it.next());
		assertEquals("four", it.next());

		list = new PositionalLinkedList<String>();
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
	 * Tests the position() method
	 */
	@Test
	public void testPositions() {
		assertEquals(0, list.size());
		Position<String> first = list.addFirst("one");
		Position<String> second = list.addLast("two");
		Position<String> third = list.addLast("three");
		assertEquals(3, list.size());

		Iterator<Position<String>> it = list.positions().iterator();
		assertTrue(it.hasNext());
		assertEquals(first, it.next());
		assertEquals(second, it.next());
		assertEquals(third, it.next());

		it.remove();
		assertFalse(it.hasNext());
		try {
			it.next();
		} catch (Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}

	}

}
