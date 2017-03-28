
public class SingleAnswer extends Question{

	private String answer;
	
	public SingleAnswer(String question, double weight, double penalty, String answer) {
		super(question, weight, penalty);
		this.answer = answer;
		//System.out.println(answer);
	}
	
	public boolean checkIfCorrect(Answer answer){
		return this.answer.equals(answer.getAnswer().get(0));
	}

}
