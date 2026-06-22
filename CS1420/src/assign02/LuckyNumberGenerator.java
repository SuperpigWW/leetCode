// CS 1420 Assignment 2 by Haoquan Wang (August 27, 2025).

package assign02;

import java.util.Scanner;

public class LuckyNumberGenerator {

	public static void main(String[] args) {
		
		// Create a Scanner class variable 'input'.
		Scanner input = new Scanner(System.in);
		
		// Create a while loop to make the program execute repeatedly.
		while(true) {
		
			System.out.println("What is your name?");
			String name = input.nextLine();
			if ("exit".equals(name)) {
				System.out.println("Good luck!");
				break;
			}
			
			System.out.println("In what month were you born? "
					+ "(Enter the month as a number; i.e., 1 for January.)");
			String monthStr = input.nextLine();
			if ("exit".equals(monthStr)) {
				System.out.println("Good luck!");
				break;
			}
			// Convert a String type variable to an int type.
			int month = Integer.parseInt(monthStr);
			
			System.out.println("On what day of the month were you born? ");
			String dateStr = input.nextLine();
			if ("exit".equals(dateStr)) {
				System.out.println("Good luck!");
				break;
			}
			int date = Integer.parseInt(dateStr);
			
			int lucky;
			int sum = 0;
			char first = name.charAt(0);
			
			// Determine the type of the initial letter using the '||' symbol.
			if (first == 'A' || first == 'E' || first == 'I' || first == 'O' || 
				first == 'U' || first == 'a' || first == 'e' || first == 'i' || 
				first == 'o' || first == 'u')
				for(int i = 0; i <= name.length() - 1; i ++) {
					sum += name.charAt(i);
				}
			else if(first == 'B' || first == 'C' || first == 'D' || first == 'F' || 
					first == 'G' || first == 'H' || first == 'J' || first == 'K' || 
					first == 'L' || first == 'b' || first == 'c' || first == 'd' || 
					first == 'f' || first == 'g' || first == 'h' || first == 'j' || 
					first == 'k' || first == 'l')
				for(int j = 0; j <= name.length() - 1; j += 2) {
					sum += name.charAt(j);
				}
			else if(first == 'M' || first == 'N' || first == 'P' || first == 'Q' || 
					first == 'R' || first == 'S' || first == 'T' || first == 'V' || 
					first == 'W' || first == 'X' || first == 'Y' || first == 'Z' || 
					first == 'm' || first == 'n' || first == 'p' || first == 'q' || 
					first == 'r' || first == 's' || first == 't' || first == 'v' ||
					first == 'w' || first == 'x' || first == 'y' || first == 'z') 
				for(int k = 1; k <= name.length() - 1; k += 2) {
					sum += name.charAt(k);
				}
			lucky = (sum % date) + month;
			
			// Match the month numbers with their names.
			String monthPrint;
			if (month == 1)
				monthPrint = "January";
			else if(month == 2)
				monthPrint = "February";
			else if(month == 3)
				monthPrint = "March";
			else if(month == 4)
				monthPrint = "April";
			else if(month == 5)
				monthPrint = "May";
			else if(month == 6)
				monthPrint = "June";
			else if(month == 7)
				monthPrint = "July";
			else if(month == 8)
				monthPrint = "August";
			else if(month == 9)
				monthPrint = "September";
			else if(month == 10)
				monthPrint = "October";
			else if(month == 11)
				monthPrint = "November";
			else if(month == 12)
				monthPrint = "December";
			else
			    monthPrint = "Error";
			
			System.out.println("For " + name + " born on " + monthPrint + " " 
			                    + date +", the lucky number is " + lucky + ".");
		}
		input.close();

	}

}
