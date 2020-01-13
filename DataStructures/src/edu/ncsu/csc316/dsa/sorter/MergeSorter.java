package edu.ncsu.csc316.dsa.sorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Creates Merge Sort algorithm and subsequent methods
 * 
 * @author Gianna Mastrandrea
 *
 * @param <E> generic object
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * Creates new MergeSorter object with custom comparator
	 * 
	 * @param comparator custom comparator
	 */
	public MergeSorter(Comparator<E> comparator) {
		super(comparator);
	}

	/**
	 * Creates new MergeSorter object with default comparator
	 */
	public MergeSorter() {
		this(null);
	}

	@Override
	public void sort(E[] data) {

		if (data.length < 2) {
			return;
		}
		int mid = data.length / 2;
		E[] left = Arrays.copyOfRange(data, 0, mid);
		E[] right = Arrays.copyOfRange(data, mid, data.length);

		sort(left);
		sort(right);

		merge(left, right, data);

	}

	private void merge(E[] left, E[] right, E[] data) {
		int leftIndex = 0;
		int rightIndex = 0;
		while ((leftIndex + rightIndex) < data.length) {
			if (rightIndex == right.length
					|| (leftIndex < left.length && super.compare(left[leftIndex], right[rightIndex]) < 0)) {
				data[leftIndex + rightIndex] = left[leftIndex];
				leftIndex++;
			} else {
				data[leftIndex + rightIndex] = right[rightIndex];
				rightIndex++;
			}
		}
	}

}
