package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * A maximum priority queue implemented using dynamic array.
 * 
 * @author CS 2420 course staff and Haoquan Wang and Zewei Ren
 * @version 2026-01-29
 */
public class SimplePriorityQueue<E> implements PriorityQueue<E>{
	
	private E[] array;
	private int size;
	private Comparator<? super E> comparator;
	
	/**
	 * Construct an empty priority queue.
	 */
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue() {
		array = (E[]) new Object[16];
		comparator = null;
		size = 0;
	}
	
	/**
	 * Create an empty priority queue and use the specified comparator for comparison.
	 * 
	 * @param cmp A comparator used for comparing magnitudes
	 */
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue(Comparator<? super E> cmp) {
		array = (E[]) new Object[16];
		comparator = cmp;
		size = 0;
	}
	/**
	 * Compare the sizes of two elements.If there is a comparator, then use it.
	 * 
	 * @param lh left element
	 * @param rh right element
	 * @return if lh lower than rh return negative,if lh bigger than rh than return positive,equals return 0
	 */
	@SuppressWarnings("unchecked")
	private int compareElements(E lh, E rh) {
		if (comparator == null) {
			return ((Comparable<E>) lh).compareTo(rh);
		}
		else
			return comparator.compare(lh, rh);
	}
	/**
	 * Remove all elements from the array
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void clear() {
		array = (E[]) new Object[16];
		size = 0;
	}
	/**
	 * Determine whether the specified element exists in the priority queue.
	 * @param item The element to be searched for
	 * @return If it includes return true, it does not include return false.
	 */
	@Override
	public boolean contains(E item) {
		int checkIndex = binarySearch(item);
		if (checkIndex == size)
			return false;
		return compareElements(array[checkIndex], item) == 0;
	}
	/**
	 * Determine whether all the elements in the specified set are present in the priority queue.
	 * @param coll The set of elements to be checked
	 * @return If it all includes return true, it does not include return false.
	 */
	@Override
	public boolean containsAll(Collection<? extends E> coll) {
		for (E item : coll) {
			if (!contains(item))
				return false;
		}
		return true;
	}
	/**
	 * Delete and return the largest element in the priority queue
	 * @return The largest element
	 * @throws NoSuchElementException When the set is empty throw the exception
	 */
	@Override
	public E deleteMax() throws NoSuchElementException{
		if (isEmpty())
			throw new NoSuchElementException("The queue is empty.");
		E max = array[size - 1];
		array[size - 1] = null;
		size -= 1;
		return max;
	}
	/**
	 * Return the largest element in the priority queue
	 * @return the largest element
	 * @throws NoSuchElementException When the set is empty throw the exception
	 */
	@Override
	public E findMax() throws NoSuchElementException{
		if (isEmpty())
			throw new NoSuchElementException("The queue is empty.");
		return array[size - 1];
	}
	/**
	 * Insert an element into the priority queue
	 * @param iterm The inserted element
	 */
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
		int insertIndex = binarySearch(item);
		for (int i = size - 1; i >= insertIndex; i --) {
			array[i + 1] = array[i];
		}
		size += 1;
		array[insertIndex] = item;
	}
	/**
	 * Insert all the elements of the specified set into the priority queue.
	 * @coll The set of elements to be insert
	 */
	@Override
	public void insertAll(Collection<? extends E> coll) {
		for (E item : coll) {
			insert(item);
		}
	}
	/**
	 * Check whether the priority queue is empty
	 * @return Return true when there are no elements; otherwise, return false.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	/**
	 * Return the number of elements in the priority queue
	 * @return the number of elements in the priority queue
	 */
	@Override
	public int size() {
		return size;
	}
	/**
	 * Perform binary search in the currently sorted array
	 * @param target The target element to be searched for
	 * @return the index that is closest to the one found.
	 */
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
