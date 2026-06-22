package assign03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import timing.TimingExperiment;

/**
 * Experiment to measure the running time for the containsAll method for
 * SimplePriorityQueue for various problem sizes.
 *
 * @author CS 2420 course staff
 * @version January 22, 2026
 */
public class SimplePriorityQueueContainsAllTimingExperiment extends TimingExperiment {

	private static String problemSizeDescription = "SPQ size";
	private static int problemSizeMin = 10000;
	private static int problemSizeCount = 19;
	private static int problemSizeStep = 5000;
	private static int experimentIterationCount = 20;

	private final static Random rng = new Random();

	private SimplePriorityQueue<Integer> priorityQueue;
	private ArrayList<Integer> targets;

	public static void main(String[] args) {
		TimingExperiment timingExperiment = new SimplePriorityQueueContainsAllTimingExperiment();

		System.out.println("\n---Computing timing results---\n");
		timingExperiment.printResults();
	}

	public SimplePriorityQueueContainsAllTimingExperiment() {
		super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
	}

	/**
	 * Fills the priority queue and targets list with the given number of integers.
	 * 
	 * @param problemSize - the number of integers to fill the priority queue
	 */
	@Override
	protected void setupExperiment(int problemSize) {
		// Populate targets list with problemSize integers.
		targets = new ArrayList<Integer>();
		targets.add(rng.nextInt(10));
		for(int i = 1; i < problemSize; i++)
			targets.add(targets.get(i - 1) + rng.nextInt(1, 11));
		
		// Populates priority queue with every element from targets list. Since 
		// this method is not timed, inserts in sorted order for quicker setup. 
		priorityQueue = new SimplePriorityQueue<Integer>();
		for(int i = 0; i < problemSize; i++) {
			priorityQueue.insert(targets.get(i));
		}
		
		// Shuffle elements of the targets array for use in containsAll.
        	Collections.shuffle(targets);
	}

	/**
	 * Runs the containsAll method for the priority queue.
	 */
	@Override
	protected void runComputation() {
		priorityQueue.containsAll(targets);
	}
}