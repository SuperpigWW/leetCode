package assign06;

import timing.TimingExperiment;

/**
 * Experiment to measure the running time of a single push operation on an ArrayStack,
 * for various problem sizes.
 *
 * @author Haoquan Wang
 * @version 2026-2-26
 */
public class ArrayStackPushTimingExperiment extends TimingExperiment {

    protected Stack<Integer> stack;

    public static void main(String[] args) {
        ArrayStackPushTimingExperiment experiment = new ArrayStackPushTimingExperiment();
        experiment.printResults();
    }

    public ArrayStackPushTimingExperiment() {
        super("number of pushes", 10000, 30, 1000, 100);
    }

    /**
     * Sets up an ArrayStack pre-filled with problemSize elements.
     *
     * @param problemSize - the number of elements already in the stack before timing
     */
    @Override
    protected void setupExperiment(int problemSize) {
        stack = new ArrayStack<>();
        for (int i = 0; i < problemSize; i++)
            stack.push(i);
    }

    /**
     * Pushes 100000 additional element onto the stack.
     */
    @Override
    protected void runComputation() {
    	for (int i = 0; i < 100000; i++)
    		stack.push(0);
    }
}