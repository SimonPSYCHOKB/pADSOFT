package gui.windows;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import application.Course;
/**
 * 
 * @author Blanca, Simon
 *
 * This JFrame appears when you want to edit the basic parameters of a course
 */
public class EditCourse extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField title;
	private JTextField desc;
	private JCheckBox visib;
	private JButton done;
	private JButton cancel;
	
	/**
	 * Constructor
	 * @param c - the course to be edited
	 */
	public EditCourse(Course c){
		super("Edit course");
		
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(8, 8, 8, 8);
		getContentPane().add(new JLabel("Title : "), gbc);
		gbc.gridy += 1;; 
		getContentPane().add(new JLabel("Description : "), gbc);
		gbc.gridy += 1;; 
		getContentPane().add(new JLabel("Visible : "), gbc);
		
		gbc.gridx += 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		title = new JTextField(c.getTitle());
		getContentPane().add(title, gbc);
		gbc.gridy += 1;
        desc = new JTextField(c.getDescription());
        getContentPane().add(desc, gbc);
        gbc.gridy += 1;
        visib = new JCheckBox();
        visib.setSelected(c.isVisibility());
        getContentPane().add(visib, gbc);
        
        gbc.gridx = 0;
        gbc.gridy += 1;
        done = new JButton("Done");
        getContentPane().add(done, gbc);
        gbc.gridx += 1;
        cancel = new JButton("Cancel");
        getContentPane().add(cancel, gbc);        
        
        
        setSize(800, 200);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	/**
	 * This method returns the title of the course
	 * @return field with the title
	 */
	public JTextField getTitleField(){
		return title;
	}

	/**
	 * This method returns the description of the course
	 * @return field with the description
	 */
	public JTextField getDescField(){
		return desc;
	}
	
	/**
	 * This method returns the visibility of the course
	 * @return check box with the visibility
	 */
	public JCheckBox getVisibility(){
		return visib;
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
}
