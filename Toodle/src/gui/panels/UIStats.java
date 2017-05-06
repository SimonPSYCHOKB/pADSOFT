package gui.panels;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

public class UIStats extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JTable course;
	private JScrollPane scrollBar;
	private SpringLayout layout;
	
	public UIStats(Object[][] objs, String[] titles){
		
		
		course = new JTable(objs, titles);
		DefaultTableModel tableModel = new DefaultTableModel(objs, titles){
			
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		course.setModel(tableModel);
		course.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		course.setVisible(true);
		
		scrollBar = new JScrollPane(course);
		
		layout = new SpringLayout();
		setLayout(layout);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollBar, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, scrollBar, 50, SpringLayout.NORTH, this);		
		
		add(scrollBar);
		setPreferredSize(new Dimension(600, 500));
		
	}
	
	public void addController(MouseAdapter ma){
		course.addMouseListener(ma);
	}
	
	public void addButton(JButton back){
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, back, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, back, 50, SpringLayout.SOUTH, scrollBar);
		
		add(back);
	}

}
