package exercise;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Simon Valcarcel
 * @author Blanca Martin
 * 
 * This class contains the information of the type of question single answer
 *
 */
public class SingleAnswer extends Question implements Serializable{

	private static final long serialVersionUID = 1L;

	private String answer;
	private List<String> options;
	
	/**
	 * Constructor 
	 * 
	 * @param question - String with the wording
	 * @param weight - double with the weight of the Question in an Exercise
	 * @param penalty - double with the points to be subtracted if the question isn't answered correctly 
	 * @param answer - String containing the correct answer
	 * @param options - List of String containing the options of the question
	 */
	public SingleAnswer(String question, double weight, double penalty, String answer, List<String> options) {
		super(question, weight, penalty);
		this.answer = answer;
		this.options = new ArrayList<String>();
		this.options = options;
	}
	
	public boolean checkIfCorrect(Answer answer){
		return this.answer.equals(answer.getAnswer().get(0));
	}
	
	/**
	 * This method returns the options of the question
	 * @return list with the options
	 */
	public List<String> getOptions(){
		return Collections.unmodifiableList(options);
	}
	
	/**
	 * This method sets the options of the question
	 * @param options - the new options
	 */
	public void setOptions(List<String> options){
		this.options = options;
	}
	
	/**
	 * This method returns the answer of the question
	 * @return the answer
	 */
	public String getAnswer(){
		return answer;
	}
	
	/**
	 * This method changes the answer of the question
	 * @param answ - the new answer
	 */
	public void setAnswer(String answ){
		answer = answ;
	}
	
	public String showQuestion(){
		String s = this.getQuestion();
		for(String option : options)
			s = s + "\n\t" + option;
		return s;
	}

}
