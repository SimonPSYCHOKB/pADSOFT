package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import Application.Application;
import Test.*;

public class QuestionCreation extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private String question = " ";
	private double weig = 0;
	private double penalt = 0;

	public QuestionCreation(final Exercise e, Application model){
		super("Add question");
		final JFrame dispose = this;
		
		//BorderLayout
		setLayout(new BorderLayout());
		
		//Info
		JPanel info = new JPanel();
		SpringLayout layout = new SpringLayout();
		info.setLayout(layout);
		//Wording
		JLabel wording = new JLabel("Wording :");
		final JTextField w = new JTextField(20);
		
		layout.putConstraint(SpringLayout.NORTH, wording, 40, SpringLayout.NORTH, info);
		layout.putConstraint(SpringLayout.WEST, wording, 150, SpringLayout.WEST, info);
		layout.putConstraint(SpringLayout.NORTH, w, 40, SpringLayout.NORTH, info);
		layout.putConstraint(SpringLayout.WEST, w, 30, SpringLayout.EAST, wording);
		info.add(wording);
		info.add(w);
		
		//Question type
		String[] t = {"Single Answer", "Multiple Choice", "Free Text", "True-False"};
		JLabel type = new JLabel("Type of question :");
		final JComboBox<String> types = new JComboBox<String>(t);
		types.setSelectedIndex(0);
		
		layout.putConstraint(SpringLayout.NORTH, type, 30, SpringLayout.NORTH, wording);
		layout.putConstraint(SpringLayout.WEST, type, 150, SpringLayout.WEST, info);
		layout.putConstraint(SpringLayout.NORTH, types, 30, SpringLayout.NORTH, w);
		layout.putConstraint(SpringLayout.WEST, types, 30, SpringLayout.EAST, type);
		info.add(type);
		info.add(types);
		
		//Weight
		JLabel weight = new JLabel("Weight :");
		SpinnerModel sm = new SpinnerNumberModel(0,0,100,0.1);
		final JSpinner spinner = new JSpinner(sm);
		
		layout.putConstraint(SpringLayout.NORTH, weight, 30, SpringLayout.NORTH, type);
		layout.putConstraint(SpringLayout.WEST, weight, 150, SpringLayout.WEST, info);
		layout.putConstraint(SpringLayout.NORTH, spinner, 30, SpringLayout.NORTH, types);
		layout.putConstraint(SpringLayout.WEST, spinner, 30, SpringLayout.EAST, weight);
		info.add(weight);
		info.add(spinner);
		
		//Penalty
		JLabel penalty = new JLabel("Penalty :");
		SpinnerModel sm2 = new SpinnerNumberModel(0,0,100,0.1);
		final JSpinner spinner2 = new JSpinner(sm2);
		
		layout.putConstraint(SpringLayout.NORTH, penalty, 30, SpringLayout.NORTH, weight);
		layout.putConstraint(SpringLayout.WEST, penalty, 150, SpringLayout.WEST, info);
		layout.putConstraint(SpringLayout.NORTH, spinner2, 30, SpringLayout.NORTH, spinner);
		layout.putConstraint(SpringLayout.WEST, spinner2, 30, SpringLayout.EAST, penalty);
		info.add(penalty);
		info.add(spinner2);
		
		info.setVisible(true);
		info.setPreferredSize(new Dimension(500, 150));
		
		add(info, BorderLayout.CENTER);
		
		//Buttons
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		
		JButton cancel = new JButton("Cancel");
		JButton next = new JButton("Next");
		buttons.add(cancel);
		buttons.add(next);
		
		cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose.dispose();
			}		
		});
		
		next.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				question = w.getText();
				weig = (double) spinner.getValue();
				penalt = (double) spinner2.getValue();
		        String type = (String)types.getSelectedItem();
		        if(type.equals("Multiple Choice"))
		        	multipleAnswer();
		        else if(type.equals("Single Answer"))
		        	singleAnswer();
		        else if(type.equals("Free Text"))
		        	freeText();
		        else trueFalse();
			}

			private void trueFalse() {
				final JFrame tf = new JFrame("True-False");
				tf.setLayout(new BorderLayout());
				
				//Answer
				JPanel word = new JPanel(new FlowLayout());
				JLabel answer = new JLabel("Answer :");
				word.add(answer);
				final JTextField answ = new JTextField(10);
				word.add(answ);
				word.setVisible(true);
				
				JPanel buttons = new JPanel(new FlowLayout());
				JButton cancel = new JButton("Cancel");
				JButton done = new JButton("Done");
				buttons.add(cancel);
				buttons.add(done);
				buttons.setVisible(true);
				
				tf.add(word, BorderLayout.CENTER);
				tf.add(buttons, BorderLayout.SOUTH);
				
				tf.setVisible(true);
				tf.setSize(400, 100);
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				tf.setLocation(dim.width/2-tf.getSize().width/2, dim.height/2-tf.getSize().height/2);
				tf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				cancel.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						tf.dispose();
					}
				});
				done.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						e.addQuestion(new TrueFalse(question, weig, penalt, answ.getText()));
						tf.dispose();
						dispose.dispose();
					}
				});
			}

			private void freeText() {
				final JFrame tf = new JFrame("Free Text");
				tf.setLayout(new BorderLayout());
				JPanel word = new JPanel(new FlowLayout());
				JLabel answer = new JLabel("Answer :");
				word.add(answer);
				final JTextField answ = new JTextField(10);
				word.add(answ);
				word.setVisible(true);
				
				JPanel buttons = new JPanel(new FlowLayout());
				JButton cancel = new JButton("Cancel");
				JButton done = new JButton("Done");
				buttons.add(cancel);
				buttons.add(done);
				buttons.setVisible(true);
				
				tf.add(word, BorderLayout.CENTER);
				tf.add(buttons, BorderLayout.SOUTH);
				
				tf.setVisible(true);
				tf.setSize(400, 100);
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				tf.setLocation(dim.width/2-tf.getSize().width/2, dim.height/2-tf.getSize().height/2);
				tf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				cancel.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						tf.dispose();
					}
				});
				done.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						e.addQuestion(new FreeText(question, weig, penalt, answ.getText()));
						tf.dispose();
						dispose.dispose();
					}
				});
			}

			private void singleAnswer() {
				final JFrame tf = new JFrame("Single Answer");
				tf.setLayout(new BorderLayout());
				JPanel word = new JPanel();
				SpringLayout layout  = new SpringLayout();
				word.setLayout(layout);
				ButtonGroup rb = new ButtonGroup();
				final JRadioButton c1 = new JRadioButton();
				layout.putConstraint(SpringLayout.NORTH, c1, 30, SpringLayout.NORTH, word);
				layout.putConstraint(SpringLayout.WEST, c1, 40, SpringLayout.WEST, word);
				rb.add(c1);
				word.add(c1);
				JLabel a1 = new JLabel("Option 1 :");
				layout.putConstraint(SpringLayout.NORTH, a1, 30, SpringLayout.NORTH, word);
				layout.putConstraint(SpringLayout.WEST, a1, 80, SpringLayout.WEST, word);
				word.add(a1);
				final JTextField i1 = new JTextField(10);
				layout.putConstraint(SpringLayout.NORTH, i1, 30, SpringLayout.NORTH, word);
				layout.putConstraint(SpringLayout.WEST, i1, 22, SpringLayout.EAST, a1);
				word.add(i1);
				final JRadioButton c2 = new JRadioButton();
				layout.putConstraint(SpringLayout.NORTH, c2, 30, SpringLayout.NORTH, c1);
				layout.putConstraint(SpringLayout.WEST, c2, 40, SpringLayout.WEST, word);
				rb.add(c2);
				word.add(c2);
				JLabel a2 = new JLabel("Option 2 :");
				layout.putConstraint(SpringLayout.NORTH, a2, 30, SpringLayout.NORTH, a1);
				layout.putConstraint(SpringLayout.WEST, a2, 80, SpringLayout.WEST, word);
				word.add(a2);
				final JTextField i2 = new JTextField(10);
				layout.putConstraint(SpringLayout.NORTH, i2, 30, SpringLayout.NORTH, i1);
				layout.putConstraint(SpringLayout.WEST, i2, 22, SpringLayout.EAST, a2);
				word.add(i2);
				final JRadioButton c3 = new JRadioButton();
				layout.putConstraint(SpringLayout.NORTH, c3, 30, SpringLayout.NORTH, c2);
				layout.putConstraint(SpringLayout.WEST, c3, 40, SpringLayout.WEST, word);
				rb.add(c3);
				word.add(c3);
				JLabel a3 = new JLabel("Option 3 :");
				layout.putConstraint(SpringLayout.NORTH, a3, 30, SpringLayout.NORTH, a2);
				layout.putConstraint(SpringLayout.WEST, a3, 80, SpringLayout.WEST, word);
				word.add(a3);
				final JTextField i3 = new JTextField(10);
				layout.putConstraint(SpringLayout.NORTH, i3, 30, SpringLayout.NORTH, i2);
				layout.putConstraint(SpringLayout.WEST, i3, 22, SpringLayout.EAST, a3);
				word.add(i3);
				final JRadioButton c4 = new JRadioButton();
				layout.putConstraint(SpringLayout.NORTH, c4, 30, SpringLayout.NORTH, c3);
				layout.putConstraint(SpringLayout.WEST, c4, 40, SpringLayout.WEST, word);
				rb.add(c4);
				word.add(c4);
				JLabel a4 = new JLabel("Option 4 :");
				layout.putConstraint(SpringLayout.NORTH, a4, 30, SpringLayout.NORTH, a3);
				layout.putConstraint(SpringLayout.WEST, a4, 80, SpringLayout.WEST, word);
				word.add(a4);
				final JTextField i4 = new JTextField(10);
				layout.putConstraint(SpringLayout.NORTH, i4, 30, SpringLayout.NORTH, i3);
				layout.putConstraint(SpringLayout.WEST, i4, 22, SpringLayout.EAST, a4);
				word.add(i4);
				
				word.setPreferredSize(new Dimension(400, 400));
				word.setVisible(true);
				
				JPanel buttons = new JPanel(new FlowLayout());
				JButton cancel = new JButton("Cancel");
				JButton done = new JButton("Done");
				buttons.add(cancel);
				buttons.add(done);
				buttons.setVisible(true);
				
				tf.add(word, BorderLayout.CENTER);
				tf.add(buttons, BorderLayout.SOUTH);
				
				tf.setVisible(true);
				tf.setSize(400, 300);
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				tf.setLocation(dim.width/2-tf.getSize().width/2, dim.height/2-tf.getSize().height/2);
				tf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				cancel.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						tf.dispose();
					}
				});
				done.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						ArrayList<String> options = new ArrayList<String>();
						String answer;
						if(i1.getText().equals("") == false) options.add(i1.getText());
						if(i2.getText().equals("") == false) options.add(i2.getText());
						if(i3.getText().equals("") == false) options.add(i3.getText());
						if(i4.getText().equals("") == false) options.add(i4.getText());
						if(c1.isSelected()){
							if(i1.getText().equals("") == false)
								answer = i1.getText();
							else{
								JOptionPane.showMessageDialog(tf, "The options must include the answer", "Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						else if(c2.isSelected()){
							if(i2.getText().equals("") == false)
								answer = i2.getText();
							else{
								JOptionPane.showMessageDialog(tf, "The options must include the answer", "Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						else if(c3.isSelected()){
							if(i3.getText().equals("") == false)
								answer = i3.getText();
							else{
								JOptionPane.showMessageDialog(tf, "The options must include the answer", "Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						else if(c4.isSelected()){
							if(i4.getText().equals("") == false)
								answer = i4.getText();
							else{
								JOptionPane.showMessageDialog(tf, "The options must include the answer", "Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						else{
							JOptionPane.showMessageDialog(tf, "An answer must be selected", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						e.addQuestion(new SingleAnswer(question, weig, penalt, answer, options));
						tf.dispose();
						dispose.dispose();
					}
				});
			}

			private void multipleAnswer() {
				final JFrame tf = new JFrame("Single Answer");
				tf.setLayout(new BorderLayout());
				JPanel word = new JPanel();
				SpringLayout layout  = new SpringLayout();
				word.setLayout(layout);
				final JCheckBox c1 = new JCheckBox();
				layout.putConstraint(SpringLayout.NORTH, c1, 30, SpringLayout.NORTH, word);
				layout.putConstraint(SpringLayout.WEST, c1, 40, SpringLayout.WEST, word);
				word.add(c1);
				JLabel a1 = new JLabel("Option 1 :");
				layout.putConstraint(SpringLayout.NORTH, a1, 30, SpringLayout.NORTH, word);
				layout.putConstraint(SpringLayout.WEST, a1, 80, SpringLayout.WEST, word);
				word.add(a1);
				final JTextField i1 = new JTextField(10);
				layout.putConstraint(SpringLayout.NORTH, i1, 30, SpringLayout.NORTH, word);
				layout.putConstraint(SpringLayout.WEST, i1, 22, SpringLayout.EAST, a1);
				word.add(i1);
				final JCheckBox c2 = new JCheckBox();
				layout.putConstraint(SpringLayout.NORTH, c2, 30, SpringLayout.NORTH, c1);
				layout.putConstraint(SpringLayout.WEST, c2, 40, SpringLayout.WEST, word);
				word.add(c2);
				JLabel a2 = new JLabel("Option 2 :");
				layout.putConstraint(SpringLayout.NORTH, a2, 30, SpringLayout.NORTH, a1);
				layout.putConstraint(SpringLayout.WEST, a2, 80, SpringLayout.WEST, word);
				word.add(a2);
				final JTextField i2 = new JTextField(10);
				layout.putConstraint(SpringLayout.NORTH, i2, 30, SpringLayout.NORTH, i1);
				layout.putConstraint(SpringLayout.WEST, i2, 22, SpringLayout.EAST, a2);
				word.add(i2);
				final JCheckBox c3 = new JCheckBox();
				layout.putConstraint(SpringLayout.NORTH, c3, 30, SpringLayout.NORTH, c2);
				layout.putConstraint(SpringLayout.WEST, c3, 40, SpringLayout.WEST, word);
				word.add(c3);
				JLabel a3 = new JLabel("Option 3 :");
				layout.putConstraint(SpringLayout.NORTH, a3, 30, SpringLayout.NORTH, a2);
				layout.putConstraint(SpringLayout.WEST, a3, 80, SpringLayout.WEST, word);
				word.add(a3);
				final JTextField i3 = new JTextField(10);
				layout.putConstraint(SpringLayout.NORTH, i3, 30, SpringLayout.NORTH, i2);
				layout.putConstraint(SpringLayout.WEST, i3, 22, SpringLayout.EAST, a3);
				word.add(i3);
				final JCheckBox c4 = new JCheckBox();
				layout.putConstraint(SpringLayout.NORTH, c4, 30, SpringLayout.NORTH, c3);
				layout.putConstraint(SpringLayout.WEST, c4, 40, SpringLayout.WEST, word);
				word.add(c4);
				JLabel a4 = new JLabel("Option 4 :");
				layout.putConstraint(SpringLayout.NORTH, a4, 30, SpringLayout.NORTH, a3);
				layout.putConstraint(SpringLayout.WEST, a4, 80, SpringLayout.WEST, word);
				word.add(a4);
				final JTextField i4 = new JTextField(10);
				layout.putConstraint(SpringLayout.NORTH, i4, 30, SpringLayout.NORTH, i3);
				layout.putConstraint(SpringLayout.WEST, i4, 22, SpringLayout.EAST, a4);
				word.add(i4);
				
				word.setPreferredSize(new Dimension(400, 400));
				word.setVisible(true);
				
				JPanel buttons = new JPanel(new FlowLayout());
				JButton cancel = new JButton("Cancel");
				JButton done = new JButton("Done");
				buttons.add(cancel);
				buttons.add(done);
				buttons.setVisible(true);
				
				tf.add(word, BorderLayout.CENTER);
				tf.add(buttons, BorderLayout.SOUTH);
				
				tf.setVisible(true);
				tf.setSize(400, 300);
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				tf.setLocation(dim.width/2-tf.getSize().width/2, dim.height/2-tf.getSize().height/2);
				tf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				cancel.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						tf.dispose();
					}
				});
				done.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						ArrayList<String> options = new ArrayList<String>();
						ArrayList<String> answer = new ArrayList<String>();
						if(i1.getText().equals("") == false) options.add(i1.getText());
						if(i2.getText().equals("") == false) options.add(i2.getText());
						if(i3.getText().equals("") == false) options.add(i3.getText());
						if(i4.getText().equals("") == false) options.add(i4.getText());
						if(c1.isSelected()){
							if(i1.getText().equals("") == false)
								answer.add(i1.getText());
							else{
								JOptionPane.showMessageDialog(tf, "The options must include the answer", "Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						if(c2.isSelected()){
							if(i2.getText().equals("") == false)
								answer.add(i2.getText());
							else{
								JOptionPane.showMessageDialog(tf, "The options must include the answer", "Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						if(c3.isSelected()){
							if(i3.getText().equals("") == false)
								answer.add(i3.getText());
							else{
								JOptionPane.showMessageDialog(tf, "The options must include the answer", "Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						if(c4.isSelected()){
							if(i4.getText().equals("") == false)
								answer.add(i4.getText());
							else{
								JOptionPane.showMessageDialog(tf, "The options must include the answer", "Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						if(answer.isEmpty()){
							JOptionPane.showMessageDialog(tf, "An answer must be selected", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						e.addQuestion(new MultipleAnswer(question, weig, penalt, answer, options));
						tf.dispose();
						dispose.dispose();
					}
				});
			}
		});
		
		add(buttons, BorderLayout.SOUTH);
		
		setSize(600, 250);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
