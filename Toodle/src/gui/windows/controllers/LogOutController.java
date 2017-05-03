package gui.windows.controllers;

import gui.windows.General;
import gui.windows.LogIn;

import java.awt.event.*;

import application.*;


public class LogOutController implements ActionListener{
	
	private Application model;
	private General view;
	
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
