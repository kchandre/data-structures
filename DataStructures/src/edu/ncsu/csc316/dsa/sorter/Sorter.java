package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior
 * 
 * @param <E> object
 * @author Dr. King
 * @author Gianna Mastrandrea
 */
public interface Sorter<E> {

	/**
	 * Sorts through an array of integers
	 * 
	 * @param data list elements to sort
	 */
	public void sort(E[] data);

}
