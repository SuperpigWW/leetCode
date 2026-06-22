/**
 * Node class used for implementing the LinkedStack and CircularSinglyLinkedList.
 */
public class LinkedNode<T> {

    private T data;
    private LinkedNode<T> next;

    /**
     * Constructs a new LinkedNode with the given data and next node.
     *
     * @param data the data stored in the new node
     * @param next the next node in the list
     */
    public LinkedNode(T data, LinkedNode<T> next) {
        this.data = data;
        this.next = next;
    }

    /**
     * Creates a new node with only data.
     *
     * @param data the data to store in the node
     */
    public LinkedNode(T data) {
        this(data, null);
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
     * Gets the next node.
     *
     * @return the next node
     */
    public LinkedNode<T> getNext() {
        return next;
    }

    /**
     * Sets the next node.
     *
     * @param next the new next node
     */
    public void setNext(LinkedNode<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node containing: " + data;
    }
}
