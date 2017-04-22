package GUI;

import java.awt.*;

import javax.swing.*;

import Application.*;

public class UICourse extends JPanel{

	private static final long serialVersionUID = 1L;

	public UICourse(Course c) {
		JTabbedPane root = new JTabbedPane();
		for(Unit u : c.getUnits()){
			JPanel unit = new JPanel();
			unit.setLayout(new FlowLayout());
			JLabel label = new JLabel(u.toString());
			unit.add(label);
			unit.setVisible(true);
			root.addTab(u.getName(), unit);
		}
		root.setVisible(true);
		add(root);
		setVisible(true);
	}

}
