package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests the QuickSorter class
 * 
 * @author Gianna Mastrandrea
 *
 */
public class QuickSorterTest {

	private Student sOne = new Student("AFirst", "ALast", 1, 1, 1.0, "oneUnityID");
	private Student sTwo = new Student("BFirst", "ALast", 2, 2, 2.0, "twoUnityID");
	private Student sThree = new Student("ThreeFirst", "CLast", 3, 3, 3.0, "threeUnityID");
	private Student sFour = new Student("FourFirst", "DLast", 4, 4, 4.0, "fourUnityID");
	private Student sFive = new Student("FiveFirst", "ELast", 5, 5, 5.0, "fiveUnityID");

	private Student[] studentAscending = { sOne, sTwo, sThree, sFour, sFive };
	private Student[] studentDescending = { sFive, sFour, sThree, sTwo, sOne };
	private Student[] studentRandom = { sFour, sOne, sFive, sThree, sTwo };

	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };

	private QuickSorter<Integer> firstPivotSort;
	private QuickSorter<Integer> lastPivotSort;
	private QuickSorter<Integer> middlePivotSort;
	private QuickSorter<Integer> randomPivotSort;

	private QuickSorter<Student> firstStudentPivotSort;
	private QuickSorter<Student> lastStudentPivotSort;
	private QuickSorter<Student> middleStudentPivotSort;
	private QuickSorter<Student> randomStudentPivotSort;

	/**
	 * Creates new insertion sorter objects
	 */
	@Before
	public void setUp() {
		firstPivotSort = new QuickSorter<Integer>(QuickSorter.FIRST_ELEMENT_SELECTOR);
		lastPivotSort = new QuickSorter<Integer>(QuickSorter.LAST_ELEMENT_SELECTOR);
		middlePivotSort = new QuickSorter<Integer>(QuickSorter.MIDDLE_ELEMENT_SELECTOR);
		randomPivotSort = new QuickSorter<Integer>();

		firstStudentPivotSort = new QuickSorter<Student>(QuickSorter.FIRST_ELEMENT_SELECTOR);
		lastStudentPivotSort = new QuickSorter<Student>(QuickSorter.LAST_ELEMENT_SELECTOR);
		middleStudentPivotSort = new QuickSorter<Student>(QuickSorter.MIDDLE_ELEMENT_SELECTOR);
		randomStudentPivotSort = new QuickSorter<Student>();

	}

	/**
	 * Tests the sort() method with first element as pivot
	 */
	@Test
	public void testSortFirstPivot() {
		firstPivotSort.sort(dataAscending);
		assertEquals(0, dataAscending[0].compareTo(1));
		assertEquals(0, dataAscending[1].compareTo(2));
		assertEquals(0, dataAscending[2].compareTo(3));
		assertEquals(0, dataAscending[3].compareTo(4));
		assertEquals(0, dataAscending[4].compareTo(5));

		firstPivotSort.sort(dataDescending);
		assertEquals(0, dataDescending[0].compareTo(1));
		assertEquals(0, dataDescending[1].compareTo(2));
		assertEquals(0, dataDescending[2].compareTo(3));
		assertEquals(0, dataDescending[3].compareTo(4));
		assertEquals(0, dataDescending[4].compareTo(5));

		firstPivotSort.sort(dataRandom);
		assertEquals(0, dataRandom[0].compareTo(1));
		assertEquals(0, dataRandom[1].compareTo(2));
		assertEquals(0, dataRandom[2].compareTo(3));
		assertEquals(0, dataRandom[3].compareTo(4));
		assertEquals(0, dataRandom[4].compareTo(5));
	}

	/**
	 * Tests the sort() method with last element as pivot
	 */
	@Test
	public void testSortLastPivot() {
		lastPivotSort.sort(dataAscending);
		assertEquals(0, dataAscending[0].compareTo(1));
		assertEquals(0, dataAscending[1].compareTo(2));
		assertEquals(0, dataAscending[2].compareTo(3));
		assertEquals(0, dataAscending[3].compareTo(4));
		assertEquals(0, dataAscending[4].compareTo(5));

		lastPivotSort.sort(dataDescending);
		assertEquals(0, dataDescending[0].compareTo(1));
		assertEquals(0, dataDescending[1].compareTo(2));
		assertEquals(0, dataDescending[2].compareTo(3));
		assertEquals(0, dataDescending[3].compareTo(4));
		assertEquals(0, dataDescending[4].compareTo(5));

		lastPivotSort.sort(dataRandom);
		assertEquals(0, dataRandom[0].compareTo(1));
		assertEquals(0, dataRandom[1].compareTo(2));
		assertEquals(0, dataRandom[2].compareTo(3));
		assertEquals(0, dataRandom[3].compareTo(4));
		assertEquals(0, dataRandom[4].compareTo(5));
	}

	/**
	 * Tests the sort() method with middle element as pivot
	 */
	@Test
	public void testSortMiddlePivot() {
		middlePivotSort.sort(dataAscending);
		assertEquals(0, dataAscending[0].compareTo(1));
		assertEquals(0, dataAscending[1].compareTo(2));
		assertEquals(0, dataAscending[2].compareTo(3));
		assertEquals(0, dataAscending[3].compareTo(4));
		assertEquals(0, dataAscending[4].compareTo(5));

		middlePivotSort.sort(dataDescending);
		assertEquals(0, dataDescending[0].compareTo(1));
		assertEquals(0, dataDescending[1].compareTo(2));
		assertEquals(0, dataDescending[2].compareTo(3));
		assertEquals(0, dataDescending[3].compareTo(4));
		assertEquals(0, dataDescending[4].compareTo(5));

		middlePivotSort.sort(dataRandom);
		assertEquals(0, dataRandom[0].compareTo(1));
		assertEquals(0, dataRandom[1].compareTo(2));
		assertEquals(0, dataRandom[2].compareTo(3));
		assertEquals(0, dataRandom[3].compareTo(4));
		assertEquals(0, dataRandom[4].compareTo(5));
	}

	/**
	 * Tests the sort() method with random element as pivot
	 */
	@Test
	public void testSortRandomPivot() {
		randomPivotSort.sort(dataAscending);
		assertEquals(0, dataAscending[0].compareTo(1));
		assertEquals(0, dataAscending[1].compareTo(2));
		assertEquals(0, dataAscending[2].compareTo(3));
		assertEquals(0, dataAscending[3].compareTo(4));
		assertEquals(0, dataAscending[4].compareTo(5));

		randomPivotSort.sort(dataDescending);
		assertEquals(0, dataDescending[0].compareTo(1));
		assertEquals(0, dataDescending[1].compareTo(2));
		assertEquals(0, dataDescending[2].compareTo(3));
		assertEquals(0, dataDescending[3].compareTo(4));
		assertEquals(0, dataDescending[4].compareTo(5));

		randomPivotSort.sort(dataRandom);
		assertEquals(0, dataRandom[0].compareTo(1));
		assertEquals(0, dataRandom[1].compareTo(2));
		assertEquals(0, dataRandom[2].compareTo(3));
		assertEquals(0, dataRandom[3].compareTo(4));
		assertEquals(0, dataRandom[4].compareTo(5));
	}

	/**
	 * Tests the sort() method with first element as pivot
	 */
	@Test
	public void testStudentFirstPivot() {
		firstStudentPivotSort.sort(studentAscending);
		assertEquals(0, studentAscending[0].compareTo(sOne));
		assertEquals(0, studentAscending[1].compareTo(sTwo));
		assertEquals(0, studentAscending[2].compareTo(sThree));
		assertEquals(0, studentAscending[3].compareTo(sFour));
		assertEquals(0, studentAscending[4].compareTo(sFive));

		firstStudentPivotSort.sort(studentDescending);
		assertEquals(0, studentDescending[0].compareTo(sOne));
		assertEquals(0, studentDescending[1].compareTo(sTwo));
		assertEquals(0, studentDescending[2].compareTo(sThree));
		assertEquals(0, studentDescending[3].compareTo(sFour));
		assertEquals(0, studentDescending[4].compareTo(sFive));

		firstStudentPivotSort.sort(studentRandom);
		assertEquals(0, studentRandom[0].compareTo(sOne));
		assertEquals(0, studentRandom[1].compareTo(sTwo));
		assertEquals(0, studentRandom[2].compareTo(sThree));
		assertEquals(0, studentRandom[3].compareTo(sFour));
		assertEquals(0, studentRandom[4].compareTo(sFive));
	}

	/**
	 * Tests the sort() method with last element as pivot
	 */
	@Test
	public void testStudentLastPivot() {
		lastStudentPivotSort.sort(studentAscending);
		assertEquals(0, studentAscending[0].compareTo(sOne));
		assertEquals(0, studentAscending[1].compareTo(sTwo));
		assertEquals(0, studentAscending[2].compareTo(sThree));
		assertEquals(0, studentAscending[3].compareTo(sFour));
		assertEquals(0, studentAscending[4].compareTo(sFive));

		lastStudentPivotSort.sort(studentDescending);
		assertEquals(0, studentDescending[0].compareTo(sOne));
		assertEquals(0, studentDescending[1].compareTo(sTwo));
		assertEquals(0, studentDescending[2].compareTo(sThree));
		assertEquals(0, studentDescending[3].compareTo(sFour));
		assertEquals(0, studentDescending[4].compareTo(sFive));

		lastStudentPivotSort.sort(studentRandom);
		assertEquals(0, studentRandom[0].compareTo(sOne));
		assertEquals(0, studentRandom[1].compareTo(sTwo));
		assertEquals(0, studentRandom[2].compareTo(sThree));
		assertEquals(0, studentRandom[3].compareTo(sFour));
		assertEquals(0, studentRandom[4].compareTo(sFive));
	}

	/**
	 * Tests the sort() method with middle element as pivot
	 */
	@Test
	public void testStudentMiddlePivot() {
		middleStudentPivotSort.sort(studentAscending);
		assertEquals(0, studentAscending[0].compareTo(sOne));
		assertEquals(0, studentAscending[1].compareTo(sTwo));
		assertEquals(0, studentAscending[2].compareTo(sThree));
		assertEquals(0, studentAscending[3].compareTo(sFour));
		assertEquals(0, studentAscending[4].compareTo(sFive));

		middleStudentPivotSort.sort(studentDescending);
		assertEquals(0, studentDescending[0].compareTo(sOne));
		assertEquals(0, studentDescending[1].compareTo(sTwo));
		assertEquals(0, studentDescending[2].compareTo(sThree));
		assertEquals(0, studentDescending[3].compareTo(sFour));
		assertEquals(0, studentDescending[4].compareTo(sFive));

		middleStudentPivotSort.sort(studentRandom);
		assertEquals(0, studentRandom[0].compareTo(sOne));
		assertEquals(0, studentRandom[1].compareTo(sTwo));
		assertEquals(0, studentRandom[2].compareTo(sThree));
		assertEquals(0, studentRandom[3].compareTo(sFour));
		assertEquals(0, studentRandom[4].compareTo(sFive));
	}

	/**
	 * Tests the sort() method with random element as pivot
	 */
	@Test
	public void testStudentRandomPivot() {
		randomStudentPivotSort.sort(studentAscending);
		assertEquals(0, studentAscending[0].compareTo(sOne));
		assertEquals(0, studentAscending[1].compareTo(sTwo));
		assertEquals(0, studentAscending[2].compareTo(sThree));
		assertEquals(0, studentAscending[3].compareTo(sFour));
		assertEquals(0, studentAscending[4].compareTo(sFive));

		randomStudentPivotSort.sort(studentDescending);
		assertEquals(0, studentDescending[0].compareTo(sOne));
		assertEquals(0, studentDescending[1].compareTo(sTwo));
		assertEquals(0, studentDescending[2].compareTo(sThree));
		assertEquals(0, studentDescending[3].compareTo(sFour));
		assertEquals(0, studentDescending[4].compareTo(sFive));

		randomStudentPivotSort.sort(studentRandom);
		assertEquals(0, studentRandom[0].compareTo(sOne));
		assertEquals(0, studentRandom[1].compareTo(sTwo));
		assertEquals(0, studentRandom[2].compareTo(sThree));
		assertEquals(0, studentRandom[3].compareTo(sFour));
		assertEquals(0, studentRandom[4].compareTo(sFive));
	}

}
