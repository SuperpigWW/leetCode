package assign04;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Utility class for solving the largest number problem.
 * Provides methods to find the largest number that can be formed by concatenating
 * integers from an array, as well as related operations like finding the k-th largest
 * number from multiple arrays, computing sums, and reading arrays from files.
 * Uses insertion sort with a custom comparator to arrange digits optimally.
 * 
 * @param <T> generic type parameter (not currently used in static methods)
 * 
 * @author Haoquan Wang and Zewei Ren
 * @version 2026.2.4
 */
public class LargestNumberSolver<T>{
	
	/**
	 * Creates a comparator for integers that determines which concatenation order
	 * produces a larger number.
	 * For two integers a and b, compares the numeric values of concatenations
	 * "ab" vs "ba" to determine optimal ordering for forming the largest number.
	 * 
	 * @return a Comparator that orders integers to maximize the concatenated result
	 */
	public static Comparator<Integer> createComparator() {
        return new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                String ab = a.toString() + b.toString();
                String ba = b.toString() + a.toString();
                return ba.compareTo(ab); 
            }
        };
    }
	
	/**
	 * Sorts an array using the insertion sort algorithm with a custom comparator.
	 * Insertion sort works by building a sorted section one element at a time,
	 * inserting each new element into its correct position within the sorted section.
	 * Time complexity: O(n^2) worst and average case, O(n) best case.
	 * Space complexity: O(1) - sorts in place.
	 * 
	 * @param <T> the type of elements in the array
	 * @param arr the array to be sorted
	 * @param cmp the comparator defining the sort order
	 */
	public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
        if (arr == null || cmp == null || arr.length <= 1) {
            return;
        }
        
        for (int i = 1; i < arr.length; i++) {
            T current = arr[i];
            int j = i - 1;
            while (j >= 0 && cmp.compare(arr[j], current) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = current;
        }
    }
	
	/**
	 * Finds the largest number that can be formed by concatenating all integers
	 * in the given array in optimal order using custom comparator and insertion sort.
	 * 
	 * @param arr array of integers to concatenate
	 * @return BigInteger representing the largest possible number, or 0 if array is null/empty
	 */
	public static BigInteger findLargestNumber(Integer[] arr) {
		if (arr == null || arr.length == 0)
			return BigInteger.valueOf(0);
		Integer[] copy = Arrays.copyOf(arr, arr.length);
        Comparator<Integer> comparator = createComparator();
        insertionSort(copy, comparator);
        
        if (copy[0] == 0) {
            return BigInteger.valueOf(0);
        }
        
        StringBuilder elementToStringBuilder = new StringBuilder();
        for (Integer num : copy) {
        	elementToStringBuilder.append(num);
        }
        return new BigInteger(elementToStringBuilder.toString());
	}
	
	/**
	 * Finds the largest number that can be formed from the array and returns it as an int.
	 * 
	 * @param arr array of integers to concatenate
	 * @return the largest number as an int
	 * @throws OutOfRangeException if the result exceeds Integer.MAX_VALUE
	 */
	public static int findLargestInt(Integer[] arr) {
		if (arr == null || arr.length == 0) 
            return 0;
		
		BigInteger largestBigInt = findLargestNumber(arr);
        
        BigInteger maxInt = BigInteger.valueOf(Integer.MAX_VALUE);
        
        if (largestBigInt.compareTo(maxInt) > 0) {
            throw new OutOfRangeException(
                "Number " + largestBigInt + 
                " exceeds maximum int value " + Integer.MAX_VALUE
            );
        }
        
        return largestBigInt.intValue();
	}
	
	/**
	 * Finds the largest number that can be formed from the array and returns it as a long.
	 * 
	 * @param arr array of integers to concatenate
	 * @return the largest number as a long
	 * @throws OutOfRangeException if the result exceeds Long.MAX_VALUE
	 */
	 public static long findLargestLong(Integer[] arr) throws OutOfRangeException {
	        if (arr == null || arr.length == 0) {
	            return 0L;
	        }
	        
	        BigInteger largestBigInt = findLargestNumber(arr);
	        
	        BigInteger maxLong = BigInteger.valueOf(Long.MAX_VALUE);
	        
	        if (largestBigInt.compareTo(maxLong) > 0) {
	            throw new OutOfRangeException(
	                "Number " + largestBigInt + 
	                " exceeds maximum long value " + Long.MAX_VALUE
	            );
	        }
	        
	        return largestBigInt.longValue();
	    }
	 
	/**
	 * Computes the sum of the largest numbers that can be formed from each array in the list.
	 * Finds optimal concatenation for each array, then sums all results.
 	 * 
	 * @param list a list of integer arrays
	 * @return BigInteger representing the sum of all largest numbers, or 0 if list is null
	 */
	  public static BigInteger sum(List<Integer[]> list) {
	        if (list == null) {
	            return BigInteger.ZERO;
	        }
	        
	        BigInteger total = BigInteger.ZERO;
	        
	        for (Integer[] arr : list) {
	            BigInteger largest = findLargestNumber(arr);
	            total = total.add(largest);
	        }
	        
	        return total;
	    }
	    
	/**
	 * Finds the k-th largest number from a list of integer arrays.
	 * Computes largest number for each array, sorts them, and returns the k-th largest.
	 * Complexity: O(m*n^2 + m^2) where m = list.size(), n = array length.
	 * 
	 * @param list a list of integer arrays to compare
	 * @param k the index of the desired largest number (0-indexed)
	 * @return the integer array that produces the k-th largest number
	 * @throws IllegalArgumentException if list is null or k is out of valid range
	 */
	  public static Integer[] findKthLargest(List<Integer[]> list, int k) {
		    if (list == null || k < 0 || k >= list.size()) {
		        throw new IllegalArgumentException(
		            "Invalid k value: " + k + 
		            ". Must be 0 <= k < " + (list == null ? "?" : list.size())
		        );
		    }
		    
		    BigInteger[] largestNumbers = new BigInteger[list.size()];
		    for (int i = 0; i < list.size(); i++) {
		        largestNumbers[i] = findLargestNumber(list.get(i));
		    }
		    
		    Integer[] indices = new Integer[list.size()];
		    for (int i = 0; i < list.size(); i++) {
		        indices[i] = i;
		    }
		    
		    insertionSort(indices, (i1, i2) -> 
		        largestNumbers[i2].compareTo(largestNumbers[i1])
		    );
		    
		    return list.get(indices[k]);
		}
	    
	/**
	 * Reads integer arrays from a file where each line contains space-separated integers.
	 * Each line becomes an array. Invalid integers become 0. Returns empty list if file not found.
	 * 
	 * @param filename the path to the file to read
	 * @return a list of integer arrays, one per line in the file
	 */
	    public static List<Integer[]> readFile(String filename) {
	        List<Integer[]> result = new ArrayList<>();
	        
	        File file = new File(filename);
	        if (!file.exists() || !file.canRead()) {
	            return result; 
	        }
	        
	        try (Scanner scanner = new Scanner(file)) {
	            while (scanner.hasNextLine()) {
	                String line = scanner.nextLine().trim();
	                if (line.isEmpty()) {
	                    continue; 
	                }
	                
	                String[] tokens = line.split("\\s+");
	                Integer[] arr = new Integer[tokens.length];
	                
	                for (int i = 0; i < tokens.length; i++) {
	                    try {
	                        arr[i] = Integer.parseInt(tokens[i]);
	                    } catch (NumberFormatException e) {
	                        arr[i] = 0; 
	                    }
	                }
	                
	                result.add(arr);
	            }
	        } catch (IOException e) {
	            return new ArrayList<>();
	        }
	        
	        return result;
	    }
}