package gui.panels;

import java.util.*;

import javax.swing.*;

import exercise.*;



/**
 * This class makes reference to the view of a question to be answered
 * @author Simon, Blanca
 *
 */
public class UIQuestion extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private List<AbstractButton> options;
	private JTextField answer;
	
	/**
	 * Constructor
	 * @param question - the question to be displayed
	 */
	public UIQuestion(Question question) {
		options = new ArrayList<AbstractButton>();
		
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
	
		JLabel wording = new JLabel(question.getQuestion() +" (" + question.getWeight() + " points)");
		layout.putConstraint(SpringLayout.NORTH, wording, 150, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, wording, 150, SpringLayout.WEST, this);
		add(wording);
		
		JComponent previous = wording;
		if(question instanceof SingleAnswer){
			ButtonGroup buttons = new ButtonGroup();
			for(String option : ((SingleAnswer) question).getOptions()){
				JRadioButton radio = new JRadioButton(option);
				layout.putConstraint(SpringLayout.NORTH, radio, 50, SpringLayout.NORTH, previous);
				layout.putConstraint(SpringLayout.WEST, radio, 150, SpringLayout.WEST, this);
				buttons.add(radio);
				add(radio);
				options.add(radio);
				
				previous = radio;
			}
		}
		else if(question instanceof MultipleAnswer){
			for(String option : ((MultipleAnswer) question).getOptions()){
				JCheckBox radio = new JCheckBox(option);
				layout.putConstraint(SpringLayout.NORTH, radio, 50, SpringLayout.NORTH, previous);
				layout.putConstraint(SpringLayout.WEST, radio, 150, SpringLayout.WEST, this);
				add(radio);
				options.add(radio);
				
				previous = radio;
			}
		}
		else {	
			answer = new JTextField(10);
			layout.putConstraint(SpringLayout.NORTH, answer, 50, SpringLayout.NORTH, previous);
			layout.putConstraint(SpringLayout.WEST, answer, 150, SpringLayout.WEST, this);
			add(answer);
		}
		
		setVisible(true);
	}
	
	/**
	 * This method returns the options of the question
	 * @return buttons of the options
	 */
	public List<AbstractButton> getOptions(){
		return options;
	}
	
	/**
	 * This method returns the answer of the question
	 * @return field with the answer
	 */
	public JTextField getField(){
		return answer;
	}

}
