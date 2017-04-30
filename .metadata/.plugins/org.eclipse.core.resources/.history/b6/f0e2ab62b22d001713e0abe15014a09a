package Statistics;

import java.util.ArrayList;

import Application.Course;
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
		if(people == 0) return;
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
		for(Exercise e : c.getTests()){
//			AnsweredTest at = s.getAnsweredTest(e);
//			if(at == null) continue;
			tests.add(new Statistic(e, s));
		}
//		for(AnsweredTest at : s.getTestsFromCourse(c))
//			tests.add(new Statistic(at.getTest(), s));
	}
	
	/**
	 * This method returns the course of the statistic
	 * @return Course - course of the statistic
	 */
	public Course getCourse(){
		return course;
	}
	
	public ArrayList<Statistic> getStatistics(){
		return tests;
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


