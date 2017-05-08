package application;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import exercise.Exercise;



/**
 * @author Simon Valcarcel
 * @author Blanca Martin
 * 
 * This class contains the information of the units
 *
 */
public class Unit extends LearningObj implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Unit> units;
	private List<Note> notes;
	private List<Exercise> tests;
	private String name;
	private Course course;
	private Unit unit;
	
	/**
	 * Constructor
	 * 
	 * @param visibility - boolean that sets whether the unit is visible
	 * @param name - String with the name of the unit
	 */
	public Unit(boolean visibility, String name) {
		super(visibility);
		units = new ArrayList<Unit>();
		this.name = name;
		notes = new ArrayList<Note>();
		tests = new ArrayList<Exercise>();
		unit = null;
		course = null;
	}
	
	/**
	 * This method returns the subunits inside the unit
	 * @return units - List of Unit
	 */
	public List<Unit> getSubUnits(){
		return Collections.unmodifiableList(units);
	}
	
	/**
	 * This method returns the notes inside the unit
	 * @return notes - List of Note
	 */
	public List<Note> getNotes(){
		return Collections.unmodifiableList(notes);
	}
	
	/**
	 * This method adds a subunit to the unit
	 * @param u - The Unit to be added
	 */
	public void createSubSection(Unit u){
		if(u.getSubUnits().contains(this)) return;
		units.add(u);
		u.setCourse(course);
		u.setUnit(this);
	}
	
	public void removeUnit(Unit u){
		if(getSubUnits().contains(u) == false) return;
		units.remove(u);
	}
	
	public Unit getUnit(){
		return unit;
	}
	
	public void setUnit(Unit u){
		unit = u;
	}
	
	/**
	 * This method adds an exercise to the unit
	 * @param t - The Exercise to be added
	 */
	public void addTest(Exercise t){
		tests.add(t);
		t.setCourse(course);
		t.setUnit(this);
		course.addTest(t);
	}
	
	public void removeTest(Exercise t){
		if(tests.contains(t) == false) return;
		t.setUnit(null);
		tests.remove(t);
		t.getCourse().removeTest(t);
	}
	
	/**
	 * This method adds notes to the unit
	 * @param n - The Note to be added
	 */
	public void addNotes(Note n){
		notes.add(n);
		n.setUnit(this);
	}
	
	public void removeNotes(Note n){
		if(notes.contains(n) == false) return;
		notes.remove(n);
		n.setUnit(null);
	}
	
	/**
	 * This method sets the course that the unit belongs to
	 * @param course - Course
	 */
	public void setCourse(Course course){
		this.course = course;
	}
	
	/**
	 * This method returns the course that the unit belongs to
	 * @return course - Course
	 */
	public Course getCourse(){
		
		return course;
	}
	
	/**
	 * This method returns the name of the unit
	 * @return name - String 
	 */
	public String getName(){
		return name;
	}
	
	public List<Exercise> getTests(){
		return Collections.unmodifiableList(tests);
	}
	
	@Override
	public String toString(){
		String str =name;
		for(Note n : notes){
			str+= "\n" + "\t"+ n.toString();
		}
		for (Unit u : units){
			str+= "\n" + "\t"+"\t" + u.toString();
		}
		return str;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + ((units == null) ? 0 : units.hashCode());
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
		Unit other = (Unit) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (units == null) {
			if (other.units != null)
				return false;
		} else if (!units.equals(other.units))
			return false;
		return true;
	}		
	

}
