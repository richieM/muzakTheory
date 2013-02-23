package com.google.gwt.muzaktheorygwt.client;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

//C("C"), CSHARP("C#"), D("D"),
//EFLAT("Eb"), E("E"), F("F"),
//FSHARP("F#"), G("G"), GSHARP("G#"),
//A("A"), BFLAT("Bb"), B("B"),
//NONE("Keep it abstract (half steps)");

public class ChooseRootNotePanel {
	
	private VerticalPanel panel = new VerticalPanel();
	
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);	
	private DynamicForm form = new DynamicForm();
	private ChangedHandler changedHandler = new NoteWasChanged();
	private ComboBoxItem cbItem = new ComboBoxItem();  
	
	public ChooseRootNotePanel() {
		
        cbItem.setTitle("Select root note");  
      
        cbItem.setType("comboBox");  
        cbItem.setValueMap("Keep it abstract (half steps)", "C", "C#", "D", "Eb", "E", "F", 
        					"F#","G", "G#", "A", "Bb", "B" );  
        cbItem.setAttribute("readOnly", true);
        cbItem.addChangedHandler(changedHandler);
        
        form.setWidth(350);  
        form.setFields(cbItem);
        panel.add(form);        
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}
	
	public VerticalPanel getPanel() {
		return panel;
	}
	
	class NoteWasChanged implements ChangedHandler {

		@Override
		public void onChanged(ChangedEvent event) {
			pcs.firePropertyChange("New root node selected", null, cbItem.getDisplayValue());
		}
		
	}

}
