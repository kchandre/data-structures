package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * Tests the HeapAdaptablePriorityQueue class
 * 
 * @author Gianna Mastrandrea
 *
 */
public class HeapAdaptablePriorityQueueTest {

	private HeapAdaptablePriorityQueue<Integer, String> heap;

	/**
	 * Sets up heap adaptable priority queue for testing
	 */
	@Before
	public void setUp() {
		heap = new HeapAdaptablePriorityQueue<Integer, String>();
	}

	/**
	 * Tests replaceKey()
	 */
	@Test
	public void testReplaceKey() {
		assertTrue(heap.isEmpty());
		assertEquals(0, heap.size());

		Entry<Integer, String> e8 = heap.insert(8, "eight");
		Entry<Integer, String> e7 = heap.insert(7, "seven");
		Entry<Integer, String> e6 = heap.insert(6, "six");
		Entry<Integer, String> e5 = heap.insert(5, "five");
		Entry<Integer, String> e4 = heap.insert(4, "four");
		assertEquals(5, heap.size());

		heap.replaceKey(e8, -5);
		assertEquals(-5, (int) heap.min().getKey());
		assertEquals("eight", heap.min().getValue());
		assertEquals(5, heap.size());

		heap.replaceKey(e7, -6);
		assertEquals(-6, (int) heap.min().getKey());
		assertEquals("seven", heap.min().getValue());
		assertEquals(5, heap.size());

		heap.replaceKey(e6, 0);
		assertEquals(-6, (int) heap.min().getKey());
		assertEquals("seven", heap.min().getValue());
		assertEquals(0, e6.getKey().intValue());
		assertEquals(5, heap.size());

		heap.replaceKey(e5, -10);
		assertEquals(-10, (int) heap.min().getKey());
		assertEquals("five", heap.min().getValue());
		assertEquals(5, heap.size());

		heap.replaceKey(e4, -11);
		assertEquals(-11, (int) heap.min().getKey());
		assertEquals("four", heap.min().getValue());
		assertEquals(5, heap.size());

		heap.remove(e4);
		assertEquals(-10, (int) heap.min().getKey());
		assertEquals("five", heap.min().getValue());
		assertEquals(4, heap.size());
	}

	/**
	 * Tests replaceValue()
	 */
	@SuppressWarnings("unused")
	@Test
	public void testReplaceValue() {
		assertTrue(heap.isEmpty());
		assertEquals(0, heap.size());

		Entry<Integer, String> e8 = heap.insert(8, "eight");
		Entry<Integer, String> e7 = heap.insert(7, "seven");
		Entry<Integer, String> e6 = heap.insert(6, "six");
		Entry<Integer, String> e5 = heap.insert(5, "five");
		Entry<Integer, String> e4 = heap.insert(4, "four");
		Entry<Integer, String> e3 = heap.insert(3, "three");
		Entry<Integer, String> e2 = heap.insert(2, "two");
		Entry<Integer, String> e1 = heap.insert(1, "one");
		Entry<Integer, String> e0 = heap.insert(0, "zero");
		assertEquals(9, heap.size());

		heap.replaceValue(e8, "EIGHT");
		assertEquals(0, (int) heap.min().getKey());
		assertEquals("zero", heap.min().getValue());
		assertEquals(9, heap.size());
		assertEquals("EIGHT", e8.getValue());

		heap.replaceValue(e0, "newmin");
		assertEquals(0, (int) heap.min().getKey());
		assertEquals("newmin", heap.min().getValue());
		assertEquals(9, heap.size());

		heap.replaceValue(e7, "sEvEn");
		assertEquals(0, (int) heap.min().getKey());
		assertEquals("newmin", heap.min().getValue());
		assertEquals(9, heap.size());
		assertEquals("sEvEn", e7.getValue());

		heap.replaceValue(e6, "z");
		assertEquals(0, (int) heap.min().getKey());
		assertEquals("newmin", heap.min().getValue());
		assertEquals(9, heap.size());
		assertEquals("z", e6.getValue());

		heap.remove(e5);
		try {
			heap.replaceValue(e5, "sEvEn");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid Adaptable PQ Entry.");
		}

		assertEquals(0, (int) heap.min().getKey());
		assertEquals("newmin", heap.min().getValue());
		assertEquals(8, heap.size());

	}

