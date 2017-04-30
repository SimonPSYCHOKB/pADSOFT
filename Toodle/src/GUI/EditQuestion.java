package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Test.*;

public class EditQuestion extends JPanel{
	
	private JPanel quest;
	private JTextField wording;
	private JSpinner spinner1, spinner2;

	private static final long serialVersionUID = 1L;

	public EditQuestion(final Question q){
		
		//BorderLayout
		setLayout(new BorderLayout());
		
		//Questions view
		quest = new JPanel();
		quest.setLayout(new GridLayout(0,1,5,5));
		
		//Wording
		wording = new JTextField(q.getQuestion());
		wording.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				wording.setText("");
			}
			@Override
			public void focusLost(FocusEvent arg0){
				wording.setText(q.getQuestion());
			}
		});
		wording.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				q.setQuestion(wording.getText());
			}
		});
		quest.add(wording);
		
		//Options and answers
		// MultipleAnswer
		if(q instanceof MultipleAnswer){
			
			JLabel a = new JLabel("Options :");
			quest.add(a);
			
			for(final String option : ((MultipleAnswer) q).getOptions()){
				final JTextField op = new JTextField(option);
				op.addFocusListener(new FocusListener(){
					@Override
					public void focusGained(FocusEvent arg0) {
						op.setText("");
					}
					@Override
					public void focusLost(FocusEvent arg0){
						op.setText(option);
					}
				});
				op.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						System.out.println(option + " " + op.getText());
						((MultipleAnswer) q).removeOption(option);
						((MultipleAnswer) q).addOption(op.getText());
					}
				});
				quest.add(op);
			}
			
			a = new JLabel("Answers :");
			quest.add(a);
			for(final String answer : ((MultipleAnswer) q).getAnswer()){
				final JTextField answ = new JTextField(answer);
				answ.addFocusListener(new FocusListener(){
					@Override
					public void focusGained(FocusEvent arg0) {
						answ.setText("");
					}
					@Override
					public void focusLost(FocusEvent arg0){
						answ.setText(answer);
					}
				});
				answ.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
					}
				});
				quest.add(answ);
			}
		} 
		// Single Answer
		else if(q instanceof SingleAnswer){
			
			JLabel a = new JLabel("Options :");
			quest.add(a);
			
			for(final String option : ((SingleAnswer) q).getOptions()){
				final JTextField op = new JTextField(option);
				op.addFocusListener(new FocusListener(){
					@Override
					public void focusGained(FocusEvent arg0) {
						op.setText("");
					}
					@Override
					public void focusLost(FocusEvent arg0){
						op.setText(option);
					}
				});
				op.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						((SingleAnswer) q).removeOption(option);
						((SingleAnswer) q).addOption(op.getText());
					}
				});
				quest.add(op);
			}
						
			a = new JLabel("Answer :");
			quest.add(a);
			final JTextField answ = new JTextField(((SingleAnswer) q).getAnswer());
			answ.addFocusListener(new FocusListener(){
				@Override
				public void focusGained(FocusEvent arg0) {
					answ.setText("");
				}
				@Override
				public void focusLost(FocusEvent arg0){
					answ.setText(((SingleAnswer) q).getAnswer());
				}
			});
			answ.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					((SingleAnswer) q).setAnswer(answ.getText());
				}
			});
			quest.add(answ);
		} 
		//FreeText and TrueFalse
		else{			
			JLabel a = new JLabel("Answer :");
			quest.add(a);
			final JTextField answ = new JTextField();
			final String text;
			if(q instanceof FreeText)
				text = ((FreeText) q).getAnswer();
			else
				text = ((TrueFalse) q).getAnswer();
			answ.setText(text);
			answ.addFocusListener(new FocusListener(){
				@Override
				public void focusGained(FocusEvent arg0) {
					answ.setText("");
				}
				@Override
				public void focusLost(FocusEvent arg0){
					if(q instanceof FreeText)
						answ.setText(((FreeText) q).getAnswer());
					else
						answ.setText(((TrueFalse) q).getAnswer());
				}
			});
			answ.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(q instanceof FreeText)
						((FreeText) q).setAnswer(answ.getText());
					else
						((TrueFalse) q).setAnswer(answ.getText());
				}
			});
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
		spinner1.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				q.setWeight((double) spinner1.getValue());
			}
		});
		
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
		spinner1.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				q.setPenalty((double) spinner2.getValue());
			}
		});
		
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
}
