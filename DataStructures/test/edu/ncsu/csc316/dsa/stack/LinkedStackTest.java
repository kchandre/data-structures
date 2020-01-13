package edu.ncsu.csc316.dsa.stack;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the LinkedStack class
 * 
 * @author Gianna Mastrandrea
 *
 */
public class LinkedStackTest {

	private Stack<String> stack;

	/**
	 * Sets up stack
	 */
	@Before
	public void setUp() {
		stack = new LinkedStack<String>();
	}

	/**
	 * Tests the push() method
	 */
	@Test
	public void testPush() {
		assertEquals(0, stack.size());
		assertTrue(stack.isEmpty());

		stack.push("one");
		assertEquals(1, stack.size());
		assertFalse(stack.isEmpty());
		assertEquals(stack.top(), "one");

		stack.push("two");
		assertEquals(2, stack.size());
		assertEquals(stack.top(), "two");

		stack.push("three");
		assertEquals(3, stack.size());
		assertEquals(stack.top(), "three");

	}

	/**
	 * Tests the pop() method
	 */
	@Test
	public void testPop() {
		assertEquals(0, stack.size());
		stack.push("one");
		stack.push("two");
		stack.push("three");
		stack.push("four");
		stack.push("five");
		stack.push("six");
		assertEquals(6, stack.size());

		assertEquals("six", stack.pop());
		assertEquals(5, stack.size());

		assertEquals("five", stack.pop());
		assertEquals(4, stack.size());

		assertEquals("four", stack.pop());
		assertEquals(3, stack.size());

		assertEquals("three", stack.pop());
		assertEquals(2, stack.size());

		assertEquals("two", stack.pop());
		assertEquals(1, stack.size());

		assertEquals("one", stack.pop());
		assertEquals(0, stack.size());
		assertTrue(stack.isEmpty());

		try {
			stack.pop();
		} catch (Exception e) {
			assertTrue(e instanceof EmptyStackException);
		}

	}

	/**
	 * Tests the top() method
	 */
	@Test
	public void testTop() {
		assertEquals(0, stack.size());
		stack.push("one");
		assertEquals(stack.top(), "one");
		stack.push("two");
		assertEquals(stack.top(), "two");
		stack.pop();
		assertEquals(stack.top(), "one");
		stack.pop();
		
		try {
			stack.top();
		} catch (Exception e) {
			assertTrue(e instanceof EmptyStackException);
		}
		
	}

}
