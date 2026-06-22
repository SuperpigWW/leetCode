package lab03;

import java.io.IOException;
import java.io.File;
import java.util.Scanner; 

public class TreeDataAnalyzer {

	public static void main(String[] args) throws IOException {
		
		File filename = new File("forest.txt"); // A file object just represents a path to a file
		Scanner file = new Scanner(filename); // Opens the file for reading (scanning)

		String treeName;
		int treeCount;
		int totalTreeCount = 0;
		int highestCount = 0, lowestCount = 0; 

		while (file.hasNext()) {
			treeName = file.next();     // Scan the next word from the file.
			treeCount = file.nextInt();     // Scan the next int from the file.
			System.out.println("Tree: " + treeName + " has a count of " + treeCount + ".");
			totalTreeCount += treeCount;
			if(treeCount > highestCount)
				highestCount = treeCount;
			if (totalTreeCount == treeCount || treeCount < lowestCount)
				lowestCount = treeCount;
			
		}
		
		System.out.println("Total tree count is " + totalTreeCount + ".");
		System.out.println("Highest tree count is " + highestCount + ".");
		System.out.println("Lowest tree count is " + lowestCount + ".");

		file.close();

	}

}
