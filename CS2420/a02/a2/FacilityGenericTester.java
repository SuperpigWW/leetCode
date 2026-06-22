package assign02;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains tests for FacilityGeneric.
 * 
 * @author CS 2420 course staff and Haoquan Wang and Zewei Ren
 * @version 2026-1-21
 */
public class FacilityGenericTester {

	private FacilityGeneric<Integer> uIDFacility, emptyFacility, phase3Facility;
	private FacilityGeneric<UHealthID> uHIDFacility;
	private FacilityGeneric<String> nameFacility;

	private UHealthID[] uHIDs;
	private GregorianCalendar[] dates;
	private String[] firstNames, lastNames, physicianNames;
	// For phase 3
	private UHealthID p3id1, p3id2, p3id3, p3id4;
	private GregorianCalendar p3date1, p3date2, p3date3, p3date4;

	@BeforeEach
	void setUp() throws Exception {
		// Modifying these numbers will affect the provided tests
		int nPatients = 20;
		int nPhysicians = 8;

		uHIDs = generateUHIDs(nPatients + nPhysicians);
		dates = generateDates(nPatients);
		firstNames = generateNames(nPatients);
		lastNames = generateNames(nPatients);
		physicianNames = generateNames(nPhysicians);

		uIDFacility = new FacilityGeneric<Integer>();
		uHIDFacility = new FacilityGeneric<UHealthID>();
		nameFacility = new FacilityGeneric<String>();
		emptyFacility = new FacilityGeneric<Integer>();
		phase3Facility = new FacilityGeneric<Integer>();

		for (int i = 0; i < nPatients; i++) {
			uIDFacility.addPatient(new CurrentPatientGeneric<Integer>(firstNames[i], lastNames[i], uHIDs[i],
					1234567 + i % nPhysicians, dates[i]));
			uHIDFacility.addPatient(new CurrentPatientGeneric<UHealthID>(firstNames[i], lastNames[i], uHIDs[i],
					uHIDs[nPatients + i % nPhysicians], dates[i]));
			nameFacility.addPatient(new CurrentPatientGeneric<String>(firstNames[i], lastNames[i], uHIDs[i],
					physicianNames[i % nPhysicians], dates[i]));
		}

		p3id1 = new UHealthID("XXXX-1111");
		p3id2 = new UHealthID("BBBB-1111");
		p3id3 = new UHealthID("FFFF-1111");
		p3id4 = new UHealthID("BBBB-2222");
		p3date1 = new GregorianCalendar(2019, 1, 5);
		p3date2 = new GregorianCalendar(2019, 1, 4);
		p3date3 = new GregorianCalendar(2019, 1, 3);
		p3date4 = new GregorianCalendar(2019, 1, 2);

		phase3Facility.addPatient(new CurrentPatientGeneric<Integer>("A", "B", new UHealthID("XXXX-1111"), 7,
				new GregorianCalendar(2019, 1, 5)));
		phase3Facility.addPatient(new CurrentPatientGeneric<Integer>("A", "B", new UHealthID("BBBB-1111"), 7,
				new GregorianCalendar(2019, 1, 4)));
		phase3Facility.addPatient(new CurrentPatientGeneric<Integer>("A", "C", new UHealthID("FFFF-1111"), 7,
				new GregorianCalendar(2019, 1, 3)));
		phase3Facility.addPatient(new CurrentPatientGeneric<Integer>("R", "T", new UHealthID("BBBB-2222"), 7,
				new GregorianCalendar(2019, 1, 2)));

		// Extend this tester to add more tests for the facilities above,
		// as well as to create and test other facilities.
		// (HINT: For a larger facility, use the helpers at the end of this file to
		// generate names, IDs, and dates.)
	}

	// empty Facility tests --------------------------------------------------------

	@Test
	public void testEmptyLookupUHID() {
		assertNull(emptyFacility.lookupByUHID(uHIDs[0]));
	}

	@Test
	public void testEmptyLookupPhysician() {
		ArrayList<CurrentPatientGeneric<Integer>> patients = emptyFacility.lookupByPhysician(1010101);
		assertEquals(0, patients.size());
	}

	@Test
	public void testEmptySetVisit() {
		// ensure no exceptions thrown
		emptyFacility.setLastVisit(uHIDs[0], dates[3]);
	}

	@Test
	public void testEmptySetPhysician() {
		// ensure no exceptions thrown
		emptyFacility.setPhysician(uHIDs[0], 1010101);
	}

	@Test
	public void testEmptyGetRecentPatients() {
		ArrayList<CurrentPatientGeneric<Integer>> patients = emptyFacility.getRecentPatients(dates[4]);
		assertEquals(0, patients.size());
	}

