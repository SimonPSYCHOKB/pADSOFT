
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
	
	private List<Course> registeredCourses;
	private List<Course> pendingCourses;
	private List<Course> expelledCourses;
	private List<Test> currentExercises;
	private List<AnsweredTest> answeredTests;
	private String manu;

	//Constructor
	public Student(String fname, String lname, String password, String email, String manu) {
		super(fname ,lname,  password, email);
		this.manu = manu;
		registeredCourses = new ArrayList<Course>();
		pendingCourses = new ArrayList<Course>();
		expelledCourses = new ArrayList<Course>();
		currentExercises = new ArrayList<Test>();
		answeredTests = new ArrayList<AnsweredTest>();
	}
	
	//Getters y setters
	
	private List<AnsweredTest> getTestsFromCourse(Course c){
		List<AnsweredTest> ats = new ArrayList<AnsweredTest>();
		List<Test> ts = c.getTests();
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
	
	public List<Test> getCurrentExercises(){
		return currentExercises;
	}
	
	public void setRegisteredCourses(List<Course> registeredCourses) {
		this.registeredCourses = registeredCourses;
	}
	
	public void setCurrentExercises(List<Test> currentExercises){
		this.currentExercises = currentExercises;
	}
	
	public void addTestStudent(Test t){
		//Habria que comprobar que puede empezarlo
		currentExercises.add(t);
	}
	
	public void removeTestStudent(Test t){
		currentExercises.remove(t);
	}
	
	public void answerTest(Test test, List<Answer> answers){
		//Habria que comprobar que puede empezarlo
		AnsweredTest at = new AnsweredTest(test, answers);
		answeredTests.add(at);
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
		pendingCourses.remove(c);
		expelledCourses.remove(c);
		registeredCourses.add(c);
	}
	
	public void expellStudent(Course c){
		registeredCourses.remove(c);
		expelledCourses.add(c);
	}
	
	private AnsweredTest getAnsweredTest(Test t){
		int i = 0;
		for( ; i < answeredTests.size(); i++)
			if(answeredTests.get(i).getTest().equals(t))
			//Habria que comprobar que puede verlo
					return answeredTests.get(i);
		return null;
	}
	
	public String viewPastTest(Test t){
		return getAnsweredTest(t).toString();
	}
	
	public double correctTest(Test t){
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
		if (pendingCourses == null) {
			if (other.pendingCourses != null)
				return false;
		} else if (!pendingCourses.equals(other.pendingCourses))
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
	
	
	
	//public void getStatistics()

}
