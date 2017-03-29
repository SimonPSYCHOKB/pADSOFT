																																																				/**
 * 
 */

/**
 * @author Simon Valcarcel, Blanca Martin
 *
 */
public class FreeText extends Question{
	
	private String answer;
	/**
	 * 
	 */
	public FreeText(String question, double weight, double penalty, String answer) {
		super(question, weight, penalty);
		this.answer = answer;
	}

	public boolean checkIfCorrect(Answer answer){
		return this.answer.equals(answer.getAnswer().get(0));
	}
	
	public String showQuestion(){
		return this.getQuestion();
	}

}
