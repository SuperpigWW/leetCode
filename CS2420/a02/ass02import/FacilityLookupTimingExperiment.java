package assign02;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Random;

import timing.TimingExperiment;

/**
 * Experiment to measure the running time for the lookupByUHID method in 
 * facilities with various numbers of patients.
 *
 * @author CS 2420 course staff and ***STUDENT FILL YOUR NAME IN***
 * @version ***STUDENT FILL IN THE DATE***
 */
public class FacilityLookupTimingExperiment extends TimingExperiment {
	private static String problemSizeDescription = "patients";  // TODO: fill in string appropriately
	private static int problemSizeMin = 10000;  // TODO: initialize appropriately (do not use 0)
	private static int problemSizeCount = 10;  // TODO: initialize appropriately (do not use 0)
	private static int problemSizeStep = 10000;  // TODO: initialize appropriately (do not use 0)
	private static int experimentIterationCount = 50;

	private Facility randomFacility;
	private UHealthID randomUHealthID;
	private final static Random rng = new Random();

	public static void main(String[] args) {
		TimingExperiment timingExperiment = new FacilityLookupTimingExperiment();

		System.out.println("\n---Computing timing results---\n");
		timingExperiment.printResults();
	}

	public FacilityLookupTimingExperiment() {
		super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
	}

	/**
	 * Fills the Facility with the given number of patients.
	 * 
	 * @param problemSize - the number of patients to add
	 */
	@Override
	protected void setupExperiment(int problemSize) {
		randomFacility = new Facility();
		ArrayList<CurrentPatient> patients = generatePatients(problemSize);
		randomFacility.addAll(patients);
		randomUHealthID = generatePatients(1).get(0).getUHealthID();
	}

	/**
	 * Runs the lookupByUHID method for the facility.
	 */
	@Override
	protected void runComputation() {
		randomFacility.lookupByUHID(randomUHealthID);
	}

	/**
	 * Generate unique CurrentPatients.
	 * 
	 * @param howMany - patients to make
	 * @return a list of CurrentPatients
	 */
	private ArrayList<CurrentPatient> generatePatients(int howMany) {
		ArrayList<CurrentPatient> patients = new ArrayList<CurrentPatient>(howMany);
		HashSet<UHealthID> idSet = new HashSet<UHealthID>(howMany);
		char[] prefix = new char[5];
		while(idSet.size() < howMany) {
			prefix[0] = (char)('A' + rng.nextInt(26));
			prefix[1] = (char)('A' + rng.nextInt(26));
			prefix[2] = (char)('A' + rng.nextInt(26));
			prefix[3] = (char)('A' + rng.nextInt(26));
			prefix[4] = '-';
			idSet.add(new UHealthID(new String(prefix) + String.format("%04d", rng.nextInt(10000))));
		}
		GregorianCalendar date = new GregorianCalendar(2025, 1, 1);
		for(UHealthID id : idSet) {
			patients.add(new CurrentPatient("name", "name", id, 1234567, date));
		}
		return patients;
	}
}