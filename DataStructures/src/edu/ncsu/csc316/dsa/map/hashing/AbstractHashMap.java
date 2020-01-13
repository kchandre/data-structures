package edu.ncsu.csc316.dsa.map.hashing;

import java.util.Random;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.AbstractMap;

/**
 * Defines behaviors necessary for all hash maps to implement
 * 
 * @author Gianna Mastrandrea
 *
 * @param <K> key
 * @param <V> value
 */
public abstract class AbstractHashMap<K, V> extends AbstractMap<K, V> {

	/** An initial capacity for the hash table */
	protected static final int DEFAULT_CAPACITY = 17;

	// From our discussion in class, the expected number of probes
	// for separate chaining remains relatively the same no matter
	// what the load factor may be. However, for linear probing, to
	// reduce the chance of having large clusters, we will resize
	// when the load factor reaches 0.5
	private static final double MAX_LOAD_FACTOR = 0.5;

	/** A default value for the prime value for MAD compression */
	protected static final int DEFAULT_PRIME = 109345121;

	// Alpha and Beta values for MAD compression
	// This implementation uses a variation of the MAD method
	// where h(k) = ( (alpha * f(k) + beta) % prime) % capacity
	private long alpha;
	private long beta;

	// The prime number to use for compression strategy
	private int prime;

	// You can use the isTesting flag (set to true) to control
	// the testing environment and avoid random numbers when testing
	/**
	 * Creates new abstract hash map with given capacity, for or not for testing
	 * 
	 * @param capacity  capacity
	 * @param isTesting true if map is for testing
	 */
	public AbstractHashMap(int capacity, boolean isTesting) {
		if (isTesting) {
			alpha = 1;
			beta = 1;
			prime = 7;
		} else {
			Random rand = new Random();
			alpha = rand.nextInt(DEFAULT_PRIME - 1) + 1;
			beta = rand.nextInt(DEFAULT_PRIME);
			prime = DEFAULT_PRIME;
		}
		createTable(capacity);
	}

	private int compress(K key) {
		return (int) ((Math.abs(key.hashCode() * alpha + beta) % prime) % capacity());
	}

	@Override
	public V put(K key, V value) {
		V ret = bucketPut(compress(key), key, value);
		if ((double) size() / capacity() > MAX_LOAD_FACTOR) {
			resize(2 * capacity() + 1);
		}
		return ret;
	}

	@Override
	public V get(K key) {
		return bucketGet(compress(key), key);
	}

	@Override
	public V remove(K key) {
		return bucketRemove(compress(key), key);
	}

	private void resize(int newCapacity) {
		List<Entry<K, V>> list = new ArrayBasedList<Entry<K, V>>();
		for (Entry<K, V> entry : entrySet()) {
			list.addLast(entry);
		}
		createTable(newCapacity);
		for (Entry<K, V> entry : list) {
			put(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * Gets capacity of hash map
	 * 
	 * @return capacity of hash map
	 */
	protected abstract int capacity();

	/**
	 * Creates table for hashing
	 * 
	 * @param capacity capacity of table
	 */
	protected abstract void createTable(int capacity);

	/**
	 * Gets given key from bucket with the given hash
	 * 
	 * @param hash hash of the key
	 * @param key  key to get
	 * @return value of key at given hash
	 */
	protected abstract V bucketGet(int hash, K key);

	/**
	 * Puts given key and value into bucket with the given hash
	 * 
	 * @param hash  hash of the key
	 * @param key   key to put
	 * @param value value to put
	 * @return value overwritten, if any
	 */
	protected abstract V bucketPut(int hash, K key, V value);

	/**
	 * Removes given key from bucket with the given hash
	 * 
	 * @param hash hash of key
	 * @param key  key to remove
	 * @return value of key removed
	 */
	protected abstract V bucketRemove(int hash, K key);
}
