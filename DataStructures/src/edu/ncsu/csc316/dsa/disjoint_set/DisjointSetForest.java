package edu.ncsu.csc316.dsa.disjoint_set;

import edu.ncsu.csc316.dsa.Position;

/**
 * Defines methods necessary for disjoint sets
 * 
 * @author Gianna Mastrandrea
 *
 * @param <E> generic object
 */
public interface DisjointSetForest<E> {

	/**
	 * Makes set of given value
	 * 
	 * @param value value of set
	 * @return position of set
	 */
	Position<E> makeSet(E value);

	/**
	 * Finds given value
	 * 
	 * @param value target value
	 * @return position of value
	 */
	Position<E> find(E value);

	/**
	 * Joins two positions in set together so that there are no duplicate elements
	 * 
	 * @param s set one
	 * @param t set two
	 */
	void union(Position<E> s, Position<E> t);

}
