package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E>{
	
	private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
            this.next = null;
        }
        
//        Node(E data, Node nextNode){
//        	this.data = data;
//        	this.next = nextNode;
//        }
    }

    private Node<E> startDummy; 
    private int size;

    public SinglyLinkedList() {
        startDummy = new Node<>(null);
        size = 0;
    }
	
	@Override
	public void insertFirst(E element) {
		Node<E> elementNode = new Node<>(element);
		elementNode.next = startDummy.next;////////////
		startDummy.next = elementNode;
		size ++;
	}

	@Override
	public void insert(int index, E element) throws IndexOutOfBoundsException {
		if (index < 0 || index > size())
			throw new IndexOutOfBoundsException("Wrong index input, please choose a valid one.");
		Node<E> temp = startDummy;
		Node<E> elementNode = new Node<>(element);
		for(int i = 0; i < index; i++) {
			temp = temp.next;
		}
		elementNode.next = temp.next;
		temp.next = elementNode;
		size ++;
	}

	@Override
	public E getFirst() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException("This list is empty.");
		return startDummy.next.data;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException("Wrong index input, please choose a valid one.");
		Node<E> temp = startDummy;
		for(int i = 0; i <= index; i++) 
			temp = temp.next;
		return temp.data;
	}

	@Override
	public E deleteFirst() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException("This list is empty.");
		E firstDeleteNodeData = startDummy.next.data;
		startDummy.next = startDummy.next.next;
		size --;
		return firstDeleteNodeData;
	}

	@Override
	public E delete(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException("Wrong index input, please choose a valid one.");
		Node<E> temp = startDummy;
		for(int i = 0; i < index; i++) 
			temp = temp.next;
		E deleteNodeData = temp.next.data;
		temp.next = temp.next.next;
		size --;
		return deleteNodeData;
	}

	@Override
	public int indexOf(E element) {
		Node<E> temp = startDummy.next;
		for(int i = 0; i <= size - 1; i++) {
			if (temp.data.equals(element))
				return i;
			temp = temp.next;
		}
		return -1;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		size = 0;
		startDummy.next = null;
	}

	@Override
	public Object[] toArray() {
		Object[] arr = new Object[size];
	    Node<E> temp = startDummy.next;
	    for(int i = 0; i < size; i++) {
	        arr[i] = temp.data;
	        temp = temp.next;
	    }
	    return arr;
	}

	@Override
	public Iterator<E> iterator() {
	    return new SinglyLinkedListIterator();
	}

	private class SinglyLinkedListIterator implements Iterator<E> {
	    private Node<E> prevPrev;  
	    private Node<E> prev;      
	    private Node<E> temp;   
	    SinglyLinkedListIterator() {
	        prevPrev = null;
	        prev = startDummy;       
	        temp = startDummy.next; 
	    }

	    @Override
	    public boolean hasNext() {
	        return temp != null;
	    }

	    @Override
	    public E next() {
	        if (!hasNext())
	            throw new NoSuchElementException();
	        prevPrev = prev;
	        prev = temp;
	        temp = temp.next;
	        return prev.data;
	    }

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
