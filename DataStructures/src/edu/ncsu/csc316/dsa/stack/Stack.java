package edu.ncsu.csc316.dsa.stack;

/**
 * Establishes methods required for a Stack ADT
 * 
 * @author Gianna Mastrandrea
 *
 * @param <E> generic object
 */
public interface Stack<E> {

	/**
	 * Pushes given value to top of stack
	 * 
	 * @param value value to push
	 */
	void push(E value);

	/**
	 * Gets and removes the top element of stack
	 * 
	 * @return popped element
	 */
	E pop();

	/**
	 * Gets top element of stack
	 * 
	 * @return top element of stack
	 */
	E top();

	/**
	 * Gets size of stack
	 * 
	 * @return size of stack
	 */
	int size();

	/**
	 * Determines if stack is empty
	 * 
	 * @return true if stack is empty
	 */
	boolean isEmpty();

}
