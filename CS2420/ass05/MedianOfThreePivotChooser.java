package assign05;

import java.util.ArrayList;

/**
 * Selects the median of the first, middle, and last elements as the pivot.
 *
 * @author Haoquan Wang and Zewei Rem
 * @version 2026-2-18
 */
public class MedianOfThreePivotChooser<E extends Comparable<? super E>> implements PivotChooser<E> {

	/**
	 * Returns the index of the median among the first, middle, and last elements
	 * of the sublist.
	 *
	 * @param list       - the list containing the sublist
	 * @param leftIndex  - start index of the sublist
	 * @param rightIndex - end index of the sublist
	 * @return index of the median-of-three element
	 */
	@Override
	public int getPivotIndex(ArrayList<E> list, int leftIndex, int rightIndex) {
		int midIndex = leftIndex + (rightIndex - leftIndex) / 2;
		if (list.get(leftIndex).compareTo(list.get(midIndex)) < 0) {
			if (list.get(midIndex).compareTo(list.get(rightIndex)) < 0)
				return midIndex;
			else if (list.get(leftIndex).compareTo(list.get(rightIndex)) < 0)
				return rightIndex;
			else
				return leftIndex;
		}
		else {
			if (list.get(rightIndex).compareTo(list.get(midIndex)) < 0)
				return midIndex;
			else if (list.get(leftIndex).compareTo(list.get(rightIndex)) < 0)
				return leftIndex;
			else
				return rightIndex;
		}
	}

}