	// uID Facility tests --------------------------------------------------------

	@Test
	public void testUIDLookupPhysicianCount() {
		ArrayList<CurrentPatientGeneric<Integer>> actualPatients = uIDFacility.lookupByPhysician(1234568);
		assertEquals(3, actualPatients.size());
	}

	@Test
	public void testUIDLookupPhysicianPatient() {
		Patient expectedPatient = new Patient(firstNames[1], lastNames[1], new UHealthID(uHIDs[1].toString()));
		ArrayList<CurrentPatientGeneric<Integer>> actualPatients = uIDFacility.lookupByPhysician(1234568);
		assertEquals(expectedPatient, actualPatients.get(0));
	}

	@Test
	public void testUIDLookupPhysicianReturnEmpty() {
		ArrayList<CurrentPatientGeneric<Integer>> actualPatients = uIDFacility.lookupByPhysician(9999999);
		assertEquals(0, actualPatients.size());
	}

	@Test
	public void testUIDLookupByUID() {
		CurrentPatientGeneric<Integer> patients = uIDFacility.lookupByUHID(uHIDs[5]);
		assertEquals(uHIDs[5], patients.getUHealthID());

	}

	@Test
	public void testSetPhysicianUID() {
		uIDFacility.setPhysician(uHIDs[5], 1010101);
		CurrentPatientGeneric<Integer> patients = uIDFacility.lookupByUHID(uHIDs[5]);
		assertEquals(1010101, patients.getPhysician());
	}

	@Test
	public void testUIDSetLastVisitDay() {
		uIDFacility.setLastVisit(uHIDs[5], new GregorianCalendar(2026, 1, 21));
		CurrentPatientGeneric<Integer> patients = uIDFacility.lookupByUHID(uHIDs[5]);
		assertEquals(new GregorianCalendar(2026, 1, 21), patients.getLastVisit());
	}

	// UHealthID facility tests ---------------------------------------------------

	@Test
	public void testUHIDLookupPhysicianCount() {
		ArrayList<CurrentPatientGeneric<UHealthID>> actualPatients = uHIDFacility
				.lookupByPhysician(uHIDs[uHIDs.length - 1]);
		assertEquals(2, actualPatients.size());
	}

	@Test
	public void testUHIDUpdatePhysician() {
		uHIDFacility.lookupByUHID(uHIDs[2]).updatePhysician(uHIDs[uHIDs.length - 1]);
		CurrentPatientGeneric<UHealthID> patient = uHIDFacility.lookupByUHID(uHIDs[2]);
		assertEquals(uHIDs[uHIDs.length - 1], patient.getPhysician());
	}

	@Test
	public void testUIDLookupByUHID() {
		CurrentPatientGeneric<UHealthID> patients = uHIDFacility.lookupByUHID(uHIDs[5]);
		assertEquals(uHIDs[5], patients.getUHealthID());

	}

	@Test
	public void testSetPhysicianUHID() {
		uHIDFacility.setPhysician(uHIDs[5], uHIDs[0]);
		CurrentPatientGeneric<UHealthID> patients = uHIDFacility.lookupByUHID(uHIDs[5]);
		assertEquals(uHIDs[0], patients.getPhysician());
	}

	@Test
	public void testUHIDSetLastVisitDay() {
		uHIDFacility.setLastVisit(uHIDs[5], new GregorianCalendar(2026, 1, 21));
		CurrentPatientGeneric<UHealthID> patients = uHIDFacility.lookupByUHID(uHIDs[5]);
		assertEquals(new GregorianCalendar(2026, 1, 21), patients.getLastVisit());
	}

	// name facility tests
	// -------------------------------------------------------------------------

	@Test
	public void testNameLookupPhysician() {
		Patient expectedPatient1 = new Patient(firstNames[1], lastNames[1], new UHealthID(uHIDs[1].toString()));
		Patient expectedPatient2 = new Patient(firstNames[9], lastNames[9], new UHealthID(uHIDs[9].toString()));

		ArrayList<CurrentPatientGeneric<String>> actualPatients = nameFacility.lookupByPhysician(physicianNames[1]);
		assertTrue(actualPatients.contains(expectedPatient1) && actualPatients.contains(expectedPatient2));
	}

	@Test
	public void testNameGetPhysicianList() {
		ArrayList<String> actual = nameFacility.getPhysicianList();
		assertEquals(8, actual.size());
	}

	@Test
	public void testNameLookupByUHID() {
		CurrentPatientGeneric<String> patients = nameFacility.lookupByUHID(uHIDs[5]);
		assertEquals(uHIDs[5], patients.getUHealthID());

	}

