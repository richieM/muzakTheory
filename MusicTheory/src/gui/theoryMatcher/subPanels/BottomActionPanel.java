package gui.theoryMatcher.subPanels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BottomActionPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 5502869927141345258L;

	public BottomActionPanel() {
		//this.add(Box.createRigidArea(new Dimension(80,120)));
		
		JButton runButton = new JButton("Find Amigos");
		runButton.addActionListener(this);
		runButton.setFont(new Font("Arial", Font.BOLD, 28));
		this.add(runButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String actionCommand = event.getActionCommand();
		
		if (actionCommand.contains("Find Amigos")) {
			this.firePropertyChange("Find Amigos", null, null);
		}
		
	}

}
