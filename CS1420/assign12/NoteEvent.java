package assign12;

/**
 * Represents a note event in a music sequence.
 * A NoteEvent specifies when a note should start playing, its duration,
 * pitch, and which track/instrument should play it.
 * 
 * @author Haoquan Wang
 * @version 2025-10-30
 */
public class NoteEvent extends AudioEvent {
	
	private int pitch;
	private int duration;

	/**
	 * Constructs a NoteEvent with the specified parameters.
	 * 
	 * @param time the starting time at which the note begins (must be non-negative)
	 * @param trackNumber the track number for this note (must be between 0 and 9 inclusive)
	 * @param duration the duration of the note (must be between 0 and 127 inclusive)
	 * @param pitch the pitch of the note (must be non-negative)
	 * @throws IllegalArgumentException if pitch is negative or duration is not between 0-127
	 */
	public NoteEvent(int time, int trackNumber, int duration, int pitch) {
		
		super(time, trackNumber);
		
		if (pitch < 0 || pitch > 127 || duration < 0)
			throw new IllegalArgumentException("Pitch or duration out of range.");
		
		this.pitch = pitch;
		this.duration = duration;
	}
	
	/**
	 * Returns the duration of this note event.
	 * 
	 * @return the duration of the note
	 */
	public int getDuration() {
		return duration;
	}
	
	/**
	 * Returns the pitch of this note event.
	 * 
	 * @return the pitch of the note
	 */
	public int getPitch() {
		return pitch;
	}
	
	/**
	 * Signals the completion of this note event by turning off the note
	 * using the provided synthesizer.
	 * 
	 * @param synth the synthesizer used to stop the note
	 */
	public void complete(SimpleSynthesizer synth) {
		synth.noteOff(this.getTrackNumber(), this.pitch);
	}
	
	/**
	 * Executes this note event by starting to play the note
	 * using the provided synthesizer.
	 * 
	 * @param synth the synthesizer used to play the note
	 */
	public void execute(SimpleSynthesizer synth) {
		synth.noteOn(this.getTrackNumber(), pitch);
	}
	
	/**
	 * Returns a string representation of this note event in the format:
	 * "Note[time, trackNumber, duration, pitch]"
	 * 
	 * @return a string representation of the note event
	 */
	public String toString() {
		return ("Note[" + this.getTime() + ", " + this.getTrackNumber() + ", " + duration + ", " + pitch + "]");
	}
	
	/**
	 * Compares this NoteEvent with another AudioEvent for ordering.
	 * NoteEvents come before VolumeEvents. When comparing two NoteEvents,
	 * the one with higher pitch comes first.
	 * Note: this class has a natural ordering that is inconsistent with equals.
	 * 
	 * @param other the other AudioEvent to compare to
	 * @return a negative integer if this event comes before the other,
	 *         a positive integer if this event comes after the other,
	 *         or zero if they are equal in ordering
	 */
	public int compareTo(AudioEvent other) {
		
		int result = 0;
		
		if (other instanceof VolumeEvent)
			result = -1;
		
		if (other instanceof NoteEvent) {
			NoteEvent otherNoteEvent = (NoteEvent) other;
			if (this.pitch > otherNoteEvent.pitch)
				result = -1;
			else if (this.pitch < otherNoteEvent.pitch)
				result = 1;
			else
				result = 0;
		}
		
		return result;
	}
}