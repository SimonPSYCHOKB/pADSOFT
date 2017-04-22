package GUI;

import java.awt.*;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Courses extends JPanel{

	private static final long serialVersionUID = 1L;
	JTable cs;
	General gn;

	public Courses(General gn, Object[][] courses) {
		this.gn = gn;
		this.setLayout(new BorderLayout());
		
		JPanel top = new JPanel();
		SpringLayout layout = new SpringLayout();
		top.setLayout(layout);
		JLabel label = new JLabel("COURSES");
		label.setMaximumSize(new Dimension(10,50));
		layout.putConstraint(SpringLayout.WEST, label, 50, SpringLayout.WEST, top);
		layout.putConstraint(SpringLayout.NORTH, label, 35, SpringLayout.NORTH, top);
		
		top.add(label);
		top.setPreferredSize(new Dimension(250,50));
		top.setVisible(true);
		
		this.add(BorderLayout.NORTH, top);
		
		String[] title = {"Title"};
		cs = new JTable(courses, title);
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
}
