import java.util.ArrayList;
import java.util.List;

/**
 * @author Simon Valcarcel, Blanca Martin
 *
 */

public class MultipleAnswer extends Question{

	private List<String> answers;
	
	public MultipleAnswer(String question, double weight, double penalty, List<String> answers) {
		super(question, weight, penalty);
		this.answers = new ArrayList<String>();
		this.answers = answers;
	}

	public boolean checkIfCorrect(Answer answer){
		int n = answer.getAnswer().size();
		int m = this.answers.size();
		int i = 0;
		if (n != m)
			return false;
		for( ; i < n; i++)
			if(this.answers.contains(answers.get(i)) == false)
				return false;
		return true;
	}

}
