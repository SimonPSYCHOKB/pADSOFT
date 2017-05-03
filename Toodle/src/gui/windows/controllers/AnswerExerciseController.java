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


public class AnswerExerciseController implements ActionListener{
		
	private Application model;
	private Test view;
	private Exercise e;
	
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
		JOptionPane.showMessageDialog(view,((Student) model.getCurrentUser()).viewPastTest(e),((Student) model.getCurrentUser()).getAnsweredTest(e).getGradeTest() + " " , JOptionPane.ERROR_MESSAGE);
	}

}
