package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.search_tree.AVLTreeMap;
import edu.ncsu.csc316.dsa.map.search_tree.BinarySearchTreeMap;

/**
 * Defines and implements methods required for a map using the separate chaining
 * collision strategy
 * 
 * @author Gianna Mastrandrea
 *
 * @param <K> key
 * @param <V> value
 */
public class SeparateChainingHashMap<K extends Comparable<K>, V> extends AbstractHashMap<K, V> {

	private Map<K, V>[] table;
	private int size;

	/**
	 * Creates a new separate chaining hash map with default capacity, not for
	 * testing
	 */
	public SeparateChainingHashMap() {
		this(AbstractHashMap.DEFAULT_CAPACITY, false);
	}

	/**
	 * Creates a new linear probing hash map with default capacity, for or not for
	 * testing
	 * 
	 * @param isTesting true if map is for testing
	 */
	public SeparateChainingHashMap(boolean isTesting) {
		this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
	}

	/**
	 * Creates a new linear probing hash map with given capacity, not for testing
	 * 
	 * @param capacity capacity
	 */
	public SeparateChainingHashMap(int capacity) {
		this(capacity, false);
	}

	/**
	 * Creates a new linear probing hash map with given capacity, for or not for
	 * testing
	 * 
	 * @param capacity  capacity
	 * @param isTesting true if map is for testing
	 */
	public SeparateChainingHashMap(int capacity, boolean isTesting) {
		super(capacity, isTesting);
		size = 0;
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		List<Entry<K, V>> list = new SinglyLinkedList<Entry<K, V>>();
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				// Each bucket contains a map, so include
				// all entries in the entrySet for the map
				// at the current bucket
				for (Entry<K, V> entry : table[i].entrySet()) {
					list.addLast(entry);
				}
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void createTable(int capacity) {
		// Example -- change this to whatever map you'd like
		table = (BinarySearchTreeMap<K, V>[]) new AVLTreeMap[capacity];
		size = 0;
	}

	@Override
	public V bucketGet(int hash, K key) {
		// Get the bucket at the specified index in the hash table
		Map<K, V> bucket = table[hash];
		// If there is no map in the bucket, then the entry does not exist
		if (bucket == null) {
			return null;
		}
		// Otherwise, delegate to the existing map's get method to return the value
		return bucket.get(key);
	}

	@Override
	public V bucketPut(int hash, K key, V value) {
		Map<K, V> bucket = table[hash];
		if (bucket != null) {
			int temp = bucket.size();
			V val = table[hash].put(key, value);
			size = size + (bucket.size() - temp);
			return val;
		}
		bucket = new AVLTreeMap<K, V>();
		bucket.put(key, value);
		table[hash] = (Map<K, V>) bucket;
		size++;
		return null;
	}

	@Override
	public V bucketRemove(int hash, K key) {
		Map<K, V> bucket = table[hash];
		if (bucket != null) {
			int temp = bucket.size();
			V val = bucket.remove(key);
			size = size - (temp - bucket.size());
			return val;
		}
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	protected int capacity() {
		return table.length;
	}
}
