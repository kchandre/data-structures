package edu.ncsu.csc316.dsa.priority_queue;

import java.util.Comparator;

/**
 * Defines and implements common methods of all priority queues
 * 
 * @author Gianna Mastrandrea
 *
 * @param <K> key
 * @param <V> value
 */
public abstract class AbstractPriorityQueue<K extends Comparable<K>, V> implements PriorityQueue<K, V> {

	private Comparator<K> comparator;

	/**
	 * Creates new abstract priority queue with custom comparator
	 * 
	 * @param c comparator
	 */
	public AbstractPriorityQueue(Comparator<K> c) {
		setComparator(c);
	}

	private void setComparator(Comparator<K> c) {
		if (c == null) {
			c = new NaturalOrder();
		}
		comparator = c;
	}

	/**
	 * Defines the natural order of comparisons for an abstract priority queue
	 * 
	 * @author Gianna Mastrandrea
	 *
	 */
	public class NaturalOrder implements Comparator<K> {
		/**
		 * Compares two given keys using the natural order
		 * 
		 * @param first  first key to compare
		 * @param second second key to compare
		 * @return 0 if keys are same, greater than 0 if first key is larger, less than
		 *         0 if first key is smaller
		 */
		public int compare(K first, K second) {
			return ((Comparable<K>) first).compareTo(second);
		}
	}

	/**
	 * Compares two given keys
	 * 
	 * @param data1 first key to compare
	 * @param data2 second key to compare
	 * @return 0 if keys are same, greater than 0 if first key is larger, less than
	 *         0 if first key is smaller
	 */
	public int compare(K data1, K data2) {
		return comparator.compare(data1, data2);
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	// Make sure you import PriorityQueue.Entry and NOT Map.Entry!
	/**
	 * Defines and implements necessary methods for an entry in a priority queue
	 * 
	 * @author Gianna Mastrandrea
	 *
	 * @param <K> key
	 * @param <V> value
	 */
	public static class PQEntry<K, V> implements Entry<K, V> {

		private K key;
		private V value;

		/**
		 * Creates new priority queue entry with given key and value
		 * 
		 * @param key   key of entry
		 * @param value value of entry
		 */
		public PQEntry(K key, V value) {
			setKey(key);
			setValue(value);
		}

		/**
		 * Sets key of entry
		 * 
		 * @param key key to set
		 */
		public void setKey(K key) {
			this.key = key;
		}

		/**
		 * Sets value of entry
		 * 
		 * @param value value to set
		 */
		public void setValue(V value) {
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}
	}

	/**
	 * Factory method for constructing a new priority queue entry object
	 * 
	 * @param key   key of entry
	 * @param value value of entry
	 * @return entry created
	 */
	protected Entry<K, V> createEntry(K key, V value) {
		return new PQEntry<K, V>(key, value);
	}
}
