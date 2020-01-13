package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;

/**
 * Creates a positional linked list and related methods
 * 
 * @author Gianna Mastrandrea
 *
 * @param <E> generic object
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

	private PositionalNode<E> front;
	private PositionalNode<E> tail;
	private int size;

	/**
	 * Constructs new positional linked list
	 */
	public PositionalLinkedList() {
		front = new PositionalNode<E>(null);
		tail = new PositionalNode<E>(null, null, front);
		front.setNext(tail);
		size = 0;
	}

	private Position<E> addBetween(E value, PositionalNode<E> next, PositionalNode<E> prev) {
		validate(next);
		validate(prev);

		Position<E> temp = new PositionalNode<E>(value, next, prev);
		prev.setNext((PositionalNode<E>) temp);
		next.setPrevious((PositionalNode<E>) temp);
		size++;
		return temp;
	}

	@Override
	public Position<E> addFirst(E value) {
		return addBetween(value, front.getNext(), front);
	}

	@Override
	public Position<E> addLast(E value) {
		return addBetween(value, tail, tail.getPrevious());
	}

	@Override
	public Position<E> addAfter(Position<E> p, E value) {
		return addBetween(value, (PositionalNode<E>) after(p), (PositionalNode<E>) p);
	}

	@Override
	public Position<E> addBefore(Position<E> p, E value) {
		return addBetween(value, (PositionalNode<E>) p, (PositionalNode<E>) before(p));
	}

	@Override
	public E remove(Position<E> p) {
		validate(p);
		PositionalNode<E> temp = front;
		while (temp.getNext() != null && temp.getNext().getElement() != p.getElement()) {
			temp = temp.getNext();
		}
		if (temp.getNext() != null) {
			E removed = temp.getNext().getElement();
			if (last() == temp.getNext()) {
				temp.setNext(tail);
				tail.setPrevious(temp);
			} else {
				temp.setNext(temp.getNext().getNext());
				try {
					temp.getNext().setPrevious(temp);
				} catch (NullPointerException e) {
					// Tail case
					return removed;
				}
			}
			size--;
			return removed;
		}
		return null;
	}

	@Override
	public E set(Position<E> p, E value) {
		validate(p);
		PositionalNode<E> temp = front;
		while (temp.getElement() != p.getElement()) {
			temp = temp.getNext();
		}
		E replaced = temp.getElement();
		temp.setElement(value);
		return replaced;
	}

	@Override
	public Position<E> after(Position<E> p) {
		validate(p);
		PositionalNode<E> temp = front;
		while (temp != (PositionalNode<E>) p) {
			temp = temp.getNext();
		}
		if (temp.getNext() == null) {
			throw new NoSuchElementException();
		}
		return temp.getNext();
	}

	@Override
	public Position<E> before(Position<E> p) {
		validate(p);
		PositionalNode<E> temp = front;
		while (temp != (PositionalNode<E>) p && temp != null) {
			temp = temp.getNext();
		}
		if (temp == null || temp.getNext() == null) {
			throw new NoSuchElementException();
		} else {
			return temp.getPrevious();
		}
	}

	@Override
	public Position<E> first() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return front.getNext();
	}

	@Override
	public Position<E> last() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return tail.getPrevious();
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
		// we start at front.getNext() because front is a dummy/sentinel node
		return new ElementIterator(front.getNext());
	}

	@Override
	public Iterable<Position<E>> positions() {

		Iterable<Position<E>> e = new PositionIterable();
		return e;
	}

	private PositionalNode<E> validate(Position<E> p) {
		if (p instanceof PositionalNode) {
			return (PositionalNode<E>) p;
		}
		throw new IllegalArgumentException("Position is not a valid positional list node.");
	}

	/**
	 * Creates positional node for navigation through the linked list
	 * 
	 * @author Gianna Mastrandrea
	 *
	 * @param <E>
	 */
	private static class PositionalNode<E> implements Position<E> {

		private E element;
		private PositionalNode<E> next;
		private PositionalNode<E> previous;

		public PositionalNode(E value) {
			this(value, null);
		}

		public PositionalNode(E value, PositionalNode<E> next) {
			this(value, next, null);
		}

		public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
			setElement(value);
			setNext(next);
			setPrevious(prev);
		}

		public void setPrevious(PositionalNode<E> prev) {
			previous = prev;
		}

		public PositionalNode<E> getPrevious() {
			return previous;
		}

		public void setNext(PositionalNode<E> next) {
			this.next = next;
		}

		public PositionalNode<E> getNext() {
			return next;
		}

		@Override
		public E getElement() {
			return element;
		}

		public void setElement(E element) {
			this.element = element;
		}
	}

	/**
	 * Iterates through positions in list
	 * 
	 * @author Gianna Mastrandrea
	 *
	 */
	private class PositionIterator implements Iterator<Position<E>> {

		private Position<E> current;
		private boolean removeOK;

		public PositionIterator(PositionalNode<E> start) {
			current = start;
			removeOK = false;
		}

		@Override
		public boolean hasNext() {
			if (isEmpty()) {
				return false;
			} else if (current == null) {
				return true;
			} else if (tail != null && current.getElement() == tail.getElement()) {
				return false;
			}
			return true;
		}

		@Override
		public Position<E> next() {
			removeOK = true;
			if (current.getElement() == null) {
				throw new NoSuchElementException();
			}
			Position<E> temp = current;
			PositionalNode<E> nav = front;
			while (nav != current) {
				nav = nav.getNext();
			}
			current = nav.getNext();
			return temp;
		}

		@Override
		public void remove() {
			if (removeOK) {
				Position<E> temp = current;
				PositionalNode<E> nav = front;
				while (nav.getNext() != current) {
					nav = nav.getNext();
				}
				temp = nav;
				PositionalLinkedList.this.remove(temp);
			} else {
				throw new IllegalStateException();
			}
			removeOK = false;
		}
	}

	/**
	 * Iterates through elements in list
	 * 
	 * @author Gianna Mastrandrea
	 *
	 */
	private class ElementIterator implements Iterator<E> {

		private Iterator<Position<E>> it;

		public ElementIterator(PositionalNode<E> start) {
			it = new PositionIterator(start);
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
			it.remove();
		}
	}

	/**
	 * Adapts iterator to become an Iterable object
	 * 
	 * @author Gianna Mastrandrea
	 *
	 */
	private class PositionIterable implements Iterable<Position<E>> {

		@Override
		public Iterator<Position<E>> iterator() {
			return new PositionIterator(front.getNext());
		}
	}

}
