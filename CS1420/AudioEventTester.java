package assign08;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for AudioEvent classes
 * Tests normal and edge cases of all instance methods in NoteEvent and VolumeEvent
 * Includes comprehensive testing of compareTo and toString methods
 */
public class AudioEventTester {

    @Test
    public void testNoteEventConstructorNormal() {
        NoteEvent note = new NoteEvent(100, 5, 50, 60);
        assertEquals(100, note.getTime());
        assertEquals(5, note.getTrackNumber());
        assertEquals(50, note.getDuration());
        assertEquals(60, note.getPitch());
    }

    @Test
    public void testNoteEventConstructorBoundary() {
        NoteEvent note1 = new NoteEvent(0, 0, 0, 0);
        assertEquals(0, note1.getTime());
        assertEquals(0, note1.getTrackNumber());
        assertEquals(0, note1.getDuration());
        assertEquals(0, note1.getPitch());

        NoteEvent note2 = new NoteEvent(Integer.MAX_VALUE, 9, 127, 127);
        assertEquals(Integer.MAX_VALUE, note2.getTime());
        assertEquals(9, note2.getTrackNumber());
        assertEquals(127, note2.getDuration());
        assertEquals(127, note2.getPitch());
    }

    @Test
    public void testNoteEventConstructorInvalidPitch() {
        assertThrows(IllegalArgumentException.class, () -> {
            new NoteEvent(100, 5, 50, -1);
        });
    }

    @Test
    public void testNoteEventConstructorInvalidDuration() {
        assertThrows(IllegalArgumentException.class, () -> {
            new NoteEvent(100, 5, -1, 60);
        });
    }

    @Test
    public void testNoteEventConstructorDurationTooLarge() {
        assertThrows(IllegalArgumentException.class, () -> {
            new NoteEvent(100, 5, 128, 60);
        });
    }

    @Test
    public void testNoteEventGetters() {
        NoteEvent note = new NoteEvent(200, 3, 75, 80);
        assertEquals(200, note.getTime());
        assertEquals(3, note.getTrackNumber());
        assertEquals(75, note.getDuration());
        assertEquals(80, note.getPitch());
    }

    @Test
    public void testNoteEventToString() {
        NoteEvent note = new NoteEvent(150, 2, 45, 70);
        String expected = "Note[150, 2, 45, 70]";
        assertEquals(expected, note.toString());
    }

    @Test
    public void testNoteEventCompareToVolumeEvent() {
        NoteEvent note = new NoteEvent(100, 5, 50, 60);
        VolumeEvent volume = new VolumeEvent(100, 5, 80);
        assertTrue(note.compareTo(volume) < 0);
        assertTrue(volume.compareTo(note) > 0);
    }

    @Test
    public void testNoteEventCompareToOtherNoteEventDifferentPitch() {
        NoteEvent note1 = new NoteEvent(100, 5, 50, 60);
        NoteEvent note2 = new NoteEvent(100, 5, 50, 70);
        assertTrue(note1.compareTo(note2) > 0);
        assertTrue(note2.compareTo(note1) < 0);
    }

    @Test
    public void testNoteEventCompareToOtherNoteEventSamePitch() {
        NoteEvent note1 = new NoteEvent(100, 5, 50, 60);
        NoteEvent note2 = new NoteEvent(200, 3, 75, 60);
        assertEquals(0, note1.compareTo(note2));
        assertEquals(0, note2.compareTo(note1));
    }

    @Test
    public void testVolumeEventConstructorNormal() {
        VolumeEvent volume = new VolumeEvent(100, 5, 80);
        assertEquals(100, volume.getTime());
        assertEquals(5, volume.getTrackNumber());
        assertEquals(80, volume.getValue());
    }

    @Test
    public void testVolumeEventConstructorBoundary() {
        VolumeEvent volume1 = new VolumeEvent(0, 0, 0);
        assertEquals(0, volume1.getTime());
        assertEquals(0, volume1.getTrackNumber());
        assertEquals(0, volume1.getValue());

        VolumeEvent volume2 = new VolumeEvent(Integer.MAX_VALUE, 9, 127);
        assertEquals(Integer.MAX_VALUE, volume2.getTime());
        assertEquals(9, volume2.getTrackNumber());
        assertEquals(127, volume2.getValue());
    }

