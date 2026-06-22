package assign05;

/**
 * Represents a date on the calendar with month, day, and year components.
 * The date is validated to ensure year is between 1000 and 9999 inclusive.
 * 
 * @author Haoquan Wang
 * @version 09.25.2025
 */
public class CalendarDate {
    
	private Month month;
    private int day;
    private int year;
    
    /**
     * Constructs a CalendarDate object for January 1, 1000.
     */
    public CalendarDate() {
        this.month = new Month(1);
        this.day = 1;
        this.year = 1000;
    }
    
    /**
     * Constructs a CalendarDate object with the specified month, day, and year.
     * 
     * @param monthNumber the month number (1-12)
     * @param day the day of the month
     * @param year the year (must be between 1000 and 9999 inclusive)
     * @throws IllegalArgumentException if year is not between 1000 and 9999
     */
    public CalendarDate(int monthNumber, int day, int year) {
        
    	if (year < 1000 || year > 9999) {    
            throw new IllegalArgumentException("Year out of range, it must be between 1000 and 9999");
        }
        
        this.month = new Month(monthNumber);
        this.day = day;
        this.year = year;
    }
    
    /**
     * Returns the Month object of this date.
     * 
     * @return the Month object
     */
    public Month getMonth() {
        return this.month;
    }
    
    /**
     * Returns the day of the month.
     * 
     * @return the day of the month
     */
    public int getDay() {
        return this.day;
    }
    
    /**
     * Returns the year.
     * 
     * @return the year
     */
    public int getYear() {
        return this.year;
    }
    
    /**
     * Determines whether this date comes before the specified date.
     * 
     * @param other the date to compare with
     * @return true if this date comes before the other date, false otherwise
     */
    public boolean comesBefore(CalendarDate other) {
        // First compare years
    	if (this.year < other.year) {
            return true;
        } else if (this.year > other.year) {
            return false;
        }
        
        // If years are equal, compare months
        if (this.month.getMonthNumber() < other.month.getMonthNumber()) {
            return true;
        } else if (this.month.getMonthNumber() > other.month.getMonthNumber()) {
            return false;
        }
        
        // If months are equal, compare days
        return this.day < other.day;
    }
    
    /**
     * Determines whether this date comes after the specified date.
     * 
     * @param other the date to compare with
     * @return true if this date comes after the other date, false otherwise
     */
    public boolean comesAfter(CalendarDate other) {
        // A date comes after another if it's not equal and not before
        return !this.equals(other) && !this.comesBefore(other);
    }
    
    /**
     * Advances this date by one day. Moves to the next month or year if necessary.
     */
    public void advanceOneDay() {
        // Get the last day of current month considering leap year
    	int lastDay = this.month.lastDay(this.isLeapYear());
        
        if (this.day < lastDay) {
            this.day++;  // Simple case: just increment day within same month
        } else {
            this.day = 1;  // Reset to first day of month
            if (this.month.getMonthNumber() < 12) {
                this.month = new Month(this.month.getMonthNumber() + 1);  // Next month
            } else {
                this.month = new Month(1);  // January of next year
                this.year++;  // Increment year when crossing December
            }
        }
    }
    
    /**
     * Calculates the day of the year for this date (1-based).
     * 
     * @return the day number within the year (1-365 or 1-366 for leap years)
     */
    public int dayOfYear() {
        // Accumulate days from previous months
    	int dayCount = 0;
        
    	for (int i = 1; i < this.month.getMonthNumber(); i++) {
            Month tempMonth = new Month(i);
            dayCount += tempMonth.lastDay(this.isLeapYear());  // Add days from each previous month
        }
        
    	dayCount += this.day;  // Add days from current month
        return dayCount;
    }
    
    /**
     * Determines whether the year of this date is a leap year.
     * A year is a leap year if divisible by 4, unless divisible by 100 but not by 400.
     * 
     * @return true if this is a leap year, false otherwise
     */
    public boolean isLeapYear() {
        // Leap year rules in order of precedence
    	if (this.year % 400 == 0) {
            return true;  // Divisible by 400: always leap year
        } else if (this.year % 100 == 0) {
            return false; // Divisible by 100 but not 400: not leap year
        } else {
            return this.year % 4 == 0;  // Divisible by 4: leap year
        }
    }
    
    /**
     * Returns a string representation of this date in the format "Month Day, Year".
     * 
     * @return the string representation of this date
     */
    public String toString() {
        return this.month.toString() + " " + this.day + ", " + this.year;
    }
    
    /**
     * Compares this date to the specified object for equality.
     * Two dates are equal if they have the same month, day, and year.
     * 
     * @param other the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    public boolean equals(Object other) {
        // Check if same object reference
    	if (this == other) 
            return true;                    
        
        // Check for null and class type match
    	if (other == null || getClass() != other.getClass()) 
            return false;
        
        // Safe cast after type checking
    	CalendarDate otherDate = (CalendarDate) other;
        return this.year == otherDate.year &&
               this.day == otherDate.day &&
               this.month.equals(otherDate.month);  // Use Month's equals method
    }
}