package assign06;

import java.util.Iterator;
import java.util.Random;

import timing.TimingExperiment;

/**
 * Experiment to measure the running times of removing elements from a linked list using 
 * its iterator, for various problem sizes.
 *
 * @author CS 2420 course staff
 * @version February 19, 2026
 */
public class SinglyLinkedListIteratorRemoveTimingExperiment extends TimingExperiment {
    private static String problemSizeDescription = "number of elements";  
    private static int problemSizeMin = 10000; 
    private static int problemSizeCount = 20; 
    private static int problemSizeStep = 1000;  
    private static int experimentIterationCount = 100;  

    private final Random rng = new Random();
    
    private SinglyLinkedList<Integer> linkedList;

    public static void main(String[] args) {
        SinglyLinkedListIteratorRemoveTimingExperiment timingExperiment = new SinglyLinkedListIteratorRemoveTimingExperiment();
        timingExperiment.printResults();
    }

    public SinglyLinkedListIteratorRemoveTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

	/**
	 * Fills the SinglyLinkedList with the given number of integers.
	 * 
	 * @param problemSize - the number of integers to fill the linked list
	 */
	@Override
    protected void setupExperiment(int problemSize) {
        linkedList = new SinglyLinkedList<Integer>();
        for(int i = 0; i < problemSize; i++) 
            linkedList.insertFirst(rng.nextInt());
    }

	/**
	 * Runs the filterOutEvenNumbersFromLinkedList method.
	 */
	@Override
    protected void runComputation() {
        filterOutEvenNumbersFromLinkedList();
    }

    /**
     * Uses the iterator to remove all even integers from the linked list.
     *
     * @implNote Uses the {@code hasNext()}, {@code next()}, and {@code remove()} methods
     * from the linked list iterator. If these have been implemented to run in O(1), then
     * filtering out the even numbers should run in O(n).
     */
    private void filterOutEvenNumbersFromLinkedList() {
        Iterator<Integer> iterator = linkedList.iterator();
        while(iterator.hasNext()) {
            Integer value = iterator.next();
            if(value % 2 == 0) 
                iterator.remove();
        }
    }
}