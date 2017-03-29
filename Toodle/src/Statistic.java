import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Simon,  blanca
 *
 */
public class Statistic implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private double mean =0;
	private Exercise test;
	private ArrayList<QuestionStatistic> qestStat = new ArrayList<QuestionStatistic>();
	private ArrayList <AnsweredTest> ansTests= new ArrayList<AnsweredTest>();
	  
	 
	public Statistic(Exercise t) {
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
		for (QuestionStatistic qs: qestStat){
			total+=qs.getMean();
			count++;
		}
		
		mean = total/count;
	}

	



	public String toString(){
		String  str = "A statistic:" +"\n"+mean ;
		for (QuestionStatistic qs: qestStat){
			str += "\n" + qs.toString();
		}
		
		return str;
	}

	public Exercise getTest() {
		return test;
	}



	public void setTest(Exercise test) {
		this.test = test;
	}

}
