package lab07;

/**
 * This class represents a fraction; e.g., 1/2.
 * 
 * @author Prof. Parker
 * @version September 28, 2023
 */
public class Fraction {

	private int numerator;
	private int denominator;

	/**
	 * This constructor builds a Fraction object, given a numerator and denominator.
	 * 
	 * @param numerator - value for initializing the numerator
	 * @param denominator - value for initializing the denominator
	 */
	public Fraction(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	/**
	 * This method computes the GCD of this Fraction object's numerator and
	 * denominator.
	 * 
	 * @return the GCD
	 */
	private int gcd() {
		int a = this.numerator;
		int b = this.denominator;

		while(b != 0) {
			int t = b;
			b = a % b;
			a = t;
		}

		return a;
	}

	/**
	 * This method reduces this Fraction object to its simplest form.
	 */
	public void reduce() {
		int divisor = gcd();
		this.numerator /= divisor;
		this.denominator /= divisor;
	}
	
	/**
	 * This method calculates the decimal-point equivalent of this Fraction object.
	 * 
	 * @return the decimal-point equivalent
	 */
	public double toDouble() {
		return numerator / (double) denominator;
	}

	/**
	 * This method generates a textual representation of this Fraction object.
	 * 
	 * @return a string containing the textual representation
	 */
	public String toString() {
		return this.numerator + "/" + this.denominator;
	}

	/**
	 * This method determines whether this Fraction object and the given object are
	 * equal.
	 * 
	 * Note: If this Fraction object and the other Fraction object are not already
	 * in their simplest forms, they will be after this method returns.
	 * 
	 * @param other - the other object to compare
	 * @return true if the two objects are equal, false otherwise
	 */
	public boolean equals(Object other) {
		if(!(other instanceof Fraction))
			return false;
		Fraction otherFraction = (Fraction) other;

		Fraction thisCopy = this.copyOf();
		Fraction otherCopy = otherFraction.copyOf();

		thisCopy.reduce();
		otherCopy.reduce();

		return thisCopy.numerator == otherCopy.numerator && thisCopy.denominator == otherCopy.denominator;
	}

	/**
	 * This method creates a new, distinct object with data copied from this
	 * Fraction object.
	 * 
	 * @return a copy of this Fraction object
	 */
	public Fraction copyOf() {
		Fraction copy = new Fraction(this.numerator, this.denominator);
		return copy;
	}

	/**
	 * This method multiplies this Fraction object and the given Fraction object.
	 * 
	 * @param other - the given Fraction object
	 * @return the product of this Fraction object and other
	 */
	public Fraction multiply(Fraction other) {
		Fraction product = new Fraction(this.numerator * other.numerator, this.denominator * other.denominator);
		product.reduce();
		return product;
	}

	/**
	 * This method adds this Fraction object and the given Fraction object.
	 * 
	 * @param other - the given Fraction object
	 * @return the sum of this Fraction object and other
	 */
	public Fraction add(Fraction other) {
		Fraction sum = new Fraction(this.numerator * other.denominator + this.denominator * other.numerator,
				this.denominator * other.denominator);
		sum.reduce();
		return sum;
	}
}