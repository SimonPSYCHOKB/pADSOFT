package GUI;

import java.util.*;

import javax.swing.*;

import Test.*;

public class UIQuestion extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private List<AbstractButton> options;
	private JTextField answer;
	
	public UIQuestion(Question question) {
		options = new ArrayList<AbstractButton>();
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JLabel wording = new JLabel(question.getQuestion());
		add(wording);
		
		if(question instanceof SingleAnswer){
			ButtonGroup buttons = new ButtonGroup();
			for(String option : ((SingleAnswer) question).getOptions()){
				JRadioButton radio = new JRadioButton(option);
				buttons.add(radio);
				add(radio);
				options.add(radio);
			}
		}
		else if(question instanceof MultipleAnswer){
			for(String option : ((MultipleAnswer) question).getOptions()){
				JCheckBox radio = new JCheckBox(option);
				add(radio);
				options.add(radio);
			}
		}
		else {
			answer = new JTextField(10);
			add(answer);
		}
		
		setVisible(true);
	}
	
	public List<AbstractButton> getOptions(){
		return options;
	}
	
	public JTextField getField(){
		return answer;
	}

}
