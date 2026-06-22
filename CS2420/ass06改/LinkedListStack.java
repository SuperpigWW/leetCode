package assign06;

import java.util.NoSuchElementException;

/**
 * A generic stack implementation backed by a {@link SinglyLinkedList}.
 * Follows last-in-first-out (LIFO) ordering. All operations run in O(1) time
 * because they delegate to the O(1) head-insertion and head-deletion methods
 * of the underlying linked list.
 *
 * @author Haoquan Wang
 * @version 2026-2-23
 *
 * @param <E> - the type of elements held in this stack
 */
public class LinkedListStack<E> implements Stack<E> {

	/** The underlying singly-linked list used to store stack elements. */
	private SinglyLinkedList<E> linkedList;

	/**
	 * Constructs an empty LinkedListStack.
	 */
	public LinkedListStack() {
		linkedList = new SinglyLinkedList<E>();
	}

	/**
	 * Removes all elements from the stack.
	 * O(1).
	 */
	@Override
	public void clear() {
		linkedList.clear();
	}

	/**
	 * Returns true if the stack contains no elements.
	 * O(1).
	 *
	 * @return true if the stack is empty; false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return linkedList.size() == 0;
	}

	/**
	 * Returns, but does not remove, the element at the top of the stack.
	 * O(1).
	 *
	 * @return the element at the top of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */
	@Override
	public E peek() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException("The stack is empty.");
		return linkedList.getFirst();
	}

	/**
	 * Returns and removes the element at the top of the stack.
	 * O(1).
	 *
	 * @return the element at the top of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */
	@Override
	public E pop() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException("The stack is empty.");
		return linkedList.deleteFirst();
	}

	/**
	 * Adds an element to the top of the stack.
	 * O(1).
	 *
	 * @param element - the element to be added
	 */
	@Override
	public void push(E element) {
		linkedList.insertFirst(element);
	}

	/**
	 * Returns the number of elements currently in the stack.
	 * O(1).
	 *
	 * @return the number of elements in the stack
	 */
	@Override
	public int size() {
		return linkedList.size();
	}
}