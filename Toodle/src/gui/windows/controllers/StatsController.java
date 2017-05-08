package gui.windows.controllers;

import gui.panels.UIStats;
import gui.panels.controllers.UIStatsController;
import gui.windows.General;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


import javax.swing.*;

import statistics.CourseStatistic;
import statistics.SingleUserStatistic;
import users.Student;

import application.*;



/**
 * 
 * @author Blanca, Simon
 * 
 * Loads the relevant Statistics for the current user. If the User is a Student, it generates
 * a SingleUserStatistic which is loaded with the UiStatsController 
 *
 */
public class StatsController implements ActionListener {

	private Application model;
	private General view;
	
	public StatsController(General view, Application model){
		this.view = view;
		this.model = model;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		List<Course> courses = model.getCourses();
		
		String titles[] = {"Course", "Mean"};
		Object[][] objs;
		if(model.getCurrentUser().equals(model.getTeacher())){
			int i = 0;
			objs = new Object[courses.size()][2];
			List<CourseStatistic> c = new ArrayList<CourseStatistic>();
			for(Course cr : courses){
				CourseStatistic aux = new CourseStatistic(cr);
				c.add(aux);
				objs[i][0] = cr.getTitle();
				objs[i][1] = aux.getMean();
				i += 1;
			}
		}
		else{
			Student student = model.searchStudentByName(model.getCurrentUser().getName());
			SingleUserStatistic stats = new SingleUserStatistic(student);
			final List<CourseStatistic> cs = stats.getCourseStatistics();
			objs = new Object[cs.size()][2];
			
			int i = 0;
			for(CourseStatistic c : cs){
				objs[i][0] = c.getCourse().getTitle();
				if(c.getCourse().getTests().isEmpty() == true)
					objs[i][1] = "--";
				else objs[i][1] = c.getMean();
				i += 1;
			}
		}
		UIStats cs = new UIStats(objs, titles);
		view.addPanel(cs);
		SwingUtilities.updateComponentTreeUI(view);
		cs.addController(new UIStatsController(model, view));		
		
	}

}
