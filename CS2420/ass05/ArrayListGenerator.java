package timing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Utility class for generating ArrayLists of various sizes and orderings,
 * used for timing experiments.
 *
 * @author Haoquan Wang and Zewei Ren
 * @version 2026-2-16
 */
public class ArrayListGenerator {

    private static final Random rng = new Random();

    /**
     * Generates an ArrayList with problemSize random integers in nearly ascending order.
     *
     * @implNote calls generateAscendingArray and then swaps a small number of
     *           random pairs of nearby elements
     * @param problemSize - size of the list
     * @return an ArrayList of integers in nearly ascending order
     */
    public static ArrayList<Integer> generateNearlyAscendingArrayList(int problemSize) {
        Integer[] array = generateAscendingArray(problemSize);
        slightlyShuffleArray(array);
        return new ArrayList<>(Arrays.asList(array));
    }

    /**
     * Generates an ArrayList with problemSize random integers in a permuted (shuffled) order.
     *
     * @implNote calls generateAscendingArray and then shuffles the contents
     * @param problemSize - size of the list
     * @return an ArrayList of integers in random order
     */
    public static ArrayList<Integer> generatePermutedArrayList(int problemSize) {
        Integer[] array = generateAscendingArray(problemSize);
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(array));
        Collections.shuffle(list);
        return list;
    }

    /**
     * Generates an ArrayList with problemSize random integers in descending order.
     *
     * @implNote calls generateAscendingArray and then reverses the contents
     * @param problemSize - size of the list
     * @return an ArrayList of integers in descending order
     */
    public static ArrayList<Integer> generateDescendingArrayList(int problemSize) {
        Integer[] array = generateAscendingArray(problemSize);
        for (int i = 0; i < array.length / 2; i++) {
            swapArrayElements(array, i, array.length - 1 - i);
        }
        return new ArrayList<>(Arrays.asList(array));
    }

    /**
     * Generates an array with problemSize random integers in ascending order.
     *
     * @implNote integers are bounded between 0 and 20 + 10 * problemSize
     * @param problemSize - size of the array
     * @return an ascending Integer array
     */
    private static Integer[] generateAscendingArray(int problemSize) {
        Integer[] array = new Integer[problemSize];
        int currentElement = rng.nextInt(20);
        for (int i = 0; i < problemSize; i++) {
            array[i] = currentElement;
            currentElement += rng.nextInt(10);
        }
        return array;
    }

    /**
     * Slightly shuffles the contents of the given array, such that it remains in nearly
     * ascending order, by swapping a small number of random pairs of nearby elements.
     *
     * @param array - the array to be slightly shuffled
     */
    private static void slightlyShuffleArray(Integer[] array) {
        // If the array is too small to safely swap nearby elements, skip shuffling
        if (array.length < 12)
            return;
        // Choose a random number of pairs to swap, between 5 and 19
        int swapCount = 5 + rng.nextInt(15);
        for (int i = 0; i < swapCount; i++) {
            // Choose a random index, excluding the final 11 indices
            int idx1 = rng.nextInt(array.length - 11);
            // Choose an index between 1 and 10 to the right of idx1
            int idx2 = idx1 + 1 + rng.nextInt(10);
            swapArrayElements(array, idx1, idx2);
        }
    }

    /**
     * Swaps two elements in the given array.
     *
     * @param array       - the array containing elements to be swapped
     * @param firstIndex  - index of the first element
     * @param secondIndex - index of the second element
     * @throws IndexOutOfBoundsException if either index is out of bounds
     */
    private static void swapArrayElements(Integer[] array, int firstIndex, int secondIndex) {
        if (firstIndex < 0 || firstIndex >= array.length ||
                secondIndex < 0 || secondIndex >= array.length) {
            throw new IndexOutOfBoundsException();
        }
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }
}