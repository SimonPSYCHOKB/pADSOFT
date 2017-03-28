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
		graded = true;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Answer other = (Answer) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		if (Double.doubleToLongBits(grade) != Double
				.doubleToLongBits(other.grade))
			return false;
		if (graded != other.graded)
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}
	
	public boolean isBlank(){
		
		if(answer.isEmpty()){
			return true;
		}else{
			return false;
		}
	}
	

}
