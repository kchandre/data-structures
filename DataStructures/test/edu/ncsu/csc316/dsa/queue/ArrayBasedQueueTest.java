package edu.ncsu.csc316.dsa.queue;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the ArrayBasedQueue class
 * 
 * @author Gianna Mastrandrea
 *
 */
public class ArrayBasedQueueTest {

	private Queue<String> queue;

	/**
	 * Sets up queue
	 */
	@Before
	public void setUp() {
		queue = new ArrayBasedQueue<String>();
	}

	/**
	 * Tests the enqueue() method
	 */
	@Test
	public void testEnqueue() {
		assertEquals(0, queue.size());
		assertTrue(queue.isEmpty());

		queue.enqueue("one");
		assertEquals(1, queue.size());
		assertFalse(queue.isEmpty());

		queue.enqueue("two");
		queue.enqueue("three");
		queue.enqueue("four");
		queue.enqueue("five");
		queue.enqueue("six");
		queue.enqueue("seven");
		queue.enqueue("eight");
		queue.enqueue("nine");
		queue.enqueue("ten");
		assertEquals(10, queue.size());
		assertEquals("one", queue.front());

		queue.enqueue("eleven");
		assertEquals(11, queue.size());
		assertEquals("one", queue.front());
	}

	/**
	 * Tests the dequeue() method
	 */
	@Test
	public void testDequeue() {
		assertEquals(0, queue.size());
		queue.enqueue("one");
		queue.enqueue("two");
		queue.enqueue("three");
		queue.enqueue("four");
		queue.enqueue("five");
		queue.enqueue("six");
		assertEquals(6, queue.size());

		assertEquals("one", queue.dequeue());
		assertEquals(5, queue.size());
		assertEquals("two", queue.front());

		assertEquals("two", queue.dequeue());
		assertEquals("three", queue.front());

		assertEquals("three", queue.dequeue());
		assertEquals("four", queue.dequeue());
		assertEquals("five", queue.dequeue());
		assertEquals("six", queue.dequeue());

		try {
			queue.dequeue();
		} catch (Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}

		assertEquals(0, queue.size());
		assertTrue(queue.isEmpty());

	}

	/**
	 * Tests the front() method
	 */
	@Test
	public void testFront() {
		queue.enqueue("one");
		assertEquals("one", queue.front());

		queue.enqueue("two");
		assertEquals("one", queue.front());

		queue.enqueue("three");
		queue.dequeue();
		assertEquals("two", queue.front());
		queue.dequeue();
		queue.dequeue();

		try {
			queue.front();
		} catch (Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}

	}

}
