package edu.ncsu.csc316.dsa.tree;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;

/**
 * Creates and defines behaviors and methods for all tree ADTs
 * 
 * @author Gianna Mastrandrea
 *
 * @param <E> generic object
 */
public abstract class AbstractTree<E> implements Tree<E> {

	@Override
	public boolean isInternal(Position<E> p) {
		return numChildren(p) > 0;
	}

	@Override
	public boolean isLeaf(Position<E> p) {
		return numChildren(p) == 0;
	}

	@Override
	public boolean isRoot(Position<E> p) {
		return p == root();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Creates and defines behaviors for positional nodes in all trees
	 * 
	 * @author Gianna Mastrandrea
	 *
	 * @param <E> generic object
	 */
	protected abstract static class AbstractNode<E> implements Position<E> {

		private E element;

		/**
		 * Creates new node with given element
		 * 
		 * @param element element
		 */
		public AbstractNode(E element) {
			setElement(element);
		}

		@Override
		public E getElement() {
			return element;
		}

		/**
		 * Sets element at current positional node
		 * 
		 * @param element element to set
		 */
		public void setElement(E element) {
			this.element = element;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[\n");
		toStringHelper(sb, "", root());
		sb.append("]");
		return sb.toString();
	}

	private void toStringHelper(StringBuilder sb, String indent, Position<E> root) {
		if (root == null) {
			return;
		}
		sb.append(indent).append(root.getElement()).append("\n");
		for (Position<E> child : children(root)) {
			toStringHelper(sb, indent + " ", child);
		}
	}

	/**
	 * Iterates through the tree in preOrder traversal
	 * 
	 * @return iterable list of positions in preOrder traversal order
	 */
	public Iterable<Position<E>> preOrder() {
		// You can use any list data structure here that supports
		// O(1) addLast
		List<Position<E>> traversal = new SinglyLinkedList<Position<E>>();
		if (!isEmpty()) {
			preOrderHelper(root(), traversal);
		}
		return traversal;
	}

	private void preOrderHelper(Position<E> p, List<Position<E>> traversal) {
		if (p != null && p.getElement() != null)
			traversal.addLast(p);
		for (Position<E> c : children(p)) {
			preOrderHelper(c, traversal);
		}
	}

	@Override
	public Iterable<Position<E>> postOrder() {
		// You can use any list data structure here that supports
		// O(1) addLast
		List<Position<E>> list = new SinglyLinkedList<Position<E>>();
		if (!isEmpty()) {
			postOrderHelper(root(), list);
		}
		return list;
	}

	private void postOrderHelper(Position<E> p, List<Position<E>> list) {
		for (Position<E> c : children(p)) {
			postOrderHelper(c, list);
		}
		if (p != null && p.getElement() != null)
			list.addLast(p);
	}

	@Override
	public Iterable<Position<E>> levelOrder() {
		List<Position<E>> list = new SinglyLinkedList<Position<E>>();
		if (!isEmpty()) {
			levelOrderHelper(root(), list);
		}
		return list;
	}

	private void levelOrderHelper(Position<E> p, List<Position<E>> list) {
		ArrayBasedQueue<Position<E>> q = new ArrayBasedQueue<Position<E>>();
		ArrayBasedQueue<Position<E>> q2 = new ArrayBasedQueue<Position<E>>();
		if (p == null) {
			return;
		}
		q.enqueue(p);
		q2.enqueue(p);
		while (!q.isEmpty()) {
			Position<E> temp = q.dequeue();
			Iterator<Position<E>> it = children(temp).iterator();
			while (it.hasNext()) {
				Position<E> pos = it.next();
				q.enqueue(pos);
				q2.enqueue(pos);
			}
		}
		while (!q2.isEmpty()) {
			Position<E> temp = q2.dequeue();
			if (temp != null && temp.getElement() != null)
				list.addLast(temp);
		}

	}

	/**
	 * Creates and defines behaviors and methods for an element iterator
	 * 
	 * @author Gianna Mastrandrea
	 *
	 */
	protected class ElementIterator implements Iterator<E> {
		private Iterator<Position<E>> it;

		/**
		 * Creates new element iterator with given iterator
		 * 
		 * @param iterator iterator
		 */
		public ElementIterator(Iterator<Position<E>> iterator) {
			it = iterator;
		}

		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public E next() {
			return it.next().getElement();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("The remove operation is not supported yet.");
		}
	}

}
