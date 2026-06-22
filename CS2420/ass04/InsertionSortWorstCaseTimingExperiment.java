package assign04;

import java.util.Comparator;
import timing.TimingExperiment;

/**
 * Timing experiment to measure insertion sort performance in the worst case.
 * Worst case occurs when the array is sorted in reverse order of the desired direction.
 * Uses descending arrays sorted with descending comparator, forcing maximum comparisons.
 * Expected time complexity: O(n^2) quadratic.
 * 
 * @author Haoquan Wang and Zewei Ren
 * @version 2026.2.4
 */
public class InsertionSortWorstCaseTimingExperiment extends TimingExperiment {
	
	private static String problemSizeDescription = "Array Size";
	private static int problemSizeMin = 1000;
	private static int problemSizeCount = 20;
	private static int problemSizeStep = 500;
	private static int experimentIterationCount = 30;
	
	/** The array to be sorted in each iteration (must be protected for autograder access) */
	protected Integer[] array;
	
	/** Comparator used for sorting (descending order for worst case) */
	private Comparator<Integer> comparator;
	
	/** Current problem size being tested */
	private int currentProblemSize;
	
	/**
	 * Main method to run the worst case timing experiment.
	 * Prints results showing running times for various array sizes.
	 * 
	 * @param args command line arguments (unused)
	 */
	public static void main(String[] args) {
		TimingExperiment timingExperiment = new InsertionSortWorstCaseTimingExperiment();
		System.out.println("\n---Computing Insertion Sort Worst Case Running Time---\n");
		timingExperiment.printResults();
	}
	
	/**
	 * Constructs a new worst case timing experiment with predefined parameters.
	 * Tests array sizes from 1000 to 10500 (step 500), with 30 iterations each.
	 */
	public InsertionSortWorstCaseTimingExperiment() {
		super(problemSizeDescription, problemSizeMin, problemSizeCount, 
		      problemSizeStep, experimentIterationCount);
	}
	
	/**
	 * Sets up the experiment for a given problem size.
	 * Initializes the comparator to descending order to create worst case conditions
	 * when paired with descending input arrays.
	 * 
	 * @param problemSize the size of array to test in this experiment iteration
	 */
	@Override
	protected void setupExperiment(int problemSize) {
		currentProblemSize = problemSize;
		
		// Initialize and fill array immediately (descending order)
		array = new Integer[problemSize];
		for (int i = 0; i < problemSize; i++) {
			array[i] = problemSize - 1 - i;
		}
		
		// Ascending comparator: when combined with descending array, creates worst case
		comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				return a.compareTo(b);  
			}
		};
	}
	
	/**
	 * Runs the timed computation: generates a descending array and sorts it.
	 * This method is called repeatedly and its execution time is measured.
	 * Regenerates the array on each call to ensure consistent worst-case conditions.
	 */
	@Override
	protected void runComputation() {
		// Regenerate descending array for each timing run
		for (int i = 0; i < currentProblemSize; i++) {
			array[i] = currentProblemSize - 1 - i;
		}
		
		LargestNumberSolver.insertionSort(array, comparator);
	}
}