import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author blanca
 *
 */
public class AnsweredTest {

	private Exercise test;
	private List<Answer> answers;
	private double grade;

	/**
	 * 
	 */
	public AnsweredTest(Exercise test, List<Answer> answers) {
		this.test = test;
		this.answers = new ArrayList<Answer>();
		this.answers = answers;
	}

	public Exercise getTest(){
		return test;
	}
	
	public List<Answer> getAnswers(){
		return answers;
	}
	
	public void correctAnsweredTest(){
		int i = 0;
		double cnt = 0;
		for( ; i < answers.size(); i++){
			answers.get(i).correctAnswer();
			cnt = cnt + answers.get(i).getGrade();
		}
		if(cnt < 0)
			grade = 0;
		else 
			grade = cnt;
	}
	
	public double getGradeTest(){
		return grade*10/test.getTotal();
	}
	
	public double getRelativeGradeTest(){
		return getGradeTest()*test.getWeight();
	}
	
	@Override
	public String toString() {
		int i = 0;
		String s = new String();
		for( ; i < answers.size(); i++){
			//s.concat(test.showQuestionTest() + " Answer: " + ((answers.get(i) != null) ? answers.get(i) : "Not answered"));
			s = s + "\n" + answers.get(i).getQuestion().getQuestion() + " Answer: " + ((answers.get(i).getAnswer() != null) ? answers.get(i) : "Not answered");
		}
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
		AnsweredTest other = (AnsweredTest) obj;
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
			return false;
		if (Double.doubleToLongBits(grade) != Double
				.doubleToLongBits(other.grade))
			return false;
		if (test == null) {
			if (other.test != null)
				return false;
		} else if (!test.equals(other.test))
			return false;
		return true;
	}
	
}
