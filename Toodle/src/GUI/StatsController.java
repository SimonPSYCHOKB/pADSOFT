package GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

import Statistics.*;

import Application.*;

public class StatsController implements ActionListener {

	private Application model;
	private General view;
	
	public StatsController(General view, Application model){
		this.view = view;
		this.model = model;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		final List<Course> courses = model.getCourses();
		
		String titles[] = {"Course", "Mean"};
		Object[][] objs = new Object[courses.size()][2];
		int i = 0;
		final List<CourseStatistic> cs = new ArrayList<CourseStatistic>();
		for(Course cr : courses){
			CourseStatistic aux = new CourseStatistic(cr);
			cs.add(aux);
			objs[i][0] = cr.getTitle();
			objs[i][1] = aux.getMean();
			i += 1;
		}
		
		final JTable course = new JTable(objs, titles);
		DefaultTableModel tableModel = new DefaultTableModel(objs, titles){
			
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		course.setModel(tableModel);
		course.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		course.setVisible(true);
		
		JScrollPane scrollBar = new JScrollPane(course);
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(scrollBar);
		panel.setVisible(true);
		
		view.addPanel(panel);
		SwingUtilities.updateComponentTreeUI(view);
		
		course.addMouseListener(new MouseAdapter()	{
			public void mouseClicked(MouseEvent me) {
				
				int row = course.getSelectedRow();
				CourseStatistic current = cs.get(row);
				
				final List<Statistic> exe = current.getStatistics();
				System.out.println(cs.toString());
				
				Object[][] objs = new Object[exe.size()][2];
				String[] titles = {"Exercise", "Mean"};
				
				int i = 0;
				for(Statistic e : exe){
					objs[i][0] = "Exercise " + i;
					objs[i][1] = e.getMean();
					i += 1;
				}
				
				final JTable exercises = new JTable(objs, titles);
				DefaultTableModel tableModel = new DefaultTableModel(objs, titles) {

					private static final long serialVersionUID = 1L;

					@Override
				    public boolean isCellEditable(int row, int column) {
				       //all cells false
				       return false;
				    }
				};
				exercises.setModel(tableModel);
				exercises.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				exercises.setVisible(true);
				
				JScrollPane scrollBar =	new	JScrollPane(exercises);
				
				JPanel panel2 = new JPanel();
				panel2.setLayout(new FlowLayout());
				panel2.add(scrollBar);
				panel2.setVisible(true);
				
				view.addPanel(panel2);
				
				SwingUtilities.updateComponentTreeUI(view);
				
				exercises.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e){
						
						int row = exercises.getSelectedRow();
						
						Statistic current = exe.get(row);
						List<QuestionStatistic> questions = current.getQuestionStatistics();
						
						Object[][] objs = new Object[questions.size()][5];
						String[] titles = {"Question", "Mean", "Wrong", "Right", "Blank"};
						
						int i = 0;
						for(QuestionStatistic qs : questions){
							objs[i][0] = "Question " + i;
							objs[i][1] = qs.getMean();
							objs[i][2] = qs.getWrongNumber();
							objs[i][3] = qs.getCorrectNumber();
							objs[i][4] = qs.getBlankNumber();
							i += 1;
						}
						
						JTable question = new JTable(objs, titles);
						DefaultTableModel tableModel = new DefaultTableModel(objs, titles) {

							private static final long serialVersionUID = 1L;

							@Override
						    public boolean isCellEditable(int row, int column) {
						       //all cells false
						       return false;
						    }
						};
						question.setModel(tableModel);
						question.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						question.setVisible(true);
						
						JScrollPane scrollBar =	new	JScrollPane(question);
						
						JPanel panel2 = new JPanel();
						panel2.setLayout(new FlowLayout());
						panel2.add(scrollBar);
						panel2.setVisible(true);
						
						view.addPanel(panel2);
						
						SwingUtilities.updateComponentTreeUI(view);
					}
				});
			}
		});
		
		
	}

}
