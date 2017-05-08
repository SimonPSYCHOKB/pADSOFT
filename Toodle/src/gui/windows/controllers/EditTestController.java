package gui.windows.controllers;

import exercise.Exercise;
import exercise.FreeText;
import exercise.TrueFalse;
import exercise.MultipleAnswer;
import exercise.Question;
import exercise.SingleAnswer;
import gui.panels.EditQuestion;
import gui.windows.EditTest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;


/**
 * 
 * @author Blanca, Simon
 *
 *The controller that allows for the teacher to edit a Test, transferring the data from
 *the View to the model
 *
 */
public class EditTestController implements ActionListener{
	
	private Exercise model;
	private EditTest view;

	/**
	 * Constructor
	 * @param model -The exercise to be edited
	 * @param view	-The EditTest view
	 */
	public EditTestController(Exercise model, EditTest view){
		this.model = model;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		List<EditQuestion> questions = view.getQuestions();
		List<Question> exquestions = model.getQuestions();
		
		//Editing the exercise
		System.out.println(view.getVisibility().isSelected());
		model.setVisibility(view.getVisibility().isSelected());
		model.setWeigth((double) view.getWeigth().getValue());
		model.editDateOfBegining((Date) view.getDateBegining().getValue());
		model.editDateOfEnd((Date) view.getDateEnd().getValue());
		
		//Editing each question
		int i = 0;
		for(EditQuestion eq : questions){
			
			//Penalty, weight and wording
			exquestions.get(i).setPenalty((double) eq.getPenalty().getValue());
			exquestions.get(i).setWeight((double) eq.getWeight().getValue());
			exquestions.get(i).setQuestion(eq.getWording().getText());
			
			//Options and answers
			if(exquestions.get(i) instanceof MultipleAnswer){
				List<String> options = new ArrayList<String>();
				for(JTextField text : eq.getOptions())
					options.add(text.getText());
								
				List<String> answers = new ArrayList<String>();
				for(JTextField text : eq.getAnswers())
					answers.add(text.getText());
				
				if(options.containsAll(answers) == false){
					JOptionPane.showMessageDialog(view, "In the Question " + i + " the options must include the answers", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				((MultipleAnswer) exquestions.get(i)).setOptions(options);
				((MultipleAnswer) exquestions.get(i)).setAnswer(answers);
			}
			else if(exquestions.get(i) instanceof SingleAnswer){
				List<String> options = new ArrayList<String>();
				for(JTextField text : eq.getOptions())
					options.add(text.getText());
							
				String answer = eq.getAnswers().get(0).getText();	

				if(options.contains(answer) == false){
					JOptionPane.showMessageDialog(view, "In the Question " + i + " the options must include the answer", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				((SingleAnswer) exquestions.get(i)).setAnswer(answer);
				((SingleAnswer) exquestions.get(i)).setOptions(options);
			}
			else{
				String answer = eq.getAnswers().get(0).getText();
				
				if(answer.equals("")){
					JOptionPane.showMessageDialog(view, "The Question " + i + " must have an answer", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(exquestions.get(i) instanceof FreeText)
					((FreeText) exquestions.get(i)).setAnswer(answer);
				else
					((TrueFalse) exquestions.get(i)).setAnswer(answer);
			}
			i += 1;
			
		}
		view.dispose();
	}

}
