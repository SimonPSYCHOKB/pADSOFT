package GUI;



import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import Test.Exercise;

import Application.*;

public class UICourse extends JPanel{

	private static final long serialVersionUID = 1L;

	public UICourse(Course c) {
		JTabbedPane root = new JTabbedPane();
		root.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		for(Unit u : c.getUnits()){
			JPanel unit = new JPanel();
			unit.setLayout(new BoxLayout(unit, BoxLayout.PAGE_AXIS));
			
			for(Note n : u.getNotes()){
				JLabel note = new JLabel(n.toString());
				unit.add(note);
			}
			for(Exercise e : u.getTests()){
				JLabel test = new JLabel(e.toString());
				unit.add(test);
			}
			for(Unit ss : u.getSubUnits()){
				JLabel subUnit = new JLabel(ss.getName());
				unit.add(subUnit);
				
				for(Note n : u.getNotes()){
					JLabel note = new JLabel(n.toString());
					unit.add(note);
				}
				int i = 0;
				for(final Exercise e : u.getTests()){
					JLabel test = new JLabel("Exercise " + i);
					test.addMouseListener(new MouseAdapter()	{
						public void mouseClicked(MouseEvent me) {
							new Test(e);
						}
					});
					unit.add(test);
					i += 1;
				}
			}
			
//			JLabel label = new JLabel(u.toString());
//			
//			unit.add(label);
			unit.setVisible(true);
			
			root.addTab(u.getName(), unit);
		}
		root.setVisible(true);
		root.setPreferredSize(new Dimension(1000, 800));
		add(root);
		setVisible(true);
	}

}
