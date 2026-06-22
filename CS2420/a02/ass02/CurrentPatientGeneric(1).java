package assign02;

import java.util.GregorianCalendar;

/**
 * This class represents a current UHealth patient, Inherited from the Patient
 * class. This class add the physicianID and last visit date. Physicians use a
 * generic type parameter<Type>
 * 
 * @author CS 2420 course staff and Haoquan Wang and Zewei Ren
 * @version 2026-1-21
 */

public class CurrentPatientGeneric<Type> extends Patient {

	private Type physician;
	private GregorianCalendar lastVisit;

	/**
	 * Create a new CurrentPatientGeneric function
	 * 
	 * @param firstName patient's first name
	 * @param lastName  patient's last name
	 * @param uHealthID patient's UHealthID
	 * @param physician patient's UID
	 * @param lastVisit patient's last visit date
	 */
	public CurrentPatientGeneric(String firstName, String lastName, UHealthID uHealthID, Type physician,
			GregorianCalendar lastVisit) {
		super(firstName, lastName, uHealthID);
		this.physician = physician;
		this.lastVisit = lastVisit;
	}

	/**
	 * Get physician's UID.
	 * 
	 * @return physician's UID
	 */

	public Type getPhysician() {
		return physician;
	}

	/**
	 * Get the patient last visit date.
	 * 
	 * @return patient's last visit time
	 */
	public GregorianCalendar getLastVisit() {
		return lastVisit;
	}

	/**
	 * Update the physician's UID
	 * 
	 * @param newPhysician new physician's UID
	 */
	public void updatePhysician(Type newPhysician) {
		this.physician = newPhysician;
	}

	/**
	 * Update the patient's last visit date
	 * 
	 * @param date new visit date
	 */
	public void updateLastVisit(GregorianCalendar date) {
		this.lastVisit = date;
	}

}
