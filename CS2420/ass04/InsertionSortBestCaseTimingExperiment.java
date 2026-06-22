package assign04;

import java.util.Comparator;
import timing.TimingExperiment;

/**
 * Timing experiment to measure insertion sort performance in the best case.
 * Best case occurs when the input array is already sorted or nearly sorted.
 * Uses nearly-ascending arrays with minimal swaps.
 * Expected time complexity: O(n) linear in ideal best case.
 *
 * @author Haoquan Wang and Zewei Ren
 * @version 2026.2.4
 */
public class InsertionSortBestCaseTimingExperiment extends TimingExperiment {
	
	private static String problemSizeDescription = "Array Size";
	private static int problemSizeMin = 1000;           
	private static int problemSizeCount = 20;           
	private static int problemSizeStep = 500;           
	private static int experimentIterationCount = 50;   
	
	/** The array to be sorted in each iteration (must be protected for autograder access) */
	protected Integer[] array;
	
	/** Comparator used for sorting (ascending order to match nearly-ascending input) */
	private Comparator<Integer> comparator;
	
	/** Current problem size being tested */
	private int currentProblemSize;
	
	/**
	 * Main method to run the best case timing experiment.
	 * Prints results showing running times for various array sizes.
	 * 
	 * @param args command line arguments (unused)
	 */
	public static void main(String[] args) {
		TimingExperiment timingExperiment = new InsertionSortBestCaseTimingExperiment();
		System.out.println("\n---Computing Insertion Sort Best Case Running Time---\n");
		timingExperiment.printResults();
	}
	
	/**
	 * Constructs a new best case timing experiment with predefined parameters.
	 * Tests array sizes from 1000 to 10500 (step 500), with 50 iterations each.
	 */
	public InsertionSortBestCaseTimingExperiment() {
		super(problemSizeDescription, problemSizeMin, problemSizeCount, 
		      problemSizeStep, experimentIterationCount);
	}
	
	/**
	 * Sets up the experiment for a given problem size.
	 * Initializes the comparator to ascending order, which matches the nearly-sorted
	 * input arrays to create best-case or near-best-case conditions.
	 * 
	 * @param problemSize the size of array to test in this experiment iteration
	 */
	@Override
	protected void setupExperiment(int problemSize) {
		currentProblemSize = problemSize;
		
		// Initialize and fill array immediately (ascending order)
		array = new Integer[problemSize];
		for (int i = 0; i < problemSize; i++) {
			array[i] = i;
		}
		
		// Perform exactly 3 adjacent swaps to make it "nearly" sorted
		// This keeps the array almost perfectly sorted
		if (problemSize >= 2) {
			// Swap element at position 10 with position 11
			if (problemSize > 11) {
				Integer temp = array[10];
				array[10] = array[11];
				array[11] = temp;
			}
			
			// Swap element at position 50 with position 51
			if (problemSize > 51) {
				Integer temp = array[50];
				array[50] = array[51];
				array[51] = temp;
			}
			
			// Swap element at position 100 with position 101
			if (problemSize > 101) {
				Integer temp = array[100];
				array[100] = array[101];
				array[101] = temp;
			}
		}
		
		// Ascending comparator matches the nearly-ascending input for best case
		comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				return a.compareTo(b);
			}
		};
	}
	
	/**
	 * Runs the timed computation: generates a nearly-ascending array and sorts it.
	 * This method is called repeatedly and its execution time is measured.
	 * Since the array is nearly sorted and the comparator matches this order,
	 * insertion sort requires minimal work, approaching O(n) performance.
	 * Regenerates the array on each call to maintain consistent test conditions.
	 */
	@Override
	protected void runComputation() {
		// Regenerate ascending array for each timing run
		for (int i = 0; i < currentProblemSize; i++) {
			array[i] = i;
		}
		
		// Perform exactly 3 adjacent swaps
		if (currentProblemSize >= 2) {
			if (currentProblemSize > 11) {
				Integer temp = array[10];
				array[10] = array[11];
				array[11] = temp;
			}
			
			if (currentProblemSize > 51) {
				Integer temp = array[50];
				array[50] = array[51];
				array[51] = temp;
			}
			
			if (currentProblemSize > 101) {
				Integer temp = array[100];
				array[100] = array[101];
				array[101] = temp;
			}
		}
		
		LargestNumberSolver.insertionSort(array, comparator);
	}
}