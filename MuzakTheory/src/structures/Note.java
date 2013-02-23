package structures;

public class Note implements Comparable<Note> {
	
	private static DodecaNote root = DodecaNote.G;
	
	public static void setRootDodecaNote(DodecaNote rootNote) {
		root = rootNote;
	}
	
	int halfStep;
	DodecaNote letterNote;
	
	public Note(int halfStep) {
		this.halfStep = halfStep;
	}
	
	public int getHalfStep() {
		return halfStep;
	}

	@Override
	public int compareTo(Note that) {
		if (this.halfStep == that.getHalfStep()) {
			return 0;
		} else if (this.halfStep < that.getHalfStep()) {
			return -1;
		} else {
			return 1;
		}
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + halfStep;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Note)) {
			return false;
		}
		Note other = (Note) obj;
		if (halfStep != other.halfStep) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return DodecaNote.getNoteRepresentation(root, this);
	}
}
