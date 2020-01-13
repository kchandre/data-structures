package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.Position;

/**
 * Defines and implements necessary behaviors of splay tree maps
 * 
 * @author Gianna Mastrandrea
 *
 * @param <K> key
 * @param <V> value
 */
public class SplayTreeMap<K extends Comparable<K>, V> extends BinarySearchTreeMap<K, V> {

	/**
	 * Creates new splay tree map with default comparator
	 */
	public SplayTreeMap() {
		super(null);
	}

	/**
	 * Creates new splay tree map with custom comparator
	 * 
	 * @param compare custom comparator
	 */
	public SplayTreeMap(Comparator<K> compare) {
		super(compare);
	}

	/*
	 * Citing Help from the Textbooks 
	 * The code for this method is based on the
	 * splay algorithm on page 494 in the course textbook
	 * "Data Structures & Algorithms" by Goodrich, Tamassia, Goldwasser.
	 */
	private void splay(Position<Entry<K, V>> p) {
		while (!isRoot(p)) {
			// Track the parent and grandparent nodes
			Position<Entry<K, V>> parent = parent(p);
			Position<Entry<K, V>> grandparent = parent(parent);

			if (grandparent == null) {
				// ZIG --> Perform a single rotation if there is no grandparent
				rotate(p);
			} else if (((parent == left(grandparent)) == (p == left(parent)))) {
				// ZIG-ZIG --> Rotate the parent around grandparent first
				rotate(parent);
				rotate(p); // Then rotate the node around the parent
			} else {
				// ZIG-ZAG --> Rotate node around parent
				rotate(p);
				rotate(p); // Then rotate node around grandparent
			}
		}
	}

	@Override
	protected void actionOnAccess(Position<Entry<K, V>> p) {
		// If p is a dummy/sentinel node, move to the parent
		if (isLeaf(p)) {
			p = parent(p);
		}
		if (p != null) {
			splay(p);
		}
	}

	@Override
	protected void actionOnInsert(Position<Entry<K, V>> node) {
		splay(node);
	}

	@Override
	protected void actionOnDelete(Position<Entry<K, V>> p) {
		if (!isRoot(p)) {
			splay(parent(p));
		}
	}
}
