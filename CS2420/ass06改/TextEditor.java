package assign06;

/**
 * Simulates the edit, undo, and redo functionality of a text editor. Insert 
 * updates the current String and the stack of undo actions. Undo reverses
 * the most recent edit and updates the redo stack. Redo reapplies the last
 * undone edit and updates the undo stack.
 * 
 * @author CS 2420 course staff and Haoquan Wang
 * @version 2026-2-25
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
		Edit edit = new Edit(character, position);
        edit.apply(text);
        undoStack.push(edit);
        while (!redoStack.isEmpty())
            redoStack.pop();
	}
	
	/**
	 * Undoes one edit that was previously applied to the text.
	 * This is done in the reverse of the order in which they were applied.
	 * If there are no edits to undo, this method has no effect.
	 */
	public void undo() {
		if (undoStack.isEmpty())
            return;
        Edit edit = undoStack.pop();
        edit.revert(text);
        redoStack.push(edit);
	}
	
	/**
	 * Re-applies one edit that was previously undone.
	 * This is done in the reverse of the order in which they were undone.
	 * If there are no undone edits to re-apply, this has no effect.
	 */
	public void redo() {
		if (redoStack.isEmpty())
            return;
        Edit edit = redoStack.pop();
        edit.apply(text);
        undoStack.push(edit);
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
		SinglyLinkedList<Edit> result = new SinglyLinkedList<Edit>();
        Stack<Edit> temp = new LinkedListStack<Edit>();
        while (!undoStack.isEmpty()) {
            temp.push(undoStack.pop());
        }

        while (!temp.isEmpty()) {
            Edit edit = temp.pop();
            result.insert(result.size(), edit);   
            undoStack.push(edit);  
        }

        return result;
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
