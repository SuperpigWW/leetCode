package assign02;

import java.util.Comparator;

/**
 * A class that orders objects by date, with tie-breaking logic.
 *
 * @author Haoquan Wang and Zewei Ren
 * @version 2026-1-21
 */
public class OrderByDate<Type> implements Comparator<CurrentPatientGeneric<Type>> {
	
	/** 
	 * Compares two objects for order based on date.
     * The comparison is performed in the following sequence:
	 * 1. First compares patients by their last visit date
	 * 2. If last visit date are identical, compares by their UHealthID string representations
	 * 
	 * @param lhs the first object to be compared
     * @param rhs the second object to be compared
     * @return a negative integer, zero, or a positive integer as the first argument
     *         is less than, equal to, or greater than the second according to the
     *         specified ordering criteria
	 */
	public int compare(CurrentPatientGeneric<Type>lhs,CurrentPatientGeneric<Type> rhs) {
		if(lhs.getLastVisit().compareTo(rhs.getLastVisit()) != 0){
			return lhs.getLastVisit().compareTo(rhs.getLastVisit());
		}
		else {
			return lhs.getUHealthID().toString().compareTo(rhs.getUHealthID().toString());
		}
	}

}
	
	


