package lab05;

public class TestingPractice {
	/**
	 * Creates a string containing a subsequence of the char array.
	 * The subsequence is between the begin and end indices, inclusive.
	 * If either index is invalid, an empty string is returned.
	 * An invalid index is when:
	 *  - The index is less than 0
	 *  - The index is greater than the last index in the array
	 *  - The end index is less than the begin index
	 * 
	 * @param array of chars
	 * @param begin index of subsequence
	 * @param end   index of subsequence
	 * @return String containing the subsequence of chars
	 */
	public static String subsequenceString(char[] array, int begin, int end) {
	    if (begin < 0 || end >= array.length || end < begin)
	        return "";
	    String result = "";
	    for (int i = begin; i <= end; i++)
	        result += array[i];
	     return result;
	}

}
