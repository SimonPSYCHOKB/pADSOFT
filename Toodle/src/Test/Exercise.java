package Test;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Application.Course;
import Application.LearningObj;
import Application.Unit;
import Users.Student;

/**
 * @author Simon Valcarcel
 * @author Blanca Martin
 * 
 * This class contains information of an Exercise
 *
 */
public class Exercise extends LearningObj implements Serializable{

	private static final long serialVersionUID = 1L;

	private Course course;
	private Unit unit;
	private LocalDate dateOfBegining;
	private LocalDate dateOfEnd;
	private double weight;
	private double total;
	private List<Question> questions;
	private boolean started;
	private int count;
	
	
	/**
	 * Constructor
	 * @param visibility - boolean that sets if the Exercise is visible or not
	 * @param dateOfBegining - LocalDate referring to the beginning of the Exercise
	 * @param dateOfEnd - LocalDate referring to the end of the Exercise
	 * @param weight - double which marks the weight of the Exercise in a Course
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
	
	/**
	 * This method returns the total points of the Exercise
	 * @return total
	 */
	public double getTotal(){
		return total;
	}
	
	/**
	 * This method returns the weight of the Exercise in a Course
	 * @return weight
	 */
	public double getWeight(){
		return weight;
	}
	
	/**
	 * This method returns the Questions of an Exercise
	 * @return questions - List of Question
	 */
	public List<Question> getQuestions(){
		return questions;
	}
	
	/**
	 * This method returns the date of end of an exercise
	 * @return dateOfEnd - LocalDate that marks the end of the Exercise
	 */
	public LocalDate getDateOfEnd(){
		return dateOfEnd;
	}
	
	/**
	 * This method returns the date of beginning of an exercise
	 * @return dateOfBegining - LocalDate that marks the beginning of the Exercise
	 */
	public LocalDate getDateOfBegining(){
		return dateOfBegining;
	}

	/**
	 * This method begins an Exercise by a Student
	 * @param s - Student that begins the Exercise
	 * @return true if the student has been able to begin the exercise, false if the date has passed
	 */
	public boolean beginExercise(Student s){
		if(LocalDate.now().isAfter(dateOfEnd) == true)
			return false;
		s.addTestStudent(this);
		started = true;
		return true;
	}
	
	/**
	 * This method finishes an Exercise by a Student
	 * @param s - The Student who finishes the Exercise
	 */
	public void finishExercise(Student s){
		s.removeTestStudent(this);
		count = 0;
	}
	
	/**
	 * This method adds a Question to an Exercise
	 * @param q - The Question to be added.
	 */
	public void addQuestion(Question q){
		questions.add(q);
		total = total + q.getWeight();
	}
	
	/**
	 * This method changes the date of end of an Exercise to the given date
	 * @param dateOfEnd - LocalDate which is the new date of end
	 */
	public void editDateOfEnd(LocalDate dateOfEnd){
		if(started == true)
			return;
		this.dateOfEnd = dateOfEnd; 
	}
	
	/**
	 * This method changes the date of beginning of an Exercise to the given date
	 * @param dateOfBegining - LocalDate which is the new date of beginning
	 */
	public void editDateOfBegining(LocalDate dateOfBegining){
		if(started == true)
			return;
		this.dateOfBegining = dateOfBegining; 
	}
	
	/**
	 * This method returns the next question of the Exercise
	 * @return String containing the information of the Question
	 */
	public String showQuestionTest(){
		return questions.get(count).showQuestion();
	}
	
	/**
	 * This method answers a Question of an Exercise and returns its Answer
	 * @param answer - List of String containing the answers to the Question
	 * @return a - Answer to the current question, with the given answer
	 */
	public Answer answerQuestionTest(List<String> answer){
		Answer a = new Answer(answer, questions.get(count));
		count++;
		return a;
	}
	
	/**
	 * This method answers an Exercise by a Student
	 * @param student - Student who is answering the Exercise
	 * @param answers - List of Answers containing the answers of the Student
	 */
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

	/**
	 * This method returns the Course of the Exercise
	 * @return course - Course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * This method sets the Course of the Exercise
	 * @param course - The Course
	 */
	public void setCourse(Course course) {
		this.course = course;
	}	
	
	/**
	 * This method returns the unit of the exercise
	 * @return unit - Unit
	 */
	public Unit getUnit(){
		return unit;
	}
	
	/**
	 * This method sets the unit of the exercise
	 * @param unit - The Unit
	 */
	public void setUnit(Unit unit){
		this.unit = unit;
	}

	
}