	/**
	 * Tests remove()
	 */
	@Test
	public void testRemove() {
		assertTrue(heap.isEmpty());
		assertEquals(0, heap.size());

		Entry<Integer, String> e8 = heap.insert(8, "eight");
		Entry<Integer, String> e7 = heap.insert(7, "seven");
		Entry<Integer, String> e6 = heap.insert(6, "six");
		Entry<Integer, String> e5 = heap.insert(5, "five");
		Entry<Integer, String> e4 = heap.insert(4, "four");
		Entry<Integer, String> e3 = heap.insert(3, "three");
		Entry<Integer, String> e2 = heap.insert(2, "two");
		Entry<Integer, String> e1 = heap.insert(1, "one");
		Entry<Integer, String> e0 = heap.insert(0, "zero");
		assertEquals(9, heap.size());

		heap.remove(e0);
		assertEquals(1, (int) heap.min().getKey());
		assertEquals("one", heap.min().getValue());
		assertEquals(8, heap.size());

		heap.remove(e7);
		assertEquals(1, (int) heap.min().getKey());
		assertEquals("one", heap.min().getValue());
		assertEquals(7, heap.size());

		heap.remove(e1);
		assertEquals(2, (int) heap.min().getKey());
		assertEquals("two", heap.min().getValue());
		assertEquals(6, heap.size());

		heap.remove(e6);
		assertEquals(2, (int) heap.min().getKey());
		assertEquals("two", heap.min().getValue());
		assertEquals(5, heap.size());

		heap.remove(e5);
		assertEquals(2, (int) heap.min().getKey());
		assertEquals("two", heap.min().getValue());
		assertEquals(4, heap.size());

		try {
			heap.remove(e6);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid Adaptable PQ Entry.");
		}

		assertEquals(2, (int) heap.min().getKey());
		assertEquals("two", heap.min().getValue());
		assertEquals(4, heap.size());

		heap.remove(e4);
		assertEquals(2, (int) heap.min().getKey());
		assertEquals("two", heap.min().getValue());
		assertEquals(3, heap.size());

		heap.remove(e2);
		assertEquals(3, (int) heap.min().getKey());
		assertEquals("three", heap.min().getValue());
		assertEquals(2, heap.size());

		heap.remove(e3);
		assertEquals(8, (int) heap.min().getKey());
		assertEquals("eight", heap.min().getValue());
		assertEquals(1, heap.size());

		heap.remove(e8);
		assertEquals(0, heap.size());
		assertNull(heap.min());
	}

	/**
	 * Tests heap methods with student objects
	 */
	@Test
	public void testStudentHeap() {
		AdaptablePriorityQueue<Student, String> sHeap = new HeapAdaptablePriorityQueue<Student, String>(
				new StudentIDComparator());
		Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
		Student s2 = new Student("J", "S", 2, 1, 2, "js2");
		Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
		Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
		Student s5 = new Student("L", "B", 5, 1, 5, "lb5");

		assertTrue(sHeap.isEmpty());
		assertEquals(0, sHeap.size());

		sHeap.insert(s1, "one");
		assertEquals(sHeap.min().getKey(), s1);
		sHeap.insert(s2, "two");
		sHeap.insert(s3, "three");
		sHeap.insert(s4, "four");
		sHeap.insert(s5, "five");

		assertEquals(sHeap.deleteMin().getKey(), s1);
		assertEquals(sHeap.min().getKey(), s2);

		sHeap.replaceKey(sHeap.min(), s1);
		assertEquals(sHeap.min().getKey(), s1);

		sHeap.replaceValue(sHeap.min(), "newvalue");
		assertEquals(sHeap.min().getValue(), "newvalue");
	}

}
