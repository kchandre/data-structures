package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Creates a singly linked list and related methods
 * 
 * @author Gianna Mastrandrea
 *
 * @param <E> generic type
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

	private int size;
	private LinkedListNode<E> front;
	private LinkedListNode<E> tail;

	/**
	 * Constructs new singly linked list
	 */
	public SinglyLinkedList() {
		// Let front be a dummy (sentinel) node
		front = new LinkedListNode<E>(null);
		tail = null;
		size = 0;
	}

	@Override
	public void add(int index, E value) {
		super.checkIndexForAdd(index);

		LinkedListNode<E> nav = front;
		for (int i = 1; i < index + 1 && nav != null; i++) {
			nav = nav.getNext();
		}
		if (nav != null) {
			LinkedListNode<E> temp = new LinkedListNode<E>(value, nav.getNext());
			nav.setNext(temp);
		}
		if (index == size) {
			tail = new LinkedListNode<E>(value);
		}
		size++;
	}

	@Override
	public E get(int index) {
		super.checkIndex(index);
		LinkedListNode<E> temp = front;
		for (int i = 0; i < index + 1; i++) {
			temp = temp.getNext();
		}
		return temp.getElement();
	}

	@Override
	public E remove(int index) {
		super.checkIndex(index);

		if (index == 0) {
			E temp = front.getNext().getElement();
			front.setNext(front.getNext().getNext());
			size--;
			return temp;
		} else {
			LinkedListNode<E> prev = front;
			for (int i = 0; i < index && prev != null; i++) {
				prev = prev.getNext();
			}
			if (index == size) {
				tail = prev.getNext();
			}
			if (prev != null) {
				E temp = prev.getNext().getElement();
				prev.setNext(prev.getNext().getNext());
				size--;
				return temp;
			}
		}
		return null;
	}

	@Override
	public E set(int index, E value) {
		super.checkIndex(index);
		if (index == size) {
			tail = new LinkedListNode<E>(value);
		}

		LinkedListNode<E> prev = front;
		for (int i = 0; i < index + 1 && prev != null; i++) {
			prev = prev.getNext();
		}
		if (prev != null) {
			E temp = prev.getElement();
			prev.setElement(value);
			return temp;
		}
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
		// skip dummy/sentinel node
		return new ElementIterator(front.getNext());
	}

	@Override
	public E last() {
		if (tail != null) {
			return tail.getElement();
		}
		return null;
	}

	@Override
	public void addLast(E value) {
		if (front.getNext() == null) {
			front.setNext(new LinkedListNode<E>(value));
			tail = new LinkedListNode<E>(value);
		} else {
			LinkedListNode<E> temp = front;
			for (int i = 1; i < size + 1 && temp != null; i++) {
				temp = temp.getNext();
			}
			if (temp != null) {
				temp.setNext(new LinkedListNode<E>(value, temp.getNext()));
			}
			tail = new LinkedListNode<E>(value);
		}
		size++;
	}

	/**
	 * Creates list nodes to be used by SinglyLinkedLists
	 * 
	 * @author Gianna Mastrandrea
	 *
	 * @param <E>
	 */
	private static class LinkedListNode<E> {
		private E data;
		private LinkedListNode<E> next;

		public LinkedListNode(E data) {
			this.data = data;
			next = null;
		}

		public LinkedListNode(E data, LinkedListNode<E> next) {
			this.data = data;
			this.next = next;
		}

		public LinkedListNode<E> getNext() {
			return next;
		}

		public E getElement() {
			return data;
		}

		public void setNext(LinkedListNode<E> next) {
			this.next = next;
		}

		public void setElement(E data) {
			this.data = data;
		}

	}

	/**
	 * Creates an iterator to move through given elements in list
	 * 
	 * @author Gianna Mastrandrea
	 *
	 */
	private class ElementIterator implements Iterator<E> {
		// Keep track of the next node that will be processed
		private LinkedListNode<E> current;
		// Keep track of the node that was processed on the last call to 'next'
		private LinkedListNode<E> previous;
		// Keep track of the previous-previous node that was processed so that we can
		// update 'next' links when removing
		// private LinkedListNode<E> previousPrevious;
		private boolean removeOK;

		public ElementIterator(LinkedListNode<E> start) {
			current = start;
			removeOK = false;
		}

		public boolean hasNext() {
			if (current != null) {
				return true;
			}
			return false;
		}

		public E next() {
			if (current == null) {
				throw new NoSuchElementException();
			}
			previous = current;
			E temp = current.getElement();
			current = current.getNext();
			removeOK = true;
			return temp;
		}

		public void remove() {
			if (removeOK) {
				int pos = -1;
				LinkedListNode<E> nav = front;
				while (nav != previous && nav != null) {
					nav = nav.getNext();
					pos++;
				}
				SinglyLinkedList.this.remove(pos);
				if (pos == size) {
					nav = front;
					while (nav != null && nav.getNext() != null) {
						nav = nav.getNext();
					}
					tail = nav;
				}
			} else if (!removeOK) {
				throw new IllegalStateException();
			}
			removeOK = false;
		}
	}

}
