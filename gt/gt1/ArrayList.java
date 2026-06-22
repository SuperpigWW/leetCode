import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayList.
 */
public class ArrayList<T> {

    /*
     * The initial capacity of the ArrayList.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 9;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new ArrayList.
     * 
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast an Object[] to a T[] to get the generic typing.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Adds the data to the front of the list.
     *
     * This add may require elements to be shifted.
     *
     * Method should run in O(n) time.
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data to the list.");
        }
        
        if (size == backingArray.length) {
            resize();
        }
        
        for (int i = size; i > 0; i--) {
            backingArray[i] = backingArray[i - 1];
        }
        
        backingArray[0] = data;
        size++;
    }

    /**
     * Adds the data to the back of the list.
     *
     * Method should run in amortized O(1) time.
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data to the list.");
        }
        
        if (size == backingArray.length) {
            resize();
        }
        
        backingArray[size] = data;
        size++;
    }

    /**
     * Adds the data at the specified index.
     *
     * This add may require elements to be shifted.
     *
     * Method should run in O(n) time.
     *
     * @param index the index at which to add the new data
     * @param data the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index must be between 0 and size (inclusive).");
        }
        
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data to the list.");
        }
        
        if (size == backingArray.length) {
            resize();
        }
        
        for (int i = size; i > index; i--) {
            backingArray[i] = backingArray[i - 1];
        }
        
        backingArray[index] = data;
        size++;
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Do not shrink the backing array.
     *
     * This remove may require elements to be shifted.
     *
     * Method should run in O(n) time.
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (size == 0) {
            throw new NoSuchElementException("Cannot remove from an empty list.");
        }
        
        T removed = backingArray[0];
        
        for (int i = 0; i < size - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }
        
        backingArray[size - 1] = null;
        size--;
        
        return removed;
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Do not shrink the backing array.
     *
     * Method should run in O(1) time.
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (size == 0) {
            throw new NoSuchElementException("Cannot remove from an empty list.");
        }
        
        T removed = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        
        return removed;
    }

    /**
     * Removes and returns the data at the specified index.
     *
     * Do not shrink the backing array.
     *
     * This remove may require elements to be shifted.
     *
     * Method should run in O(n) time.
     *
     * @param index the index of the data to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be between 0 and size - 1 (inclusive).");
        }
        
        T removed = backingArray[index];
        
        for (int i = index; i < size - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }
        
        backingArray[size - 1] = null;
        size--;
        
        return removed;
    }

    /**
     * Returns the data at the specified index.
     *
     * Method should run in O(1) time.
     *
     * @param index the index of the data to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be between 0 and size - 1 (inclusive).");
        }
        
        return backingArray[index];
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Method should run in O(1) time.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the list.
     *
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     *
     * Method should run in O(1) time.
     */
    public void clear() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the backing array of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        return backingArray;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Private helper method to resize the backing array to twice its current capacity.
     */
    private void resize() {
        T[] newArray = (T[]) new Object[backingArray.length * 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = backingArray[i];
        }
        backingArray = newArray;
    }
}
