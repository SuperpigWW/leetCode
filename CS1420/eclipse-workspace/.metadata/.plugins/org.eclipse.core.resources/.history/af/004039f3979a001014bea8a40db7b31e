package assign05;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * This class contains unit tests to check the correctness of the CalendarDate class.
 * 
 * @author CS 1420 course staff and UPDATE WITH YOUR NAME
 * @version UPDATE WITH MOST RECENT DATE
 */
public class CalendarDateTest {
	@Test
	public void testGetDay() {
		CalendarDate date = new CalendarDate(3, 15, 1950);
		assertEquals(15, date.getDay(), "getDay method incorrect");
	}
	
	@Test
	public void testToString() {
		CalendarDate date = new CalendarDate(8, 1, 1970);
		assertEquals("August 1, 1970", date.toString(),
				"toString does not return correct String -- check for typos");
	}
	
	@Test
	public void testComesBeforeTrue() {
		CalendarDate date = new CalendarDate(5, 19, 1985);
		assertTrue(date.comesBefore(new CalendarDate(6, 9, 1985)),
				"comesBefore does not return true when date on which method is called comes before argument");
	}
	
	@Test
	public void testComesAfterSameDate() {
		CalendarDate date = new CalendarDate(2, 7, 1888);
		assertFalse(date.comesAfter(new CalendarDate(2, 7, 1888)),
				"comesAfter does not return false when date on which method is called is the same as argument");
	}
	
	@Test
	public void testAdvanceOneDayEndOfMonth() {
		CalendarDate date = new CalendarDate(4, 30, 1200);
		date.advanceOneDay();
		assertEquals(5, date.getMonth().getMonthNumber(),
				"advanceOneDay does not add 1 to month when at the end of the month");
		assertEquals(1, date.getDay(),
				"advanceOneDay does set day to 1 when at the end of the month");
		assertEquals(1200, date.getYear(),
				"advanceOneDay changed year when at the end of the month (not December)");
	}
	
	@Test
	public void testDayOfYearFirst() {
		CalendarDate date = new CalendarDate(1, 1, 3000);
		assertEquals(1, date.dayOfYear(), "dayOfYear does not return 1 for the first day of a year");
	}
	
	@Test
	public void testIsLeapYearTrue() {
		CalendarDate date = new CalendarDate(1, 1, 2004);
		assertTrue(date.isLeapYear(), "isLeapYear does not return true for year divisible by 4 but not 100");
	}
	
	@Test
	public void testEqualsTrue() {
		CalendarDate date = new CalendarDate(10, 10, 3333);
		assertTrue(date.equals(new CalendarDate(10, 10, 3333)),
				"equals method does not return true for same dates");
	}
}