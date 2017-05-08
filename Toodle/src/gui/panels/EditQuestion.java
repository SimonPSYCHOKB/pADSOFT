package gui.panels;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import exercise.*;


/**
 * 
 * @author Blanca, Simon
 * 
 * Once Edit Test is called, 
 *
 */
public class EditQuestion extends JPanel{
	
	private JPanel quest;
	private JTextField wording;
	private JSpinner spinner1, spinner2;
	private ArrayList<JTextField> options;
	private ArrayList<JTextField> answers;

	private static final long serialVersionUID = 1L;

	public EditQuestion(Question q){
		
		//BorderLayout
		setLayout(new BorderLayout());
		
		//Questions view
		quest = new JPanel();
		quest.setLayout(new GridLayout(0,1,5,5));
		
		//Wording
		wording = new JTextField(q.getQuestion());
		quest.add(wording);
		
		//Options and answers
		options = new ArrayList<JTextField>();
		answers = new ArrayList<JTextField>();
		// MultipleAnswer
		if(q instanceof MultipleAnswer){
			
			JLabel a = new JLabel("Options :");
			quest.add(a);
			
			for(String option : ((MultipleAnswer) q).getOptions()){
				JTextField op = new JTextField(option);
				options.add(op);
				quest.add(op);
			}
			
			a = new JLabel("Answers :");
			quest.add(a);
			for(String answer : ((MultipleAnswer) q).getAnswer()){
				JTextField answ = new JTextField(answer);
				answers.add(answ);
				quest.add(answ);
			}
		} 
		// Single Answer
		else if(q instanceof SingleAnswer){
			
			JLabel a = new JLabel("Options :");
			quest.add(a);
			
			for(String option : ((SingleAnswer) q).getOptions()){
				JTextField op = new JTextField(option);
				options.add(op);
				quest.add(op);
			}
						
			a = new JLabel("Answer :");
			quest.add(a);
			JTextField answ = new JTextField(((SingleAnswer) q).getAnswer());
			answers.add(answ);
			quest.add(answ);
		} 
		//FreeText and TrueFalse
		else{			
			JLabel a = new JLabel("Answer :");
			quest.add(a);
			JTextField answ = new JTextField();
			String text;
			if(q instanceof FreeText)
				text = ((FreeText) q).getAnswer();
			else
				text = ((TrueFalse) q).getAnswer();
			answ.setText(text);
			answers.add(answ);
			quest.add(answ);
		}
		
		quest.setVisible(true);
		quest.setSize(400,150);
		
		
		//Question Data
		JPanel data = new JPanel();
		SpringLayout layout = new SpringLayout();
		data.setLayout(layout);
		
		//Weight
		JLabel weight = new JLabel("Weight :");
		layout.putConstraint(SpringLayout.NORTH, weight, 40, SpringLayout.NORTH, data);
		layout.putConstraint(SpringLayout.WEST, weight, 50, SpringLayout.WEST, data);
		data.add(weight);
		SpinnerModel sm1 = new SpinnerNumberModel(q.getWeight(), 0, 100, 0.1);
		spinner1 = new JSpinner(sm1);
		layout.putConstraint(SpringLayout.NORTH, spinner1, 40, SpringLayout.NORTH, data);
		layout.putConstraint(SpringLayout.WEST, spinner1, 100, SpringLayout.WEST, weight);
		data.add(spinner1);
		
		//Penalty
		JLabel penalty = new JLabel("Penalty :");
		layout.putConstraint(SpringLayout.NORTH, penalty, 20, SpringLayout.NORTH, weight);
		layout.putConstraint(SpringLayout.WEST, penalty, 50, SpringLayout.WEST, data);
		data.add(penalty);
		SpinnerModel sm2 = new SpinnerNumberModel(q.getPenalty(), 0, 100, 0.1);
		spinner2 = new JSpinner(sm2);
		layout.putConstraint(SpringLayout.NORTH, spinner2, 20, SpringLayout.NORTH, spinner1);
		layout.putConstraint(SpringLayout.WEST, spinner2, 100, SpringLayout.WEST, penalty);
		data.add(spinner2);
		
		data.setVisible(true);
		data.setPreferredSize(new Dimension(300, 150));
		
		add(quest, BorderLayout.CENTER);
		add(data, BorderLayout.EAST);
		
		setVisible(true);
		
	}
	
	public JPanel getPanel(){
		return quest;
	}
	
	public JTextField getWording(){
		return wording;
	}
	
	public JSpinner getWeight(){
		return spinner1;
	}
	
	public JSpinner getPenalty(){
		return spinner2;
	}
	
	public ArrayList<JTextField> getOptions(){
		return options;
	}
	
	public ArrayList<JTextField> getAnswers(){
		return answers;
	}
}
