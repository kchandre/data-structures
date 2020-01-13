package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * Defines necessary methods for all binary trees
 * 
 * @author Gianna Mastrandrea
 *
 * @param <E> generic object
 */
public interface BinaryTreeCollection<E> extends BinaryTree<E>, Iterable<E> {

	/**
	 * Adds given element as the root of the tree
	 * 
	 * @param value element to add
	 * @return reference to the new root
	 */
	Position<E> addRoot(E value);

	/**
	 * Adds an element as the left child of given position p
	 * 
	 * @param p     position
	 * @param value element to add
	 * @return reference to the new child
	 */
	Position<E> addLeft(Position<E> p, E value);

	/**
	 * Adds an element as the right child of given position p
	 * 
	 * @param p     position
	 * @param value element to add
	 * @return reference to the new child
	 */
	Position<E> addRight(Position<E> p, E value);

	/**
	 * Removes element at given position in tree
	 * 
	 * @param p position to remove
	 * @return removed element
	 */
	E remove(Position<E> p);

	/**
	 * Sets element at given position in tree
	 * 
	 * @param p     position to set
	 * @param value element to set position to
	 * @return original overwritten element
	 */
	E set(Position<E> p, E value);

}
