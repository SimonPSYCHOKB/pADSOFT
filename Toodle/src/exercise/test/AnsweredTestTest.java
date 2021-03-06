package exercise.test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exercise.Answer;
import exercise.AnsweredTest;
import exercise.Exercise;
import exercise.FreeText;
import exercise.MultipleAnswer;
import exercise.Question;
import exercise.SingleAnswer;
import exercise.TrueFalse;




public class AnsweredTestTest {
	
	AnsweredTest at;
	Exercise test;
	List<Answer> answers;

	@Before
	public void setUp() throws Exception {
		List<String> options = new ArrayList<String>();
		test = new Exercise(true, new Date(), new Date(LocalDate.now().plusDays(10).toEpochDay()), 1.5);
		answers = new ArrayList<Answer>();
		
		options.add("1"); options.add("2"); options.add("3"); options.add("4");
		Question q = new SingleAnswer("SA", 1, 0.5, "1", options);
		test.addQuestion(q);
		List<String> ma = new ArrayList<String>();
		options = new ArrayList<String>();
		options.add("1"); options.add("2"); options.add("3"); options.add("4");
		ma.add("1"); ma.add("2");
		q = new MultipleAnswer("MA", 1, 0.5, ma, options);
		test.addQuestion(q);
		q = new TrueFalse("TF", 1, 0.5, "true");
		test.addQuestion(q);
		q = new FreeText("FT", 1, 0.5, "Hey");
		test.addQuestion(q);
		
		test.showQuestionTest();
		List<String> answ = new ArrayList<String>();
		answ.add("1");
		answers.add(test.answerQuestionTest(answ));
		test.showQuestionTest();
		answ = new ArrayList<String>();
		answ.add("1"); answ.add("2");
		answers.add(test.answerQuestionTest(answ));
		test.showQuestionTest();
		answ = new ArrayList<String>();
		answ.add("false");
		answers.add(test.answerQuestionTest(answ));
		test.showQuestionTest();
		answ = new ArrayList<String>();
		answ.add("1");
		answers.add(test.answerQuestionTest(answ));
		
		at = new AnsweredTest(test, answers);
	}

	@Test
	public final void testAnsweredTest() {
		AnsweredTest testat = new AnsweredTest(test, answers);
		assertTrue(testat.equals(at));
	}

	@Test
	public final void testGetTest() {
		assertEquals(test, at.getTest());
	}

	@Test
	public final void testGetAnswers() {
		assertTrue(answers.equals(at.getAnswers()));
	}

	@Test
	public final void testGetGradeTest() {
		at.correctAnsweredTest();
		assertEquals(at.getGradeTest(), 2.5, 0.0);
	}

	@Test
	public final void testGetRelativeGradeTest() {
		at.correctAnsweredTest();
		assertEquals(at.getRelativeGradeTest(), 3.75, 0.0);
	}

}
