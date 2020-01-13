package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * Creates new UnorderedLinkedMap object and defines necessary methods
 * 
 * @author Gianna Mastrandrea
 *
 * @param <K> key
 * @param <V> value
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {

	private PositionalList<Entry<K, V>> list;

	/**
	 * Creates new unordered linked map
	 */
	public UnorderedLinkedMap() {
		this.list = new PositionalLinkedList<Entry<K, V>>();
	}

	private Position<Entry<K, V>> lookUp(K key) {
		Iterator<Position<Entry<K, V>>> it = list.positions().iterator();
		while (it.hasNext()) {
			Position<Entry<K, V>> temp = it.next();
			if (temp.getElement().getKey() == key) {
				moveToFront(temp);
				return temp;
			}
		}
		return null;
	}

	@Override
	public V get(K key) {
		Position<Entry<K, V>> p = lookUp(key);
		if (p != null) {
			return p.getElement().getValue();
		}
		return null;
	}

	private void moveToFront(Position<Entry<K, V>> position) {
		list.remove(position);
		list.addFirst(position.getElement());
	}

	@Override
	public V put(K key, V value) {
		Position<Entry<K, V>> p = lookUp(key);
		if (p == null) {
			list.addFirst(new MapEntry<K, V>(key, value));
			return null;
		} else {
			V val = p.getElement().getValue();
			list.set(p, new MapEntry<K, V>(key, value));
			return val;
		}
	}

	@Override
	public V remove(K key) {
		Position<Entry<K, V>> p = lookUp(key);
		if (p != null) {
			return list.remove(p).getValue();
		}
		return null;
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		PositionalList<Entry<K, V>> set = new PositionalLinkedList<Entry<K, V>>();
		for (Entry<K, V> m : list) {
			set.addLast(m);
		}
		return set;
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
