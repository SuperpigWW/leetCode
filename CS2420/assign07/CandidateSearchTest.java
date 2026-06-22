package assign07;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class provides unit tests for the CandidateSearch class.
 * It tests both sequential search and binary search algorithms with various
 * test cases including normal cases, edge cases, and error conditions.
 * 
 * @author Haoquan Wang
 * @version 10/23/2025
 */
public class CandidateSearchTest {

    /**
     * Creates a test array of Candidate objects for testing purposes.
     * 
     * @return an array of Candidate objects with various names, ages, and ratings
     */
    private Candidate[] createTestCandidates() {
        return new Candidate[]{
            new Candidate("Alice", 25, 8),
            new Candidate("Bob", 30, 7),
            new Candidate("Charlie", 22, 9),
            new Candidate("David", 35, 6),
            new Candidate("Eve", 28, 8)
        };
    }

    /**
     * Creates a pre-sorted test array of Candidate objects for binary search testing.
     * 
     * @return a sorted array of Candidate objects by name
     */
    private Candidate[] createSortedTestCandidates() {
        return new Candidate[]{
            new Candidate("Alice", 20, 8),
            new Candidate("Bob", 25, 7),
            new Candidate("Charlie", 30, 9)
        };
    }

    /**
     * Tests sequential search when the target candidate is found in the array.
     */
    @Test
    public void testSequentialSearch_Found() {
        Candidate[] candidates = createTestCandidates();
        Candidate target = new Candidate("Bob", 30);
        
        Candidate result = CandidateSearch.sequentialSearch(candidates, target);
        
        assertNotNull(result);
        assertEquals("Bob", result.toString().split("\\(")[0]);
        assertEquals(7, result.getRating());
    }

    /**
     * Tests sequential search when the target candidate is not found in the array.
     */
    @Test
    public void testSequentialSearch_NotFound() {
        Candidate[] candidates = createTestCandidates();
        Candidate target = new Candidate("Frank", 40);
        
        Candidate result = CandidateSearch.sequentialSearch(candidates, target);
        
        assertNull(result);
    }

    /**
     * Tests sequential search when the target candidate is the first element in the array.
     */
    @Test
    public void testSequentialSearch_FirstElement() {
        Candidate[] candidates = createTestCandidates();
        Candidate target = new Candidate("Alice", 25);
        
        Candidate result = CandidateSearch.sequentialSearch(candidates, target);
        
        assertNotNull(result);
        assertEquals("Alice", result.toString().split("\\(")[0]);
        assertEquals(1, CandidateSearch.getCallCount());
    }

    /**
     * Tests sequential search when the target candidate is the last element in the array.
     */
    @Test
    public void testSequentialSearch_LastElement() {
        Candidate[] candidates = createTestCandidates();
        Candidate target = new Candidate("Eve", 28);
        
        Candidate result = CandidateSearch.sequentialSearch(candidates, target);
        
        assertNotNull(result);
        assertEquals("Eve", result.toString().split("\\(")[0]);
        assertEquals(5, CandidateSearch.getCallCount());
    }

    /**
     * Tests binary search when the target candidate is found in the array.
     */
    @Test
    public void testBinarySearch_Found() {
        Candidate[] candidates = createSortedTestCandidates();
        Candidate target = new Candidate("Bob", 25);
        
        Candidate result = CandidateSearch.binarySearch(candidates, target);
        
        assertNotNull(result);
        assertEquals("Bob", result.toString().split("\\(")[0]);
        assertEquals(7, result.getCallCount());
    }

    /**
     * Tests binary search when the target candidate is not found in the array.
     */
    @Test
    public void testBinarySearch_NotFound() {
        Candidate[] candidates = createSortedTestCandidates();
        Candidate target = new Candidate("Frank", 40);
        
        Candidate result = CandidateSearch.binarySearch(candidates, target);
        
        assertNull(result);
    }

    /**
     * Tests binary search when the target candidate is the first element in the array.
     */
    @Test
    public void testBinarySearch_FirstElement() {
        Candidate[] candidates = createSortedTestCandidates();
        Candidate target = new Candidate("Alice", 20);
        
        Candidate result = CandidateSearch.binarySearch(candidates, target);
        
        assertNotNull(result);
        assertEquals("Alice", result.toString().split("\\(")[0]);
    }

    /**
     * Tests binary search when the target candidate is the last element in the array.
     */
    @Test
    public void testBinarySearch_LastElement() {
        Candidate[] candidates = createSortedTestCandidates();
        Candidate target = new Candidate("Charlie", 30);
        
        Candidate result = CandidateSearch.binarySearch(candidates, target);
        
        assertNotNull(result);
        assertEquals("Charlie", result.toString().split("\\(")[0]);
    }

