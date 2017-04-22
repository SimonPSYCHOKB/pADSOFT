package GUI;

import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import Application.*;

public class LogInController implements ActionListener{
	
	private Application model;
	private LogIn view;
	
	public LogInController(Application model, LogIn view){
		this.model = model;
		this.view = view;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String name = view.getUserName();
		String password = view.getPassword();
		
		if (model.logIn(name, password) == false){
			JOptionPane.showMessageDialog(view, "Invalid user and password combination.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else {
			view.setVisible(false);
			List<Course> courses = model.getCourses();
			Object[][] objs = new Object[courses.size()][1];
			
			int i = 0;
			for(Course c: courses){
				objs[i][0] = c.getTitle();
				i += 1;
			}
			if(model.getTeacher().getName().equals(name)){
				General frame = new GeneralTeacher();
				frame.addControllerLogOut(new LogOutController(model, frame));
				final Courses course = new Courses(frame, objs);
				frame.addPanel(course);
				course.setController(new MouseAdapter()	{
					public void mouseClicked(MouseEvent e) {
						
						int row = course.getTable().getSelectedRow();
						
						// Solo para ver si funcionaba
						JOptionPane.showMessageDialog(view,row, "Error", JOptionPane.ERROR_MESSAGE);
					}
				});
			}
			else{
				final GeneralStudent frame = new GeneralStudent();
				frame.addControllerGrades(new GradesController(model, frame));
				frame.addControllerLogOut(new LogOutController(model, frame));
				final Courses course = new Courses(frame, objs);
				frame.addPanel(course);
				course.setController(new MouseAdapter()	{
					public void mouseClicked(MouseEvent e) {
						
						int row = course.getTable().getSelectedRow();
						UICourse c = new UICourse(model.getCourses().get(row));
						frame.addPanel(c);
					}
				});
			}
		}
		//System.out.println("HEY");
	}
	
	

}
