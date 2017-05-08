package gui.windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * 
 * @author Blanca, Simon
 *
 *A simple JFrame to create a Multiple Answer Question. You can mark the checkboxes with
 *the correct answer and write up to five answers. If an answer is not written on, it 
 *will not count
 */
public class CreateMultipleAnswer extends JFrame{

	private static final long serialVersionUID = 1L;
	private JButton done;
	private JButton cancel;
	private ArrayList<JTextField> options;
	private ArrayList<JCheckBox> answers;
	
	/**
	 * Constructor
	 */
	public CreateMultipleAnswer(){
		super("Create Multiple Choice Question");
		
		//Layout for the window
		setLayout(new BorderLayout());
		
		//Panel with the options/answers
		JPanel center = new JPanel();
		SpringLayout layout  = new SpringLayout();
		center.setLayout(layout);
		
		options = new ArrayList<JTextField>();
		answers = new ArrayList<JCheckBox>();
		JComponent previous = center;
		for(int j = 0; j < 5; j++){
			JCheckBox c = new JCheckBox();
			layout.putConstraint(SpringLayout.NORTH, c, 30, SpringLayout.NORTH, previous);
			layout.putConstraint(SpringLayout.WEST, c, 40, SpringLayout.WEST, center);
			center.add(c);
			JLabel a = new JLabel("Option " + j + " :");
			layout.putConstraint(SpringLayout.NORTH, a, 30, SpringLayout.NORTH, previous);
			layout.putConstraint(SpringLayout.WEST, a, 80, SpringLayout.WEST, center);
			center.add(a);
			JTextField i = new JTextField(10);
			layout.putConstraint(SpringLayout.NORTH, i, 30, SpringLayout.NORTH, previous);
			layout.putConstraint(SpringLayout.WEST, i, 22, SpringLayout.EAST, a);
			center.add(i);
			previous = i;
			
			options.add(i);
			answers.add(c);
		}
		
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
	 * This method returns the options entered for the question
	 * @return list with the fields of the options
	 */
	public ArrayList<JTextField> getOptions(){
		return options;
	}
	
	/**
	 * This method returns the answers for the question
	 * @return list with the fields of the answers
	 */
	public ArrayList<JCheckBox> getAnswers(){
		return answers;
	}
	

}
