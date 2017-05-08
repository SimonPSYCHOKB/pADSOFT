package gui.windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author Blanca, Simon
 *
 *Basic class that displays a Jframe upon which you can create a Free Text Question
 */
public class CreateTextQuestion extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField answer;
	private JButton cancel;
	private JButton done;
	
	/**
	 * Constructor
	 * @param title
	 */
	public CreateTextQuestion(String title){
		super(title);
		
		//Layout for the window
		setLayout(new BorderLayout());
		
		//Panel with the answer
		JPanel center = new JPanel(new FlowLayout());
		
		JLabel s = new JLabel("Answer : ");
		center.add(s);
		answer = new JTextField(20);
		center.add(answer);
		
		center.setPreferredSize(new Dimension(400, 400));
		center.setVisible(true);
		
		//Panel for the buttons
		JPanel buttons = new JPanel(new FlowLayout());
		cancel = new JButton("Cancel");
		done = new JButton("Done");
		buttons.add(cancel);
		buttons.add(done);
		buttons.setVisible(true);
		
		add(center, BorderLayout.CENTER);
		add(buttons, BorderLayout.SOUTH);
		
		setVisible(true);
		setSize(400, 300);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	/**
	 * This method sets the controller for the cancel button
	 * @param al - the controller
	 */
	public void setControllerCancel(ActionListener al){
		cancel.addActionListener(al);
	}
	
	/**
	 * This method sets the controller for the done button
	 * @param al - the controller
	 */
	public void setControllerDone(ActionListener al){
		done.addActionListener(al);
	}
	
	/**
	 * This method returns the answer of the question
	 * @return field for the answer
	 */
	public JTextField getAnswer(){
		return answer;
	}

}
