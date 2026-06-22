package assign06;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the TextEditor class.
 * 
 * @author CS 2420 course staff and ***FILL IN STUDENT NAME***
 * @version ***FILL IN DATE***
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
		// Note that inserting at an invalid index will result in an exception
		
		// Edit objects can also be made for testing the history method
		mediumEdits = new Edit[29];
		for(int i = 0; i < 26; i++) {
			mediumEdits[i] = new Edit((char)('a' + i), i);
			medium.insert((char)('a' + i), i);
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
	void testEmptyInsert() {
		empty.insert('Y', 0);
		assertEquals("Y", empty.toString());
	}
	
	@Test
	void testEmptyInsertUndo() {
		empty.insert('Z', 0);
		empty.undo();
		assertEquals("", empty.toString());
	}
	
	@Test
	void testEmptyInsertUndoRedo() {
		empty.insert('Z', 0);
		empty.undo();
		empty.redo();
		assertEquals("Z", empty.toString());
	}
	
	@Test
	void testSmalltoString() {
		assertEquals("hello", small.toString());
	}
	
	@Test
	void testMediumHistorySize() {
		SinglyLinkedList<Edit> hist = medium.history();
		assertEquals(mediumEdits.length, hist.size());
	}
	
	// ---- Add several more tests for the methods you wrote ---- //
	
}
