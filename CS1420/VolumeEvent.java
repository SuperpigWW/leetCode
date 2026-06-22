package assign08;

/**
 * Represents a volume event in a music sequence.
 * A VolumeEvent specifies when the volume should be changed on a particular track.
 * 
 * @author Haoquan Wang
 * @version 2025-10-30
 */
public class VolumeEvent extends AudioEvent {

	private int value;
	
	/**
	 * Constructs a VolumeEvent with the specified parameters.
	 * 
	 * @param time the starting time at which the volume change occurs (must be non-negative)
	 * @param trackNumber the track number for this volume change (must be between 0 and 9 inclusive)
	 * @param value the volume value to set (must be between 0 and 127 inclusive)
	 * @throws IllegalArgumentException if value is not between 0-127
	 */
	public VolumeEvent(int time, int trackNumber, int value) {
		
		super(time, trackNumber);
		
		if (value < 0 || value > 127)
			throw new IllegalArgumentException("Value out of range.");
		
		this.value = value;
	}
	
	/**
	 * Returns the volume value of this event.
	 * 
	 * @return the volume value (0-127)
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Executes this volume event by setting the volume on the specified track
	 * using the provided synthesizer.
	 * 
	 * @param synth the synthesizer used to set the volume
	 */
	public void execute(SimpleSynthesizer synth) {
		synth.setVolume(this.getTrackNumber(), value);
	}
	
	/**
	 * Returns a string representation of this volume event in the format:
	 * "Volume[time, trackNumber, value]"
	 * 
	 * @return a string representation of the volume event
	 */
	public String toString() {
		return ("Volume[" + this.getTime() + ", " + this.getTrackNumber() + ", " + value + "]");
	}
	
	/**
	 * Compares this VolumeEvent with another AudioEvent for ordering.
	 * VolumeEvents come after NoteEvents. When comparing two VolumeEvents,
	 * the one with smaller time comes first.
	 * Note: this class has a natural ordering that is inconsistent with equals.
	 * 
	 * @param other the other AudioEvent to compare to
	 * @return a negative integer if this event comes before the other,
	 *         a positive integer if this event comes after the other,
	 *         or zero if they are equal in ordering
	 */
	public int compareTo(AudioEvent other) {
		
		int result = 0;
		
		if (other instanceof NoteEvent)
			result = 1;
		
		if (other instanceof VolumeEvent) {
			if (this.getTime() < other.getTime())
				result = -1;
			else if (this.getTime() > other.getTime())
				result = 1;
			else
				result = 0;
		}
		
		return result;
	}
}
