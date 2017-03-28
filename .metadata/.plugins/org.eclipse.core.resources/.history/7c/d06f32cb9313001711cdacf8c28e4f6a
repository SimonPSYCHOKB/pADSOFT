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
	private String name;
	
	public Unit(boolean visibility, String name) {
		super(visibility);
		units = new ArrayList<Unit>();
		this.name = name;
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
	
	//public void editUnit();

}
