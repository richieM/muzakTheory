package structures.old;

import java.util.Vector;


public class Group {
	
	Vector<Note> groupNotes;
	Vector<Vector <Group>> relatedScales;
	Vector<Vector <Group>> relatedChords; // most important!
	
	public enum TypeOfGroup { SCALE, CHORD }
	
	TypeOfGroup type;
	
	String name;
	
	public Group(TypeOfGroup type) {
		this.type = type;
		groupNotes = new Vector<Note>();
		this.setName("");
		relatedScales =  new Vector<Vector <Group>>();
		relatedChords =  new Vector<Vector <Group>>();
	}
	
	public Group(String name) {
		groupNotes = new Vector<Note>();
		this.setName(name);
		relatedScales =  new Vector<Vector <Group>>();
		relatedChords =  new Vector<Vector <Group>>();
	}
	
	public int getNumberOfNotes() {
		return groupNotes.size();
	}
	
	public void addNotes(Vector<Note> notes) {
		this.groupNotes = notes;
		
		// initialize the outer Vectors of relatedXXXX to be same length as notes
		for (int i=0; i<groupNotes.size(); i++) {
			relatedChords.add(new Vector<Group>());
			relatedScales.add(new Vector<Group>());
		}
	}
	
	public void addRelatedChord(Group c, int scaleMode) {
		relatedChords.get(scaleMode-1).add(c);
	}
	
	//TODO: addRelatedScale
	
	
	public Vector<Note> getNotes() {
		return groupNotes;
	}
	
	// i is unit-indexed
	// scaleNotes is zero-indexed
	public Note getNote(int i) {
		if (i >= 0 && i <= groupNotes.size()) {
			return groupNotes.get(i-1); // fix the indexing diff
		}	else {
			return null;
		} // close if			
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		String str = "Scale: " + name
				+ "\tNotes: ";
		for (Note note : groupNotes ) {
			str += " " + note.getHalfStep();
		}
		
		return str;
		
	}
	
	public String verboseToString() {
		String str = this.toString();
		str += "\nMODES:\n";
		
		for (int i=1; i<=groupNotes.size(); i++) {
			str += "\nMode " + i + ": ";
			for (Note note : this.getModeNotes(i) ) {
				str += " " + note.getHalfStep();
			}
			str += "\tChords:\n";
			for (int j=0; j<relatedChords.get(i-1).size(); j++) {
				str += "\t\t" + relatedChords.get(i-1).get(j) + "\n";
			}
		}
		
		return str;
		
	}
	
	public String otherVerboseToString() {
		String str = "*************\n" + this.toString();
			
		for (int i=0; i<groupNotes.size(); i++) {
			str += "\nNote: " + groupNotes.get(i).getHalfStep() + " (";
			for (Note note : this.getModeNotes(i+1) ) {
				str += note.getHalfStep() + " ";
			}
			str += ")  Mode " + new Integer(i+1).intValue() + "\n";
			
			for (int j=0; j<relatedChords.get(i).size(); j++) {
				str += "\t" + relatedChords.get(i).get(j) + "\n";
			}
		}
		
		return str;
		
	}
	
	
	
	public Vector<Note> getModeNotes(int mode) {
		Vector<Note> modeNotes = new Vector<Note>();
		int modeShifter = groupNotes.get(mode-1).getHalfStep();
		
		for (int i = mode-1; i<groupNotes.size(); i++) {
			modeNotes.add(new Note(groupNotes.get(i).getHalfStep()-modeShifter));
		}
		
		for (int i = 0; i < mode-1; i++) {
			modeNotes.add(new Note(groupNotes.get(i).getHalfStep()+12-modeShifter));
		}
		
		return modeNotes;
	}
	
	
}


