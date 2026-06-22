package assign06;

import java.util.NoSuchElementException;

/**
 * This interface specifies the general behavior of a last-in-first-out (LIFO)
 * stack of elements.
 * 
 * @author CS 2420 course staff
 * @version February 19, 2026
 * 
 * @param <E> - the type of elements contained in the stack
 */
public interface Stack<E> {

	/**
	 * Removes all of the elements from the stack.
	 */
	public void clear();

	/**
	 * Answers whether the stack is empty or contains elements.
	 * 
	 * @return true if the stack contains no elements; false, otherwise.
	 */
	public boolean isEmpty();

	/**
	 * Returns, but does not remove, the element at the top of the stack.
	 * 
	 * @return the element at the top of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */
	public E peek() throws NoSuchElementException;

	/**
	 * Returns and removes the item at the top of the stack.
	 * 
	 * @return the element at the top of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */
	public E pop() throws NoSuchElementException; 

	/**
	 * Adds a given element to the stack, putting it at the top of the stack.
	 * 
	 * @param element - the element to be added
	 */
	public void push(E element); 
	
	/**
	 * Gets the number of elements in the stack.
	 * 
	 * @return the number of elements in the stack
	 */
	public int size();
}