package Test;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Simon Valcarcel
 * @author Blanca Martin
 * 
 * This class contains the information of a question's answer given by a student
 *
 */
public class Answer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<String> answer;
	private Question question;
	private double grade;
	private boolean graded;

	
	/**
	 * Constructor 
	 * @param answer -The List of String containing the answers
	 * @param question - The Question answered
	 */
	public Answer(List<String> answer, Question question) {
		this.answer = new ArrayList<String>();
		this.answer = answer;
		this.question = question;
		grade = 0;
	}
	
	/**
	 * This method returns the answers of the student
	 * @return answer - The List of String containing the answers
	 */
	public List<String> getAnswer(){
		return answer;
	}
	
	/**
	 * This method returns the Question answered
	 * @return question - The Question answered
	 */
	public Question getQuestion(){
		return question;
	}
	
	/**
	 * This method corrects an Answer and saves the grade in the information of the Answer
	 */
	private void correctAnswer(){
		double weight = question.getWeight();
		boolean is = question.checkIfCorrect(this);		
		if(is == true)
			grade = weight;
		else if(question.getPenalty() != 0)
			grade = -question.getPenalty();
		graded = true;
	}
	
	/**
	 * This method returns the grade of an Answer
	 * @return grade
	 */
	public double getGrade(){
		if(graded == false)
			correctAnswer();
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
	
	/**
	 * This method returns whether an answer has been left blank
	 * @return true if is blank, false if not
	 */
	public boolean isBlank(){
		
		if(answer.isEmpty()){
			return true;
		}else{
			return false;
		}
	}
	

}