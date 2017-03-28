import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StatisticTest {
	Application testToodle;
	
	Course c1;
	
	Student s1;
	Student s2;
	Student s3;
	
	Question q1;
	Question q2;
	Question q3;
	
	Exercise t1; 
	Exercise t2;
	Exercise t3;
	
	@Before
	public void setUp()  {
		
	//Create toodle	
	testToodle = new  Application ("Toodle/src/data.txt");
	
	//Get Students
	s1 = testToodle.getStudents().get(0);
	s2 = testToodle.getStudents().get(1);
	s3 = testToodle.getStudents().get(2);
	
	//Create Course
	c1= testToodle.createCourse(true, "TestCourse", "A test course (please dont fail us)");

	//Create exercises
	t1 = new Exercise(true, LocalDate.now(), LocalDate.now().plusDays(10), 0.3);
	t2 = new Exercise(true, LocalDate.now(), LocalDate.now().plusDays(5), 0.3);
	t3 = new Exercise(true, LocalDate.now(), LocalDate.now().plusDays(5), 0.3);
	
	//Create Questions
	List<String> options = new ArrayList<String>();
	options.add("1"); options.add("2"); options.add("3"); options.add("4");
	Question q = new SingleAnswer("SA", 1, 0.5, "1", options);
	t1.addQuestion(q);
	t2.addQuestion(q);
	t3.addQuestion(q);
	
	List<String> ma = new ArrayList<String>();
	ma.add("1"); ma.add("2");
	q = new MultipleAnswer("MA", 1, 0.5, ma, options);
	t1.addQuestion(q);
	t2.addQuestion(q);
	t3.addQuestion(q);
	
	q = new TrueFalse("TF", 1, 0.5, "true");
	t1.addQuestion(q);
	t2.addQuestion(q);
	t3.addQuestion(q);
	
	
	q = new FreeText("FT", 1, 0.5, "Hey");
	t1.addQuestion(q);
	t2.addQuestion(q);
	t3.addQuestion(q);
	
	//Add exercises
	c1.addTest(t1);
	c1.addTest(t2);
	c1.addTest(t3);
	
	//Accept students
	s1.acceptStudent(c1);
	s2.acceptStudent(c1);
	s3.acceptStudent(c1);
	
	
	//TEST 1 STUDENT 1 
	List<Answer> a = new ArrayList<Answer>();
	if(t1.beginExercise(s1) == false)
		fail("Fecha limite rebasada.\n");
	else{
		List<String> answ = new ArrayList<String>();
		answ.add("1");
		a.add(t1.answerQuestionTest(answ));

		answ = new ArrayList<String>();
		answ.add("1"); answ.add("2");
		a.add(t1.answerQuestionTest(answ));

		answ = new ArrayList<String>();
		answ.add("false");
		a.add(t1.answerQuestionTest(answ));

		answ = new ArrayList<String>();
		answ.add("1");
		a.add(t1.answerQuestionTest(answ));
					
		s1.answerTest(t1, a);
		t1.finishExercise(s1);

	}
	
	//TEST 2 STUDENT 1 
    a = new ArrayList<Answer>();
	if(t2.beginExercise(s1) == false)
		fail("Fecha limite rebasada.\n");
	else{
		List<String> answ = new ArrayList<String>();
		answ.add("1");
		a.add(t2.answerQuestionTest(answ));

		answ = new ArrayList<String>();
		answ.add("1"); answ.add("2");
		a.add(t2.answerQuestionTest(answ));

		answ = new ArrayList<String>();
		answ.add("false");
		a.add(t2.answerQuestionTest(answ));

		answ = new ArrayList<String>();
		answ.add("1");
		a.add(t2.answerQuestionTest(answ));
					
		s1.answerTest(t1, a);
		t1.finishExercise(s1);

	}
	
	//TEST 3 STUDENT 1
	a = new ArrayList<Answer>();
	if(t1.beginExercise(s1) == false)
		fail("Fecha limite rebasada.\n");
	else{
		List<String> answ = new ArrayList<String>();
		answ.add("2");
		a.add(t1.answerQuestionTest(answ));

		answ = new ArrayList<String>();
		answ.add("3"); answ.add("2");
		a.add(t1.answerQuestionTest(answ));

		answ = new ArrayList<String>();
		answ.add("true");
		a.add(t2.answerQuestionTest(answ));

		answ = new ArrayList<String>();
		answ.add("Hey");
		a.add(t3.answerQuestionTest(answ));
					
		s1.answerTest(t1, a);
		t1.finishExercise(s1);

	}
	
		
	}
	
	

	@Test
	public void testStatistic() {
		
		fail("Not yet implemented");
	}

	@Test
	public void testGetTest() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTest() {
		fail("Not yet implemented");
	}

}
