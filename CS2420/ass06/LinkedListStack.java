package assign06;

import java.util.NoSuchElementException;

public class LinkedListStack<E> implements Stack<E> {
	
	private SinglyLinkedList<E> linkedList;
	
	public LinkedListStack() {
		linkedList = new SinglyLinkedList<E>();
	}

	@Override
	public void clear() {
		linkedList.clear();
	}

	@Override
	public boolean isEmpty() {
		return linkedList.size() == 0;
	}

	@Override
	public E peek() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException("The stack is empty.");
		return linkedList.getFirst();
	}

	@Override
	public E pop() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException("The stack is empty.");
		return linkedList.deleteFirst();
	}

	@Override
	public void push(E element) {
		linkedList.insertFirst(element);
	}

	@Override
	public int size() {
		return linkedList.size();
	}

}
