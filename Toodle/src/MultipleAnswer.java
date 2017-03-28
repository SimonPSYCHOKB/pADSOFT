import java.util.ArrayList;
import java.util.List;

/**
 * @author Simon Valcarcel, Blanca Martin
 *
 */

public class MultipleAnswer extends Question{

	private List<String> answers;
	private List<String> options;
	
	public MultipleAnswer(String question, double weight, double penalty, List<String> answers, List<String> options) {
		super(question, weight, penalty);
		this.answers = new ArrayList<String>();
		this.options = new ArrayList<String>();
		this.answers = answers;
		this.options = options;
	}

	public boolean checkIfCorrect(Answer answer){
		int n = answer.getAnswer().size();
		int m = this.answers.size();
		List<String> a = new ArrayList<String>();
		a = answer.getAnswer();
		if (n != m)
			return false;
		for(int i = 0, flag = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				if(a.get(i).equals(this.answers.get(j)) == true){
					flag = 1;
				}
			}
			if(flag == 0)
				return false;
			else
				flag = 0;
		}
		return true;
	}

}
