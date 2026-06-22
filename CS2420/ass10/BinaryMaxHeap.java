package assign10;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.List;

/**
 * Generic BinaryMaxHeap implementation using a priority queue interface
 * Can either use items that are Comparable or can be given a comparator to decide where elements go
 *
 * @author Haoquan Wang and Jake Scott
 * @version 2026-4-6
 */
public class BinaryMaxHeap<E> implements PriorityQueue<E> {

    private E[] heap;
    private int size;
    private Comparator<? super E> cmp = null;
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Creates an empty binary max heap using comparable
     */
    public BinaryMaxHeap() {
        buildList(DEFAULT_CAPACITY);
        this.size = 0;
    }

    /**
     * Creates an empty binary max heap using the comparator provided
     *
     * @param cmp The comparator to use for comparison between elements
     */
    public BinaryMaxHeap(Comparator<? super E> cmp) {
        buildList(DEFAULT_CAPACITY);
        this.size = 0;
        this.cmp = cmp;
    }

    /**
     * Creates a binary max heap with the given elements in the list
     *
     * @param list The list of items to put in the binary max heap
     */
    public BinaryMaxHeap(List<? extends E> list) {
        buildList(list.size());
        this.size = list.size();

        for (int i = 0; i < list.size(); i++) {
            this.heap[i] = list.get(i);
        }
        buildHeap();
    }

    /**
     * Creates a binary max heap with the given elements in the list
     * AND uses the giving comparator to make comparisons
     *
     * @param list The list of items to put in the binary max heap
     */
    public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp) {
        buildList(list.size());
        this.size = list.size();
        this.cmp = cmp;

        for (int i = 0; i < list.size(); i++) {
            this.heap[i] = list.get(i);
        }
        buildHeap();
    }

    /**
     * Helper method to create a generic basic array
     * @param listSize The size of the generic basic array to create
     */
    @SuppressWarnings("unchecked")
    private void buildList(int listSize) {
        int capacity = Math.max(listSize, DEFAULT_CAPACITY);
        this.heap = (E[]) new Object[capacity];
    }

    /**
     * Swaps two nodes in the heap
     * @param i the first nodes index
     * @param j the second node index
     */
    private void swap(int i, int j) {
        if (i < 0 || j < 0 || i >= this.heap.length || j >= this.heap.length) {
            return;
        }
        E temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    /**
     * Doubles the backing array when the heap becomes full
     */
    @SuppressWarnings("unchecked")
    private void dilatation() {
        if (size == heap.length) {
            int largerCapacity = heap.length * 2;
            E[] largerHeap = (E[]) new Object[largerCapacity];
            for (int i = 0; i < size; i++) {
                largerHeap[i] = heap[i];
            }
            this.heap = largerHeap;
        }
    }

    /**
     * Helper method for comparisons
     * Uses a comparator if there is one
     * Otherwise tries using the objects compareTo method
     *
     * @param e1 The first element to compare with
     * @param e2 The second element to compare with
     * @return The comparison result
     */
    @SuppressWarnings("unchecked")
    private int innerCompare(E e1, E e2) {
        if (this.cmp != null)
            return cmp.compare(e1, e2);
        else
            return ((Comparable<? super E>) e1).compareTo(e2);
    }

    /**
     * Moves the element up until it is no longer greater than its parent
     * @param index The index of the element to move up
     */
    private void percolateUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (innerCompare(heap[index], heap[parentIndex]) > 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else
                break;
        }
    }

    /**
     * Moves the element down until it is greater/equal to both children
     * @param index The index of the element to move up
     */
    private void percolateDown(int index) {
        while (true) {
            int leftChild = index * 2 + 1;
            int rightChild = index * 2 + 2;
            int largest = index;

            if (leftChild < size && innerCompare(heap[leftChild], heap[largest]) > 0) {
                largest = leftChild;
            }
            if (rightChild < size && innerCompare(heap[rightChild], heap[largest]) > 0) {
                largest = rightChild;
            }

            if (largest != index) {
                swap(index, largest);
                index = largest;
            } else {
                break;
            }
        }
    }

    /**
     * Helper method to populate/build the heap
     */
    private void buildHeap() {
        for (int i = (size / 2) - 1; i >= 0; i--) {
            percolateDown(i);
        }
    }

    /**
     * Adds the given element to the binary max heap
     * @param item The element to add
     */
    @Override
    public void add(E item) {
        dilatation();
        heap[size] = item;
        percolateUp(size);
        size++;
    }

    /**
     * Gets the top element in the binary max heap
     * @return
     * @throws NoSuchElementException
     */
    @Override
    public E peek() throws NoSuchElementException {
        if (size == 0)
            throw new NoSuchElementException("The queue is empty");
        return heap[0];
    }

    /**
     * Gets and returns the max element in the heap
     * @return The max element
     * @throws NoSuchElementException when the queue is empty
     */
    @Override
    public E extractMax() throws NoSuchElementException {
        if (size == 0)
            throw new NoSuchElementException("The queue is empty");

        E max = heap[0];
        heap[0] = heap[size - 1];
        heap[size - 1] = null;
        size--;

        if (size > 0)
            percolateDown(0);
        return max;
    }

    /**
     * Gets the size of the binary max heap
     * @return The size of the binary max heap
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if the bianry heap is empty
     * @return True if the heap is empty, false if it has elements
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the binary max heap
     */
    @Override
    public void clear() {
        this.size = 0;
    }

    /**
     * Converts the heap into an array
     * @return The array of elements in the heap
     */
    @Override
    public Object[] toArray() {
        Object[] result = new Object[this.size];
        for (int i = 0; i < this.size; i++) {
            result[i] = this.heap[i];
        }
        return result;
    }

}
