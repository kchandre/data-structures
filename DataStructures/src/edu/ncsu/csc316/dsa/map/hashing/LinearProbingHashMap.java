package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * Defines and implements methods required for a map using the linear probing
 * collision strategy
 * 
 * @author Gianna Mastrandrea
 *
 * @param <K> key
 * @param <V> value
 */
public class LinearProbingHashMap<K, V> extends AbstractHashMap<K, V> {

	// This time, our array is an array of TableEntry objects
	private TableEntry<K, V>[] table;
	private int size;

	/**
	 * Creates a new linear probing hash map with default capacity, not for testing
	 */
	public LinearProbingHashMap() {
		this(AbstractHashMap.DEFAULT_CAPACITY, false);
	}

	/**
	 * Creates new linear probing hash map with default capacity, for or not for
	 * testing
	 * 
	 * @param isTesting true if map is for testing
	 */
	public LinearProbingHashMap(boolean isTesting) {
		this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
	}

	/**
	 * Creates new linear probing hash map with given capacity, not for testing
	 * 
	 * @param capacity capacity
	 */
	public LinearProbingHashMap(int capacity) {
		this(capacity, false);
	}

	/**
	 * Creates new linear probing hash map with given capacity, for or not for
	 * testing
	 * 
	 * @param capacity  capacity
	 * @param isTesting true if map is for testing
	 */
	public LinearProbingHashMap(int capacity, boolean isTesting) {
		super(capacity, isTesting);
		size = 0;
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		List<Entry<K, V>> list = new SinglyLinkedList<Entry<K, V>>();
		for (TableEntry<K, V> entry : table) {
			if (entry != null && !entry.isDeleted())
				list.addLast(entry);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void createTable(int capacity) {
		table = (TableEntry<K, V>[]) new TableEntry[capacity];
		size = 0;
	}

	// Helper method to determine whether a bucket has an entry or not
	private boolean isAvailable(int index) {
		return (table[index] == null || table[index].isDeleted());
	}

	// Helper method to find the bucket for an entry;
	// If the entry *is* in the map, returns the index of the bucket
	// If the entry is *not* in the map, returns -(a + 1) to indicate
	// that the entry should be added at index a
	private int findBucket(int index, K key) {
		int avail = -1;
		int j = index;
		do {
			if (isAvailable(j)) {
				if (avail == -1)
					avail = j;
				if (table[j] == null)
					return -(avail + 1);
			} else if (table[j].getKey().equals(key))
				return j;
			j = (j + 1) % table.length;
		} while (j != index);
		return -(avail + 1);
	}

	@Override
	public V bucketGet(int hash, K key) {
		int index = findBucket(hash, key);
		if (index >= 0 && !table[index].isDeleted()) {
			return table[index].getValue();
		}
		return null;
	}

	@Override
	public V bucketPut(int hash, K key, V value) {
		int index = findBucket(hash, key);
		if (index >= 0 && !table[index].isDeleted()) {
			return table[index].setValue(value);
		}
		table[-(index + 1)] = new TableEntry<K, V>(key, value);
		size++;
		return null;
	}

	@Override
	public V bucketRemove(int hash, K key) {
		int index = findBucket(hash, key);
		if (index >= 0 && !table[index].isDeleted()) {
			table[index].setDeleted(true);
			size--;
			return table[index].getValue();
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

	/**
	 * Stores an element in the table and a field to indicate whether the element
	 * has been deleted
	 * 
	 * @author Gianna Mastrandrea
	 *
	 * @param <K> key
	 * @param <V> value
	 */
	private static class TableEntry<K, V> extends MapEntry<K, V> {

		private boolean isDeleted;

		public TableEntry(K key, V value) {
			super(key, value);
			setDeleted(false);
		}

		public boolean isDeleted() {
			return isDeleted;
		}

		public void setDeleted(boolean deleted) {
			isDeleted = deleted;
		}
	}
}
