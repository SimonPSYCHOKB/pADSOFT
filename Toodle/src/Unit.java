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
	private List<Test> tests;
	
	public Unit(boolean visibility) {
		super(visibility);
		units = new ArrayList<Unit>();
		notes = new ArrayList<Note>();
		tests = new ArrayList<Test>();
	}
	
	public void createSubSection(Unit u){
		units.add(u);
	}
	
	public void addTest(Test t){
		tests.add(t);
	}
	
	public void addNotes(Note n){
		notes.add(n);
	}
	
	public String toString(){
		return "This is a unit";
	}
	//public Statistic getStatistics();
	
	//public void editUnit();

}
