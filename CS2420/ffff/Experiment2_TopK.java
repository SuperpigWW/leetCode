package timing;

import comprehensive.MarkovChain;
import comprehensive.Words;
import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Timing Experiment 2: Measures how getProbableNextWords (getTopK) time
 * scales as k increases, with a FIXED vocabulary/model.
 *
 * The input file is a fixed-size text where word "seed" is followed by
 * VOCAB_SIZE distinct words each appearing once, giving the heap exactly
 * VOCAB_SIZE candidates to sort through. We then call getTopK(k) and vary k.
 *
 * Problem size N = k (number of top words requested).
 *
 * Run from project root. Results printed as:
 *   k    time(ns)
 */
public class Experiment2_TopK extends TimingExperiment {

    private static final int VOCAB_SIZE = 100000; // fixed number of distinct next-words
    private static final String SEED = "seed";

    private File tempFile;
    private MarkovChain model;
    private int currentK;

    /**
     * problemSizeMin   = 10000  (k starts at 10000)
     * problemSizeCount = 10     (10 data points)
     * problemSizeStep  = 10000  (step by 10000 each time, up to k=100000)
     * iterations       = 50
     */
    public Experiment2_TopK() {
        super("k_value", 10000, 10, 10000, 50);
    }

    /**
     * Build the model once (outside timed region), then store k for runComputation.
     */
    @Override
    protected void setupExperiment(int problemSize) {
        currentK = problemSize;

        // Only build the file + model on the first call (or if not yet built)
        if (model == null) {
            try {
                tempFile = File.createTempFile("markov_exp2_", ".txt");
                tempFile.deleteOnExit();

                // File: "seed 1 seed 2 seed 3 ... seed VOCAB_SIZE"
                // Each number word appears exactly once after "seed"
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i <= VOCAB_SIZE; i++) {
                    sb.append(SEED).append(' ').append(i);
                    if (i < VOCAB_SIZE) sb.append(' ');
                }
                Files.writeString(tempFile.toPath(), sb.toString());

                model = new MarkovChain(tempFile.getAbsolutePath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void runComputation() {
        // Time only the getTopK call
        model.getProbableNextWords(SEED, currentK);
    }

    public static void main(String[] args) {
        new Experiment2_TopK().printResults();
    }
}
