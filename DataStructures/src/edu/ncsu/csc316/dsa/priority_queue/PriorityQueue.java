package edu.ncsu.csc316.dsa.priority_queue;

/**
 * Defines necessary methods for priority queues to implement
 * 
 * @author Gianna Mastrandrea
 *
 * @param <K> key
 * @param <V> value
 */
public interface PriorityQueue<K, V> {

	/**
	 * Defines necessary methods for an entry in a priority queue
	 * 
	 * @author Gianna Mastrandrea
	 *
	 * @param <K> key
	 * @param <V> value
	 */
	interface Entry<K, V> {
		/**
		 * Gets key of entry
		 * 
		 * @return key
		 */
		K getKey();

		/**
		 * Gets value of entry
		 * 
		 * @return value
		 */
		V getValue();
	}

	/**
	 * Inserts entry with given key and value into queue
	 * 
	 * @param key   key of entry
	 * @param value value of entry
	 * @return entry overwritten, if any
	 */
	Entry<K, V> insert(K key, V value);

	/**
	 * Gets the minimum entry in the queue
	 * 
	 * @return minimum entry
	 */
	Entry<K, V> min();

	/**
	 * Removes the minimum entry in the queue
	 * 
	 * @return entry removed
	 */
	Entry<K, V> deleteMin();

	/**
	 * Gets size of queue
	 * 
	 * @return size of queue
	 */
	int size();

	/**
	 * Determines if queue is empty
	 * 
	 * @return true if queue is empty
	 */
	boolean isEmpty();
}
