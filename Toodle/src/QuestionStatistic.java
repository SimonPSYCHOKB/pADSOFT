import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class QuestionStatistic implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double correctNumber=0;
	private double wrongNumber=0;
	private double blankNumber=0;
	
	private Question question;
	private ArrayList<Answer> answers = new ArrayList<Answer>();
	
	private double mean=0;
	
	public QuestionStatistic (Question q, ArrayList<Answer> aGiven ){
		question = q;
		answers = aGiven;
		double people=0;
		
		for(Answer a : aGiven){
			if(a.isBlank()){
				blankNumber++;
			}
			else if(question.checkIfCorrect(a)){
				correctNumber++;
			}else{
				wrongNumber++;
			}
			
			people++;
			
		}//For answer
		
		//Compute average
		
		mean = ((double)(correctNumber/people))*10;
		
	}
	
	public double getCorrectNumber() {
		return correctNumber;
	}
	public void setCorrectNumber(int correctNumber) {
		this.correctNumber = correctNumber;
	}
	public double getWrongNumber() {
		return wrongNumber;
	}
	public void setWrongNumber(int wrongNumber) {
		this.wrongNumber = wrongNumber;
	}
	public double getBlankNumber() {
		return blankNumber;
	}
	public void setBlankNumber(int blankNumber) {
		this.blankNumber = blankNumber;
	}
	public ArrayList<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(ArrayList<Answer> answers) {
		this.answers = answers;
	}

	public double getMean() {
		return mean;
	}
	
	public String toString(){
		NumberFormat formatter = new DecimalFormat("#0.00"); 
		return "mean: "+mean+ " correct: " +formatter.format(correctNumber)+
	" wrong: " + formatter.format(wrongNumber) +
	" blank: " + formatter.format(blankNumber);
	}

	
	
	

}
