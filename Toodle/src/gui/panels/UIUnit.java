package gui.panels;

import exercise.Exercise;
import gui.panels.controllers.ExerciseController;
import gui.windows.General;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import application.Application;
import application.Note;
import application.Unit;


/**
 * 
 * @author Blanca, Simon
 * 
 * The scroll panel for a unit. These can be nested for subunits. They are the basic building blocks of
 * a Course UI. It displays:
 * 		-Notes
 * 		-Tests
 * 		-Subunits
 *
 */
public class UIUnit extends JScrollPane{

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * @param u - the unit we want to display
	 * @param app - the application 
	 * @param gen - the general view
	 */
	public UIUnit(final Unit u, final Application app, final General gen){
		
		//Layout for the main panel
		JPanel root = new JPanel();
		root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
		
		JPanel unit = new JPanel();
		unit.setLayout(new BoxLayout(unit, BoxLayout.PAGE_AXIS));
		
		for(Note n : u.getNotes()){
			if(n.isVisibility() == false) continue;
			JTextArea note = new JTextArea(n.toString());
			
			//Controllers for the course
		    note.setEditable(false);
			note.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			unit.add(Box.createRigidArea(new Dimension(0,50)));
			unit.add(note);
		}
		int i = 0;
		for(Exercise e : u.getTests()){
			if(e.isVisibility() == false) continue;
			JLabel test;
			if(e.getDateOfBegining().before(new Date())){
				if(e.getDateOfEnd().before(new Date()))
					test = new JLabel("View exercise " + i);
				else
					test = new JLabel("Exercise " + i + " available until " + e.getDateOfEnd());
			}
			else
				test = new JLabel("Exercise " + i + " available the " + e.getDateOfBegining());
			Font font = test.getFont();
			Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			
			
			//Controllers for the test 
			test.setFont(font.deriveFont(attributes));
			test.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			test.addMouseListener(new ExerciseController(app, e, gen));

			unit.add(Box.createRigidArea(new Dimension(0,50)));
			unit.add(test);
			i += 1;
		}
	
		for(Unit ss : u.getSubUnits()){
			if(ss.isVisibility() == false) continue;
			UIUnit sube = new UIUnit(ss, app, gen);
			sube.setBorder(BorderFactory.createTitledBorder(ss.getName()));
			unit.add(Box.createRigidArea(new Dimension(0,50)));
			unit.add(sube);
		}
		
		unit.setVisible(true);

        root.add(unit);

		root.setVisible(true);
		
		this.setViewportView(root);
		setPreferredSize(new Dimension(1000, 600));
		setBorder(BorderFactory.createEmptyBorder());
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		

	}
}
