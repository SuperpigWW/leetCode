package assign12;

import assign12.SoundSketcherFrame;

/**
 * Main class for the SoundSketcher application.
 * Creates and displays the main application frame.
 * 
 * @author Haoquan Wang
 * @version 2025-11-13
 */
public class SoundSketcher {

    /**
     * Main method that launches the SoundSketcher application.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
    	//SongFiles.debugFileReading("test.song");
        SoundSketcherFrame frame = new SoundSketcherFrame();
        frame.setVisible(true);
    }
}