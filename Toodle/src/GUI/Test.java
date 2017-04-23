package GUI;

import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;


import Application.*;
import Test.*;
import Users.*;

public class Test extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private int i;
	private JFrame view;
	private UIQuestion center;
	
	private static List<Answer> answer = new ArrayList<Answer>();
	
	public Test(final Exercise e, final Application app) {
		view = this;
		i = 1;
		
		getContentPane().setLayout(new BorderLayout());
		
		final List<Question> questions = e.getQuestions();
		UIQuestion question = new UIQuestion(questions.get(0));
		center = question;
		
		add(question, BorderLayout.CENTER);
		
		JPanel buttons = new JPanel(new FlowLayout());
		JButton next = new JButton("Next");
		JButton previous = new JButton("Previous");
		JButton finish = new JButton("Finish");
		buttons.add(previous);
		buttons.add(finish);
		buttons.add(next);
		
		add(buttons, BorderLayout.SOUTH);
		
		next.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<String> answers = new ArrayList<String>();
				if(center.getOptions().isEmpty() == false){
					for(AbstractButton button : center.getOptions())
						if(button.isSelected())
							answers.add(button.getText());
				}
				else{
					answers.add(center.getField().getText());
				}
				for(Answer a : answer){
					if(a.getQuestion().equals(questions.get(i-1))){
						answer.remove(a);
						break;
					}
				}
				e.setCount(i-1);
				answer.add(e.answerQuestionTest(answers));
				if(i >= questions.size()) return;
				UIQuestion question = new UIQuestion(questions.get(i));
				i += 1;
				center.setVisible(false);
				center = question;
				view.add(question, BorderLayout.CENTER);
			}
			
		});
		
		finish.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
//				view.dispose();	
				List<String> answers = new ArrayList<String>();
				if(center.getOptions().isEmpty() == false){
					for(AbstractButton button : center.getOptions())
						if(button.isSelected())
							answers.add(button.getText());
				}
				else{
					answers.add(center.getField().getText());
				}
				for(Answer a : answer){
					if(a.getQuestion().equals(questions.get(i-1))){
						answer.remove(a);
						break;
					}
				}
				e.setCount(i-1);
				answer.add(e.answerQuestionTest(answers));
				e.answerTest((Student) app.getCurrentUser(), answer);
				// Solo para ver si funciona
				JOptionPane.showMessageDialog(view,((Student) app.getCurrentUser()).viewPastTest(e),((Student) app.getCurrentUser()).getAnsweredTest(e).getGradeTest() + " " , JOptionPane.ERROR_MESSAGE);
			}
			
		});
		
		previous.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<String> answers = new ArrayList<String>();
				if(center.getOptions().isEmpty() == false){
					for(AbstractButton button : center.getOptions())
						if(button.isSelected())
							answers.add(button.getText());
				}
				else{
					answers.add(center.getField().getText());
				}
				for(Answer a : answer){
					if(a.getQuestion().equals(questions.get(i-1))){
						answer.remove(a);
						break;
					}
				}
				e.setCount(i-1);
				answer.add(e.answerQuestionTest(answers));
				if(i-1 == 0) return;
				UIQuestion question = new UIQuestion(questions.get(i-2));
				i -= 1;
				center.setVisible(false);
				center = question;
				view.add(question, BorderLayout.CENTER);			
			}
			
		});
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		setVisible(true);
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public UIQuestion getCenter(){
		return center;
	}
	
}
