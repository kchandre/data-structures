package edu.ncsu.csc316.dsa.set;

/**
 * Implements common methods all sets will use
 * 
 * @author Gianna Mastrandrea
 *
 * @param <E> generic object
 */
public abstract class AbstractSet<E> implements Set<E> {

	@Override
	public void addAll(Set<E> t) {
		if (!t.isEmpty())
			for (E element : t) {
				add(element);
			}
	}

	@Override
	public void retainAll(Set<E> t) {
		for (E element : this) {
			if (!t.contains(element)) {
				remove(element);
			}
		}
	}

	@Override
	public void removeAll(Set<E> t) {
		if (!t.isEmpty())
			for (E element : t) {
				remove(element);
			}
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

}
