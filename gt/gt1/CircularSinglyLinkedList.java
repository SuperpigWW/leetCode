import java.util.NoSuchElementException;

/**
 * Your implementation of a CircularSinglyLinkedList without a tail pointer.
 */
public class CircularSinglyLinkedList<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private LinkedNode<T> head;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
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
        
        if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            LinkedNode<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            LinkedNode<T> newNode = new LinkedNode<>(data, current.getNext());
            current.setNext(newNode);
            size++;
        }
    }

    /**
     * Adds the data to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data to the list.");
        }
        
        if (head == null) {
            head = new LinkedNode<>(data);
            head.setNext(head);
        } else {
            LinkedNode<T> newNode = new LinkedNode<>(head.getData(), head.getNext());
            head.setNext(newNode);
            head = new LinkedNode<>(data, newNode);
        }
        size++;
    }

    /**
     * Adds the data to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data to the list.");
        }
        
        if (head == null) {
            head = new LinkedNode<>(data);
            head.setNext(head);
        } else {
            LinkedNode<T> newNode = new LinkedNode<>(head.getData(), head.getNext());
            head.setNext(newNode);
            head = new LinkedNode<>(data, newNode);
        }
        size++;
    }

    /**
     * Removes and returns the data at the specified index.
     *
     * Must be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be between 0 and size - 1 (inclusive).");
        }
        
        if (index == 0) {
            return removeFromFront();
        } else {
            LinkedNode<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            T removed = current.getNext().getData();
            current.setNext(current.getNext().getNext());
            size--;
            return removed;
        }
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (size == 0) {
            throw new NoSuchElementException("Cannot remove from an empty list.");
        }
        
        T removed = head.getData();
        
        if (size == 1) {
            head = null;
        } else {
            head = new LinkedNode<>(head.getNext().getData(), head.getNext().getNext());
        }
        
        size--;
        return removed;
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (size == 0) {
            throw new NoSuchElementException("Cannot remove from an empty list.");
        }
        
        if (size == 1) {
            T removed = head.getData();
            head = null;
            size--;
            return removed;
        }
        
        LinkedNode<T> current = head;
        for (int i = 0; i < size - 2; i++) {
            current = current.getNext();
        }
        
        T removed = current.getNext().getData();
        current.setNext(head);
        size--;
        
        return removed;
    }

    /**
     * Returns the data at the specified index.
     *
     * Should be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be between 0 and size - 1 (inclusive).");
        }
        
        LinkedNode<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        
        return current.getData();
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(n).
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot remove null data from the list.");
        }
        
        if (size == 0) {
            throw new NoSuchElementException("Data not found in the list.");
        }
        
        LinkedNode<T> current = head;
        LinkedNode<T> lastOccurrence = null;
        LinkedNode<T> beforeLast = null;
        LinkedNode<T> beforeCurrent = null;
        
        for (int i = 0; i < size; i++) {
            if (current.getData().equals(data)) {
                lastOccurrence = current;
                beforeLast = beforeCurrent;
            }
            beforeCurrent = current;
            current = current.getNext();
        }
        
        if (lastOccurrence == null) {
            throw new NoSuchElementException("Data not found in the list.");
        }
        
        T removed = lastOccurrence.getData();
        
        if (lastOccurrence == head) {
            return removeFromFront();
        } else {
            beforeLast.setNext(lastOccurrence.getNext());
            size--;
            return removed;
        }
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return the array of length size holding all of the data (not the
     * nodes) in the list in the same order
     */
    public T[] toArray() {
        T[] array = (T[]) new Object[size];
        
        if (size == 0) {
            return array;
        }
        
        LinkedNode<T> current = head;
        for (int i = 0; i < size; i++) {
            array[i] = current.getData();
            current = current.getNext();
        }
        
        return array;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public LinkedNode<T> getHead() {
        return head;
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
}
