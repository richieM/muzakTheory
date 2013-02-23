package structures;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import toolBox.ModeMath;

/*
 * copy constructor needed?
 */

public class Group {

	public enum TypeOfGroup { SCALE, CHORD, GROUP }

	TypeOfGroup type;
	String name;
	List<Note> notes;
	int modeNoteValue;
	List<Group> amigos = new ArrayList<Group>();
	List<Group> myModes = new ArrayList<Group>();

	public Group(TypeOfGroup type) {
		this.type = type;
		this.name = "";
		this.modeNoteValue = 0;
	}

	public Group(TypeOfGroup type, String name, int mode) {
		this.type = type;
		this.name = name;
		this.modeNoteValue = mode;
	}

	public Group(String name) {
		this.type = TypeOfGroup.GROUP;
		this.name = name;		
		this.modeNoteValue = 0;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getModeValue() {
		return modeNoteValue;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
		if (modeNoteValue == 0) {
			setupAllModes();
		}
	}

	public List<Note> getNotes() {
		return notes;
	}

	public TypeOfGroup getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		// prob a crazy stackOverflow
		//result = prime * result + ((matchingGroups == null) ? 0 : matchingGroups.hashCode());
		result = prime * result + modeNoteValue;
		
		//crazy stackOverflow
		//result = prime * result + ((myModes == null) ? 0 : myModes.hashCode());
		
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	public void addAnAmigo(Group amigo) {
		amigos.add(amigo);
	}
	
	public List<Group> getAmigos() {
		return amigos;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Group)) {
			return false;
		}
		Group other = (Group) obj;
		if (amigos == null) {
			if (other.amigos != null) {
				return false;
			}
		} 
		
		if (modeNoteValue != other.modeNoteValue) {
			return false;
		}
		if (myModes == null) {
			if (other.myModes != null) {
				return false;
			}
		} 
		
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (notes == null) {
			if (other.notes != null) {
				return false;
			}
		} else if (!notes.equals(other.notes)) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		return true;
	}

	void setMyModes(List<Group> modes) {
		myModes = modes;
	}
	
	public List<Group> getMyModes() {
		return myModes;
	}

	private void setupAllModes() {
		for (Note currentNote : notes) {
			int desiredMode = currentNote.halfStep;
			if (desiredMode == 0) {
				myModes.add(this);
			} else {
				Group currentGroup = new Group(type, name, desiredMode);
				currentGroup.setNotes(ModeMath.shiftToMode(notes, desiredMode));
				myModes.add(currentGroup);
			}
		}

		for (Group currentGroup : myModes) {
			currentGroup.setMyModes(myModes);
		}

	}

	public Group retrieveOtherMode(int desiredMode) throws NoSuchElementException {
		for (Group currentMode : myModes) {
			if (currentMode.getModeValue() == desiredMode) {
				return currentMode;
			}
		}

		throw new NoSuchElementException();
	}

	@Override
	public String toString() {
		String result = "";
		result += "Group [type=" + type + ", name=" + name + ", modeNoteValue=" + modeNoteValue + 
				", notes=" + notes + ", numberOfAmigos= " + amigos.size() + "]";
		
		
		return result;
	}
	
	public String fullAutoBiography() {
		String result = "****";
		result += this.toString() + "\n\n";
		
		for (Group currentMode : myModes) {
			result += currentMode.getModeValue() + "\t" + currentMode.toString() + "\n";
			for (Group currentAmigo : currentMode.getAmigos()) {
				result += "\t\t" + currentAmigo.toString() + "\n";
			}
			result += "\n";
		}
		
		return result;
	}

	public void clearAmigos() {
		amigos.clear();		
	}
	
	
	
	
}
