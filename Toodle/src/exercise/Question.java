package exercise;
import java.io.Serializable;

/**
 * @author Simon Valcarcel
 * @author Blanca Martin
 * 
 * This abstract class makes reference to the Questions in an Exercise
 *
 */
public abstract class Question implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String question;
	private double weight;
	private double penalty;
	private Exercise test;

	
	/**
	 * Constructor
	 * 
	 * @param question - String with the wording
	 * @param weight - double with the weight of the Question in an Exercise
	 * @param penalty - double with the points to be subtracted if the question isn't answered correctly 
	 */
	public Question(String question, double weight, double penalty) {
		this.question = question;
		this.weight = weight;
		this.penalty = penalty;
	}
	
	/**
	 * This method returns the wording of the Question
	 * @return question - String with the wording
	 */
	public String getQuestion(){
		return question;
	}
	
	public void setQuestion(String q){
		question = q;
	}
	
	/**
	 * This method returns the weight of the Question in an Exercise
	 * @return weight 
	 */
	public double getWeight(){
		return weight;
	}
	
	public void setWeight(double w){
		weight = w;
	}
	
	/**
	 * This method returns the penalty of the Question
	 * @return penalty
	 */
	public double getPenalty(){
		return penalty;
	}
	
	public void setPenalty(double p){
		penalty = p;
	}
	
	public void setExercise(Exercise test){
		this.test = test;
	}
	
	public double getRelativeWeight(){
		return weight/test.getTotal();
	}
	
	/**
	 * This method checks whether an Answer given to the Question is correct
	 * @param answer - Answer to the Question
	 * @return true if the answer is right, false if not
	 */
	public abstract boolean checkIfCorrect(Answer answer);
	
	/**
	 * This method shows the Question
	 * @return String with the question
	 */
	public abstract String showQuestion();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(penalty);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (Double.doubleToLongBits(penalty) != Double
				.doubleToLongBits(other.penalty))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (Double.doubleToLongBits(weight) != Double
				.doubleToLongBits(other.weight))
			return false;
		return true;
	}

	

}
