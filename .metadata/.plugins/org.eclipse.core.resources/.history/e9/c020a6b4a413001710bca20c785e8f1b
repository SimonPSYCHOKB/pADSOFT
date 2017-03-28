import java.util.ArrayList;
import java.util.List;


public class TrueFalse extends Question{
	
	private String answer;
	private List<String> options;

	public TrueFalse(String question, double weight, double penalty, String answer) {
		super(question, weight, penalty);
		this.answer = answer;
		this.options = new ArrayList<String>();
		this.options.add("true"); this.options.add("false");
	}

	public String getAnswer() {
		return answer;
	}


	public void setAnswer(String answer) {
		this.answer = answer;
	}


	public boolean checkIfCorrect(Answer answer){
		return this.answer.equals(answer.getAnswer().get(0));
	}
}
