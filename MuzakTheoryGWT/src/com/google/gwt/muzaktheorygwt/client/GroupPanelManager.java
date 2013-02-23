package com.google.gwt.muzaktheorygwt.client;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import structures.Group;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.types.Autofit;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;

public class GroupPanelManager {

	private Label panelName = new Label();
	private ListGrid groupTable = new ListGrid();
	private VerticalPanel panel = new VerticalPanel();

	private List<Group> groups = new ArrayList<Group>();
	
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);	

	public GroupPanelManager(String name) {
		panelName.setText(name);

		setUpTable();

		panel.add(panelName);
		panel.add(groupTable);		
		panel.setSpacing(10);
	}

	private void setUpTable() {
		ListGridField name = new ListGridField("Name", "Name");
		ListGridField notes = new ListGridField("Notes", "Notes");

		groupTable.setFields(name, notes);

		groupTable.setAutoFitData(Autofit.BOTH);
		groupTable.setAutoFitMaxColumns(5);
		groupTable.setWidth(300);

		groupTable.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				GWT.log("table in " + panelName.getText() + " was clicked.");	
				pcs.firePropertyChange(panelName.getText() + " state changed.", null, getSelectedGroups());
			}
		});
		//groupTable.setShowAllRecords(true);
		//groupTable.setShowAllColumns(true);
	}

	private List<Group> getSelectedGroups() {
		List<Group> selectedGroups = new ArrayList<Group>();
		
		ListGridRecord[] records = groupTable.getSelectedRecords();
		for (int i=0 ; i < records.length ; i++) {
			selectedGroups.add(((GroupListGridRecord)(records[i])).getGroup());
		}
		
		return selectedGroups;
	}
	
	public void setGroups(List<Group> groups) {
		this.groups = groups;
		displayGroupsInTable();
	}

	public void displayGroupsInTable() {
		groupTable.setData(new ListGridRecord[] {});
		
		for (Group currentGroup : groups) {
			ListGridRecord currentGridRecord = new GroupListGridRecord(currentGroup);

			groupTable.addData(currentGridRecord);
		}		
	}

	public VerticalPanel getPanel() {
		return panel;
	} 
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

	class GroupListGridRecord extends ListGridRecord {
		private Group myGroup;

		public GroupListGridRecord(Group myGroup) {
			this.myGroup = myGroup;
			this.setAttribute("Name", myGroup.getName());
			this.setAttribute("Notes", myGroup.getNotes().toString());
		}

		public Group getGroup() {
			return myGroup;
		}
	}

}