	@Test
	public void testSetPhysicianName() {
		nameFacility.setPhysician(uHIDs[5], "Seven");
		CurrentPatientGeneric<String> patients = nameFacility.lookupByUHID(uHIDs[5]);
		assertEquals("Seven", patients.getPhysician());
	}

	@Test
	public void testNameSetLastVisitDay() {
		nameFacility.setLastVisit(uHIDs[5], new GregorianCalendar(2026, 1, 21));
		CurrentPatientGeneric<String> patients = nameFacility.lookupByUHID(uHIDs[5]);
		assertEquals(new GregorianCalendar(2026, 1, 21), patients.getLastVisit());
	}

	// phase 3 tests

	@Test
	public void testOrderedByUHIDCount() {
		ArrayList<CurrentPatientGeneric<Integer>> actual = phase3Facility
				.getOrderedPatients(new OrderByUHealthID<Integer>());
		assertEquals(4, actual.size());
	}

	@Test
	public void testOrderedByUHIDOrder() {
		ArrayList<CurrentPatientGeneric<Integer>> actual = phase3Facility
				.getOrderedPatients(new OrderByUHealthID<Integer>());
		assertEquals(new CurrentPatientGeneric<Integer>("A", "B", p3id1, 7, p3date1), actual.get(3));
		assertEquals(new CurrentPatientGeneric<Integer>("A", "B", p3id2, 7, p3date2), actual.get(0));
		assertEquals(new CurrentPatientGeneric<Integer>("A", "C", p3id3, 7, p3date3), actual.get(2));
		assertEquals(new CurrentPatientGeneric<Integer>("R", "T", p3id4, 7, p3date4), actual.get(1));
	}

	@Test
	public void testOrderedByName() {
		ArrayList<CurrentPatientGeneric<Integer>> actual = phase3Facility
				.getOrderedPatients(new OrderByUHealthID<Integer>());
		assertEquals(new CurrentPatientGeneric<Integer>("A", "B", p3id2, 7, p3date2), actual.get(0));
		assertEquals(new CurrentPatientGeneric<Integer>("R", "T", p3id4, 7, p3date4), actual.get(1));
		assertEquals(new CurrentPatientGeneric<Integer>("A", "C", p3id3, 7, p3date3), actual.get(2));
		assertEquals(new CurrentPatientGeneric<Integer>("A", "B", p3id1, 7, p3date1), actual.get(3));
	}
	
	@Test
	public void testOrderedByDate() {
		ArrayList<CurrentPatientGeneric<Integer>> actual = phase3Facility
				.getOrderedPatients(new OrderByUHealthID<Integer>());
		assertEquals(new CurrentPatientGeneric<Integer>("R", "T", p3id4, 7, p3date4), actual.get(1));
		assertEquals(new CurrentPatientGeneric<Integer>("A", "C", p3id3, 7, p3date3), actual.get(2));
		assertEquals(new CurrentPatientGeneric<Integer>("A", "B", p3id2, 7, p3date2), actual.get(0));
		assertEquals(new CurrentPatientGeneric<Integer>("A", "B", p3id1, 7, p3date1), actual.get(3));
	}

	// Private helper methods
	// ---------------------------------------------------------------

	/**
	 * A private helper to generate unique UHealthIDs. Valid for up to 260,000 IDs.
	 * 
	 * @param howMany - IDs to make
	 * @return an array of UHealthIDs
	 */
	private UHealthID[] generateUHIDs(int howMany) {
		UHealthID[] ids = new UHealthID[howMany];
		for (int i = 0; i < howMany; i++) {
			String prefix = "JKL" + (char) ('A' + (i / 10000) % 26);
			ids[i] = new UHealthID(prefix + "-" + String.format("%04d", i % 10000));
		}
		return ids;
	}

	/**
	 * A private helper to generate dates.
	 * 
	 * @param howMany - dates to generate
	 * @return an array of dates
	 */
	private GregorianCalendar[] generateDates(int howMany) {
		GregorianCalendar[] dates = new GregorianCalendar[howMany];
		for (int i = 0; i < howMany; i++)
			dates[i] = new GregorianCalendar(2000 + i % 22, i % 12, i % 28);
		return dates;
	}

	/**
	 * A private helper to generate names.
	 * 
	 * @param howMany - names to generate
	 * @return an array of names
	 */
	private String[] generateNames(int howMany) {
		String[] names = new String[howMany];
		Random rng = new Random();
		for (int i = 0; i < howMany; i++)
			names[i] = "" + (char) ('A' + rng.nextInt(26)) + (char) ('a' + rng.nextInt(26))
					+ (char) ('a' + rng.nextInt(26)) + (char) ('a' + rng.nextInt(26));
		return names;
	}
}
