package gui.windows;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;


/**
 * 
 * @author Blanca, Simon
 *
 *This is the general Frame for the Student. It extends the abstract class and adds 
 *support for single user statistics
 */
public class GeneralStudent extends General{
	
	private static final long serialVersionUID = 1L;
	JMenuItem grades;

	/**
	 * Constructor
	 */
	public GeneralStudent() {
		super();
		//My grades option in the general window
		grades = new JMenuItem("My grades");
		addOption(grades);
	}
	
	public void addControllerGrades(ActionListener al){
		grades.addActionListener(al);
	}

}
