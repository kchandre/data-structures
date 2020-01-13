package edu.ncsu.csc316.dsa.stack;

/**
 * Establishes a common way for all stacks to check isEmpty()
 * 
 * @author Gianna Mastrandrea
 *
 * @param <E> generic object
 */
public abstract class AbstractStack<E> implements Stack<E> {

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

}
