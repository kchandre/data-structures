package edu.ncsu.csc316.dsa.map;

/**
 * Declares necessary functions for Map ADT
 * 
 * @author Gianna Mastrandrea
 *
 * @param <K> key
 * @param <V> value
 */
public interface Map<K, V> extends Iterable<K> {
	
	/**
	 * Returns all entries in map
	 * 
	 * @return iterable of all entries in map
	 */
	Iterable<Entry<K, V>> entrySet();

	/**
	 * Gets value of given key
	 * 
	 * @param key unique key
	 * @return value of key
	 */
	V get(K key);

	/**
	 * Determines if map is empty
	 * 
	 * @return true if map is empty
	 */
	boolean isEmpty();

	/**
	 * Puts given key and value into map. If key already exists, value is updated.
	 * 
	 * @param key   key
	 * @param value value
	 * @return value of key
	 */
	V put(K key, V value);

	/**
	 * Removes given key and corresponding value
	 * 
	 * @param key key
	 * @return value of key removed
	 */
	V remove(K key);

	/**
	 * Gets size of map
	 * 
	 * @return size of map
	 */
	int size();

	/**
	 * Returns all values in map
	 * 
	 * @return iterable of all values in map
	 */
	Iterable<V> values();

	/**
	 * Declares necessary functions for each entry in a map
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

		/**
		 * Sets key of entry
		 * 
		 * @param key key to set
		 * @return overwritten key
		 */
		K setKey(K key);

		/**
		 * Sets value of entry
		 * 
		 * @param value value to set
		 * @return overwritten value
		 */
		V setValue(V value);
	}
}
