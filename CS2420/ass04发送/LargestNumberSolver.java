package assign04;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;

public class LargestNumberSolver<T>{
	
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
	
	public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
        if (arr == null || cmp == null || arr.length <= 1) {
            return;
        }
        
        for (int i = 1; i < arr.length; i++) {
            T current = arr[i];
            int j = i - 1;
            while (j >= 0 && cmp.compare(arr[j], current) < 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = current;
        }
    }
	
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
