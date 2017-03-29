/**
 * 
 */

/**
 * @author Simon Valcarcel
 * @author Blanca Martin
 * 
 * This abstract class makes reference to the Questions in an Exercise
 *
 */
public abstract class Question {
	
	private String question;
	private double weight;
	private double penalty;

	
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
	
	/**
	 * This method returns the weight of the Question in an Exercise
	 * @return weight 
	 */
	public double getWeight(){
		return weight;
	}
	
	/**
	 * This method returns the penalty of the Question
	 * @return penalty
	 */
	public double getPenalty(){
		return penalty;
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
