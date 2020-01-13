package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * Creates and manages Unordered Array Map methods and objects
 * 
 * @author Gianna Mastrandrea
 *
 * @param <K> key
 * @param <V> value
 */
public class UnorderedArrayMap<K, V> extends AbstractMap<K, V> {

	// Use the adapter pattern to delegate to our existing
	// array-based list implementation
	private ArrayBasedList<Entry<K, V>> list;

	/**
	 * Creates new UnorderedArrayMap
	 */
	public UnorderedArrayMap() {
		this.list = new ArrayBasedList<Entry<K, V>>();
	}

	private int lookUp(K key) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getKey() == key) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public V get(K key) {
		int index = lookUp(key);
		if (index != -1)
			return transpose(index);
		return null;
	}

	@Override
	public V put(K key, V value) {
		int index = lookUp(key);
		if (index == -1) {
			list.add(0, new MapEntry<K, V>(key, value));
			return null;
		} else {
			V val = list.get(index).getValue();
			list.set(index, new MapEntry<K, V>(key, value));
			transpose(index);
			return val;
		}
	}

	@Override
	public V remove(K key) {
		int index = lookUp(key);
		if (index == -1) {
			return null;
		}
		return list.remove(index).getValue();
	}

	@Override
	public int size() {
		return list.size();
	}

	private V transpose(int index) {
		V val = list.get(index).getValue();
		if (index != 0) {
			Entry<K, V> prev = list.get(index - 1);
			list.set(index - 1, list.get(index));
			list.set(index, prev);
		}
		return val;
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayBasedList<Entry<K, V>> l = new ArrayBasedList<Entry<K, V>>();
		for (int i = 0; i < list.size(); i++) {
			l.add(i, list.get(i));
		}
		return l;
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
