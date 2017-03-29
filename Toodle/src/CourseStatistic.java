import java.util.ArrayList;

public class CourseStatistic {
	private Course course;
	private double mean;
	private ArrayList<Statistic> tests = new ArrayList<Statistic>(); 

	
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
	
	public String toString(){
		String str= "\nThe statistics for the course "+
				 course.getTitle()+ 
				"\nThe mean for the whole course is: "+ getMean();
		for(Statistic s : tests){
			str+= s.toString();
		}
		
		return str;
	}

	public double getMean() {
		return mean;
	}


}


