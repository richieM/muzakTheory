package toolBox;
import java.util.Vector;

import structures.Group;
import structures.Note;


public class Comparator {
	
	// returns true if they match in that scale's mode, false otherwise
	public static boolean compare(Group scale, Group chord, int scaleMode) {
		Vector<Note> modeNotes = scale.getModeNotes(scaleMode);
		
		boolean isInThere = false;
		
		for (Note chordNote : chord.getNotes()) {
			for (Note scaleNote : modeNotes) {
				if (scaleNote.compareTo(chordNote) == 0) {
					isInThere = true;
					break;
				}
			}			
			if (!isInThere) {return false;}
			isInThere = false;
		} 
		
		return true;
		
		}

}
