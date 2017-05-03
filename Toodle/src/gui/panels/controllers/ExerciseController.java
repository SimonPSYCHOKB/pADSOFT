package gui.panels.controllers;

import exercise.Exercise;
import gui.CancelController;
import gui.panels.UICourseEditable;
import gui.windows.EditTest;
import gui.windows.General;
import gui.windows.Test;
import gui.windows.controllers.AnswerExerciseController;
import gui.windows.controllers.EditCourseController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import users.Student;


import application.Application;
import application.Unit;



public class ExerciseController extends MouseAdapter{

	private Application model;
	private Exercise e;
	private General gen;
	
	public ExerciseController(Application model, Exercise e, General gen){
		this.model = model;
		this.e = e;
		this.gen = gen;
	}
	
	public void mouseClicked(MouseEvent me){
		//If the current user is the teacher we display an editable test
		if(model.getCurrentUser().equals(model.getTeacher())){
			final EditTest et = new EditTest(e);
			et.setControllerSave(new CancelController(et));
			et.setControllerDelete(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Unit u = e.getUnit();
					u.removeTest(e);
					et.dispose();
					
					UICourseEditable ce = new UICourseEditable(u.getCourse(), model, gen);
					ce.addControllerUnit(new UnitController(model, ce, gen));
					ce.addControllerEditCourse(new EditCourseController(u.getCourse(), gen, model));
					gen.addPanel(ce);
				}
			});
		}
		
		//If the user is a student we display the test
		else{
			if(e.beginExercise((Student) model.getCurrentUser()) == false){
				if(((Student) model.getCurrentUser()).getAnsweredTest(e) != null)
					JOptionPane.showMessageDialog(new JFrame(),((Student) model.getCurrentUser()).viewPastTest(e),((Student) model.getCurrentUser()).getAnsweredTest(e).getGradeTest() + " " , JOptionPane.ERROR_MESSAGE);

				JOptionPane.showMessageDialog(new JFrame(),"Fecha limite rebasada", "Error", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			else{
				Test test = new Test(e);
				ActionListener al = new AnswerExerciseController(model, test, e);
				test.addControllerFinish(al);
			}
		}
	}
}
