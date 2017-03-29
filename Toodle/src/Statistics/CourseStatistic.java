package Statistics;

import java.util.ArrayList;

import Application.Course;
import Test.AnsweredTest;
import Test.Exercise;
import Users.Student;

/**
 * @author Simon Valcarcel
 * @author Blanca Martin
 * 
 * This class contains the information for the statistic of a course
 */
public class CourseStatistic extends Stats{
	protected Course course;
	protected ArrayList<Statistic> tests = new ArrayList<Statistic>(); 

	
	/**
	 * Constructor
	 * @param c - The Course for the statistic
	 */
	public CourseStatistic(Course c){
		super();
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
		
		setMean(total/people);
		
	}
	
	/**
	 * Constructor for the statistics of a single student
	 * @param c - Course
	 * @param s - Student
	 */
	public CourseStatistic(Course c, Student s){
		super();
		course = c;
		
		setMean(s.getGradeCourse(c));
		for(AnsweredTest at : s.getTestsFromCourse(c))
			tests.add(new Statistic(at.getTest(), s));
	}
	
	@Override
	public String toString(){
		String str= "\nThe statistics for the course "+
				 course.getTitle()+ 
				"\nThe mean for the whole course is: "+ getMean();
		for(Statistic s : tests){
			str+= s.toString();
		}
		
		return str;
	}

}


