package assign06;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * This class contains unit tests to check the correctness of the Word class.
 * 
 * @author CS 1420 course staff and UPDATE WITH YOUR NAME
 * @version UPDATE WITH MOST RECENT DATE
 */
public class WordTest {
	// Provided
	@Test
	public void testFirstConstructorException() {
		assertThrows(IllegalArgumentException.class, () -> { new Word("hel!o"); });
	}
	
	// Provided
	@Test
	public void testSecondConstructorException() {
		assertThrows(IllegalArgumentException.class, () -> 
			{ new Word(new char[] { 'W', 'h', 'o', '?' }); });
	}
	
	// Provided
	@Test
	public void testToStringNormal() {
		Word normal = new Word("Normal");
		assertEquals("Normal", normal.toString());
	}
	
	// New
	@Test
    public void testEmptyString() {
        Word empty = new Word("");
        assertEquals("", empty.toString());
    }
	
	// New
	@Test
    public void testSingleCharacter() {
        Word single = new Word("a");
        assertEquals("a", single.toString());
    }
	
	// New
	@Test
    public void testStringWithSpaces() {
		assertThrows(IllegalArgumentException.class, () -> 
		{ new Word("hello world"); });
    }

	// Provided
	@Test
	public void testCountOccurrencesOneLetter() {		
		Word oneLetter = new Word("a");
		assertEquals(1, oneLetter.countOccurrences('a'));
	}
	
	// Provided
	@Test
	public void testIsCountOccurrencesMultiple() {
		Word multiplePs = new Word("saippuakivikauppias");
		assertEquals(4, multiplePs.countOccurrences('p'));
	}

	
	// New
	@Test
	public void testCaseSensitivity() {
		Word caseCS = new Word("AAAAaAAAA");
		assertEquals(1, caseCS.countOccurrences('a'));
	}
	
	// New
	@Test
	public void testLetterNotExit() {
		Word notExit = new Word("AAAA");
		assertEquals(0, notExit.countOccurrences('a'));
	}
	
	// New
	@Test
	public void testNotLetter() {
		Word notLetter = new Word("AAAA");
		assertThrows(IllegalArgumentException.class, () -> {notLetter.countOccurrences('0');});
	}

	// Provided
	@Test
	public void testReplaceLastOccurrenceExceptionFirstArgument() {
		Word oneLetter = new Word("a");
     	assertThrows(IllegalArgumentException.class, () -> { oneLetter.replaceLastOccurrence(' ', 'l'); });
	}
	
	// Provided
	@Test
	public void testReplaceLastOccurrenceHello() {
		Word hello = new Word("hello");
		hello.replaceLastOccurrence('l', 's');
		assertEquals("helso", hello.toString());
	}
	
	// New
	@Test
	public void testReplaceLastOccurrenceSingleCharNoMatch() {
	    Word single = new Word("a");
	    single.replaceLastOccurrence('b', 'c');
	    assertEquals("a", single.toString());
	}
	
	// New
	@Test
	public void testReplaceLastOccurrenceAllSameChars() {
	    Word allSame = new Word("aaaa");
	    allSame.replaceLastOccurrence('a', 'x');
	    assertEquals("aaax", allSame.toString());
	}
	
	// New
	@Test
	public void testReplaceLastOccurrenceExceptionReplacementArgument() {
		Word oneLetter = new Word("a");
     	assertThrows(IllegalArgumentException.class, () -> { oneLetter.replaceLastOccurrence('0', 'b'); });
	}
	
	// New
	@Test
	public void testReplaceLastOccurrenceExceptionLetterArgument() {
		Word oneLetter = new Word("a");
     	assertThrows(IllegalArgumentException.class, () -> { oneLetter.replaceLastOccurrence('a', '0'); });
	}
	
	
	// Provided
	@Test
	public void testReverseHello() {
		Word hello = new Word("hello");
		assertEquals("olleh", hello.reverse().toString());
	}
	
	// Provided
	@Test
	public void testReverseEmpty() {
		Word empty = new Word("");
		assertEquals("", empty.reverse().toString());
	}

	
	// New
	@Test
	public void testReverseSingleChar() {
	    Word single = new Word("a");
	    assertEquals("a", single.reverse().toString());
	}
	
	// New
	@Test
	public void testReverseTwoSameChars() {
	    Word twoSame = new Word("aa");
	    assertEquals("aa", twoSame.reverse().toString());
	}
	
	// New
	@Test
	public void testReversePalindrome() {
	    Word palindrome = new Word("racecar");
	    assertEquals("racecar", palindrome.reverse().toString());
	}
	
}