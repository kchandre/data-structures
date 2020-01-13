package edu.ncsu.csc316.dsa.queue;

/**
 * Establishes a common way for all queues to check isEmpty()
 * 
 * @author Gianna Mastrandrea
 *
 * @param <E> generic type
 */
public abstract class AbstractQueue<E> implements Queue<E> {

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

}
