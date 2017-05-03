package gui.windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;

public class CreateTest extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JSpinner spinner1;
	private JSpinner spinner2;
	private JSpinner spinner3;
	private JCheckBox ok;
	private JButton cancel;
	private JButton edit;
	private JButton done;

	public CreateTest(){
		// Setting layout
		getContentPane().setLayout(new BorderLayout());
		
		//Panel for the test info
		JPanel test = new JPanel();
		SpringLayout layout = new SpringLayout();
		test.setLayout(layout);
		
		// Date of Beginning
		JLabel start = new JLabel("Date of beginning");
		SpinnerModel sm1 = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
		spinner1 = new JSpinner(sm1);
		layout.putConstraint(SpringLayout.NORTH, start, 50, SpringLayout.NORTH, test);
		layout.putConstraint(SpringLayout.WEST, start, 250, SpringLayout.WEST, test);
		test.add(start);
		layout.putConstraint(SpringLayout.NORTH, spinner1, 50, SpringLayout.NORTH, test);
		layout.putConstraint(SpringLayout.WEST, spinner1, 30, SpringLayout.EAST, start);
		test.add(spinner1);
		
		// Date of End
		JLabel end = new JLabel("Date of end");
		SpinnerDateModel sm2 = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
		spinner2 = new JSpinner(sm2);
		layout.putConstraint(SpringLayout.NORTH, end, 20, SpringLayout.SOUTH, start);
		layout.putConstraint(SpringLayout.WEST, end, 250, SpringLayout.WEST, test);
		test.add(end);
		layout.putConstraint(SpringLayout.NORTH, spinner2, 15, SpringLayout.SOUTH, spinner1);
		layout.putConstraint(SpringLayout.WEST, spinner2, 75, SpringLayout.EAST, end);
		test.add(spinner2);
		
		//Weigth
		JLabel weight = new JLabel("Weight");
		SpinnerModel sm3 = new SpinnerNumberModel(0, 0, 100, 0.1);
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
		layout.putConstraint(SpringLayout.NORTH, visibility, 20, SpringLayout.SOUTH, weight);
		layout.putConstraint(SpringLayout.WEST, visibility, 250, SpringLayout.WEST, test);
		test.add(visibility);
		layout.putConstraint(SpringLayout.NORTH, ok, 15, SpringLayout.SOUTH, spinner3);
		layout.putConstraint(SpringLayout.WEST, ok, 106, SpringLayout.EAST, visibility);
		test.add(ok);
		
		test.setVisible(true);
		add(test, BorderLayout.CENTER);

		//Panel for the buttons
		JPanel buttons = new JPanel(new FlowLayout());
		cancel = new JButton("Cancel");
		buttons.add(cancel);
		edit = new JButton("Edit");
		edit.setToolTipText("Add questions to the new test");
		buttons.add(edit);
		done = new JButton("Done");
		buttons.add(done);
		
		buttons.setVisible(true);
		add(buttons, BorderLayout.SOUTH);
		
		//Window settings
		setSize(800, 300);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void setControllerCancel(ActionListener al){
		cancel.addActionListener(al);
	}
	
	public void setControllerDone(ActionListener al){
		done.addActionListener(al);
	}
	
	public void setControllerEdit(ActionListener al){
		edit.addActionListener(al);
	}
	
	public double getWeight(){
		return (double) spinner3.getValue();
	}
	
	public Date getDateEnd(){
		return (Date) spinner2.getValue();
	}
	
	public Date getDateBegining(){
		return (Date) spinner1.getValue();
	}
	
	public boolean getVisibility(){
		return ok.isSelected();
	}
	
}
