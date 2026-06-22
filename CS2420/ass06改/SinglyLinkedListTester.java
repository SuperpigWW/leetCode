package assign06;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Comprehensive tests for the SinglyLinkedList class.
 *
 * @author Haoquan Wang
 * @version 2026-2-26
 */
class SinglyLinkedListTester {

	private SinglyLinkedList<Integer> empty;
	private SinglyLinkedList<Integer> single;
	private SinglyLinkedList<Integer> multi;
	private SinglyLinkedList<String> strings;

	@BeforeEach
	void setUp() {
		empty = new SinglyLinkedList<>();

		single = new SinglyLinkedList<>();
		single.insertFirst(42);

		multi = new SinglyLinkedList<>();
		for (int i = 5; i >= 1; i--)
			multi.insertFirst(i);

		strings = new SinglyLinkedList<>();
		strings.insertFirst("c");
		strings.insertFirst("b");
		strings.insertFirst("a"); // ["a", "b", "c"]
	}

	// ---- isEmpty & size ----

	@Test
	void testEmptyIsEmpty() {
		assertTrue(empty.isEmpty());
	}

	@Test
	void testEmptySize() {
		assertEquals(0, empty.size());
	}

	@Test
	void testMultiSize() {
		assertEquals(5, multi.size());
	}

	// ---- insertFirst ----

	@Test
	void testInsertFirstOnEmpty() {
		empty.insertFirst(10);
		assertEquals(10, empty.getFirst());
		assertEquals(1, empty.size());
	}

	@Test
	void testInsertFirstPrepends() {
		multi.insertFirst(0);
		assertEquals(0, multi.getFirst());
		assertEquals(6, multi.size());
	}

	@Test
	void testInsertFirstMultipleTimes() {
		empty.insertFirst(3);
		empty.insertFirst(2);
		empty.insertFirst(1);
		assertEquals(1, empty.getFirst());
		assertEquals(3, empty.size());
	}

	// ---- insert(index, element) ----

	@Test
	void testInsertAtIndexZero() {
		multi.insert(0, 99);
		assertEquals(99, multi.get(0));
		assertEquals(6, multi.size());
	}

	@Test
	void testInsertAtMiddle() {
		multi.insert(2, 99);
		assertEquals(99, multi.get(2));
		assertEquals(1, multi.get(0));
		assertEquals(2, multi.get(1));
		assertEquals(3, multi.get(3));
	}

	@Test
	void testInsertNegativeIndexThrows() {
		assertThrows(IndexOutOfBoundsException.class, () -> multi.insert(-1, 99));
	}

	@Test
	void testInsertIndexBeyondSizeThrows() {
		assertThrows(IndexOutOfBoundsException.class, () -> multi.insert(6, 99));
	}

	@Test
	void testInsertAtSizeIsAllowed() {
		assertDoesNotThrow(() -> multi.insert(multi.size(), 99));
	}

	// ---- getFirst ----

	@Test
	void testGetFirstOnEmpty() {
		assertThrows(NoSuchElementException.class, () -> empty.getFirst());
	}

	@Test
	void testGetFirstOnSingle() {
		assertEquals(42, single.getFirst());
	}

	@Test
	void testGetFirstOnMulti() {
		assertEquals(1, multi.getFirst());
	}

	@Test
	void testGetFirstDoesNotRemove() {
		multi.getFirst();
		assertEquals(5, multi.size());
	}

	// ---- get(index) ----

	@Test
	void testGetOnEmpty() {
		assertThrows(IndexOutOfBoundsException.class, () -> empty.get(0));
	}

	@Test
	void testGetFirstIndex() {
		assertEquals(1, multi.get(0));
	}

	@Test
	void testGetNegativeIndexThrows() {
		assertThrows(IndexOutOfBoundsException.class, () -> multi.get(-1));
	}

	@Test
	void testGetIndexEqualSizeThrows() {
		assertThrows(IndexOutOfBoundsException.class, () -> multi.get(5));
	}

	// ---- deleteFirst ----

	@Test
	void testDeleteFirstOnEmpty() {
		assertThrows(NoSuchElementException.class, () -> empty.deleteFirst());
	}

	@Test
	void testDeleteFirstReducesSize() {
		multi.deleteFirst();
		assertEquals(4, multi.size());
	}

	@Test
	void testDeleteFirstUntilEmpty() {
		single.deleteFirst();
		assertTrue(single.isEmpty());
	}

	// ---- delete(index) ----

	@Test
	void testDeleteIndexZero() {
		int val = multi.delete(0);
		assertEquals(1, val);
		assertEquals(2, multi.getFirst());
		assertEquals(4, multi.size());
	}

	@Test
	void testDeleteLastIndex() {
		int val = multi.delete(4);
		assertEquals(5, val);
		assertEquals(4, multi.size());
	}

	@Test
	void testDeleteNegativeIndexThrows() {
		assertThrows(IndexOutOfBoundsException.class, () -> multi.delete(-1));
	}

	@Test
	void testDeleteIndexEqualSizeThrows() {
		assertThrows(IndexOutOfBoundsException.class, () -> multi.delete(5));
	}

	// ---- indexOf ----

	@Test
	void testIndexOfNotFound() {
		assertEquals(-1, multi.indexOf(99));
	}

	@Test
	void testIndexOfFirstElement() {
		assertEquals(0, multi.indexOf(1));
	}

	@Test
	void testIndexOfLastElement() {
		assertEquals(4, multi.indexOf(5));
	}

	@Test
	void testIndexOfOnEmpty() {
		assertEquals(-1, empty.indexOf(1));
	}

	// ---- clear ----

	@Test
	void testClearMakesEmpty() {
		multi.clear();
		assertTrue(multi.isEmpty());
	}

	@Test
	void testClearSizeIsZero() {
		multi.clear();
		assertEquals(0, multi.size());
	}

	@Test
	void testClearThenInsert() {
		multi.clear();
		multi.insertFirst(7);
		assertEquals(7, multi.getFirst());
		assertEquals(1, multi.size());
	}

	// ---- toArray ----

	@Test
	void testToArrayOnEmpty() {
		Object[] arr = empty.toArray();
		assertEquals(0, arr.length);
	}

	@Test
	void testToArrayLength() {
		Object[] arr = multi.toArray();
		assertEquals(5, arr.length);
	}

	@Test
	void testToArrayStrings() {
		Object[] arr = strings.toArray();
		assertArrayEquals(new Object[]{"a", "b", "c"}, arr);
	}

	// ---- iterator ----

	@Test
	void testIteratorHasNextOnEmpty() {
		Iterator<Integer> it = empty.iterator();
		assertFalse(it.hasNext());
	}

	@Test
	void testIteratorTraversesAllElements() {
		Iterator<Integer> it = multi.iterator();
		int count = 0;
		while (it.hasNext()) {
			it.next();
			count++;
		}
		assertEquals(5, count);
	}

	@Test
	void testIteratorOrder() {
		Iterator<Integer> it = multi.iterator();
		for (int i = 1; i <= 5; i++)
			assertEquals(i, it.next());
	}

	@Test
	void testIteratorRemoveFirst() {
		Iterator<Integer> it = multi.iterator();
		it.next();       // advance to element 1
		it.remove();     // remove element 1
		assertEquals(4, multi.size());
		assertEquals(2, multi.getFirst());
	}


	@Test
	void testForEachLoop() {
		int sum = 0;
		for (int val : multi)
			sum += val;
		assertEquals(15, sum); // 1+2+3+4+5
	}
}
