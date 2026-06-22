package assign02;

import java.util.GregorianCalendar;

public class CurrentPatient extends Patient{
	
	private int physician;
	private GregorianCalendar lastVisit;
	
	public CurrentPatient(String firstName, String lastName, UHealthID uHealthID, int physician, GregorianCalendar lastVisit) {
		super(firstName, lastName, uHealthID);
        this.physician = physician;
        this.lastVisit = lastVisit;
	}

	public int getPhysician() {
		return physician;
	}
	
	public GregorianCalendar getLastVisit() {
		return lastVisit;
	}
	
	public void updatePhysician(int newPhysician) {
		this.physician = newPhysician;
	}
	
	public void updateLastVisit(GregorianCalendar date) {
		this.lastVisit = date;
	}

}
