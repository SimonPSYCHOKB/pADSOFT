package gui.panels.controllers;

import exercise.Exercise;
import gui.panels.StudentsCourse;
import gui.panels.UICourseEditable;
import gui.windows.EditTest;
import gui.windows.General;
import gui.windows.controllers.EditCourseController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.Application;
import application.Unit;

/**
 * 
 * @author Blanca, Simon
 *
 *A simple controller that permits the deletion of Tests and refreshes the screen
 */
public class DeleteTestController implements ActionListener{
	
	
	private Exercise e;
	private Application app;
	private General gen;
	private EditTest et;

	/**
	 * Constructor
	 * @param e - the exercise that is going to be deleted
	 * @param app - the application
	 * @param gen - the general frame
	 * @param et - the view of the edition of the test
	 */
	public DeleteTestController(Exercise e, Application app, General gen, EditTest et){
		this.e = e;
		this.app = app;
		this.gen = gen;
		this.et = et;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Unit u = e.getUnit();
		u.removeTest(e);
		et.dispose();
		
		UICourseEditable ce = new UICourseEditable(u.getCourse(), app, gen);
		ce.addControllerUnit(new UnitController(app, ce, gen));
		ce.addControllerEditCourse(new EditCourseController(u.getCourse(), gen, app));
		gen.addPanelWest(ce);
		
		StudentsCourse sc = new StudentsCourse(u.getCourse().getRegistered(), u.getCourse().getPending(), u.getCourse().getExpelled());
		gen.addPanelEast(sc);

	}

}
