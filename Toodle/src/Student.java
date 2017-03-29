
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
/*
import es.uam.eps.padsof.emailconnection.EmailSystem;
import es.uam.eps.padsof.emailconnection.FailedInternetConnectionException;
import es.uam.eps.padsof.emailconnection.InvalidEmailAddressException;
*/

/**
 * @author Simon Valcarcel
 * @author Blanca Martin
 * 
 * This class contains the information of the users who are students
 *
 */
public class Student extends User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Course> rejectedCourses;
	private List<Course> registeredCourses;
	private List<Course> pendingCourses;
	private List<Course> expelledCourses;
	private List<Exercise> currentExercises;
	private List<AnsweredTest> answeredTests;
	private String manu;

	
	/**
	 * Constructor 
	 * 
	 * @param fname - String with the first name of the Student
	 * @param lname - String with the last name of the Student
	 * @param password - String with the password of the Student
	 * @param email - String with the email of the Student
	 * @param manu - String with the manu of the Student
	 */
	public Student(String fname, String lname, String password, String email, String manu) {
		super(fname ,lname,  password, email);
		this.manu = manu;
		registeredCourses = new ArrayList<Course>();
		pendingCourses = (new ArrayList<Course>());
		expelledCourses = new ArrayList<Course>();
		currentExercises = new ArrayList<Exercise>();
		answeredTests = new ArrayList<AnsweredTest>();
		rejectedCourses = new ArrayList<Course>();
	}
	
	/**
	 * This method returns the Exercises answered by the Student
	 * @return answeredTests - List of AnsweredTests
	 */
	public List<AnsweredTest> getAnsweredTests(){
		return answeredTests;
	}
	
	/**
	 * This method get the exercises the Student has asnwered in a course
	 * @param c - The Course we are referring to
	 * @return ats - List of AnsweredTests
	 */
	private List<AnsweredTest> getTestsFromCourse(Course c){
		List<AnsweredTest> ats = new ArrayList<AnsweredTest>();
		List<Exercise> ts = c.getTests();
		int i = 0;
		for( ; i < ts.size(); i++)
			ats.add(getAnsweredTest(ts.get(i)));
		return ats;
	}
	
	/**
	 * This method returns the grade of a Student in a Course
	 * @param c - The Course
	 * @return a double with the grade of the Student in the Course c
	 */
	public double getGradeCourse(Course c){
		if(registeredCourses.contains(c) == false)
			return 0;
		List<AnsweredTest> ats = getTestsFromCourse(c);
		int i = 0;
		double grade = 0;
		for( ; i < ats.size(); i++)
			grade = grade + ats.get(i).getRelativeGradeTest();
		return grade/c.getTotal();
	}
	
	/**
	 * This method returns the courses in which the student is registered
	 * @return registeredCourses - List of Courses
	 */
	public List<Course> getRegisteredCourses() {
		return registeredCourses;
	}
	
	/**
	 * This method returns the exercises that a student is doing right now
	 * @return currentExercises - List of Exercise
	 */
	public List<Exercise> getCurrentExercises(){
		return currentExercises;
	}
	
	/**
	 * This method adds an exercise to the list of current exercises of the student
	 * @param t - Exercise to be added
	 */
	public void addTestStudent(Exercise t){
		//Habria que comprobar que puede empezarlo
		currentExercises.add(t);
	}
	
	/**
	 * This method removes an exercise from the list of current exercises of the student
	 * @param t - Exercise to be removed
	 */
	public void removeTestStudent(Exercise t){
		currentExercises.remove(t);
	}
	
	/**
	 * This method answers an exercise by a student
	 * @param test - Exercise to be answered
	 * @param answers - List of Answer containing the answers to the test
	 */
	public void answerTest(Exercise test, List<Answer> answers){
		//Habria que comprobar que puede empezarlo 
		answeredTests.add(new AnsweredTest(test, answers));
		//System.out.println(answeredTests.size());
	}
	
	/*

	public void applyForCourse(Course c){
		try {
			EmailSystem.send("teacher@esdu.es", "Apply", "Hola que tal", true);
		} catch (InvalidEmailAddressException e) {
			e.printStackTrace();
		} catch (FailedInternetConnectionException e) {
			e.printStackTrace();
		}
		pendingCourses.add(c);
	}
	
	*/
	
	/**
	 * This method accepts a student in a course
	 * @param c - Course in which the student is accepted
	 */
	public void acceptStudent(Course c){
		getPendingCourses().remove(c);
		expelledCourses.remove(c);
		registeredCourses.add(c);
		c.addStudents(this);
	}
	
	/**
	 * This method rejects a student in a course
	 * @param c - Course in which the student is rejected
	 */
	public void rejectStudent(Course c){
		getPendingCourses().remove(c);
		//expelledCourses.remove(c);
		rejectedCourses.add(c);
	}
	
	/**
	 * This method expels a student in a course
	 * @param c - Course in which the student is expelled
	 */
	public void expellStudent(Course c){
		registeredCourses.remove(c);
		expelledCourses.add(c);
	}
	
	/**
	 * This method gets the answers of the student to a given exercise he has previously answered
	 * @param t - Exercise we are referring to
	 * @return the AnsweredTest, null if the Student hasn't answered the Exercise yet
	 */
	public AnsweredTest getAnsweredTest(Exercise t){
		int i = 0;
		for( ; i < answeredTests.size(); i++)
			if(answeredTests.get(i).getTest().equals(t))
			//Habria que comprobar que puede verlo
					return answeredTests.get(i);
		return null;
	}
	
	/**
	 * This method returns a string containins an answered exercise by the student
	 * @param t - The Exercise we are referring to
	 * @return String containing the information of the Exercise and the Student's answers
	 */
	public String viewPastTest(Exercise t){
		return getAnsweredTest(t).toString();
	}
	
	/**
	 * This method corrects an exercise done by a student
	 * @param t - The Exercise we want to correct
	 * @return 0 if the Student hasn't answered the Exercise yet, a double with the grade if he has.
	 */
	public double correctTest(Exercise t){
		AnsweredTest at = getAnsweredTest(t);
		if(at == null)
			return 0;
		at.correctAnsweredTest();
		return at.getGradeTest();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Student))
			return false;
		Student other = (Student) obj;
		if (answeredTests == null) {
			if (other.answeredTests != null)
				return false;
		} else if (!answeredTests.equals(other.answeredTests))
			return false;
		if (currentExercises == null) {
			if (other.currentExercises != null)
				return false;
		} else if (!currentExercises.equals(other.currentExercises))
			return false;
		if (expelledCourses == null) {
			if (other.expelledCourses != null)
				return false;
		} else if (!expelledCourses.equals(other.expelledCourses))
			return false;
		if (manu == null) {
			if (other.manu != null)
				return false;
		} else if (!manu.equals(other.manu))
			return false;
		if (getPendingCourses() == null) {
			if (other.getPendingCourses() != null)
				return false;
		} else if (!getPendingCourses().equals(other.getPendingCourses()))
			return false;
		if (registeredCourses == null) {
			if (other.registeredCourses != null)
				return false;
		} else if (!registeredCourses.equals(other.registeredCourses))
			return false;
		return true;
	}
	
	public String toString(){
		return fName + " " + lName + " "+ email +" "+
	manu + " "+ " "+ password + "\n";
	}
	
	/**
	 * This method adds a course to the pending courses of a student
	 * @param c - Course the Students applies for
	 */
	public void apply(Course c){
		pendingCourses.add(c);
	}

	/**
	 * This method returns the pending courses of a student
	 * @return pendingCourses - List of Course
	 */
	public List<Course> getPendingCourses() {
		return pendingCourses;
	}
	
	/**
	 * This method returns the rejected courses of a student
	 * @return rejectedCourses - List of Course
	 */
	public List<Course> getRejectedCourses() {
		return rejectedCourses;
	}
	
	/**
	 * This method returns the expelled courses of a student
	 * @return expelledCourses - List of Course
	 */
	public List<Course> getExpelledCourses() {
		return expelledCourses;
	}

}
