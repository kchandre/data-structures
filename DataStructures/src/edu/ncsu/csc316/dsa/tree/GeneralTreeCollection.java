package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * Defines methods and behaviors of general trees
 * 
 * @author Gianna Mastrandrea
 *
 * @param <E> generic object
 */
public interface GeneralTreeCollection<E> extends Tree<E>, Iterable<E> {

	/**
	 * Adds given value to the root of the tree
	 * 
	 * @param value value to add
	 * @return new position added
	 */
	Position<E> addRoot(E value);

	/**
	 * Adds given value to as a child to given position
	 * 
	 * @param p     position to add child to
	 * @param value value to add
	 * @return new position added
	 */
	Position<E> addChild(Position<E> p, E value);

	/**
	 * Removes element at given position
	 * 
	 * @param p position to remove
	 * @return value stored at position removed
	 */
	E remove(Position<E> p);

	/**
	 * Updates value at given position
	 * 
	 * @param p     position
	 * @param value value to set
	 * @return old value of position
	 */
	E set(Position<E> p, E value);
	
}
