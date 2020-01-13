package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Student class
 * 
 * @author Gianna Mastrandrea
 *
 */
public class StudentTest {

	private Student sOne;
	private Student sTwo;
	private Student sThree;
	private Student sFour;
	private Student sFive;

	/**
	 * Constructs student objects
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
	}

	/**
	 * Tests the setFirst() method
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
	}

	/**
	 * Tests the setLast() method
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
	}

	/**
	 * Tests the setId() method
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}

	/**
	 * Tests the setGpa() method
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}

	/**
	 * Tests the setUnityID() method
	 */
	@Test
	public void testSetUnityID() {
		sOne.setUnityID("oneUnity");
		assertEquals("oneUnity", sOne.getUnityID());
	}

	/**
	 * Tests the getCreditHours() method
	 */
	@Test
	public void testGetCreditHours() {
		assertEquals(sOne.getCreditHours(), 1);
	}

	/**
	 * Tests the setCreditHours() method
	 */
	@Test
	public void testSetCreditHours() {
		sOne.setCreditHours(2);
		assertEquals(sOne.getCreditHours(), 2);
	}

	/**
	 * Tests the compareTo() method
	 */
	@Test
	public void testCompareTo() {
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		assertTrue(sOne.compareTo(sOne) == 0);
		assertTrue(sTwo.compareTo(sTwo) == 0);
		sFour.setFirst("OneFirst");
		assertFalse(sFour.compareTo(sOne) == 0);
		sFour.setLast("OneLast");
		assertTrue(sOne.compareTo(sFour) < 0);
		assertTrue(sFour.compareTo(sOne) > 0);
	}

	/**
	 * Tests the toString() method
	 */
	@Test
	public void testToString() {
		assertEquals(sOne.toString(),
				"Student [first=OneFirst, last=OneLast, id=1, creditHours=1, gpa=1.0, unityID=oneUnityID]");
	}

	/**
	 * Tests the equals() method
	 */
	@Test
	public void testEquals() {
		assertTrue(sOne.equals(sOne));
		Student sOneCopy = new Student("OneFirst", "OneLast", 1, 1, 5.0, "oneUnityID");
		assertTrue(sOneCopy.equals(sOne));
		assertTrue(sOne.equals(sOneCopy));
		assertFalse(sThree.equals(sFive));
		sOne.setFirst(null);
		assertFalse(sOne.equals(sTwo));
		sTwo.setFirst(null);
		assertFalse(sOne.equals(sTwo));
		sOne.setLast(null);
		sTwo.setId(sOne.getId());
		assertFalse(sOne.equals(sTwo));
	}
}
