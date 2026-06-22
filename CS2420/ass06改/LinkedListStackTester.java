package assign06;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Comprehensive tests for the LinkedListStack class.
 *
 * @author Haoquan Wang
 * @version 2026-2-26
 */
class LinkedListStackTester {

	private LinkedListStack<Integer> empty;
	private LinkedListStack<Integer> single;
	private LinkedListStack<Integer> multi;
	private LinkedListStack<String> strings;

	@BeforeEach
	void setUp() {
		empty = new LinkedListStack<>();

		single = new LinkedListStack<>();
		single.push(10);

		multi = new LinkedListStack<>();
		for (int i = 1; i <= 5; i++)
			multi.push(i);

		strings = new LinkedListStack<>();
		strings.push("first");
		strings.push("second");
		strings.push("third"); 
	}

	// ---- isEmpty ----

	@Test
	void testEmptyIsEmpty() {
		assertTrue(empty.isEmpty());
	}

	@Test
	void testSingleNotEmpty() {
		assertFalse(single.isEmpty());
	}

	@Test
	void testMultiNotEmpty() {
		assertFalse(multi.isEmpty());
	}

	@Test
	void testIsEmptyAfterPushPop() {
		empty.push(1);
		empty.pop();
		assertTrue(empty.isEmpty());
	}

	// ---- size ----

	@Test
	void testEmptySize() {
		assertEquals(0, empty.size());
	}

	@Test
	void testSingleSize() {
		assertEquals(1, single.size());
	}

	@Test
	void testMultiSize() {
		assertEquals(5, multi.size());
	}

	@Test
	void testSizeAfterMultiplePushes() {
		empty.push(1);
		empty.push(2);
		empty.push(3);
		assertEquals(3, empty.size());
	}

	@Test
	void testSizeAfterPop() {
		multi.pop();
		assertEquals(4, multi.size());
	}

	// ---- push ----

	@Test
	void testPushOnEmpty() {
		empty.push(99);
		assertEquals(99, empty.peek());
		assertEquals(1, empty.size());
	}

	@Test
	void testPushGoesToTop() {
		multi.push(99);
		assertEquals(99, multi.peek());
	}

	@Test
	void testPushIncreasesSize() {
		int before = multi.size();
		multi.push(99);
		assertEquals(before + 1, multi.size());
	}

	@Test
	void testPushMultipleAndCheckOrder() {
		empty.push(1);
		empty.push(2);
		empty.push(3);
		assertEquals(3, empty.pop());
		assertEquals(2, empty.pop());
		assertEquals(1, empty.pop());
	}

	// ---- peek ----

	@Test
	void testPeekOnEmpty() {
		assertThrows(NoSuchElementException.class, () -> empty.peek());
	}

	@Test
	void testPeekOnSingle() {
		assertEquals(10, single.peek());
	}

	@Test
	void testPeekReturnsTop() {
		assertEquals(5, multi.peek());
	}

	@Test
	void testPeekDoesNotRemove() {
		multi.peek();
		assertEquals(5, multi.size());
		assertEquals(5, multi.peek());
	}

	@Test
	void testPeekStrings() {
		assertEquals("third", strings.peek());
	}

	// ---- pop ----

	@Test
	void testPopOnEmpty() {
		assertThrows(NoSuchElementException.class, () -> empty.pop());
	}

	@Test
	void testPopOnSingleReturnValue() {
		assertEquals(10, single.pop());
	}

	@Test
	void testPopOnSingleMakesEmpty() {
		single.pop();
		assertTrue(single.isEmpty());
	}

	@Test
	void testPopReturnsTop() {
		assertEquals(5, multi.pop());
	}

	@Test
	void testPopReducesSize() {
		multi.pop();
		assertEquals(4, multi.size());
	}

	@Test
	void testPopOrder() {
		// LIFO: should come out 5,4,3,2,1
		for (int i = 5; i >= 1; i--)
			assertEquals(i, multi.pop());
	}

	@Test
	void testPopAllThenEmpty() {
		while (!multi.isEmpty())
			multi.pop();
		assertTrue(multi.isEmpty());
		assertEquals(0, multi.size());
	}

	@Test
	void testPopStringsOrder() {
		assertEquals("third", strings.pop());
		assertEquals("second", strings.pop());
		assertEquals("first", strings.pop());
	}

	// ---- clear ----

	@Test
	void testClearOnEmpty() {
		assertDoesNotThrow(() -> empty.clear());
		assertTrue(empty.isEmpty());
	}

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
	void testClearThenPush() {
		multi.clear();
		multi.push(77);
		assertEquals(77, multi.peek());
		assertEquals(1, multi.size());
	}

	@Test
	void testClearThenPeekThrows() {
		multi.clear();
		assertThrows(NoSuchElementException.class, () -> multi.peek());
	}

	@Test
	void testClearThenPopThrows() {
		multi.clear();
		assertThrows(NoSuchElementException.class, () -> multi.pop());
	}

	// ---- push/pop interleaved ----

	@Test
	void testInterleavedPushPop() {
		empty.push(1);
		empty.push(2);
		assertEquals(2, empty.pop());
		empty.push(3);
		assertEquals(3, empty.pop());
		assertEquals(1, empty.pop());
		assertTrue(empty.isEmpty());
	}
}
