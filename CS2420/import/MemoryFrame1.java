package lec19;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class implements a Memory Game GUI.
 * This is also a program for creating and launching the GUI
 * (see the main method).
 * 
 * This is the first version of the GUI built in class, 
 * just an "empty" JFrame.
 * 
 * @author Eric Heisler
 * @version 2025-11-3
 */
public class MemoryFrame1 extends JFrame{
	/**
	 * Creates a Memory Game GUI.
	 */
	public MemoryFrame1() {
		// JFrame has a default constructor, so don't need to call super()
		
		// Set up the frame itself ////////////////////////////////////
		// Exit on close
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Title at the top
		setTitle("Memory");
		// How big should the frame be?
		setPreferredSize(new Dimension(600, 600));
		
		// Now make all the pieces that go in this frame //////////////
		// Create a container to hold and organize the 16 buttons
		// Put them in a 4x4 grid
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 4));
		
		// Put the components into this frame. ///////////////////////
		setContentPane(panel);
		pack();
	}
	
	// Required by a serializable class (ignore for now)
	private static final long serialVersionUID = 1L;
}