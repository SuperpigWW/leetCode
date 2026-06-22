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
}