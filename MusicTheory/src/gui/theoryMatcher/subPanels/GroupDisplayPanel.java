package gui.theoryMatcher.subPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.AbstractTableModel;

import runner.Main;
import structures.Group;

public class GroupDisplayPanel extends JPanel {

	private static final long serialVersionUID = 4087939924707123528L;
	private String panelName;
	
	private JScrollPane scrollPane;
	private JTable groupTable;
	private GroupTableModel groupTableModel;
	private Logger logger = Main.getLogger();
	
	public GroupDisplayPanel(String name) {
		this.setLayout(new BorderLayout());
		this.panelName = name;
		JLabel titleLabel = new JLabel(panelName);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 26));		
		this.add(titleLabel, BorderLayout.NORTH);
		
		groupTableModel = new GroupTableModel();
		groupTable = new JTable(groupTableModel);
		groupTable.setAutoscrolls(true);
		
		scrollPane = new JScrollPane(groupTable);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		scrollPane.setPreferredSize(new Dimension(700, 400));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setAutoscrolls(true);
		
		this.add(scrollPane, BorderLayout.SOUTH);		
	}
	
	public void updateTable(List<Group> groups) {
		groupTableModel.updateGroupData(groups);
		groupTable.repaint();
		scrollPane.revalidate();
	}
	
	public List<Group> getSelectedGroups() {
		logger.info("Retrieving list of selected groups in " + panelName);
		
		int[] selectedRows = groupTable.getSelectedRows();
		List<Group> selectedGroups = new ArrayList<Group>();

		for (int i = 0 ; i < selectedRows.length ; i++ ) {
			Group currentGroup = groupTableModel.getGroupAtGivenRow(selectedRows[i]);
			currentGroup.clearAmigos();
			
			selectedGroups.add(currentGroup);
		}
		
		return selectedGroups;
	}

	@SuppressWarnings("serial")
	class GroupTableModel extends AbstractTableModel {
		private List<String> columnNames = Arrays.asList("Name", "Notes");
		private List<Group> groups = new ArrayList<Group>();
		
		public void updateGroupData(List<Group> newGroupData) {
			this.groups = newGroupData;
			// TODO sorting?
		}

		@Override
		public int getColumnCount() {
			return columnNames.size();
		}

		@Override
		public int getRowCount() {
			return groups.size();
		}
		
		@Override
		public String getColumnName(int col) {
			return columnNames.get(col);
		}

		@Override
		public Object getValueAt(int row, int col) {
			Group currentGroup = groups.get(row);
			
			String columnNameRequested = columnNames.get(col);
			
			if (columnNameRequested == "Name") {
				return currentGroup.getName();
			} else if (columnNameRequested == "Notes") {
				return currentGroup.getNotes().toString();
			}
			
			return "";
		}
		
		public Group getGroupAtGivenRow(int row) {
			return groups.get(row);
		}
		
	}
}
