package assign06;

import timing.TimingExperiment;

/**
 * Experiment to measure the running time of push operations on a LinkedListStack,
 * for various problem sizes.
 *
 * @author Haoquan Wang
 * @version 2026-2-26
 */
public class LinkedListStackPushTimingExperiment extends TimingExperiment {

    protected Stack<Integer> stack;

    public static void main(String[] args) {
        LinkedListStackPushTimingExperiment experiment = new LinkedListStackPushTimingExperiment();
        experiment.printResults();
    }

    public LinkedListStackPushTimingExperiment() {
        super("number of pushes", 10000, 20, 1000, 100);
    }

    /**
     * Sets up a LinkedListStack pre-filled with problemSize elements.
     *
     * @param problemSize - the number of elements already in the stack before timing
     */
    @Override
    protected void setupExperiment(int problemSize) {
        stack = new LinkedListStack<>();
        for (int i = 0; i < problemSize; i++)
            stack.push(i);
    }

    /**
     * Pushes 100000 additional elements onto the stack.
     */
    @Override
    protected void runComputation() {
        for (int i = 0; i < 100000; i++)
            stack.push(0);
    }
}