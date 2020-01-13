package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Tests the LinkedBinaryTree class
 * 
 * @author Gianna Mastrandrea
 *
 */
public class LinkedBinaryTreeTest {

	private LinkedBinaryTree<String> tree;
	private Position<String> one;
	private Position<String> three;
	private Position<String> four;
	private Position<String> six;
	private Position<String> seven;
	private Position<String> eight;
	private Position<String> ten;
	private Position<String> thirteen;
	private Position<String> fourteen;

	private Position<String> two;
	private Position<String> nine;

	/**
	 * Helper class to create an invalid position to help test validate(p)
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
	 * Sets up binary tree for testing
	 */
	@Before
	public void setUp() {
		tree = new LinkedBinaryTree<String>();
	}

	/**
	 * Sample tree to help with testing
	 *
	 * Eight -> Three -> One -> Six -> Four -> Seven -> Ten -> Fourteen -> Thirteen
	 * 
	 * Or, visually: eight / \ three ten / \ \ one six fourteen / \ / four seven
	 * thirteen
	 */
	private void createTree() {
		eight = tree.addRoot("eight");
		three = tree.addLeft(eight, "three");
		ten = tree.addRight(eight, "ten");
		one = tree.addLeft(three, "one");
		six = tree.addRight(three, "six");
		four = tree.addLeft(six, "four");
		seven = tree.addRight(six, "seven");
		fourteen = tree.addRight(ten, "fourteen");
		thirteen = tree.addLeft(fourteen, "thirteen");
	}

