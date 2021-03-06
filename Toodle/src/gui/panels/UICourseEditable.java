package gui.panels;

import gui.panels.controllers.CoursesTableController;
import gui.windows.General;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import application.*;



/**
 * 
 * @author Blanca, Simon
 *
 *The User Interface for the teacher. In this case, it is much like the UI for the student, but
 *as the name suggests, it allows to add or edit subunits, exercises and Subunits.
 *
 *Of special Consideration is the fact that the controllers for editing the objects mentioned above
 *are not loaded here, but in UIUnitEditable. This latter class forms the building block for UICourseEditable
 */
public class UICourseEditable extends JPanel{
		
	private JButton addUnit;
	private Course c;
	private JButton editCourse;
	private JButton remove;

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * @param c - the course to be edited
	 * @param app - the application
	 * @param gen - the general frame
	 */
	public UICourseEditable(final Course c, final Application app, final General gen) {
		
		this.c = c;
		
		JTabbedPane root = new JTabbedPane();
		root.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		
		setLayout(new BorderLayout());
		
		for(Unit u : c.getUnits()){
			UIUnitEditable rootUnit = new UIUnitEditable(u, app, gen);
			root.addTab(u.getName(), rootUnit);
		}
		root.setVisible(true);
		
		add(root, BorderLayout.CENTER);
		
		//Title of the course 
		JPanel title = new JPanel(new FlowLayout());
		JLabel course = new JLabel(c.getTitle());
		
		title.add(course);
		//Delete course button
		remove = new JButton("Delete course");
		title.add(remove);
		//Edit course button
		editCourse = new JButton("Edit course");
		title.add(editCourse);
		//Add unit button
		addUnit = new JButton("Add unit");
		title.add(addUnit);
		title.setVisible(true);
		add(title, BorderLayout.NORTH);
		
		setVisible(true);
		
		this.setMaximumSize(new Dimension((int) (gen.getWidth()*0.6), course.getHeight()));
		this.setPreferredSize(new Dimension((int) (gen.getWidth()*0.6), course.getHeight()));
		this.setMinimumSize(new Dimension((int) (gen.getWidth()*0.6), course.getHeight()));
		
		remove.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				app.deleteCourse(c);
				
				List<Course> courses = app.getCourses();
				ArrayList<Integer> rows = new ArrayList<Integer>();
				Object[][] objs = new Object[courses.size()][1];
				
				int i = 0;
				for(Course c: courses){
					objs[i][0] = c.getTitle();
					i += 1;
				}
				
				Courses panel = new Courses(objs, rows);
				panel.setController(new CoursesTableController(app, gen));
				gen.addPanel(panel);
			}
		});
	}
		
	/**
	 * This method adds a controller to the add unit button
	 * @param al - the controller
	 */
	public void addControllerUnit(ActionListener al){
		addUnit.addActionListener(al);
	}
	
	/**
	 * This method adds a controller to the edit course button
	 * @param al - the controller
	 */
	public void addControllerEditCourse(ActionListener al){
		editCourse.addActionListener(al);
	}
	
	/**
	 * This method adds a controller to the delete course button
	 * @param al - the controller
	 */
	public void addControllerDelete(ActionListener al){
		remove.addActionListener(al);
	}
	
	/**
	 * This method returns the course that is being edited
	 * @return the Course edited
	 */
	public Course getCourse(){
		return c;
	}
	
}

