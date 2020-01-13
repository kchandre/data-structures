package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the StudentIDComparator class
 * 
 * @author Gianna Mastrandrea
 *
 */
public class StudentIDComparatorTest {

	private Student sOne;
	private Student sTwo;
	private Student sThree;
	private Student sFour;
	private Student sFive;

	private StudentIDComparator comparator;

	/**
	 * Sets up students
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		comparator = new StudentIDComparator();
	}

	/**
	 * Tests the compare() method
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sOne, sTwo) < 0);
		assertFalse(comparator.compare(sTwo, sOne) < 0);

		assertTrue(comparator.compare(sOne, sOne) == 0);
		assertTrue(comparator.compare(sFour, sOne) > 0);
		assertFalse(comparator.compare(sTwo, sThree) > 0);
		assertFalse(comparator.compare(sThree, sFive) > 0);
	}

}
