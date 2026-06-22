package assign12;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class provides static methods for reading and writing song files.
 * It also provides a method for filtering duplicate audio events.
 * 
 * @author Haoquan Wang
 * @version 2025-12-02
 */
public class SongFiles {
    
    /**
     * A comparator that orders AudioEvents in chronological order (by time).
     * Events with the same time are ordered using their natural ordering.
     */
    public static class ChronologicalOrder implements Comparator<AudioEvent> {
        /**
         * Compares two AudioEvents based on their execution time.
         * Events with earlier times come first. Events with the same time
         * are compared using their natural ordering.
         * 
         * @param e1 the first AudioEvent to compare
         * @param e2 the second AudioEvent to compare
         * @return a negative integer if e1 comes before e2,
         *         zero if they are equal, or a positive integer if e1 comes after e2
         */
        @Override
        public int compare(AudioEvent e1, AudioEvent e2) {
            int timeComparison = Integer.compare(e1.getTime(), e2.getTime());
            if (timeComparison != 0) {
                return timeComparison;
            }
            // For events with the same time, use natural ordering
            return e1.compareTo(e2);
        }
    }
    
    /**
     * Writes a Song to a file in the specified format.
     * 
     * @param file the file to write to
     * @param song the song to write
     * @throws RuntimeException if there's an error writing to the file
     */
    public static void writeFile(File file, Song song) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            StringBuilder content = new StringBuilder();
            
            // Write tempo and song length
            content.append(song.getTempo()).append("\n");
            content.append(song.getSongLength()).append("\n");
            
            // Write each track
            for (int trackNum = 0; trackNum < 10; trackNum++) {
                ArrayList<AudioEvent> trackEvents = song.getTrack(trackNum);
                
                // Filter duplicates and sort chronologically
                ArrayList<AudioEvent> filteredEvents = filterEvents(trackEvents);
                Collections.sort(filteredEvents, new ChronologicalOrder());
                
                // Write track header
                content.append("track").append(trackNum).append("\n");
                content.append(trackNum).append("\n");
                content.append(song.getSynthesizer().getInstrument(trackNum)).append("\n");
                content.append(filteredEvents.size()).append("\n");
                
                // Write each event
                for (AudioEvent event : filteredEvents) {
                    content.append(eventToString(event));
                }
            }
            
            writer.write(content.toString());
            
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + e.getMessage(), e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    // Ignore close error
                }
            }
        }
    }
    
    /**
     * Reads a song from a file and updates the provided Song object.
     * 
     * @param file the file to read from
     * @param song the song to update with file data
     * @throws RuntimeException if there's an error reading the file or if the format is invalid
     */
    public static void readFile(File file, Song song) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            
            // Clear existing song data
            song.clearAll();
            
            // Read tempo and song length
            int tempo = scanner.nextInt();
            int songLength = scanner.nextInt();
            
            song.setTempo(tempo);
            song.setSongLength(songLength);
            
            // Read each track
            for (int expectedTrackNum = 0; expectedTrackNum < 10; expectedTrackNum++) {
                // Read track header
                String trackHeader = scanner.next();
                if (!trackHeader.equals("track" + expectedTrackNum)) {
                    throw new InputMismatchException("Invalid track header: expected 'track" + 
                                                    expectedTrackNum + "', found '" + trackHeader + "'");
                }
                
                int trackNum = scanner.nextInt();
                if (trackNum != expectedTrackNum) {
                    throw new InputMismatchException("Track number mismatch: expected " + 
                                                    expectedTrackNum + ", found " + trackNum);
                }
                
                int instrument = scanner.nextInt();
                int numEvents = scanner.nextInt();
                
                // Set instrument
                song.getSynthesizer().setInstrument(trackNum, instrument);
                
                // Read events
                for (int i = 0; i < numEvents; i++) {
                    String eventType = scanner.next();
                    int time = scanner.nextInt();
                    int secondValue = scanner.nextInt(); // duration for notes, value for volumes
                    int thirdValue = scanner.nextInt();  // pitch for notes, 0 for volumes
                    
                    if (eventType.equals("note")) {
                        song.addNoteEvent(time, trackNum, secondValue, thirdValue);
                    } else if (eventType.equals("volume")) {
                        song.addVolumeEvent(time, trackNum, secondValue);
                    } else {
                        throw new InputMismatchException("Unknown event type: " + eventType);
                    }
                }
            }
            
        } catch (IOException | InputMismatchException | IllegalStateException e) {
            throw new RuntimeException("Error reading file: " + e.getMessage(), e);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
    
    /**
     * Filters duplicate AudioEvents from a list.
     * A duplicate NoteEvent has the same time and pitch.
     * A duplicate VolumeEvent has the same time (only one volume per time).
     * 
     * @param events the list of events to filter
     * @return a new list containing no duplicate events
     */
    public static ArrayList<AudioEvent> filterEvents(ArrayList<AudioEvent> events) {
        ArrayList<AudioEvent> result = new ArrayList<>();
        HashMap<Integer, ArrayList<AudioEvent>> eventMap = new HashMap<>();
        
        for (AudioEvent event : events) {
            int time = event.getTime();
            
            // Get or create the list for this time
            ArrayList<AudioEvent> eventsAtTime = eventMap.get(time);
            if (eventsAtTime == null) {
                eventsAtTime = new ArrayList<>();
                eventMap.put(time, eventsAtTime);
            }
            
            boolean isDuplicate = false;
            
            // Check for duplicates
            for (AudioEvent existingEvent : eventsAtTime) {
                if (event instanceof NoteEvent && existingEvent instanceof NoteEvent) {
                    NoteEvent note1 = (NoteEvent) event;
                    NoteEvent note2 = (NoteEvent) existingEvent;
                    if (note1.getPitch() == note2.getPitch()) {
                        isDuplicate = true;
                        break;
                    }
                } else if (event instanceof VolumeEvent && existingEvent instanceof VolumeEvent) {
                    // For volume events, only one per time
                    isDuplicate = true;
                    break;
                }
            }
            
            // If not a duplicate, add to both lists
            if (!isDuplicate) {
                eventsAtTime.add(event);
                result.add(event);
            }
        }
        
        return result;
    }
    
    /**
     * Helper method to create a string representation of an AudioEvent for writing to file.
     * 
     * @param event the AudioEvent to convert
     * @return the string representation
     */
    private static String eventToString(AudioEvent event) {
        if (event instanceof NoteEvent) {
            NoteEvent note = (NoteEvent) event;
            return String.format("note\n%d\n%d\n%d\n", 
                note.getTime(), note.getDuration(), note.getPitch());
        } else if (event instanceof VolumeEvent) {
            VolumeEvent volume = (VolumeEvent) event;
            return String.format("volume\n%d\n%d\n0\n", 
                volume.getTime(), volume.getValue());
        }
        return "";
    }
}