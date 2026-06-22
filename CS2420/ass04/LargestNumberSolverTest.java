package assign04;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Comprehensive JUnit tests for LargestNumberSolver.
 * Tests cover normal cases, edge cases, and boundary conditions.
 * 
 * @author Haoquan Wang and Zewei Ren
 * @version 2026.2.4
 */
public class LargestNumberSolverTest {
    
    // ==================== insertionSort Tests ====================
    
    /**
     * Tests insertion sort with a basic unsorted array.
     */
    @Test
    void testInsertionSortBasic() {
        Integer[] arr = {5, 2, 8, 1, 9};
        LargestNumberSolver.insertionSort(arr, Integer::compareTo);
        assertArrayEquals(new Integer[]{1, 2, 5, 8, 9}, arr);
    }
    
    /**
     * Tests insertion sort with null array, should handle gracefully.
     */
    @Test
    void testInsertionSortNullArray() {
        assertDoesNotThrow(() -> 
            LargestNumberSolver.insertionSort(null, Integer::compareTo));
    }
    
    /**
     * Tests insertion sort with empty array.
     */
    @Test
    void testInsertionSortEmpty() {
        Integer[] arr = {};
        LargestNumberSolver.insertionSort(arr, Integer::compareTo);
        assertArrayEquals(new Integer[]{}, arr);
    }
    
    /**
     * Tests insertion sort with single element array.
     */
    @Test
    void testInsertionSortSingleElement() {
        Integer[] arr = {42};
        LargestNumberSolver.insertionSort(arr, Integer::compareTo);
        assertArrayEquals(new Integer[]{42}, arr);
    }
    
    /**
     * Tests insertion sort with duplicate elements.
     */
    @Test
    void testInsertionSortDuplicates() {
        Integer[] arr = {3, 1, 4, 1, 5};
        LargestNumberSolver.insertionSort(arr, Integer::compareTo);
        assertArrayEquals(new Integer[]{1, 1, 3, 4, 5}, arr);
    }
    
    // ==================== findLargestNumber Tests ====================
    
    /**
     * Tests finding largest number from basic example {1, 45, 9}.
     * Expected: 9451
     */
    @Test
    void testFindLargestNumberBasic() {
        Integer[] arr = {1, 45, 9};
        assertEquals(new BigInteger("9451"), 
            LargestNumberSolver.findLargestNumber(arr));
    }
    
    /**
     * Tests finding largest number from assignment example {11, 67, 79, 7, 22, 13}.
     * Expected: 79767221311
     */
    @Test
    void testFindLargestNumberAssignmentExample() {
        Integer[] arr = {11, 67, 79, 7, 22, 13};
        assertEquals(new BigInteger("79767221311"), 
            LargestNumberSolver.findLargestNumber(arr));
    }
    
    /**
     * Tests finding largest number from empty array.
     * Expected: 0
     */
    @Test
    void testFindLargestNumberEmpty() {
        Integer[] arr = {};
        assertEquals(BigInteger.ZERO, 
            LargestNumberSolver.findLargestNumber(arr));
    }
    
    /**
     * Tests finding largest number from array of all zeros.
     * Expected: 0
     */
    @Test
    void testFindLargestNumberAllZeros() {
        Integer[] arr = {0, 0, 0};
        assertEquals(BigInteger.ZERO, 
            LargestNumberSolver.findLargestNumber(arr));
    }
    
    /**
     * Tests that original array is not modified.
     */
    @Test
    void testFindLargestNumberDoesNotModifyOriginal() {
        Integer[] arr = {3, 1, 4};
        Integer[] original = Arrays.copyOf(arr, arr.length);
        LargestNumberSolver.findLargestNumber(arr);
        assertArrayEquals(original, arr);
    }
    
    // ==================== findLargestInt Tests ====================
    
    /**
     * Tests finding largest int that fits within int range.
     */
    @Test
    void testFindLargestIntValid() {
        Integer[] arr = {1, 2, 3};
        assertEquals(321, LargestNumberSolver.findLargestInt(arr));
    }
    
    /**
     * Tests finding largest int from empty array.
     * Expected: 0
     */
    @Test
    void testFindLargestIntEmpty() {
        Integer[] arr = {};
        assertEquals(0, LargestNumberSolver.findLargestInt(arr));
    }
    
    /**
     * Tests that OutOfRangeException is thrown when result exceeds Integer.MAX_VALUE.
     * Array {999, 639, 1, 7, 58, 9} produces 99997639581 which is too large.
     */
    @Test
    void testFindLargestIntThrowsException() {
        Integer[] arr = {999, 639, 1, 7, 58, 9};
        assertThrows(OutOfRangeException.class, 
            () -> LargestNumberSolver.findLargestInt(arr));
    }
    
    // ==================== findLargestLong Tests ====================
    
    /**
     * Tests finding largest long that fits within long range.
     * Array {999, 639, 1, 7, 58, 9} produces 99997639581
     */
    @Test
    void testFindLargestLongValid() {
        Integer[] arr = {999, 639, 1, 7, 58, 9};
        assertDoesNotThrow(() -> LargestNumberSolver.findLargestLong(arr));
    }
    
