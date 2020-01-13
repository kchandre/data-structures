package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
//import edu.ncsu.csc316.dsa.test_util.TS_TestUtils;

/**
 * Tests the CountingSorter class
 * 
 * @author Gianna Mastrandrea
 *
 */
public class CountingSorterTest {

	private Student sOne;
	private Student sTwo;
	private Student sThree;
	private Student sFour;
	private Student sFive;

	private CountingSorter<Student> sorter;

	/**
	 * Creates student objects
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		sorter = new CountingSorter<Student>();
	}

	/**
	 * Tests the sort() method
	 */
	@Test
	public void testSortStudent() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
	}

	/**
	 * Tests the sort() method with input in descending order
	 */
	@Test
	public void testSortStudentDescending() {
		Student[] original = { sFive, sFour, sThree, sTwo, sOne };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
	}

	/**
	 * Tests the sort() method with input in ascending order
	 */
	@Test
	public void testSortStudentAscending() {
		Student[] original = { sOne, sTwo, sThree, sFour, sFive };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
	}

}
