package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import users.Student;

import exercise.Exercise;



/**
 * @author Simon Valcarcel
 * @author Blanca Martin
 * 
 * This class contains the information of a Course
 *
 */
public class Course implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private boolean visibility;
	private String title;
	private String description;
	private List<Unit> units; 
	private List<Exercise> tests;
	private double total;
	private List<Student> registered;
	private List<Student> pending;
	private List<Student> expelled;
	
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
		registered = new ArrayList<Student>();
		pending = new ArrayList<Student>();
		expelled = new ArrayList<Student>();
		total = 0;
	}

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
		return Collections.unmodifiableList(tests);
	}

	/**
	 * This method returns the description of a Course
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method returns the Units of a Course
	 * @return units - List of Unit
	 */
	public List<Unit> getUnits(){
		return Collections.unmodifiableList(units);
	}
	
	/**
	 * This method modifies the visibility of the course
	 * @param visibility - boolean with the visibility
	 */
	public void setVisibility(boolean visibility){
		this.visibility = visibility;
	}
	
	/**
	 * This method modifies the title of the course
	 * @param title - the new title
	 */
	public void setTitle(String title){
		this.title = title;
	}
	
	/**
	 * This method modifies the description of the course
	 * @param description - the new description
	 */
	public void setDescription(String description){
		this.description = description;
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
	
	/**
	 * This method erases an exercise from the course
	 * @param t - the Exercise we want to erase
	 */
	public void removeTest(Exercise t){
		if(tests.contains(t) == false) return;
		t.setCourse(null);
		tests.remove(t);
	}

	@Override
	public String toString(){
		String str = title;
		for (Unit u: units){
			str += "\n" + "\t"+ u.toString();
		}
		return str;
	}

	/**
	 * This method removes the unit from the course
	 * @param u - The Unit we want to remove
	 */
	public void deleteUnit(Unit u){
		if(units.contains(u)) units.remove(u);
		if(u.getUnit() == null) return;
		u.getUnit().removeUnit(u);
	}
	
	/**
	 * This method adds a unit to a course
	 * @param u - The Unit to be added
	 */
	public void addUnit(Unit u){
		units.add(u);
		u.setCourse(this);
	}
	
	/**
	 * This method expels a student from the course
	 * @param s - the Student to be expelled
	 */
	public void expelStudent(Student s){
		expelled.add(s);
		registered.remove(s);
		pending.remove(s);
	}
	
	/**
	 * This method registers a student from the course
	 * @param s - the Student to be registered
	 */
	public void registerStudent(Student s){
		registered.add(s);
		pending.remove(s);
	}
	
	/**
	 * This method saves the application of a student to register in the course
	 * @param s - the Student that has applied
	 */
	public void applyStudent(Student s){
		pending.add(s);
	}
	
	/**
	 * This method returns the registered students in the course
	 * @return list of students registered in the course
	 */
	public List<Student> getRegistered(){
		return Collections.unmodifiableList(registered);
	}
	
	/**
	 * This method returns the pending students in the course
	 * @return list of students pending in the course
	 */
	public List<Student> getPending(){
		return Collections.unmodifiableList(pending);
	}
	
	/**
	 * This method returns the expelled students in the course
	 * @return list of students expelled in the course
	 */
	public List<Student> getExpelled(){
		return Collections.unmodifiableList(expelled);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		long temp;
		temp = Double.doubleToLongBits(total);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (visibility ? 1231 : 1237);
		return result;
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
