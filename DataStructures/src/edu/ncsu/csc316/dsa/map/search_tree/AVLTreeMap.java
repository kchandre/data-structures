package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.Position;

/**
 * Defines and implements necessary behaviors of AVL tree maps
 * 
 * @author Gianna Mastrandrea
 *
 * @param <K> key
 * @param <V> value
 */
public class AVLTreeMap<K extends Comparable<K>, V> extends BinarySearchTreeMap<K, V> {

	/**
	 * Creates new AVL tree map with default comparator
	 */
	public AVLTreeMap() {
		super(null);
	}

	/**
	 * Creates new AVL tree map with custom comparator
	 * 
	 * @param compare custom comparator
	 */
	public AVLTreeMap(Comparator<K> compare) {
		super(compare);
	}

	// Helper method to trace upward from position p checking to make
	// sure each node on the path is balanced. If a rebalance is necessary,
	// trigger a trinode resturcturing
	private void rebalance(Position<Entry<K, V>> p) {
		int oldHeight, newHeight;
		do {
			oldHeight = getProperty(p); // not yet recalculated if internal
			if (!isBalanced(p)) { // imbalance detected
				// perform trinode restructuring, setting p to resulting root,
				// and recompute new local heights after the restructuring
				p = restructure(tallerChild(tallerChild(p)));
				recomputeHeight(left(p));
				recomputeHeight(right(p));
			}
			recomputeHeight(p);
			newHeight = getProperty(p);
			p = parent(p);
		} while (oldHeight != newHeight && p != null);
	}

	// Returns the child of p that has the greater height
	// If both children have the same height, use the child that
	// matches the parent's orientation
	private Position<Entry<K, V>> tallerChild(Position<Entry<K, V>> p) {
		if (getProperty(left(p)) > getProperty(right(p)))
			return left(p);
		if (getProperty(left(p)) < getProperty(right(p)))
			return right(p);
		if (isRoot(p))
			return left(p);
		if (p == left(parent(p)))
			return left(p);
		else
			return right(p);
	}

	private boolean isBalanced(Position<Entry<K, V>> p) {
		return Math.abs(getProperty(left(p)) - getProperty(right(p))) <= 1;
	}

	private void recomputeHeight(Position<Entry<K, V>> p) {
		int h = 1 + Math.max(getProperty(left(p)), getProperty(right(p)));
		setProperty(p, h);
	}

	@Override
	protected void actionOnInsert(Position<Entry<K, V>> node) {
		rebalance(node);
	}

	@Override
	protected void actionOnDelete(Position<Entry<K, V>> node) {
		if (!isRoot(node)) {
			rebalance(parent(node));
		}
	}
}