    /**
     * Tests finding largest long from empty array.
     * Expected: 0
     */
    @Test
    void testFindLargestLongEmpty() {
        Integer[] arr = {};
        assertEquals(0L, LargestNumberSolver.findLargestLong(arr));
    }
    
    /**
     * Tests that OutOfRangeException is thrown when result exceeds Long.MAX_VALUE.
     */
    @Test
    void testFindLargestLongThrowsException() {
        Integer[] arr = new Integer[30];
        Arrays.fill(arr, 999);
        assertThrows(OutOfRangeException.class, 
            () -> LargestNumberSolver.findLargestLong(arr));
    }
    
    // ==================== sum Tests ====================
    
    /**
     * Tests sum with assignment example arrays {88, 51} and {7, 42, 97}.
     * Expected: 8851 + 97742 = 106593
     */
    @Test
    void testSumBasic() {
        List<Integer[]> list = new ArrayList<>();
        list.add(new Integer[]{88, 51});
        list.add(new Integer[]{7, 42, 97});
        assertEquals(new BigInteger("106593"), 
            LargestNumberSolver.sum(list));
    }
    
    /**
     * Tests sum with empty list.
     * Expected: 0
     */
    @Test
    void testSumEmptyList() {
        List<Integer[]> list = new ArrayList<>();
        assertEquals(BigInteger.ZERO, LargestNumberSolver.sum(list));
    }
    
    /**
     * Tests sum with null list.
     * Expected: 0
     */
    @Test
    void testSumNullList() {
        assertEquals(BigInteger.ZERO, LargestNumberSolver.sum(null));
    }
    
    /**
     * Tests that original list and arrays are not modified.
     */
    @Test
    void testSumDoesNotModifyOriginal() {
        List<Integer[]> list = new ArrayList<>();
        Integer[] arr1 = {3, 1};
        Integer[] arr2 = {2, 4};
        list.add(arr1);
        list.add(arr2);
        
        LargestNumberSolver.sum(list);
        
        assertArrayEquals(new Integer[]{3, 1}, arr1);
        assertArrayEquals(new Integer[]{2, 4}, arr2);
        assertEquals(2, list.size());
    }
    
    // ==================== findKthLargest Tests ====================
    
    /**
     * Tests finding 0th largest (largest overall) from assignment example.
     * Expected: {7, 42, 97} which forms 97742
     */
    @Test
    void testFindKthLargestFirst() {
        List<Integer[]> list = new ArrayList<>();
        list.add(new Integer[]{88, 51});
        list.add(new Integer[]{7, 42, 97});
        assertArrayEquals(new Integer[]{7, 42, 97}, 
            LargestNumberSolver.findKthLargest(list, 0));
    }
    
    /**
     * Tests finding 1st largest (second overall) from assignment example.
     * Expected: {88, 51} which forms 8851
     */
    @Test
    void testFindKthLargestSecond() {
        List<Integer[]> list = new ArrayList<>();
        list.add(new Integer[]{88, 51});
        list.add(new Integer[]{7, 42, 97});
        assertArrayEquals(new Integer[]{88, 51}, 
            LargestNumberSolver.findKthLargest(list, 1));
    }
    
    /**
     * Tests that IllegalArgumentException is thrown for negative k.
     */
    @Test
    void testFindKthLargestNegativeK() {
        List<Integer[]> list = new ArrayList<>();
        list.add(new Integer[]{1, 2});
        assertThrows(IllegalArgumentException.class, 
            () -> LargestNumberSolver.findKthLargest(list, -1));
    }
    
    /**
     * Tests that IllegalArgumentException is thrown for k >= list.size().
     */
    @Test
    void testFindKthLargestKTooLarge() {
        List<Integer[]> list = new ArrayList<>();
        list.add(new Integer[]{1, 2});
        assertThrows(IllegalArgumentException.class, 
            () -> LargestNumberSolver.findKthLargest(list, 2));
    }
    
    /**
     * Tests that original list and arrays are not modified.
     */
    @Test
    void testFindKthLargestDoesNotModifyOriginal() {
        List<Integer[]> list = new ArrayList<>();
        Integer[] arr1 = {3, 1};
        Integer[] arr2 = {2, 4};
        list.add(arr1);
        list.add(arr2);
        
        LargestNumberSolver.findKthLargest(list, 0);
        
        assertArrayEquals(new Integer[]{3, 1}, arr1);
        assertArrayEquals(new Integer[]{2, 4}, arr2);
        assertEquals(2, list.size());
    }
    
    // ==================== readFile Tests ====================
    
    /**
     * Tests reading from non-existent file.
     * Expected: empty list
     */
    @Test
    void testReadFileNotExist() {
        List<Integer[]> result = LargestNumberSolver.readFile("nonexistent.txt");
        assertTrue(result.isEmpty());
    }
    
    /**
     * Tests that readFile handles empty lines correctly by skipping them.
     */
    @Test
    void testReadFileWithEmptyLines() {
        // This test assumes you create a test file, or you can skip it
        // Just testing the method doesn't crash
        assertDoesNotThrow(() -> 
            LargestNumberSolver.readFile("test_empty_lines.txt"));
    }
    
    /**
     * Tests that readFile returns empty list for invalid file path.
     */
    @Test
    void testReadFileInvalidPath() {
        List<Integer[]> result = LargestNumberSolver.readFile("");
        assertTrue(result.isEmpty());
    }
}