package edu.ncsu.csc316.dsa.disjoint_set;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * Implements necessary methods for a disjoint set forest
 * 
 * @author Gianna Mastrandrea
 *
 * @param <E> generic object
 */
public class UpTreeDisjointSetForest<E> implements DisjointSetForest<E> {

	// We need a secondary map to quickly locate an entry within the forest of
	// up-trees
	// NOTE: The textbook implementation does not include this
	// functionality; instead, the textbook implementation leaves
	// the responsibility of tracking positions to the client in a
	// separate map structure
	private Map<E, UpTreeNode<E>> map;

	/**
	 * Creates new disjoint set forest of up trees
	 */
	public UpTreeDisjointSetForest() {
		// Use an efficient map!
		map = new LinearProbingHashMap<E, UpTreeNode<E>>();
	}

	@Override
	public Position<E> makeSet(E value) {
		UpTreeNode<E> temp = new UpTreeNode<E>(value);
		map.put(value, temp);
		return temp;
	}

	@Override
	public Position<E> find(E value) {
		// NOTE: The textbook solution requires the client to keep
		// track of the location of each position in the forest.
		// Our implementation includes a Map to handle this for the
		// client, so we should allow the client to find the set
		// that contains a node by specifying the element
		UpTreeNode<E> temp = map.get(value);
		if (temp != null)
			return findHelper(temp);
		return null;
	}

	private UpTreeNode<E> findHelper(UpTreeNode<E> current) {
		// Implement path-compression find
		if (current != current.getParent()) {
			current.setParent(findHelper(current.getParent()));
		}
		return current.getParent();
	}

	@Override
	public void union(Position<E> s, Position<E> t) {
		// Instead of trusting the client to give us the roots
		// of two up-trees, we will verify by finding the root
		// of the up-tree that contains the element in positions s and t
		UpTreeNode<E> a = validate(find(s.getElement()));
		UpTreeNode<E> b = validate(find(t.getElement()));
		if (a.getCount() > b.getCount()) {
			a.setCount(a.getCount() + b.getCount());
			b.setParent(a);
		} else {
			b.setCount(a.getCount() + b.getCount());
			a.setParent(b);
		}
	}

	private UpTreeNode<E> validate(Position<E> p) {
		if (!(p instanceof UpTreeNode)) {
			throw new IllegalArgumentException("Position is not a valid up tree node.");
		}
		return (UpTreeNode<E>) p;
	}

	/**
	 * Defines a node in an up tree data structure
	 * 
	 * @author Gianna Mastrandrea
	 *
	 * @param <E> generic object
	 */
	private static class UpTreeNode<E> implements Position<E> {

		private E element;
		private UpTreeNode<E> parent;
		private int count;

		public UpTreeNode(E element) {
			setElement(element);
			setParent(this);
			setCount(1);
		}

		public void setElement(E element) {
			this.element = element;
		}

		@Override
		public E getElement() {
			return element;
		}

		public void setParent(UpTreeNode<E> parent) {
			this.parent = parent;
		}

		public UpTreeNode<E> getParent() {
			return parent;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public int getCount() {
			return count;
		}
	}

}
