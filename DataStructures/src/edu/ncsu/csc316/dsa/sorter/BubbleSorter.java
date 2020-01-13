package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * BubbleSorter uses the bubble sort algorithm to sort data
 * 
 * @author Gianna Mastrandrea
 *
 * @param <E> the generic type of data to sort
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	
	/**
	 * Constructs bubble sorter object
	 */
	public BubbleSorter() {
		super(null);
	}

	/**
	 * Constructs bubble sorter object with custom comparator
	 * 
	 * @param comparator custom sorting comparator
	 */
	public BubbleSorter(Comparator<E> comparator) {
		super(comparator);
	}

	@Override
	public void sort(E[] data) {
		boolean r = true;
		while (r) {
			r = false;
			for (int i = 1; i < data.length; i++) {
				if (super.compare(data[i], data[i - 1]) < 1) {
					E x = data[i - 1];
					data[i - 1] = data[i];
					data[i] = x;
					r = true;
				}
			}
		}
	}

}
