package assign04;

import java.util.Comparator;
import timing.TimingExperiment;

/**
 * Timing experiment to measure insertion sort performance in the average case.
 * Average case occurs when the input array is in random permuted order.
 * Expected time complexity: O(n^2) quadratic (approximately half the work of worst case)
 * 
 * @author Haoquan Wang and Zewei Ren
 * @version 2026.2.4
 */
public class InsertionSortAverageCaseTimingExperiment extends TimingExperiment {
	
	private static String problemSizeDescription = "Array Size";
	private static int problemSizeMin = 1000;           
	private static int problemSizeCount = 20;           
	private static int problemSizeStep = 500;           
	private static int experimentIterationCount = 50;   
	
	/** The array to be sorted in each iteration (must be protected for autograder access) */
	protected Integer[] array;
	
	/** Comparator used for sorting (ascending order) */
	private Comparator<Integer> comparator;
	
	/** Current problem size being tested */
	private int currentProblemSize;
	
	/**
	 * Main method to run the average case timing experiment.
	 * Prints results showing running times for various array sizes.
	 * 
	 * @param args command line arguments (unused)
	 */
	public static void main(String[] args) {
		TimingExperiment timingExperiment = new InsertionSortAverageCaseTimingExperiment();
		System.out.println("\n---Computing Insertion Sort Average Case Running Time---\n");
		timingExperiment.printResults();
	}
	
	/**
	 * Constructs a new average case timing experiment with predefined parameters.
	 * Tests array sizes from 1000 to 10500 (step 500), with 50 iterations each.
	 */
	public InsertionSortAverageCaseTimingExperiment() {
		super(problemSizeDescription, problemSizeMin, problemSizeCount, 
		      problemSizeStep, experimentIterationCount);
	}
	
	/**
	 * Sets up the experiment for a given problem size.
	 * Initializes the comparator to ascending order for natural sorting of
	 * randomly permuted arrays.
	 * 
	 * @param problemSize the size of array to test in this experiment iteration
	 */
	@Override
	protected void setupExperiment(int problemSize) {
		currentProblemSize = problemSize;
		
		// Initialize and fill array immediately
		array = new Integer[problemSize];
		for (int i = 0; i < problemSize; i++) {
			array[i] = i;
		}
		
		// Fisher-Yates shuffle
		for (int i = problemSize - 1; i > 0; i--) {
			int j = (int) (Math.random() * (i + 1));
			Integer temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
		
		// Ascending comparator for natural order sorting
		comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				return a.compareTo(b);
			}
		};
	}
	
	/**
	 * Runs the timed computation: generates a randomly permuted array and sorts it.
	 * This method is called repeatedly and its execution time is measured.
	 * Regenerates a fresh random permutation on each call to ensure representative
	 * average-case behavior across all iterations.
	 */
	@Override
	protected void runComputation() {
		// Regenerate array for each timing run
		for (int i = 0; i < currentProblemSize; i++) {
			array[i] = i;
		}
		
		// Fisher-Yates shuffle
		for (int i = currentProblemSize - 1; i > 0; i--) {
			int j = (int) (Math.random() * (i + 1));
			Integer temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
		
		LargestNumberSolver.insertionSort(array, comparator);
	}
}