package assign02;

import java.util.Comparator;

public class OrderByDate<Type> implements Comparator<CurrentPatientGeneric<Type>> {
	
	public int compare(CurrentPatientGeneric<Type>lhs,CurrentPatientGeneric<Type> rhs) {
		if(lhs.getLastVisit().compareTo(rhs.getLastVisit()) != 0){
			return lhs.getLastVisit().compareTo(rhs.getLastVisit());
		}
		else {
			return lhs.getUHealthID().toString().compareTo(rhs.getUHealthID().toString());
		}
	}

}
