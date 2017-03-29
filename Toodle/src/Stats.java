
/**
 * @author Simon Valcarcel
 * @author Blanca Martin
 * 
 * This class makes reference to the statistics
 *
 */
public abstract class Stats {
	
	private double mean;
	
	/**
	 * Constructor
	 */
	public Stats(){
		mean = 0;
	}
	
	/**
	 * This method returns the mean of a statistic
	 * @return mean - double
	 */
	public double getMean(){
		return mean;
	}
	
	/**
	 * This method sets the mean of a statistic to a value
	 * @param mean - double
	 */
	public void setMean(double mean){
		this.mean = mean;
	}
}
