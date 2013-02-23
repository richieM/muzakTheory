package com.google.gwt.muzaktheorygwt.client;

import java.util.ArrayList;
import java.util.List;

import structures.Group;
import toolBox.ModeMath;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeNode;

public class ResultsPanel {
	private Label panelName = new Label();
	private VerticalPanel panel = new VerticalPanel();
	private TreeGrid treeGrid = new TreeGrid();
	
	private List<Group> chords = new ArrayList<Group>();
	private List<Group> scales = new ArrayList<Group>();
	
	public ResultsPanel(String scaleName) {
		panelName.setText(scaleName);
		
		treeGrid.setWidth(500);  
        treeGrid.setHeight(400);
		
		panel.add(panelName);
		panel.add(treeGrid);		
		panel.setSpacing(10);
	}
	
	public VerticalPanel getPanel() {
		return panel;
	}
	
	public void setScales(List<Group> scales) {
		this.scales = scales;
		ModeMath.findGroupAmigos(this.scales, this.chords);
		updateTree();
	}
	
	public void clear() {
		this.scales.clear();
		this.chords.clear();
		updateTree();
	}
	
	public void updateTree() {
		GWT.log("Scales: " + scales.size());
		GWT.log("Chords: " + chords.size());
		Tree data = new Tree();
		data.setModelType(TreeModelType.CHILDREN);
		
		data.setRoot(new TreeNode("Scales"));
		
		for (Group currentScale : scales) {
			TreeNode currentScaleNode = new TreeNode(currentScale.getName());
			currentScaleNode.setTitle(currentScale.getName() + ": " + currentScale.getNotes().toString());
			
			data.add(currentScaleNode, data.getRoot());
			
			for (Group currentMode : currentScale.getMyModes()) {
				data.add(getModeNode(currentMode), currentScaleNode);
			}
		}
		
		treeGrid.setData(data);
	}

	private TreeNode getModeNode(Group currentMode) {
		TreeNode modeNode = new TreeNode(currentMode.getName());
		modeNode.setTitle(currentMode.getModeValue() + ": " + currentMode.getNotes().toString());
		
		List<TreeNode> amigoNodes = new ArrayList<TreeNode>();
		
		for (Group currentAmigo : currentMode.getAmigos()) {
			TreeNode currentAmigoNode = new TreeNode(currentAmigo.getName());
			currentAmigoNode.setTitle(currentAmigo.getName() + ": " + currentAmigo.getNotes().toString());
			amigoNodes.add(currentAmigoNode);
		}
		
		TreeNode[] nodes = amigoNodes.toArray(new TreeNode[amigoNodes.size()]);
		
		modeNode.setChildren(nodes);
		
		return modeNode;
	}

	public void setChords(List<Group> chords) {
		this.chords = chords;
		ModeMath.findGroupAmigos(this.scales, this.chords);
		updateTree();
	}
	
	// TODO table or what?!!?
}
