package assign07;


public class CandidateSearch {
	private static int counter;
	public static int getCallCount() {
		return counter;
	}
	public static Candidate sequentialSearch(Candidate[] array, Candidate target) {
		counter = 0;
		if (array == null || array.length == 0 || target == null)
			throw new IllegalArgumentException("Try input a new array or target");
		return sequentialSearchRecursive(array, target, 0);
		
	}
	private static Candidate sequentialSearchRecursive(Candidate[] array, Candidate target, int index) {
		counter += 1;
		if (index == array.length)
			return null;
		if (array[index].compareTo(target) == 0)
			return array[index];
		else
			return sequentialSearchRecursive(array, target, index + 1);
		
	}
	
	public static Candidate binarySearch(Candidate[] array, Candidate target) {
		counter = 0;
		return binarySearchRecursive(array, target, array.length - 1, 0);
	}
	private static Candidate binarySearchRecursive(Candidate[] array, Candidate target, int low, int high) {
	
		int mid = low + (high - low) / 2;
		if 
		while(high >= low) {
			counter += 1;
			if(array[mid].compareTo(target) == 0) {
				return array[mid];
			}
			if(array[mid].compareTo(target) < 0) {
				low = mid + 1;
				mid = low + (high - low) / 2;
			}
			else {
				high = mid - 1;
				mid = low + (high - low) / 2;
		}
		return binarySearchRecursive(array, target, high, low);
	}

}
