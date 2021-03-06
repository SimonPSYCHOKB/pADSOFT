package gui.windows.controllers;

import gui.panels.Courses;
import gui.panels.controllers.CoursesTableController;
import gui.windows.GeneralStudent;
import gui.windows.GeneralTeacher;
import gui.windows.LogIn;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import users.Student;

import application.*;

/**
 * 
 * @author Blanca , Simon
 * 
 * Controller for the LogIn JFrame. If the Login is correct, makes the login window invisible 
 * and sets up the main view for the Application using a General Frame, which then is loaded 
 * with the relevant controllers
 * 
 * For Student, it loads:
 * 		-Statistics Controller
 * 		-LogOutController
 * 		-Courses Controller
 * 		-Courses Table Controller
 * 
 * Additionally, for the teacher, it loads:
 * 		-StudentsController
 * 		-CreateCourseController
 *
 */

public class LogInController implements ActionListener{
	
	private Application model;
	private LogIn view;
	
	/**
	 * Constructor
	 * @param model - the application
	 * @param view - the log in window
	 */
	public LogInController(Application model, LogIn view){
		this.model = model;
		this.view = view;
	}
	
	/**
	 * This function loads all the relevant controllers, depending on whether the current user is a 
	 * Teacher or a Student.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String name = view.getUserName();
		String password = view.getPassword();
		
		//We validate the password
		if (model.logIn(name, password) == false){
			JOptionPane.showMessageDialog(view, "Invalid user and password combination.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else {
			view.setVisible(false);
			List<Course> courses = model.getCourses();
			
			ArrayList<Integer> rows = new ArrayList<Integer>();
			//If the current user is the teacher
			if(model.getTeacher().getName().equals(name)){
				Object[][] objs = new Object[courses.size()][1];
				
				int i = 0;
				for(Course c: courses){
					objs[i][0] = c.getTitle();
					i += 1;
				}
				
				//Principal window
				GeneralTeacher frame = new GeneralTeacher();
				frame.addControllerLogOut(new LogOutController(model, frame));
				frame.addControllerCourses(new CoursesController(model, frame));
				frame.addControllerStats(new StatsController(frame, model));
				frame.addControllerStudents(new StudentsController(frame, model));
				
				//Panel with the list of courses
				Courses course = new Courses(objs, rows);
				frame.addPanel(course);
				course.setController(new CoursesTableController(model, frame));
				
				//Panel with the student of the course
				
				//Button for creating a new course
				JButton create = new JButton("Create new Course");
				create.addActionListener(new CreateCourseController(model, frame));
				course.addButtonTop(create);
			}
			// If the current user is a student
			else{
				//Principal window
				GeneralStudent frame = new GeneralStudent();
				frame.addControllerGrades(new StatsController(frame, model));
				frame.addControllerLogOut(new LogOutController(model, frame));
				
				List<Course> registered = ((Student) model.getCurrentUser()).getRegisteredCourses();
				System.out.println(registered.size());
				List<Course> visible = new ArrayList<Course>();
				int i = 0;
				for(Course c: courses){						
					if(c.isVisibility()){
						if(registered.contains(c)){
							rows.add(i);
						}
						visible.add(c);
						i += 1;
					}
				}
				
				Object[][] objs = new Object[visible.size()][1];
				i = 0;
				for(Course c : visible){
					objs[i][0] = c.getTitle();
					i = i + 1;
				}
				//Panel with the visible courses
				Courses course = new Courses(objs, rows); 
				frame.addPanel(course);
				frame.addControllerCourses(new CoursesController(model, frame));
				course.setController(new CoursesTableController(model, frame));
			}
		}
	}
	
	

}
