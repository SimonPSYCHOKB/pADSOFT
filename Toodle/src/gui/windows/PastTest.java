package gui.windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import exercise.Answer;
import exercise.AnsweredTest;
import exercise.Exercise;
import exercise.Question;
import gui.panels.UIAnsweredQuestion;

/**
 * 
 * @author Blanca, Simon
 *
 *With this Frame, a student Can view a past test so that he may check how he answered
 */
public class PastTest extends JFrame{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor
	 * @param at -The past test that you wish to display in the JFrame
	 */
	public PastTest(AnsweredTest at){
		
		//Layout for the window
		getContentPane().setLayout(new BorderLayout());
		
		//Tabbed pane with the questions
		JTabbedPane questions = new JTabbedPane();
		questions.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		
		Exercise e = at.getTest();
		List<Answer> answers = at.getAnswers();
		
		int i = 0;
		for(Question q : e.getQuestions()){
			UIAnsweredQuestion aq = new UIAnsweredQuestion(q, answers.get(i));
			questions.addTab("Question " + i, aq);
			i += 1;
		}
		
		questions.setVisible(true);
		questions.setMinimumSize(new Dimension(800, 300));
		add(questions, BorderLayout.CENTER);
		
		//Finish button
		JPanel button = new JPanel(new FlowLayout());
		JButton close = new JButton("Close");
		button.add(close);
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

}
