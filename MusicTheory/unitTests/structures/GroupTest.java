package structures;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import structures.Group;
import structures.Group.TypeOfGroup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GroupTest {

	@Test
	public void testEnumConstructor() {
		Group group = new Group(TypeOfGroup.CHORD);
		assertEquals("Group type should be CHORD", TypeOfGroup.CHORD, group.getType());
		assertEquals("Name should be empty string", "", group.getName());
	}

	@Test
	public void testNameConstructor() {
		Group group = new Group("magicGroup");
		assertEquals("Group type should be GROUP", TypeOfGroup.GROUP, group.getType());
		assertEquals("Name should be magicGroup", "magicGroup", group.getName());
	}

	@Test
	public void testNameAndTypeConstr() {
		Group group = new Group(TypeOfGroup.CHORD, "magicGroup", 2);
		assertEquals("Group type should be GROUP", TypeOfGroup.CHORD, group.getType());
		assertEquals("Name should be magicGroup", "magicGroup", group.getName());
	}

	@Test
	public void testNoteAdding() {
		Group group1 = new Group(TypeOfGroup.CHORD, "group1", 2);

		List<Note> noteList = new ArrayList<Note>();
		noteList.add(new Note(0));
		noteList.add(new Note(4));
		noteList.add(new Note(7));

		group1.setNotes(noteList);

		List<Note> returnedNoteList = group1.getNotes();
		assertTrue("3 notes", 3 == returnedNoteList.size());
		assertTrue("2nd note is 4", 4 == returnedNoteList.get(1).getHalfStep());

		assertTrue("Note lists should be equal", true == noteList.equals(returnedNoteList));

		List<Note> secondNoteList = new ArrayList<Note>();
		secondNoteList.add(new Note(0));
		secondNoteList.add(new Note(4));
		secondNoteList.add(new Note(7));

		assertTrue("Note lists should be equal", true == secondNoteList.equals(returnedNoteList));
	}

	@Test
	public void testModeSetup() {
		Group group1 = new Group(TypeOfGroup.CHORD, "magicScale", 0);
		List<Note> noteList = new ArrayList<Note>();
		noteList.add(new Note(0));
		noteList.add(new Note(4));
		noteList.add(new Note(7));
		
		group1.setNotes(noteList);
		
		List<Group> myModes = group1.getMyModes();
		
		assertEquals("Should have 3 modes bc theres 3 notes", 3, myModes.size());

		Group mode0 = myModes.get(0);		
		assertEquals("First mode should be group1!", group1, mode0);
		
		Group mode4 = myModes.get(1);
		assertEquals("mode4 should have 3 notes", 3, mode4.getNotes().size());
		assertEquals("mode4 - first note should be 0", 0, mode4.getNotes().get(0).halfStep);
		assertEquals("mode4 - second note should be 0", 3, mode4.getNotes().get(1).halfStep);
		assertEquals("mode4 - third note should be 0", 8, mode4.getNotes().get(2).halfStep);
		
		assertEquals("name should be magicScale", "magicScale", mode4.getName());		
		
		assertEquals("using retrieveOtherMode(...) to access same obj", mode4, group1.retrieveOtherMode(4));
	}

}
