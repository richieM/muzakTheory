package toolBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import structures.Group;
import structures.Note;

public class ModeMath {

	public static List<Note> shiftToMode(List<Note> oldNotes, int mode) {
		List<Note> newModeNotes = new ArrayList<Note>();

		for (Note currentNote : oldNotes) {
			int newHalfStep = currentNote.getHalfStep() - mode;
			if (newHalfStep < 0) {
				newHalfStep += 12;
			}
			newModeNotes.add(new Note(newHalfStep));
		}

		// TODO test this
		Collections.sort(newModeNotes);

		return newModeNotes;		
	}

	public static boolean areTheyFriends(List<Note> notes1, List<Note> notes2) {
		if (notes1.containsAll(notes2)) {
			return true;
		} else if (notes2.containsAll(notes1)) {
			return true;
		}

		return false;
	}

	public static void findGroupAmigos(List<Group> scales, List<Group> chords) {
		
		for (Group currentScale : scales) {
			currentScale.clearAmigos();
		}
		
		for (Group currentChord : chords) {
			currentChord.clearAmigos();
		}
		
		for (Group currentScale : scales) {
			List<Group> currentScaleModes = currentScale.getMyModes();
			for (Group currentMode : currentScaleModes) {
				for (Group currentChord : chords) {
					if (areTheyFriends(currentMode.getNotes(), currentChord.getNotes())) {
						if (!currentMode.getAmigos().contains(currentChord)) {
							currentMode.addAnAmigo(currentChord);
						}
						if (!currentChord.getAmigos().contains(currentMode)) {
							currentChord.addAnAmigo(currentMode);
						}
					}				
				}
				for (Group currentScaleToCompareTo : scales) {
					if (areTheyFriends(currentMode.getNotes(), currentScaleToCompareTo.getNotes())) {
						if (!currentMode.getAmigos().contains(currentScaleToCompareTo) && currentScaleToCompareTo != currentMode) {
							currentMode.addAnAmigo(currentScaleToCompareTo);
						}
						if (!currentScaleToCompareTo.getAmigos().contains(currentMode) && currentScaleToCompareTo != currentMode) {
							currentScaleToCompareTo.addAnAmigo(currentMode);
						}
					}				
				}				
			}
		}
	}

}
