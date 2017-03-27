
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
	private List<LearningObj> objects; 
	private List<Test> tests;
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
	
	public List<Test> getTests(){
		return tests;
	}

	public String getDescription() {
		return description;
	}
	
	public List<Student> getExpelledStudents(){
		return expelledStudents;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setObjects(List<LearningObj> objects){
		this.objects = objects;
	}
	
	public void setExpelledStudents(List<Student> expelledStudents){
		this.expelledStudents = expelledStudents;
	}

	/**
	 * 
	 */
	public Course(boolean visibility, String title, String description) {
		this.visibility = visibility;
		this.title = title;
		this.description = description;
		objects = new ArrayList<LearningObj>();
		tests = new ArrayList<Test>();
		total = 0;
	}

	/**
	 * @param args
	 */
	
	public void addLearningObj(LearningObj lo){
		objects.add(lo);
	}
	
	public void addTest(Test t){
		tests.add(t);
		total = total + t.getWeight();
	}

	public String toString(){
		String str = title;
		for (LearningObj o: objects){
			str += "\n" + "\t"+ o.toString();
		}
		return str;
	}
}
