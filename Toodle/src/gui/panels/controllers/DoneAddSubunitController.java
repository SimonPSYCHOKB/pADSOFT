package gui.panels.controllers;

import gui.panels.StudentsCourse;
import gui.panels.UICourseEditable;
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
 *A simple controller called when a Subunit has been added in order to properly refresh the screen
 */
public class DoneAddSubunitController implements ActionListener{
	
	private Unit unit;
	private CreateUnit view;
	private Application app;
	private General gen;
	
	/**
	 * Constructor
	 * @param unit - the unit in which the subunit is added
	 * @param view - the window for the creation of a subunit
	 * @param app - the application
	 * @param gen - the general frame
	 */
	public DoneAddSubunitController(Unit unit, CreateUnit view, Application app, General gen){
		this.unit = unit;
		this.view = view;
		this.app = app;
		this.gen = gen;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		unit.createSubSection(new Unit(view.getVisibility().isSelected(), view.getNameField().getText()));
		view.dispose();
		
		
		gen.invalidate();
		
		UICourseEditable ce = new UICourseEditable(unit.getCourse(), app, gen);
		//ce.addControllerEditCourse(new EditCourseController(unit.getCourse(), gen, app));
		//ce.addControllerUnit(new UnitController(app, ce, gen));
		gen.addPanelWest(ce);
		
		StudentsCourse sc = new StudentsCourse(unit.getCourse().getRegistered(), unit.getCourse().getPending(), unit.getCourse().getExpelled());
		gen.addPanelEast(sc);
		
		
		
	}

}
