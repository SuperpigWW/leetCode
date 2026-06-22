package timing;

import comprehensive.MarkovChain;
import java.io.*;
import java.nio.file.*;

/**
 * Timing Experiment 1: Measures how buildChain (MarkovChain construction) time
 * scales as the number of DISTINCT words in the input file increases.
 *
 * Input files are generated as: "1 2 3 4 5 ... N" (each number is a unique word).
 * Problem size N = number of distinct words = total words in file.
 *
 * Run from project root. Results printed as:
 *   N    time(ns)
 */
public class Experiment1_DistinctWords extends TimingExperiment {

    private File tempFile;
    private int currentSize;

    /**
     * problemSizeMin   = 10000  (10k distinct words)
     * problemSizeCount = 10     (10 data points)
     * problemSizeStep  = 10000  (step by 10k each time, up to 100k)
     * iterations       = 25
     */
    public Experiment1_DistinctWords() {
        super("distinct_words_N", 10000, 10, 10000, 25);
    }

    @Override
    protected void setupExperiment(int problemSize) {
        currentSize = problemSize;
        try {
            tempFile = File.createTempFile("markov_exp1_", ".txt");
            tempFile.deleteOnExit();

            // Write "1 2 3 4 ... N" — every token is unique
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= problemSize; i++) {
                sb.append(i);
                if (i < problemSize) sb.append(' ');
            }
            Files.writeString(tempFile.toPath(), sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void runComputation() {
        try {
            // We are timing MarkovChain construction (i.e., buildChain)
            new MarkovChain(tempFile.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new Experiment1_DistinctWords().printResults();
    }
}
