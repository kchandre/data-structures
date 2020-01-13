package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * Defines necessary behaviors of binary tree
 * 
 * @author Gianna Mastrandrea
 *
 * @param <E> generic object
 */
public interface BinaryTree<E> extends Tree<E> {

	/**
	 * Returns left child of given node p
	 * 
	 * @param p position of node
	 * @return left child of given node
	 */
	Position<E> left(Position<E> p);

	/**
	 * Returns right child of given node p
	 * 
	 * @param p position of node
	 * @return right child of given node
	 */
	Position<E> right(Position<E> p);

	/**
	 * Returns sibling of given node p
	 * 
	 * @param p position of node
	 * @return sibling of given node
	 */
	Position<E> sibling(Position<E> p);

	/**
	 * Returns iterable of positions in order
	 * 
	 * @return iterable list of positions in order
	 */
	Iterable<Position<E>> inOrder();

}
