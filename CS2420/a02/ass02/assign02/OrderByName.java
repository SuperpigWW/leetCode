package assign02;

import java.util.Comparator;

public class OrderByName implements Comparator<CurrentPatientGeneric<Type>>{
	
	public int compare(CurrentPatientGeneric<Type> lhs, CurrentPatientGeneric<Type> rhs) {
		
		if (lhs.getFirstName().compareTo(rhs.getFirstName()) != 0)
			return lhs.getFirstName().compareTo(rhs.getFirstName());
		else if (lhs.getLastName().compareTo(rhs.getLastName()) != 0)
			return lhs.getLastName().compareTo(rhs.getLastName());
		else
			return lhs.getUHealthID().toString().compareTo(rhs.getUHealthID().toString());
		
	}

}
