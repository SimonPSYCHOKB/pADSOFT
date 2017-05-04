package gui.panels;


import gui.windows.General;

import java.awt.*;

import javax.swing.*;


import application.*;



public class UICourse extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public UICourse(Course c, final Application app, General gen) {
				
		JTabbedPane root = new JTabbedPane();
		root.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		
		setLayout(new BorderLayout());
		
		for(Unit u : c.getUnits()){
			if(u.isVisibility() == false) continue;
			UIUnit rootUnit = new UIUnit(u, app, gen);
			root.addTab(u.getName(), rootUnit);
		}
		root.setVisible(true);
		root.setPreferredSize(new Dimension(1000, 800));
		add(root, BorderLayout.CENTER);
		
		JPanel title = new JPanel(new FlowLayout());
		JLabel course = new JLabel(c.getTitle());
		course.setPreferredSize(new Dimension(100, 50));
		title.add(course);
		title.setVisible(true);
		add(title, BorderLayout.NORTH);
		
		setVisible(true);
	}
}
