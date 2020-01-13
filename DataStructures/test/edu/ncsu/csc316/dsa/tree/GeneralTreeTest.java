package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Tests the GeneralTree class
 * 
 * @author Gianna Mastrandrea
 *
 */
public class GeneralTreeTest {

	private GeneralTree<String> tree;
	private GeneralTree<String> emptyTree;

	private Position<String> one;
	private Position<String> two;
	private Position<String> three;
	private Position<String> four;
	private Position<String> five;
	private Position<String> six;
	private Position<String> seven;
	private Position<String> eight;
	private Position<String> nine;
	private Position<String> ten;

	/**
	 * An invalid Position to help test validate(p)
	 * 
	 * @author Gianna Mastrandrea
	 *
	 * @param <E> generic object
	 */
	private class InvalidPosition<E> implements Position<E> {

		@Override
		public E getElement() {
			return null;
		}

	}

	/**
	 * Sets up trees for testing
	 */
	@Before
	public void setUp() {
		tree = new GeneralTree<String>();
		emptyTree = new GeneralTree<String>();
	}

	/**
	 * Helper method to construct a sample tree
	 *
	 * One -> Two -> Six -> Five -> Ten -> Seven -> Three -> Four -> Eight -> Nine
	 *
	 * Or, visually: one / \ two three / | \ | six five ten four | / \ seven eight
	 * nine
	 */
	private void createTree() {
		one = tree.addRoot("one");
		two = tree.addChild(one, "two");
		three = tree.addChild(one, "three");
		six = tree.addChild(two, "six");
		five = tree.addChild(two, "five");
		ten = tree.addChild(two, "ten");
		seven = tree.addChild(ten, "seven");
		four = tree.addChild(three, "four");
		eight = tree.addChild(four, "eight");
		nine = tree.addChild(four, "nine");
	}

	/**
	 * Tests set() method
	 */
	@Test
	public void testSet() {
		createTree();
		assertEquals("one", tree.set(one, "ONE"));
		assertEquals(tree.root().getElement(), "ONE");

		try {
			tree.set(new InvalidPosition<String>(), "invalid");
			fail();
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		new InvalidPosition<String>().getElement();

		assertEquals(tree.set(two, "TWO"), "two");
		assertEquals(tree.children(tree.root()).iterator().next(), two);
		assertEquals(tree.children(tree.root()).iterator().next().getElement(), "TWO");

		assertEquals(tree.set(seven, "SEVEN"), "seven");
		assertEquals(tree.children(ten).iterator().next(), seven);
		assertEquals(tree.children(ten).iterator().next().getElement(), "SEVEN");

	}

	/**
	 * Tests size() method
	 */
	@Test
	public void testSize() {
		assertTrue(tree.isEmpty());
		createTree();
		assertEquals(10, tree.size());
		assertFalse(tree.isEmpty());

		tree.remove(nine);
		assertEquals(9, tree.size());

		tree.remove(four);
		assertEquals(8, tree.size());

		tree.remove(eight);
		assertEquals(7, tree.size());

		tree.remove(ten);
		assertEquals(6, tree.size());

		tree.remove(seven);
		assertEquals(5, tree.size());

		tree.remove(six);
		assertEquals(4, tree.size());

		tree.remove(two);
		assertEquals(3, tree.size());

		tree.remove(five);
		assertEquals(2, tree.size());

		tree.remove(three);
		assertEquals(1, tree.size());

		tree.remove(one);
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());

	}

	/**
	 * Tests the numChildren() method
	 */
	@Test
	public void testNumChildren() {
		createTree();
		assertEquals(tree.numChildren(one), 2);
		assertEquals(tree.numChildren(two), 3);
		assertEquals(tree.numChildren(three), 1);
		assertEquals(tree.numChildren(six), 0);
		assertEquals(tree.numChildren(five), 0);
		assertEquals(tree.numChildren(ten), 1);
		assertEquals(tree.numChildren(four), 2);
		assertEquals(tree.numChildren(eight), 0);
		assertEquals(tree.numChildren(nine), 0);
	}

