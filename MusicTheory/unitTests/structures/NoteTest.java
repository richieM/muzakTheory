package structures;

import static org.junit.Assert.*;

import org.junit.Test;

public class NoteTest {
	
	@Test
	public void testConstructor() {
		Note note = new Note(4);
		assertEquals("note halfStep should be 4", 4, note.getHalfStep());
	}
	
	@Test
	public void testCompareToMatches() {
		Note note1 = new Note(4);
		Note note2 = new Note(4);
		
		assertTrue("compareTo() should return 0", 0 == note1.compareTo(note2));
		assertTrue("compareTo() should return 0 -- both ways", 0 == note2.compareTo(note1));
		assertTrue("compareTo() should return 0 -- self", 0 == note2.compareTo(note2));
	}
	
	@Test
	public void testCompareToFails() {
		Note note4 = new Note(4);
		Note note5 = new Note(5);
		
		assertTrue("compareTo() should return negative value", note4.compareTo(note5) < 0);
		assertTrue("compareTo() should return poz value", note5.compareTo(note4) > 0);
	}
	
	@Test
	public void testEquals() {
		Note note0 = new Note(0);
		Note note1 = new Note(1);
		Note note0also = new Note(0);
		
		assertEquals("equals() should return true -- self", true, note0.equals(note0));
		assertEquals("equals() should return true -- same halfStep value", true, note0.equals(note0also));
		assertEquals("equals() should return false -- diff halfStep value", false, note0.equals(note1));
	}

}