	/**
	 * Tests set() method
	 */
	@Test
	public void testSet() {
		createTree();
		assertEquals("eight", tree.set(eight, "EIGHT"));
		assertEquals(tree.root().getElement(), "EIGHT");

		try {
			tree.set(new InvalidPosition<String>(), "invalid");
			fail();
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		new InvalidPosition<String>().getElement();

		assertEquals(tree.set(three, "THREE"), "three");
		assertEquals(tree.children(tree.root()).iterator().next(), three);
		assertEquals(tree.children(tree.root()).iterator().next().getElement(), "THREE");

		assertEquals(tree.set(fourteen, "FOURTEEN"), "fourteen");
		assertEquals(tree.children(ten).iterator().next(), fourteen);
		assertEquals(tree.children(ten).iterator().next().getElement(), "FOURTEEN");

	}

	/**
	 * Tests size() method
	 */
	@Test
	public void testSize() {
		assertTrue(tree.isEmpty());
		assertEquals(tree.size(), 0);

		createTree();
		assertEquals(tree.size(), 9);

		assertEquals(tree.remove(fourteen), "fourteen");
		assertEquals(tree.size(), 8);
		assertEquals(tree.remove(one), "one");
		assertEquals(tree.size(), 7);
		assertEquals(tree.remove(four), "four");
		assertEquals(tree.size(), 6);
		assertEquals(tree.remove(six), "six");
		assertEquals(tree.size(), 5);
		assertEquals(tree.remove(three), "three");
		assertEquals(tree.size(), 4);
		assertEquals(tree.remove(ten), "ten");
		assertEquals(tree.size(), 3);
		assertEquals(tree.remove(thirteen), "thirteen");
		assertEquals(tree.size(), 2);
		assertEquals(tree.remove(seven), "seven");
		assertEquals(tree.size(), 1);
	}

	/**
	 * Tests numChildren() method
	 */
	@Test
	public void testNumChildren() {
		createTree();
		assertEquals(tree.numChildren(eight), 2);
		assertEquals(tree.numChildren(three), 2);
		assertEquals(tree.numChildren(one), 0);
		assertEquals(tree.numChildren(six), 2);
		assertEquals(tree.numChildren(four), 0);
		assertEquals(tree.numChildren(seven), 0);
		assertEquals(tree.numChildren(ten), 1);
		assertEquals(tree.numChildren(fourteen), 1);
		assertEquals(tree.numChildren(thirteen), 0);
	}

	/**
	 * Tests parent() method
	 */
	@Test
	public void testParent() {
		createTree();
		assertEquals(tree.parent(eight), null);
		assertEquals(tree.parent(three), eight);
		assertEquals(tree.parent(ten), eight);
		assertEquals(tree.parent(six), three);
		assertEquals(tree.parent(one), three);
		assertEquals(tree.parent(four), six);
		assertEquals(tree.parent(seven), six);
		assertEquals(tree.parent(fourteen), ten);
		assertEquals(tree.parent(thirteen), fourteen);
	}

	/**
	 * Tests iterator() method
	 */
	@Test
	public void testIterator() {
		createTree();
		Iterator<String> it = tree.iterator();
		assertEquals("one", it.next());
		assertEquals("three", it.next());
		assertEquals("four", it.next());
		assertEquals("six", it.next());
		assertTrue(it.hasNext());

		try {
			it.remove();
		} catch (Exception e) {
			assertTrue(e instanceof UnsupportedOperationException);
		}
	}

	/**
	 * Tests sibling() method
	 */
	@Test
	public void testSibling() {
		createTree();
		assertEquals(tree.sibling(eight), null);
		assertEquals(tree.sibling(three), ten);
		assertEquals(tree.sibling(ten), three);
		assertEquals(tree.sibling(one), six);
		assertEquals(tree.sibling(six), one);
		assertEquals(tree.sibling(four), seven);
		assertEquals(tree.sibling(seven), four);
		assertEquals(tree.sibling(fourteen), null);
		assertEquals(tree.sibling(thirteen), null);
	}

	/**
	 * Tests isInternal() method
	 */
	@Test
	public void testIsInternal() {
		createTree();
		assertTrue(tree.isInternal(eight));
		assertTrue(tree.isInternal(three));
		assertTrue(tree.isInternal(ten));
		assertFalse(tree.isInternal(one));
		assertTrue(tree.isInternal(six));
		assertTrue(tree.isInternal(fourteen));
		assertFalse(tree.isInternal(four));
		assertFalse(tree.isInternal(seven));
		assertFalse(tree.isInternal(thirteen));
	}

	/**
	 * Tests isLeaf() method
	 */
	@Test
	public void isLeaf() {
		createTree();
		assertFalse(tree.isLeaf(eight));
		assertFalse(tree.isLeaf(three));
		assertFalse(tree.isLeaf(ten));
		assertTrue(tree.isLeaf(one));
		assertFalse(tree.isLeaf(six));
		assertFalse(tree.isLeaf(fourteen));
		assertTrue(tree.isLeaf(four));
		assertTrue(tree.isLeaf(seven));
		assertTrue(tree.isLeaf(thirteen));
	}

	/**
	 * Tests isRoot() method
	 */
	@Test
	public void isRoot() {
		createTree();
		assertTrue(tree.isRoot(eight));
		assertFalse(tree.isRoot(three));
		assertFalse(tree.isRoot(ten));
		assertFalse(tree.isRoot(one));
		assertFalse(tree.isRoot(six));
		assertFalse(tree.isRoot(fourteen));
		assertFalse(tree.isRoot(four));
		assertFalse(tree.isRoot(seven));
		assertFalse(tree.isRoot(thirteen));
	}

	/**
	 * Tests preOrder() method
	 */
	@Test
	public void testPreOrder() {
		createTree();
		Iterator<Position<String>> pre = tree.preOrder().iterator();
		assertEquals(eight, pre.next());
		assertEquals(three, pre.next());
		assertEquals(one, pre.next());
		assertEquals(six, pre.next());
		assertEquals(four, pre.next());
		assertEquals(seven, pre.next());
		assertEquals(ten, pre.next());
		assertEquals(fourteen, pre.next());
		assertEquals(thirteen, pre.next());
	}

	/**
	 * Tests postOrder() method
	 */
	@Test
	public void testPostOrder() {
		createTree();
		Iterator<Position<String>> post = tree.postOrder().iterator();
		assertEquals(one, post.next());
		assertEquals(four, post.next());
		assertEquals(seven, post.next());
		assertEquals(six, post.next());
		assertEquals(three, post.next());
		assertEquals(thirteen, post.next());
		assertEquals(fourteen, post.next());
		assertEquals(ten, post.next());
		assertEquals(eight, post.next());
	}

	/**
	 * Tests inOrder() method
	 */
	@Test
	public void testInOrder() {
		createTree();
		Iterator<Position<String>> level = tree.inOrder().iterator();
		assertEquals(one, level.next());
		assertEquals(three, level.next());
		assertEquals(four, level.next());
		assertEquals(six, level.next());
		assertEquals(seven, level.next());
	}

	/**
	 * Tests empty tree
	 */
	@Test
	public void testEmptyTree() {
		Tree<String> bTree = new LinkedBinaryTree<String>();
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

	/**
	 * Tests levelOrder() method
	 */
	@Test
	public void testLevelOrder() {
		createTree();
		Iterator<Position<String>> level = tree.levelOrder().iterator();
		assertEquals(eight, level.next());
		assertEquals(three, level.next());
		assertEquals(ten, level.next());
		assertEquals(one, level.next());
		assertEquals(six, level.next());
		assertEquals(fourteen, level.next());
		assertEquals(four, level.next());
		assertEquals(seven, level.next());
		assertEquals(thirteen, level.next());
	}

	/**
	 * Tests addChildren() method
	 */
	@Test
	public void testAddChildren() {
		assertTrue(tree.isEmpty());
		eight = tree.addRoot("eight");
		assertEquals(1, tree.size());
		assertNull(tree.parent(eight));
		assertEquals("LinkedBinaryTree[\neight\n]", tree.toString());

		three = tree.addLeft(eight, "three");
		assertEquals(2, tree.size());
		assertEquals(tree.parent(three), eight);
		assertEquals("LinkedBinaryTree[\neight\n three\n]", tree.toString());

		ten = tree.addRight(eight, "ten");
		assertEquals(3, tree.size());
		assertEquals(tree.parent(ten), eight);

		six = tree.addRight(three, "six");
		assertEquals(4, tree.size());
		assertEquals(tree.parent(six), three);

		seven = tree.addRight(six, "seven");
		assertEquals(5, tree.size());
		assertEquals(tree.parent(seven), six);
	}

	/**
	 * Tests remove() method
	 */
	@Test
	public void testRemove() {
		createTree();

		assertEquals(tree.remove(fourteen), "fourteen");
		assertEquals(tree.remove(one), "one");

		try {
			tree.remove(eight);
			fail();
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}

		assertEquals(tree.remove(four), "four");
		assertEquals(tree.remove(six), "six");
		assertEquals(tree.remove(three), "three");
		assertEquals(tree.remove(ten), "ten");
		assertEquals(tree.remove(thirteen), "thirteen");
		assertEquals(tree.remove(seven), "seven");
		assertEquals(tree.size(), 1);
	}

	/**
	 * Test to replicate failing Jenkins tests
	 */
	@Test
	public void jenkins() {
		one = tree.addRoot("one");
		two = tree.addLeft(one, "two");
		three = tree.addRight(one, "three");
		six = tree.addLeft(two, "six");
		ten = tree.addRight(two, "ten");
		four = tree.addLeft(three, "four");
		seven = tree.addLeft(ten, "seven");
		eight = tree.addLeft(four, "eight");
		nine = tree.addRight(four, "nine");

		tree.remove(nine);
		tree.remove(eight);
		tree.remove(four);
		tree.remove(three);
		assertEquals(tree.toString(), "LinkedBinaryTree[\none\n two\n  six\n  ten\n   seven\n]");
		tree.remove(one);
		assertEquals(tree.toString(), "LinkedBinaryTree[\ntwo\n six\n ten\n  seven\n]");
	}

}
