import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author Simon Valcarcel, Blanca Martin
 *
 */
public class Unit extends LearningObj{
	
	/**
	 * 
	 */
	private List<Unit> units;
	private List<Note> notes;
	private List<Exercise> tests;
	private String name;
	
	public Unit(boolean visibility, String name) {
		super(visibility);
		units = new ArrayList<Unit>();
		this.name = name;
		notes = new ArrayList<Note>();
		tests = new ArrayList<Exercise>();
	}
	
	public List<Unit> getSubUnits(){
		return units;
	}
	
	public List<Note> getNotes(){
		return notes;
	}
	
	public void createSubSection(Unit u){
		units.add(u);
	}
	
	public void addTest(Exercise t){
		tests.add(t);
	}
	
	public void addNotes(Note n){
		notes.add(n);
	}
	
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
	//public Statistic getStatistics();

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
		if (tests == null) {
			if (other.tests != null)
				return false;
		} else if (!tests.equals(other.tests))
			return false;
		if (units == null) {
			if (other.units != null)
				return false;
		} else if (!units.equals(other.units))
			return false;
		return true;
	}
	
	//public void editUnit();
	
	

}
