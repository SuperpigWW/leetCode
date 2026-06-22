package assign09;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains tests for the BetterDynamicArray class. 
 * 
 * @author CS 1420 course staff and Haoquan Wang
 * @version 2025-11-06
 */
public class BetterDynamicArrayTester {   
    
	// These variables can be used in each test.
	// Feel free to add more.
	private static BetterDynamicArray threeEvents;
	private static AudioEvent e1, e2, e3, e4, e5, e6;
	
	// A private helper for making AudioEvent objects.
	// This only uses VolumeEvent objects, but assuming your
	// AudioEvent subclasses are correct, it should work for any of them.
	private static AudioEvent makeEvent(int time) {
		return new VolumeEvent(time, 1, 1);
	}
	
	// This code executes before each test.
	// You can reference threeEvents in your tests without having to create it.
	@BeforeEach
	public void setup(){
		e1 = makeEvent(1);
		e2 = makeEvent(2);
		e3 = makeEvent(3);
		e4 = makeEvent(4);
		e5 = makeEvent(5);
		e6 = makeEvent(6);
		threeEvents = new BetterDynamicArray();
		threeEvents.add(e1);
		threeEvents.add(e2);
		threeEvents.add(e3);
	}
       
    @Test
    public void testConstructor() {
        BetterDynamicArray array = new BetterDynamicArray();
        assertEquals(0, array.size(), "Constructor did not yield a 0-sized dynamic array");
        assertEquals("[] backing array length: 10", array.toString(), 
        		"Constructor did not yield the correct dynamic array (via toString)");
    }
        
    @Test
    public void testConstructorCreatesDistinctArrays() {
        BetterDynamicArray array = new BetterDynamicArray();
        BetterDynamicArray otherArray = new BetterDynamicArray();
        otherArray.add(new VolumeEvent(4, 1, 1));
        assertEquals(0, array.size(), 
        		"Appending an element to one DynamicArray object changed the size of a different DynamicArray object");
    }
    
    @Test
    public void testAppendSimple() {
        String expected = "[" + e1 + ", " + e2 + ", " + e3 + "]" + " backing array length: 10";
        assertEquals(expected, threeEvents.toString(), "Failed adding 3 elements to empty dynamic array");
        assertEquals(3, threeEvents.size(), "Incorrect size after adding 3 elements to dynamic array");
    }
    
    @Test
    public void testAppendLarger() {
        // Appending >= 10 elements tests the double-length growth of a dynamic array.
        AudioEvent[] largeArray = new AudioEvent[11];
        for(int i = 0; i < largeArray.length; i++) {
        	largeArray[i] = makeEvent(i);
        }
        BetterDynamicArray array = new BetterDynamicArray();
        for(AudioEvent elem : largeArray) 
            array.add(elem);
        String expected = Arrays.toString(largeArray) + " backing array length: 20";
        assertEquals(expected, array.toString(), "Failed adding 11 elements to dynamic array");
        assertEquals(11, array.size(), "Incorrect size after adding 11 elements to dynamic array");
    }
    
    @Test
    public void testInsertFront() {
        threeEvents.insert(0, e5);
        String expected = "[" + e5 + ", " + e1 + ", " + e2 + ", " + e3 + "]" + " backing array length: 10";
        assertEquals(expected, threeEvents.toString(), "Failed inserting an element to the front");
        assertEquals(4, threeEvents.size(), "Incorrect size after inserting element to the front");
    }
    
    @Test
    public void testInsertMiddle() {
    	threeEvents.insert(1, e6);
        String expected = "[" + e1 + ", " + e6 + ", " + e2 + ", " + e3 + "]" + " backing array length: 10";
        assertEquals(expected, threeEvents.toString(), "Failed inserting an element to the middle");
        assertEquals(4, threeEvents.size(), "Incorrect size after inserting element to the middle");
    }
    
    @Test
    public void testInsertEnd() {
    	threeEvents.insert(3, e4);
        String expected = "[" + e1 + ", " + e2 + ", " + e3 + ", " + e4 + "]" + " backing array length: 10";
        assertEquals(expected, threeEvents.toString(), "Failed inserting an element to the end");
        assertEquals(4, threeEvents.size(), "Incorrect size after inserting element to the end");
    }
    
    @Test
    public void testInsertInvalidLowIndex() {
    	// This assertion checks that calling insert with an index that is too low throws the
    	// IndexIndexOutOfBoundsException.
    	assertThrows(IndexOutOfBoundsException.class, () -> { threeEvents.insert(-1, e4); },
    			"Failed to throw exception when inserting with too-low index");
    }
    
    @Test
    public void testInsertInvalidHighIndex() {
    	assertThrows(IndexOutOfBoundsException.class, () -> { threeEvents.insert(4, e4); }, 
    			"Failed to throw exception when inserting with too-high index");
    }
    
