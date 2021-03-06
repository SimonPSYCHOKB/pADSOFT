package exercise;
import java.io.Serializable;

/**
 * @author Simon Valcarcel
 * @author Blanca Martin
 * 
 * This class contains the information of the type of question free text
 *
 */
public class FreeText extends Question implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String answer;
	
	/**
	 * Constructor
	 * 
	 * @param question - String with the wording
	 * @param weight - double with the weight of the Question in an Exercise
	 * @param penalty - double with the points to be subtracted if the question isn't answered correctly 
	 * @param answer - String containing the correct answer
	 */
	public FreeText(String question, double weight, double penalty, String answer) {
		super(question, weight, penalty);
		this.answer = answer;
	}

	public boolean checkIfCorrect(Answer answer){
		return this.answer.equalsIgnoreCase(answer.getAnswer().get(0));
	}
	
	public String showQuestion(){
		return this.getQuestion();
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

}
