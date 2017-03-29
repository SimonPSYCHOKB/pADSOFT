import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Simon Valcarcel
 * @author Blanca Martin
 * 
 * This class contains the information of the type of question single answer
 *
 */
public class SingleAnswer extends Question implements Serializable{
	
	/**
	 * 
	 */
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
	
	public String showQuestion(){
		String s = this.getQuestion();
		for(int i = 0; i < options.size(); i++)
			s = s + "\n\t" + options.get(i);
		return s;
	}

}
