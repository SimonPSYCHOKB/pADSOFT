package gui.panels;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import exercise.*;

/**
 * This class makes reference to the panel that shows an answered question, showing too the correct answer
 * @author Simon, Blanca
 *
 */
public class UIAnsweredQuestion extends JPanel{

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * @param question - the question 
	 * @param answer - the answer to the question
	 */
	public UIAnsweredQuestion(Question question, Answer answer) {
		
		//Layout for the panel
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
	
		//Wwording of the question
		JLabel wording = new JLabel(question.getQuestion());
		layout.putConstraint(SpringLayout.NORTH, wording, 150, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, wording, 150, SpringLayout.WEST, this);
		add(wording);
		
		JComponent previous = wording;
		if(question instanceof SingleAnswer){
			String answ = answer.getAnswer().get(0);
			ButtonGroup buttons = new ButtonGroup();
			for(String option : ((SingleAnswer) question).getOptions()){
				JRadioButton radio = new JRadioButton(option);
				if(option.equals(((SingleAnswer) question).getAnswer())){
					Font font = radio.getFont();
					Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
					attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
					radio.setFont(font.deriveFont(attributes));
				}
				if(option.equals(answ)){
					radio.setSelected(true);
				}
				radio.setBorderPainted(false);
				radio.setFocusPainted(false);
				layout.putConstraint(SpringLayout.NORTH, radio, 50, SpringLayout.NORTH, previous);
				layout.putConstraint(SpringLayout.WEST, radio, 150, SpringLayout.WEST, this);
				buttons.add(radio);
				add(radio);
				
				previous = radio;
			}
		}
		else if(question instanceof MultipleAnswer){
			List<String> answ = answer.getAnswer();
			for(String option : ((MultipleAnswer) question).getOptions()){
				JCheckBox radio = new JCheckBox(option);
				if((((MultipleAnswer) question).getAnswer()).contains(option)){
					Font font = radio.getFont();
					Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
					attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
					radio.setFont(font.deriveFont(attributes));
				}
				if(answ.contains(option)){
					radio.setSelected(true);
				}
				radio.setBorderPainted(false);
				radio.setFocusPainted(false);				layout.putConstraint(SpringLayout.NORTH, radio, 50, SpringLayout.NORTH, previous);
				layout.putConstraint(SpringLayout.WEST, radio, 150, SpringLayout.WEST, this);
				add(radio);
				
				previous = radio;
			}
		}
		else {	
			JTextField field = new JTextField(answer.getAnswer().get(0));
			JLabel right;
			if(question instanceof FreeText)
				right = new JLabel(((FreeText) question).getAnswer());
			else
				right = new JLabel(((TrueFalse) question).getAnswer());
			field.setEditable(false);
			layout.putConstraint(SpringLayout.NORTH, field, 50, SpringLayout.NORTH, previous);
			layout.putConstraint(SpringLayout.WEST, field, 150, SpringLayout.WEST, this);
			add(field);
			layout.putConstraint(SpringLayout.NORTH, right, 50, SpringLayout.NORTH, field);
			layout.putConstraint(SpringLayout.WEST, right, 150, SpringLayout.WEST, this);
			add(right);
		}
		
		setVisible(true);
	}
}
