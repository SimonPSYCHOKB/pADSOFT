package Test;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Simon Valcarcel
 * @author Blanca Martin
 * 
 * This class contains the information of the type of question multiple answer
 */

public class MultipleAnswer extends Question implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<String> answers;
	private List<String> options;
	
	/**
	 * Constructor
	 * 
	 * @param question - String with the wording
	 * @param weight - double with the weight of the Question in an Exercise
	 * @param penalty - double with the points to be subtracted if the question isn't answered correctly 
	 * @param answers - List of String containing the correct answers
	 * @param options - List of String containing the different options of the answer
	 */
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
	
	public List<String> getOptions(){
		return options;
	}
	
	public String showQuestion(){
		String s = this.getQuestion();
		for(int i = 0; i < options.size(); i++)
			s = s + "\n\t" + options.get(i);
		return s;
	}

}
