package gui.windows.controllers;

import gui.windows.General;
import gui.windows.LogIn;

import java.awt.event.*;

import application.*;


/**
 * This class makes reference to the controller of the log out option
 * in the main menu bar.
 * 
 * @author Simon Valcarcel
 * @author Blanca Martin
 *
 */
public class LogOutController implements ActionListener{
	
	private Application model;
	private General view;
	
	/**
	 * Constructor
	 * @param model - the application
	 * @param view - the general frame
	 */
	public LogOutController(Application model, General view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//Log out the application and save the data
		model.logOut();
		model.saveApplication();
		view.dispose();
		//New log in window
		LogIn lg = new LogIn();
		ActionListener lic = new LogInController(Application.getApplication(), lg);
		lg.setController(lic);
	}

}
