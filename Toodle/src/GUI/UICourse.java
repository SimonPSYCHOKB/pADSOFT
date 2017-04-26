package GUI;



import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import Test.Exercise;

import Application.*;

public class UICourse extends JPanel{
	
	ArrayList<JPanel> units;

	private static final long serialVersionUID = 1L;

	public UICourse(Course c, final Application app) {
		
		units = new ArrayList<JPanel>();
		
		JTabbedPane root = new JTabbedPane();
		root.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		
		setLayout(new BorderLayout());
		
		for(Unit u : c.getUnits()){
			if(u.isVisibility() == false) continue;
			JPanel rootUnit = new JPanel();
			rootUnit.setLayout(new BorderLayout());
			final JPanel unit = new JPanel();
			SpringLayout layout = new SpringLayout();
			unit.setLayout(layout);
			
			JComponent previous = unit;
			for(Note n : u.getNotes()){
				if(n.isVisibility() == false) continue;
				JLabel note = new JLabel(n.toString());
				layout.putConstraint(SpringLayout.NORTH, note, 20, SpringLayout.NORTH, previous);
				layout.putConstraint(SpringLayout.WEST, note, 20, SpringLayout.WEST, unit);
				unit.add(note);
				previous = note;
			}
			int i = 0;
			for(final Exercise e : u.getTests()){
				if(e.isVisibility() == false) continue;
				JLabel test = new JLabel("Exercise " + i);
				test.addMouseListener(new MouseAdapter()	{
					public void mouseClicked(MouseEvent me) {
						new Test(e, app);
					}
				});
				layout.putConstraint(SpringLayout.NORTH, test, 20, SpringLayout.NORTH, previous);
				layout.putConstraint(SpringLayout.WEST, test, 20, SpringLayout.WEST, unit);
				unit.add(test);
				i += 1;
				previous = test;
			}
			
			for(Unit ss : u.getSubUnits()){
				if(ss.isVisibility() == false) continue;
				JPanel rootSub = new JPanel();
				final JPanel sub = new JPanel();
				sub.setBorder(BorderFactory.createTitledBorder(ss.getName()));
				SpringLayout subL = new SpringLayout();
				sub.setLayout(subL);
				
				JComponent before = sub;
				for(Note n : ss.getNotes()){
					if(n.isVisibility() == false) continue;
					JLabel note = new JLabel(n.toString());
					subL.putConstraint(SpringLayout.NORTH, note, 20, SpringLayout.NORTH, before);
					subL.putConstraint(SpringLayout.WEST, note, 20, SpringLayout.WEST, sub);
					sub.add(note);
					before = note;
				}
				i = 0;
				for(final Exercise e : ss.getTests()){
					if(e.isVisibility() == false) continue;
					JLabel test = new JLabel("Exercise " + i);
					test.addMouseListener(new MouseAdapter()	{
						public void mouseClicked(MouseEvent me) {
							new Test(e, app);
						}
					});
					subL.putConstraint(SpringLayout.NORTH, test, 20, SpringLayout.NORTH, before);
					subL.putConstraint(SpringLayout.WEST, test, 20, SpringLayout.WEST, sub);
					sub.add(test);
					i += 1;
					before = test;
				}
				
				sub.setVisible(true);
				sub.setPreferredSize(new Dimension(700, 100));
				rootSub.setVisible(true);
				rootSub.setPreferredSize(new Dimension(800, 200));
				layout.putConstraint(SpringLayout.NORTH, rootSub, 20, SpringLayout.NORTH, previous);
				layout.putConstraint(SpringLayout.WEST, rootSub, 20, SpringLayout.WEST, unit);
				unit.add(rootSub);
				rootSub.add(sub, BorderLayout.CENTER);
				units.add(rootSub);
			}
			rootUnit.setVisible(true);
			unit.setVisible(true);
			rootUnit.add(unit, BorderLayout.CENTER);
			units.add(rootUnit);
			root.addTab(u.getName(), rootUnit);
		}
		root.setVisible(true);
		root.setPreferredSize(new Dimension(1000, 800));
		add(root, BorderLayout.CENTER);
		setVisible(true);
	}
	
}
