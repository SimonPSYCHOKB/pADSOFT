import java.util.ArrayList;
import java.util.List;


public class SingleAnswer extends Question{

	private String answer;
	private List<String> options;
	
	public SingleAnswer(String question, double weight, double penalty, String answer, List<String> options) {
		super(question, weight, penalty);
		this.answer = answer;
		this.options = new ArrayList<String>();
		this.options = options;
	}
	
	public boolean checkIfCorrect(Answer answer){
		return this.answer.equals(answer.getAnswer().get(0));
	}

}
