
public class TrueFalse extends Question{
	
	private String answer;

	public TrueFalse(String question, double weight, double penalty, String answer) {
		super(question, weight, penalty);
		this.answer = answer;
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
