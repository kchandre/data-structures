package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

/**
 * Manages common behaviors of all types of map data structures
 * 
 * @author Gianna Mastrandrea
 *
 * @param <K> key
 * @param <V> value
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {

	/**
	 * Manages common behaviors of all map data structure entries
	 * 
	 * @author Gianna Mastrandrea
	 *
	 * @param <K> key
	 * @param <V> value
	 */
	protected static class MapEntry<K, V> implements Entry<K, V> {

		private K key;
		private V value;

		/**
		 * Creates new map entry with given key and value
		 * 
		 * @param key   key
		 * @param value value
		 */
		public MapEntry(K key, V value) {
			setKey(key);
			setValue(value);
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		/**
		 * Sets key of entry
		 * 
		 * @param key key to set
		 * @return overwritten key
		 */
		public K setKey(K key) {
			this.key = key;
			return this.key;
		}

		@Override
		public V setValue(V value) {
			V original = this.value;
			this.value = value;
			return original;
		}
	}

	/**
	 * Iterates through keys in map
	 * 
	 * @author Gianna Mastrandrea
	 *
	 */
	protected class KeyIterator implements Iterator<K> {

		private Iterator<Entry<K, V>> it;

		/**
		 * Creates new key iterator
		 * 
		 * @param iterator entry iterator
		 */
		public KeyIterator(Iterator<Entry<K, V>> iterator) {
			it = iterator;
		}

		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public K next() {
			return it.next().getKey();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("The remove operation is not supported yet.");
		}
	}

	/**
	 * Iterates through values of map
	 * 
	 * @author Gianna Mastrandrea
	 *
	 */
	protected class ValueIterator implements Iterator<V> {

		private Iterator<Entry<K, V>> it;

		/**
		 * Creates new value iterator
		 * 
		 * @param it entry iterator
		 */
		public ValueIterator(Iterator<Entry<K, V>> it) {
			this.it = it;
		}

		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public V next() {
			return it.next().getValue();
		}

	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public Iterator<K> iterator() {
		return new KeyIterator(entrySet().iterator());
	}

	@Override
	public Iterable<V> values() {
		return new ValueIterable();
	}

	/**
	 * Iterates through values of map
	 * 
	 * @author Gianna Mastrandrea
	 *
	 */
	private class ValueIterable implements Iterable<V> {

		@Override
		public Iterator<V> iterator() {
			return new ValueIterator(entrySet().iterator());
		}

	}

}
