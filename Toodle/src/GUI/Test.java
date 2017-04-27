package GUI;

import java.awt.*;
import java.awt.event.*;

import java.util.List;

import javax.swing.*;


import Application.*;
import Test.*;

public class Test extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private UIQuestion center;
	
	private JButton next;
	private JButton previous;
	private JButton finish;
	
	public Test(final Exercise e, final Application app) {
		
		getContentPane().setLayout(new BorderLayout());
		
		final List<Question> questions = e.getQuestions();
		UIQuestion question = new UIQuestion(questions.get(0));
		center = question;
		
		add(question, BorderLayout.CENTER);
		
		JPanel buttons = new JPanel(new FlowLayout());
		next = new JButton("Next");
		previous = new JButton("Previous");
		finish = new JButton("Finish");
		buttons.add(previous);
		buttons.add(finish);
		buttons.add(next);
		
		add(buttons, BorderLayout.SOUTH);
		
		setSize(800, 600);
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public UIQuestion getCenter(){
		return center;
	}
	
	public void setCenter(UIQuestion center){
		this.center = center;
	}
	
	public void addControllerNext(ActionListener al){
		next.addActionListener(al);
	}
	
	public void addControllerFinish(ActionListener al){
		finish.addActionListener(al);
	}
	
	public void addControllerPrevious(ActionListener al){
		previous.addActionListener(al);
	}
	
}
