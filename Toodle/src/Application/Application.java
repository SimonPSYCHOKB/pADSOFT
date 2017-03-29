package Application;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Test.Exercise;
import Test.FreeText;
import Test.MultipleAnswer;
import Test.Question;
import Test.SingleAnswer;
import Test.TrueFalse;
import Users.Student;
import Users.Teacher;
import Users.User;
import es.uam.eps.padsof.emailconnection.EmailSystem;
import es.uam.eps.padsof.emailconnection.FailedInternetConnectionException;
import es.uam.eps.padsof.emailconnection.InvalidEmailAddressException;

/**
 * 
 */

/**
 * @author Simon Valcarcel
 * @author Blanca Martin
 * 
 * This class is the main class, which makes reference to the application
 *
 */
public class Application implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static List<Student> students;
	private Teacher teacher;
	private List<Course> courses;
	private User currentUser;

	
	/**
	 * This method returns the students in the application
	 * @return students - List of Student
	 */
	public List<Student> getStudents() {
		return students;
	}

	/**
	 * This method returns the courses in the application
	 * @return courses - List of Course
	 */
	public List<Course> getCourses() {
		return courses;
	}

	/**
	 * Constructor that sets the teacher, the students and the courses
	 * @param studets - List of Student
	 * @param courses - List of Course
	 */
	public Application(List<Student> studets, List<Course> courses) {
		students = new ArrayList<Student>();
		courses = new ArrayList<Course>();
		teacher = new Teacher("Teacher", "Peres",  "123", "teacher@esdu.es");
		students = studets;
		this.courses = courses;
	}
	
	/**
	 * Constructor that loads the application from a file
	 * @param path - String with the path of the file
	 */
	public Application (String path){
		students = new ArrayList<Student>();
		courses = new ArrayList<Course>();
		
		
		  try
		  {
		    BufferedReader reader = new BufferedReader(new FileReader(path));
		    String line;
		    reader.readLine();
		    while ((line = reader.readLine()) != null)
		    {
		    	//Parse students
		    	String [] tokens = line.split(";");
		    	Student s = new Student(tokens[0], tokens[1],tokens[4],  tokens[2], 
		    			tokens[3]);
		    	students.add(s);
		     
		    }
		    reader.close();
		   
		  }
		  catch (Exception e)
		  {
		    System.err.format("Exception occurred trying to read '%s'.", path);
		    e.printStackTrace();
		    
		  }
		  teacher = new Teacher("Teacher", "Peres",  "123", "teacher@esdu.es");
	}
	
	/**
	 * 
	 * @return
	 */
	public static Application getApplication(){
		Application e = null;
	      try {
	         FileInputStream fileIn = new FileInputStream("toodle.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         e = (Application) in.readObject();
	         in.close();
	         fileIn.close();
	         return e;
	      }catch(IOException i) {
	         i.printStackTrace();
	         return null;
	      }catch(ClassNotFoundException c) {
	         System.out.println("Application class not found");
	         c.printStackTrace();
	         return null;
	      }
	      
	      
	}
	
	/**
	 * This method saves the information of the application in a file
	 */
	public void saveApplication(String path){
		try {
	        FileOutputStream fileOut =
	        new FileOutputStream(path);
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(this);
	        out.close();
	        fileOut.close();
	        System.out.printf("Serialized data is saved in " + path);
	     }catch(IOException exc) {
	        exc.printStackTrace();
	     }
	}
	
	/**
	 * This method logs a user in the application
	 * @param u - User that wants to log in
	 * @param attempt - String with the password
	 * @return true if the User belongs to the application and the password is correct, false if not
	 */
	public boolean logIn(User u, String attempt){
		if(students.contains(u) == true  && u.ValidatePassword(attempt)){
			currentUser = u;
			return true;
		}else if ( getTeacher().equals(u) == true && u.ValidatePassword(attempt)){
			currentUser= u;
			return true;
		}
		return false;
	}
	
	/**
	 * This method logs out the current user from the application
	 */
	public void logOut(){
		currentUser= null;
	}
	
	/**
	 * This method sends an email to all the students in the application 
	 * @param subject - String with the subject of the email
	 * @param body - String with the body of the email
	 * @throws InvalidEmailAddressException
	 * @throws FailedInternetConnectionException
	 */
	private void sendGroupEmail(String subject, String body) throws InvalidEmailAddressException, FailedInternetConnectionException{
		for(User std : students)
			EmailSystem.send(std.getEmail(), subject, body, true);
	}
	
	/**
	 * This method creates a course in the application is the current user is the teacher
	 * @param visibility - boolean that sets whether the course is visible
	 * @param title - String with the title
	 * @param description - String with the description
	 * @return the Course created, null if the current user is not the teacher
	 */
	public Course createCourse(boolean visibility, String title, String description){
		if(currentUser.equals(getTeacher())){
			Course c = new Course(visibility, title, description);
			courses.add(c);
			try {
				sendGroupEmail("New Course", "There is a new Course");
			} catch (InvalidEmailAddressException e) {
			} catch (FailedInternetConnectionException e) {
			}
			return c;
		}
		return null;
	}
	
	/**
	 * This method deletes a unit from the application if the current user is the teacher
	 * @param u - The Unit to be deleted
	 */
	public void deleteUnit(Unit u){
		if(currentUser.equals(teacher)){
			for(Student s : students)
				s.deleteAnsweredTests(u);
			u.getCourse().deleteUnit(u);
		}
	}
	
	/**
	 * This method creates a unit in the application if the current user is the teacher
	 * @param visibility - boolean that sets whether the unit is visible
	 * @param title - String with the title
	 * @return the Unit created, null if the current user is not the teacher
	 */
	public Unit createUnit(boolean visibility, String title){
		if(currentUser.equals(teacher)){
			Unit u = new Unit(visibility, title);
			return u;
		}
		return null;
	}
	
	/**
	 * This method adds a unit to a course if the current user is the teacher
	 * @param u - Unit to be added
	 * @param c - The Course we are referring to
	 */
	public void addUnitToCourse(Unit u, Course c){
		if(currentUser.equals(teacher)){
			c.addUnit(u);
			u.setCourse(c);
		}
	}
	
	/**
	 * This method adds a subunit to a unit if the current user is the teacher
	 * @param u - The Unit we are referring to
	 * @param su - Unit to be added as a subunit
	 */
	public void addSubSectionToUnit(Unit u, Unit su){
		if(currentUser.equals(teacher))
			u.createSubSection(su);
	}
	
	/**
	 * This method created a note if the current user is the teacher
	 * @param text - String with the text of the note
	 * @param visibility - boolean that sets whether the notes are visible
	 * @return the Note created, null if the current user is not the teacher
	 */
	public Note createNote(String text, boolean visibility){
		if(currentUser.equals(teacher)){
			Note n = new Note(text, visibility);
			return n;
		}
		return null;
	}
	
	/**
	 * This method adds a note to a unit if the current user is the teacher
	 * @param u - The Unit we are referring to
	 * @param n - Note to be added
	 */
	public void addNoteToUnit(Unit u, Note n){
		if(currentUser.equals(teacher))
			u.addNotes(n);
	}
	
	/**
	 * This method creates an exercise if the current user is the teacher
	 * @param visibility - boolean that sets if the exercise is visible or not
	 * @param dateOfBegining - LocalDate referring to the beginning of the exercise
	 * @param dateOfEnd - LocalDate referring to the end of the exercise
	 * @param weight - double which marks the weight of the exercise in a course
	 * @return the Exercise created, null if the current user is not the teacher
	 */
	public Exercise createExercise(boolean visibility, LocalDate dateOfBegining, LocalDate dateOfEnd, double weight){
		if(currentUser.equals(teacher)){
			Exercise e = new Exercise(visibility, dateOfBegining, dateOfEnd, weight);
			return e;
		}
		return null;
	}
	
	/**
	 * This method creates a single answer question if the current user is the teacher
	 * @param question - String with the wording
	 * @param weight - double with the weight of the question in an exercise
	 * @param penalty - double with the points to be subtracted if the question isn't answered correctly 
	 * @param answer - String containing the correct answer
	 * @param options - List of String containing the options of the question
	 * @return the Question created, null if the current user is not the teacher
	 */
	public Question createSingleAnswer(String question, double weight, double penalty, String answer, List<String> options){
		if(currentUser.equals(getTeacher())){
			Question q = new SingleAnswer(question, weight, penalty, answer, options);
			return q;
		}
		return null;
	}
	
	/**
	 * This method creates a multiple answer question if the current user is the teacher
	 * @param question - String with the wording
	 * @param weight - double with the weight of the question in an exercise
	 * @param penalty - double with the points to be subtracted if the question isn't answered correctly 
	 * @param answer - List of String containing the correct answers
	 * @param options - List of String containing the options of the question
	 * @return the Question created, null if the current user is not the teacher
	 */
	public Question createMultipleAnswer(String question, double weight, double penalty, List<String> answer, List<String> options){
		if(currentUser.equals(teacher)){
			Question q = new MultipleAnswer(question, weight, penalty, answer, options);
			return q;
		}
		return null;
	}
	
	/**
	 * This method creates a true or false question if the current user is the teacher
	 * @param question - String with the wording
	 * @param weight - double with the weight of the question in an exercise
	 * @param penalty - double with the points to be subtracted if the question isn't answered correctly 
	 * @param answer - String containing the correct answer
	 * @return the Question created, null if the current user is not the teacher
	 */
	public Question createTrueFalse(String question, double weight, double penalty, String answer){
		if(currentUser.equals(teacher)){
			Question q = new TrueFalse(question, weight, penalty, answer);
			return q;
		}
		return null;
	}
	
	/**
	 * This method creates a free text question if the current user is the teacher
	 * @param question - String with the wording
	 * @param weight - double with the weight of the question in an exercise
	 * @param penalty - double with the points to be subtracted if the question isn't answered correctly 
	 * @param answer - String containing the correct answer
	 * @return the Question created, null if the current user is not the teacher
	 */
	public Question createFreeText(String question, double weight, double penalty, String answer){
		if(currentUser.equals(teacher)){
			Question q = new FreeText(question, weight, penalty, answer);
			return q;
		}
		return null;
	}
	
	/**
	 * This method adds a question to an exercise if the current user is the teacher
	 * @param q - Question to be added
	 * @param e - The Exercise we are referring to
	 */
	public void addQuestionTest(Question q, Exercise e){
		if(currentUser.equals(teacher))
			e.addQuestion(q);
	}
	
	/**
	 * This method adds an exercise to a course if the current user is the teacher
	 * @param e - Exercise to be added
	 * @param c - The Course we are referring to
	 */
	public void addTestToCourse(Exercise e, Course c){
		if(currentUser.equals(teacher))
			c.addTest(e);
	}
	
	/**
	 * This method sends an email to a user
	 * @param u - User that recieves the email
	 * @param subject - String with the subject of the email
	 * @param body - String with the body of the email
	 * @throws InvalidEmailAddressException
	 * @throws FailedInternetConnectionException
	 */
	private void sendEmailTo(User u, String subject, String body) throws InvalidEmailAddressException, FailedInternetConnectionException{
		EmailSystem.send(u.getEmail(), subject, body);
	}
	/**
	 * This method is for the student to apply for a course
	 * @param s - Student who wants to apply (has to be the current user)
	 * @param c - Course that the Student wants to apply to.
	 */
	public void applyStudent(Student s, Course c){
		if(currentUser.equals(s)){
			s.apply(c);
			try {
				sendEmailTo(teacher, "New application", "The student " + s.getName() + " wants to apply for the course " + c.getTitle());
			} catch (InvalidEmailAddressException e) {
			} catch (FailedInternetConnectionException e) {
			}
		}
	}
	
	/**
	 * This method accepts a student in a course if the current user is the teacher
	 * @param s - Student accepted
	 * @param c - Course in which the Student is accepted
	 */
	public void acceptStudent(Student s, Course c){
		if(currentUser.equals(teacher)){
			teacher.acceptStudent(s, c);
			try {
				sendEmailTo(s, "Accepted!", "You have been accepted in the course " + c.getTitle());
			} catch (InvalidEmailAddressException e) {
			} catch (FailedInternetConnectionException e) {
			}
		}
	}
	
	/**
	 * This method rejects a student from a course if the current user is the teacher
	 * @param s - Student rejected
	 * @param c - Course from which the Student is rejected
	 */
	public void rejectStudent(Student s, Course c){
		if(currentUser.equals(teacher)){
			teacher.rejectStudent(s, c);
			try {
				sendEmailTo(s, "Rejected!", "Your application for the course " + c.getTitle() + " has been rejected");
			} catch (InvalidEmailAddressException e) {
			} catch (FailedInternetConnectionException e) {
			}
		}
	}
	
	/**
	 * This method expels a student from a course if the current user is the teacher
	 * @param s - Student expelled
	 * @param c - Course from which the Student is expelled
	 */
	public void expellStudent(Student s, Course c){
		if(currentUser.equals(teacher)){
			teacher.expellStudent(s, c);
			try {
				sendEmailTo(s, "Expelled!", "You have been expelled from the course " + c.getTitle());
			} catch (InvalidEmailAddressException e) {
			} catch (FailedInternetConnectionException e) {
			}
		}
	}
	
	/**
	 * This method registers the begining of an exercise by a student 
	 * @param s - The Student (has to be current user)
	 * @param e - The Exercise the Student wants to begin
	 */
	public void begingExercise(Student s, Exercise e){
		if(currentUser.equals(s))
			e.beginExercise(s);
	}
	
	/**
	 * This method returns the information of all the students in the application
	 * @return String containing the fill name of all the students
	 */
	public String students(){
		int i = 0;
		String s = new String();
		for( ; i < students.size(); i++)
			s = s + "\n" + students.get(i).getName();
		return s;
	}

	/**
	 * This method returns the teacher in the application
	 * @return teacher - Teacher
	 */
	public Teacher getTeacher() {
		return teacher;
	}
	
	/**
	 * This method returns the current user of the application
	 * @return current user - User
	 */
	public User getCurrentUser(){
		return currentUser;
	}
	
	/**
	 * This method returns a past exercise answered by a student
	 * @param s - Student who answered the exercise
	 * @param e - Exercise answered
	 * @return String containing the information of the Exercise and the Student's answers, null
	 *  if the Student hasn't answered the Exercise
	 */
	public String showStudentPastTest(Student s, Exercise e){
		return s.viewPastTest(e);
	}
	
	/**
	 * This method returns the students who belong to a course
	 * @param course - The Course we are referring to
	 * @return studenetsRet - List of Student
	 */
	public static List<Student> getStudents(Course course){
		List<Student> studentsRet = new ArrayList<Student>();
		for(Student stud : students)
			for(Course c : stud.getRegisteredCourses())
				if(c.equals(course))
					studentsRet.add(stud);
		return studentsRet;

	}

}
