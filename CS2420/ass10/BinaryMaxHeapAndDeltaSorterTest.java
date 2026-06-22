package assign10;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * This is a tester class used for testing the BinaryMaxHeap class
 *
 * @author Haoquan Wang and Jake Scott
 * @version 2026-4-6
 */
public class BinaryMaxHeapAndDeltaSorterTest {

    private BinaryMaxHeap<Integer> emptyHeap;
    private BinaryMaxHeap<Integer> smallHeap;

    @BeforeEach
    void setUp() {
        emptyHeap = new BinaryMaxHeap<>();
        smallHeap = new BinaryMaxHeap<>();
        smallHeap.add(3);
        smallHeap.add(1);
        smallHeap.add(5);
        smallHeap.add(2);
        smallHeap.add(4);
    }

    // ==================== BinaryMaxHeap Tests ====================

    @Test
    void testEmptyHeapSize() {
        assertEquals(0, emptyHeap.size());
    }

    @Test
    void testIsEmptyOnNewHeap() {
        assertTrue(emptyHeap.isEmpty());
    }

    @Test
    void testIsNotEmptyAfterAdd() {
        emptyHeap.add(1);
        assertFalse(emptyHeap.isEmpty());
    }

    @Test
    void testPeekThrowsOnEmpty() {
        assertThrows(NoSuchElementException.class, () -> emptyHeap.peek());
    }

    @Test
    void testExtractMaxThrowsOnEmpty() {
        assertThrows(NoSuchElementException.class, () -> emptyHeap.extractMax());
    }

    @Test
    void testPeekReturnsMax() {
        assertEquals(5, smallHeap.peek());
    }

    @Test
    void testPeekDoesNotRemove() {
        smallHeap.peek();
        assertEquals(5, smallHeap.size());
    }

    @Test
    void testExtractMaxReturnsMax() {
        assertEquals(5, smallHeap.extractMax());
    }

    @Test
    void testExtractMaxReducesSize() {
        smallHeap.extractMax();
        assertEquals(4, smallHeap.size());
    }

    @Test
    void testExtractMaxOrder() {
        assertEquals(5, smallHeap.extractMax());
        assertEquals(4, smallHeap.extractMax());
        assertEquals(3, smallHeap.extractMax());
        assertEquals(2, smallHeap.extractMax());
        assertEquals(1, smallHeap.extractMax());
    }

    @Test
    void testClear() {
        smallHeap.clear();
        assertTrue(smallHeap.isEmpty());
        assertEquals(0, smallHeap.size());
    }

    @Test
    void testToArray() {
        emptyHeap.add(10);
        Object[] arr = emptyHeap.toArray();
        assertEquals(1, arr.length);
        assertEquals(10, arr[0]);
    }

    @Test
    void testToArrayRootIsMax() {
        Object[] arr = smallHeap.toArray();
        assertEquals(5, arr[0]);
    }

    // ---------- Constructor with List ----------

    @Test
    void testListConstructorSize() {
        List<Integer> list = Arrays.asList(4, 2, 7, 1, 9);
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>(list);
        assertEquals(5, heap.size());
    }

    @Test
    void testListConstructorPeekIsMax() {
        List<Integer> list = Arrays.asList(4, 2, 7, 1, 9);
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>(list);
        assertEquals(9, heap.peek());
    }

    // ---------- Comparator Constructor ----------

    @Test
    void testComparatorReverseOrder() {
        // reverse order comparator: smaller int = higher priority
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>(Comparator.reverseOrder());
        heap.add(3);
        heap.add(1);
        heap.add(5);
        assertEquals(1, heap.peek()); // smallest is "max" under reverse order
    }

    @Test
    void testListComparatorConstructor() {
        List<Integer> list = Arrays.asList(4, 2, 7, 1, 9);
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>(list, Comparator.reverseOrder());
        assertEquals(1, heap.peek());
    }

    // ---------- String Heap ----------

    @Test
    void testStringHeap() {
        BinaryMaxHeap<String> heap = new BinaryMaxHeap<>();
        heap.add("banana");
        heap.add("apple");
        heap.add("cherry");
        assertEquals("cherry", heap.extractMax());
        assertEquals("banana", heap.extractMax());
    }

    // ---------- Duplicates ----------

    @Test
    void testDuplicateValues() {
        emptyHeap.add(5);
        emptyHeap.add(5);
        emptyHeap.add(5);
        assertEquals(5, emptyHeap.extractMax());
        assertEquals(5, emptyHeap.extractMax());
        assertEquals(5, emptyHeap.extractMax());
        assertTrue(emptyHeap.isEmpty());
    }

    // ==================== DeltaSorter Tests ====================

    @Test
    void testDeltaSorterBasic() {
        List<Integer> list = Arrays.asList(5, 4, 3, 2, 1);
        DeltaSorter.sort(list, 2);
        assertEquals(Arrays.asList(5, 4, 3, 2, 1), list);
    }

    @Test
    void testDeltaSorterDeltaZero() {
        List<Integer> list = Arrays.asList(5, 4, 3, 2, 1);
        DeltaSorter.sort(list, 0);
        assertEquals(Arrays.asList(5, 4, 3, 2, 1), list);
    }

    @Test
    void testDeltaSorterResultDescending() {
        List<Integer> list = Arrays.asList(3, 5, 4, 1, 2);
        DeltaSorter.sort(list, 4);
        for (int i = 0; i < list.size() - 1; i++)
            assertTrue(list.get(i) >= list.get(i + 1));
    }

    @Test
    void testDeltaSorterSingleElement() {
        List<Integer> list = Arrays.asList(42);
        DeltaSorter.sort(list, 0);
        assertEquals(42, list.get(0));
    }

    @Test
    void testDeltaSorterInvalidDeltaNegative() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        assertThrows(IllegalArgumentException.class, () -> DeltaSorter.sort(list, -1));
    }

    @Test
    void testDeltaSorterInvalidDeltaTooLarge() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        assertThrows(IllegalArgumentException.class, () -> DeltaSorter.sort(list, 3));
    }

    @Test
    void testDeltaSorterWithComparator() {
        List<Integer> list = Arrays.asList(3, 5, 4, 1, 2);
        DeltaSorter.sort(list, 4, Comparator.naturalOrder());
        for (int i = 0; i < list.size() - 1; i++)
            assertTrue(list.get(i) >= list.get(i + 1));
    }

    @Test
    void testDeltaSorterComparatorInvalidDelta() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        assertThrows(IllegalArgumentException.class,
                () -> DeltaSorter.sort(list, -1, Comparator.naturalOrder()));
    }
}