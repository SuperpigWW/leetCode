package assign10;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.Dimension;
import java.awt.BorderLayout;

/**
 * Main application frame for the SoundSketcher music composition tool.
 * Contains controls for playback and track management.
 * 
 * @author Haoquan Wang
 * @version 2025-11-13
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
        setTitle("Song Player");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setLayout(new BorderLayout());
        
        initializeTrackPanels();
        initializeControls();
        
        pack();
        setVisible(true);
    }
    
    /**
     * Initializes all track panels and adds them to a tabbed pane.
     */
    private void initializeTrackPanels() {
        trackPanel = new TrackPanel[10];
        for (int trackNum = 0; trackNum < 10; trackNum++) {
            trackPanel[trackNum] = new TrackPanel(
                trackNum, 
                song.getSongLength(), 
                song.getTrack(trackNum), 
                song.getSynthesizer()
            );
        }
        
        JTabbedPane trackTabs = new JTabbedPane();
        for (int i = 0; i < trackPanel.length; i++) {
            trackTabs.addTab("Track " + (i + 1), trackPanel[i]);
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
     * Handles action events from buttons.
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
        }
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
