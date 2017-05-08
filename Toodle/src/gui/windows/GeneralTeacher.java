package gui.windows;

import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * 
 * @author Blanca, Simon
 *
 *This is the general Frame for the Teacher. It extends the abstract class and adds 
 *support for the statistics of the courses
 */
public class GeneralTeacher extends General{

	private static final long serialVersionUID = 1L;
	private JMenuItem stats;
	private JMenuItem students;
	
	/**
	 * Constructor
	 */
	public GeneralTeacher() {
		//Statistics option in the user menu
		stats = new JMenuItem("Statistics");
		addOption(stats);
		
		students = new JMenuItem("Students");
		addOption(students);
	}
	
	/**
	 * This method sets the controller for the stats option on the menu
	 * @param al - the controller
	 */
	public void addControllerStats(ActionListener al){
		stats.addActionListener(al);
	}
	
	/**
	 * This method sets the controller for the students option on the menu
	 * @param al - the controller
	 */
	public void addControllerStudents(ActionListener al){
		students.addActionListener(al);
	}

}
