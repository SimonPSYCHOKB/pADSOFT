
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 */

/**
 * @author Simon Valcarcel, Blanca Martin
 *
 */
public class Application {
	
	private List<Student> students;
	private Teacher teacher;
	private List<Course> courses;
	private User currentUser;

	
	public List<Student> getStudents() {
		return students;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}


	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	/**
	 * 
	 */
	
	public Application(){
		students = new ArrayList<Student>();
		courses = new ArrayList<Course>();
		this.setTeacher(new Teacher("Teacher", "Peres",  "123", "teacher@esdu.es"));		
	}
	
	public Application(List<Student> students, List<Course> courses) {
		this();
		this.students = students;
		this.courses = courses;
	}
	
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
		  //this.setTeacher(new Teacher("Teacher", "Peres",  "123", "teacher@esdu.es"));
	}
	
	
	public void addStudent(Student s){
		students.add(s);
	}
	
	//Modified, now requires correct password
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
	
	public void logOut(){
		currentUser= null;
	}
	
	public Course createCourse(boolean visibility, String title, String description){
		if(currentUser.equals(getTeacher())){
			Course c = new Course(visibility, title, description);
			courses.add(c);
			return c;
		}
		return null;
	}
	
	public Unit createUnit(boolean visibility, String title){
		if(currentUser.equals(getTeacher())){
			Unit u = new Unit(visibility, title);
			return u;
		}
		return null;
	}
	
	public void addUnitToCourse(Unit u, Course c){
		if(currentUser.equals(getTeacher())){
			c.addLearningObj(u);
		}
	}
	
	public void addSubSectionToUnit(Unit u, Unit su){
		if(currentUser.equals(getTeacher())){
			u.createSubSection(su);
		}
	}
	
	public Note createNote(String text, LocalDate date){
		if(currentUser.equals(getTeacher())){
			Note n = new Note(text, date);
			return n;
		}
		return null;
	}
	
	public void addNoteToUnit(Unit u, Note n){
		if(currentUser.equals(getTeacher())){
			u.addNotes(n);
		}
	}
	
	public Exercise createExercise(boolean visibility, LocalDate dateOfBegining, LocalDate dateOfEnd, double weight){
		if(currentUser.equals(getTeacher())){
			Exercise e = new Exercise(visibility, dateOfBegining, dateOfEnd, weight);
			return e;
		}
		return null;
	}
	
	public String students(){
		int i = 0;
		String s = new String();
		for( ; i < students.size(); i++)
			s = s + "\n" + students.get(i).getName();
		return s;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public User getCurrentUser(){
		return currentUser;
	}

}
