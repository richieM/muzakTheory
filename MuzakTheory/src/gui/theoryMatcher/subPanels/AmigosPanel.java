package gui.theoryMatcher.subPanels;

import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import runner.Main;
import structures.Group;
import toolBox.ModeMath;

public class AmigosPanel extends JPanel {
	
	private static final long serialVersionUID = -2053431443889356778L;
	private JTextArea amigosTextArea;
	private JScrollPane scrollPane;
	
	private List<Group> scales;
	private List<Group> chords;
	
	private Logger logger = Main.getLogger();
	
	public AmigosPanel(List<Group> scales, List<Group> chords) {
		amigosTextArea = new JTextArea();
		amigosTextArea.setEditable(false);
		amigosTextArea.setFont(new Font("Arial", Font.BOLD, 16));
		scrollPane = new JScrollPane(amigosTextArea);
		scrollPane.setPreferredSize(new Dimension(1200,1200));
		
		this.scales = scales;
		this.chords = chords;

		ModeMath.findGroupAmigos(scales, chords);
		
		for (Group currentGroup : scales) {
			amigosTextArea.append(currentGroup.fullAutoBiography() + "\n");
		}
		
		this.add(scrollPane);		
	}

}
