package gui.windows.controllers;

import exercise.*;
import gui.panels.UIQuestion;
import gui.windows.Test;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import users.Student;


import application.Application;

/**
 * 
 * @author Blanca, Simon
 * 
 * The Controller for the answer of an exercise
 *
 */
public class AnswerExerciseController implements ActionListener{
		
	private Application model;
	private Test view;
	private Exercise e;
	
	/**
	 * 
	 * @param model -the Application
	 * @param view  -The view of the Test
	 * @param e -The data type of the exercise
	 */
	public AnswerExerciseController(Application model, Test view, Exercise e){
		this.model = model;
		this.view = view;
		this.e = e;
	}

	
	@Override
	public void actionPerformed(ActionEvent arg0) {		
		List<Answer> answer = new ArrayList<Answer>();
		List<String> answers;
		List<UIQuestion> uiquestions = view.getQuestions();
		
		for(UIQuestion q : uiquestions){
			answers = new ArrayList<String>();
			if(q.getOptions().isEmpty() == false){
				for(AbstractButton b : q.getOptions())
					if(b.isSelected())
						answers.add(b.getText());
			}
			else{
				answers.add(q.getField().getText());
			}
			answer.add(e.answerQuestionTest(answers));
		}
		
		e.answerTest((Student) model.getCurrentUser(), answer);
		e.finishExercise((Student) model.getCurrentUser());
		view.dispose();
	}

}
