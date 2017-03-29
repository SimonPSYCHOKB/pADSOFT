
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author Simon Valcarcel
 * @author Blanca Martin
 * 
 * This class contains the information of a Course
 *
 */
public class Course {
	
	private boolean visibility;
	private String title;
	private String description;
	private List<Unit> units; 
	private List<Exercise> tests;
	private List<Student> students;
	private List<Student> expelledStudents;
	private double total;

	/**
	 * This method returns whether a Course if visible
	 * @return true if it is visible, false if not
	 */
	public boolean isVisibility() {
		return visibility;
	}
	
	/**
	 * This method returns the total points of the Exercises in the Course
	 * @return total
	 */
	public double getTotal(){
		return total;
	}
	
	/**
	 * This method returns the title of a Course
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * This method returns the Exercises in a Course 
	 * @return tests - List of Exercise
	 */
	public List<Exercise> getTests(){
		return tests;
	}

	/**
	 * This method returns the description of a Course
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * This method returns the expelled Students of a Course
	 * @return expelledStudents - List of Student 
	 */
	public List<Student> getExpelledStudents(){
		return expelledStudents;
	}

	/**
	 * This method returns the Units of a Course
	 * @return units - List of Unit
	 */
	public List<Unit> getUnits(){
		return units;
	}
	
	/**
	 * Constructor
	 * @param visibility - boolean that sets whether the Course is visible
	 * @param title - String with the title
	 * @param description - String with the description
	 */
	public Course(boolean visibility, String title, String description) {
		this.visibility = visibility;
		this.title = title;
		this.description = description;
		units = new ArrayList<Unit>();
		tests = new ArrayList<Exercise>();
		students = new ArrayList<Student>();
		expelledStudents = new ArrayList<Student>();
		total = 0;
	}
	
	/**
	 * This methods adds a LearningObject to the Course
	 * @param lo - The LearningObject to be added 
	 */
	public void addLearningObj(Unit lo){
		units.add(lo);
	}
	
	/**
	 * This method adds an Exercise to the Course
	 * @param t - The Exercise to be added
	 */
	public void addTest(Exercise t){
		tests.add(t);
		t.setCourse(this);
		//We actualize the value of the total in the Course
		total = total + t.getWeight();
	}

	public String toString(){
		String str = title;
		for (Unit u: units){
			str += "\n" + "\t"+ u.toString();
		}
		return str;
	}
	
	public String toStringShort(){
		String str = title;
		return str;
	}

	/**
	 * This method returns the Students registered in the Course
	 * @return students - List of Student
	 */
	public List<Student> getStudents() {
		return students;
	}

	/**
	 * This method adds a Student to the Course
	 * @param students - Student to be added
	 */
	public void addStudents(Student students) {
		this.students.add(students);
	}
	
	/**
	 * This method expels a Student from the Course
	 * @param students - The Student to be expelled
	 */
	public void expellStudents(Student students) {
		expelledStudents.add(students);
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (expelledStudents == null) {
			if (other.expelledStudents != null)
				return false;
		} else if (!expelledStudents.equals(other.expelledStudents))
			return false;
		if (students == null) {
			if (other.students != null)
				return false;
		} else if (!students.equals(other.students))
			return false;
		if (tests == null) {
			if (other.tests != null)
				return false;
		} else if (!tests.equals(other.tests))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (Double.doubleToLongBits(total) != Double
				.doubleToLongBits(other.total))
			return false;
		if (units == null) {
			if (other.units != null)
				return false;
		} else if (!units.equals(other.units))
			return false;
		if (visibility != other.visibility)
			return false;
		return true;
	}
	
	

}
