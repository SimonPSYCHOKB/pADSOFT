package statistics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import users.Student;

import exercise.Answer;
import exercise.AnsweredTest;
import exercise.Exercise;
import exercise.Question;


import application.Application;
import application.Course;


/**
 * @author Simon valcarcel
 * @author Blanca Martin
 * 
 * This class contains the information of a statistic of a test
 *
 */
public class Statistic extends Stats implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Exercise test;
	private ArrayList<QuestionStatistic> qestStat;
	private ArrayList <AnsweredTest> ansTests;
	  
	 
	/**
	 * Constructor
	 * @param t - Exercise of which the statistic is going to be calculated
	 */
	public Statistic(Exercise t) {
		super();
		qestStat = new ArrayList<QuestionStatistic>();
		ansTests= new ArrayList<AnsweredTest>();
		test= t;
		
		//Get Answered Tests for all Students in the course
		Course c = t.getCourse();
		for (Student s : Application.getStudents(c)){
			AnsweredTest at = s.getAnsweredTest(test);
			if(at !=null){
				ansTests.add(at);
			}
		}
		
		//Create QuestionStatistics for every question in the test
		
		//Get all the Questions. One QuestionStatistic per Question
		for(Question q: test.getQuestions()){
			ArrayList<Answer> answers = new ArrayList<Answer> ();
		
			//For every student that responded
			for(AnsweredTest at : ansTests){
				//Get the matching Answer
				for(Answer a : at.getAnswers()){
					if(a.getQuestion().equals(q)){
						answers.add(a);
						break;
					}//if
				}//Student
				
			}//Each question
			
			qestStat.add(new QuestionStatistic(q, answers));
			
		}//Questions
		
		int count=0;
		double total=0;
		for (AnsweredTest qs: ansTests){
			total+=qs.getGradeTest();
			count++;
		}
		if(count == 0) return;
		this.setMean(total/count);
	}
	
	/**
	 * Constructor for a single student
	 * @param e - Exercise
	 * @param s - Student
	 */
	public Statistic(Exercise e, Student s){
		test = e;
		ansTests = new ArrayList<AnsweredTest>();
		qestStat = new ArrayList<QuestionStatistic>();
//		if(s.getAnsweredTest(e) == null) return;
//		ansTests.add(s.getAnsweredTest(e));
		
		if(s.getAnsweredTest(e) != null)
			setMean(s.getAnsweredTest(e).getGradeTest());
		
		for(Question q : e.getQuestions()){
			List<Answer> answ = new ArrayList<Answer>();
			if(s.getAnsweredTest(e) == null)
				qestStat.add(new QuestionStatistic(q, null));
			else
				for(Answer a : s.getAnsweredTest(e).getAnswers())
					if(a.getQuestion().equals(q)){
						answ.add(a);
						qestStat.add(new QuestionStatistic(q, answ));
					}
		}
	}

	@Override
	public String toString(){
		String  str = "\nA statistic for test: " +"\nMean for the test: "+ getMean() ;
		for (QuestionStatistic qs: qestStat){
			str += "\n" + qs.toString();
		}
		
		return str;
	}
	
	/**
	 * This method returns the statistic for each one of the questions of the
	 * statistic (of an exercise)
	 * @return questStat - List of QuestionStatistic
	 */
	public List<QuestionStatistic> getQuestionStatistics(){
		return Collections.unmodifiableList(qestStat);
	}
	
	/**
	 * This method returns the exercise of the statistic
	 * @return the exercise
	 */
	public Exercise getExercise(){
		return test;
	}

}
