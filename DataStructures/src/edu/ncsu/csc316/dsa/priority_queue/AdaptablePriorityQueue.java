package edu.ncsu.csc316.dsa.priority_queue;

/**
 * Defines behaviors necessary for all adaptable priority queues
 * 
 * @author Gianna Mastrandrea
 *
 * @param <K> key
 * @param <V> value
 */
public interface AdaptablePriorityQueue<K, V> extends PriorityQueue<K, V> {

	/**
	 * Removes given entry from priority queue
	 * 
	 * @param entry entry to be removed
	 */
	void remove(Entry<K, V> entry);

	/**
	 * Replaces key of given entry
	 * 
	 * @param entry target entry
	 * @param key   key to replace
	 */
	void replaceKey(Entry<K, V> entry, K key);

	/**
	 * Replaces value of given entry
	 * 
	 * @param entry target entry
	 * @param value value to replace
	 */
	void replaceValue(Entry<K, V> entry, V value);

}
