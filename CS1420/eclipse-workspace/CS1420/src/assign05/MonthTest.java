package assign05;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

/**
 * This class contains unit tests to check the correctness of the Month class.
 * 
 * @author CS 1420 course staff and UPDATE WITH YOUR NAME
 * @version UPDATE WITH MOST RECENT DATE
 */
public class MonthTest {
	@Test
	public void testNoParameterConstructor() {
		Month jan = new Month();
		assertEquals(1, jan.getMonthNumber(), "No-parameter constructor does not set month number to 1");
	}
	
	@Test
	public void testToString() {
		Month oct = new Month(10);
		assertEquals("October", oct.toString(), "toString does not return correct String -- check for typos");
	}
	
	@Test
	public void testLastDayFebruaryLeapYear() {
		Month feb = new Month(2);
		assertEquals(29, feb.lastDay(true), "Last day of February is incorrect for a leap year");
	}
	
	@Test
	public void testLastDayApril() {
		Month apr = new Month(4);
		assertEquals(30, apr.lastDay(false), "Last day of April is incorrect");
	}
	
	@Test
	public void testLastDayMay() {
		Month may = new Month(5);
		assertEquals(31, may.lastDay(false), "Last day of May is incorrect");
	}
	
	@Test
	public void testValidDayNormal() {
		Month jun = new Month(6);
		assertTrue(jun.validDay(10, false), "validDay incorrect for valid day");
	}
	
	@Test
	public void testValidDayFebruaryLeapYear() {
		Month feb = new Month(2);
		assertTrue(feb.validDay(29, true), "validDay incorrect for leap year February");
	}
	
	@Test
	public void testEqualsFalse() {
		Month sep = new Month(9);
		assertFalse(sep.equals(new Month(8)),
				"equals method does not return false when passed Month object that is the different");
	}
	
	@Test
	public void testEqualsNonMonth() {
		Month mar = new Month(3);
		assertFalse(mar.equals(new Scanner(System.in)),
				"equals method does not return false when passed a non-Month object");
	}
	
	// new
	@Test
	public void testLastDayFebruaryNonLeapYear() {
		Month feb = new Month(2);
		assertEquals(28, feb.lastDay(false), "Last day of February should be 28 for non-leap year");
	}
	
	// new
	@Test
	public void testLastDayAllMonthsLeapYear() {
		Month jan = new Month(1);
		assertEquals(31, jan.lastDay(true), "January should have 31 days even in leap year");
		
		Month feb = new Month(2);
		assertEquals(29, feb.lastDay(true), "February should have 29 days in leap year");
		
		Month mar = new Month(3);
		assertEquals(31, mar.lastDay(true), "March should have 31 days even in leap year");
	}
	
	// new
	@Test
	public void testLastDayAllMonthsNonLeapYear() {
		Month apr = new Month(4);
		assertEquals(30, apr.lastDay(false), "April should have 30 days");
		
		Month jun = new Month(6);
		assertEquals(30, jun.lastDay(false), "June should have 30 days");
		
		Month nov = new Month(11);
		assertEquals(30, nov.lastDay(false), "November should have 30 days");
	}
	
	// new
	@Test
	public void testValidDayLowerBoundary() {
		Month jul = new Month(7);
		assertTrue(jul.validDay(1, false), "Day 1 should be valid for any month");
	}
	
	// new
	@Test
	public void testValidDayUpperBoundary() {
		Month aug = new Month(8);
		assertTrue(aug.validDay(31, false), "Day 31 should be valid for August");
	}
	
	// new
	@Test
	public void testValidDayInvalidLow() {
		Month sep = new Month(9);
		assertFalse(sep.validDay(0, false), "Day 0 should be invalid for any month");
	}
	
	// new
	@Test
	public void testValidDayInvalidHigh() {
		Month oct = new Month(10);
		assertFalse(oct.validDay(32, false), "Day 32 should be invalid for October");
	}
	
	// new
	@Test
	public void testValidDayFebruaryNonLeapYear() {
		Month feb = new Month(2);
		assertFalse(feb.validDay(29, false), "Day 29 should be invalid for February in non-leap year");
	}
	
	// new
	@Test
	public void testValidDayFebruaryLeapYearBoundary() {
		Month feb = new Month(2);
		assertTrue(feb.validDay(29, true), "Day 29 should be valid for February in leap year");
		assertFalse(feb.validDay(30, true), "Day 30 should be invalid for February even in leap year");
	}
	
	// new
	@Test
	public void testToStringAllMonths() {
		Month jan = new Month(1);
		assertEquals("January", jan.toString(), "Month 1 should return 'January'");
		
		Month jul = new Month(7);
		assertEquals("July", jul.toString(), "Month 7 should return 'July'");
		
		Month dec = new Month(12);
		assertEquals("December", dec.toString(), "Month 12 should return 'December'");
	}
	
	// new
	@Test
	public void testEqualsWithNull() {
		Month month = new Month(5);
		assertFalse(month.equals(null), "Equals should return false when comparing with null");
	}
	
	// new
	@Test
	public void testEqualsReflexive() {
		Month month = new Month(6);
		assertTrue(month.equals(month), "Equals should be reflexive (month.equals(month) should be true)");
	}
	
	// new
	@Test
	public void testEqualsSymmetric() {
		Month month1 = new Month(4);
		Month month2 = new Month(4);
		assertTrue(month1.equals(month2) && month2.equals(month1), "Equals should be symmetric (both directions should agree)");
	}
	
	// new
	@Test
	public void testEqualsDifferentMonths() {
		Month month1 = new Month(3);
		Month month2 = new Month(11);
		assertFalse(month1.equals(month2), "Months with different numbers should not be equal");
	}
	
	// new
	@Test
	public void testGetMonthNumber() {
		Month month = new Month(8);
		assertEquals(8, month.getMonthNumber(), "getMonthNumber should return the correct month number");
	}
	
	// new
	@Test
	public void testLastDayInvalidMonth() {
		Month invalidMonth = new Month(12); 
		assertEquals(31, invalidMonth.lastDay(false), "December should return 31 days");
	}
	
	// new
	@Test
	public void testValidDayEdgeCases() {
		Month apr = new Month(4);
		assertTrue(apr.validDay(30, false), "Last day of April (30) should be valid");
		assertFalse(apr.validDay(31, false), "Day 31 should be invalid for April");
	}
}