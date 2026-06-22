package assign09;

import java.util.ArrayList;

import timing.ArrayListGenerator;

/**
 * Experiment to measure the number of collisions incurred for the put method in  
 * a hash table of students using a medium hash function, for various problem sizes.
 *
 * @author Haoquan Wang
 * @version April 2, 2026
 */
public class StudentMediumHashCollisionExperiment extends CollisionExperiment<StudentMediumHash, Object> {
    private static String problemSizeDescription = "element count";
    private static int problemSizeMin = 10000;
    private static int problemSizeCount = 10;
    private static int problemSizeStep = 10000;
    private static int experimentIterationCount = 5; 
    	
	private StudentMediumHash keyToPut;
    
    public static void main(String[] args) {
    	CollisionExperiment<StudentMediumHash, Object> collisionExperiment = new StudentMediumHashCollisionExperiment();
    	collisionExperiment.printResults();
    }

    public StudentMediumHashCollisionExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

	/**
	 * Creates a hash table of problemSize StudentMediumHash elements.
	 * 
	 * @param problemSize - the number of unique students to fill the hash table
	 */
    protected void setupExperiment(int problemSize) {
    	ArrayList<Integer> uniqueIntegers = ArrayListGenerator.generatePermutedArrayListWithoutDuplicates(3 * (problemSize + 1));

    	table = new HashTable<StudentMediumHash, Object>();
    	for(int i = 0; i < 3 * problemSize; i += 3) {
    		int uid = uniqueIntegers.get(i);
    		String firstName = "Abcde" + uniqueIntegers.get(i + 1);
    		String lastName = "Zyxwvut" + uniqueIntegers.get(i + 2);
    		table.put(new StudentMediumHash(uid, firstName, lastName), null);
    	}
    	
    	int uid = uniqueIntegers.get(3 * problemSize);
		String firstName = "Abcde" + uniqueIntegers.get(3 * problemSize + 1);
		String lastName = "Zyxwvut" + uniqueIntegers.get(3 * problemSize + 2);
    	keyToPut = new StudentMediumHash(uid, firstName, lastName);
    }
    
	/**
	 * Runs the put method for a hash table containing problemSize elements.
	 */
    protected void runComputation() {
    	table.put(keyToPut, null);
    }
}
