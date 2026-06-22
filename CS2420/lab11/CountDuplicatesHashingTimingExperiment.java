package lab11;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import timing.TimingExperiment;

/**
 * Timing experiment to collect the running times of counting duplicates (hash table strategy).
 *
 * @author CS 2420 course staff
 * @version March 27, 2026
 */
public class CountDuplicatesHashingTimingExperiment extends TimingExperiment {
    private static String problemSizeDescription = "element count";
    private static int problemSizeMin = 10000;
    private static int problemSizeCount = 10;
    private static int problemSizeStep = 10000;
    private static int experimentIterationCount = 50;
    
    private List<Integer> elements;
    private final static Random rng = new Random();
    
    public static void main(String[] args) {
        TimingExperiment timingExperiment = new CountDuplicatesHashingTimingExperiment();
        timingExperiment.printResults();
    }

    public CountDuplicatesHashingTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    protected void setupExperiment(int problemSize) {
    	elements = new ArrayList<Integer>();
    	for(int i = 0; i < problemSize; i++)
    		elements.add(rng.nextInt(problemSize));    
    }
    
    protected void runComputation() {
    	DuplicateCounter.countDuplicatesHashing(elements);
    }
}