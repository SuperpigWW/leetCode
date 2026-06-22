package test;

public class teeee {
	public static int countOccurrences(char letter, int index) {
		char[] letters = new char[] {'a', 'b'};
		int countOccurrences = 0;
		if (index >= letters.length) 
	        return 0;
		if (index < 0)
			throw new IllegalArgumentException ("Index out of range");
		if ((letter >= 'A' && letter <= 'Z') || letter >= 'a' && letter <= 'z'){
			if (letters[index] == letter)
				countOccurrences = 1;
		}
		else
			throw new IllegalArgumentException ("Please enter letters only.");
		return countOccurrences + countOccurrences(letter, index + 1);
	}
	public static void main(String[] args) {
		System.out.printf(countOccurrences('a', 0) + "");

	}

}
