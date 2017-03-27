
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
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
	
	public boolean addCourse(Course c){
		if(currentUser.equals(teacher)){
			courses.add(c);
			return true;
		}else{
			return false;
		}
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
		    	Student s = new Student(tokens[0], tokens[1], tokens[3], tokens[2],
		    			tokens[4]);
		    	students.add(s);
		     
		    }
		    reader.close();
		   
		  }
		  catch (Exception e)
		  {
		    System.err.format("Exception occurred trying to read '%s'.", path);
		    e.printStackTrace();
		    
		  }
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

}
