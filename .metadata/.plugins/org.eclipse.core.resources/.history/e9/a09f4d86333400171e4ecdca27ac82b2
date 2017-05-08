package gui.panels.controllers;

import gui.CancelController;
import gui.panels.StudentsCourse;
import gui.panels.UICourseEditable;
import gui.windows.CreateNotes;
import gui.windows.General;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.Application;
import application.Note;
import application.Unit;

/**
 * 
 * @author Blanca, Simon
 *
 *A controller in order to add text to a note
 */
public class AddNotesController implements ActionListener{

	private Unit u;
	private Application app;
	private General gen;
	
	public AddNotesController(Unit u, Application app, General gen){
		this.u = u;
		this.app = app;
		this.gen = gen;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0){
		final CreateNotes cn = new CreateNotes();
		cn.setControllerCancel(new CancelController(cn));
		cn.setControllerDone(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0){
				u.addNotes(new Note(cn.getNotes().getText(), cn.getVisibility().isSelected()));
				cn.dispose();
				gen.addPanelWest(new UICourseEditable(u.getCourse(), app, gen));
				
				StudentsCourse sc = new StudentsCourse(u.getCourse().getRegistered(), u.getCourse().getPending(), u.getCourse().getExpelled());
				gen.addPanelEast(sc);
			}
		});
	}
}

