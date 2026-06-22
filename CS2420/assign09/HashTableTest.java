package assign09;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

/**
 * JUnit 5 test class for HashTable.
 *
 * @author Haoquan Wang
 * @version April 2, 2026
 */
public class HashTableTest {

    private HashTable<String, Integer> table;

    @BeforeEach
    public void setUp() {
        table = new HashTable<>();
    }

    // ── constructor ──────────────────────────────────────────────────────────

    @Test
    public void testDefaultConstructorIsEmpty() {
        assertTrue(table.isEmpty());
        assertEquals(0, table.size());
    }

    @Test
    public void testCustomLoadFactorConstructorValid() {
        HashTable<String, Integer> t = new HashTable<>(2.0);
        assertTrue(t.isEmpty());
    }

    @Test
    public void testCustomLoadFactorConstructorThrowsOnTooSmall() {
        assertThrows(IllegalArgumentException.class, () -> new HashTable<>(0.5));
    }

    // ── put ──────────────────────────────────────────────────────────────────

    @Test
    public void testPutNewKeyReturnsNull() {
        assertNull(table.put("a", 1));
    }

    @Test
    public void testPutIncreasesSize() {
        table.put("a", 1);
        table.put("b", 2);
        assertEquals(2, table.size());
    }

    @Test
    public void testPutDuplicateKeyUpdateValueAndReturnOld() {
        table.put("a", 1);
        Integer old = table.put("a", 99);
        assertEquals(1, old);
        assertEquals(99, table.get("a"));
        assertEquals(1, table.size()); // size should not increase
    }

    @Test
    public void testPutNullValue() {
        assertNull(table.put("a", null));
        assertNull(table.get("a"));
        assertEquals(1, table.size());
    }

    // ── get ──────────────────────────────────────────────────────────────────

    @Test
    public void testGetExistingKey() {
        table.put("hello", 42);
        assertEquals(42, table.get("hello"));
    }

    @Test
    public void testGetMissingKeyReturnsNull() {
        assertNull(table.get("missing"));
    }

    // ── containsKey ──────────────────────────────────────────────────────────

    @Test
    public void testContainsKeyTrue() {
        table.put("x", 10);
        assertTrue(table.containsKey("x"));
    }

    @Test
    public void testContainsKeyFalse() {
        assertFalse(table.containsKey("x"));
    }

    // ── containsValue ────────────────────────────────────────────────────────

    @Test
    public void testContainsValueTrue() {
        table.put("a", 5);
        assertTrue(table.containsValue(5));
    }

    @Test
    public void testContainsValueFalse() {
        table.put("a", 5);
        assertFalse(table.containsValue(99));
    }

    @Test
    public void testContainsNullValue() {
        table.put("a", null);
        assertTrue(table.containsValue(null));
    }

    // ── remove ───────────────────────────────────────────────────────────────

    @Test
    public void testRemoveExistingKey() {
        table.put("a", 1);
        assertEquals(1, table.remove("a"));
        assertEquals(0, table.size());
        assertFalse(table.containsKey("a"));
    }

    @Test
    public void testRemoveMissingKeyReturnsNull() {
        assertNull(table.remove("nothere"));
    }

    @Test
    public void testRemoveDecreasesSize() {
        table.put("a", 1);
        table.put("b", 2);
        table.remove("a");
        assertEquals(1, table.size());
    }

    // ── clear ────────────────────────────────────────────────────────────────

    @Test
    public void testClearEmptiesTable() {
        table.put("a", 1);
        table.put("b", 2);
        table.clear();
        assertTrue(table.isEmpty());
        assertEquals(0, table.size());
    }

    @Test
    public void testClearThenPutWorks() {
        table.put("a", 1);
        table.clear();
        table.put("b", 2);
        assertEquals(1, table.size());
        assertEquals(2, table.get("b"));
    }

    // ── entries ──────────────────────────────────────────────────────────────

    @Test
    public void testEntriesSize() {
        table.put("a", 1);
        table.put("b", 2);
        table.put("c", 3);
        List<MapEntry<String, Integer>> entries = table.entries();
        assertEquals(3, entries.size());
    }

    @Test
    public void testEntriesEmptyTable() {
        assertTrue(table.entries().isEmpty());
    }

    // ── rehash / large input ─────────────────────────────────────────────────

    @Test
    public void testLargeNumberOfInsertions() {
        for (int i = 0; i < 200; i++)
            table.put("key" + i, i);
        assertEquals(200, table.size());
        for (int i = 0; i < 200; i++)
            assertEquals(i, table.get("key" + i));
    }

    // ── integer keys ─────────────────────────────────────────────────────────

    @Test
    public void testIntegerKeys() {
        HashTable<Integer, String> intTable = new HashTable<>();
        intTable.put(1, "one");
        intTable.put(2, "two");
        assertEquals("one", intTable.get(1));
        assertEquals("two", intTable.get(2));
        assertEquals(2, intTable.size());
    }
}