package gui.amigoFinder;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTree;

import structures.Group;

public class AmigoTree extends JTree implements PropertyChangeListener {

	AmigoTreeModel treeModel;
	
	public AmigoTree(Group root) {
		super (new AmigoTreeModel(root));
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
	
}
