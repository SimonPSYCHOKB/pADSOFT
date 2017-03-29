import java.util.ArrayList;

/**
 * @author Simon Valcarcel
 * @author Blanca Martin
 * 
 * This class contains the information for the statistic of a course
 */
public class CourseStatistic {
	private Course course;
	private double mean;
	private ArrayList<Statistic> tests = new ArrayList<Statistic>(); 

	
	/**
	 * Constructor
	 * @param c - The Course for the statistic
	 */
	public CourseStatistic(Course c){
		course=c;
		for (Exercise t : c.getTests()){
			tests.add(new Statistic(t));
		}
		double total=0;
		double people=0;
		for (Statistic t :tests){
			total+=t.getMean();
			people++;
		}
		
		mean= (total/people);
		
	}
	
	@Override
	public String toString(){
		String str= "\nThe statistics for the course "+
				 course.getTitle()+ 
				"\nThe mean for the whole course is: "+ mean;
		for(Statistic s : tests){
			str+= s.toString();
		}
		
		return str;
	}

	/**
	 * This method returns the mean of a statistic
	 * @return mean - double
	 */
	public double getMean() {
		return mean;
	}


}


