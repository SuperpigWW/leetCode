package lab02;

import java.util.Random;

import timing.TimingExperiment;

/**
 * This class defines a timing experiment to measure the running time for
 * finding the maximum integer in arrays of various sizes.
 *
 * @author CS 2420 course staff
 * @version January 16, 2026
 */
public class ArrayMaximumTimingExperimentV2 extends TimingExperiment {

	private static String problemSizeDescription = "arraySize";
	private static int problemSizeMin = 200000;
	private static int problemSizeCount = 30;
	private static int problemSizeStep = 10000;
	private static int experimentIterationCount = 50;

	private Integer[] array;
	private final static Random rng = new Random();

	public static void main(String[] args) {
		TimingExperiment timingExperiment = new ArrayMaximumTimingExperimentV2();
		timingExperiment.printResults();
	}

	public ArrayMaximumTimingExperimentV2() {
		super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
	}

	/**
	 * Populates the array with random integers.
	 * 
	 * @param problemSize - the size of the array
	 */
	@Override
	protected void setupExperiment(int problemSize) {
		array = new Integer[problemSize];
		for(int i = 0; i < problemSize; i++) {
			array[i] = rng.nextInt();
		}
	}

	/**
	 * Runs the computeMaximum method for the array.
	 */
	@Override
	protected void runComputation() {
		ArrayUtility.computeMaximum(array);
	}
}