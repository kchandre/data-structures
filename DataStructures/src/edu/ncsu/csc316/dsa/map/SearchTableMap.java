package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Iterator;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * Creates SearchTableMap object and defines necessary methods
 * 
 * @author Gianna Mastrandrea
 *
 * @param <K> key
 * @param <V> value
 */
public class SearchTableMap<K extends Comparable<K>, V> extends AbstractSortedMap<K, V> {

	private ArrayBasedList<Entry<K, V>> list;

	/**
	 * Constructs new search table map with default comparator
	 */
	public SearchTableMap() {
		this(null);
	}

	/**
	 * Creates new search table map with custom comparator
	 * 
	 * @param compare comparator
	 */
	public SearchTableMap(Comparator<K> compare) {
		super(compare);
		list = new ArrayBasedList<Entry<K, V>>();
	}

	/**
	 * Looks up entry with given key
	 * 
	 * @param key key to look up
	 * @return index of entry
	 */
	private int lookUp(K key) {
		if (list.size() == 0) {
			return -1;
		}
		return binarySearchHelper(0, list.size() - 1, key);
	}

	private int binarySearchHelper(int min, int max, K key) {
		if (min > max)
			return -1 * (min + 1);
		int mid = (max + min) / 2;
		if (list.get(mid).getKey() == key)
			return mid;
		else if (super.compare(list.get(mid).getKey(), key) > 0)
			return binarySearchHelper(min, mid - 1, key);
		else
			return binarySearchHelper(mid + 1, max, key);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public V get(K key) {
		int index = lookUp(key);
		if (index > -1)
			return list.get(index).getValue();
		return null;
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayBasedList<Entry<K, V>> set = new ArrayBasedList<Entry<K, V>>();
		for (Entry<K, V> m : list) {
			set.addLast(m);
		}
		return set;
	}

	@Override
	public V put(K key, V value) {
		int index = lookUp(key);
		if (index <= -1) {
			list.add(-1 * (index + 1), new MapEntry<K, V>(key, value));
			return null;
		}
		return list.set(index, new MapEntry<K, V>(key, value)).getValue();
	}

	@Override
	public V remove(K key) {
		int index = lookUp(key);
		if (index > -1)
			return list.remove(index).getValue();
		return null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[");
		Iterator<Entry<K, V>> it = list.iterator();
		while (it.hasNext()) {
			sb.append(it.next().getKey());
			if (it.hasNext()) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}
