package assign05;

import java.util.ArrayList;

public class MergeSorter<E extends Comparable<? super E>> implements Sorter<E>{
	
	private int threshold;
	
	public MergeSorter(int threshold) {
		if (threshold <= 0)
			throw new IllegalArgumentException("The size of the imput array is not positive.");
		this.threshold = threshold;
	}

	@Override
	public void sort(ArrayList<E> list) {
		if (list.size() <= 1) {
		    return; 
		}
		ArrayList<E> auxiliarylist = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
	        auxiliarylist.add(null);
	    }
		int realThreshold;
		if (list.size() <= threshold)
			realThreshold = list.size();
		else
			realThreshold = this.threshold;
		mergesort(list, 0, list.size() - 1, auxiliarylist, realThreshold);
	}
	
	private void mergesort(ArrayList<E> arr, int left, int right, ArrayList<E> aux, int realThreshold) {
		if (left < right) {
			if (right - left + 1 < realThreshold) {
				insertionsort(arr, left, right);
				return;
			}
			int mid = (left + right) / 2;
			mergesort(arr, left, mid, aux, realThreshold);
			mergesort(arr, mid + 1, right, aux, realThreshold);
			merge(arr, left, mid + 1, right, aux);
		}
	}
	
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
