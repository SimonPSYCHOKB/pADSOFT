package GUI;

import java.awt.event.*;
import java.util.List;

import javax.swing.*;

import Application.*;
import Users.*;

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
				frame.addControllerCourses(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						frame.addPanel(course);
					}
				});
				course.setController(new MouseAdapter()	{
					public void mouseClicked(MouseEvent e) {
						
						int row = course.getTable().getSelectedRow();
						final Course selected = model.getCourses().get(row);
						if(((Student) model.getCurrentUser()).getRegisteredCourses().contains(selected)){				
							UICourse c = new UICourse(selected, model);
							frame.addPanel(c);
						}
						else if(((Student) model.getCurrentUser()).getPendingCourses().contains(selected))
							JOptionPane.showMessageDialog(view,"Your application for " + selected.getTitle() + " is still pending.", "Registration", JOptionPane.INFORMATION_MESSAGE);
						else{
							final Register register = new Register("You are not registered in " + selected.getTitle() + ". What would you like to do?");
							register.setControllerOK(new ActionListener(){

								@Override
								public void actionPerformed(ActionEvent arg0) {
									model.applyStudent((Student) model.getCurrentUser(), selected);	
									JOptionPane.showMessageDialog(view,"You have applied for " + selected.getTitle(), "Registration", JOptionPane.INFORMATION_MESSAGE);
									register.dispose();
								}
								
							});
							register.setControllerCancel(new ActionListener(){

								@Override
								public void actionPerformed(ActionEvent arg0) {
									register.dispose();
									
								}
								
							});
						}
					}
				});
			}
		}
		//System.out.println("HEY");
	}
	
	

}
