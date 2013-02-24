package structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum DodecaNote { 
	 C("C"), CSHARP("C#"), D("D"),
	 EFLAT("Eb"), E("E"), F("F"),
	 FSHARP("F#"), G("G"), GSHARP("G#"),
	 A("A"), BFLAT("Bb"), B("B"),
	 NONE("Numbers (half steps)");
	
	private static int modeShift;
	private final String abbreviation;		
	private static final Map<String, DodecaNote> lookup = new HashMap<String, DodecaNote>();
	
	private static final List<DodecaNote> dodecaNotes = new ArrayList<DodecaNote>();
	
	static {
		for (DodecaNote d : DodecaNote.values()) {
			lookup.put(d.getAbbreviation(), d);
			if (d != DodecaNote.NONE) {
				dodecaNotes.add(d);
			}
		}
	}
	
	private DodecaNote(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	private String getAbbreviation() {
		return abbreviation;
	}
	
	public static void setModeShift(int ms) {
		modeShift = ms;
	}
	
	public static String getNoteRepresentation(DodecaNote root, Note note) {		
		if (root == DodecaNote.NONE) {
			return note.getHalfStep() + modeShift + "";
		} else {
			int indexOfRoot = dodecaNotes.indexOf(root);
			int indexOfNote = (indexOfRoot + modeShift + note.getHalfStep()) % 12;
			return dodecaNotes.get(indexOfNote).getAbbreviation() + "";
		}
	}
	
	public static DodecaNote getDodecaNoteFromAbbrev(String abbreviation) {
		return lookup.get(abbreviation);
	}
	
}
