package edu.ncsu.csc316.dsa.list;

/**
 * Establishes core list functions
 * 
 * @author Gianna Mastrandrea
 *
 * @param <E> object
 */
public interface List<E> extends Iterable<E> {

	/**
	 * Adds value to list at given index
	 * 
	 * @param index position to add
	 * @param value value to add
	 */
	void add(int index, E value);

	/**
	 * Adds value to front of list
	 * 
	 * @param value value to add
	 */
	void addFirst(E value);

	/**
	 * Adds value to back of list
	 * 
	 * @param value value to add
	 */
	void addLast(E value);

	/**
	 * Gets first element of list
	 * 
	 * @return first element of list
	 */
	E first();

	/**
	 * Gets element at given position
	 * 
	 * @param index position of element
	 * @return element at given index
	 */
	E get(int index);

	/**
	 * Determines if list is empty
	 * 
	 * @return true if list is empty
	 */
	boolean isEmpty();

	/**
	 * Gets last element of list
	 * 
	 * @return last element of list
	 */
	E last();

	/**
	 * Removes element at given index
	 * 
	 * @param index position of element
	 * @return element removed
	 */
	E remove(int index);

	/**
	 * Removes first element of list
	 * 
	 * @return element removed
	 */
	E removeFirst();

	/**
	 * Removes last element of list
	 * 
	 * @return element removed
	 */
	E removeLast();

	/**
	 * Sets element at given index
	 * 
	 * @param index position of element
	 * @param value value to set
	 * @return previous element at given index
	 */
	E set(int index, E value);

	/**
	 * Gives size of list
	 * 
	 * @return size of list
	 */
	int size();
}
