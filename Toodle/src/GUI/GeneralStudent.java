package GUI;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class GeneralStudent extends General{
	
	private static final long serialVersionUID = 1L;
	JMenuItem grades;

	public GeneralStudent() {
		super();
		grades = new JMenuItem("My grades");
		addOption(grades);
	}
	
	public void addControllerGrades(ActionListener al){
		grades.addActionListener(al);
	}

}
