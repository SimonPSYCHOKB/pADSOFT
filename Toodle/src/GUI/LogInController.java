package GUI;

import java.awt.event.*;
import javax.swing.*;

import Application.Application;

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
			if(model.getTeacher().getName().equals(name)){
				General frame = new GeneralTeacher();
				frame.addControllerLogOut(new LogOutController(model, frame));
			}
			else{
				GeneralStudent frame = new GeneralStudent();
				frame.addControllerGrades(new GradesController(model, frame));
				frame.addControllerLogOut(new LogOutController(model, frame));
			}
		}
		//System.out.println("HEY");
	}
	
	

}