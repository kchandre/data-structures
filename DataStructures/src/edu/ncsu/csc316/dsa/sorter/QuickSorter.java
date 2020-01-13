package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * Creates Quick Sort algorithm and subsequent methods
 * 
 * @author Gianna Mastrandrea
 *
 * @param <E> generic object
 */
public class QuickSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/** Creates new PivotSelector that will select the first element */
	public static final PivotSelector FIRST_ELEMENT_SELECTOR = new FirstElementSelector();
	/** Creates new PivotSelector that will select the last element */
	public static final PivotSelector LAST_ELEMENT_SELECTOR = new LastElementSelector();
	/** Creates new PivotSelector that will select the middle element */
	public static final PivotSelector MIDDLE_ELEMENT_SELECTOR = new MiddleElementSelector();
	/** Creates new PivotSelector that will select a random element */
	public static final PivotSelector RANDOM_ELEMENT_SELECTOR = new RandomElementSelector();

	private PivotSelector selector;

	/**
	 * Creates new QuickSorter object with custom comparator and selector
	 * 
	 * @param comparator custom comparator
	 * @param selector   custom pivot selector
	 */
	public QuickSorter(Comparator<E> comparator, PivotSelector selector) {
		super(comparator);
		setSelector(selector);
	}

	/**
	 * Creates new QuickSorter object with custom comparator and default selector
	 * 
	 * @param comparator custom comparator
	 */
	public QuickSorter(Comparator<E> comparator) {
		this(comparator, null);
	}

	/**
	 * Creates new QuickSorter object with default comparator and custom selector
	 * 
	 * @param selector custom selector
	 */
	public QuickSorter(PivotSelector selector) {
		this(null, selector);
	}

	/**
	 * Creates new QuickSorter object with default comparator and selector
	 */
	public QuickSorter() {
		this(null, null);
	}

	private void setSelector(PivotSelector selector) {
		if (selector == null) {
			selector = new RandomElementSelector();
		}
		this.selector = selector;
	}

	@Override
	public void sort(E[] data) {
		quickSort(data, 0, data.length - 1);
	}

	private void quickSort(E[] data, int low, int high) {
		if (low < high) {
			int pivotLocation = partition(data, low, high);
			quickSort(data, low, pivotLocation - 1);
			quickSort(data, pivotLocation + 1, high);
		}
	}

	private int partition(E[] data, int low, int high) {
		// Select a Pivot element
		int pivotIndex = selector.selectPivot(low, high);

		// Swap the pivot to be the last element in the array
		swap(data, pivotIndex, high);
		return partitionHelper(data, low, high);
	}

	private int partitionHelper(E[] data, int low, int high) {
		// The pivot will be in the last index location
		E pivot = data[high];
		int index = low; // index of smaller element
		for (int j = low; j < high; j++) {
			if (super.compare(data[j], pivot) <= 0) {
				swap(data, index, j);
				index++;
			}
		}
		// Swap the index with the pivot (data[high] is the pivot)
		swap(data, index, high);

		// Return the index of the pivot element
		return index;
	}

	private void swap(E[] data, int low, int high) {
		E temp = data[low];
		data[low] = data[high];
		data[high] = temp;
	}

	/**
	 * Interface for selecting pivot to sort
	 * 
	 * @author Gianna Mastrandrea
	 *
	 */
	private interface PivotSelector {
		/**
		 * Returns the index of the selected pivot element
		 * 
		 * @param low  - the lowest index to consider
		 * @param high - the highest index to consider
		 * @return the index of the selected pivot element
		 */
		int selectPivot(int low, int high);
	}

	/**
	 * Returns the lowest/first index to consider
	 * 
	 * @author Gianna Mastrandrea
	 *
	 */
	private static class FirstElementSelector implements PivotSelector {
		@Override
		public int selectPivot(int low, int high) {
			return low;
		}

	}

	/**
	 * Returns the highest/last index to consider
	 * 
	 * @author Gianna Mastrandrea
	 *
	 */
	private static class LastElementSelector implements PivotSelector {
		@Override
		public int selectPivot(int low, int high) {
			return high;
		}

	}

	/**
	 * Returns the middle index to consider
	 * 
	 * @author Gianna Mastrandrea
	 *
	 */
	private static class MiddleElementSelector implements PivotSelector {
		@Override
		public int selectPivot(int low, int high) {
			return (high + low) / 2;
		}

	}

	/**
	 * Returns a random index between low and high inclusive to consider
	 * 
	 * @author Gianna Mastrandrea
	 *
	 */
	private static class RandomElementSelector implements PivotSelector {
		@Override
		public int selectPivot(int low, int high) {
			return low + (int) (Math.random() * ((high - low) + 1));
		}

	}

}
