package assign08;

import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * An interface for representing a sorted set of generically-typed items. By
 * definition, a set contains no duplicate items. The items are ordered using
 * their natural ordering (i.e., each item must be Comparable). Note that this
 * interface is much like Java's SortedSet, but simpler.
 * 
 * Note that because this interface inherits from the Iterable interface, any 
 * class implementing SortedSet must also implement Iterable.
 * 
 * @author CS 2420 course staff
 * @version March 5, 2026
 */
public interface SortedSet<Type extends Comparable<? super Type>> extends Iterable<Type>{
	/**
	 * Ensures that this set contains the specified item.
	 * 
	 * @param item - the item whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         the input item was actually inserted); otherwise, returns false
	 */
	public boolean add(Type item);

	/**
	 * Ensures that this set contains all items in the specified collection.
	 * Unlike add, this does not return anything.
	 * 
	 * @param items - the collection of items whose presence is ensured in this set
	 */
	public void addAll(Collection<? extends Type> items);

	/**
	 * Determines if there is an item in this set that is equal to the specified
	 * item.
	 * 
	 * @param item - the item sought in this set
	 * @return true if there is an item in this set that is equal to the input item;
	 *         otherwise, returns false
	 */
	public boolean contains(Type item);

	/**
	 * Ensures that this set does not contain the specified item.
	 * 
	 * @param item - the item whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         the input item was actually removed); otherwise, returns false
	 */
	public boolean remove(Type item);

	/**
	 * Returns the first (i.e., smallest) item in this set.
	 * 
	 * @throws NoSuchElementException if the set is empty
	 */
	public Type first() throws NoSuchElementException;

	/**
	 * Returns the last (i.e., largest) item in this set.
	 * 
	 * @throws NoSuchElementException if the set is empty
	 */
	public Type last() throws NoSuchElementException;

	/**
	 * Returns the number of items in this set.
	 */
	public int size();

	/**
	 * Returns true if this set contains no items.
	 */
	public boolean isEmpty();

	/**
	 * Removes all items from this set. The set will be empty after this method
	 * call.
	 */
	public void clear();
}