	/**
	 * Tests the parent() method
	 */
	@Test
	public void testParent() {
		createTree();
		assertEquals(tree.parent(one), null);
		assertEquals(tree.parent(two), one);
		assertEquals(tree.parent(three), one);
		assertEquals(tree.parent(six), two);
		assertEquals(tree.parent(five), two);
		assertEquals(tree.parent(ten), two);
		assertEquals(tree.parent(seven), ten);
		assertEquals(tree.parent(four), three);
		assertEquals(tree.parent(eight), four);
		assertEquals(tree.parent(nine), four);
	}

	/**
	 * Tests the isInternal() method
	 */
	@Test
	public void testIsInternal() {
		createTree();
		assertTrue(tree.isInternal(one));
		assertTrue(tree.isInternal(two));
		assertTrue(tree.isInternal(three));
		assertFalse(tree.isInternal(six));
		assertFalse(tree.isInternal(five));
		assertTrue(tree.isInternal(ten));
		assertTrue(tree.isInternal(four));
		assertFalse(tree.isInternal(seven));
		assertFalse(tree.isInternal(eight));
		assertFalse(tree.isInternal(nine));
	}

	/**
	 * Tests the isLeaf() method
	 */
	@Test
	public void isLeaf() {
		createTree();
		assertFalse(tree.isLeaf(one));
		assertFalse(tree.isLeaf(two));
		assertFalse(tree.isLeaf(three));
		assertTrue(tree.isLeaf(six));
		assertTrue(tree.isLeaf(five));
		assertFalse(tree.isLeaf(ten));
		assertFalse(tree.isLeaf(four));
		assertTrue(tree.isLeaf(seven));
		assertTrue(tree.isLeaf(eight));
		assertTrue(tree.isLeaf(nine));
	}

	/**
	 * Tests the isRoot() method
	 */
	@Test
	public void isRoot() {
		createTree();
		assertTrue(tree.isRoot(one));
		assertFalse(tree.isRoot(two));
		assertFalse(tree.isRoot(three));
		assertFalse(tree.isRoot(four));
		assertFalse(tree.isRoot(five));
		assertFalse(tree.isRoot(six));
		assertFalse(tree.isRoot(seven));
		assertFalse(tree.isRoot(eight));
		assertFalse(tree.isRoot(nine));
		assertFalse(tree.isRoot(ten));
	}

	/**
	 * Tests the isEmpty() method
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(emptyTree.isEmpty());

		createTree();
		tree.remove(nine);
		tree.remove(four);
		tree.remove(eight);
		tree.remove(ten);
		tree.remove(seven);
		tree.remove(six);
		tree.remove(two);
		tree.remove(five);
		tree.remove(three);
		tree.remove(one);

		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());

	}

	/**
	 * Tests the preOrder() method
	 */
	@Test
	public void testPreOrder() {
		createTree();
		Iterator<Position<String>> pre = tree.preOrder().iterator();
		assertEquals(one, pre.next());
		assertEquals(two, pre.next());
		assertEquals(six, pre.next());
		assertEquals(five, pre.next());
		assertEquals(ten, pre.next());
		assertEquals(seven, pre.next());
		assertEquals(three, pre.next());
		assertEquals(four, pre.next());
		assertEquals(eight, pre.next());
		assertEquals(nine, pre.next());
	}

	/**
	 * Tests the iterator() method
	 */
	@Test
	public void testIterator() {
		createTree();
		Iterator<String> pre = tree.iterator();
		assertEquals("one", pre.next());
		assertEquals("two", pre.next());
		assertEquals("six", pre.next());
		assertEquals("five", pre.next());
		assertEquals("ten", pre.next());
		assertEquals("seven", pre.next());
		assertEquals("three", pre.next());
		assertEquals("four", pre.next());
		assertEquals("eight", pre.next());
		assertEquals("nine", pre.next());
	}

	/**
	 * Tests the postOrder() method
	 */
	@Test
	public void testPostOrder() {
		createTree();
		Iterator<Position<String>> post = tree.postOrder().iterator();
		assertEquals(six, post.next());
		assertEquals(five, post.next());
		assertEquals(seven, post.next());
		assertEquals(ten, post.next());
		assertEquals(two, post.next());
		assertEquals(eight, post.next());
		assertEquals(nine, post.next());
		assertEquals(four, post.next());
		assertEquals(three, post.next());
		assertEquals(one, post.next());
	}

