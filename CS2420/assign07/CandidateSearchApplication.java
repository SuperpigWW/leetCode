package assign07;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * This class provides a command-line application for searching candidate information
 * from data files. It reads candidate data from a file and searches for specific
 * candidates using binary search algorithm.
 * 
 * @author Haoquan Wang
 * @version 10/23/2025
 */
public class CandidateSearchApplication {

	/**
	 * Main method that serves as the entry point for the candidate search application.
	 * The application expects three command-line arguments:
	 * 1. File path to the candidate data file
	 * 2. Name of the candidate to search for
	 * 3. Age of the candidate to search for
	 * 
	 * The candidate data file must follow the specified format: the first line contains
	 * the number of candidates, followed by three pieces of information for each candidate
	 * (name, age, and rating) on subsequent lines.
	 * 
	 * @param args command-line arguments containing file path, candidate name, and candidate age
	 */
	public static void main(String[] args) {
		if (args.length < 3) {
	        System.out.println(" There should be at least three command-line arguments.");
	        return;
	    }
		String filePath = args[0];
	    String name = args[1]; 
	    int age = Integer.parseInt(args[2]);
	    Scanner scanner = null;
	    File filename = new File(filePath);
	    int numCandidates = 0;
	    try {
	    	scanner = new Scanner(filename);
	    	numCandidates = scanner.nextInt();
	    }catch(FileNotFoundException e) {
	    	System.out.println("Please enter a valid filename and path.");
	    }
	    int i = 0;
	    Candidate[] candidate = new Candidate[numCandidates];
	    while (scanner.hasNextLine()) {
	    	candidate[i] = new Candidate(scanner.next(), scanner.nextInt(), scanner.nextInt());
	    	i ++;
	    }
	    Candidate candidateTarget = new Candidate(name, age);
	    if (CandidateSearch.binarySearch(candidate, candidateTarget) == null)
	    	System.out.println(name + "(" + age + ") was not found.");
	    else {
	    	Candidate searchResult = CandidateSearch.binarySearch(candidate, candidateTarget);
		    int rate = searchResult.getRating();
		    System.out.println(name + "(" + age + ") has rating " + rate + ".");
	    }
	    System.out.println(CandidateSearch.getCallCount());
	    
	    
	}

}
