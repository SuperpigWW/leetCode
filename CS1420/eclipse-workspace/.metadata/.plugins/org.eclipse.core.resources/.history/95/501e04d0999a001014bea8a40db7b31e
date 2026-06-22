package assign05;

/**
 * This class contains a main method to demonstrate how to create and use 
 * CalendarDate objects, as well as a method to count the number of dates
 * in a given array that come before a given target date (to be added by 
 * students).
 * 
 * @author CS 1420 course staff and UPDATE WITH YOUR NAME
 * @version UPDATE WITH MOST RECENT DATE
 */
public class CalendarDateDemo {

	public static void main(String[] args) {
		CalendarDate lastDayOfClass = new CalendarDate(12, 4, 2025);
		CalendarDate finalExamDate = new CalendarDate(12, 12, 2025);
 		
		System.out.println("The CS 1420 final exam is on " + finalExamDate + 
				", which is day " + finalExamDate.dayOfYear() + " of this year.");
		System.out.print("The last day of class is on " + lastDayOfClass + 
				", which is ");
		if(lastDayOfClass.comesBefore(finalExamDate))
			System.out.print("before");
		else if(lastDayOfClass.comesAfter(finalExamDate))
			System.out.print("after");
		else
			System.out.print("on the same day as");
		System.out.println(" the final exam.");
		
		finalExamDate.advanceOneDay();
		System.out.println("The next day of the exam period is " + finalExamDate + ".");
		
		CalendarDate[] classMeetings = new CalendarDate[28];
		classMeetings[0] = new CalendarDate(8, 27, 2025);
		classMeetings[1] = new CalendarDate(9, 10, 2025);
		classMeetings[2] = new CalendarDate(10, 22, 2025);
		classMeetings[3] = new CalendarDate(11, 17, 2025);
		classMeetings[4] = new CalendarDate(10, 27, 2025);
		classMeetings[5] = new CalendarDate(11, 19, 2025);
		classMeetings[6] = new CalendarDate(12, 3, 2025);
		classMeetings[7] = new CalendarDate(10, 1, 2025);
		classMeetings[8] = new CalendarDate(8, 18, 2025);
		classMeetings[9] = new CalendarDate(10, 20, 2025);
		classMeetings[10] = new CalendarDate(12, 1, 2025);
		classMeetings[11] = new CalendarDate(11, 5, 2025);
		classMeetings[12] = new CalendarDate(9, 17, 2025);
		classMeetings[13] = new CalendarDate(11, 10, 2025);
		classMeetings[14] = new CalendarDate(9, 29, 2025);
		classMeetings[15] = new CalendarDate(11, 12, 2025);
		classMeetings[16] = new CalendarDate(10, 29, 2025);
		classMeetings[17] = new CalendarDate(11, 3, 2025);
		classMeetings[18] = new CalendarDate(9, 24, 2025);
		classMeetings[19] = new CalendarDate(9, 8, 2025);
		classMeetings[20] = new CalendarDate(11, 24, 2025);
		classMeetings[21] = new CalendarDate(9, 15, 2025);
		classMeetings[22] = new CalendarDate(10, 13, 2025);
		classMeetings[23] = new CalendarDate(9, 22, 2025);
		classMeetings[24] = new CalendarDate(8, 20, 2025);
		classMeetings[25] = new CalendarDate(11, 26, 2025);
		classMeetings[26] = new CalendarDate(9, 3, 2025);
		classMeetings[27] = new CalendarDate(8, 25, 2025);
	
		// Uncomment print statement after defining the countDatesBefore method.
		/* System.out.println("There are " + countDatesBefore(classMeetings, new CalendarDate(10, 15, 2025)) +
				" class meetings before the midterm exam."); */
		// Expected output: There are 14 class meetings before the midterm exam.
	}
}