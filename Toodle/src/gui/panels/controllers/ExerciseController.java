package gui.panels.controllers;

import exercise.Exercise;
import gui.CancelController;
import gui.windows.EditTest;
import gui.windows.General;
import gui.windows.PastTest;
import gui.windows.Test;
import gui.windows.controllers.AnswerExerciseController;
import gui.windows.controllers.EditTestController;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import users.Student;


import application.Application;

/**
 * 
 * @author Blanca, Simon
 * 
 * Controller for the exercise. If the current User is a teacher, it displays an editable 
 * Course, loading the pertinent controllers:
 * 		-EditTest
 * 		-DeleteTest
 * 		-CancelButton
 *
 *If the User is a Student, it adds the controller for finishing the exercise
 */
public class ExerciseController extends MouseAdapter{

	private Application model;
	private Exercise e;
	private General gen;
	
	/**
	 * Constructor
	 * @param model - the application
	 * @param e - the exercise that has been clicked
	 * @param gen - the general frame
	 */
	public ExerciseController(Application model, Exercise e, General gen){
		this.model = model;
		this.e = e;
		this.gen = gen;
	}
	
	public void mouseClicked(MouseEvent me){
		//If the current user is the teacher we display an editable test
		if(model.getCurrentUser().equals(model.getTeacher())){
			final EditTest et = new EditTest(e, model, gen);
			et.setControllerSave(new EditTestController(e, et));
			et.setControllerDelete(new DeleteTestController(e, model, gen, et));
			et.setControllerCancel(new CancelController(et));
		}
		
		//If the user is a student we display the test
		else{
			//Too late, or test has already been answered
			if(e.beginExercise((Student) model.getCurrentUser()) == false){
				//If test has already been answered
				if(((Student) model.getCurrentUser()).getAnsweredTest(e) != null){
					if(e.getDateOfEnd().before(new Date()))					
						new PastTest(((Student) model.getCurrentUser()).getAnsweredTest(e));
					else
						JOptionPane.showMessageDialog(new JFrame(),"The test has already been answered", "Error", JOptionPane.INFORMATION_MESSAGE);
				}

				else 
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
