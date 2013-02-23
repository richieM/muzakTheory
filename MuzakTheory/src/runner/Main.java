package runner;
import gui.theoryMatcher.TheoryMatcher;
import io.ReadScalesAndChords;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import structures.Group;

public class Main implements Runnable, PropertyChangeListener {
	
	private static final Logger logger = Logger.getLogger("Muzak Theory");
	private TheoryMatcher theoryMatcher;

	public static void main(String[] args) {

		UIManager.put("control", new Color(255, 255, 255));
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}

			FileHandler fileHandler = new FileHandler("MuzakTheory.log");
			fileHandler.setFormatter(new SimpleFormatter());
			logger.addHandler(fileHandler);
			logger.setLevel(Level.INFO);
			logger.info("Starting Muzak Theory");

		} catch (Exception e) {
			e.printStackTrace();
			logger.severe(e.getMessage());
		}

		SwingUtilities.invokeLater(new Main());
	}

	

	@Override
	public void run() {
		List<Group> scales = new ArrayList<Group>();
		List<Group> chords = new ArrayList<Group>();

		ReadScalesAndChords reader = new ReadScalesAndChords();
		reader.readIn(chords, scales);

		// this should be run on final page
		//ModeMath.findGroupAmigos(scales, chords);
		
		theoryMatcher = new TheoryMatcher();
		theoryMatcher.initializeGui();
		theoryMatcher.setScales(scales);
		theoryMatcher.setChords(chords);
		
		Thread theoryMatcherThread = new Thread(theoryMatcher, "Theory Matcher");
		theoryMatcherThread.start();
		theoryMatcher.addPropertyChangeListener(this);		
	}



	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
	
	public static Logger getLogger(){
		return logger;
	}

}

//for (Group currentScale : scales) {
//	for (Group currentMode : currentScale.getMyModes()) {
//		System.out.println(currentMode.toString());
//		System.out.println("Amigos:");
//		for (Group currentAmigo : currentMode.getAmigos()) {
//			System.out.println(currentAmigo.toString());
//		}
//		System.out.println();				
//	}
//	System.out.println("*");
//}
//
//for (Group currentChord : chords) {
//	for (Group currentMode : currentChord.getMyModes()) {
//		System.out.println(currentMode.toString());
//		System.out.println("Amigos:");
//		for (Group currentAmigo : currentMode.getAmigos()) {
//			System.out.println(currentAmigo.toString());
//		}
//		System.out.println();	
//	}
//	System.out.println("*");
//}