    @Test
    public void testGetElement(){
        assertEquals(e1, threeEvents.get(0), "Failed getting element from front");
        assertEquals(e2, threeEvents.get(1), "Failed getting element from middle");
        assertEquals(e3, threeEvents.get(2), "Failed getting element from end");
    }
    
    @Test
    public void testGetElementSizeUnchanged(){
        threeEvents.get(0);
        threeEvents.get(1);
        threeEvents.get(2);
        assertEquals(3, threeEvents.size(), "Calling getElement changed the size of dynamic array");
    }
    
    @Test
    public void testGetElementInvalidLow() {
    	assertThrows(IndexOutOfBoundsException.class, () -> { threeEvents.get(-1); }, 
    			"Failed to throw exception when getting element with too-low index");
    }
    
    @Test
    public void testGetElementInvalidHigh() {
    	assertThrows(IndexOutOfBoundsException.class, () -> { threeEvents.get(3); },
    			"Failed to throw exception when getting element with too-high index");
    }
    
    @Test
    public void testDoublingIsFaster() {
        double betterDynamicArrayTime = DynamicArrayTimer.addToBetterDynamicArray(10000);
        double regularDynamicArrayTime = DynamicArrayTimer.addToDynamicArray(10000);
        assertTrue(betterDynamicArrayTime < regularDynamicArrayTime,
                "The time to add 10k items to a doubling dynamic array should be faster, " +
                "but it is not with the current implementation");
    }
    
    //New
    @Test
    public void testSetFront() {
        threeEvents.set(0, e4);
        assertEquals(e4, threeEvents.get(0), "Failed to set element at front");
        assertEquals(3, threeEvents.size(), "Size changed after set operation at front");
    }
    
    //New
    @Test
    public void testSetMiddle() {
        threeEvents.set(1, e5);
        assertEquals(e5, threeEvents.get(1), "Failed to set element at middle");
        assertEquals(3, threeEvents.size(), "Size changed after set operation at middle");
    }
    
    //New
    @Test
    public void testSetEnd() {
        threeEvents.set(2, e6);
        assertEquals(e6, threeEvents.get(2), "Failed to set element at end");
        assertEquals(3, threeEvents.size(), "Size changed after set operation at end");
    }
    
