package assign05;

import java.util.ArrayList;

/**
 * Selects the first element of the sublist as the pivot.
 *
 * @author Haoquan Wang and Zewei Rem
 * @version 2026-2-18
 */
public class FirstPivotChooser<E extends Comparable<? super E>> implements PivotChooser<E> {

	/**
	 * Returns the index of the first element in the sublist as the pivot.
	 *
	 * @param list       - the list containing the sublist
	 * @param leftIndex  - start index of the sublist
	 * @param rightIndex - end index of the sublist
	 * @return leftIndex
	 */
	@Override
	public int getPivotIndex(ArrayList<E> list, int leftIndex, int rightIndex) {
		return leftIndex;
	}

}