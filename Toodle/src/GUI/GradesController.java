package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
		final List<CourseStatistic> cs = stats.getCourseStatistics();
		
		String titles[] = {"Course", "Mark"};
		final Object[][] objs = new Object[cs.size()][2];
		
		int i = 0;
		for(CourseStatistic c : cs){
			objs[i][0] = c.getCourse().getTitle();
			if(c.getCourse().getTests().isEmpty() == true)
				objs[i][1] = "--";
			else objs[i][1] = c.getMean();
			i += 1;
		}
		
		final JTable courses = new JTable(objs, titles);
		DefaultTableModel tableModel = new DefaultTableModel(objs, titles) {

			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		courses.setModel(tableModel);
		courses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		courses.setVisible(true);
		
		JScrollPane scrollBar =	new	JScrollPane(courses);
	
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(scrollBar);
		panel.setVisible(true);
		
		view.addPanel(panel);
		
		SwingUtilities.updateComponentTreeUI(view);
		
		courses.addMouseListener(new MouseAdapter()	{
			public void mouseClicked(MouseEvent e) {
				
				int row = courses.getSelectedRow();
				final CourseStatistic course = cs.get(row);
				
				Object[][] objs = new Object[course.getStatistics().size()][2];
				String[] titles = {"Exercise", "Mark"};
				
				int i = 0;
				final List<Statistic> stats = cs.get(row).getStatistics();
				for(Statistic s : stats){
					objs[i][0] = "Exercise " + i;
					objs[i][1] = s.getMean();
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
						List<QuestionStatistic> questions = stats.get(row).getQuestionStatistics();
						
						Object[][] objs = new Object[questions.size()][2];
						String[] titles = {"Question", "Mark"};
						
						int i = 0;
						for(QuestionStatistic qs : stats.get(row).getQuestionStatistics()){
							objs[i][0] = "Question " + i;
							objs[i][1] = qs.getMean();
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
