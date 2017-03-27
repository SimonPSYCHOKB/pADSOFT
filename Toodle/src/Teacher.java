
/**
 * 
 */

/**
 * @author Simon Valcarcel, Blanca Martin
 *
 */
public class Teacher extends User{

	/**
	 * 
	 */
	
	//Constructor
	public Teacher(String fname, String lname, String password, String email) {
		super(fname, lname,  password, email);
	}


	/**
	 * @param args
	 */
	public void acceptStudent(Student s, Course c) {
		(s.getRegisteredCourses()).add(c);
	}
	
	public void rejectStudent(Student s, Course c){
		
	}
	
	public void readmitStudent(Student s, Course c){
		(s.getRegisteredCourses()).add(c);
	}
	
	public void expellStudent(Student s, Course c){
		(s.getRegisteredCourses()).remove(c);
	}
	
	public void viewStatistics(){
		//Algo de las stats
	}
	
	public void sendNotification(){
		//Algo de las notificaciones
	}
		

}
