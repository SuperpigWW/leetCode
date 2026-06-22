package assign05;

import timing.ArrayListGenerator;
import timing.TimingExperiment;
import java.util.ArrayList;

/**
 * Timing experiment for measuring the running time of quicksort
 * on a nearly ascending ArrayList.
 *
 * Swap the pivotChooser to compare different pivot selection strategies
 * 
 * @author Haoquan Wang and Zewei Rem
 * @version 2026-2-18
 */
public class QuickSortNearlyAscendingTimingExperiment extends TimingExperiment {

    /** Pivot selection strategy used when constructing the QuickSorter */
    protected static PivotChooser<Integer> pivotChooser = new MedianOfThreePivotChooser<>();

    protected ArrayList<Integer> list;
    private QuickSorter<Integer> sorter;

    /**
     * Constructs the timing experiment with predefined problem size parameters.
     */
    public QuickSortNearlyAscendingTimingExperiment() {
        super(
            "list size",   // description of problem size
            5000,          // minimum problem size
            20,            // number of problem sizes
            1000,          // step between problem sizes
            50             // number of iterations per problem size
        );
        sorter = new QuickSorter<>(pivotChooser);
    }

    /**
     * Generates a new nearly ascending ArrayList for each timing iteration.
     *
     * @param problemSize - the size of the list to generate
     */
    @Override
    protected void setupExperiment(int problemSize) {
        list = ArrayListGenerator.generateNearlyAscendingArrayList(problemSize);
    }

    /**
     * Runs quicksort on the list. This is the method whose runtime is measured.
     */
    @Override
    protected void runComputation() {
        sorter.sort(list);
    }

    public static void main(String[] args) {
        TimingExperiment experiment = new QuickSortNearlyAscendingTimingExperiment();
        System.out.println("=== Quick Sort - Nearly Ascending ArrayList ===");
        System.out.println("pivotChooser = " + pivotChooser.getClass().getSimpleName());
        experiment.printResults();
    }
}