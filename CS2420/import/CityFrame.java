package lab12;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class implements a GUI for drawing houses in a city.
 * 
 * This version has sliders for setting house size.
 * 
 * @author CS 1420 course staff and ????
 * @version 2025-11-11
 */
public class CityFrame extends JFrame implements ActionListener { 
	// Menu items for closing, clearing, and setting color.
	private JMenuItem closeItem;
	private JMenuItem clearItem;
	private JMenuItem colorItem;
	private JMenuItem saveItem;
	
	// TODO - JSliders for setting house size
	
	private CityPanel cityPanel;
	
	/**
	 * The constructor starts with an empty city.
	 */
	public CityFrame() {
		// Make a panel for the content of this frame.
		cityPanel = new CityPanel();
		cityPanel.setPreferredSize(new Dimension(600, 400));
		setContentPane(cityPanel);
		
		// Add the menu items to a menu in the menu bar.
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("Edit");
		clearItem = new JMenuItem("Clear");
		clearItem.addActionListener(this);
		menu.add(clearItem);
		colorItem = new JMenuItem("Choose color");
		colorItem.addActionListener(this);
		menu.add(colorItem);
		saveItem = new JMenuItem("Save");
		saveItem.addActionListener(this);
		menu.add(saveItem);
		closeItem = new JMenuItem("Close");
		closeItem.addActionListener(this);
		menu.add(closeItem);
		menubar.add(menu);
		setJMenuBar(menubar);
		
		// TODO - Set up and add sliders for setting house size.
		
		// Setup this frame.
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
	}
	
	/**
	 * The method is called when the user makes a menu selection.
	 */
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if(src == closeItem) 
			dispose();
		else if(src == clearItem) 
			cityPanel.clearHouses();
		else if(src == colorItem){
			Color newColor = JColorChooser.showDialog(this, "Select a color", Color.ORANGE);
			if(newColor != null)
				cityPanel.setColor(newColor); 
		}else if(src == saveItem) {
			JFileChooser chooser = new JFileChooser();
			chooser.setSelectedFile(new File("myCity.jpg"));
			chooser.setFileFilter(new FileNameExtensionFilter("JPG  Images", "jpg"));
			chooser.setDialogTitle("Select the location for the new file.");
			if(chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "Save file cancelled.");
				return;
			}		
			BufferedImage img = new BufferedImage(cityPanel.getWidth(), cityPanel.getHeight(), 
					BufferedImage.TYPE_INT_RGB);
			cityPanel.paint(img.getGraphics());
			try {
				ImageIO.write(img, "jpg", chooser.getSelectedFile());
			}
			catch(IOException ex) {
				JOptionPane.showMessageDialog(null, "The drawing cannot be written to file.");
			}
		}
	}
	
	// Required by a serializable class (ignore for now)
	private static final long serialVersionUID = 1L;
}