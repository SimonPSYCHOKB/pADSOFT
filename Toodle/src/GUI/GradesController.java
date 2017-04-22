package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;

import Statistics.*;
import Users.*;

import Application.*;

public class GradesController implements ActionListener{
	
	private Application model;
	private General view;
		
	public GradesController(Application model, General view){
		this.model = model;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Student student = model.searchStudentByName(model.getCurrentUser().getName());
		SingleUserStatistic stats = new SingleUserStatistic(student);
		List<CourseStatistic> cs = stats.getCourseStatistics();
		
		String titles[] = {"Course", "Mean"};
		Object[][] objs = new Object[cs.size()][2];
		
		int i = 0;
		for(CourseStatistic c : cs){
			objs[i][0] = c.getCourse();
			objs[i][1] = c.getMean();
			i += 1;
		}
		
		JTable courses = new JTable(objs, titles);
		courses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		courses.setVisible(true);
		
		JScrollPane scrollBar =	new	JScrollPane(courses);
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(scrollBar);
		panel.setVisible(true);
		
		view.addPanel(panel);
		
		SwingUtilities.updateComponentTreeUI(view);
	}

}