package assign06;

import java.util.ArrayList;
import java.util.Random;

import timing.TimingExperiment;

/**
 * Experiment to measure the running times of copying the elements of a linked list using 
 * an enhanced for-loop, for various problem sizes.
 *
 * @author CS 2420 course staff
 * @version February 19, 2026
 */
public class SinglyLinkedListForEachTimingExperiment extends TimingExperiment {
    private static String problemSizeDescription = "number of elements";  
    private static int problemSizeMin = 10000;  
    private static int problemSizeCount = 20;  
    private static int problemSizeStep = 1000;  
    private static int experimentIterationCount = 100;  

    private final Random rng = new Random();

    private SinglyLinkedList<Integer> linkedList;
    private ArrayList<Integer> arrayList;

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new SinglyLinkedListForEachTimingExperiment();
        timingExperiment.printResults();
    }

    public SinglyLinkedListForEachTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

	/**
	 * Fills the SinglyLinkedList with the given number of integers and creates an
	 * empty ArrayList.
	 * 
	 * @param problemSize - the number of integers to fill the linked list
	 */
	@Override
    protected void setupExperiment(int problemSize) {
        linkedList = new SinglyLinkedList<Integer>();
        for(int i = 0; i < problemSize; i++)
            linkedList.insertFirst(rng.nextInt());
        arrayList = new ArrayList<Integer>(problemSize);
    }

	/**
	 * Runs the copyLinkedListToArrayList method.
	 */
	@Override
    protected void runComputation() {
        copyLinkedListToArrayList();
    }

    /**
     * Uses an enhanced for-loop to copy the values of a linked list into an ArrayList.
     *
     * @implNote An enhanced for-loop uses the iterator methods {@code hasNext()} and {@code next()}.
     * If these methods are implemented to run in O(1), then the for loop should run in O(N).
     */
    private void copyLinkedListToArrayList() {
        for(Integer value : linkedList) 
            arrayList.add(value);
    }
}