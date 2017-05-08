package gui.windows;

import exercise.*;
import gui.CancelController;
import gui.panels.EditQuestion;
import gui.windows.controllers.CreateQuestionController;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

import javax.swing.*;

import application.Application;


/**
 * 
 * @author Blanca, Simon
 *
 * UI for Editing a Test. It allows the teacher to set the beginning and End dates,
 * the weight and the Penalization. Additionally, it loads the controller for creation 
 * of an exercise  
 */
public class EditTest extends JFrame{

	private static final long serialVersionUID = 1L;
	private JButton close;
	private List<EditQuestion> quest;
	private Exercise e;
	
	private JSpinner spinner1;
	private JSpinner spinner2;
	private JSpinner spinner3;
	private JCheckBox ok;
	private JButton remove;
	private JButton cancel;

	public EditTest(final Exercise e, final Application app, final General gen){
		this.e = e;
		final JFrame dispose = this;
		final EditTest update = this;
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		quest = new ArrayList<EditQuestion>();
		JPanel test = new JPanel();
		
		// Setting layout
		SpringLayout layout = new SpringLayout();
		test.setLayout(layout);
		
		// Date of Beginning
		JLabel start = new JLabel("Date of beginning");
		SpinnerModel sm1 = new SpinnerDateModel(e.getDateOfBegining(), null, null, Calendar.DAY_OF_MONTH);
		spinner1 = new JSpinner(sm1);
		layout.putConstraint(SpringLayout.NORTH, start, 50, SpringLayout.NORTH, test);
		layout.putConstraint(SpringLayout.WEST, start, 250, SpringLayout.WEST, test);
		test.add(start);
		layout.putConstraint(SpringLayout.NORTH, spinner1, 50, SpringLayout.NORTH, test);
		layout.putConstraint(SpringLayout.WEST, spinner1, 30, SpringLayout.EAST, start);
		test.add(spinner1);
		
		
		// Date of End
		JLabel end = new JLabel("Date of end");
		SpinnerDateModel sm2 = new SpinnerDateModel(e.getDateOfEnd(), null, null, Calendar.DAY_OF_MONTH);
		spinner2 = new JSpinner(sm2);
		layout.putConstraint(SpringLayout.NORTH, end, 20, SpringLayout.SOUTH, start);
		layout.putConstraint(SpringLayout.WEST, end, 250, SpringLayout.WEST, test);
		test.add(end);
		layout.putConstraint(SpringLayout.NORTH, spinner2, 15, SpringLayout.SOUTH, spinner1);
		layout.putConstraint(SpringLayout.WEST, spinner2, 75, SpringLayout.EAST, end);
		test.add(spinner2);

		
		//Weigth
		JLabel weight = new JLabel("Weight");
		SpinnerModel sm3 = new SpinnerNumberModel(e.getWeight(), 0, 100, 0.1);
		spinner3 = new JSpinner(sm3);
		layout.putConstraint(SpringLayout.NORTH, weight, 20, SpringLayout.SOUTH, end);
		layout.putConstraint(SpringLayout.WEST, weight, 250, SpringLayout.WEST, test);
		test.add(weight);
		layout.putConstraint(SpringLayout.NORTH, spinner3, 15, SpringLayout.SOUTH, spinner2);
		layout.putConstraint(SpringLayout.WEST, spinner3, 106, SpringLayout.EAST, weight);
		test.add(spinner3);

		
		//Visibility
		JLabel visibility = new JLabel("Visible");
		ok = new JCheckBox();
		ok.setSelected(e.isVisibility());
		layout.putConstraint(SpringLayout.NORTH, visibility, 20, SpringLayout.SOUTH, weight);
		layout.putConstraint(SpringLayout.WEST, visibility, 250, SpringLayout.WEST, test);
		test.add(visibility);
		layout.putConstraint(SpringLayout.NORTH, ok, 15, SpringLayout.SOUTH, spinner3);
		layout.putConstraint(SpringLayout.WEST, ok, 106, SpringLayout.EAST, visibility);
		test.add(ok);
		
		
		//Add Question
		JButton add = new JButton("Add Question");
		layout.putConstraint(SpringLayout.NORTH, add, 40, SpringLayout.SOUTH, visibility);
		layout.putConstraint(SpringLayout.WEST, add, 350, SpringLayout.WEST, test);
		test.add(add);
		
		add.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CreateQuestion cq = new CreateQuestion(e, dispose);
				cq.setControllerCancel(new CancelController(cq));
				cq.setControllerNext(new CreateQuestionController(cq, e, update, app, gen));
			}			
		});
		
		add(test);
		
		//The questions
		JTabbedPane questions = new JTabbedPane();
		questions.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		int i = 0;
		for(Question q : e.getQuestions()){
			EditQuestion eq = new EditQuestion(q);
			quest.add(eq);
			questions.addTab("Question "+i, eq);
			i = i + 1;
		}
		questions.setVisible(true);
		questions.setMinimumSize(new Dimension(800,300));
		add(questions);
	
		//Button
		JPanel buttons = new JPanel(new FlowLayout());
		cancel = new JButton("Cancel");
		buttons.add(cancel);
		remove = new JButton("Delete test");
		buttons.add(remove);
		close = new JButton("Save");	
		buttons.add(close);
		add(buttons);
		
		//Window settings
		setSize(800, 600);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void setControllerSave(ActionListener al){
		close.addActionListener(al);
	}
	
	public void setControllerDelete(ActionListener al){
		remove.addActionListener(al);
	}
	
	public void setControllerCancel(ActionListener al){
		cancel.addActionListener(al);
	}
	
	public List<EditQuestion> getQuestions(){
		return quest;
	}
	
	public Exercise getExercise(){
		return e;
	}
	
	public JSpinner getWeigth(){
		return spinner3;
	}
	
	public JSpinner getDateEnd(){
		return spinner2;
	}
	
	public JSpinner getDateBegining(){
		return spinner1;
	}	
	
	public JCheckBox getVisibility(){
		return ok;
	}
	
}
