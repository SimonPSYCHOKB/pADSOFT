package Statistics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Test.*;
import Users.Student;
import Application.Course;


/**
 * @author Simon Valcarcel
 * @author Blanca Martin
 * 
 * This class contains the information of the statistics for a student
 *
 */
public class SingleUserStatistic implements Serializable{
	
	private static final long serialVersionUID = 1L;

	List<CourseStatistic> courses;
	List<Statistic> tests;
	List<QuestionStatistic> questions;
	
	/**
	 * Constructor
	 * @param student - Student
	 */
	public SingleUserStatistic(Student student) {
		courses = new ArrayList<CourseStatistic>();
		tests = new ArrayList<Statistic>();
		questions = new ArrayList<QuestionStatistic>();
		
		for(Course c : student.getRegisteredCourses()){
			courses.add(new CourseStatistic(c, student));
			for(Exercise t : c.getTests())
				tests.add(new Statistic(t, student));
		}
		
//		for(AnsweredTest at : student.getAnsweredTests())
//			tests.add(new Statistic(at.getTest(), student));
		
		for(Statistic stat : tests)
			questions.addAll(stat.getQuestionStatistics());
		
	}
	
	/**
	 * This method returns the course statistics of a student
	 * @return a List of CourseStatistic
	 */
	public List<CourseStatistic> getCourseStatistics(){
		return courses;
	}

	@Override
	public String toString(){
		String s = new String();
		for(CourseStatistic cs : courses)
			s = s + "\n" + cs.toString();
		return s;
	}
}
