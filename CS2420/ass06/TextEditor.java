package assign06;

/**
 * Simulates the edit, undo, and redo functionality of a text editor. Insert 
 * updates the current String and the stack of undo actions. Undo reverses
 * the most recent edit and updates the redo stack. Redo reapplies the last
 * undone edit and updates the undo stack.
 * 
 * @author CS 2420 course staff and ***FILL IN STUDENT NAME***
 * @version ***FILL IN DATE***
 */
public class TextEditor {
	private StringBuilder text;
	private Stack<Edit> undoStack, redoStack;
	
	/**
	 * Constructs a TextEditor with empty text and undo/redo stacks.
	 */
	public TextEditor() {
		text = new StringBuilder();
		undoStack = new LinkedListStack<Edit>();
		redoStack = new LinkedListStack<Edit>();
	}
	
	/**
	 * Inserts one character at a specified position in the text.
	 * Note that this will result in an exception if the index is not valid.
	 * You don't need to handle or check for this situation in this method.
	 * 
	 * @param character to insert
	 * @param position in string at which to insert
	 */
	public void insert(char character, int position) {
		// TODO: Fill in this method
	}
	
	/**
	 * Undoes one edit that was previously applied to the text.
	 * This is done in the reverse of the order in which they were applied.
	 * If there are no edits to undo, this method has no effect.
	 */
	public void undo() {
		// TODO: Fill in this method
	}
	
	/**
	 * Re-applies one edit that was previously undone.
	 * This is done in the reverse of the order in which they were undone.
	 * If there are no undone edits to re-apply, this has no effect.
	 */
	public void redo() {
		// TODO: Fill in this method
	}
	
	/**
	 * Get a list of Edits representing the history of text edits.
	 * This does not include edits that have been undone.
	 * The state of the object after calling this method must be exactly 
	 * the same as before.
	 * Edits are ordered from oldest to newest.
	 * 
	 * @return list of Edits in the order in which they were applied
	 */
	public SinglyLinkedList<Edit> history() {
		// TODO: Fill in this method
		return null;
	}
	
	/**
	 * Get the text currently held in the editor.
	 * 
	 * @return a string representing the text currently in the editor
	 */
	public String toString() {
		return text.toString();
	}
}