    @Test
    public void testVolumeEventConstructorInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new VolumeEvent(100, 5, -1);
        });
    }

    @Test
    public void testVolumeEventConstructorValueTooLarge() {
        assertThrows(IllegalArgumentException.class, () -> {
            new VolumeEvent(100, 5, 128);
        });
    }

    @Test
    public void testVolumeEventGetters() {
        VolumeEvent volume = new VolumeEvent(200, 3, 90);
        assertEquals(200, volume.getTime());
        assertEquals(3, volume.getTrackNumber());
        assertEquals(90, volume.getValue());
    }

    @Test
    public void testVolumeEventToString() {
        VolumeEvent volume = new VolumeEvent(150, 2, 75);
        String expected = "Volume[150, 2, 75]";
        assertEquals(expected, volume.toString());
    }

    @Test
    public void testVolumeEventCompareToOtherVolumeEventDifferentTime() {
        VolumeEvent volume1 = new VolumeEvent(100, 5, 80);  
        VolumeEvent volume2 = new VolumeEvent(200, 5, 80);  
      
        assertTrue(volume1.compareTo(volume2) < 0);
        assertTrue(volume2.compareTo(volume1) > 0);
    }

    @Test
    public void testVolumeEventCompareToOtherVolumeEventSameTime() {
        VolumeEvent volume1 = new VolumeEvent(100, 5, 80);
        VolumeEvent volume2 = new VolumeEvent(100, 3, 90);
        assertEquals(0, volume1.compareTo(volume2));
        assertEquals(0, volume2.compareTo(volume1));
    }

    @Test
    public void testCrossTypeCompareToSymmetry() {
        NoteEvent note = new NoteEvent(100, 5, 50, 60);
        VolumeEvent volume = new VolumeEvent(100, 5, 80);
        assertTrue(note.compareTo(volume) < 0);
        assertTrue(volume.compareTo(note) > 0);
    }

    @Test
    public void testMultipleEventsCompareToConsistency() {
        NoteEvent note1 = new NoteEvent(100, 5, 50, 60);
        NoteEvent note2 = new NoteEvent(100, 5, 50, 70);
        VolumeEvent volume = new VolumeEvent(100, 5, 80);
        assertTrue(note1.compareTo(note2) > 0);
        assertTrue(note2.compareTo(volume) < 0);
        assertTrue(note1.compareTo(volume) < 0);
    }

    @Test
    public void testAudioEventConstructorInvalidTime() {
        assertThrows(IllegalArgumentException.class, () -> {
            new NoteEvent(-1, 5, 50, 60);
        });
    }

    @Test
    public void testAudioEventConstructorInvalidTrackNumberLow() {
        assertThrows(IllegalArgumentException.class, () -> {
            new NoteEvent(100, -1, 50, 60);
        });
    }

    @Test
    public void testAudioEventConstructorInvalidTrackNumberHigh() {
        assertThrows(IllegalArgumentException.class, () -> {
            new NoteEvent(100, 10, 50, 60);
        });
    }

    @Test
    public void testTrackNumberBoundaries() {
        NoteEvent note1 = new NoteEvent(100, 0, 50, 60);
        assertEquals(0, note1.getTrackNumber());
        NoteEvent note2 = new NoteEvent(100, 9, 50, 60);
        assertEquals(9, note2.getTrackNumber());
    }

    @Test
    public void testVolumeValueBoundaries() {
        VolumeEvent volume1 = new VolumeEvent(100, 5, 0);
        assertEquals(0, volume1.getValue());
        VolumeEvent volume2 = new VolumeEvent(100, 5, 127);
        assertEquals(127, volume2.getValue());
    }

    @Test
    public void testPitchBoundaries() {
        NoteEvent note1 = new NoteEvent(100, 5, 50, 0);
        assertEquals(0, note1.getPitch());
        NoteEvent note2 = new NoteEvent(100, 5, 50, 127);
        assertEquals(127, note2.getPitch());
    }

    @Test
    public void testDurationBoundaries() {
        NoteEvent note1 = new NoteEvent(100, 5, 0, 60);
        assertEquals(0, note1.getDuration());
        NoteEvent note2 = new NoteEvent(100, 5, 127, 60);
        assertEquals(127, note2.getDuration());
    }
}