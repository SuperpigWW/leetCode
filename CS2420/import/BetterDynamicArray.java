package assign09;

/**
 * This class represents a better dynamic array of AudioEvents, doubling the length of
 * the backing array when more space is needed and never shrinking.
 * 
 * @author CS 1420 course staff and ADD STUDENT NAME
 * @version ADD DATE
 */
public class BetterDynamicArray {

	// DO NOT ADD OR REMOVE ANY INSTANCE VARIABLES
	private AudioEvent[] elements; // the backing array
	private int elementCount; // the number of elements

	/**
	 * Creates a dynamic array with space for ten elements, but zero spaces occupied.
	 * 
	 * DO NOT MODIFY THIS METHOD
	 */
	public BetterDynamicArray() {
		elements = new AudioEvent[10];
		elementCount = 0;
	}

	/**
	 * Appends the given AudioEvent to end of this dynamic array.
	 * 
	 * @param value - the AudioEvent to append
	 */
	public void add(AudioEvent value) {
		// TODO: Fill in by calling the insert method appropriately.
	}

	/**
	 * Inserts a given AudioEvent into this dynamic array at a given index.
	 * 
	 * @param index - the index at which to insert
	 * @param value - the AudioEvent to insert
	 * @throws IndexOutOfBoundsException if the given index is out of bounds
	 */
	public void insert(int index, AudioEvent value) {	
		// TODO: Fill in according to steps below.
		
		// Step 1: Add code to ensure the value of index is valid.
		// HINT: The index is valid if it is in the range of indexes currently used by 
		// elements in this dynamic array.  The range is determined by elementCount, 
		// not elements.length.
		
		// Step 2: If there are no free spaces in the backing array, expand.
		// (This step is already done for you.)
		if(elementCount == elements.length) 
			doubleBackingArray();

		// Step 3: Shift the elements at positions index, index + 1, index + 2, and so on
		// up one position.  This makes room for value at index.
		// ADVICE: Try this on paper first since this can be a difficult operation.
		// HINT: It is best to shift the elements if you start at the end because
		// it prevents overwriting values that have not yet been shifted.
		
		// Step 4: Insert value at index.
		
		// Step 5: Update elementCount.
		
		// DO NOT create a new array each time this method is called. If you create 
		// a new array each time, your program will be too slow to earn full credit.
	}
	
	/**
	 * Creates a new array with twice the length as the backing array.
	 * Copies all elements from the backing array to the new array.
	 * Sets the backing array reference to the new array.
	 */
	private void doubleBackingArray() {
		AudioEvent[] largerArray = new AudioEvent[elements.length * 2];
		for(int i = 0; i < elements.length; i++) 
			largerArray[i] = elements[i];			
		elements = largerArray;
	}

	/**
	 * Gets the AudioEvent stored in this dynamic array at the given index.
	 * 
	 * @param index - the index of the element to get
	 * @return the element at the given index
	 * @throws IndexOutOfBoundsException if the given index is out of bounds
	 */
	public AudioEvent get(int index) {		
		// TODO: Fill in and replace "return null" appropriately.

		return null; 
	}

	/**
	 * Returns the number of elements in this dynamic array.
	 * 
	 * @return the number of elements
	 */
	public int size() {
		// TODO: Replace "return 0" appropriately.

		return 0; 
	}

	/**
	 * Sets (i.e., changes) the AudioEvent stored in this dynamic array at the given index
	 * to the given integer.
	 * 
	 * @param index - the index of the element to set
	 * @param value - the new AudioEvent value for setting the element
	 * @throws IndexOutOfBoundsException if the given index is out of bounds
	 */
	public void set(int index, AudioEvent value) {
		// TODO: Fill in.
	}

	/**
	 * Removes the AudioEvent at the given index from this dynamic array. 
	 * 
	 * @param index - the index of the element to delete
	 * @throws IndexOutOfBoundsException if the given index is out of bounds
	 */
	public void remove(int index) {
		// TODO: Fill in. 
		
		// Do not shrink the backing array. 
		// Its length should be the same before and after executing this method.
		
		// Step 1: Add code to ensure the value of index is valid.

		// Step 2: Shift the elements at positions index + 1, index + 2, and so on
		// down one position.  This overwrites the deleted element at index.
		
		// Step 3: Update elementCount.
		
		// DO NOT create a new array each time this method is called. If you create 
		// a new array each time, your program will be too slow to earn full credit.
	}
	
	/**
	 * Removes the first element in the array that is equal to the given value.
	 * If no equal element is found, the array is not changed.
	 * 
	 * @param value - the AudioEvent to be removed
	 */
	public void remove(AudioEvent value) {
		// TODO: Fill in.
		
		// Step 1: iterate over the elements in the array searching for one that
		// is equal to the value (using the equals method). When found, store the
		// index of that element and exit the loop.
		
		// Step 2: If an equal element was found, call the other remove method with
		// its index. If not found, do not remove anything.
	}
	
	/**
	 * Removes all elements from the dynamic array.
	 */
	public void clear() {
		// TODO: Fill in by simply changing the value of elementCount.
		// No elements need to be shifted in this method.
		
		// Do not shrink the backing array. 
		// Its length should be the same before and after executing this method.
	}
	
	/**
	 * Sorts the elements of this dynamic array from smallest to largest.
	 * This depends on your AudioEvent class implementing the
	 * Comparable interface.
	 */
	public void sort() {
		// TODO: Fill in with the appropriate call to a sort method from Java's Arrays class.
		// https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/util/Arrays.html
		// Choose the method that will use the natural ordering of elements and won't
		// run into any issues caused by the empty elements at the end of the array.
		// This will only work correctly if your AudioEvent class correctly implements the 
		// Comparable interface.
	}

	/**
	 * Generates a textual representation of this dynamic array.
	 * 
	 * @return the textual representation
	 * 
	 * DO NOT MODIFY THIS METHOD
	 */
	public String toString() {
		String result = "[";
		if(size() > 0) 
			result += get(0);
		
		for(int i = 1; i < size(); i++) 
			result += ", " + get(i);
		
		return result + "] backing array length: " + elements.length;
	}
}