    //New
    @Test
    public void testSetInvalidLowIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> { threeEvents.set(-1, e4); },
                "Failed to throw exception when setting with too-low index");
    }
    
    //New
    @Test
    public void testSetInvalidHighIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> { threeEvents.set(3, e4); },
                "Failed to throw exception when setting with too-high index");
    }
    
    //New
    @Test
    public void testRemoveInvalidLowIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> { threeEvents.remove(-1); },
                "Failed to throw exception when removing with too-low index");
    }
    
    //New
    @Test
    public void testRemoveInvalidHighIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> { threeEvents.remove(3); },
                "Failed to throw exception when removing with too-high index");
    }
    
    //New
    @Test
    public void testRemoveFromEmptyArray() {
        BetterDynamicArray emptyArray = new BetterDynamicArray();
        assertThrows(IndexOutOfBoundsException.class, () -> { emptyArray.remove(0); },
                "Failed to throw exception when removing from empty array");
    }
    
    //New
    @Test
    public void testRemoveByValueFront() {
        threeEvents.remove(e1);
        assertEquals(2, threeEvents.size(), "Size not updated after removing by value from front");
        assertEquals(e2, threeEvents.get(0), "Elements not shifted correctly after removing by value from front");
    }
    
    //New
    @Test
    public void testRemoveByValueMiddle() {
        threeEvents.remove(e2);
        assertEquals(2, threeEvents.size(), "Size not updated after removing by value from middle");
        assertEquals(e1, threeEvents.get(0), "First element changed after removing by value from middle");
        assertEquals(e3, threeEvents.get(1), "Elements not shifted correctly after removing by value from middle");
    }
    
    //New
    @Test
    public void testRemoveByValueEnd() {
        threeEvents.remove(e3);
        assertEquals(2, threeEvents.size(), "Size not updated after removing by value from end");
        assertEquals(e1, threeEvents.get(0), "First element changed after removing by value from end");
        assertEquals(e2, threeEvents.get(1), "Second element changed after removing by value from end");
    }
    
    //New
    @Test
    public void testRemoveByValueNotFound() {
        AudioEvent nonExistent = makeEvent(99);
        threeEvents.remove(nonExistent);
        assertEquals(3, threeEvents.size(), "Size changed when removing non-existent value");
        // Verify all original elements are still present
        assertEquals(e1, threeEvents.get(0), "First element changed when removing non-existent value");
        assertEquals(e2, threeEvents.get(1), "Second element changed when removing non-existent value");
        assertEquals(e3, threeEvents.get(2), "Third element changed when removing non-existent value");
    }
    
    //New
    @Test
    public void testRemoveByValueDuplicate() {
        // Add duplicate element
        threeEvents.add(e1);
        threeEvents.remove(e1);
        assertEquals(3, threeEvents.size(), "Size not correct after removing duplicate value");
        // Should remove the first occurrence only
        assertEquals(e2, threeEvents.get(0), "Wrong element at position 0 after removing duplicate");
        assertEquals(e1, threeEvents.get(2), "Second occurrence should still be present");
    }
    
    //New
    @Test
    public void testClearNonEmptyArray() {
        threeEvents.clear();
        assertEquals(0, threeEvents.size(), "Size not zero after clear");
        assertEquals("[] backing array length: 10", threeEvents.toString(), 
                "Array not empty after clear");
    }
    
    //New
    @Test
    public void testClearEmptyArray() {
        BetterDynamicArray emptyArray = new BetterDynamicArray();
        emptyArray.clear();
        assertEquals(0, emptyArray.size(), "Size not zero after clearing empty array");
        assertEquals("[] backing array length: 10", emptyArray.toString(), 
                "Empty array not correct after clear");
    }
    
    //New
    @Test
    public void testRemoveFront() {
        int initialSize = threeEvents.size();
        threeEvents.remove(0);
        assertEquals(initialSize - 1, threeEvents.size(), "Size not updated correctly after removing from front");
        assertEquals(e2, threeEvents.get(0), "Elements not shifted correctly after removing from front");
    }

    //New
    @Test
    public void testRemoveMiddle() {
        int initialSize = threeEvents.size();
        threeEvents.remove(1);
        assertEquals(initialSize - 1, threeEvents.size(), "Size not updated correctly after removing from middle");
        assertEquals(e1, threeEvents.get(0), "First element changed after removing from middle");
        assertEquals(e3, threeEvents.get(1), "Elements not shifted correctly after removing from middle");
    }

    //New
    @Test
    public void testRemoveEnd() {
        int initialSize = threeEvents.size();
        threeEvents.remove(2);
        assertEquals(initialSize - 1, threeEvents.size(), "Size not updated correctly after removing from end");
        assertEquals(e1, threeEvents.get(0), "First element changed after removing from end");
        assertEquals(e2, threeEvents.get(1), "Second element changed after removing from end");
    }
    //New
    @Test
    public void testSortEmptyArray() {
        BetterDynamicArray emptyArray = new BetterDynamicArray();
        emptyArray.sort();
        assertEquals(0, emptyArray.size(), "Size changed after sorting empty array");
    }
    
    //New
    @Test
    public void testSortSingleElement() {
        BetterDynamicArray single = new BetterDynamicArray();
        single.add(e1);
        single.sort();
        assertEquals(1, single.size(), "Size changed after sorting single element");
        assertEquals(e1, single.get(0), "Single element changed after sort");
    }
    
  //New
    @Test
    public void testOperationsOnDoubledArray() {
        BetterDynamicArray largeArray = new BetterDynamicArray();
        
        for(int i = 0; i < 15; i++) {
            largeArray.add(makeEvent(i * 2));  
        }
        
        assertEquals(15, largeArray.size(), "Size incorrect after filling array");
        
        largeArray.set(10, e5);
        assertEquals(e5, largeArray.get(10), "Set/Get failed on doubled array");
        
        largeArray.insert(5, e6);
        assertEquals(e6, largeArray.get(5), "Insert failed on doubled array");
        assertEquals(16, largeArray.size(), "Size incorrect after insert");
        
        largeArray.remove(5);
        assertEquals(15, largeArray.size(), "Size incorrect after remove");
        
        assertNotNull(largeArray.get(0), "Array corrupted after operations");
    }
    
    //New
    @Test
    public void testMultipleDoublingOperations() {
        BetterDynamicArray array = new BetterDynamicArray();
        // Add enough elements to cause multiple doublings
        for(int i = 0; i < 25; i++) {
            array.add(makeEvent(i));
        }
        
        assertEquals(25, array.size(), "Size incorrect after multiple doublings");
        // Backing array should be 40 (10 -> 20 -> 40)
        assertTrue(array.toString().contains("backing array length: 40"), 
                "Backing array not correct size after multiple doublings");
        
        // Test various operations
        array.insert(10, e1);
        assertEquals(26, array.size(), "Size incorrect after insert on multi-doubled array");
        
        array.remove(10);
        assertEquals(25, array.size(), "Size incorrect after remove on multi-doubled array");
        
        array.clear();
        assertEquals(0, array.size(), "Size incorrect after clear on multi-doubled array");
        assertTrue(array.toString().contains("backing array length: 40"), 
                "Backing array shrunk after clear on multi-doubled array");
    }
    
    //New
    @Test
    public void testToStringOnDoubledArray() {
        BetterDynamicArray array = new BetterDynamicArray();
        for(int i = 0; i < 15; i++) {
            array.add(makeEvent(i));
        }
        
        String result = array.toString();
        assertTrue(result.contains("backing array length: 20"), 
                "ToString doesn't show correct backing array length after doubling");
        assertTrue(result.startsWith("["), "ToString doesn't start correctly");
        assertTrue(result.endsWith("backing array length: 20"), "ToString doesn't end correctly");
    }
}