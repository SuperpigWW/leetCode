package lab07;

/**
 * This class contains a potentially buggy average method.
 * 
 * @author Prof. Parker
 * @version September 28, 2023
 */
public class Part2 {
	
	public static Fraction average(Fraction[] arr) {
		if(arr.length < 2)
			return null;
		Fraction sum = arr[0].add(arr[1]);
		for(int i = 2; i < arr.length - 1; i++)
			sum = sum.add(arr[i]);
		Fraction a = sum.multiply(new Fraction(1, arr.length));
		return a;
	}
}