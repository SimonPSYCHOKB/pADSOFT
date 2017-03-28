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
