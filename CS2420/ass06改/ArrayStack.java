package assign06;

import java.util.NoSuchElementException;

/** 
 * This class represents a last-in-first-out (LIFO) stack data structure 
 * (backed by a basic array).
 * 
 * @author CS 2420 course staff
 * @version February 19, 2026
 */
public class ArrayStack<E> implements Stack<E> {
	private E[] arr;
	private int topIndex;
	
	@SuppressWarnings("unchecked")
	public ArrayStack() {
		this.arr = (E[])new Object[100]; 
		this.topIndex = -1;
	}

	/**
	 * Removes all of the elements from the stack.
	 */
	public void clear() {
		this.topIndex = -1;
	}

	/**
	 * Indicates whether the stack contains any elements.
	 * 
	 * @return true if the stack contains no elements; false, otherwise.
	 */
	public boolean isEmpty() {
		return this.topIndex == -1;
	}

	/**
	 * Returns, but does not remove, the element at the top of the stack.
	 * 
	 * @return the element at the top of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */
	public E peek() throws NoSuchElementException {
		if(this.topIndex == -1)
			throw new NoSuchElementException();
		return this.arr[this.topIndex];
	}

	/**
	 * Returns and removes the item at the top of the stack.
	 * 
	 * @return the element at the top of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */
	public E pop() throws NoSuchElementException {
		if(this.topIndex == -1)
			throw new NoSuchElementException();
		return this.arr[this.topIndex--];    
	}

	/**
	 * Adds a given element to the stack, putting it at the top of the stack.
	 * 
	 * @param element - the element to be added
	 */
	@SuppressWarnings("unchecked")
	public void push(E element) {
		// expand the backing array, as needed
		if(this.topIndex + 1 == this.arr.length) {
			Object[] temp = new Object[this.arr.length * 2];
			for(int i = 0; i < this.arr.length; i++)
				temp[i] = this.arr[i];
			this.arr = (E[])temp;  
		}
		
		this.arr[++this.topIndex] = element;
	}

	/**
	 * Indicates the number of elements in the stack.
	 * 
	 * @return the number of elements in the stack
	 */
	public int size() {
		return this.topIndex + 1;
	}
}