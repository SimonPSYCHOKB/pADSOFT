package gui.windows.controllers;

import gui.BackController;
import gui.panels.UIStats;
import gui.panels.controllers.ExercisesStatsController;
import gui.windows.General;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.JTable;

import statistics.CourseStatistic;
import statistics.SingleUserStatistic;
import statistics.Statistic;
import users.Student;

import application.Application;
import application.Course;

/**
 * This class makes reference to the controller of the menu option students in the menu 
 * bar, creates a table with all the students and displays it for the teacher to consoult
 * one of the students grades in the courses he/she is registered in, the exercises in the course
 * and the questions of the tests.
 * 
 * @author Simon Valcarcel
 * @author Blanca Martin
 *
 */
public class StudentsController implements ActionListener{
	
	private General view;
	private Application model;
	
	/**
	 * Contructor
	 * @param view - the general frame
	 * @param model - the application
	 */
	public StudentsController(General view, Application model){
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		final List<Student> students = model.getStudents();
		
		String[] titles = {"Title"};
		Object[][] objs = new Object[students.size()][1];
		
		int i = 0;
		for(Student s : students){
			objs[i][0] = s.toString();
			i += 1;
		}
	
		UIStats std = new UIStats(objs, titles);
		std.addController(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				int row = ((JTable) e.getSource()).getSelectedRow();
				final Student student = model.searchStudentByName(students.get(row).getName());
				SingleUserStatistic stats = new SingleUserStatistic(student);
				final List<CourseStatistic> cs = stats.getCourseStatistics();
				Object[][] objs = new Object[cs.size()][2];
				String[] titles = {"Course", "Mean"};
				
				int i = 0;
				for(CourseStatistic c : cs){
					objs[i][0] = c.getCourse().getTitle();
					if(c.getCourse().getTests().isEmpty() == true)
						objs[i][1] = "--";
					else objs[i][1] = c.getMean();
						i += 1;
				}
				UIStats s = new UIStats(objs, titles);
				view.addPanel(s);
				SwingUtilities.updateComponentTreeUI(view);
				s.addController(new MouseAdapter(){
					@Override
					public void mouseClicked(MouseEvent e){
						int row = ((JTable) e.getSource()).getSelectedRow();
						
						Course c;
						Object[][] objs;
						String[] titles = {"Exercise", "Mean"};
						
						SingleUserStatistic statis = new SingleUserStatistic(student);
						List<CourseStatistic> cs = statis.getCourseStatistics();			
						CourseStatistic course = cs.get(row);
						
						objs = new Object[course.getStatistics().size()][2];
						
						int i = 0;
						List<Statistic> stats = cs.get(row).getStatistics();
						for(Statistic s : stats){
							objs[i][0] = "Exercise " + i;
							objs[i][1] = s.getMean();
							i += 1;
						}
						c = course.getCourse();
						
						UIStats es = new UIStats(objs, titles);
						
						JButton back = new JButton("Back");
						back.addActionListener(new BackController(view));
						es.addButton(back);
						
						view.addPanel(es);		
						SwingUtilities.updateComponentTreeUI(view);
						es.addController(new ExercisesStatsController(c, view, model));
						
					}
				});	
			}
		});
		view.addPanel(std);
		
		
	}

}
