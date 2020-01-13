package edu.ncsu.csc316.dsa.set;

/**
 * Defines necessary methods for all sets to implement
 * 
 * @author Gianna Mastrandrea
 *
 * @param <E> generic object
 */
public interface Set<E> extends Iterable<E> {

	/**
	 * Adds given value to set
	 * 
	 * @param value value to add
	 */
	void add(E value);

	/**
	 * Determines if set contains given value
	 * 
	 * @param value target value
	 * @return true if set contains value
	 */
	boolean contains(E value);

	/**
	 * Removes given value from set
	 * 
	 * @param value value to remove
	 * @return value removed
	 */
	E remove(E value);

	/**
	 * Determines if set is empty
	 * 
	 * @return true if set is empty
	 */
	boolean isEmpty();

	/**
	 * Gives size of set
	 * 
	 * @return size of set
	 */
	int size();

	/**
	 * Adds all values in a given set to current set
	 * 
	 * @param t set of values to add
	 */
	void addAll(Set<E> t);

	/**
	 * Removes all values from set not contained in given set
	 * 
	 * @param t set of values to retain
	 */
	void retainAll(Set<E> t);

	/**
	 * Removes all values from set contained in given set
	 * 
	 * @param t set of values to remove
	 */
	void removeAll(Set<E> t);

}
