import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author Simon Valcarcel, Blanca Martin
 *
 */
public class Answer {
	
	private List<String> answer;
	private Question question;
	private double grade;
	private boolean graded;

	/**
	 * 
	 */
	public Answer(List<String> answer, Question question) {
		this.answer = new ArrayList<String>();
		this.answer = answer;
		this.question = question;
		grade = 0;
	}
	
	public List<String> getAnswer(){
		return answer;
	}
	
	public Question getQuestion(){
		return question;
	}
	
	public void correctAnswer(){
		double weight = question.getWeight();
		boolean is = question.checkIfCorrect(this);		
		if(is == true)
			grade = weight;
		else if(question.getPenalty() != 0)
			grade = -question.getPenalty();
	}
	
	public boolean isGraded(){
		return graded;
	}
	
	public double getGrade(){
		return grade;
	}

	
	@Override
	public String toString() {
		int i = 0;
		String s = new String();
		for( ; i < answer.size(); i++)
			s = s + " " + answer.get(i);
		return s;
	}
	
	

}
