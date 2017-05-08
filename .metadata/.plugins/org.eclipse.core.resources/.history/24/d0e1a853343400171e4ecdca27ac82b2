package gui.panels.controllers;

import gui.CancelController;
import gui.panels.UICourseEditable;
import gui.windows.CreateUnit;
import gui.windows.General;
import gui.windows.controllers.CreateUnitController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.Application;

/**
 * 
 * @author Blanca, Simon
 *The Main Unit controller for the UICourseEditable View. All other units that are subunits will be nested in here
 *
 */
public class UnitController implements ActionListener{

	private UICourseEditable view;
	private Application model;
	private General gen;
	
	public UnitController(Application model, UICourseEditable view, General gen){
		this.view = view;
		this.model = model;
		this.gen = gen;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		CreateUnit cu = new CreateUnit();
		cu.setControllerCancel(new CancelController(cu));
		cu.setControllerDone(new CreateUnitController(model, view, cu, gen));
	}

}
