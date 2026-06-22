package assign07;

/**
 * This represents a candidate for a job opening.
 * Candidates have a name, age, and a rating (1-10) based on their application.
 * 
 * @author CS 1420 course staff and UPDATE WITH YOUR NAME
 * @version UPDATE WITH MOST RECENT DATE
 */
public class Candidate implements Comparable<Candidate>{
	private String name;
	private int age;
	private int rating;
	
	/**
	 * This constructor initializes all instance variables using the given data.
	 * 
	 * @param name of candidate
	 * @param age of candidate
	 * @param rating from 1 to 10 based on their application
	 * @throws IllegalArgumentException if age is negative or rating is out of range
	 */
	public Candidate(String name, int age, int rating) {
		if (age < 0 || rating > 10)
			throw new IllegalArgumentException("Age is negative or rating is out of range.");
		this.name = name;
		this.age = age;
		this.rating = rating;
	}
	
	/**
	 * This constructor initializes name and age using the given data.
	 * Rating is set to 1 by default.
	 * 
	 * @param name of candidate
	 * @param age of candidate
	 * @throws IllegalArgumentException if age is negative
	 */
	public Candidate(String name, int age) {
		if (age < 0)
			throw new IllegalArgumentException("Age out of range.");
		this.name = name;
		this.age = age;
		this.rating = 1;
	}
	
	/**
	 * Get the rating for this candidate.
	 * 
	 * @return rating from 1 to 10
	 */
	public int getRating() {
		return this.rating;
	}
	
	/**
	 * Compares this object to another Candidate object.
	 * The comparison is primarily based on name. Use name's compareTo method, which also returns 
	 * an int matching the requirements of the return value for this method. If this and the other 
	 * object have equal names, use age to break ties. A smaller age means an object comes before 
	 * the other. If both name and age are equal, the objects are considered equal.
	 * 
	 * @param other Candidate to compare this to
	 * @return an int that is negative if this object comes before (is less than) other,
	 *                        positive if this object comes after (is greater than) other, or
	 *                        zero if this object is equal to other
	 * NOTE: The value of the returned int is not important. Any value that is
	 *       correctly positive, negative, or zero is alright.
	 */
	public int compareTo(Candidate other) {
		int result;
		result = this.name.compareTo(other.name);
		if (result != 0)
			return result;
		else {
			if (this.age < other.age)
				return -1;
			if (this.age > other.age)
				return 1;
			if (this.age == other.age)
				return 0;
		}
	}
	
	/**
	 * Is this object equal to the other?
	 * 
	 * @param other object to compare this to
	 * @return true if other is a Candidate with equal name and age, false otherwise
	 */
	public boolean equals(Object other) {
		Candidate otherCandidate = (Candidate)other;
		if (this.name.compareTo(otherCandidate.name) == 0 && this.age == otherCandidate.age)
			return true;
		else
			return false;
	}
	
	/**
	 * A candidate is represented by a String like NAME(AGE).
	 * For example, if name is "Zoey" and age is 21, this returns "Zoey(21)".
	 * 
	 * @return a text representation of this Candidate
	 */
	public String toString() {
		return (this.name + "(" + this.age + ")");
	}
}
