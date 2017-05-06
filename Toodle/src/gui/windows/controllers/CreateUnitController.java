package gui.windows.controllers;

import gui.panels.StudentsCourse;
import gui.panels.UICourseEditable;
import gui.panels.controllers.UnitController;
import gui.windows.CreateUnit;
import gui.windows.General;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.Application;
import application.Unit;


public class CreateUnitController implements ActionListener{

	private Application model;
	private UICourseEditable view;
	private CreateUnit frame;
	private General gen;
	
	public CreateUnitController(Application model, UICourseEditable view, CreateUnit frame, General gen){
		this.model = model;
		this.view = view;
		this.frame = frame;
		this.gen = gen;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		view.getCourse().addUnit(new Unit(frame.getVisibility().isSelected(), frame.getNameField().getText()));
		UICourseEditable ce = new UICourseEditable(view.getCourse(), model, gen); 
		ce.addControllerUnit(new UnitController(model, ce, gen));
		ce.addControllerEditCourse(new EditCourseController(view.getCourse(), gen, model));
		gen.addPanelWest(ce);
		
		StudentsCourse sc = new StudentsCourse(view.getCourse().getRegistered(), view.getCourse().getPending(), view.getCourse().getExpelled());
		gen.addPanelEast(sc);
		
		frame.dispose();
	}
}
