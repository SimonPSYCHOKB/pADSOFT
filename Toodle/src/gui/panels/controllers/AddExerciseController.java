package gui.panels.controllers;

import exercise.Exercise;
import gui.CancelController;
import gui.panels.UICourseEditable;
import gui.windows.CreateTest;
import gui.windows.EditTest;
import gui.windows.General;
import gui.windows.controllers.EditCourseController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;


import application.Application;
import application.Unit;


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
				gen.addPanel(ce);
				
				if(((JButton) arg0.getSource()).getText().equals("Edit")){
					final EditTest et = new EditTest(e);
					et.setControllerSave(new CancelController(et));
					et.setControllerDelete(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							Unit u = e.getUnit();
							u.removeTest(e);
							et.dispose();
							
							UICourseEditable ce = new UICourseEditable(u.getCourse(), app, gen);
							ce.addControllerUnit(new UnitController(app, ce, gen));
							ce.addControllerEditCourse(new EditCourseController(u.getCourse(), gen, app));
						}
					});
				}
			}
		});
	}	
}

