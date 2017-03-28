/**
 * 
 */

/**
 * @author blanca
 *
 */
public abstract class Question {
	
	private String question;
	private double weight;
	private double penalty;

	/**
	 * 
	 */
	public Question(String question, double weight, double penalty) {
		this.question = question;
		this.weight = weight;
		this.penalty = penalty;
	}
	
	//public Statistic getStatistics();
	
	public String getQuestion(){
		return question;
	}
	
	public double getWeight(){
		return weight;
	}
	
	public double getPenalty(){
		return penalty;
	}
	
	public abstract boolean checkIfCorrect(Answer answer);

}
