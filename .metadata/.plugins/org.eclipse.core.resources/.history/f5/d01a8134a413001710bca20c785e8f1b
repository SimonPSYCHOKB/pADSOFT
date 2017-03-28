import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class AnsweredTestTest {
	
	AnsweredTest at;
	Exercise test;
	List<Answer> answers;

	@Before
	public void setUp() throws Exception {
		test = new Exercise(true, LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), 1.5);
		answers = new ArrayList<Answer>();
		
		Question q = new SingleAnswer("SA", 1, 0.5, "1");
		test.addQuestion(q);
		List<String> ma = new ArrayList<String>();
		ma.add("1"); ma.add("2");
		q = new MultipleAnswer("MA", 1, 0.5, ma);
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
