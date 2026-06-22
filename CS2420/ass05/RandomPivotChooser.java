package assign05;

import java.util.ArrayList;
import java.util.Random;

/**
 * Selects a random element within the sublist as the pivot.
 *
 * @author Haoquan Wang and Zewei Rem
 * @version 2026-2-18
 */
public class RandomPivotChooser<E extends Comparable<? super E>> implements PivotChooser<E> {

	private final Random rand = new Random();

	/**
	 * Returns the index of a randomly chosen element within the sublist.
	 *
	 * @param list       - the list containing the sublist
	 * @param leftIndex  - start index of the sublist
	 * @param rightIndex - end index of the sublist
	 * @return a random index between leftIndex and rightIndex, inclusive
	 */
	@Override
	public int getPivotIndex(ArrayList<E> list, int leftIndex, int rightIndex) {
		return leftIndex + rand.nextInt(rightIndex - leftIndex + 1);
	}

}