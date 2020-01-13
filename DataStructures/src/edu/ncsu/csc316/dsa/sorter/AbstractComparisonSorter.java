package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * Abstracts comparison sorter classes to one general class
 * 
 * @param <E> object
 * @author Gianna Mastrandrea
 *
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {

	private Comparator<E> comparator;

	/**
	 * Creates new abstract comparison sorter and sets the type of comparator
	 * 
	 * @param comparator type of comparator
	 */
	public AbstractComparisonSorter(Comparator<E> comparator) {
		setComparator(comparator);
	}

	private void setComparator(Comparator<E> comparator) {
		if (comparator == null) {
			comparator = new NaturalOrder();
		}
		this.comparator = comparator;
	}

	/**
	 * Default comparator if a custom comparator isn't provided
	 * 
	 * @author Gianna Mastrandrea
	 *
	 */
	private class NaturalOrder implements Comparator<E> {
		public int compare(E first, E second) {
			return ((Comparable<E>) first).compareTo(second);
		}
	}

	/**
	 * Compares two objects
	 * 
	 * @param data1 object one
	 * @param data2 object two
	 * @return negative if less than, positive if greater than, zero if the same
	 */
	public int compare(E data1, E data2) {
		return comparator.compare(data1, data2);
	}

}
