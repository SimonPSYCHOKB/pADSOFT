package gui.windows;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * 
 * @author Blanca, Simon
 *The JFrame that appears when a teacher wants to create a new Course. You can put the Course
 *title, visibility, and a short description
 */
public class CreateCourse extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private JTextField titleField;
	private JTextField descField;
	private JCheckBox visibCheck;
	private JButton done;
	private JButton cancel;
	
	/**
	 * Constructor
	 */
	public CreateCourse(){
		super("New Course");
		
		getContentPane().setLayout(new BorderLayout());
		
		JPanel info = new JPanel();
		SpringLayout layout = new SpringLayout();
		info.setLayout(layout);
		
		JLabel title = new JLabel("Title: ");
		JLabel desc = new JLabel("Description: ");
		JLabel visibility = new JLabel("Visibile: ");
		
		titleField = new JTextField(15);
		descField = new JTextField(30);
		visibCheck = new JCheckBox();
		
		layout.putConstraint(SpringLayout.WEST, title, 30, SpringLayout.WEST, info);
		layout.putConstraint(SpringLayout.NORTH, title, 30, SpringLayout.NORTH, info);
		layout.putConstraint(SpringLayout.WEST, desc, 30, SpringLayout.WEST, info);
		layout.putConstraint(SpringLayout.NORTH, desc, 30, SpringLayout.NORTH, title);
		layout.putConstraint(SpringLayout.WEST, visibility, 30, SpringLayout.WEST, info);
		layout.putConstraint(SpringLayout.NORTH, visibility, 30, SpringLayout.NORTH, desc);
		
		layout.putConstraint(SpringLayout.WEST, titleField, 90, SpringLayout.EAST, title);
		layout.putConstraint(SpringLayout.NORTH, titleField, 30, SpringLayout.NORTH, info);
		
		layout.putConstraint(SpringLayout.WEST, descField, 40, SpringLayout.EAST, desc);
		layout.putConstraint(SpringLayout.NORTH, descField, 30, SpringLayout.NORTH, titleField);
		
		layout.putConstraint(SpringLayout.WEST, visibCheck, 70, SpringLayout.EAST, visibility);
		layout.putConstraint(SpringLayout.NORTH, visibCheck, 30, SpringLayout.NORTH, descField);
		
		info.add(title); info.add(titleField);
		info.add(desc); info.add(descField);
		info.add(visibility); info.add(visibCheck);
		
		add(info, BorderLayout.CENTER);
		
		JPanel buttons = new JPanel(new FlowLayout());
		cancel = new JButton("Cancel");
		done = new JButton("Done");
		buttons.add(cancel);
		buttons.add(done);
		
		add(buttons, BorderLayout.SOUTH);
		
		setSize(800, 150);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	/**
	 * This method sets the controller to the done button
	 * @param al - the controller
	 */
	public void setControllerDone(ActionListener al){
		done.addActionListener(al);
	}
	
	/**
	 * This method sets the controller to the cancel button
	 * @param al - the controller
	 */
	public void setControllerCancel(ActionListener al){
		cancel.addActionListener(al);
	}
	
	/**
	 * This method returns the title entered for the course
	 * @return the title
	 */
	public String getTitleField(){
		return titleField.getText();
	}
	
	/**
	 * This method returns the description entered for the course
	 * @return the description
	 */
	public String getDescField(){
		return descField.getText();
	}
	
	/**
	 * This method returns the visibility selected for the course
	 * @return true if the course is visible; false if not
	 */
	public boolean getVisibCheck(){
		return visibCheck.isSelected(); 
	}

}
