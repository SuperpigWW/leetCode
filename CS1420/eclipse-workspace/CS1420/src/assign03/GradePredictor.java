// CS 1420 Assignment 3 by Haoquan Wang (September 5, 2025).

package assign03;

import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.util.Arrays;

public class GradePredictor {

	public static void main(String[] args) {
		
		// Create a boolean variable 'judge' to determine if the file was successfully scanned
		boolean judge = false;
		Scanner file = null;
		Scanner input = new Scanner(System.in);
		
		/* Use a try-catch structure to catch the IOException error type 
		 * and print "Error: File not found.%n"
		 */
		while (! judge) {
			try {
				System.out.printf("Enter a file name with path:%n");
				String filenameIn = input.nextLine();
				File filename = new File(filenameIn); 
				file = new Scanner(filename);
				judge = true;
			}catch(IOException e) {
				System.out.printf("Error: File not found.%n");
			}
		}
		
		double midSC = file.nextDouble();
		double finalSC = file.nextDouble();
		double avgLabSC = file.nextDouble();
		double avgQuizSC = file.nextDouble();
		int number = file.nextInt();
		
		// Create an int array 'assSC' to store and sort assignment scores
		int assSC[] = new int[number];
		double sumAssSC = 0.0;
		int countZero = 0;
		
		// Use an accumulation loop to traverse the array and obtain the total assignment score
		for (int i = 0; i < number; i ++) {
			assSC[i] = file.nextInt();
			sumAssSC += assSC[i];
			if (assSC[i] == 0)
				countZero += 1;	
		}
		double avgAssSC = sumAssSC / number;
		
		Arrays.sort(assSC);
		int highestSC = assSC[number - 1];
		int lowestSC = assSC[0];
	        
		int medAssSC;
		// Prevent the median calculation formula from reporting an error when the array length is 1
		if (number == 1)
			medAssSC = assSC[0];
		else
			medAssSC = assSC[(number - 2) / 2 + 1];
			
		// Use %.2f to control the output of a double type to display two decimal places
		System.out.printf("The average assignment score is %.2f.%n", avgAssSC);
		System.out.printf("The median assignment score is %d.%n", medAssSC);
		System.out.printf("The number of 0 assignment scores is %d.%n", countZero);
		System.out.printf("The highest assignment score is %d.%n", highestSC);
		System.out.printf("The lowest assignment score is %d.%n", lowestSC);
		
		// Calculate the weight of the scores that have been obtained so far
		double weightedSC = 10 * midSC + 15 * finalSC + 20 * avgAssSC + 10 * avgLabSC
							+ 5 * avgQuizSC;
		
		// Create arrays of equal length to store grades and standard scores respectively
		// This facilitates subsequent retrieval through the same index
		String level[] = new String[] {"C-", "C", "C+", "B-", "B", "B+", "A-", "A"};
		double levelSC[] = new double[] {70, 73, 77, 80, 83, 87, 90, 93};
		double testSC;
		
		for (int j = 0; j < level.length; j ++) {
			testSC = (levelSC[j] * 100 - weightedSC) / 40;
			if (testSC > 100) {
				System.out.printf("It is not possible to achieve %s with these scores.", level[j]);
				break;
			}
			else
				System.out.printf("A test average of %.2f is needed to achieve %s.%n", 
						testSC, level[j]);
					
		}
		
		// Close the Scanner variable to release memory
		file.close();
	}

}
