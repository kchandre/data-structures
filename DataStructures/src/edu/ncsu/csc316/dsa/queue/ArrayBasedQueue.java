package edu.ncsu.csc316.dsa.queue;

import java.util.NoSuchElementException;

/**
 * Creates an ArrayBasedQueue and related methods
 * 
 * @author Gianna Mastrandrea
 *
 * @param <E> generic type
 */
public class ArrayBasedQueue<E> extends AbstractQueue<E> {

	private E[] data;
	private int front;
	private int rear;
	private int size;

	private static final int DEFAULT_CAPACITY = 10;

	/**
	 * Creates new ArrayBasedQueue with given initial capacity
	 * 
	 * @param initialCapacity initial capacity of queue
	 */
	@SuppressWarnings("unchecked")
	public ArrayBasedQueue(int initialCapacity) {
		data = (E[]) (new Object[initialCapacity]);
		size = 0;
		front = 0;
		rear = -1;
	}

	/**
	 * Creates new ArrayBasedQueue with default initial capacity
	 */
	public ArrayBasedQueue() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public void enqueue(E value) {
		if (size == data.length) {
			ensureCapacity(front);
		}
		if (rear + 1 == data.length)
			rear = 0;
		else
			rear++;
		
		data[rear] = value;
		size++;
	}

	@Override
	public E dequeue() {
		if (isEmpty())
			throw new NoSuchElementException();

		size--;
		E o = data[front];
		
		if (front + 1 == data.length)
			front = 0;
		else
			front++;
		
		return o;
	}

	@Override
	public E front() {
		if (isEmpty())
			throw new NoSuchElementException();
		
		return data[front];
	}

	@Override
	public int size() {
		return size;
	}

	@SuppressWarnings("unchecked")
	private void ensureCapacity(int capacity) {
		Object[] temp;

		temp = new Object[data.length * 2];

		// Copy elements that are logically in the queue
		for (int i = 0; i < size; i++) {
			temp[i] = data[front];
			if (front + 1 == data.length)
				front = 0;
			else
				front++;
		}

		data = (E[]) temp;
		front = 0;
		rear = size - 1;
	}

}
