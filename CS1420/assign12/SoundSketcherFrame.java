package assign12;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Main application frame for the SoundSketcher music composition tool.
 * Contains controls for playback and track management.
 * 
 * @author Haoquan Wang
 * @version 2025-12-02
 */
public class SoundSketcherFrame extends JFrame implements ActionListener, ChangeListener {
    
    private final int DEFAULT_TEMPO = 300;
    private final int SONG_DURATION = 16;
    private Song song;
    private TrackPanel[] trackPanel;
    
    private JToggleButton player;
    private JToggleButton loop;
    private JSlider tempoSlider;
    private JSpinner controlDuration;
    
    /**
     * Constructs the main application frame with all controls and track panels.
     */
    public SoundSketcherFrame() {
        song = new Song(DEFAULT_TEMPO, SONG_DURATION);
        setPreferredSize(new Dimension(800, 800));
        setTitle("SoundSketcher");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setLayout(new BorderLayout());
        
        initializeMenuBar();
        initializeTrackPanels();
        initializeControls();
        
        pack();
        setVisible(true);
    }
    
    /**
     * Initializes the menu bar with File menu containing Save and Load options.
     */
    private void initializeMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu fileMenu = new JMenu("File");
        
        JMenuItem saveItem = new JMenuItem("Save Song");
        JMenuItem loadItem = new JMenuItem("Load Song");
        
        saveItem.addActionListener(this);
        loadItem.addActionListener(this);
        
        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }
    
    /**
     * Initializes all track panels and adds them to a tabbed pane.
     */
    private void initializeTrackPanels() {
        trackPanel = new TrackPanel[10];
        
        JTabbedPane trackTabs = new JTabbedPane();
        for (int trackNum = 0; trackNum < 10; trackNum++) {
            trackPanel[trackNum] = new TrackPanel(
                trackNum, 
                song.getSongLength(), 
                song.getTrack(trackNum), 
                song.getSynthesizer()
            );
            trackTabs.addTab("Track " + (trackNum + 1), trackPanel[trackNum]);
        }
        add(trackTabs, BorderLayout.CENTER);
    }
    
    /**
     * Initializes and arranges all control components.
     */
    private void initializeControls() {
        player = new JToggleButton("Play", false);
        loop = new JToggleButton("Loop", false);
        tempoSlider = new JSlider(20, 600);
        JLabel explanTempoSlider = new JLabel("Playback speed");
        controlDuration = new JSpinner(new SpinnerNumberModel(DEFAULT_TEMPO, 4, 1024, 4));
        JLabel explanControlDuration = new JLabel("Duration");
        
        player.addActionListener(this);
        loop.addActionListener(this);
        tempoSlider.addChangeListener(this);
        controlDuration.addChangeListener(this);
        
        JPanel controlPanel = createControlPanel(player, loop, tempoSlider, 
                                                explanTempoSlider, controlDuration, 
                                                explanControlDuration);
        add(controlPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Creates and arranges the control panel components.
     * 
     * @param player the play/stop button
     * @param loop the loop toggle button
     * @param tempoSlider the tempo adjustment slider
     * @param tempoLabel the tempo slider description
     * @param durationSpinner the song length spinner
     * @param durationLabel the duration spinner description
     * @return the configured control panel
     */
    private JPanel createControlPanel(JToggleButton player, JToggleButton loop, 
                                     JSlider tempoSlider, JLabel tempoLabel,
                                     JSpinner durationSpinner, JLabel durationLabel) {
        JPanel mainControl = new JPanel(new BorderLayout());
        
        JPanel topRow = new JPanel(new BorderLayout());
        topRow.add(tempoSlider, BorderLayout.NORTH);
        topRow.add(tempoLabel, BorderLayout.WEST);
        
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        centerPanel.add(durationSpinner, BorderLayout.NORTH);
        centerPanel.add(durationLabel, BorderLayout.SOUTH);
        topRow.add(centerPanel, BorderLayout.CENTER);
        topRow.add(loop, BorderLayout.EAST);
        
        JPanel bottomRow = new JPanel(new BorderLayout());
        bottomRow.setPreferredSize(new Dimension(800, 50));
        bottomRow.add(player, BorderLayout.CENTER);
        
        mainControl.add(topRow, BorderLayout.NORTH);
        mainControl.add(bottomRow, BorderLayout.SOUTH);
        
        return mainControl;
    }
    
    /**
     * Handles action events from buttons and menu items.
     * 
     * @param e the action event
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == player) {
            if (player.isSelected()) {
                song.play();
                player.setText("Stop");
            } else {
                song.stop();
                player.setText("Play");
            }
        } else if (e.getSource() == loop) {
            if (loop.isSelected()) {
                song.enableLoop(true);
            } else {
                song.enableLoop(false);
            }
        } else if (e.getActionCommand().equals("Save Song")) {
            saveSong();
        } else if (e.getActionCommand().equals("Load Song")) {
            loadSong();
        }
    }
    
    /**
     * Handles saving a song to a file.
     */
    private void saveSong() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
            "Song files", "song"));
        
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        
        int result = chooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            // Ensure the file has .song extension
            if (!file.getName().toLowerCase().endsWith(".song")) {
                file = new File(file.getAbsolutePath() + ".song");
            }
            
            try {
                SongFiles.writeFile(file, song);
                JOptionPane.showMessageDialog(this, 
                    "Song saved successfully!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error saving song: " + ex.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Handles loading a song from a file.
     */
    private void loadSong() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
            "Song files", "song"));
        
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            
            try {
                // Stop any currently playing song
                song.stop();
                player.setSelected(false);
                player.setText("Play");
                
                // Load the new song
                SongFiles.readFile(file, song);
                
                // Update GUI components
                updateGUIAfterLoad();
                
                JOptionPane.showMessageDialog(this, 
                    "Song loaded successfully!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                    
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error loading song: " + ex.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Updates all GUI components after loading a song.
     */
    private void updateGUIAfterLoad() {
        // Update tempo slider
        setTempoSlider(song.getTempo());
        
        // Update song length spinner
        controlDuration.setValue(song.getSongLength());
        
        // Update each track panel
        for (int i = 0; i < 10; i++) {
            trackPanel[i].setSongLength(song.getSongLength());
            trackPanel[i].setInstrument(song.getSynthesizer().getInstrument(i));
        }
    }
    
    /**
     * Sets the tempo slider value, adjusting min/max if necessary.
     * 
     * @param newTempo the new tempo value
     */
    private void setTempoSlider(int newTempo) {
        if (newTempo < tempoSlider.getMinimum()) {
            tempoSlider.setMinimum(newTempo);
        } else if (newTempo > tempoSlider.getMaximum()) {
            tempoSlider.setMaximum(newTempo);
        }
        tempoSlider.setValue(newTempo);
    }
    
    /**
     * Handles change events from sliders and spinners.
     * 
     * @param e the change event
     */
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == tempoSlider) {
            int tempo = tempoSlider.getValue();
            song.setTempo(tempo);
        } else if (e.getSource() == controlDuration) {
            int length = (Integer) controlDuration.getValue();
            song.setSongLength(length);
            for (TrackPanel panel : trackPanel) {
                panel.setSongLength(length);
            }
        }
    }
}