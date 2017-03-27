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

	private Test test;
	private List<Answer> answers;
	private double grade;

	/**
	 * 
	 */
	public AnsweredTest(Test test, List<Answer> answers) {
		this.test = test;
		this.answers = new ArrayList<Answer>();
		this.answers = answers;
	}

	public Test getTest(){
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
}
