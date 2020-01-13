package edu.ncsu.csc316.dsa.priority_queue;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * Implements method necessary for a heap priority queue
 * 
 * @author Gianna Mastrandrea
 *
 * @param <K> key
 * @param <V> value
 */
public class HeapPriorityQueue<K extends Comparable<K>, V> extends AbstractPriorityQueue<K, V> {

	// Remember that heaps can be easily implemented using an internal array
	// representation versus a linked representation.

	/** Internal array representation of a heap */
	protected ArrayBasedList<Entry<K, V>> list;

	/**
	 * Creates new heap priority queue with custom comparator
	 * 
	 * @param comparator comparator
	 */
	public HeapPriorityQueue(Comparator<K> comparator) {
		super(comparator);
		list = new ArrayBasedList<Entry<K, V>>();
	}

	/**
	 * Creates new heap priority queue with default comparator
	 */
	public HeapPriorityQueue() {
		this(null);
	}

	//////////////////////////////////////////
	// ADT Operations
	//////////////////////////////////////////

	@Override
	public Entry<K, V> insert(K key, V value) {
		Entry<K, V> temp = createEntry(key, value);
		list.addLast(temp);
		upHeap(list.size() - 1);
		return temp;
	}

	@Override
	public Entry<K, V> min() {
		if (list.isEmpty()) {
			return null;
		}
		return list.first();
	}

	@Override
	public Entry<K, V> deleteMin() {
		if (list.isEmpty()) {
			return null;
		}
		Entry<K, V> temp = list.first();
		swap(0, list.size() - 1);
		list.removeLast();
		downHeap(0);
		return temp;
	}

	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Terminates when key at given index is less than the keys of both its children
	 * or the top of the heap is reached, swaps parent and child elements
	 * 
	 * @param index target index
	 */
	protected void upHeap(int index) {
		if (index > 0) {
			int parentIndex = parent(index);
			if (super.compare(list.get(parentIndex).getKey(), list.get(index).getKey()) > 0) {
				swap(parentIndex, index);
				upHeap(parentIndex);
			}
		}
	}

	/**
	 * Swaps elements at given indexes
	 * 
	 * @param index1 index of first element
	 * @param index2 index of second element
	 */
	protected void swap(int index1, int index2) {
		Entry<K, V> temp = list.get(index1);
		list.set(index1, list.get(index2));
		list.set(index2, temp);
	}

	/**
	 * Terminates when key at given index is greater than the keys of both its
	 * children or the bottom of the heap is reached, swaps parent and child
	 * elements
	 * 
	 * @param index target index
	 */
	protected void downHeap(int index) {
		if (!hasLeft(index)) {
			return;
		}
		int min = left(index);
		if (hasRight(index) && super.compare(list.get(right(index)).getKey(), list.get(left(index)).getKey()) < 0) {
			min = right(index);
		}
		if (super.compare(list.get(min).getKey(), list.get(index).getKey()) >= 0) {
			return;
		}
		swap(min, index);
		downHeap(min);
	}

	//////////////////////////////////////////////////
	// Convenience methods to help abstract the math
	// involved in jumping to parent or children
	//////////////////////////////////////////////////

	/**
	 * Calculates the index of the parent of the given index
	 * 
	 * @param index child index
	 * @return parent index
	 */
	protected int parent(int index) {
		return (index - 1) / 2;
	}

	/**
	 * Calculates the index of the left of the given index
	 * 
	 * @param index target index
	 * @return left index
	 */
	protected int left(int index) {
		return 2 * index + 1;
	}

	/**
	 * Calculates the index of the right of the given index
	 * 
	 * @param index target index
	 * @return right index
	 */
	protected int right(int index) {
		return 2 * index + 2;
	}

	/**
	 * Determines if given index has a valid left index
	 * 
	 * @param index target index
	 * @return true if left index exists
	 */
	protected boolean hasLeft(int index) {
		return left(index) < list.size();
	}

	/**
	 * Determines if given index has a valid right index
	 * 
	 * @param index target index
	 * @return true if right index exists
	 */
	protected boolean hasRight(int index) {
		return right(index) < list.size();
	}

}
