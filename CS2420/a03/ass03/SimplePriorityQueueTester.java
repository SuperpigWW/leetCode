package assign03;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * This class contains unit tests to check the correctness of the SimplePriorityQueue class.
 * 
 * @author HaoQuan Wang and Zewei Ren
 * @version 2026.1.29
 */
public class SimplePriorityQueueTester {

	@Test
	public void priorityQueueIsEmpty() {
		SimplePriorityQueue<Integer> priorityQueue = new SimplePriorityQueue<>();
		assertTrue(priorityQueue.isEmpty());
	}

	@Test
	public void priorityQueueSize0() {
		SimplePriorityQueue<Integer> priorityQueue = new SimplePriorityQueue<>();
		assertEquals(0, priorityQueue.size());
	}

	@Test
	public void priorityQueueSizeInArray() {
		SimplePriorityQueue<Integer> priorityQueue = new SimplePriorityQueue<>();
		priorityQueue.insertAll(Arrays.asList(4, 7, 10, 11, 34, 3));
		assertEquals(6, priorityQueue.size());
	}

	@Test
	public void priorityQueueDeleteMaxThrowException() {
		SimplePriorityQueue<Integer> priorityQueue = new SimplePriorityQueue<>();
		assertThrows(NoSuchElementException.class, () -> priorityQueue.deleteMax());
	}

	@Test
	public void priorityQueueDeleteMaxInArray() {
		SimplePriorityQueue<Integer> priorityQueue = new SimplePriorityQueue<>();
		priorityQueue.insertAll(Arrays.asList(4, 7, 10, 11, 34, 3));
		priorityQueue.deleteMax();
		assertEquals(5, priorityQueue.size());
		assertEquals(11, priorityQueue.deleteMax());
		assertEquals(10, priorityQueue.deleteMax());
		assertEquals(7, priorityQueue.deleteMax());
		assertEquals(4, priorityQueue.deleteMax());
		assertEquals(3, priorityQueue.deleteMax());
	}

	@Test
	public void priorityQueueDeleteMax() {
		SimplePriorityQueue<Integer> priorityQueue = new SimplePriorityQueue<>();
		priorityQueue.insert(3);
		priorityQueue.deleteMax();
		assertEquals(0, priorityQueue.size());
	}

	@Test
	public void priorityQueueFindMaxThrowException() {
		SimplePriorityQueue<Integer> priorityQueue = new SimplePriorityQueue<>();
		assertThrows(NoSuchElementException.class, () -> priorityQueue.findMax());
	}

	@Test
	public void priorityQueueFindMax() {
		SimplePriorityQueue<Integer> priorityQueue = new SimplePriorityQueue<>();
		priorityQueue.insert(3);
		assertEquals(3, priorityQueue.findMax());
	}

	@Test
	public void priorityQueueFindMaxInArray() {
		SimplePriorityQueue<Integer> priorityQueue = new SimplePriorityQueue<>();
		priorityQueue.insertAll(Arrays.asList(4, 7, 10, 11, 34, 3));
		assertEquals(34, priorityQueue.findMax());
	}

	@Test
	public void priorityQueueContainsTrue() {
		SimplePriorityQueue<Integer> priorityQueue = new SimplePriorityQueue<>();
		priorityQueue.insert(3);
		assertTrue(priorityQueue.contains(3));
	}
	
	@Test
	public void priorityQueueContainsFalse() {
		SimplePriorityQueue<Integer> priorityQueue = new SimplePriorityQueue<>();
		assertFalse(priorityQueue.contains(3));
	}

	@Test
	public void priorityQueueContainsAllFalse() {
		SimplePriorityQueue<Integer> priorityQueue = new SimplePriorityQueue<>();
		priorityQueue.insertAll(Arrays.asList(4, 7, 10, 11, 34, 3));
		assertFalse(priorityQueue.containsAll(Arrays.asList(1, 50)));
	}
	
	@Test
	public void priorityQueueContainsAllTrue() {
		SimplePriorityQueue<Integer> priorityQueue = new SimplePriorityQueue<>();
		priorityQueue.insertAll(Arrays.asList(1,2,3,4,5));
		assertTrue(priorityQueue.containsAll(Arrays.asList(1, 5)));
	}

	@Test
	public void priorityQueueClear() {
		SimplePriorityQueue<Integer> priorityQueue = new SimplePriorityQueue<>();
		priorityQueue.insertAll(Arrays.asList(4, 7, 10, 11, 34, 3));
		priorityQueue.clear();
		assertTrue(priorityQueue.isEmpty());
		assertEquals(0, priorityQueue.size());
	}
}
