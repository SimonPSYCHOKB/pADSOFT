package GUI;

import java.awt.*;
import java.awt.event.*;

import java.util.List;

import javax.swing.*;


import Test.*;

public class Test extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private int i;
	private JFrame view;
	private JPanel center;
	
	public Test(Exercise e) {
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
		JButton cancel = new JButton("Cancel");
		buttons.add(previous);
		buttons.add(cancel);
		buttons.add(next);
		
		add(buttons, BorderLayout.SOUTH);
		
		next.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(i >= questions.size()) return;
				UIQuestion question = new UIQuestion(questions.get(i));
				i += 1;
				center.setVisible(false);
				center = question;
				view.add(question, BorderLayout.CENTER);
			}
			
		});
		
		cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				view.dispose();				
			}
			
		});
		
		previous.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(i-1 == 0) return;
				UIQuestion question = new UIQuestion(questions.get(i-2));
				i -= 1;
				center.setVisible(false);
				center = question;
				view.add(question, BorderLayout.CENTER);			
			}
			
		});
		
		setVisible(true);
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	
}