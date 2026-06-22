package lab11;

/**
 * This class represents a phone number.
 * 
 * E.g., for phone number 801-581-8224, the areaCode is 801, the prefix is 581,
 * and the lineNumber is 8224.
 * 
 * @author CS 2420 course staff
 * @version March 27, 2026
 */
public class PhoneNumber implements Comparable<PhoneNumber> {
	private String areaCode;
	private String prefix;
	private String lineNumber;

	/**
	 * Creates a phone number object from given strings for each part.
	 * 
	 * @param areaCode
	 * @param prefix
	 * @param lineNumber
	 */
	public PhoneNumber(String areaCode, String prefix, String lineNumber) {
		this.areaCode = areaCode;
		this.prefix = prefix;
		this.lineNumber = lineNumber;
	}

	/**
	 * Creates a phone number object by parsing each of the three parts from one
	 * "row" of a CSV file. In a such file, field are separated by commas (e.g.,
	 * "801,581,8224).
	 * 
	 * @param rowOfCsvFile - string to be parsed
	 * @throws IllegalArgumentException if CSV file is not formatted as expected
	 */
	public PhoneNumber(String rowOfCsvFile) throws IllegalArgumentException {
		this.areaCode = "";
		this.prefix = "";
		this.lineNumber = "";

		String errorMsg = "CSV file must be 3-digit area code, 3-digit prefex, 4-digit line number.";
		
		try {
			this.areaCode = rowOfCsvFile.substring(0, 3);
			this.prefix = rowOfCsvFile.substring(4, 7);
			this.lineNumber = rowOfCsvFile.substring(8, 12);
		} catch (IndexOutOfBoundsException e) {
			throw new IllegalArgumentException(errorMsg);
		}

		if (rowOfCsvFile.charAt(3) != ',' || rowOfCsvFile.charAt(7) != ',')
			throw new IllegalArgumentException(errorMsg);
	}

	/**
	 * Two phone numbers are considered equal if they have the same parts.
	 * 
	 * @param other - object begin compared with this phone number
	 * @return true if other object is a PhoneNumer type and is equal to this phone
	 *         number, false otherwise
	 */
	public boolean equals(Object other) {
		if (!(other instanceof PhoneNumber))
			return false;

		PhoneNumber rhs = (PhoneNumber) other;
		PhoneNumber lhs = this;

		return lhs.areaCode.equals(rhs.areaCode) && lhs.prefix.equals(rhs.prefix) && lhs.lineNumber.equals(rhs.lineNumber);
	}

	/**
	 * This phone number is "smaller" than the other if its full 10-character string
	 * comes lexicographically before other's. E.g., "1234567890" is "smaller" than
	 * "1244567890"
	 * 
	 * @return positive value if this phone number is larger, negative value if this 
	 *         phone number is smaller, 0 otherwise
	 */
	public int compareTo(PhoneNumber other) {
		String thisPhoneNum = this.areaCode + this.prefix + this.lineNumber;
		String otherPhoneNum = other.areaCode + other.prefix + other.lineNumber;

		return thisPhoneNum.compareTo(otherPhoneNum);
	}

	/**
	 * Generates a hash code for this phone number (used by HashMap and other
	 * hash table implementations).
	 * 
	 * As required, two equal phone numbers have the same hash code.
	 * 
	 * @return hash code of this phone number
	 */
	public int hashCode() {
		return Integer.parseInt(this.areaCode) * 10000 + Integer.parseInt(this.prefix) * 
				100 + Integer.parseInt(this.lineNumber);
	}

	/**
	 * Generates a textual representation of this phone number.
	 * 
	 * @return formatted string for this phone number
	 */
	public String toString() {
		return "(" + this.areaCode + ") " + prefix + "-" + this.lineNumber;
	}
}