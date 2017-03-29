import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * @author Simon Valcarcel
 * @author Blanca Martin
 * 
 * This class contains the information of the statistic of a question
 *
 */
public class QuestionStatistic implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private double correctNumber=0;
	private double wrongNumber=0;
	private double blankNumber=0;
	
	private Question question;
	
	private double mean=0;
	
	/**
	 * Constructor
	 * @param q - The Question for the statistic
	 * @param aGiven - List of Answers to the question 
	 */
	public QuestionStatistic (Question q, List<Answer> aGiven ){
		question = q;
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
	
	/**
	 * This method returns the correct number of answers to the question
	 * @return correctNumber - double
	 */
	public double getCorrectNumber() {
		return correctNumber;
	}
	
	/**
	 * This method returns the wrong number of answers to the question
	 * @return wrongNumber - double
	 */
	public double getWrongNumber() {
		return wrongNumber;
	}
	
	/**
	 * This method returns the blank number of answers to the question
	 * @return blankNumber - double
	 */
	public double getBlankNumber() {
		return blankNumber;
	}

	/**
	 * This method returns the mean of the statistic of the question
	 * @return mean - double
	 */
	public double getMean() {
		return mean;
	}
	
	@Override
	public String toString(){
		NumberFormat formatter = new DecimalFormat("#0.00"); 
		return "mean: "+mean+ " correct: " +formatter.format(correctNumber)+
	" wrong: " + formatter.format(wrongNumber) +
	" blank: " + formatter.format(blankNumber);
	}

	
	
	

}
