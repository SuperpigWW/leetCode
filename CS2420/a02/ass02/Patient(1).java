package assign02;

/**
 * This class represents a UHealth patient who has a unique UHealthID
 * and a first and last name.
 *
 * @author CS 2420 course staff and Haoquan Wang
 * @version 2026-01-20
 */
public class Patient {

	private String firstName;
	private String lastName;
	private UHealthID uHealthID;

	/**
	 * Creates a patient with a given name and ID.
	 *
	 * @param firstName - of the patient
	 * @param lastName - of the patient
	 * @param UHealthID - of the patient
	 */
	public Patient(String firstName, String lastName, UHealthID uHealthID) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.uHealthID = uHealthID;
	}

	/**
	 * Getter method for the first name of this patient object.
	 *
	 * @return this patient's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Getter method for the last nameof this patient object.
	 *
	 * @return this patient's last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Getter method for the UHealthID of this patient object.
	 *
	 * @return this patient's UHealthID
	 */
	public UHealthID getUHealthID() {
		return uHealthID;
	}

	/**
	 * Two patients are considered equal if they have the same UHealthID.
	 *
	 * @param other - the object being compared with this patient
	 * @return true if the other object is a Patient with an equal UHealthID, 
	 *         false otherwise.
	 */
	public boolean equals(Object other) {
		if(!(other instanceof Patient)) {
			return false;
		}
		Patient otherPatient = (Patient) other;
		return this.uHealthID.equals(otherPatient.uHealthID);
	}

	/**
	 * Gets a String representing this patient.
	 * 
	 * @return a textual representation of this patient.
	 */
	public String toString() {
		return firstName + " " + lastName + " (" + uHealthID + ")";
	}
}
