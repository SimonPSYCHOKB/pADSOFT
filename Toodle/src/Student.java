
import java.util.List;
import java.util.ArrayList;
/*
import es.uam.eps.padsof.emailconnection.EmailSystem;
import es.uam.eps.padsof.emailconnection.FailedInternetConnectionException;
import es.uam.eps.padsof.emailconnection.InvalidEmailAddressException;
*/

/**
 * 
 */

/**
 * @author Simon Valcarcel, Blanca Martin
 *
 */
public class Student extends User{

	/**
	 * 
	 */
	private List<Course> rejectedCourses;
	private List<Course> registeredCourses;
	private List<Course> pendingCourses;
	private List<Course> expelledCourses;
	private List<Exercise> currentExercises;
	private List<AnsweredTest> answeredTests;
	private String manu;

	//Constructor
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
	
	//Getters y setters
	
	public List<AnsweredTest> getAnsweredTests(){
		return answeredTests;
	}
	
	private List<AnsweredTest> getTestsFromCourse(Course c){
		List<AnsweredTest> ats = new ArrayList<AnsweredTest>();
		List<Exercise> ts = c.getTests();
		int i = 0;
		for( ; i < ts.size(); i++)
			ats.add(getAnsweredTest(ts.get(i)));
		return ats;
	}
	
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
	
	public List<Course> getRegisteredCourses() {
		return registeredCourses;
	}
	
	public List<Exercise> getCurrentExercises(){
		return currentExercises;
	}
	
	public void addTestStudent(Exercise t){
		//Habria que comprobar que puede empezarlo
		currentExercises.add(t);
	}
	
	public void removeTestStudent(Exercise t){
		currentExercises.remove(t);
	}
	
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
	public void acceptStudent(Course c){
		getPendingCourses().remove(c);
		expelledCourses.remove(c);
		registeredCourses.add(c);
		c.addStudents(this);
	}
	
	public void rejectStudent(Course c){
		getPendingCourses().remove(c);
		//expelledCourses.remove(c);
		rejectedCourses.add(c);
	}
	
	public void expellStudent(Course c){
		registeredCourses.remove(c);
		expelledCourses.add(c);
	}
	
	public AnsweredTest getAnsweredTest(Exercise t){
		int i = 0;
		for( ; i < answeredTests.size(); i++)
			if(answeredTests.get(i).getTest().equals(t))
			//Habria que comprobar que puede verlo
					return answeredTests.get(i);
		return null;
	}
	
	public String viewPastTest(Exercise t){
		return getAnsweredTest(t).toString();
	}
	
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
	
	public void apply(Course c){
		getPendingCourses().add(c);
	}

	public List<Course> getPendingCourses() {
		return pendingCourses;
	}

	public List<Course> getRejectedCourses() {
		return rejectedCourses;
	}
	
	public List<Course> getExpelledCourses() {
		return expelledCourses;
	}


	
	
	//public void getStatistics()

}
