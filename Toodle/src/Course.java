
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author Simon Valcarcel, Blanca Martin
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

	public boolean isVisibility() {
		return visibility;
	}
	
	public double getTotal(){
		return total;
	}
	
	public String getTitle() {
		return title;
	}
	
	public List<Exercise> getTests(){
		return tests;
	}

	public String getDescription() {
		return description;
	}
	
	public List<Student> getExpelledStudents(){
		return expelledStudents;
	}

	public List<Unit> getUnits(){
		return units;
	}
	/**
	 * 
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
	 * @param args
	 */
	
	public void addLearningObj(Unit lo){
		units.add(lo);
	}
	
	public void addTest(Exercise t){
		tests.add(t);
		t.setCourse(this);
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

	public List<Student> getStudents() {
		return students;
	}

	public void addStudents(Student students) {
		this.students.add(students);
	}
	
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
