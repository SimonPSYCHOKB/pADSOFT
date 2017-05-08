package gui.windows;

import exercise.*;
import gui.panels.UIQuestion;

import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;


/**
 * 
 * @author Blanca, Simon
 *
 *This is the JFrame associated with doing a Test. 
 */
public class Test extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JButton finish;
	private List<UIQuestion> uiquestions;
	
	/**
	 * Constructor
	 * @param e -The test that will be displayed
	 */
	public Test(Exercise e) {
		uiquestions = new ArrayList<UIQuestion>();
		getContentPane().setLayout(new BorderLayout());
		
		//Tabbed pane with the questions
		JTabbedPane questions = new JTabbedPane();
		questions.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		
		int i = 0;
		for(Question q : e.getQuestions()){
			UIQuestion quest = new UIQuestion(q);
			uiquestions.add(quest);
			questions.addTab("Question " + i, quest);
			i += 1;
		}
		questions.setVisible(true);
		questions.setMinimumSize(new Dimension(800, 300));
		add(questions, BorderLayout.CENTER);
		
		//Finish button
		JPanel button = new JPanel(new FlowLayout());
		finish = new JButton("Finish");
		button.add(finish);
		button.setVisible(true);
		add(button, BorderLayout.SOUTH);
		
		//Window settings
		setSize(800, 600);
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * 
	 * @return returns a list with all the UIQuestions, that is, all the questions of a list,
	 * nicely put in a Frame
	 */
	public List<UIQuestion> getQuestions(){
		return uiquestions;
	}
	
	/**
	 * This method sets the controller for the finish button
	 * @param al - the controller
	 */
	public void addControllerFinish(ActionListener al){
		finish.addActionListener(al);
	}
}
