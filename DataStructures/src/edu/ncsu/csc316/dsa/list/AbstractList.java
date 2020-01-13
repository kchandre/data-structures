package edu.ncsu.csc316.dsa.list;

/**
 * Extracts common functionalities between list data structures
 * 
 * @author Gianna Mastrandrea
 *
 * @param <E> object
 */
public abstract class AbstractList<E> implements List<E> {

	@Override
	public void addFirst(E value) {
		add(0, value);
	}

	@Override
	public void addLast(E value) {
		add(size(), value);
	}

	/**
	 * Checks if given index is inside bounds
	 * 
	 * @param index index to check
	 */
	protected void checkIndex(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Index is invalid: " + index + " (size=" + size() + ")");
		}
	}

	/**
	 * Checks if given index is valid when adding and element
	 * 
	 * @param index index to check
	 */
	protected void checkIndexForAdd(int index) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Index is invalid: " + index + " (size=" + size() + ")");
		}
	}

	@Override
	public E first() {
		return get(0);
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public E last() {
		return get(size() - 1);
	}

	@Override
	public E removeFirst() {
		return remove(0);
	}

	@Override
	public E removeLast() {
		return remove(size() - 1);
	}
}
