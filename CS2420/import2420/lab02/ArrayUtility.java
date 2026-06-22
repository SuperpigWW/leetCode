package lab02;

/**
 * This class contains a generic method to compute the maximum element in an array.
 *
 * @author CS 2420 course staff
 * @version January 16, 2026
 */
public class ArrayUtility {

	/**
	 * Computes the maximum element in a given array.
	 * 
	 * @param array of elements
	 * @return maximum element in the array
	 * @throws IllegalArgumentException if given array is empty
	 */
	public static <E extends Comparable<? super E>> E computeMaximum(E[] array) throws IllegalArgumentException {
		if(array.length == 0) {
			throw new IllegalArgumentException("Maximum of empty array is not defined");
		}

		E maximum = array[0];
		for(int i = 1; i < array.length; i++) {
			if(array[i].compareTo(maximum) > 0) {
				maximum = array[i];
			}
		}

		return maximum;
	}
}