	/**
	 * Tests the levelOrder() method
	 */
	@Test
	public void testLevelOrder() {
		createTree();
		Iterator<Position<String>> level = tree.levelOrder().iterator();
		assertEquals(one, level.next());
		assertEquals(two, level.next());
		assertEquals(three, level.next());
		assertEquals(six, level.next());
		assertEquals(five, level.next());
		assertEquals(ten, level.next());
		assertEquals(four, level.next());
		assertEquals(seven, level.next());
		assertEquals(eight, level.next());
		assertEquals(nine, level.next());
	}

	/**
	 * Tests the addChild() method
	 */
	@Test
	public void testAddChild() {
		assertTrue(tree.isEmpty());
		one = tree.addRoot("one");
		assertEquals(1, tree.size());
		assertNull(tree.parent(one));
		assertEquals("GeneralTree[\none\n]", tree.toString());

		two = tree.addChild(one, "two");
		assertEquals(2, tree.size());
		assertEquals(tree.parent(two), one);
		assertEquals("GeneralTree[\none\n two\n]", tree.toString());

		three = tree.addChild(one, "three");
		assertEquals(3, tree.size());
		assertEquals(tree.parent(three), one);

		four = tree.addChild(three, "four");
		assertEquals(4, tree.size());
		assertEquals(tree.parent(four), three);

		eight = tree.addChild(four, "eight");
		assertEquals(5, tree.size());
		assertEquals(tree.parent(eight), four);
	}

	/**
	 * Tests the remove() method
	 */
	@Test
	public void testRemove() {
		createTree();
		assertEquals(10, tree.size());
		assertEquals(2, tree.numChildren(four));
		tree.remove(nine);
		assertEquals("GeneralTree[\none\n two\n  six\n  five\n  ten\n   seven\n three\n  four\n   eight\n]",
				tree.toString());
		assertEquals(9, tree.size());
		assertEquals(1, tree.numChildren(four));

		tree.remove(four);
		assertEquals("GeneralTree[\none\n two\n  six\n  five\n  ten\n   seven\n three\n  eight\n]", tree.toString());
		assertEquals(8, tree.size());
		assertEquals(1, tree.numChildren(three));

		tree.remove(eight);
		assertEquals("GeneralTree[\none\n two\n  six\n  five\n  ten\n   seven\n three\n]", tree.toString());
		assertEquals(7, tree.size());
		assertEquals(0, tree.numChildren(three));

		tree.remove(ten);
		assertEquals("GeneralTree[\none\n two\n  six\n  five\n  seven\n three\n]", tree.toString());
		assertEquals(6, tree.size());
		assertEquals(3, tree.numChildren(two));

		tree.remove(seven);
		assertEquals("GeneralTree[\none\n two\n  six\n  five\n three\n]", tree.toString());
		assertEquals(5, tree.size());
		assertEquals(2, tree.numChildren(two));

		tree.remove(six);
		assertEquals("GeneralTree[\none\n two\n  five\n three\n]", tree.toString());
		assertEquals(4, tree.size());
		assertEquals(1, tree.numChildren(two));

		tree.remove(two);
		assertEquals("GeneralTree[\none\n five\n three\n]", tree.toString());
		assertEquals(3, tree.size());
		assertEquals(2, tree.numChildren(one));

		tree.remove(five);
		assertEquals("GeneralTree[\none\n three\n]", tree.toString());
		assertEquals(2, tree.size());
		assertEquals(1, tree.numChildren(one));

		tree.remove(three);
		assertEquals("GeneralTree[\none\n]", tree.toString());
		assertEquals(1, tree.size());
		assertEquals(0, tree.numChildren(one));

		tree.remove(one);
		assertEquals("GeneralTree[\n]", tree.toString());
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());
	}

	/**
	 * Tests an empty tree
	 */
	@Test
	public void testEmptyTree() {
		Tree<String> bTree = new GeneralTree<String>();
		assertTrue(bTree.isEmpty());

		assertEquals(bTree.root(), null);
		Iterator<Position<String>> it = bTree.levelOrder().iterator();
		assertFalse(it.hasNext());

		try {
			it.remove();
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}

		assertFalse(bTree.levelOrder().iterator().hasNext());
		assertFalse(bTree.preOrder().iterator().hasNext());
		assertFalse(bTree.postOrder().iterator().hasNext());
	}

}
