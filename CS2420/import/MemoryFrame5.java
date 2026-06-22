package lec19;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class implements a Memory Game GUI.
 * 
 * This is the fifth version of the GUI built in class, 
 * with buttons included, listening for when each button 
 * is selected, detection of when matches are made, and  
 * disabling buttons when they should not be selected by user. 
 *  
 * @author Eric Heisler
 * @version 2025-11-3
 */
public class MemoryFrame5 extends JFrame implements ActionListener {
	
	// buttons on game board currently selected
	private ArrayList<MemoryButton> buttonsSelected; 

	/**
	 * Creates a Memory Game GUI.
	 */
	public MemoryFrame5() {
		
		// Set up the JFrame
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Memory");
		setPreferredSize(new Dimension(600, 600));
		
		// Create a container to hold and organize the 16 buttons
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 4));
		
		// Create 16 buttons and or each button: 
		//    --construct the button and hide a secret value
		//    --add the button to the container (so it can be seen)
		//    --add a listener for the button (what to do when user selects)
		
		ArrayList<Integer> secretValues = generateSecretValues(8);
	
		for(int i = 0; i < 16; i++) {
			MemoryButton button = new MemoryButton(secretValues.remove(0));
			button.setFont(new Font("Dialog", Font.PLAIN, 100));
			panel.add(button);
			button.addActionListener(this);
		}
		
		// Keep track of buttons selected by the user, none yet
		buttonsSelected = new ArrayList<MemoryButton>();  
		
		// Add content to the frame
		setContentPane(panel);
		pack();
	}
	
	/**
	 * This is the method that is invoked when a button is selected.
	 * 
	 * @param -- an object the represents the event of the button 
	 *           being selected
	 */
	public void actionPerformed(ActionEvent e) {
		
		// If two buttons were saved from last round, their values need to be hidden.
		if(buttonsSelected.size() == 2) {
			buttonsSelected.get(0).hideValue();
			buttonsSelected.get(1).hideValue();
			buttonsSelected.clear();
		}
		
		// Get the button just selected by user
		MemoryButton button = (MemoryButton)e.getSource();
		
		// Show the value for the button
		button.showValue();

		// Disable the button
		button.setEnabled(false);
		
		// Keep track of this button for future rounds
		buttonsSelected.add(button);
 
		// If this is the second button of a pair, check for a match
		if(buttonsSelected.size() == 2) {
			if(button.getValue() == buttonsSelected.get(0).getValue()) {
				System.out.println("MATCH!");
				// We are finished with these buttons, do not re-enable
				buttonsSelected.clear();
			}
			else {
				// enable both buttons
				buttonsSelected.get(0).setEnabled(true);
				buttonsSelected.get(1).setEnabled(true);
			}
		}
	}
	
	/** 
	 * Generates a list of integers 1 to limit, two of each, 
	 * randomly shuffled.
	 * 
	 * (This is a helper method for the constructor -- make private.)
	 * 
	 * @param limit -- the largest integer generated
	 * @returns an ArrayList of integers
	 */
	private ArrayList<Integer> generateSecretValues(int limit) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 1; i <= limit; i++) {
			list.add(i);
			list.add(i);
		}	
		Collections.shuffle(list);
		return list;
	}
	
	// Required by a serializable class (ignore for now)
	private static final long serialVersionUID = 1L;
}