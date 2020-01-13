package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Tests the HeapPriorityQueue class
 * 
 * @author Gianna Mastrandrea
 *
 */
public class HeapPriorityQueueTest {

	private PriorityQueue<Integer, String> heap;

	/**
	 * Sets up heap priority queue for testing
	 */
	@Before
	public void setUp() {
		heap = new HeapPriorityQueue<Integer, String>();
	}

	/**
	 * Tests insert()
	 */
	@Test
	public void testInsert() {
		assertTrue(heap.isEmpty());
		assertTrue(heap.size() == 0);

		heap.insert(8, "eight");
		assertEquals(1, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(8, (int) heap.min().getKey());

		heap.insert(1, "one");
		assertEquals(2, heap.size());
		assertEquals(1, (int) heap.min().getKey());

		heap.insert(2, "two");
		assertEquals(3, heap.size());
		assertEquals(1, (int) heap.min().getKey());

		heap.insert(1, "newone");
		assertEquals(4, heap.size());
		assertEquals(1, (int) heap.min().getKey());

	}

	/**
	 * Tests min()
	 */
	@Test
	public void testMin() {
		assertTrue(heap.isEmpty());
		assertTrue(heap.size() == 0);

		assertNull(heap.min());

		heap.insert(2, "two");
		assertEquals(2, (int) heap.min().getKey());
		heap.insert(3, "three");
		assertEquals(2, (int) heap.min().getKey());
		heap.insert(1, "one");
		assertEquals(1, (int) heap.min().getKey());
		heap.insert(1, "newone");
		assertEquals("one", heap.min().getValue());

		assertEquals(heap.deleteMin().getValue(), "one");
		assertEquals("newone", heap.min().getValue());
		assertEquals(heap.deleteMin().getValue(), "newone");
		assertEquals("two", heap.min().getValue());

	}

	/**
	 * Tests deleteMin()
	 */
	@Test
	public void deleteMin() {
		assertTrue(heap.isEmpty());
		assertEquals(0, heap.size());

		assertNull(heap.deleteMin());

		heap.insert(2, "two");
		assertEquals(2, (int) heap.min().getKey());
		heap.insert(3, "three");
		assertEquals(2, (int) heap.min().getKey());
		assertEquals(heap.deleteMin().getValue(), "two");
		assertEquals(3, (int) heap.min().getKey());

		heap.insert(1, "one");
		assertEquals(1, (int) heap.min().getKey());

		assertEquals(heap.deleteMin().getValue(), "one");
		assertEquals(3, (int) heap.min().getKey());
		assertEquals(heap.deleteMin().getValue(), "three");
		assertNull(heap.min());
	}

	/**
	 * Tests heap with student objects
	 */
	@Test
	public void testStudentHeap() {
		PriorityQueue<Student, String> sHeap = new HeapPriorityQueue<Student, String>(new StudentIDComparator());
		Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
		Student s2 = new Student("J", "S", 2, 1, 2, "js2");
		Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
		Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
		Student s5 = new Student("L", "B", 5, 1, 5, "lb5");

		assertTrue(sHeap.isEmpty());
		assertEquals(0, sHeap.size());

		sHeap.insert(s1, "jk1");
		assertEquals(sHeap.min().getKey(), s1);
		sHeap.insert(s3, "sh3");
		assertEquals(sHeap.min().getKey(), s1);
		sHeap.insert(s2, "js2");
		assertEquals(sHeap.min().getKey(), s1);
		sHeap.insert(s5, "lb5");
		assertEquals(sHeap.min().getKey(), s1);
		assertEquals(sHeap.deleteMin().getKey(), s1);
		assertEquals(sHeap.min().getKey(), s2);
		sHeap.insert(s4, "jj4");
		assertEquals(sHeap.min().getKey(), s2);
	}

}
