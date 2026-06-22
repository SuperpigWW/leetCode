package assign11;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;

import assign11.SimpleSynthesizer;
import javax.swing.JPanel;
/**
 * A TrackEditor is the interactive GUI component for drawing a sequence of
 * note events or volume changes in a track.
 * 
 * @author CS 1420 course staff and Haoquan Wang
 * @version 2025-11-20
 */
public class TrackEditor extends JPanel implements MouseListener, MouseMotionListener{
	
	public static enum Mode{NOTE, VOLUME, COPY};
	private Mode mode;
	
	private int trackNumber;
	private SimpleSynthesizer synth;
	private ArrayList<AudioEvent> events;     // The AudioEvents for this track
	private ArrayList<NoteEvent> notesToCopy; // Stores notes during a copy operation
	
	private int columns, rows; // The number of columns and rows in the grid
	private boolean drawing;   // Set to true during drawing operations
	private int currentRow, currentColumn; // Used by drawing operations
	private int noteDuration;  // The duration of a note being drawn
	
	// For defining an area of the grid to copy
	private int copyFromRow, copyFromColumn; // Top and left of area
	private int copyToRow, copyToColumn;     // Bottom and right of area
	
	// This pitch range matches a piano. You can change these values if desired.
	private static final int lowestPitch = 21;
	private static final int highestPitch = 108;
	
