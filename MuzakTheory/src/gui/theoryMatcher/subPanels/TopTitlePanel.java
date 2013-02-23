package gui.theoryMatcher.subPanels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TopTitlePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 548676777318312685L;
	private String title = "Theory Matcher";
	JButton optionsButton;
	
	public TopTitlePanel() {
		initializeComponents();
	}

	private void initializeComponents() {
		JLabel titleLabel = new JLabel(title);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
		this.add(titleLabel);
		
		optionsButton = new JButton("Options");
		optionsButton.addActionListener(this);
		optionsButton.setFont(new Font("Arial", Font.BOLD, 20));
		this.add(optionsButton);		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String actionCommand = event.getActionCommand();
		
		if (actionCommand.contains("Options")) {
			this.firePropertyChange("Options", null, null);
			optionsButton.setFont(new Font("Arial", Font.BOLD, 12));
			optionsButton.setText("button no workey yet");			
		}
		
	}

}
