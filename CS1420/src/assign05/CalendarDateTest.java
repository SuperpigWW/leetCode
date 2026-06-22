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
	
	// new
	@Test
	public void testeBoundaryYears() {
		CalendarDate minDate = new CalendarDate(1, 1, 1000);
		assertEquals(1000, minDate.getYear(), "minimum year 1000");
		
		CalendarDate maxDate = new CalendarDate(12, 31, 9999);
		assertEquals(9999, maxDate.getYear(), "maximum year 9999");
	}
	
	// new
	@Test
	public void testComesBeforeSameDate() {
		CalendarDate date1 = new CalendarDate(5, 15, 2020);
		CalendarDate date2 = new CalendarDate(5, 15, 2020);
		assertFalse(date1.comesBefore(date2), "Same dates should not have comesBefore return true");
		assertFalse(date2.comesBefore(date1), "Same dates should not have comesBefore return true");
	}
	
	// new
	@Test
	public void testDayOfFebruaryLeapYear() {
		CalendarDate date = new CalendarDate(3, 1, 2020);
		assertEquals(61, date.dayOfYear(), "March 1 in leap year should be day 61");
	}
	
	//new
	@Test
	public void testDayOfYearFebruaryNonLeapYear() {
		CalendarDate date = new CalendarDate(3, 1, 2021);
		assertEquals(60, date.dayOfYear(), "March 1 in non-leap year should be day 60");
	}
	
	// new
	@Test
	public void testIsLeapYearInTimesOf400() {
		CalendarDate date = new CalendarDate(1, 1, 2000);
		assertTrue(date.isLeapYear(), "Year 2000 should be leap year");
	}
	
	
	    
	    @Test
	    public void testEqualsReflexive() {
	        CalendarDate date = new CalendarDate(5, 15, 2020);
	        assertTrue(date.equals(date), "Equals should be reflexive");
	    }
	    
	    @Test
	    public void testEqualsSymmetric() {
	        CalendarDate date1 = new CalendarDate(5, 15, 2020);
	        CalendarDate date2 = new CalendarDate(5, 15, 2020);
	        assertTrue(date1.equals(date2) && date2.equals(date1), "Equals should be symmetric");
	    }
	    
	    @Test
	    public void testEqualsWithNull() {
	        CalendarDate date = new CalendarDate(5, 15, 2020);
	        assertFalse(date.equals(null), "Equals should return false for null");
	    }
	    
	    @Test
	    public void testEqualsWithDifferentClass() {
	        CalendarDate date = new CalendarDate(5, 15, 2020);
	        assertFalse(date.equals("Not a date"), "Equals should return false for different class");
	    }
	    
	    @Test
	    public void testEqualsDifferentYear() {
	        CalendarDate date1 = new CalendarDate(5, 15, 2020);
	        CalendarDate date2 = new CalendarDate(5, 15, 2021);
	        assertFalse(date1.equals(date2), "Dates with different years should not be equal");
	    }
	    
	    @Test
	    public void testEqualsDifferentMonth() {
	        CalendarDate date1 = new CalendarDate(5, 15, 2020);
	        CalendarDate date2 = new CalendarDate(6, 15, 2020);
	        assertFalse(date1.equals(date2), "Dates with different months should not be equal");
	    }
	    
	    @Test
	    public void testEqualsDifferentDay() {
	        CalendarDate date1 = new CalendarDate(5, 15, 2020);
	        CalendarDate date2 = new CalendarDate(5, 16, 2020);
	        assertFalse(date1.equals(date2), "Dates with different days should not be equal");
	    }

	    @Test
	    public void testGetMonth() {
	        CalendarDate date = new CalendarDate(7, 4, 1776);
	        assertEquals(7, date.getMonth().getMonthNumber(), "getMonth should return correct month");
	    }
	    
	    @Test
	    public void testGetMonthForJanuary() {
	        CalendarDate date = new CalendarDate(1, 1, 2020);
	        assertEquals(1, date.getMonth().getMonthNumber(), "getMonth should return January as month 1");
	    }
	    
	    @Test
	    public void testGetMonthForDecember() {
	        CalendarDate date = new CalendarDate(12, 25, 2020);
	        assertEquals(12, date.getMonth().getMonthNumber(), "getMonth should return December as month 12");
	    }

	    @Test
	    public void testLastDayJanuary() {
	        CalendarDate date = new CalendarDate(1, 1, 2020);
	        assertEquals(31, date.getMonth().lastDay(false), "January should have 31 days");
	    }
	    
	    @Test
	    public void testLastDayFebruaryNonLeap() {
	        CalendarDate date = new CalendarDate(2, 1, 2021);
	        assertEquals(28, date.getMonth().lastDay(false), "February should have 28 days in non-leap year");
	    }
	    
	    @Test
	    public void testLastDayFebruaryLeap() {
	        CalendarDate date = new CalendarDate(2, 1, 2020);
	        assertEquals(29, date.getMonth().lastDay(true), "February should have 29 days in leap year");
	    }
	    
	    @Test
	    public void testLastDayApril() {
	        CalendarDate date = new CalendarDate(4, 1, 2020);
	        assertEquals(30, date.getMonth().lastDay(false), "April should have 30 days");
	    }
	    
	    @Test
	    public void testLastDayAugust() {
	        CalendarDate date = new CalendarDate(8, 1, 2020);
	        assertEquals(31, date.getMonth().lastDay(false), "August should have 31 days");
	    }

	    @Test
	    public void testComesBeforeDifferentYear() {
	        CalendarDate date1 = new CalendarDate(1, 1, 2020);
	        CalendarDate date2 = new CalendarDate(1, 1, 2021);
	        assertTrue(date1.comesBefore(date2), "Earlier year should come before");
	    }
	    
	    @Test
	    public void testComesBeforeSameYearDifferentMonth() {
	        CalendarDate date1 = new CalendarDate(5, 15, 2020);
	        CalendarDate date2 = new CalendarDate(6, 15, 2020);
	        assertTrue(date1.comesBefore(date2), "Earlier month in same year should come before");
	    }
	    
	    @Test
	    public void testComesBeforeSameMonthDifferentDay() {
	        CalendarDate date1 = new CalendarDate(5, 10, 2020);
	        CalendarDate date2 = new CalendarDate(5, 15, 2020);
	        assertTrue(date1.comesBefore(date2), "Earlier day in same month should come before");
	    }
	    
	    @Test
	    public void testComesBeforeSameDate() {
	        CalendarDate date1 = new CalendarDate(5, 15, 2020);
	        CalendarDate date2 = new CalendarDate(5, 15, 2020);
	        assertFalse(date1.comesBefore(date2), "Same date should not come before itself");
	    }
	    
	    @Test
	    public void testComesBeforeLaterDate() {
	        CalendarDate date1 = new CalendarDate(6, 15, 2020);
	        CalendarDate date2 = new CalendarDate(5, 15, 2020);
	        assertFalse(date1.comesBefore(date2), "Later date should not come before earlier date");
	    }
	    
	    @Test
	    public void testAdvanceOneDayNormal() {
	        CalendarDate date = new CalendarDate(5, 15, 2020);
	        date.advanceOneDay();
	        assertEquals(16, date.getDay(), "Day should increment by 1");
	        assertEquals(5, date.getMonth().getMonthNumber(), "Month should stay the same");
	        assertEquals(2020, date.getYear(), "Year should stay the same");
	    }
	    
    @Test
    public void testAdvanceOneDayEndOfMonth() {
        CalendarDate date = new CalendarDate(4, 30, 2020);
        date.advanceOneDay();
        assertEquals(1, date.getDay(), "Day should reset to 1 at end of month");
        assertEquals(5, date.getMonth().getMonthNumber(), "Month should increment at end of month");
    }
    
    @Test
    public void testAdvanceOneDayEndOfFebruaryLeap() {
        CalendarDate date = new CalendarDate(2, 28, 2020);
        date.advanceOneDay();
        assertEquals(29, date.getDay(), "Should advance to Feb 29 in leap year");
        assertEquals(2, date.getMonth().getMonthNumber(), "Should stay in February");
    }
    
    @Test
    public void testAdvanceOneDayEndOfFebruaryNonLeap() {
        CalendarDate date = new CalendarDate(2, 28, 2021);
        date.advanceOneDay();
        assertEquals(1, date.getDay(), "Should advance to March 1 in non-leap year");
        assertEquals(3, date.getMonth().getMonthNumber(), "Should go to March");
    }
    
    @Test
    public void testAdvanceOneDayEndOfYear() {
        CalendarDate date = new CalendarDate(12, 31, 2020);
        date.advanceOneDay();
        assertEquals(1, date.getDay(), "Day should reset to 1");
        assertEquals(1, date.getMonth().getMonthNumber(), "Month should reset to January");
        assertEquals(2021, date.getYear(), "Year should increment");
    }

    @Test
    public void testComesAfterDifferentYear() {
        CalendarDate date1 = new CalendarDate(1, 1, 2021);
        CalendarDate date2 = new CalendarDate(1, 1, 2020);
        assertTrue(date1.comesAfter(date2), "Later year should come after");
    }
    
    @Test
    public void testComesAfterSameYearDifferentMonth() {
        CalendarDate date1 = new CalendarDate(6, 15, 2020);
        CalendarDate date2 = new CalendarDate(5, 15, 2020);
        assertTrue(date1.comesAfter(date2), "Later month in same year should come after");
    }
    
    @Test
    public void testComesAfterSameMonthDifferentDay() {
        CalendarDate date1 = new CalendarDate(5, 20, 2020);
        CalendarDate date2 = new CalendarDate(5, 15, 2020);
        assertTrue(date1.comesAfter(date2), "Later day in same month should come after");
    }
    
    @Test
    public void testComesAfterSameDate() {
        CalendarDate date1 = new CalendarDate(5, 15, 2020);
        CalendarDate date2 = new CalendarDate(5, 15, 2020);
        assertFalse(date1.comesAfter(date2), "Same date should not come after itself");
    }
    
    @Test
    public void testComesAfterEarlierDate() {
        CalendarDate date1 = new CalendarDate(4, 15, 2020);
        CalendarDate date2 = new CalendarDate(5, 15, 2020);
        assertFalse(date1.comesAfter(date2), "Earlier date should not come after later date");
    }
    
    @Test
    public void testComesAfterUsesEqualsAndComesBefore() {
        CalendarDate date1 = new CalendarDate(5, 15, 2020);
        CalendarDate date2 = new CalendarDate(5, 16, 2020);
        // This tests that comesAfter correctly uses both equals and comesBefore
        assertFalse(date1.comesAfter(date2));
        assertTrue(date2.comesAfter(date1));
    }
}
	
}