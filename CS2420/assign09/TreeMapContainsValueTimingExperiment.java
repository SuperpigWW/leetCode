package assign09;

import java.util.ArrayList;
import java.util.TreeMap;

import timing.ArrayListGenerator;
import timing.TimingExperiment;

/**
 * Experiment to measure the running times of the containsValue method in
 * a TreeMap of Integer objects, for various problem sizes.
 *
 * @author Haoquan Wang
 * @version April 2, 2026
 */
public class TreeMapContainsValueTimingExperiment extends TimingExperiment {
    private static String problemSizeDescription = "element count";
    private static int problemSizeMin = 10000;
    private static int problemSizeCount = 10;
    private static int problemSizeStep = 10000;
    private static int experimentIterationCount = 100;

    private TreeMap<Integer, Integer> treeMap;

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new TreeMapContainsValueTimingExperiment();
        timingExperiment.printResults();
    }

    public TreeMapContainsValueTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    /**
     * Creates a TreeMap of problemSize Integer elements.
     *
     * @param problemSize - the number of unique integers to fill the TreeMap
     */
    protected void setupExperiment(int problemSize) {
        ArrayList<Integer> uniqueIntegers = ArrayListGenerator.generatePermutedArrayListWithoutDuplicates(problemSize);

        treeMap = new TreeMap<Integer, Integer>();
        for (int i = 0; i < problemSize; i++)
            treeMap.put(uniqueIntegers.get(i), 0);
    }

    /**
     * Runs the containsValue method for a TreeMap containing problemSize elements.
     */
    protected void runComputation() {
        treeMap.containsValue(1);
    }
}