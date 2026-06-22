package lab02;

/**
 * This class contains methods for exploring how the system clock works with
 * various calls to System.currentTimeMillis() and System.nanoTime().
 *
 * @author CS 2420 course staff
 * @version January 16, 2026
 */
public class SystemClockExploration {

	public static void main(String[] args) {
		measureCallsToCurrentTimeMillis();
//		measureCallsToNanoTime();
//		computeTimeToCallNanoTime();
	}

	/**
	 * Displays a measurement of how many times currentTimeMillis can be called
	 * before time advances.
	 */
	private static void measureCallsToCurrentTimeMillis() {
		System.out.println(
				"100 observations of how many times currentTimeMillis" + " can be called before time advances.");
		int checkCount;
		long prevTime, currTime;
		for(int i = 0; i < 100; i++) {
			prevTime = System.currentTimeMillis();
			checkCount = 1;
			currTime = System.currentTimeMillis();
			while(currTime == prevTime) {
				checkCount++;
				currTime = System.currentTimeMillis();
			}
			System.out.println("\tTime checks: " + checkCount + "\tElapsed time (ms): " + (currTime - prevTime));
		}
	}

	/**
	 * Displays a measurement of how many times nanoTime can be called before time
	 * advances.
	 */
	private static void measureCallsToNanoTime() {
		System.out.println("100 observations of how many times nanoTime" + " can be called before time advances.");
		int checkCount;
		long prevTime, currTime;
		for(int i = 0; i < 100; i++) {
			prevTime = System.nanoTime();
			checkCount = 1;
			currTime = System.nanoTime();
			while(currTime == prevTime) {
				checkCount++;
				currTime = System.nanoTime();
			}
			System.out.println("\tTime checks: " + checkCount + "\tElapsed time (ns): " + (currTime - prevTime));
		}
	}

	/**
	 * Displays the total and average time (ns) required to call nanoTime one
	 * million times.
	 */
	private static void computeTimeToCallNanoTime() {
		int iterationCount = 1_000_000;
		long endTime = 0;
		long startTime = System.nanoTime();
		for(int i = 0; i < iterationCount; i++) {
			endTime = System.nanoTime();
		}

		System.out.println("Observing the time it takes to call System.nanoTime() one million times");
		System.out.println("\tTotal time: " + (endTime - startTime) + " ns");
		System.out.println("\tAverage time: " + ((double) (endTime - startTime) / iterationCount) + " ns per call");
	}
}