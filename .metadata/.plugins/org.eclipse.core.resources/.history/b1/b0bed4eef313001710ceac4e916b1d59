
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author blanca
 *
 */
public class Exercise extends LearningObj{

	private Course course;
	private LocalDate dateOfBegining;
	private LocalDate dateOfEnd;
	private double weight;
	private double total;
	private List<Question> questions;
	private boolean started;
	private int count;
	
	/**
	 * 
	 */
	public Exercise(boolean visibility, LocalDate dateOfBegining, LocalDate dateOfEnd, double weight) {
		super(visibility);
		this.dateOfBegining = dateOfBegining;
		this.dateOfEnd = dateOfEnd;
		this.weight = weight;
		questions = new ArrayList<Question>();
		started = false;
		count = 0;
		total = 0;
	}
	
	public double getTotal(){
		return total;
	}
	
	public double getWeight(){
		return weight;
	}
	
	public List<Question> getQuestions(){
		return questions;
	}
	
	public LocalDate getDateOfEnd(){
		return dateOfEnd;
	}
	
	public LocalDate getDateOfBegining(){
		return dateOfBegining;
	}

	public boolean beginExercise(Student s){
		if(LocalDate.now().isAfter(dateOfEnd) == true)
			return false;
		s.addTestStudent(this);
		started = true;
		return true;
	}
	
	public void finishExercise(Student s){
		s.removeTestStudent(this);
		count = 0;
	}
	
	public void addQuestion(Question q){
		questions.add(q);
		total = total + q.getWeight();
	}
	
	public void editDateOfEnd(LocalDate dateOfEnd){
		if(started == true)
			return;
		this.dateOfEnd = dateOfEnd; 
	}
	
	public void editDateOfBegining(LocalDate dateOfBegining){
		if(started == true)
			return;
		this.dateOfBegining = dateOfBegining; 
	}
	
	public String showQuestionTest(){
		return questions.get(count).showQuestion();
	}
	
	public Answer answerQuestionTest(List<String> answer){
		Answer a = new Answer(answer, questions.get(count));
		count++;
		return a;
	}
	
	public void answerTest(Student student, List<Answer> answers){
		student.answerTest(this, answers);	
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exercise other = (Exercise) obj;
		if (count != other.count)
			return false;
		if (dateOfBegining == null) {
			if (other.dateOfBegining != null)
				return false;
		} else if (!dateOfBegining.equals(other.dateOfBegining))
			return false;
		if (dateOfEnd == null) {
			if (other.dateOfEnd != null)
				return false;
		} else if (!dateOfEnd.equals(other.dateOfEnd))
			return false;
		if (questions == null) {
			if (other.questions != null)
				return false;
		} else if (!questions.equals(other.questions))
			return false;
		if (started != other.started)
			return false;
		if (Double.doubleToLongBits(weight) != Double
				.doubleToLongBits(other.weight))
			return false;
		return true;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}	

	
}
