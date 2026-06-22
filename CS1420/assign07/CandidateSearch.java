package assign07;

import java.util.Arrays;

/**
 * This class provides recursive search algorithms for finding Candidate objects in arrays.
 * It includes both sequential search and binary search implementations, with call counting
 * functionality to track recursive call performance.
 * 
 * @author Haoquan Wang
 * @version 10/23/2025
 */
public class CandidateSearch {
	private static int counter;
	
	/**
	 * Returns the number of recursive calls made during the most recent search operation.
	 * This counter is reset at the beginning of each search method call.
	 * 
	 * @return the number of recursive calls made in the last search operation
	 */
	public static int getCallCount() {
		return counter;
	}
	
	/**
	 * Performs a sequential search for the target Candidate in the given array using recursion.
	 * The search starts from the beginning of the array and checks each element in order
	 * until the target is found or the end of the array is reached.
	 * 
	 * @param array the array of Candidate objects to search through
	 * @param target the Candidate object to search for
	 * @return the found Candidate object from the array that is equivalent to the target,
	 *         or null if no equivalent Candidate is found
	 * @throws IllegalArgumentException if the array is null, empty, or target is null
	 */
	public static Candidate sequentialSearch(Candidate[] array, Candidate target) {
		counter = 0;
		if (array == null || array.length == 0 || target == null)
			return null;
		return sequentialSearchRecursive(array, target, 0);
		
	}
	
	/**
	 * Private recursive helper method that performs the actual sequential search.
	 * This method increments the call counter with each recursive call.
	 * 
	 * @param array the array of Candidate objects to search through
	 * @param target the Candidate object to search for
	 * @param index the current index being examined in the array
	 * @return the found Candidate object from the array that is equivalent to the target,
	 *         or null if no equivalent Candidate is found
	 */
	private static Candidate sequentialSearchRecursive(Candidate[] array, Candidate target, int index) {
		counter ++;
		if (index == array.length)
			return null;
		if (array[index].compareTo(target) == 0)
			return array[index];
		else
			return sequentialSearchRecursive(array, target, index + 1);
		
	}
	
	/**
	 * Performs a binary search for the target Candidate in the given array using recursion.
	 * The array is first sorted using Arrays.sort() to ensure proper binary search functionality.
	 * Binary search requires the array to be sorted and provides O(log n) time complexity.
	 * 
	 * @param array the array of Candidate objects to search through
	 * @param target the Candidate object to search for
	 * @return the found Candidate object from the array that is equivalent to the target,
	 *         or null if no equivalent Candidate is found
	 * @throws IllegalArgumentException if the array is null, empty, or target is null
	 */
	public static Candidate binarySearch(Candidate[] array, Candidate target) {
		counter = 0;
		Arrays.sort(array);
		return binarySearchRecursive(array, target, 0, array.length - 1);
	}
	
	/**
	 * Private recursive helper method that performs the actual binary search.
	 * This method increments the call counter with each recursive call and implements
	 * the standard binary search algorithm by repeatedly dividing the search interval in half.
	 * 
	 * @param array the sorted array of Candidate objects to search through
	 * @param target the Candidate object to search for
	 * @param low the lower bound index of the current search interval (inclusive)
	 * @param high the upper bound index of the current search interval (inclusive)
	 * @return the found Candidate object from the array that is equivalent to the target,
	 *         or null if no equivalent Candidate is found in the current search interval
	 */
	private static Candidate binarySearchRecursive(Candidate[] array, Candidate target, int low, int high) {
		counter ++; 
		
	    if (low > high)
	        return null;
	        
	    int mid = low + (high - low) / 2;
	    int comparison = array[mid].compareTo(target);
	    
	    if (comparison == 0)
	        return array[mid];
	    else if (comparison < 0)
	        return binarySearchRecursive(array, target, mid + 1, high);
	    else
	        return binarySearchRecursive(array, target, low, mid - 1);
	}
}

