package gui.theoryMatcher;

import gui.theoryMatcher.subPanels.AmigosPanel;
import gui.theoryMatcher.subPanels.BottomActionPanel;
import gui.theoryMatcher.subPanels.GroupDisplayPanel;
import gui.theoryMatcher.subPanels.TopTitlePanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;

import runner.Main;
import structures.Group;

public class TheoryMatcher implements Runnable, PropertyChangeListener {

	private PropertyChangeSupport psc = new PropertyChangeSupport(this);
	
	private JFrame thisFrame;
	private JPanel mainPanel;
	
	private JFrame amigosFrame;
	
	private TopTitlePanel topTitlePanel;
	private GroupDisplayPanel scalesPanel;
	private GroupDisplayPanel chordsPanel;
	private BottomActionPanel bottomPanel;
	private Logger logger = Main.getLogger();

	public TheoryMatcher() {
		mainPanel = new JPanel(new BorderLayout());
	}
	
	public void setScales(List<Group> scales) {
		scalesPanel.updateTable(scales);
	}
	
	public void setChords(List<Group> chords) {
		chordsPanel.updateTable(chords);
	}
	
	public void initializeGui() {
		topTitlePanel = new TopTitlePanel();
		topTitlePanel.addPropertyChangeListener(this);
		mainPanel.add(topTitlePanel, BorderLayout.NORTH);
		
		scalesPanel = new GroupDisplayPanel("Scales");
		scalesPanel.addPropertyChangeListener(this);
		mainPanel.add(scalesPanel, BorderLayout.WEST);
		
		chordsPanel = new GroupDisplayPanel("Chords");
		chordsPanel.addPropertyChangeListener(this);
		mainPanel.add(chordsPanel, BorderLayout.EAST);
		
		bottomPanel = new BottomActionPanel();
		bottomPanel.addPropertyChangeListener(this);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);		
	}
	
	@Override
	public void run() {
		thisFrame = new JFrame();
		thisFrame.setTitle("Muzak Theory");
		thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel.setMinimumSize(new Dimension(700,700));
		
		thisFrame.add(mainPanel);
		thisFrame.pack();
		thisFrame.setResizable(true);
		thisFrame.setVisible(true);		
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent pce) {
		String propertyName = pce.getPropertyName();
		
		if (propertyName == "Find Amigos") {
			showAmigos();						
		} else if (propertyName == "Options") {
			// TODO
		} else if (propertyName == "???") {
			// TODO send something up to main?
		}
		
	}
	
	private void showAmigos() {
		List<Group> selectedScales = scalesPanel.getSelectedGroups();
		List<Group> selectedChords = chordsPanel.getSelectedGroups();
				
		AmigosPanel amigosPanel = new AmigosPanel(selectedScales, selectedChords);
		
		amigosFrame = new JFrame();
				
		amigosFrame.setTitle("Muzak Theory");
		amigosPanel.setMinimumSize(new Dimension(700,700));
		
		amigosFrame.add(amigosPanel);
		amigosFrame.pack();
		amigosFrame.validate();
		amigosFrame.setResizable(true);
		amigosFrame.setVisible(true);			
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		   this.psc.addPropertyChangeListener(listener);
		}
	
	

}