    /**
     * Tests binary search when the target candidate is the middle element in the array.
     */
    @Test
    public void testBinarySearch_MiddleElement() {
        Candidate[] candidates = createSortedTestCandidates();
        Candidate target = new Candidate("Bob", 25);
        
        Candidate result = CandidateSearch.binarySearch(candidates, target);
        
        assertNotNull(result);
        assertEquals("Bob", result.toString().split("\\(")[0]);
    }

    /**
     * Tests search operations with an empty array to ensure proper exception handling.
     */
    @Test
    public void testEmptyArray() {
        Candidate[] emptyArray = new Candidate[0];
        Candidate target = new Candidate("Alice", 25);
        
        assertThrows(IllegalArgumentException.class, () -> {
            CandidateSearch.sequentialSearch(emptyArray, target);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            CandidateSearch.binarySearch(emptyArray, target);
        });
    }

    /**
     * Tests search operations with a null array to ensure proper exception handling.
     */
    @Test
    public void testNullArray() {
        Candidate target = new Candidate("Alice", 25);
        
        assertThrows(IllegalArgumentException.class, () -> {
            CandidateSearch.sequentialSearch(null, target);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            CandidateSearch.binarySearch(null, target);
        });
    }

    /**
     * Tests search operations with a null target to ensure proper exception handling.
     */
    @Test
    public void testNullTarget() {
        Candidate[] candidates = createTestCandidates();
        
        assertThrows(IllegalArgumentException.class, () -> {
            CandidateSearch.sequentialSearch(candidates, null);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            CandidateSearch.binarySearch(candidates, null);
        });
    }

    /**
     * Tests search operations with a single-element array when the target is found.
     */
    @Test
    public void testSingleElementArray_Found() {
        Candidate[] singleArray = new Candidate[]{new Candidate("Alice", 25, 8)};
        Candidate target = new Candidate("Alice", 25);
        
        Candidate result = CandidateSearch.sequentialSearch(singleArray, target);
        assertNotNull(result);
        
        result = CandidateSearch.binarySearch(singleArray, target);
        assertNotNull(result);
    }

    /**
     * Tests search operations with a single-element array when the target is not found.
     */
    @Test
    public void testSingleElementArray_NotFound() {
        Candidate[] singleArray = new Candidate[]{new Candidate("Alice", 25, 8)};
        Candidate target = new Candidate("Bob", 30);
        
        Candidate result = CandidateSearch.sequentialSearch(singleArray, target);
        assertNull(result);
        
        result = CandidateSearch.binarySearch(singleArray, target);
        assertNull(result);
    }

    /**
     * Tests binary search with duplicate names but different ages to ensure
     * proper handling of tie-breaking by age.
     */
    @Test
    public void testDuplicateNamesDifferentAges() {
        Candidate[] candidates = new Candidate[]{
            new Candidate("Alice", 20, 8),
            new Candidate("Alice", 25, 7),
            new Candidate("Alice", 30, 9)
        };
        
        Candidate target = new Candidate("Alice", 25);
        Candidate result = CandidateSearch.binarySearch(candidates, target);
        
        assertNotNull(result);
        assertEquals(25, result.toString().split("\\(")[1].split("\\)")[0]);
        assertEquals(7, result.getRating());
    }

    /**
     * Tests that the call counter is properly reset between search operations.
     */
    @Test
    public void testCallCountReset() {
        Candidate[] candidates = createTestCandidates();
        Candidate target = new Candidate("Alice", 25);
        
        CandidateSearch.sequentialSearch(candidates, target);
        int firstCallCount = CandidateSearch.getCallCount();
        
        CandidateSearch.sequentialSearch(candidates, target);
        int secondCallCount = CandidateSearch.getCallCount();
        
        assertEquals(firstCallCount, secondCallCount);
    }

    /**
     * Tests that binary search properly sorts an unsorted array before searching.
     */
    @Test
    public void testBinarySearchSorting() {
        Candidate[] unsortedCandidates = new Candidate[]{
            new Candidate("Charlie", 30, 9),
            new Candidate("Alice", 20, 8),
            new Candidate("Bob", 25, 7)
        };
        
        Candidate target = new Candidate("Alice", 20);
        
        Candidate result = CandidateSearch.binarySearch(unsortedCandidates, target);
        
        assertNotNull(result);
        assertEquals("Alice", result.toString().split("\\(")[0]);
    }
}
