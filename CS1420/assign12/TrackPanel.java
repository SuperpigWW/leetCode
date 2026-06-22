package assign12;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;


/**
 * Panel for individual track controls and editing.
 * Contains instrument selection, mute, mode selection, and clear controls.
 * 
 * @author Haoquan Wang
 * @version 2025-12-02
 */
public class TrackPanel extends JPanel implements ActionListener {
    
    private int trackNumber;
    private SimpleSynthesizer synth;
    private TrackEditor trackEditor;
    
    private JComboBox<String> instrumentComboBox;
    private JToggleButton muteButton;
    private JToggleButton noteModeButton;
    private JToggleButton volumeModeButton;
    private JToggleButton copyModeButton;
    private JButton clearButton;
    private ButtonGroup modeButtonGroup;
    
    /**
     * Constructs a track panel for the specified track.
     * 
     * @param trackNumber the track number (0-9)
     * @param songLength the length of the song in ticks
     * @param events the audio events for this track
     * @param synth the synthesizer for audio playback
     */
    public TrackPanel(int trackNumber, int songLength, ArrayList<AudioEvent> events, SimpleSynthesizer synth) {
        this.trackNumber = trackNumber;
        this.synth = synth;
        
        setLayout(new BorderLayout());
        
        this.trackEditor = new TrackEditor(trackNumber, songLength, events, synth);
        add(trackEditor, BorderLayout.CENTER);
        
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.NORTH);
        
        setBorder(BorderFactory.createTitledBorder("Track " + trackNumber));
        
        setupEventListeners();
    }
    
    /**
     * Creates the control panel with all track controls.
     * 
     * @return the configured control panel
     */
    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        JLabel instrumentLabel = new JLabel("Instrument:");
        instrumentComboBox = new JComboBox<String>(new Vector<String>(synth.getInstrumentNames()));
        controlPanel.add(instrumentLabel);
        controlPanel.add(instrumentComboBox);
        
        controlPanel.add(Box.createHorizontalStrut(20));
        
        muteButton = new JToggleButton("Mute");
        controlPanel.add(muteButton);
        
        controlPanel.add(Box.createHorizontalStrut(20));
        
        JLabel modeLabel = new JLabel("Mode:");
        controlPanel.add(modeLabel);
        
        noteModeButton = new JToggleButton("Note");
        volumeModeButton = new JToggleButton("Volume");
        copyModeButton = new JToggleButton("Copy/Paste");
        
        modeButtonGroup = new ButtonGroup();
        modeButtonGroup.add(noteModeButton);
        modeButtonGroup.add(volumeModeButton);
        modeButtonGroup.add(copyModeButton);
        
        noteModeButton.setSelected(true);
        
        controlPanel.add(noteModeButton);
        controlPanel.add(volumeModeButton);
        controlPanel.add(copyModeButton);
        
        controlPanel.add(Box.createHorizontalStrut(20));
        
        clearButton = new JButton("Clear Track");
        controlPanel.add(clearButton);
        
        return controlPanel;
    }
    
    /**
     * Sets up event listeners for all interactive components.
     */
    private void setupEventListeners() {
        muteButton.addActionListener(this);
        noteModeButton.addActionListener(this);
        volumeModeButton.addActionListener(this);
        copyModeButton.addActionListener(this);
        clearButton.addActionListener(this);
        instrumentComboBox.addActionListener(this);
    }
    
    /**
     * Handles action events from track controls.
     * 
     * @param e the action event
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == muteButton) {
            synth.setMute(trackNumber, muteButton.isSelected());
        } else if (e.getSource() == noteModeButton) {
            trackEditor.setMode(TrackEditor.Mode.NOTE);
        } else if (e.getSource() == volumeModeButton) {
            trackEditor.setMode(TrackEditor.Mode.VOLUME);
        } else if (e.getSource() == copyModeButton) {
            trackEditor.setMode(TrackEditor.Mode.COPY);
        } else if (e.getSource() == clearButton) {
            trackEditor.clearTrack();
        } else if (e.getSource() == instrumentComboBox) {
            int instrument = instrumentComboBox.getSelectedIndex();
            synth.setInstrument(trackNumber, instrument);
        }
    }
    
    /**
     * Sets the instrument for this track.
     * 
     * @param instrument the instrument index
     */
    public void setInstrument(int instrument) {
        synth.setInstrument(trackNumber, instrument);
        instrumentComboBox.setSelectedIndex(instrument);
    }
    
    /**
     * Sets the song length for this track's editor.
     * 
     * @param songLength the new song length in ticks
     */
    public void setSongLength(int songLength) {
        trackEditor.setSongLength(songLength);
    }
    
    /**
     * Gets the track number.
     * 
     * @return the track number
     */
    public int getTrackNumber() {
        return trackNumber;
    }
    
    /**
     * Gets the synthesizer for this track.
     * 
     * @return the synthesizer
     */
    public SimpleSynthesizer getSynthesizer() {
        return synth;
    }
    
    /**
     * Gets the track editor for this track.
     * 
     * @return the track editor
     */
    public TrackEditor getTrackEditor() {
        return trackEditor;
    }
    
    /**
     * Gets the instrument combo box.
     * 
     * @return the instrument selection combo box
     */
    public JComboBox<String> getInstrumentComboBox() {
        return instrumentComboBox;
    }
    
    /**
     * Gets the mute button.
     * 
     * @return the mute toggle button
     */
    public JToggleButton getMuteButton() {
        return muteButton;
    }
    
    /**
     * Gets the note mode button.
     * 
     * @return the note mode toggle button
     */
    public JToggleButton getNoteModeButton() {
        return noteModeButton;
    }
    
    /**
     * Gets the volume mode button.
     * 
     * @return the volume mode toggle button
     */
    public JToggleButton getVolumeModeButton() {
        return volumeModeButton;
    }
    
    /**
     * Gets the copy/paste mode button.
     * 
     * @return the copy/paste mode toggle button
     */
    public JToggleButton getCopyModeButton() {
        return copyModeButton;
    }
    
    /**
     * Gets the clear track button.
     * 
     * @return the clear track button
     */
    public JButton getClearButton() {
        return clearButton;
    }
}