package assign10;

import java.util.Comparator;
import java.util.List;

/**
 * This class contains generic static methods for sorting a descending delta-sorted list.
 * The result of both methods is that the list will be in descending order.
 * 
 * @author CS 2420 course staff and Haoquan Wang and Jake Scott
 * @version 2026-4-6
 */
public class DeltaSorter {
	/**
	 * Fully sorts a descending, delta-sorted list using a BinaryMaxHeap.
	 * After completing, the provided list will contain the same items in descending order.
	 * This version uses the natural ordering of the elements.
	 * 
	 * @param list to sort that is currently delta-sorted and will be fully sorted
	 * @param delta value of the delta-sorted list
	 * @throws IllegalArgumentException if delta is less than 0 or greater than or
	 *         equal to the size of the list
	 */
	public static <T extends Comparable<? super T>> void sort(List<T> list, int delta){
		if (delta < 0 || (list.size() > 0 && delta >= list.size())) {
	        throw new IllegalArgumentException("Please input a valid delta");
	    }
	    if (list.size() <= 1) return;

	    BinaryMaxHeap<T> heap = new BinaryMaxHeap<>();

	    for (int i = 0; i < delta; i++) {
	        heap.add(list.get(i));
	    }

	    int listIndex = 0;
	    for (int i = delta; i < list.size(); i++) {
	        heap.add(list.get(i));
	        list.set(listIndex++, heap.extractMax());
	    }

	    while (!heap.isEmpty()) {
	        list.set(listIndex++, heap.extractMax());
	    }
	}
	
	/**
	 * Fully sorts a descending, delta-sorted list using a BinaryMaxHeap.
	 * After completing, the provided list will contain the same items in descending order.
	 * This version uses a provided comparator to order the elements.
	 * 
	 * @param list to sort that is currently delta-sorted and will be fully sorted
	 * @param delta value of the delta-sorted list
	 * @param cmp Comparator for ordering the elements
	 * @throws IllegalArgumentException if delta is less than 0 or greater than or
	 *         equal to the size of the list
	 */
	public static <T> void sort(List<T> list, int delta, Comparator<? super T> cmp){
		if (delta < 0 || (list.size() > 0 && delta >= list.size())) {
	        throw new IllegalArgumentException("Please input a valid delta");
	    }
	    if (list.size() <= 1) return;

	    BinaryMaxHeap<T> heap = new BinaryMaxHeap<>(cmp);

	    for (int i = 0; i < delta; i++) {
	        heap.add(list.get(i));
	    }

	    int listIndex = 0;
	    for (int i = delta; i < list.size(); i++) {
	        heap.add(list.get(i));
	        list.set(listIndex++, heap.extractMax());
	    }

	    while (!heap.isEmpty()) {
	        list.set(listIndex++, heap.extractMax());
	    }
	}
}