package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A generic singly-linked list implementation of the {@link List} interface.
 * Elements are stored in a chain of nodes, each pointing to the next node.
 * This list uses 0-based indexing and includes a sentinel (dummy) head node
 * to simplify insertion and deletion logic.
 *
 * @author Haoquan Wang
 * @version 2026-2-22
 *
 * @param <E> - the type of elements held in this list
 */
public class SinglyLinkedList<E> implements List<E> {

	/**
	 * A private static inner class representing a single node in the linked list.
	 *
	 * @param <E> - the type of data stored in the node
	 */
	private static class Node<E> {
		/** The data stored in this node. */
		E data;

		/** Reference to the next node in the list. */
		Node<E> next;

		/**
		 * Constructs a node with the given data and a null next reference.
		 *
		 * @param data - the element to store in this node
		 */
		Node(E data) {
			this.data = data;
			this.next = null;
		}
	}

	/** Sentinel (dummy) node before the first real node; never holds real data. */
	private Node<E> startDummy;

	/** The number of elements currently in the list. */
	private int size;

	/**
	 * Constructs an empty SinglyLinkedList with a dummy head node.
	 */
	public SinglyLinkedList() {
		startDummy = new Node<>(null);
		size = 0;
	}

	/**
	 * Inserts an element at the beginning of the list.
	 * O(1) for a singly-linked list.
	 *
	 * @param element - the element to add
	 */
	@Override
	public void insertFirst(E element) {
		Node<E> elementNode = new Node<>(element);
		elementNode.next = startDummy.next;
		startDummy.next = elementNode;
		size++;
	}

	/**
	 * Inserts an element at a specific position in the list.
	 * O(N) for a singly-linked list.
	 *
	 * @param index   - the specified position (0-based)
	 * @param element - the element to add
	 * @throws IndexOutOfBoundsException if index is out of range (index &lt; 0 || index &gt; size())
	 */
	@Override
	public void insert(int index, E element) throws IndexOutOfBoundsException {
		if (index < 0 || index > size())
			throw new IndexOutOfBoundsException("Wrong index input, please choose a valid one.");
		Node<E> temp = startDummy;
		Node<E> elementNode = new Node<>(element);
		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}
		elementNode.next = temp.next;
		temp.next = elementNode;
		size++;
	}

	/**
	 * Gets the first element in the list without removing it.
	 * O(1) for a singly-linked list.
	 *
	 * @return the first element in the list
	 * @throws NoSuchElementException if the list is empty
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException("This list is empty.");
		return startDummy.next.data;
	}

	/**
	 * Gets the element at a specific position in the list without removing it.
	 * O(N) for a singly-linked list.
	 *
	 * @param index - the specified position (0-based)
	 * @return the element at the given position
	 * @throws IndexOutOfBoundsException if index is out of range (index &lt; 0 || index &gt;= size())
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException("Wrong index input, please choose a valid one.");
		Node<E> temp = startDummy;
		for (int i = 0; i <= index; i++)
			temp = temp.next;
		return temp.data;
	}

	/**
	 * Deletes and returns the first element from the list.
	 * O(1) for a singly-linked list.
	 *
	 * @return the first element in the list
	 * @throws NoSuchElementException if the list is empty
	 */
	@Override
	public E deleteFirst() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException("This list is empty.");
		E firstDeleteNodeData = startDummy.next.data;
		startDummy.next = startDummy.next.next;
		size--;
		return firstDeleteNodeData;
	}

	/**
	 * Deletes and returns the element at a specific position in the list.
	 * O(N) for a singly-linked list.
	 *
	 * @param index - the specified position (0-based)
	 * @return the element previously at the given position
	 * @throws IndexOutOfBoundsException if index is out of range (index &lt; 0 || index &gt;= size())
	 */
	@Override
	public E delete(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException("Wrong index input, please choose a valid one.");
		Node<E> temp = startDummy;
		for (int i = 0; i < index; i++)
			temp = temp.next;
		E deleteNodeData = temp.next.data;
		temp.next = temp.next.next;
		size--;
		return deleteNodeData;
	}

	/**
	 * Determines the index of the first occurrence of the specified element in the list,
	 * or -1 if this list does not contain the element.
	 * O(N) for a singly-linked list.
	 *
	 * @param element - the element to search for
	 * @return the index of the first occurrence; -1 if the element is not found
	 */
	@Override
	public int indexOf(E element) {
		Node<E> temp = startDummy.next;
		for (int i = 0; i <= size - 1; i++) {
			if (temp.data.equals(element))
				return i;
			temp = temp.next;
		}
		return -1;
	}

	/**
	 * Returns the number of elements in this list.
	 * O(1) for a singly-linked list.
	 *
	 * @return the number of elements in this list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns true if this list contains no elements.
	 * O(1) for a singly-linked list.
	 *
	 * @return true if this list is empty; false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Removes all elements from this list by resetting the dummy head's next
	 * reference and setting size to zero.
	 * O(1) for a singly-linked list.
	 */
	@Override
	public void clear() {
		size = 0;
		startDummy.next = null;
	}

	/**
	 * Returns an array containing all elements in this list in proper sequence
	 * (from first element to last element).
	 * O(N) for a singly-linked list.
	 *
	 * @return an array containing all elements in this list, in order
	 */
	@Override
	public Object[] toArray() {
		Object[] arr = new Object[size];
		Node<E> temp = startDummy.next;
		for (int i = 0; i < size; i++) {
			arr[i] = temp.data;
			temp = temp.next;
		}
		return arr;
	}

	/**
	 * Returns an iterator over the elements in this list in proper sequence
	 * (from first element to last element).
	 *
	 * @return an iterator over the elements in this list
	 */
	@Override
	public Iterator<E> iterator() {
		return new SinglyLinkedListIterator();
	}

	/**
	 * A private inner class that implements the {@link Iterator} interface for
	 * traversing a {@link SinglyLinkedList} from head to tail.
	 * Each of {@code hasNext}, {@code next}, and {@code remove} runs in O(1) time.
	 */
	private class SinglyLinkedListIterator implements Iterator<E> {

		private Node<E> prevPrev;
		private Node<E> prev;
		private Node<E> temp;

		/**
		 * Constructs a new iterator positioned before the first element.
		 */
		SinglyLinkedListIterator() {
			prevPrev = null;
			prev = startDummy;
			temp = startDummy.next;
		}

		/**
		 * Returns true if the iteration has more elements.
		 * O(1).
		 *
		 * @return true if there is a next element; false otherwise
		 */
		@Override
		public boolean hasNext() {
			return temp != null;
		}

		/**
		 * Returns the next element in the iteration and advances the iterator.
		 * O(1).
		 *
		 * @return the next element in the list
		 * @throws NoSuchElementException if there are no more elements
		 */
		@Override
		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			prevPrev = prev;
			prev = temp;
			temp = temp.next;
			return prev.data;
		}

		/**
		 * Removes the element most recently returned by {@code next} from the list.
		 * O(1). Must be called after at least one call to {@code next}.
		 *
		 * @throws IllegalStateException if {@code next} has not been called yet
		 */
		@Override
		public void remove() {
			if (prev == startDummy)
				throw new IllegalStateException("Call next() before remove().");
			prevPrev.next = temp;
			prev = prevPrev;
			size--;
		}
	}
}