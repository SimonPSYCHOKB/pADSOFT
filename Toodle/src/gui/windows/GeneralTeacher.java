package gui.windows;

import java.awt.event.ActionListener;

import javax.swing.*;


public class GeneralTeacher extends General{

	private static final long serialVersionUID = 1L;
	private JMenuItem stats;
	private JMenuItem students;
	
	public GeneralTeacher() {
		//Statistics option in the user menu
		stats = new JMenuItem("Statistics");
		addOption(stats);
		
		students = new JMenuItem("Students");
		addOption(students);
	}
	
	public void addControllerStats(ActionListener al){
		stats.addActionListener(al);
	}
	
	public void addControllerStudents(ActionListener al){
		students.addActionListener(al);
	}

}
