package edu.ncsu.csc316.dsa.disjoint_set;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.disjoint_set.DisjointSetForest;

/**
 * Tests the UpTreeDisjointSetForest class
 * 
 * @author Gianna Mastrandrea
 *
 */
public class UpTreeDisjointSetForestTest {

	private DisjointSetForest<String> set;

	/**
	 * Sets up disjoint set for testing
	 */
	@Before
	public void setUp() {
		set = new UpTreeDisjointSetForest<>();
	}

	/**
	 * Tests makeSet()
	 */
	@Test
	public void testMakeSet() {
		Position<String> one = set.makeSet("one");
		assertEquals("one", one.getElement());

		Position<String> two = set.makeSet("two");
		assertEquals("two", two.getElement());

		Position<String> three = set.makeSet("three");
		assertEquals("three", three.getElement());

		Position<String> hundred = set.makeSet("hundred");
		assertEquals("hundred", hundred.getElement());

		set.union(one, two);
		assertEquals("two", set.find("one").getElement());

	}

	/**
	 * Tests find()
	 */
	@Test
	public void testUnionFind() {
		Position<String> one = set.makeSet("one");
		Position<String> two = set.makeSet("two");
		Position<String> three = set.makeSet("three");
		Position<String> four = set.makeSet("four");
		Position<String> five = set.makeSet("five");
		Position<String> six = set.makeSet("six");
		Position<String> seven = set.makeSet("seven");
		Position<String> eight = set.makeSet("eight");
		Position<String> nine = set.makeSet("nine");
		Position<String> ten = set.makeSet("ten");

		assertEquals(one, set.find("one"));
		assertEquals(two, set.find("two"));
		assertEquals(three, set.find("three"));
		assertEquals(four, set.find("four"));
		assertEquals(five, set.find("five"));
		assertEquals(six, set.find("six"));
		assertEquals(seven, set.find("seven"));
		assertEquals(eight, set.find("eight"));
		assertEquals(nine, set.find("nine"));
		assertEquals(ten, set.find("ten"));

		set.union(one, two);
		assertEquals("two", set.find("one").getElement());
		set.union(two, three);
		assertEquals("two", set.find("three").getElement());
		set.union(three, ten);
		assertEquals("two", set.find("ten").getElement());
		set.union(nine, eight);
		assertEquals("eight", set.find("eight").getElement());
		assertEquals("eight", set.find("nine").getElement());
		set.union(five, nine);
		assertEquals("eight", set.find("nine").getElement());
		assertEquals("eight", set.find("five").getElement());

	}

}
