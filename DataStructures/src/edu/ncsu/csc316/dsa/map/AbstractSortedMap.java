package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;

/**
 * Manages common behaviors of all types of sorted map data structures
 * 
 * @author Gianna Mastrandrea
 *
 * @param <K> key
 * @param <V> value
 */
public abstract class AbstractSortedMap<K extends Comparable<K>, V> extends AbstractMap<K, V> {

	private Comparator<K> compare;

	/**
	 * Creates new AbstractSortedMap with custom comparator
	 * 
	 * @param compare comparator
	 */
	public AbstractSortedMap(Comparator<K> compare) {
		if (compare == null) {
			this.compare = new NaturalOrder();
		} else {
			this.compare = compare;
		}
	}

	/**
	 * Compares two keys
	 * 
	 * @param key1 key of first entry
	 * @param key2 key of second entry
	 * @return -1 if key1 is less than key2, 1 if key1 is greater than key2, and 0
	 *         if they are the same
	 */
	public int compare(K key1, K key2) {
		return compare.compare(key1, key2);
	}

	/**
	 * Defines the natural order comparator
	 * 
	 * @author Gianna Mastrandrea
	 *
	 */
	private class NaturalOrder implements Comparator<K> {
		public int compare(K first, K second) {
			return ((Comparable<K>) first).compareTo(second);
		}
	}

}
