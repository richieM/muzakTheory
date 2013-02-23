package io;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import structures.Group;
import structures.Note;


public class ReadScalesAndChords {

	private List<String> data = new ArrayList<String>();

	public void readIn(List<Group> chords, List<Group> scales) {
		constructData();
		for (String currentGroupLine : data) {
			if (currentGroupLine.startsWith("Chord")) {
				Group newChord = retrieveGroup(currentGroupLine);
				chords.add(newChord);					
			} else if (currentGroupLine.startsWith("Scale")) {
				Group newScale = retrieveGroup(currentGroupLine);
				scales.add(newScale);
			}
		}
	}


	public static Group retrieveGroup(String currentLine) throws NoSuchElementException {
		String parts[] = currentLine.split(" ");
		Group newGroup;
		if (parts[0].equalsIgnoreCase("Chord")) {
			newGroup = new Group(Group.TypeOfGroup.CHORD);
		} else if (parts[0].equalsIgnoreCase("Scale")) {
			newGroup = new Group(Group.TypeOfGroup.SCALE);
		} else {
			throw new NoSuchElementException();
		}

		newGroup.setName(parts[1].replace('_', ' '));

		List<Note> notes = new ArrayList<Note>();
		int i = 2;

		while (i < parts.length) {
			notes.add(new Note(new Integer(parts[i])));
			i++;
		}

		newGroup.setNotes(notes);

		return newGroup;
	}

	private void constructData() {

		data.add("Scale Major 0 2 4 5 7 9 11");
		data.add("Scale Minor 0 2 3 5 7 9 10");
		data.add("Scale Jewish 0 1 4 5 7 8 10");
		data.add("Scale Hungarian_Gypsy 0 2 3 6 7 8 10");
		data.add("Scale Hungarian_Minor 0 2 3 6 7 8 11");
		data.add("Scale Hungarian_Major 0 3 4 6 7 9 10");
		data.add("Scale Whole_Tone 0 2 4 6 8 10");
		data.add("Scale Blues 0 3 5 6 7 10");
		data.add("Scale Pentatonic 0 2 4 7 9");
		data.add("Scale Harmonic_Minor 0 2 3 5 7 8 11");
		data.add("Scale Ascending_Melodic_Minor 0 2 3 5 7 9 11");
		data.add("Scale Lydian-Augmented 0 2 4 6 8 9 11");
		data.add("Scale Lydian_b7 0 2 4 6 7 9 10 12");
		data.add("Scale Locrian_#2 0 2 3 5 6 8 10");
		data.add("Scale Super_Locrian 0 1 3 4 6 8 10");
		data.add("Scale Bop 0 2 4 5 7 8 9 11");
		data.add("Scale Egyptian 0 2 5 7 10");
		data.add("Chord Major_Triad 0 4 7");
		data.add("Chord Minor_Triad 0 3 7");
		data.add("Chord Augmented_Triad 0 4 8");
		data.add("Chord Diminished_Triad 0 3 6");
		data.add("Chord Diminished_7th 0 3 6 9");
		data.add("Chord Half-Diminished_7th 0 3 6 10");
		data.add("Chord Minor_7th 0 3 7 10");
		data.add("Chord Minor_Major_7th 0 3 7 11");
		data.add("Chord Dominant_7th 0 4 7 10");
		data.add("Chord Major_7th 0 4 7 11");
		data.add("Chord Augmented_7th 0 4 8 10");
		data.add("Chord Augmented_Major_7th 0 4 8 11");		
	}
}