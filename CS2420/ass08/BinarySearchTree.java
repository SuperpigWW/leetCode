package assign08;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class represents a binary search tree (BST) that implements the SortedSet interface.
 * Elements are stored in their natural ordering (using Comparable), and no duplicate
 * items are allowed. The BST is not guaranteed to be balanced.
 * 
 * @author CS 2420 course staff and Haoquan Wang
 * @version 2026-3-17
 * @param <Type> - the type of elements stored in this BST, must be Comparable
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {

	private BinaryNode<Type> root;

	/**
	 * Creates an empty binary search tree.
	 */
	public BinarySearchTree() {
		root = null;
	}

	/**
	 * Returns an iterator over the elements in this BST in ascending (inorder) order.
	 * 
	 * @return an Iterator that traverses elements from least to greatest
	 */
	@Override
	public Iterator<Type> iterator() {
		return new BSTIterator();
	}

	/**
	 * An iterator for BinarySearchTree that traverses elements in ascending order
	 * using inorder traversal. Supports hasNext, next, and remove operations.
	 */
	private class BSTIterator implements Iterator<Type> {

		private BinaryNode<Type> nextNode;
		private BinaryNode<Type> lastReturned;

		/**
		 * Creates a new BSTIterator starting at the smallest element in the tree.
		 */
		BSTIterator() {
			nextNode = root;
			if (nextNode != null)
				while (nextNode.getLeftChild() != null)
					nextNode = nextNode.getLeftChild();
			lastReturned = null;
		}

		/**
		 * Returns true if there are more elements to iterate over.
		 * 
		 * @return true if the iteration has more elements
		 */
		@Override
		public boolean hasNext() {
			return nextNode != null;
		}

		/**
		 * Returns the next element in ascending order.
		 * 
		 * @return the next element
		 * @throws NoSuchElementException if there are no more elements
		 */
		@Override
		public Type next() {
			if (!hasNext())
				throw new NoSuchElementException();
			lastReturned = nextNode;
			nextNode = findNext(nextNode);
			return lastReturned.getData();
		}

		/**
		 * Removes the element most recently returned by next() from the BST.
		 * 
		 * @throws IllegalStateException if next() has not been called yet
		 */
		@Override
		public void remove() {
			if (lastReturned == null)
				throw new IllegalStateException();

			// If lastReturned has two children, the BST remove will replace its data
			// with successor's data and delete the successor node. The iterator's
			// nextNode already points to that successor, so we update nextNode to
			// lastReturned so it re-visits it (now with successor's data).
			if (lastReturned.getLeftChild() != null && lastReturned.getRightChild() != null) {
				nextNode = lastReturned;
			}

			BinarySearchTree.this.remove(lastReturned.getData());
			lastReturned = null;
		}
	}

	/**
	 * Finds the node that contains the next larger element after the given node,
	 * using the BST in-order successor logic.
	 * 
	 * @param node - the current node
	 * @return the node containing the next larger element, or null if none exists
	 */
	private BinaryNode<Type> findNext(BinaryNode<Type> node) {
		if (node.getRightChild() != null)
			return successor(node);
		return nextWithoutRightChild(node);
	}

	/**
	 * Finds the in-order successor by traversing upward when the given node has
	 * no right child. Returns the first ancestor for which the current node is in
	 * the left subtree.
	 * 
	 * @param node - the current node (with no right child)
	 * @return the ancestor that is the next larger element, or null if none
	 */
	private BinaryNode<Type> nextWithoutRightChild(BinaryNode<Type> node) {
		BinaryNode<Type> current = node;
		BinaryNode<Type> parent = node.getParent();
		while (parent != null && current == parent.getRightChild()) {
			current = parent;
			parent = parent.getParent();
		}
		return parent;
	}

	/**
	 * Finds the in-order successor of a node that has a right child.
	 * The successor is the leftmost node in the right subtree.
	 * 
	 * @param node - the node whose successor is sought (must have a right child)
	 * @return the node containing the smallest value greater than node's value
	 */
	private BinaryNode<Type> successor(BinaryNode<Type> node) {
		BinaryNode<Type> current = node.getRightChild();
		while (current.getLeftChild() != null)
			current = current.getLeftChild();
		return current;
	}

	/**
	 * Ensures that this set contains the specified item. If the item is already
	 * present, the set is not modified.
	 * 
	 * @param item - the item to be added
	 * @return true if the item was inserted; false if it was already present
	 */
	@Override
	public boolean add(Type item) {
		if (root == null) {
			root = new BinaryNode<Type>(item);
			return true;
		}

		BinaryNode<Type> current = root;
		BinaryNode<Type> parent = null;
		int compare = 0;

		while (current != null) {
			parent = current;
			compare = current.getData().compareTo(item);
			if (compare == 0)
				return false;
			else if (compare > 0)
				current = current.getLeftChild();
			else
				current = current.getRightChild();
		}

		// Create the new node with the correct parent reference
		BinaryNode<Type> newNode = new BinaryNode<Type>(item, null, null, parent);
		if (compare > 0)
			parent.setLeftChild(newNode);
		else
			parent.setRightChild(newNode);

		return true;
	}

	/**
	 * Ensures that this set contains all items in the specified collection.
	 * 
	 * @param items - the collection of items to be added
	 */
	@Override
	public void addAll(Collection<? extends Type> items) {
		if (items == null)
			return;
		for (Type item : items)
			this.add(item);
	}

	/**
	 * Determines if this set contains an item equal to the specified item.
	 * Uses BST search for O(log n) average performance.
	 * 
	 * @param item - the item to search for
	 * @return true if the item is found; false otherwise
	 */
	@Override
	public boolean contains(Type item) {
		BinaryNode<Type> current = root;
		while (current != null) {
			int cmp = current.getData().compareTo(item);
			if (cmp == 0)
				return true;
			else if (cmp > 0)
				current = current.getLeftChild();
			else
				current = current.getRightChild();
		}
		return false;
	}

	/**
	 * Ensures that this set does not contain the specified item.
	 * Handles all cases: no children, one child, or two children.
	 * Also handles the case where the node to remove is the root.
	 * 
	 * @param item - the item to remove
	 * @return true if the item was removed; false if it was not present
	 */
	@Override
	public boolean remove(Type item) {
		// Find the node to remove
		BinaryNode<Type> current = root;
		while (current != null) {
			int cmp = current.getData().compareTo(item);
			if (cmp == 0)
				break;
			else if (cmp > 0)
				current = current.getLeftChild();
			else
				current = current.getRightChild();
		}

		// Item not found
		if (current == null)
			return false;

		// Case 1: Node has two children — replace with successor's data, then remove successor
		if (current.getLeftChild() != null && current.getRightChild() != null) {
			BinaryNode<Type> succ = successor(current);
			current.setData(succ.getData());
			// Now remove the successor node (which has at most one child — a right child)
			current = succ;
		}

		// At this point, current has at most one child
		// Determine the single child (if any)
		BinaryNode<Type> child = (current.getLeftChild() != null)
				? current.getLeftChild()
				: current.getRightChild();

		if (child != null)
			child.setParent(current.getParent());

		if (current.getParent() == null) {
			// Removing the root
			root = child;
		} else if (current == current.getParent().getLeftChild()) {
			current.getParent().setLeftChild(child);
		} else {
			current.getParent().setRightChild(child);
		}

		return true;
	}

	/**
	 * Returns the first (smallest) item in this set.
	 * 
	 * @return the smallest element
	 * @throws NoSuchElementException if the set is empty
	 */
	@Override
	public Type first() throws NoSuchElementException {
		if (root == null)
			throw new NoSuchElementException("The set is empty");
		BinaryNode<Type> current = root;
		while (current.getLeftChild() != null)
			current = current.getLeftChild();
		return current.getData();
	}

	/**
	 * Returns the last (largest) item in this set.
	 * 
	 * @return the largest element
	 * @throws NoSuchElementException if the set is empty
	 */
	@Override
	public Type last() throws NoSuchElementException {
		if (root == null)
			throw new NoSuchElementException("The set is empty");
		BinaryNode<Type> current = root;
		while (current.getRightChild() != null)
			current = current.getRightChild();
		return current.getData();
	}

	/**
	 * Returns the number of items in this set.
	 * 
	 * @return the count of elements
	 */
	@Override
	public int size() {
		int count = 0;
		for (Type element : this)
			count++;
		return count;
	}

	/**
	 * Returns true if this set contains no items.
	 * 
	 * @return true if empty; false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Removes all items from this set. The set will be empty after this call.
	 */
	@Override
	public void clear() {
		root = null;
	}
}