package gui.panels.controllers;

import exercise.Exercise;
import gui.CancelController;
import gui.panels.StudentsCourse;
import gui.panels.UICourseEditable;
import gui.windows.CreateTest;
import gui.windows.EditTest;
import gui.windows.General;
import gui.windows.controllers.EditCourseController;
import gui.windows.controllers.EditTestController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;

import application.Application;
import application.Unit;

/**
 * 
 * @author Blanca, Simon
 * 
 * Controller passed to the button in order to add an Exercise.  Upon execution it calls the CreateTest JFrame,
 * whos ActionController is passed here programmatically. Once the test is created, the Teacher then has to 
 * Edit the Test from the Course UI in order to add questions
 *
 */
public class AddExerciseController implements ActionListener{

	private Unit u;
	private Application app;
	private General gen;
	
	public AddExerciseController(Unit u, Application app, General gen){
		this.u = u;
		this.app = app;
		this.gen = gen;
	}	
		
	@Override
	public void actionPerformed(ActionEvent arg0) {
		final CreateTest ct = new CreateTest();
		ct.setControllerCancel(new CancelController(ct));
		ct.setControllerDone(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Exercise data
				double weight = ct.getWeight();
				Date end = ct.getDateEnd();
				Date start = ct.getDateBegining();
				boolean visibility = ct.getVisibility();
				
				final Exercise e = new Exercise(visibility, start, end, weight);
				u.addTest(e);
				ct.dispose();
				
				UICourseEditable ce = new UICourseEditable(u.getCourse(), app, gen);
				ce.addControllerUnit(new UnitController(app, ce, gen));
				ce.addControllerEditCourse(new EditCourseController(u.getCourse(), gen, app));
				gen.addPanelWest(ce);
				
				StudentsCourse sc = new StudentsCourse(u.getCourse().getRegistered(), u.getCourse().getPending(), u.getCourse().getExpelled());
				gen.addPanelEast(sc);
				
				if(((JButton) arg0.getSource()).getText().equals("Edit")){
					final EditTest et = new EditTest(e, app, gen);
					et.setControllerSave(new EditTestController(e, et));
					et.setControllerDelete(new DeleteTestController(e, app, gen, et));
					et.setControllerCancel(new CancelController(et));
				}
			}
		});
	}	
}

