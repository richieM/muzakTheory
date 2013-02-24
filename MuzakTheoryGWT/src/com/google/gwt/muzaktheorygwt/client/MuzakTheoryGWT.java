package com.google.gwt.muzaktheorygwt.client;

import io.ReadScalesAndChords;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import structures.DodecaNote;
import structures.Group;
import structures.Note;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class MuzakTheoryGWT implements EntryPoint, PropertyChangeListener {
	private VLayout mainLayout = new VLayout();
	private HLayout centerLayout = new HLayout();

	List<Group> scales = new ArrayList<Group>();
	List<Group> chords = new ArrayList<Group>();

	private GroupPanelManager scalesTableManager = new GroupPanelManager("Scales");
	private GroupPanelManager chordsTableManager = new GroupPanelManager("Chords");

	private ResultsPanel resultsTableManager = new ResultsPanel("Matches");
	
	private ChooseRootNotePanel chooseRootNotePanel = new ChooseRootNotePanel();

	/**
	 * Entry point method.
	 */
	public void onModuleLoad() {

		ReadScalesAndChords reader = new ReadScalesAndChords();
		reader.readIn(chords, scales);

		scalesTableManager.setGroups(scales);
		scalesTableManager.addPropertyChangeListener(this);

		chordsTableManager.setGroups(chords);
		chordsTableManager.addPropertyChangeListener(this);
		
		chooseRootNotePanel.addPropertyChangeListener(this);
		
		GWT.log(scales.toString());
		GWT.log(chords.toString());

		centerLayout.addMember(scalesTableManager.getPanel());
		centerLayout.addMember(chordsTableManager.getPanel());

		centerLayout.addMember(resultsTableManager.getPanel());
		
		mainLayout.addMember(chooseRootNotePanel.getPanel());
		mainLayout.addMember(centerLayout);

		RootPanel.get("scaleChordsList").add(mainLayout);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void propertyChange(PropertyChangeEvent e) {
		GWT.log("Got event in TheoryGWT: " + e.getPropertyName());	
		if (e.getPropertyName().contains("Scale")) {
			resultsTableManager.setScales((List<Group>)(e.getNewValue()));
		} else if (e.getPropertyName().contains("Chord")) {
			resultsTableManager.setChords((List<Group>)(e.getNewValue()));
		} else if (e.getPropertyName().contains("root node")) { 
			String newRootNode = (String)e.getNewValue();
			GWT.log("Root node changed to " + newRootNode);
			Note.setRootDodecaNote(DodecaNote.getDodecaNoteFromAbbrev(newRootNode));
			scalesTableManager.displayGroupsInTable();
			chordsTableManager.displayGroupsInTable();
			resultsTableManager.clear();
			centerLayout.redraw();
		}
	}
}
