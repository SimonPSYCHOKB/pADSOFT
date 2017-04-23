package GUI;



import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Test.Exercise;

import Application.*;

public class UICourse extends JPanel{

	private static final long serialVersionUID = 1L;

	public UICourse(Course c, final Application app) {
		
		JTabbedPane root = new JTabbedPane();
		root.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		
		setLayout(new BorderLayout());
		
		for(Unit u : c.getUnits()){
			final JPanel unit = new JPanel();
			SpringLayout layout = new SpringLayout();
			unit.setLayout(layout);
			
			JComponent previous = unit;
			for(Note n : u.getNotes()){
				JLabel note = new JLabel(n.toString());
				layout.putConstraint(SpringLayout.NORTH, note, 20, SpringLayout.NORTH, previous);
				layout.putConstraint(SpringLayout.WEST, note, 20, SpringLayout.WEST, unit);
				unit.add(note);
				previous = note;
			}
			int i = 0;
			for(final Exercise e : u.getTests()){
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
				final JPanel sub = new JPanel();
				sub.setBorder(BorderFactory.createTitledBorder(ss.getName()));
				SpringLayout subL = new SpringLayout();
				sub.setLayout(subL);
				
				JComponent before = sub;
				for(Note n : ss.getNotes()){
					JLabel note = new JLabel(n.toString());
					subL.putConstraint(SpringLayout.NORTH, note, 20, SpringLayout.NORTH, before);
					subL.putConstraint(SpringLayout.WEST, note, 20, SpringLayout.WEST, sub);
					sub.add(note);
					before = note;
				}
				i = 0;
				for(final Exercise e : ss.getTests()){
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
				
				layout.putConstraint(SpringLayout.NORTH, sub, 20, SpringLayout.NORTH, previous);
				layout.putConstraint(SpringLayout.WEST, sub, 20, SpringLayout.WEST, unit);
				sub.setVisible(true);
				sub.setPreferredSize(new Dimension(800, 200));
				unit.add(sub);
			}
			
			unit.setVisible(true);
			root.addTab(u.getName(), unit);
		}
		root.setVisible(true);
		root.setPreferredSize(new Dimension(1000, 800));
		add(root, BorderLayout.CENTER);
		setVisible(true);
	}
	
	public void addMenuBar(JMenuBar bar){
		addMenuBar(bar);
	}
	
	

}
