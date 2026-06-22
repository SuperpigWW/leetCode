package assign06;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Comprehensive tests for the TextEditor class.
 *
 * @author CS 2420 course staff and Haoquan Wang
 * @version 2026-2-25
 */
class TextEditorTester {

	private Edit[] mediumEdits;
	private TextEditor empty, small, medium;

	@BeforeEach
	void setUp() throws Exception {
		empty = new TextEditor();
		small = new TextEditor();
		medium = new TextEditor();

		// Insert characters into a TextEditor
		small.insert('e', 0);
		small.insert('l', 1);
		small.insert('l', 2);
		small.insert('h', 0); // <- characters can be inserted at any valid index
		small.insert('o', 4);
		// Now small should contain "hello"

		// Edit objects can also be made for testing the history method
		mediumEdits = new Edit[29];
		for (int i = 0; i < 26; i++) {
			mediumEdits[i] = new Edit((char) ('a' + i), i);
			medium.insert((char) ('a' + i), i);
		}
		mediumEdits[26] = new Edit('3', 3);
		mediumEdits[27] = new Edit('3', 3);
		mediumEdits[28] = new Edit('3', 3);
		medium.insert('3', 3);
		medium.insert('3', 3);
		medium.insert('3', 3);
	}

	@Test
	void testEmptytoString() {
		assertEquals("", empty.toString());
	}

	@Test
	void testSmalltoString() {
		assertEquals("hello", small.toString());
	}

	// ---- insert ----

	@Test
	void testEmptyInsert() {
		empty.insert('Y', 0);
		assertEquals("Y", empty.toString());
	}

	@Test
	void testInsertAtBeginning() {
		small.insert('!', 0);
		assertEquals("!hello", small.toString());
	}

	@Test
	void testInsertAtEnd() {
		small.insert('!', 5);
		assertEquals("hello!", small.toString());
	}

	@Test
	void testInsertAtMiddle() {
		small.insert('-', 2);
		assertEquals("he-llo", small.toString());
	}

	@Test
	void testInsertMultipleCharacters() {
		empty.insert('a', 0);
		empty.insert('b', 1);
		empty.insert('c', 2);
		assertEquals("abc", empty.toString());
	}

	@Test
	void testInsertInvalidIndexThrows() {
		assertThrows(Exception.class, () -> small.insert('x', 99));
	}

	// ---- new tests ----

	@Test
	void testEmptyInsertUndo() {
		empty.insert('Z', 0);
		empty.undo();
		assertEquals("", empty.toString());
	}

	@Test
	void testUndoOnEmptyHasNoEffect() {
		empty.undo(); // should not throw
		assertEquals("", empty.toString());
	}

	@Test
	void testUndoOnceFromSmall() {
		small.undo(); // removes last insert ('o' at 4)
		assertEquals("hell", small.toString());
	}

	@Test
	void testUndoAllFromSmall() {
		for (int i = 0; i < 5; i++)
			small.undo();
		assertEquals("", small.toString());
	}

	@Test
	void testUndoMoreThanInsertedHasNoEffect() {
		for (int i = 0; i < 5; i++)
			small.undo();
		small.undo(); // extra undo — should have no effect
		assertEquals("", small.toString());
	}

	@Test
	void testUndoRestoresTextCorrectly() {
		small.insert('!', 5); // "hello!"
		small.undo();
		assertEquals("hello", small.toString());
	}

	// ---- redo ----

	@Test
	void testEmptyInsertUndoRedo() {
		empty.insert('Z', 0);
		empty.undo();
		empty.redo();
		assertEquals("Z", empty.toString());
	}

	@Test
	void testRedoOnEmptyHasNoEffect() {
		empty.redo(); // should not throw
		assertEquals("", empty.toString());
	}

	@Test
	void testRedoWithoutUndoHasNoEffect() {
		small.redo();
		assertEquals("hello", small.toString());
	}

	@Test
	void testUndoThenRedo() {
		small.undo();
		small.redo();
		assertEquals("hello", small.toString());
	}

	@Test
	void testUndoAllThenRedoAll() {
		for (int i = 0; i < 5; i++)
			small.undo();
		for (int i = 0; i < 5; i++)
			small.redo();
		assertEquals("hello", small.toString());
	}

	@Test
	void testRedoMoreThanUndoneHasNoEffect() {
		small.undo();
		small.redo();
		small.redo(); // extra redo — no effect
		assertEquals("hello", small.toString());
	}

	@Test
	void testInsertClearsRedoStack() {
		small.undo();       // undo 'o' at 4 → "hell"
		small.insert('!', 4); // new insert → redo stack should be cleared
		small.redo();       // should have no effect since redo stack was cleared
		assertEquals("hell!", small.toString());
	}

	@Test
	void testUndoRedoMultipleTimes() {
		small.undo(); // "hell"
		small.undo(); // "hel"
		small.redo(); // "hell"
		small.redo(); // "hello"
		assertEquals("hello", small.toString());
	}

	// ---- history ----

	@Test
	void testHistoryOnEmpty() {
		SinglyLinkedList<Edit> hist = empty.history();
		assertEquals(0, hist.size());
	}

	@Test
	void testHistorySizeAfterSmallInserts() {
		SinglyLinkedList<Edit> hist = small.history();
		assertEquals(5, hist.size());
	}

	@Test
	void testMediumHistorySize() {
		SinglyLinkedList<Edit> hist = medium.history();
		assertEquals(mediumEdits.length, hist.size());
	}

	@Test
	void testHistoryDoesNotChangeText() {
		String before = small.toString();
		small.history();
		assertEquals(before, small.toString());
	}

	@Test
	void testHistoryDoesNotChangeSize() {
		small.history();
		for (int i = 0; i < 5; i++)
			small.undo();
		assertEquals("", small.toString());
	}

	@Test
	void testHistoryAfterUndo() {
		small.undo(); // undo last edit
		SinglyLinkedList<Edit> hist = small.history();
		assertEquals(4, hist.size()); // undone edit not in history
	}

	@Test
	void testHistoryAfterUndoAndRedo() {
		small.undo();
		small.redo();
		SinglyLinkedList<Edit> hist = small.history();
		assertEquals(5, hist.size()); 
	}

	@Test
	void testHistoryAfterInsertClearsUndoneEdits() {
		small.undo();         // undo 'o'
		small.insert('!', 4); 
		SinglyLinkedList<Edit> hist = small.history();
		assertEquals(5, hist.size()); // 4 original + 1 new
	}

	@Test
	void testHistoryOrderOldestFirst() {
		TextEditor editor = new TextEditor();
		editor.insert('a', 0);
		editor.insert('b', 1);
		editor.insert('c', 2);
		SinglyLinkedList<Edit> hist = editor.history();
		// Oldest edit should be "insert a at 0"
		assertEquals("insert a at 0", hist.get(0).toString());
		assertEquals("insert b at 1", hist.get(1).toString());
		assertEquals("insert c at 2", hist.get(2).toString());
	}

	@Test
	void testHistoryCalledTwiceGivesSameSize() {
		int first = small.history().size();
		int second = small.history().size();
		assertEquals(first, second);
	}
}