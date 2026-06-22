package lab02;

import java.util.Random;

/**
 * This class defines a timing experiment to measure the running time for
 * finding the maximum integer in arrays of various sizes.
 *
 * @author CS 2420 course staff
 * @version January 16, 2026
 */
public class ArrayMaximumTimingExperiment {

	private Integer[] array;
	private final static Random rng = new Random();

	public static void main(String[] args) {
		// Instantiate the timing experiment.
		ArrayMaximumTimingExperiment timingExperiment = new ArrayMaximumTimingExperiment();

		// Run the experiment and print the results.
		timingExperiment.printResults();
	}

	/**
	 * Runs the timing experiment and prints the results.
	 */
	public void printResults() {
		int arraySize = 200000;

		// Setup the array.
		setupArray(arraySize);

		// Record the current time.
		long startTime = System.nanoTime();

		// Find the maximum element of the array.
		runComputation();

		// Record the current time.
		long endTime = System.nanoTime();

		// Compute the time elapsed; i.e., the time to find the maximum element of the
		// array.
		long elapsedTime = endTime - startTime;

		System.out.println("start time: " + startTime);
		System.out.println("end time: " + endTime);
		System.out.println("It took " + elapsedTime + " ns to find the maximum element.");
	}

	/**
	 * Populates the array with random integers.
	 * 
	 * @param arraySize - size of the array
	 */
	private void setupArray(int arraySize) {
		array = new Integer[arraySize];
		for(int i = 0; i < arraySize; i++) {
			array[i] = rng.nextInt();
		}
	}

	/**
	 * Runs the computeMaximum method for the array.
	 */
	private void runComputation() {
		ArrayUtility.computeMaximum(array);
	}
}