package assign06;

/**
 * Represents an insertion of one character at a specified position in a text String.
 * The insertion can be reverted (undone) after applying.
 * 
 * @author CS 2420 course staff
 * @version February 19, 2026
 */
public class Edit {
	private char character;
	private int position;
	private boolean applied;

	/**
	 * Constructs an Edit representing an insertion of one character.
	 * 
	 * @param character - character to be inserted
	 * @param position - to insert the character in a string
	 */
	public Edit(char character, int position) {
		this.character = character;
		this.position = position;
		applied = false;
	}
	
	/**
	 * Applies this edit to a given StringBuilder to insert one character.
	 * 
	 * @param textString - a StringBuilder to modify
	 */
	public void apply(StringBuilder textString) {
		textString.insert(position, character);
		applied = true;
	}
	
	/**
	 * Reverses an edit on a given StringBuilder. This is intended for working with the
	 * same StringBuilder that was passed to apply, but that is not required.
	 * If the edit was not previously applied, or was previously reverted without re-applying,
	 * this method has no effect.
	 * 
	 * @param textString - a StringBuilder to modify
	 */
	public void revert(StringBuilder textString) {
		if(applied)
			textString.deleteCharAt(position);
		applied = false;
	}
	
	/**
	 * Gets a textual representation of this edit.
	 * This is helpful for testing.
	 * 
	 * @return a String representation of this Edit
	 */
	public String toString() {
		return "insert " + character + " at " + position;
	}
}
