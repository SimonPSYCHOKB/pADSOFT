package gui.panels;

import exercise.Exercise;
import gui.panels.controllers.AddExerciseController;
import gui.panels.controllers.AddNotesController;
import gui.panels.controllers.AddSubunitController;
import gui.panels.controllers.ExerciseController;
import gui.panels.controllers.NotesController;
import gui.windows.General;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import application.Application;
import application.Course;
import application.Note;
import application.Unit;


/**
 * 
 * @author Blanca, Simon
 * 
 * The basic building block of UICourseEditable. Allows to add Notes, Exercises 
 * and Subunits. The controllers for these actions are loaded in this class
 *
 */
public class UIUnitEditable extends JScrollPane{

	private static final long serialVersionUID = 1L;
	private JButton exercise;
	private JButton subunit;
	private JButton notes;
	private JButton remove;
	private ArrayList<UIUnitEditable> units;

	public UIUnitEditable(final Unit u, final Application app, final General gen){
		
		units = new ArrayList<UIUnitEditable>();
				
		//Layout for the main panel
		JPanel root = new JPanel();
		root.setLayout(new BorderLayout());
		
		JPanel unit = new JPanel();
		unit.setLayout(new BoxLayout(unit, BoxLayout.PAGE_AXIS));
		
		for(Note n : u.getNotes()){
			JTextArea note = new JTextArea(n.toString());
			note.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			note.setEditable(false);
			note.addMouseListener(new NotesController(n, gen, app));
		
//			unit.add(Box.createGlue());
			unit.add(Box.createRigidArea(new Dimension(0,50)));
			unit.add(note);
		}
		int i = 0;
		for(Exercise e : u.getTests()){
			JLabel test = new JLabel("Exercise " + i);
			Font font = test.getFont();
			Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			test.setFont(font.deriveFont(attributes));
			test.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			test.addMouseListener(new ExerciseController(app, e, gen));
			
//			unit.add(Box.createGlue());
			unit.add(Box.createRigidArea(new Dimension(0,50)));
			unit.add(test);
			i += 1;
		}
	
		for(Unit ss : u.getSubUnits()){
			UIUnitEditable sube = new UIUnitEditable(ss, app, gen);
			sube.setBorder(BorderFactory.createTitledBorder(ss.getName()));
			
//			unit.add(Box.createGlue());
			unit.add(Box.createRigidArea(new Dimension(0,50)));
			unit.add(sube);
			
			units.add(sube);
		}
		
		//Buttons for editing
		JPanel editing = new JPanel();
		editing.setLayout(new FlowLayout(FlowLayout.RIGHT));
		exercise = new JButton("Create new exercise");
		subunit = new JButton("Create new subunit");
		notes = new JButton("Create new notes");
		editing.add(exercise);
		editing.add(subunit);
		editing.add(notes);
		editing.setVisible(true);
		
		exercise.addActionListener(new AddExerciseController(u, app, gen));
		subunit.addActionListener(new AddSubunitController(u, app, gen));
		notes.addActionListener(new AddNotesController(u, app, gen));

		
		//Button for deleting the unit
		JPanel removing = new JPanel(new FlowLayout(FlowLayout.LEFT));
		remove = new JButton("Delete unit");
		removing.add(remove);
		
		remove.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Course course = u.getCourse();
				course.deleteUnit(u);

				UICourseEditable ce = new UICourseEditable(course, app, gen);
				gen.addPanelWest(ce);
				
				StudentsCourse sc = new StudentsCourse(course.getRegistered(), course.getPending(), course.getExpelled());
				gen.addPanelEast(sc);
			}
		});
		
		//Panel with all the buttons
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.add(removing);
		buttons.add(editing);
		
		root.add(buttons, BorderLayout.NORTH);
		root.add(unit, BorderLayout.CENTER);
		
		root.setVisible(true);
		
		setViewportView(root);
		setPreferredSize(new Dimension(1000, 600));
		setBorder(BorderFactory.createEmptyBorder());
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		units.add(this);
		

	}
	

}
