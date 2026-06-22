package lab07;

/**
 * This class contains a potentially buggy average method.
 * 
 * @author Prof. Parker
 * @version September 28, 2023
 */
public class Part4 {
	
	public static Fraction average(Fraction[] arr) {
		if(arr.length == 0)
			return null;
		if(arr.length == 1)
			return arr[0];
		Fraction sum = arr[0].add(arr[1]);
		for(int i = 2; i < arr.length; i++)
			sum = sum.add(arr[i]);
		return sum.multiply(new Fraction(1, arr.length));
	}
}