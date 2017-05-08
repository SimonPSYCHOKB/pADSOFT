package gui.panels.controllers;

import gui.CancelController;
import gui.windows.CreateUnit;
import gui.windows.General;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.Application;
import application.Unit;


/**
 * 
 * @author Blanca, Simon
 *
 *With this controller, one can Add a subunit to a UNit. It is linked to the button of the 
 *same name in UICourseEditable
 */
public class AddSubunitController implements ActionListener{

	private Unit u;
	private Application app;
	private General gen;
		
	public AddSubunitController(Unit u, Application app, General gen){
		this.u = u;
		this.app = app;
		this.gen = gen;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		CreateUnit cu = new CreateUnit();
		cu.setControllerCancel(new CancelController(cu));
		cu.setControllerDone(new DoneAddSubunitController(u, cu, app, gen));
	}


}
