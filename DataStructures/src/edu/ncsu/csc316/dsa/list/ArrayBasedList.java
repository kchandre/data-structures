package edu.ncsu.csc316.dsa.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Creates an array-based list object
 * 
 * @author Gianna Mastrandrea
 *
 * @param <E> generic type
 */
public class ArrayBasedList<E> extends AbstractList<E> {

	private final static int DEFAULT_CAPACITY = 10;
	private E[] data;

	private int size;

	/**
	 * Constructs an ArrayBasedList with default capacity
	 */
	public ArrayBasedList() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Constructs an ArrayBasedList with given capacity
	 * 
	 * @param capacity list capacity
	 */
	@SuppressWarnings("unchecked")
	public ArrayBasedList(int capacity) {
		data = (E[]) (new Object[capacity]);
		size = 0;
	}

	@Override
	public void add(int index, E value) {
		super.checkIndexForAdd(index);
		ensureCapacity(size + 1);

		E temp = data[index];
		data[index] = value;

		for (int i = index + 1; i < size + 1; i++) {
			E temp2 = data[i];
			data[i] = temp;
			temp = data[i + 1];
			data[i + 1] = temp2;
			i++;
		}
		size++;
	}

	@Override
	public E get(int index) {
		super.checkIndex(index);
		return data[index];
	}

	@Override
	public E remove(int index) {
		super.checkIndex(index);
		E temp = data[index];
		for (int i = index; i < size; i++) {
			data[i] = data[i + 1];
		}
		size--;
		return temp;
	}

	@Override
	public E set(int index, E value) {
		super.checkIndex(index);
		E temp = data[index];
		data[index] = value;
		return temp;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	private void ensureCapacity(int minCapacity) {
		int oldCapacity = data.length;
		if (minCapacity >= oldCapacity) {
			int newCapacity = (oldCapacity * 2) + 1;
			if (newCapacity < minCapacity) {
				newCapacity = minCapacity;
			}
			data = Arrays.copyOf(data, newCapacity);
		}
	}

	/**
	 * Iterates through elements of a list
	 * 
	 * @author Gianna Mastrandrea
	 *
	 */
	private class ElementIterator implements Iterator<E> {
		private int position;
		private boolean removeOK;

		public ElementIterator() {
			position = 0;
			removeOK = false;
		}

		public boolean hasNext() {
			return data[position] != null;
		}

		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			position++;
			removeOK = true;
			return data[position - 1];
		}

		public void remove() {
			if (removeOK) {
				for (int i = position; i < size + 1; i++) {
					data[i - 1] = data[i];
				}
				position--;
				size--;
				removeOK = false;
			} else {
				throw new IllegalStateException();
			}
		}
	}

}
