package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests the MergeSorter class
 * 
 * @author Gianna Mastrandrea
 *
 */
public class MergeSorterTest {

	private Student sOne = new Student("AFirst", "ALast", 1, 1, 1.0, "oneUnityID");
	private Student sTwo = new Student("BFirst", "ALast", 2, 2, 2.0, "twoUnityID");
	private Student sThree = new Student("ThreeFirst", "CLast", 3, 3, 3.0, "threeUnityID");
	private Student sFour = new Student("FourFirst", "DLast", 4, 4, 4.0, "fourUnityID");
	private Student sFive = new Student("FiveFirst", "ELast", 5, 5, 5.0, "fiveUnityID");

	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };

	private Student[] studentAscending = { sOne, sTwo, sThree, sFour, sFive };
	private Student[] studentDescending = { sFive, sFour, sThree, sTwo, sOne };
	private Student[] studentRandom = { sFour, sOne, sFive, sThree, sTwo };

	private MergeSorter<Integer> integerSorter;
	private MergeSorter<Student> studentSorter;

	/**
	 * Creates new insertion sorter objects
	 */
	@Before
	public void setUp() {
		integerSorter = new MergeSorter<Integer>();
		studentSorter = new MergeSorter<Student>();
	}

	/**
	 * Tests the sort() method with integers
	 */
	@Test
	public void testSortIntegers() {
		integerSorter.sort(dataAscending);
		assertEquals(0, dataAscending[0].compareTo(1));
		assertEquals(0, dataAscending[1].compareTo(2));
		assertEquals(0, dataAscending[2].compareTo(3));
		assertEquals(0, dataAscending[3].compareTo(4));
		assertEquals(0, dataAscending[4].compareTo(5));

		integerSorter.sort(dataDescending);
		assertEquals(0, dataDescending[0].compareTo(1));
		assertEquals(0, dataDescending[1].compareTo(2));
		assertEquals(0, dataDescending[2].compareTo(3));
		assertEquals(0, dataDescending[3].compareTo(4));
		assertEquals(0, dataDescending[4].compareTo(5));

		integerSorter.sort(dataRandom);
		assertEquals(0, dataRandom[0].compareTo(1));
		assertEquals(0, dataRandom[1].compareTo(2));
		assertEquals(0, dataRandom[2].compareTo(3));
		assertEquals(0, dataRandom[3].compareTo(4));
		assertEquals(0, dataRandom[4].compareTo(5));
	}

	/**
	 * Tests the sort() with student objects
	 */
	@Test
	public void testSortStudent() {

		studentSorter.sort(studentAscending);
		assertEquals(0, studentAscending[0].compareTo(sOne));
		assertEquals(0, studentAscending[1].compareTo(sTwo));
		assertEquals(0, studentAscending[2].compareTo(sThree));
		assertEquals(0, studentAscending[3].compareTo(sFour));
		assertEquals(0, studentAscending[4].compareTo(sFive));

		studentSorter.sort(studentDescending);
		assertEquals(0, studentDescending[0].compareTo(sOne));
		assertEquals(0, studentDescending[1].compareTo(sTwo));
		assertEquals(0, studentDescending[2].compareTo(sThree));
		assertEquals(0, studentDescending[3].compareTo(sFour));
		assertEquals(0, studentDescending[4].compareTo(sFive));

		studentSorter.sort(studentRandom);
		assertEquals(0, studentRandom[0].compareTo(sOne));
		assertEquals(0, studentRandom[1].compareTo(sTwo));
		assertEquals(0, studentRandom[2].compareTo(sThree));
		assertEquals(0, studentRandom[3].compareTo(sFour));
		assertEquals(0, studentRandom[4].compareTo(sFive));

	}

}
