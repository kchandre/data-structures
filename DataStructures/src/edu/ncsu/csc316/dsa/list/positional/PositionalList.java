package edu.ncsu.csc316.dsa.list.positional;

import edu.ncsu.csc316.dsa.Position;

/**
 * Creates Positional List objects and access methods
 * 
 * @author Gianna Mastrandrea
 *
 * @param <E> generic object
 */
public interface PositionalList<E> extends Iterable<E> {

	/**
	 * Adds value after given position
	 * 
	 * @param p     position
	 * @param value value to add
	 * @return current position
	 */
	Position<E> addAfter(Position<E> p, E value);

	/**
	 * Adds value before given position
	 * 
	 * @param p     position
	 * @param value value to add
	 * @return current position
	 */
	Position<E> addBefore(Position<E> p, E value);

	/**
	 * Adds value to front of list
	 * 
	 * @param value value to add
	 * @return current position
	 */
	Position<E> addFirst(E value);

	/**
	 * Adds value to back of list
	 * 
	 * @param value value to add
	 * @return current position
	 */
	Position<E> addLast(E value);

	/**
	 * Gets value after given position
	 * 
	 * @param p position
	 * @return position
	 */
	Position<E> after(Position<E> p);

	/**
	 * Gets value before given position
	 * 
	 * @param p position
	 * @return position
	 */
	Position<E> before(Position<E> p);

	/**
	 * Gets first position
	 * 
	 * @return first position
	 */
	Position<E> first();

	/**
	 * Determines if list is empty
	 * 
	 * @return true if list is empty
	 */
	boolean isEmpty();

	/**
	 * Gets last position
	 * 
	 * @return last position
	 */
	Position<E> last();

	/**
	 * Iterates through positions
	 * 
	 * @return position iterator
	 */
	Iterable<Position<E>> positions();

	/**
	 * Removes element at given position
	 * 
	 * @param p position
	 * @return element removed
	 */
	E remove(Position<E> p);

	/**
	 * Sets element at given position
	 * 
	 * @param p     position
	 * @param value value to set
	 * @return previous value
	 */
	E set(Position<E> p, E value);

	/**
	 * Gets size of list
	 * 
	 * @return size of list
	 */
	int size();
}
