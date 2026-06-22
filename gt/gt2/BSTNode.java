/**
 * Node class used for implementing the BST.
 *
 * DO NOT MODIFY THIS FILE!!
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class BSTNode<T extends Comparable<? super T>> {

    private T data;
    private BSTNode<T> left;
    private BSTNode<T> right;

    /**
     * Constructs a BSTNode with the given data.
     *
     * @param data the data stored in the new node
     */
    public BSTNode(T data) {
        this.data = data;
    }

    /**
     * Gets the data.
     *
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * Gets the left child.
     *
     * @return the left child
     */
    public BSTNode<T> getLeft() {
        return left;
    }

    /**
     * Gets the right child.
     *
     * @return the right child
     */
    public BSTNode<T> getRight() {
        return right;
    }

    /**
     * Sets the data.
     *
     * @param data the new data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Sets the left child.
     *
     * @param left the new left child
     */
    public void setLeft(BSTNode<T> left) {
        this.left = left;
    }

    /**
     * Sets the right child.
     *
     * @param right the new right child
     */
    public void setRight(BSTNode<T> right) {
        this.right = right;
    }
}
