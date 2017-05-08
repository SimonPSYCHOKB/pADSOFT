package gui.windows;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import exercise.*;


/**
 * 
 * @author Blanca, Simon
 *
 *Here you choose the type of question you are going to create, as well as the wording,
 *the weight and the penalization
 */
public class CreateQuestion extends JFrame{

	private static final long serialVersionUID = 1L;

	private JButton cancel;
	private JButton next;
	private JTextField w;
	private JComboBox<String> types;

	private JSpinner spinner;

	private JSpinner spinner2;

	public CreateQuestion(final Exercise e, final JFrame et){
		super("Add question");
		
		//BorderLayout
		setLayout(new BorderLayout());
		
		//Info Panel
		JPanel info = new JPanel();
		SpringLayout layout = new SpringLayout();
		info.setLayout(layout);
		
		
		//Wording
		JLabel wording = new JLabel("Wording :");
		w = new JTextField(20);
		
		layout.putConstraint(SpringLayout.NORTH, wording, 40, SpringLayout.NORTH, info);
		layout.putConstraint(SpringLayout.WEST, wording, 150, SpringLayout.WEST, info);
		layout.putConstraint(SpringLayout.NORTH, w, 40, SpringLayout.NORTH, info);
		layout.putConstraint(SpringLayout.WEST, w, 30, SpringLayout.EAST, wording);
		info.add(wording);
		info.add(w);
		
		
		//Question type
		String[] t = {"Single Answer", "Multiple Choice", "Free Text", "True-False"};
		JLabel type = new JLabel("Type of question :");
		types = new JComboBox<String>(t);
		types.setSelectedIndex(0);
		
		layout.putConstraint(SpringLayout.NORTH, type, 30, SpringLayout.NORTH, wording);
		layout.putConstraint(SpringLayout.WEST, type, 150, SpringLayout.WEST, info);
		layout.putConstraint(SpringLayout.NORTH, types, 30, SpringLayout.NORTH, w);
		layout.putConstraint(SpringLayout.WEST, types, 30, SpringLayout.EAST, type);
		info.add(type);
		info.add(types);
		
		
		//Weight
		JLabel weight = new JLabel("Weight :");
		SpinnerModel sm = new SpinnerNumberModel(0,0,100,0.1);
		spinner = new JSpinner(sm);
		
		layout.putConstraint(SpringLayout.NORTH, weight, 30, SpringLayout.NORTH, type);
		layout.putConstraint(SpringLayout.WEST, weight, 150, SpringLayout.WEST, info);
		layout.putConstraint(SpringLayout.NORTH, spinner, 30, SpringLayout.NORTH, types);
		layout.putConstraint(SpringLayout.WEST, spinner, 30, SpringLayout.EAST, weight);
		info.add(weight);
		info.add(spinner);
		
		
		//Penalty
		JLabel penalty = new JLabel("Penalty :");
		SpinnerModel sm2 = new SpinnerNumberModel(0,0,100,0.1);
		spinner2 = new JSpinner(sm2);
		
		layout.putConstraint(SpringLayout.NORTH, penalty, 30, SpringLayout.NORTH, weight);
		layout.putConstraint(SpringLayout.WEST, penalty, 150, SpringLayout.WEST, info);
		layout.putConstraint(SpringLayout.NORTH, spinner2, 30, SpringLayout.NORTH, spinner);
		layout.putConstraint(SpringLayout.WEST, spinner2, 30, SpringLayout.EAST, penalty);
		info.add(penalty);
		info.add(spinner2);
		
		info.setVisible(true);
		info.setPreferredSize(new Dimension(500, 150));
		
		add(info, BorderLayout.CENTER);
		
		
		//Buttons
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		
		cancel = new JButton("Cancel");
		next = new JButton("Next");
		buttons.add(cancel);
		buttons.add(next);
		
		add(buttons, BorderLayout.SOUTH);
		
		setSize(600, 250);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void setControllerNext(ActionListener al){
		next.addActionListener(al);
	}
	
	public void setControllerCancel(ActionListener al){
		cancel.addActionListener(al);
	}
	
	public JTextField getWording(){
		return w;
	}
	
	public JSpinner getWeight(){
		return spinner;
	}
	
	public JSpinner getPenalty(){
		return spinner2;
	}
	
	public JComboBox<String> getTypeQuestion(){
		return types;
	}
}
