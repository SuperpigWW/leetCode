package assign05;

import timing.ArrayListGenerator;
import timing.TimingExperiment;
import java.util.ArrayList;

/**
 * Timing experiment for measuring the running time of merge sort
 * on a shuffled (permuted) ArrayList.
 *
 * Modify the threshold value to observe how it affects performance.
 * 
 * @author Haoquan Wang and Zewei Rem
 * @version 2026-2-18
 */
public class MergeSortShuffledTimingExperiment extends TimingExperiment {

    /** Threshold below which merge sort switches to insertion sort */
    protected static int threshold = 24;

    protected ArrayList<Integer> list;
    private MergeSorter<Integer> sorter;

    /**
     * Constructs the timing experiment with predefined problem size parameters.
     */
    public MergeSortShuffledTimingExperiment() {
        super(
            "list size",   // description of problem size
            5000,          // minimum problem size
            20,            // number of problem sizes
            1000,          // step between problem sizes
            50             // number of iterations per problem size
        );
        sorter = new MergeSorter<>(threshold);
    }

    /**
     * Generates a new shuffled ArrayList for each timing iteration.
     *
     * @param problemSize - the size of the list to generate
     */
    @Override
    protected void setupExperiment(int problemSize) {
        list = ArrayListGenerator.generatePermutedArrayList(problemSize);
    }

    /**
     * Runs merge sort on the list. This is the method whose runtime is measured.
     */
    @Override
    protected void runComputation() {
        sorter.sort(list);
    }

    public static void main(String[] args) {
        TimingExperiment experiment = new MergeSortShuffledTimingExperiment();
        System.out.println("=== Merge Sort - Shuffled ArrayList ===");
        System.out.println("threshold = " + threshold);
        experiment.printResults();
    }
}