	/**
	 * Create a new TrackEditor with the default configuration.
	 * 
	 * @param trackNumber assigned to this track in the midi system
	 * @param songLength the length of the song in ticks
	 * @param events the list of audio events for this track
	 * @param synth the synthesizer for making sounds
	 */
	public TrackEditor(int trackNumber, int songLength, ArrayList<AudioEvent> events, SimpleSynthesizer synth) {
		columns = songLength;
		rows = highestPitch - lowestPitch + 1;
		
		this.trackNumber = trackNumber;
		this.synth = synth;
		this.events = events;
		notesToCopy = new ArrayList<NoteEvent>();
		drawing = false;
		mode = Mode.NOTE;
		
		setBackground(Color.WHITE);
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	/**
	 * Removes all events from the track.
	 */
	public void clearTrack() {
		events.clear();
		repaint();
	}
	
	/**
	 * Set the song duration in ticks.
	 * 
	 * @param songLength in ticks
	 */
	public void setSongLength(int songLength) {
		columns = songLength;
		if (columns < 1)
			columns = 1;
		repaint();
	}
	
	/**
	 * Set the editor to the specified mode.
	 * 
	 * @param mode - either Mode.NOTE, Mode.VOLUME, or Mode.COPY
	 */
	public void setMode(Mode mode) {
		this.mode = mode;
		// Set volume back to default in case it was changed in volume mode
		synth.setVolume(trackNumber, 100);
	}
	
	/**
	 * This method is called by the system when a component needs to be painted.
	 * Which can be at one of three times: --when the component first appears --when
	 * the size of the component changes (including resizing by the user) --when
	 * repaint() is called
	 * 
	 * Partially overrides the paintComponent method of JPanel.
	 * 
	 * @param g -- graphics context onto which we can draw
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		/**
		 * Draw volume bars showing the volume levels throughout the track.
		 * Volume bars are drawn as light gray rectangles that extend from the
		 * volume level to the bottom of the panel.
		 */
		g.setColor(new Color(240, 240, 240));
		int previousVolume = 100; // the volume begins with value 100 by default
		int previousTime = 0; // the beginning of the song
		for(AudioEvent event : events) {
			if(event instanceof VolumeEvent) {
				VolumeEvent volumeEvent = (VolumeEvent) event;
				int leftEdge = colToPixel(previousTime);
	            int topEdge = rowToPixel(volumeToRow(previousVolume));
	            int rightEdge = colToPixel(volumeEvent.getTime());
	            int bottom = getHeight();
	            g.fillRect(leftEdge, topEdge, rightEdge - leftEdge, bottom - topEdge);
				previousVolume = volumeEvent.getValue();
                previousTime = volumeEvent.getTime();

			}
		}
		
		/**
		 * Draw the final volume bar from the last volume event to the end of the track.
		 */
		int leftEdge = colToPixel(previousTime);
        int topEdge = rowToPixel(volumeToRow(previousVolume));
        int rightEdge = getWidth();
        int bottom = getHeight();
        g.fillRect(leftEdge, topEdge, rightEdge - leftEdge, bottom - topEdge);
        
        /**
         * Draw the grid lines for the track editor.
         * Thin black lines for regular rows and columns, with thicker lines
         * every 12 rows (octaves) and every 4 columns for better visibility.
         */
        g.setColor(Color.BLACK);
        
        /**
         * Draw horizontal grid lines with thicker lines every octave (12 rows).
         */
        for (int i = 0; i <= rows; i++) {
            int y = rowToPixel(i);
            if (i % 12 == 0) 
            	g.fillRect(0, y, getWidth(), 2);
            else
            	g.drawLine(0, y, getWidth(), y);
        }
        
        /**
         * Draw vertical grid lines with thicker lines every 4 columns.
         */
        for (int i = 0; i <= columns; i++) {
            int x = colToPixel(i);
            if (i % 4 == 0)
            	g.fillRect(x, 0, 2, getHeight());
            else
            	g.drawLine(x, 0, x, getHeight());
        }
       
        /**
         * Draw preview rectangles for current drawing operations.
         * Different colors are used for different modes to provide visual feedback.
         */
		if(drawing) {
			if(mode == Mode.VOLUME) {
				/**
				 * Draw volume preview as a semi-transparent blue rectangle
				 * extending from the current row to the bottom of the panel.
				 */
				g.setColor(new Color(200, 200, 250, 150));
                int x = colToPixel(currentColumn);
                int y = rowToPixel(currentRow);
                int width = colToPixel(currentColumn + 1) - x;
                int height = getHeight() - y;
                g.fillRect(x, y, width, height);

			}
			else if(mode == Mode.NOTE && noteDuration > 0){
				/**
				 * Draw note preview as a semi-transparent red rectangle
				 * showing the pitch (row) and duration (width) of the note being drawn.
				 */
				g.setColor(new Color(250, 150, 150, 150));
                int x = colToPixel(currentColumn);
                int y = rowToPixel(currentRow);
                int width = colToPixel(currentColumn + noteDuration) - x;
                int height = rowToPixel(currentRow + 1) - y;
                g.fillRect(x, y, width, height);

			}
			else if(mode == Mode.COPY) {
				/**
				 * Draw copy selection preview as a semi-transparent green rectangle
				 * showing the area currently selected for copying.
				 */
				g.setColor(new Color(100, 255, 100, 100));
                int startCol = Math.min(copyFromColumn, copyToColumn);
                int endCol = Math.max(copyFromColumn, copyToColumn);
                int startRow = Math.min(copyFromRow, copyToRow);
                int endRow = Math.max(copyFromRow, copyToRow);
                
                int x = colToPixel(startCol);
                int y = rowToPixel(startRow);
                int width = colToPixel(endCol + 1) - x;
                int height = rowToPixel(endRow + 1) - y;
                g.fillRect(x, y, width, height);

			}
		}
		
		/**
		 * Draw all existing note events as red rectangles on the grid.
		 * Each note's position represents its pitch and start time,
		 * and its width represents its duration.
		 */
		g.setColor(Color.RED);
		for(AudioEvent event : events) {
			if(event instanceof NoteEvent) {
				NoteEvent note = (NoteEvent)event;
                int row = pitchToRow(note.getPitch());
                int x = colToPixel(note.getTime());
                int y = rowToPixel(row);
                int width = colToPixel(note.getTime() + note.getDuration()) - x;
                int height = rowToPixel(row + 1) - y;
                g.fillRect(x, y, width, height);
			}
		}
		
		/**
		 * Draw an orange line indicating middle C (pitch 60) for reference.
		 */
		g.setColor(Color.ORANGE);
        int middleCRow = pitchToRow(60);
        int y = rowToPixel(middleCRow);
        g.drawLine(0, y, getWidth(), y);
		
	} // end of paintComponent
	
