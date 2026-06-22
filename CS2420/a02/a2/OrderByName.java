package assign02;

import java.util.Comparator;

/**
 * A class that orders objects by name, with tie-breaking logic.
 *
 * @author Haoquan Wang and Zewei Ren
 * @version 2026-1-21
 */
public class OrderByName<Type> implements Comparator<CurrentPatientGeneric<Type>>{
	

	/** 
	 * Compares two objects for order based on name.
     * The comparison is performed in the following sequence:
	 * 1. First compares patients by their first names
	 * 2. If first names are identical, compares by their last names
	 * 3. If both first and last names are identical, compares by their UHealthID string representations
	 * 
	 * @param lhs the first object to be compared
     * @param rhs the second object to be compared
     * @return a negative integer, zero, or a positive integer as the first argument
     *         is less than, equal to, or greater than the second according to the
     *         specified ordering criteria
	 */
	public int compare(CurrentPatientGeneric<Type> lhs, CurrentPatientGeneric<Type> rhs) {
		
		if (lhs.getFirstName().compareTo(rhs.getFirstName()) != 0)
			return lhs.getFirstName().compareTo(rhs.getFirstName());
		else if (lhs.getLastName().compareTo(rhs.getLastName()) != 0)
			return lhs.getLastName().compareTo(rhs.getLastName());
		else
			return lhs.getUHealthID().toString().compareTo(rhs.getUHealthID().toString());
		
	}

}
