package GUI;

import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class Courses extends JPanel{

	private static final long serialVersionUID = 1L;
	JTable cs;
	General gn;
	SpringLayout layout;
	JPanel top;
	JLabel label;

	public Courses(General gn, Object[][] courses, final ArrayList<Integer> rows) {
		this.gn = gn;
		this.setLayout(new BorderLayout());
		
		top = new JPanel();
		layout = new SpringLayout();
		top.setLayout(layout);
		label = new JLabel("COURSES");
		label.setMaximumSize(new Dimension(10,50));
		layout.putConstraint(SpringLayout.WEST, label, 50, SpringLayout.WEST, top);
		layout.putConstraint(SpringLayout.NORTH, label, 15, SpringLayout.NORTH, top);
		
		top.add(label);
		top.setPreferredSize(new Dimension(250,50));
		top.setVisible(true);
		
		this.add(BorderLayout.NORTH, top);
		
		String[] title = {"Title"};
//		cs = new JTable(courses, title);
		DefaultTableModel tableModel = new DefaultTableModel(courses, title) {

			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
//		cs.setModel(tableModel);
		cs = new JTable(tableModel){
			private static final long serialVersionUID = 1L;
			
			    public Component prepareRenderer(TableCellRenderer renderer, int Index_row, int Index_col) {
			    	Component comp = super.prepareRenderer(renderer, Index_row, Index_col);
			        if (rows.contains(Index_row)) {
			            comp.setBackground(Color.lightGray);
			        } else {
			            comp.setBackground(Color.white);
			        }
			        return comp;
			    }
			
		};
		cs.setTableHeader(null);

		JScrollPane scrollBar =	new	JScrollPane(cs);
		
		this.add(BorderLayout.CENTER, scrollBar);		
		
		this.setVisible(true);
	}
	
	public void setController(MouseListener al){
		cs.addMouseListener(al);
	}
	
	public JTable getTable(){
		return cs;
	}
	
	public General getGeneral(){
		return gn;
	}
	
	public void addButtonTop(JButton button){
		layout.putConstraint(SpringLayout.WEST, button, 150, SpringLayout.EAST, label);
		layout.putConstraint(SpringLayout.NORTH, button, 10, SpringLayout.NORTH, top);
		top.add(button);
		SwingUtilities.updateComponentTreeUI(this);
	}
}
