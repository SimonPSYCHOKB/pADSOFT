import java.io.Serializable;

/**
 * 
 */

/**
 * @author Simon Valcarcel
 * @author Blanca Martin
 * 
 * This class contains the information of the teacher
 *
 */
public class Teacher extends User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor
	 * 
	 * @param fname - String with the first name of the Teacher
	 * @param lname - String with the last name of the Teacher
	 * @param password - String with the password of the Teacher
	 * @param email - String with the email of the Teacher
	 */
	public Teacher(String fname, String lname, String password, String email) {
		super(fname, lname,  password, email);
	}


	/**
	 * This method accepts a student in a course
	 * @param s - Student to be accepted
	 * @param c - Course in which the Student has been accepted
	 */
	public void acceptStudent(Student s, Course c) {
		Course cRem= null;
		for (Course ct: s.getPendingCourses()){
			if(ct.equals(c)){
				cRem = c;
				break;
				
			}
		}
		if(cRem != null){
			s.acceptStudent(c);
			c.addStudents(s);
		}
	}
	
	/**
	 * This method rejects a student from a course
	 * @param s - Student to be rejected
	 * @param c - Course from which the Student has been rejected
	 */
	public void rejectStudent(Student s, Course c) {
		Course cRem= null;
		for (Course ct: s.getPendingCourses()){
			if(ct.equals(c)){
				cRem = c;
				break;
				
			}
		}
		if(cRem != null){
			s.rejectStudent(c);
		}
	}
	
	/**
	 * This method readmits a student in a course
	 * @param s - Student to be readmitted
	 * @param c - Course in which the Student has been accepted
	 */
	public void readmitStudent(Student s, Course c){
		s.acceptStudent(c);
	}
	
	/**
	 * This method expels a student from a course
	 * @param s - Student to be accepted
	 * @param c - Course from which the Student has been expelled
	 */
	public void expellStudent(Student s, Course c){
		s.expellStudent(c);
	}
		

}
