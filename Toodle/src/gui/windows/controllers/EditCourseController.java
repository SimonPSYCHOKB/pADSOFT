package gui.windows.controllers;

import gui.CancelController;
import gui.panels.UICourseEditable;
import gui.panels.controllers.UnitController;
import gui.windows.EditCourse;
import gui.windows.General;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.Application;
import application.Course;

public class EditCourseController implements ActionListener{
	
	private Course model;
	private General view;
	private Application app;
	
	public EditCourseController(Course model, General view, Application app){
		this.model = model;
		this.view = view;
		this.app = app;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		final EditCourse ec = new EditCourse(model);
		ec.setControllerCancel(new CancelController(ec));
		ec.setControllerDone(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setTitle(ec.getTitleField().getText());
				model.setDescription(ec.getDescField().getText());
				model.setVisibility(ec.getVisibility().isSelected());
				
				UICourseEditable nec = new UICourseEditable(model, app, view);
				nec.addControllerUnit(new UnitController(app, nec, view));
				nec.addControllerEditCourse(new EditCourseController(model, view, app));
				view.addPanel(nec);
				ec.dispose();
			}
		});
	}

}
