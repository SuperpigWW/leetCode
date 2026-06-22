package lab07;

/**
 * This class contains a very buggy rangeSize method.
 * 
 * @author Prof. Heisler
 * @version September 28, 2023
 */
public class Part5 {
	/**
	 * This method calculates the range size for a collection of Fractions.
	 * The range size is the difference between the greatest and least values.
	 * For example, the range size of [1/4, 4/5, 1/2] is 4/5 - 1/4 = 11/20.
	 * The result is another Fraction object that is returned.
	 * 
	 * @param arr - array of Fractions
	 * @return the range size Fraction
	 */
	public static Fraction rangeSize(Fraction[] arr) {
		if(arr.length == 0)
			return new Fraction(0, 1);
		Fraction min = minimum(arr, 0);
		Fraction max = maximum(arr, 0);
		// returns max + (-1 * min) which is max - min
		return max.add(min.multiply(new Fraction(-1, 0)));
	}
	
	/**
	 * This recursive method returns the minimum Fraction in the array
	 * beginning at the given index.
	 * 
	 * @param arr - array of Fractions
	 * @param index - starting index to check from
	 * @return minimum fraction
	 */
	private static Fraction minimum(Fraction[] arr, int index) {
		if(index < arr.length)
			return arr[index];
		
		Fraction followingMin = minimum(arr, index++);
		if(arr[index].toDouble() < followingMin.toDouble())
			return arr[index];
		return followingMin;
	}
	
	/**
	 * This recursive method returns the maximum Fraction in the array
	 * beginning at the given index.
	 * 
	 * @param arr - array of Fractions
	 * @param index - starting index to check from
	 * @return maximum fraction
	 */
	private static Fraction maximum(Fraction[] arr, int index) {
		if(index < arr.length)
			return arr[index];
		
		Fraction followingMax = maximum(arr, index++);
		if(arr[index].toDouble() < followingMax.toDouble())
			return arr[index];
		return followingMax;
	}
}