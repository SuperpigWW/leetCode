package assign07;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class provides unit tests for the Candidate class.
 * It tests all constructors, methods, and edge cases to ensure
 * the Candidate class functions correctly according to its specifications.
 * 
 * @author Haoquan Wang
 * @version 10/23/2025
 */
public class CandidateTest {

    /**
     * Tests the three-parameter constructor with valid input values.
     */
    @Test
    public void testThreeParameterConstructor_ValidInput() {
        Candidate candidate = new Candidate("John", 25, 8);
        
        assertEquals("John", candidate.toString().split("\\(")[0]);
        assertEquals(8, candidate.getRating());
    }
    
    /**
     * Tests that the three-parameter constructor throws an exception for negative age.
     */
    @Test
    public void testThreeParameterConstructor_NegativeAge() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Candidate("John", -5, 8);
        });
    }
    
    /**
     * Tests that the three-parameter constructor throws an exception for rating out of range.
     */
    @Test
    public void testThreeParameterConstructor_RatingOutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Candidate("John", 25, 15);
        });
    }
    
    /**
     * Tests the two-parameter constructor with valid input values.
     */
    @Test
    public void testTwoParameterConstructor_ValidInput() {
        Candidate candidate = new Candidate("Jane", 30);
        
        assertEquals("Jane", candidate.toString().split("\\(")[0]);
        assertEquals(1, candidate.getRating());
    }
    
    /**
     * Tests that the two-parameter constructor throws an exception for negative age.
     */
    @Test
    public void testTwoParameterConstructor_NegativeAge() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Candidate("Jane", -10);
        });
    }

    /**
     * Tests compareTo method with candidates having different names.
     */
    @Test
    public void testCompareTo_DifferentNames() {
        Candidate candidate1 = new Candidate("Alice", 25, 8);
        Candidate candidate2 = new Candidate("Bob", 25, 8);
        
        assertTrue(candidate1.compareTo(candidate2) < 0);
        assertTrue(candidate2.compareTo(candidate1) > 0);
    }
    
    /**
     * Tests compareTo method with candidates having same name but different ages.
     */
    @Test
    public void testCompareTo_SameNameDifferentAges() {
        Candidate candidate1 = new Candidate("Alice", 20, 8);
        Candidate candidate2 = new Candidate("Alice", 25, 8);
        
        assertTrue(candidate1.compareTo(candidate2) < 0);
        assertTrue(candidate2.compareTo(candidate1) > 0);
    }
    
    /**
     * Tests compareTo method with candidates having same name and same age.
     */
    @Test
    public void testCompareTo_SameNameSameAge() {
        Candidate candidate1 = new Candidate("Alice", 25, 8);
        Candidate candidate2 = new Candidate("Alice", 25, 9);
        
        assertEquals(0, candidate1.compareTo(candidate2));
        assertEquals(0, candidate2.compareTo(candidate1));
    }
    
    /**
     * Tests compareTo method with case-sensitive name comparison.
     */
    @Test
    public void testCompareTo_CaseSensitiveNames() {
        Candidate candidate1 = new Candidate("alice", 25, 8);
        Candidate candidate2 = new Candidate("Alice", 25, 8);
        
        assertTrue(candidate1.compareTo(candidate2) > 0);
    }

    /**
     * Tests equals method with candidates having same name and age.
     */
    @Test
    public void testEquals_SameNameAndAge() {
        Candidate candidate1 = new Candidate("John", 25, 8);
        Candidate candidate2 = new Candidate("John", 25, 9);
        
        assertTrue(candidate1.equals(candidate2));
        assertTrue(candidate2.equals(candidate1));
    }
    
    /**
     * Tests equals method with candidates having different names.
     */
    @Test
    public void testEquals_DifferentNames() {
        Candidate candidate1 = new Candidate("John", 25, 8);
        Candidate candidate2 = new Candidate("Jane", 25, 8);
        
        assertFalse(candidate1.equals(candidate2));
        assertFalse(candidate2.equals(candidate1));
    }
    
    /**
     * Tests equals method with candidates having different ages.
     */
    @Test
    public void testEquals_DifferentAges() {
        Candidate candidate1 = new Candidate("John", 25, 8);
        Candidate candidate2 = new Candidate("John", 30, 8);
        
        assertFalse(candidate1.equals(candidate2));
        assertFalse(candidate2.equals(candidate1));
    }
    
    /**
     * Tests equals method with the same object for reflexivity.
     */
    @Test
    public void testEquals_SameObject() {
        Candidate candidate = new Candidate("John", 25, 8);
        
        assertTrue(candidate.equals(candidate));
    }
    
    /**
     * Tests equals method with null object.
     */
    @Test
    public void testEquals_NullObject() {
        Candidate candidate = new Candidate("John", 25, 8);
        
        assertFalse(candidate.equals(null));
    }
    
    /**
     * Tests equals method with object of different class.
     */
    @Test
    public void testEquals_DifferentClass() {
        Candidate candidate = new Candidate("John", 25, 8);
        String notCandidate = "John";
        
        assertFalse(candidate.equals(notCandidate));
    }

    /**
     * Tests toString method with standard name and age format.
     */
    @Test
    public void testToString_StandardFormat() {
        Candidate candidate = new Candidate("Zoey", 21, 7);
        
        assertEquals("Zoey(21)", candidate.toString());
    }
    
    /**
     * Tests toString method with name containing underscore.
     */
    @Test
    public void testToString_WithUnderscoreName() {
        Candidate candidate = new Candidate("Zoey_Smith", 21, 7);
        
        assertEquals("Zoey_Smith(21)", candidate.toString());
    }
    
    /**
     * Tests toString method with zero age.
     */
    @Test
    public void testToString_ZeroAge() {
        Candidate candidate = new Candidate("Baby", 0, 1);
        
        assertEquals("Baby(0)", candidate.toString());
    }

    /**
     * Tests getRating method with three-parameter constructor.
     */
    @Test
    public void testGetRating_ThreeParameterConstructor() {
        Candidate candidate = new Candidate("John", 25, 8);
        
        assertEquals(8, candidate.getRating());
    }
    
    /**
     * Tests getRating method with two-parameter constructor.
     */
    @Test
    public void testGetRating_TwoParameterConstructor() {
        Candidate candidate = new Candidate("John", 25);
        
        assertEquals(1, candidate.getRating());
    }
    
    /**
     * Tests getRating method with minimum valid rating.
     */
    @Test
    public void testGetRating_MinRating() {
        Candidate candidate = new Candidate("John", 25, 1);
        
        assertEquals(1, candidate.getRating());
    }
    
    /**
     * Tests getRating method with maximum valid rating.
     */
    @Test
    public void testGetRating_MaxRating() {
        Candidate candidate = new Candidate("John", 25, 10);
        
        assertEquals(10, candidate.getRating());
    }

    /**
     * Tests edge case with minimum valid age (0).
     */
    @Test
    public void testEdgeCase_MinimumValidAge() {
        Candidate candidate = new Candidate("Newborn", 0, 5);
        
        assertEquals("Newborn(0)", candidate.toString());
        assertEquals(5, candidate.getRating());
    }
    
    /**
     * Tests edge case with maximum valid rating (10).
     */
    @Test
    public void testEdgeCase_MaximumValidRating() {
        Candidate candidate = new Candidate("Perfect", 25, 10);
        
        assertEquals(10, candidate.getRating());
    }
    
    /**
     * Tests edge case with empty name string.
     */
    @Test
    public void testEdgeCase_EmptyName() {
        Candidate candidate = new Candidate("", 25, 5);
        
        assertEquals("(25)", candidate.toString());
    }
    
    /**
     * Tests consistency between compareTo and equals methods.
     */
    @Test
    public void testConsistencyBetweenCompareToAndEquals() {
        Candidate candidate1 = new Candidate("Alice", 25, 8);
        Candidate candidate2 = new Candidate("Alice", 25, 9);
        
        assertEquals(0, candidate1.compareTo(candidate2));
        assertTrue(candidate1.equals(candidate2));
        
        Candidate candidate3 = new Candidate("Bob", 25, 8);
        assertTrue(candidate1.compareTo(candidate3) != 0);
        assertFalse(candidate1.equals(candidate3));
    }
}