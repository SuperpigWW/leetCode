package assign09;

import java.util.ArrayList;

import timing.ArrayListGenerator;

/**
 * Experiment to measure the number of collisions incurred for the put method in
 * a hash table of Integer keys, for various problem sizes and load factors.
 *
 * @author Haoquan Wang
 * @version April 2, 2026
 */
public class LoadFactorCollisionExperiment extends CollisionExperiment<Integer, Integer> {
    private static String problemSizeDescription = "element count";
    private static int problemSizeMin = 10000;
    private static int problemSizeCount = 10;
    private static int problemSizeStep = 10000;
    private static int experimentIterationCount = 5;

    private double loadFactor;
    private Integer keyToPut;

    public static void main(String[] args) {
        double[] loadFactors = {1.0, 3.0, 5.0, 7.0, 10.0};

        for (double lf : loadFactors) {
            System.out.println("=== Load Factor: " + lf + " ===");
            CollisionExperiment<Integer, Integer> experiment = new LoadFactorCollisionExperiment(lf);
            experiment.printResults();
            System.out.println();
        }
    }

    /**
     * Creates a load factor collision experiment with the specified load factor.
     *
     * @param loadFactor - the maximum load factor at which the hash table rehashes
     */
    public LoadFactorCollisionExperiment(double loadFactor) {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
        this.loadFactor = loadFactor;
    }

    /**
     * Creates a hash table of problemSize Integer elements using the specified load factor.
     *
     * @param problemSize - the number of unique integers to fill the hash table
     */
    protected void setupExperiment(int problemSize) {
        ArrayList<Integer> uniqueIntegers = ArrayListGenerator.generatePermutedArrayListWithoutDuplicates(problemSize + 1);

        table = new HashTable<Integer, Integer>(loadFactor);
        for (int i = 0; i < problemSize; i++)
            table.put(uniqueIntegers.get(i), 0);

        keyToPut = uniqueIntegers.get(problemSize);
    }

    /**
     * Runs the put method for a hash table containing problemSize elements.
     */
    protected void runComputation() {
        table.put(keyToPut, 0);
    }
}