package edu.ncsu.csc316.dsa.queue;

/**
 * Establishes methods required for a Queue ADT
 * 
 * @author Gianna Mastrandrea
 *
 * @param <E> generic type
 */
public interface Queue<E> {

	/**
	 * Adds given value to front of queue
	 * 
	 * @param value value to add
	 */
	void enqueue(E value);

	/**
	 * Gets and removes element at front of queue
	 * 
	 * @return removed element
	 */
	E dequeue();

	/**
	 * Gets element at front of queue
	 * 
	 * @return element at front of queue
	 */
	E front();

	/**
	 * Gets size of queue
	 * 
	 * @return size of queue
	 */
	int size();

	/**
	 * Determines if queue is empty
	 * 
	 * @return true if queue is empty
	 */
	boolean isEmpty();

}
