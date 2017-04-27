package GUI;

import java.awt.event.*;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import Users.Student;

import Application.*;

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
			UICourseEditable ce = new UICourseEditable(selected, model);
			view.addPanel(ce);
		}
		//If not, well, that
		else{
			if(((Student) model.getCurrentUser()).getRegisteredCourses().contains(selected)){				
				UICourse cr = new UICourse(selected, model);
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
				register.setControllerCancel(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						register.dispose();
						
					}
					
				});
			}
		}
	}
}