	/**
	 * Handles mouse press events for starting drawing operations.
	 * Left mouse button starts drawing in the current mode.
	 * 
	 * @param e the mouse event containing press information
	 */
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            drawing = true;
            currentColumn = pixelToCol(e.getX());
            currentRow = pixelToRow(e.getY());
            
            if (mode == Mode.NOTE) {
                int pitch = rowToPitch(currentRow);
                synth.noteOn(trackNumber, pitch);
                noteDuration = 1;
            } else if (mode == Mode.VOLUME) {
                int volume = rowToVolume(currentRow);
                synth.setVolume(trackNumber, volume);
                synth.noteOn(trackNumber, 60);
            } else if (mode == Mode.COPY) {
                copyFromRow = currentRow;
                copyFromColumn = currentColumn;
                copyToRow = currentRow;
                copyToColumn = currentColumn;
            }
            
            repaint();
        }
    }
    
    /**
     * Handles mouse release events for completing drawing operations.
     * Creates new audio events based on the current drawing operation.
     * 
     * @param e the mouse event containing release information
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (drawing) {
            if (mode == Mode.NOTE) {
                int pitch = rowToPitch(currentRow);
                synth.noteOff(trackNumber, pitch);
                
                if (noteDuration > 0) {
                    NoteEvent newNote = new NoteEvent(currentColumn, trackNumber, noteDuration, pitch);
                    events.add(newNote);
                    Collections.sort(events);
                }
            } else if (mode == Mode.VOLUME) {
                synth.noteOff(trackNumber, 60);
                
                int volume = rowToVolume(currentRow);
                VolumeEvent newVolume = new VolumeEvent(currentColumn, trackNumber, volume);
                events.add(newVolume);
                Collections.sort(events);
            } else if (mode == Mode.COPY) {
                notesToCopy.clear();
                int minCol = Math.min(copyFromColumn, copyToColumn);
                int maxCol = Math.max(copyFromColumn, copyToColumn);
                int minRow = Math.min(copyFromRow, copyToRow);
                int maxRow = Math.max(copyFromRow, copyToRow);
                
                for (AudioEvent event : events) {
                    if (event instanceof NoteEvent) {
                        NoteEvent note = (NoteEvent) event;
                        int row = pitchToRow(note.getPitch());
                        if (row >= minRow && row <= maxRow && 
                            note.getTime() >= minCol && note.getTime() <= maxCol) {
                            notesToCopy.add(note);
                        }
                    }
                }
            }
            
            drawing = false;
            repaint();
        }
    }
    
    /**
     * Handles mouse drag events for updating drawing operations in real-time.
     * Updates note duration, pitch, volume, or copy selection area during dragging.
     * 
     * @param e the mouse event containing drag information
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (drawing) {
            int tempRow = pixelToRow(e.getY());
            int tempCol = pixelToCol(e.getX());
            
            if (mode == Mode.NOTE) {
                noteDuration = Math.max(1, tempCol - currentColumn + 1);
                
                if (tempRow != currentRow) {
                    int oldPitch = rowToPitch(currentRow);
                    int newPitch = rowToPitch(tempRow);
                    synth.noteOff(trackNumber, oldPitch);
                    synth.noteOn(trackNumber, newPitch);
                    currentRow = tempRow;
                }
            } else if (mode == Mode.VOLUME) {
                if (tempRow != currentRow) {
                    currentRow = tempRow;
                    int volume = rowToVolume(currentRow);
                    synth.setVolume(trackNumber, volume);
                    synth.noteOn(trackNumber, 60);
                }
            } else if (mode == Mode.COPY) {
                copyToRow = tempRow;
                copyToColumn = tempCol;
            }
            
            repaint();
        }
    }
    
    /**
     * Handles mouse click events for secondary operations.
     * Right-click deletes notes in NOTE mode or pastes copied notes in COPY mode.
     * 
     * @param e the mouse event containing click information
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() != MouseEvent.BUTTON1) {
            int col = pixelToCol(e.getX());
            int row = pixelToRow(e.getY());
            
            if (mode == Mode.NOTE) {
                int pitch = rowToPitch(row);
                for (int i = 0; i < events.size(); i++) {
                    AudioEvent event = events.get(i);
                    if (event instanceof NoteEvent) {
                        NoteEvent note = (NoteEvent) event;
                        if (note.getTime() == col && note.getPitch() == pitch) {
                            events.remove(i);
                            break;
                        }
                    }
                }
                repaint();
            } else if (mode == Mode.COPY && !notesToCopy.isEmpty()) {
                int timeOffset = col - copyFromColumn;
                int pitchOffset = rowToPitch(row) - rowToPitch(copyFromRow);
                
                for (NoteEvent originalNote : notesToCopy) {
                    int newTime = originalNote.getTime() + timeOffset;
                    int newPitch = originalNote.getPitch() + pitchOffset;
                    
                    if (newTime >= 0 && newTime < columns && 
                        newPitch >= 0 && newPitch <= 127) {
                    	NoteEvent newNote = new NoteEvent(newTime, trackNumber, originalNote.getDuration(), newPitch);
                        events.add(newNote);
                    }
                }
                Collections.sort(events);
                repaint();
            }
        }
    }
    
    // Other required mouse listener methods
    /**
     * Handles mouse enter events (no operation required).
     * 
     * @param e the mouse event
     */
    @Override
    public void mouseEntered(MouseEvent e) {}
    
    /**
     * Handles mouse exit events (no operation required).
     * 
     * @param e the mouse event
     */
    @Override
    public void mouseExited(MouseEvent e) {}
    
    /**
     * Handles mouse move events (no operation required).
     * 
     * @param e the mouse event
     */
    @Override
    public void mouseMoved(MouseEvent e) {}
	
	//////////////////////////////////////////////////////////////////////
	// Private helper methods
	//////////////////////////////////////////////////////////////////////
	
	/**
	 * Convert a row index in the grid to a pitch number.
	 * 
	 * @param rowNumber - to convert
	 * @return pitch corresponding to that row
	 */
	private int rowToPitch(int rowNumber) {
		return highestPitch - rowNumber;
	}
	
	/**
	 * Convert a pitch number to a row index in the grid.
	 * 
	 * @param pitch - to convert
	 * @return row index corresponding to that pitch
	 */
	private int pitchToRow(int pitch) {
		return highestPitch - pitch;
	}
	
	/**
	 *  Convert a row index in the grid to a volume value.
	 *  
	 * @param rowNumber - to convert
	 * @return volume value corresponding to that row
	 */
	private int rowToVolume(int rowNumber) {
		return 127 - rowNumber * 127 / rows;
	}
	
	/**
	 * Convert a volume value to a row index in the grid.
	 * 
	 * @param volume - to convert
	 * @return row index corresponding to that volume
	 */
	private int volumeToRow(int volume) {
		return rows - volume * rows / 127;
	}
	
	/**
	 * Converts a row index to pixel y value of the TOP edge of the row.
	 * 
	 * @param row - index
	 * @return pixel y value of the top edge
	 */
	private int rowToPixel(int row) {
		return row * getHeight() / rows;
	}

	/**
	 * Converts a column index to pixel x value of the LEFT edge of the column.
	 * 
	 * @param col - column index
	 * @return pixel x value of the left side
	 */
	private int colToPixel(int col) {
		return col * getWidth() / columns;
	}

	/**
	 * Converts a pixel y value to a row index.
	 * 
	 * @param pixelY - pixel y value
	 * @return index of row containing that pixel
	 */
	private int pixelToRow(int pixelY) {
		return rows * pixelY / getHeight();
	}

	/**
	 * Converts a pixel x value to a column index.
	 * 
	 * @param pixelX - pixel x value
	 * @return index of column containing that pixel
	 */
	private int pixelToCol(int pixelX) {
		return columns * pixelX / getWidth();
	}

	// Required by a serializable class (ignore for now)
	private static final long serialVersionUID = 1L;
}
