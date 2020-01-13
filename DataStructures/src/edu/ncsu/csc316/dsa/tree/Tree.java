package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * Declares necessary methods and behaviors of all trees
 * 
 * @author Gianna Mastrandrea
 *
 * @param <E> generic object
 */
public interface Tree<E> {

	/**
	 * Gets root of tree
	 * 
	 * @return root of tree as a position
	 */
	Position<E> root();

	/**
	 * Gets parent of given position
	 * 
	 * @param p position to get parent of
	 * @return parent of given position as position
	 */
	Position<E> parent(Position<E> p);

	/**
	 * Returns iterable list of children of given position
	 * 
	 * @param p position to get children of
	 * @return iterable list of children as positions
	 */
	Iterable<Position<E>> children(Position<E> p);

	/**
	 * Gets number of children of a given position
	 * 
	 * @param p position to count children of
	 * @return number of children of given position
	 */
	int numChildren(Position<E> p);

	/**
	 * Returns true if given position is an internal node
	 * 
	 * @param p position
	 * @return true if position is an internal node
	 */
	boolean isInternal(Position<E> p);

	/**
	 * Returns true if given position is a leaf node
	 * 
	 * @param p position
	 * @return true if position is a leaf node
	 */
	boolean isLeaf(Position<E> p);

	/**
	 * Returns true if given position is a root node
	 * 
	 * @param p position
	 * @return true if position is a root node
	 */
	boolean isRoot(Position<E> p);

	/**
	 * Gets size of tree
	 * 
	 * @return size of tree
	 */
	int size();

	/**
	 * Determines if tree is empty
	 * 
	 * @return true if tree is empty
	 */
	boolean isEmpty();

	/**
	 * Gets iterable list of positions in tree in pre-order
	 * 
	 * @return iterable of positions
	 */
	Iterable<Position<E>> preOrder();

	/**
	 * Gets iterable list of positions in tree in post-order
	 * 
	 * @return iterable of positions
	 */
	Iterable<Position<E>> postOrder();

	/**
	 * Gets iterable list of positions in tree in level-order
	 * 
	 * @return iterable of positions
	 */
	Iterable<Position<E>> levelOrder();
}
