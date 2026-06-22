package assign05;

import java.util.ArrayList;

/**
 * Sorts an ArrayList using quicksort with a pluggable pivot selection strategy.
 *
 * @author Haoquan Wang and Zewei Rem
 * @version 2026-2-18
 */
public class QuickSorter<E extends Comparable<? super E>> implements Sorter<E> {
	
	private PivotChooser<E> chooser;
	
	/**
	 * Constructs a QuickSorter with the specified pivot selection strategy.
	 *
	 * @param chooser - the pivot chooser used to select a pivot element
	 */
	public QuickSorter(PivotChooser<E> chooser) {
		this.chooser = chooser;
	}

	/**
	 * Sorts the given list in ascending order using quicksort.
	 *
	 * @param list - the list to be sorted
	 */
	@Override
	public void sort(ArrayList<E> list) {
		if (list == null || list.size() <= 1) 
			return;
		quickSort(list, 0, list.size() - 1);
	}
	
	/**
	 * Recursively sorts the sublist from left to right using quicksort.
	 *
	 * @param arr   - the list being sorted
	 * @param left  - start index of the sublist
	 * @param right - end index of the sublist
	 */
	private void quickSort(ArrayList<E> arr, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(arr, left, right);
            
            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
    }

	/**
	 * Partitions the sublist around a pivot so that elements less than the pivot
	 * come before it and elements greater come after it.
	 *
	 * @param arr   - the list being partitioned
	 * @param left  - start index of the sublist
	 * @param right - end index of the sublist
	 * @return the final index of the pivot element
	 */
	private int partition(ArrayList<E> arr, int left, int right) {
	    int pivotIndex = chooser.getPivotIndex(arr, left, right);
	    E pivotVal = arr.get(pivotIndex);
	    arr.set(pivotIndex, arr.get(right));
	    arr.set(right, pivotVal);

	    int i = left;      
	    int j = right - 1; 

	    while (i <= j) {
	        while (i <= j && arr.get(i).compareTo(pivotVal) < 0) {
	            i++;
	        }
	        while (i <= j && arr.get(j).compareTo(pivotVal) > 0) {
	            j--;
	        }

	        if (i <= j) {
	            E temp = arr.get(i);
	            arr.set(i, arr.get(j));
	            arr.set(j, temp);
	            i++;
	            j--;
	        }
	    }

	    E finalTemp = arr.get(i);
	    arr.set(i, arr.get(right));
	    arr.set(right, finalTemp);

	    return i;
	}

}