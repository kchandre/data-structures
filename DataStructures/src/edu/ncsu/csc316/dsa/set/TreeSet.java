package edu.ncsu.csc316.dsa.set;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.search_tree.BinarySearchTreeMap;

/**
 * Implements methods necessary for tree sets
 * 
 * @author Gianna Mastrandrea
 *
 * @param <E> generic object
 */
// Remember that search trees are ordered, so our elements must be Comparable
public class TreeSet<E extends Comparable<E>> extends AbstractSet<E> {

	private Map<E, E> tree;

	/**
	 * Creates new tree set
	 */
	public TreeSet() {
		tree = new BinarySearchTreeMap<E, E>();
	}

	@Override
	public Iterator<E> iterator() {
		return tree.iterator();
	}

	@Override
	public void add(E value) {
		tree.put(value, value);
	}

	@Override
	public boolean contains(E value) {
		return tree.get(value) != null;
	}

	@Override
	public E remove(E value) {
		return tree.remove(value);
	}

	@Override
	public int size() {
		return tree.size();
	}

}
