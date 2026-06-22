package assign09;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains tests for the Song class. 
 * 
 * @author Haoquan Wang
 * @version 2025-11-06
 */
public class SongTester {
    
    private Song song;
    private static final int TEST_TEMPO = 120;
    private static final int TEST_LENGTH = 1000;
    
    @BeforeEach
    public void setup() {
        song = new Song(TEST_TEMPO, TEST_LENGTH);
    }
    
    @Test
    public void testConstructor() {
        assertEquals(TEST_TEMPO, song.getTempo());
        assertEquals(TEST_LENGTH, song.getSongLength());
        assertNotNull(song.getSynthesizer());
        assertNotNull(song.getTimer());
        
        for (int i = 0; i < 10; i++) {
            ArrayList<AudioEvent> track = song.getTrack(i);
            assertNotNull(track);
            assertTrue(track.isEmpty());
        }
    }
    
    @Test
    public void testAddNoteEvent() {
        song.addNoteEvent(100, 0, 50, 60);
        ArrayList<AudioEvent> track = song.getTrack(0);
        
        assertEquals(1, track.size());
        assertTrue(track.get(0) instanceof NoteEvent);
        
        NoteEvent note = (NoteEvent) track.get(0);
        assertEquals(100, note.getTime());
        assertEquals(0, note.getTrackNumber());
        assertEquals(50, note.getDuration());
        assertEquals(60, note.getPitch());
    }
    
    @Test
    public void testAddNoteEventMultiple() {
        song.addNoteEvent(100, 1, 40, 70);
        song.addNoteEvent(100, 1, 30, 65);
        song.addNoteEvent(100, 1, 35, 75);
        
        ArrayList<AudioEvent> track = song.getTrack(1);
        assertEquals(3, track.size());
        
        assertEquals(75, ((NoteEvent) track.get(0)).getPitch());
        assertEquals(70, ((NoteEvent) track.get(1)).getPitch());
        assertEquals(65, ((NoteEvent) track.get(2)).getPitch());
    }
    
    @Test
    public void testAddNoteEventDifferentTracks() {
        song.addNoteEvent(100, 0, 50, 60);
        song.addNoteEvent(200, 5, 40, 65);
        song.addNoteEvent(150, 9, 30, 70);
        
        assertEquals(1, song.getTrack(0).size());
        assertEquals(1, song.getTrack(5).size());
        assertEquals(1, song.getTrack(9).size());
        
        for (int i = 1; i < 5; i++) {
            assertTrue(song.getTrack(i).isEmpty());
        }
        for (int i = 6; i < 9; i++) {
            assertTrue(song.getTrack(i).isEmpty());
        }
    }
    
    @Test
    public void testAddVolumeEvent() {
        song.addVolumeEvent(100, 2, 80);
        ArrayList<AudioEvent> track = song.getTrack(2);
        
        assertEquals(1, track.size());
        assertTrue(track.get(0) instanceof VolumeEvent);
        
        VolumeEvent volume = (VolumeEvent) track.get(0);
        assertEquals(100, volume.getTime());
        assertEquals(2, volume.getTrackNumber());
        assertEquals(80, volume.getValue());
    }
    
    @Test
    public void testAddVolumeEventMultiple() {
        song.addVolumeEvent(200, 3, 90);
        song.addVolumeEvent(100, 3, 70);
        song.addVolumeEvent(150, 3, 80);
        
        ArrayList<AudioEvent> track = song.getTrack(3);
        assertEquals(3, track.size());
        
        assertEquals(100, track.get(0).getTime());
        assertEquals(150, track.get(1).getTime());
        assertEquals(200, track.get(2).getTime());
    }
    
    @Test
    public void testMixedEventTypes() {
        song.addNoteEvent(100, 4, 50, 60);
        song.addVolumeEvent(150, 4, 80);
        song.addNoteEvent(120, 4, 40, 65);
        
        ArrayList<AudioEvent> track = song.getTrack(4);
        assertEquals(3, track.size());
        
        assertTrue(track.get(0) instanceof NoteEvent);
        assertEquals(65, ((NoteEvent) track.get(0)).getPitch());
        
        assertTrue(track.get(1) instanceof NoteEvent);
        assertEquals(60, ((NoteEvent) track.get(1)).getPitch());
        
        assertTrue(track.get(2) instanceof VolumeEvent);
        assertEquals(150, track.get(2).getTime());
    }
    
    @Test
    public void testNoteEventSortingByPitch() {
        song.addNoteEvent(100, 5, 50, 60);
        song.addNoteEvent(100, 5, 40, 70);
        song.addNoteEvent(100, 5, 30, 65);
        
        ArrayList<AudioEvent> track = song.getTrack(5);
        assertEquals(3, track.size());
        
        assertEquals(70, ((NoteEvent) track.get(0)).getPitch());
        assertEquals(65, ((NoteEvent) track.get(1)).getPitch());
        assertEquals(60, ((NoteEvent) track.get(2)).getPitch());
    }

