package Statistics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Application.Application;
import Application.Course;
import Test.Answer;
import Test.AnsweredTest;
import Test.Exercise;
import Test.Question;
import Users.Student;

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
	private ArrayList<QuestionStatistic> qestStat = new ArrayList<QuestionStatistic>();
	private ArrayList <AnsweredTest> ansTests= new ArrayList<AnsweredTest>();
	  
	 
	/**
	 * Constructor
	 * @param t - Exercise of which the statistic is going to be calculated
	 */
	public Statistic(Exercise t) {
		super();
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
		
		this.setMean(total/count);
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
		return qestStat;
	}

}