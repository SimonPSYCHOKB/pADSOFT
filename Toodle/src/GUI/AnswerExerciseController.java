package GUI;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import Application.Application;
import Test.Answer;
import Test.Exercise;
import Test.Question;
import Users.Student;

public class AnswerExerciseController implements ActionListener{
	private static List<Answer> answer = new ArrayList<Answer>();
	private static int i = 1;
	
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
		JButton selected = (JButton) arg0.getSource();
		boolean cond = selected.getText().equals("Finish");
		List<Question> questions = e.getQuestions();
		List<String> answers = new ArrayList<String>();
		if(view.getCenter().getOptions().isEmpty() == false){
			for(AbstractButton button : view.getCenter().getOptions())
				if(button.isSelected())
					answers.add(button.getText());
		}
		else{
			answers.add(view.getCenter().getField().getText());
		}
		for(Answer a : answer){
			if(a.getQuestion().equals(questions.get(i-1))){
				answer.remove(a);
				break;
			}
		}
		e.setCount(i-1);
		answer.add(e.answerQuestionTest(answers));
		
		if(cond == false){	
			UIQuestion question;
			if(selected.getText().equals("Next")){
				if(i >= questions.size()) return;
				question = new UIQuestion(questions.get(i));
				i += 1;
			}
			else{
				if(i-1 == 0) return;
				question = new UIQuestion(questions.get(i-2));
				i -= 1;
			}
			view.getCenter().setVisible(false);
			view.setCenter(question);
			view.add(question, BorderLayout.CENTER);
		}
		else{
			e.answerTest((Student) model.getCurrentUser(), answer); 
			view.dispose();
			JOptionPane.showMessageDialog(view,((Student) model.getCurrentUser()).viewPastTest(e),((Student) model.getCurrentUser()).getAnsweredTest(e).getGradeTest() + " " , JOptionPane.ERROR_MESSAGE);
		}
	}

}
