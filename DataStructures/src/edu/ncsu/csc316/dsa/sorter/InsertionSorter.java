package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * 
 * @param <E> object
 * @author Dr. King
 * @author Gianna Mastrandrea
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * Constructs insertion sorter object
	 */
	public InsertionSorter() {
		super(null);
	}

	/**
	 * Constructs insertion sorter object with custom comparator
	 * 
	 * @param comparator custom sorting comparator
	 */
	public InsertionSorter(Comparator<E> comparator) {
		super(comparator);
	}

	@Override
	public void sort(E[] data) {
		for (int i = 1; i < data.length; i++) {
			E x = data[i];
			int j = i - 1;
			while (j >= 0 && super.compare(data[j], x) == 1) {
				data[j + 1] = data[j];
				j = j - 1;
			}
			data[j + 1] = x;
		}
	}
}