    @Test
    public void testGetTrackInvalidLow() {
        assertThrows(IllegalArgumentException.class, () -> {
            song.getTrack(-1);
        });
    }

    @Test
    public void testGetTrackInvalidHigh() {
        assertThrows(IllegalArgumentException.class, () -> {
            song.getTrack(10);
        });
    }
    
    @Test
    public void testNoteEventsBeforeVolumeEvents() {
        song.addVolumeEvent(100, 7, 80);
        song.addNoteEvent(200, 7, 50, 60);
        song.addVolumeEvent(150, 7, 90);
        song.addNoteEvent(50, 7, 40, 70);
        
        ArrayList<AudioEvent> track = song.getTrack(7);
        assertEquals(4, track.size());
        
        assertTrue(track.get(0) instanceof NoteEvent);
        assertTrue(track.get(1) instanceof NoteEvent);
        assertTrue(track.get(2) instanceof VolumeEvent);
        assertTrue(track.get(3) instanceof VolumeEvent);
        
        assertEquals(70, ((NoteEvent) track.get(0)).getPitch());
        assertEquals(60, ((NoteEvent) track.get(1)).getPitch());
        assertEquals(100, track.get(2).getTime());
        assertEquals(150, track.get(3).getTime());
    }
    
    @Test
    public void testClearTrack() {
        song.addNoteEvent(100, 0, 50, 60);
        song.addVolumeEvent(200, 0, 80);
        assertEquals(2, song.getTrack(0).size());
        
        song.clearTrack(0);
        assertTrue(song.getTrack(0).isEmpty());
        
        song.addNoteEvent(150, 1, 40, 65);
        assertEquals(1, song.getTrack(1).size());
    }
    
    @Test
    public void testClearTrackInvalidLow() {
        assertThrows(IllegalArgumentException.class, () -> {
            song.clearTrack(-1);
        });
    }
    
    @Test
    public void testClearTrackInvalidHigh() {
        assertThrows(IllegalArgumentException.class, () -> {
            song.clearTrack(10);
        });
    }
    
    @Test
    public void testClearAll() {
        song.addNoteEvent(100, 0, 50, 60);
        song.addVolumeEvent(200, 3, 80);
        song.addNoteEvent(150, 7, 40, 65);
        
        assertFalse(song.getTrack(0).isEmpty());
        assertFalse(song.getTrack(3).isEmpty());
        assertFalse(song.getTrack(7).isEmpty());
        
        song.clearAll();
        
        for (int i = 0; i < 10; i++) {
            assertTrue(song.getTrack(i).isEmpty());
        }
    }
    
    @Test
    public void testClearAllEmptySong() {
        song.clearAll();
        
        for (int i = 0; i < 10; i++) {
            assertTrue(song.getTrack(i).isEmpty());
        }
    }
    
    @Test
    public void testGetTrack() {
        ArrayList<AudioEvent> track = song.getTrack(5);
        assertNotNull(track);
        assertTrue(track.isEmpty());
        
        song.addNoteEvent(100, 5, 50, 60);
        assertEquals(1, track.size());
    }
    
    
    @Test
    public void testGetTrackBoundaryValues() {
        assertNotNull(song.getTrack(0));
        assertNotNull(song.getTrack(9));
        
        song.addNoteEvent(100, 0, 50, 60);
        song.addVolumeEvent(200, 9, 80);
        
        assertEquals(1, song.getTrack(0).size());
        assertEquals(1, song.getTrack(9).size());
    }
    
    @Test
    public void testSettersAndGetters() {
        song.setTempo(140);
        assertEquals(140, song.getTempo());
        
        song.setSongLength(2000);
        assertEquals(2000, song.getSongLength());
    }
    
    @Test
    public void testEventSortingWithComplexScenario() {
        song.addNoteEvent(300, 2, 50, 72);
        song.addVolumeEvent(100, 2, 90);
        song.addNoteEvent(200, 2, 40, 68);
        song.addVolumeEvent(250, 2, 80);
        song.addNoteEvent(150, 2, 30, 65);
        
        ArrayList<AudioEvent> track = song.getTrack(2);
        assertEquals(5, track.size());
        
        assertTrue(track.get(0) instanceof NoteEvent);
        assertEquals(72, ((NoteEvent) track.get(0)).getPitch());
        
        assertTrue(track.get(1) instanceof NoteEvent);
        assertEquals(68, ((NoteEvent) track.get(1)).getPitch());
        
        assertTrue(track.get(2) instanceof NoteEvent);
        assertEquals(65, ((NoteEvent) track.get(2)).getPitch());
        
        assertTrue(track.get(3) instanceof VolumeEvent);
        assertEquals(100, track.get(3).getTime());
        
        assertTrue(track.get(4) instanceof VolumeEvent);
        assertEquals(250, track.get(4).getTime());
    }
}