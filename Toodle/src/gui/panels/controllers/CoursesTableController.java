package gui.panels.controllers;

import gui.CancelController;
import gui.panels.UICourse;
import gui.panels.UICourseEditable;
import gui.panels.UIStats;
import gui.windows.General;
import gui.windows.Register;
import gui.windows.controllers.EditCourseController;

import java.awt.event.*;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import users.Student;

import application.*;



public class CoursesTableController extends MouseAdapter{

	private Application model;
	private General view;
	
	public CoursesTableController(Application model, General view){
		this.view = view;
		this.model = model;
	}
	
	public void mouseClicked(MouseEvent e){
		JTable table = (JTable) e.getSource();
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();
		if(col > 0) return;
		
		String name = (String) table.getValueAt(row, col);
		final Course selected = model.searchCourseByName(name);
		
		//If the current user is the teacher we display an editable course view
		if(model.getCurrentUser().equals(model.getTeacher())){
			UICourseEditable ce = new UICourseEditable(selected, model, view);
			ce.addControllerUnit(new UnitController(model, ce, view));
			ce.addControllerEditCourse(new EditCourseController(selected, view, model));
			view.addPanelWest(ce);
			
			List<Student> students = model.getStudents();
			
			String[] titles = {"Students"};
			Object[][] objs = new Object[students.size()][1];
			
			int i = 0;
			for(Student s : students){
				objs[i][0] = s.toString();
				i += 1;
			}
		
			UIStats std = new UIStats(objs, titles);
			view.addPanelEast(std);
		}
		//If not, well, that
		else{
			if(((Student) model.getCurrentUser()).getRegisteredCourses().contains(selected)){				
				UICourse cr = new UICourse(selected, model, view);
				view.addPanel(cr);
			}
			else if(((Student) model.getCurrentUser()).getPendingCourses().contains(selected))
				JOptionPane.showMessageDialog(view,"Your application for " + selected.getTitle() + " is still pending.", "Registration", JOptionPane.INFORMATION_MESSAGE);
			else{
				final Register register = new Register("You are not registered in " + selected.getTitle() + ". What would you like to do?");
				register.setControllerOK(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						model.applyStudent((Student) model.getCurrentUser(), selected);	
						JOptionPane.showMessageDialog(view,"You have applied for " + selected.getTitle(), "Registration", JOptionPane.INFORMATION_MESSAGE);
						register.dispose();
					}
					
				});
				register.setControllerCancel(new CancelController(register));
			}
		}
	}
}
