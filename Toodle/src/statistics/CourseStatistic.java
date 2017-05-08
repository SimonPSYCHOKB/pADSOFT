package statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import users.Student;

import exercise.Exercise;


import application.Course;


/**
 * @author Simon Valcarcel
 * @author Blanca Martin
 * 
 * This class contains the information for the statistic of a course
 */
public class CourseStatistic extends Stats{
	protected Course course;
	protected List<Statistic> tests = new ArrayList<Statistic>(); 

	
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
			if(e.getDateOfEnd().before(new Date()))
				tests.add(new Statistic(e, s));
		}
	}
	
	/**
	 * This method returns the course of the statistic
	 * @return Course - course of the statistic
	 */
	public Course getCourse(){
		return course;
	}
	
	/**
	 * This method returns the statistic of the tests in the course
	 * @return the list with the tests' statistics
	 */
	public List<Statistic> getStatistics(){
		return Collections.unmodifiableList(tests);
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


