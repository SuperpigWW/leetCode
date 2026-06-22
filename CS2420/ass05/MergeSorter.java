package assign05;

import java.util.ArrayList;

/**
 * Sorts an ArrayList using merge sort, switching to insertion sort
 * when the sublist size falls at or below a given threshold.
 *
 * @author Haoquan Wang and Zewei Rem
 * @version 2026-2-18
 */
public class MergeSorter<E extends Comparable<? super E>> implements Sorter<E>{
	
	private int threshold;
	
	/**
	 * Constructs a MergeSorter with the specified insertion sort threshold.
	 *
	 * @param threshold - sublist size at or below which insertion sort is used
	 * @throws IllegalArgumentException if threshold is not positive
	 */
	public MergeSorter(int threshold) {
		if (threshold <= 0)
			throw new IllegalArgumentException("The size of the imput array is not positive.");
		this.threshold = threshold;
	}

	/**
	 * Sorts the given list in ascending order using merge sort.
	 * Switches to insertion sort for sublists at or below the threshold.
	 *
	 * @param list - the list to be sorted
	 */
	@Override
	public void sort(ArrayList<E> list) {
	    if (list == null || list.size() <= 1)
	        return;
	    ArrayList<E> aux = new ArrayList<>(list); 
	    mergesort(list, 0, list.size() - 1, aux);
	}

	/**
	 * Recursively sorts the sublist from left to right using merge sort.
	 * Switches to insertion sort when the sublist size is at or below the threshold.
	 *
	 * @param arr   - the list being sorted
	 * @param left  - start index of the sublist
	 * @param right - end index of the sublist
	 * @param aux   - pre-allocated auxiliary list used during merging
	 */
	private void mergesort(ArrayList<E> arr, int left, int right, ArrayList<E> aux) {
	    if (right - left + 1 <= threshold) {
	        insertionsort(arr, left, right);
	        return;
	    }
	    int mid = left + (right - left) / 2; 
	    mergesort(arr, left, mid, aux);
	    mergesort(arr, mid + 1, right, aux);
	    merge(arr, left, mid + 1, right, aux);
	}
	
	/**
	 * Merges two adjacent sorted sublists into a single sorted sublist.
	 *
	 * @param arr   - the list containing both sublists
	 * @param left  - start index of the left sublist
	 * @param mid   - start index of the right sublist
	 * @param right - end index of the right sublist
	 * @param aux   - auxiliary list used as temporary storage
	 */
	private void merge(ArrayList<E> arr, int left, int mid, int right, ArrayList<E> aux) {
		int i = left;
		int j = mid;
		int k = left;
		while(i < mid && j <= right) {
			if (arr.get(i).compareTo(arr.get(j)) <= 0) {
				aux.set(k, arr.get(i));
				i ++;
			}
			else {
				aux.set(k, arr.get(j));
				j ++;
			}
			k ++;
		}
		
		while (i < mid) {
	        aux.set(k, arr.get(i));
	        i++;
	        k++;
	    }
	    while (j <= right) {
	        aux.set(k, arr.get(j));
	        j++;
	        k++;
	    }
	    
	    for (int index = left; index <= right; index++) {
	        arr.set(index, aux.get(index));
	    }
	}
	
	/**
	 * Sorts the sublist from left to right using insertion sort.
	 *
	 * @param arr   - the list containing the sublist
	 * @param left  - start index of the sublist
	 * @param right - end index of the sublist
	 */
	private void insertionsort(ArrayList<E> arr, int left, int right) {
		for (int i = left + 1; i <= right; i++) {
			E val = arr.get(i);
			int j;
			for	(j = i -1; j >= left && arr.get(j).compareTo(val) > 0; j --)
				arr.set(j + 1, arr.get(j));
			arr.set(j + 1, val);
		}
	}

}