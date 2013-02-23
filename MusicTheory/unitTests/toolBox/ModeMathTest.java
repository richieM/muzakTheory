package toolBox;

import static org.junit.Assert.*;
import io.ReadScalesAndChords;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import structures.Group;
import toolBox.ModeMath;

public class ModeMathTest {
	List<Group> scales = new ArrayList<Group>();
	List<Group> chords = new ArrayList<Group>();

	@Before
	public void setup() {
		scales = new ArrayList<Group>();
		chords = new ArrayList<Group>();

		ReadScalesAndChords reader = new ReadScalesAndChords();
		reader.readIn(chords, scales);		
	}

	@Test
	public void testReadScalesAndChordsWorked() {
		assertEquals(17, scales.size());
		assertEquals(12, chords.size());
	}

	@Test 
	public void testFindAmigos() {
		ModeMath.findGroupAmigos(scales, chords);

		assertEquals(8, scales.get(0).getAmigos().size());
		assertEquals(3, scales.get(1).retrieveOtherMode(7).getAmigos().size());
		assertEquals(2, scales.get(1).retrieveOtherMode(9).getAmigos().size());
	}

}
