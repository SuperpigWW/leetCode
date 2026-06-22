package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class SimplePriorityQueue<E> implements PriorityQueue<E>{
	
	private E[] array;
	private int size;
	private Comparator<? super E> comparator;
	
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue() {
		array = (E[]) new Object[16];
		comparator = null;
		size = 0;
	}
	
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue(Comparator<? super E> cmp) {
		array = (E[]) new Object[16];
		comparator = cmp;
		size = 0;
	}
	
	@SuppressWarnings("unchecked")
	private int compareElements(E lh, E rh) {
		if (comparator == null) {
			return ((Comparable<E>) lh).compareTo(rh);
		}
		else
			return comparator.compare(lh, rh);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void clear() {
		array = (E[]) new Object[16];
		size = 0;
	}
	
	@Override
	public boolean contains(E item) {
		int checkIndex = binarySearch(item);
		if (checkIndex == size)
			return false;
		return compareElements(array[checkIndex], item) == 0;
	}
	
	@Override
	public boolean containsAll(Collection<? extends E> coll) {
		for (E item : coll) {
			if (!contains(item))
				return false;
		}
		return true;
	}
	
	@Override
	public E deleteMax() throws NoSuchElementException{
		if (isEmpty())
			throw new NoSuchElementException("The queue is empty.");
		E max = array[size - 1];
		array[size - 1] = null;
		size -= 1;
		return max;
	}
	
	@Override
	public E findMax() throws NoSuchElementException{
		if (isEmpty())
			throw new NoSuchElementException("The queue is empty.");
		return array[size - 1];
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void insert(E item) {
		if (size == array.length) {
			E[] biggerArray = (E[]) new Object[array.length * 2];
			for(int i = 0; i < size; i++) {
				biggerArray[i] = array[i];
			}
			array = biggerArray;
		}
		int insertIndex = binarySearch(item) + 1;
		for (int i = size - 1; i >= insertIndex; i --) {
			array[i + 1] = array[i];
		}
		size += 1;
		array[insertIndex] = item;
	}
	
	@Override
	public void insertAll(Collection<? extends E> coll) {
		for (E item : coll) {
			insert(item);
		}
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	private int binarySearch(E target) {
		int left = 0;
		int right = size - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (compareElements(array[mid], target) == 0)
				return mid;
			else if (compareElements(array[mid], target) < 0)
				left = mid + 1;
			else
				right = mid - 1;
		}
		return left;
	}

}
