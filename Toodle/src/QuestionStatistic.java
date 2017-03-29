import java.util.ArrayList;

public class QuestionStatistic {
	
	private int correctNumber=0;
	private int wrongNumber=0;
	private int blankNumber=0;
	
	private Question question;
	private ArrayList<Answer> answers = new ArrayList<Answer>();
	
	private double mean=0;
	
	public QuestionStatistic (Question q, ArrayList<Answer> aGiven ){
		question = q;
		answers = aGiven;
		int total=0;
		int people=0;
		
		for(Answer a : aGiven){
			if(a.isBlank()){
				blankNumber++;
			}
			else if(question.checkIfCorrect(a)){
				correctNumber++;
			}else{
				wrongNumber++;
			}
			
			total+=a.getGrade();
			people++;
			
		}//For answer
		
		//Compute average
		
		mean = (total/people);
		
	}
	
	public int getCorrectNumber() {
		return correctNumber;
	}
	public void setCorrectNumber(int correctNumber) {
		this.correctNumber = correctNumber;
	}
	public int getWrongNumber() {
		return wrongNumber;
	}
	public void setWrongNumber(int wrongNumber) {
		this.wrongNumber = wrongNumber;
	}
	public int getBlankNumber() {
		return blankNumber;
	}
	public void setBlankNumber(int blankNumber) {
		this.blankNumber = blankNumber;
	}
	public ArrayList<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(ArrayList<Answer> answers) {
		this.answers = answers;
	}

	public double getMean() {
		return mean;
	}
	
	public String toString(){
		return "mean: "+mean+ " correct: " +correctNumber+
	" wrong: " + wrongNumber +
	" blank: " + blankNumber;
	}

	
	
	

}
