package assign08;

/**
 * Abstract base class representing an audio event in a music sequence.
 * Audio events occur at specific times on specific tracks and can be
 * compared for ordering purposes.
 * 
 * @author Haoquan Wang
 * @version 2025-10-30
 */
public abstract class AudioEvent implements Comparable<AudioEvent> {

    private int time;
    private int trackNumber;
    
    /**
     * Constructs an AudioEvent with the specified time and track number.
     * 
     * @param time the starting time at which the event occurs (must be non-negative)
     * @param trackNumber the track number for this event (must be between 0 and 9 inclusive)
     * @throws IllegalArgumentException if time is negative or trackNumber is not between 0-9
     */
    public AudioEvent(int time, int trackNumber) {
    	
        if (time < 0 || trackNumber < 0 || trackNumber > 9)
            throw new IllegalArgumentException("Time or trackNumber out of range.");
        
        this.time = time;
        this.trackNumber = trackNumber;
    }
    
    /**
     * Returns the time at which this audio event occurs.
     * 
     * @return the starting time of this event
     */
    public int getTime() {
        return time;
    }
    
    /**
     * Returns the track number associated with this audio event.
     * 
     * @return the track number (0-9) for this event
     */
    public int getTrackNumber() {
        return trackNumber;
    }
    
    /**
     * Executes this audio event using the provided synthesizer.
     * Concrete subclasses must implement this method to define
     * the specific behavior when the event is triggered.
     * 
     * @param synth the synthesizer used to execute the audio event
     */
    public abstract void execute(SimpleSynthesizer synth);
}
