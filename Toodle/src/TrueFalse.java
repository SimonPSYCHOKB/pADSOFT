import java.util.ArrayList;
import java.util.List;


/**
 * @author Simon Valcarcel
 * @author Blanca Martin
 * 
 * This class contains the information of the type of question true or false
 *
 */
public class TrueFalse extends Question{
	
	private String answer;
	private List<String> options;

	/**
	 * Constructor 
	 * 
	 * @param question - String with the wording
	 * @param weight - double with the weight of the Question in an Exercise
	 * @param penalty - double with the points to be subtracted if the question isn't answered correctly 
	 * @param answer - String containing the correct answer
	 */
	public TrueFalse(String question, double weight, double penalty, String answer) {
		super(question, weight, penalty);
		this.answer = answer;
		this.options = new ArrayList<String>();
		this.options.add("true"); this.options.add("false");
	}

	public boolean checkIfCorrect(Answer answer){
		return this.answer.equals(answer.getAnswer().get(0));
	}
	
	public String showQuestion(){
		return this.getQuestion() + "\n\ttrue\n\tfalse";
	}
}
