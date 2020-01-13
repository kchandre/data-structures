package edu.ncsu.csc316.dsa.tree;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.list.List;

/**
 * Creates and defines behaviors and methods for all binary tree ADTs
 * 
 * @author Gianna Mastrandrea
 *
 * @param <E> generic object
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTreeCollection<E> {

	@Override
	public Iterable<Position<E>> inOrder() {
		List<Position<E>> traversal = new SinglyLinkedList<Position<E>>();
		if (!isEmpty()) {
			inOrderHelper(root(), traversal);
		}
		return traversal;
	}

	private void inOrderHelper(Position<E> node, List<Position<E>> traversal) {
		if (node == null) {
			return;
		}
		inOrderHelper(left(node), traversal);
		if (node != null && node.getElement() != null)
			traversal.addLast(node);
		inOrderHelper(right(node), traversal);
	}

	@Override
	public Position<E> sibling(Position<E> p) {
		if (parent(p) != null) {
			if (left(parent(p)) == p)
				return right(parent(p));
			return left(parent(p));
		}
		return null;
	}

	private AbstractNode<E> validate(Position<E> p) {
		if (!(p instanceof AbstractNode)) {
			throw new IllegalArgumentException("Position is not a valid binary tree node");
		}
		return (AbstractNode<E>) (p);
	}

	@Override
	public int numChildren(Position<E> p) {
		if (left(p) == null) {
			if (right(p) == null)
				return 0;
			else
				return 1;

		} else if (right(p) == null)
			return 1;
		return 2;
	}

	@Override
	public E set(Position<E> p, E value) {
		AbstractNode<E> node = validate(p);
		E original = node.getElement();
		node.setElement(value);
		return original;
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator(inOrder().iterator());
	}

	@Override
	public Iterable<Position<E>> children(Position<E> p) {
		AbstractNode<E> node = validate(p);
		List<Position<E>> ret = new SinglyLinkedList<Position<E>>();
		if (left(node) != null) {
			ret.addLast(left(node));
		}
		if (right(node) != null) {
			ret.addLast(right(node));
		}
		return ret;
	}
}
