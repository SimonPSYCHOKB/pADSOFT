import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class ExerciseTest {
	
	Exercise e;
	Student s;
	Question sa, ma, ft, tf;

	@Before
	public void setUp() throws Exception {
		List<String> options = new ArrayList<String>();
		List<String> answs = new ArrayList<String>();
		
		options.add("1"); options.add("2"); options.add("3"); options.add("4");
		sa = new SingleAnswer("Question1", 1, 0.5, "1", options);
		answs.add("2"); answs.add("3");
		options = new ArrayList<String>();
		options.add("1"); options.add("2"); options.add("3"); options.add("4");
		ma = new MultipleAnswer("Question2", 1, 0.5, answs, options);
		ft = new FreeText("Question3", 1, 0.5, "4");
		tf = new TrueFalse("Question4", 1, 0.5, "false");
		
		e = new Exercise(true, LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), 1.5);
		e.addQuestion(sa); e.addQuestion(ma); e.addQuestion(ft); e.addQuestion(tf);
		
		s = new Student("Paco", "Perez", "123", "pperez@esdu.es", "12");
	}

	@Test
	public void testGetTotal() {
		assertEquals(e.getTotal(), 4.0, 0.0);
	}

	@Test
	public void testGetWeight() {
		assertEquals(e.getWeight(), 1.5, 0.0);
	}

	@Test
	public void testGetQuestions() {
		List<Question> myqs = e.getQuestions();
		assertEquals(sa, myqs.get(0));
		assertEquals(ma, myqs.get(1));
		assertEquals(ft, myqs.get(2));
		assertEquals(tf, myqs.get(3));
	}

	@Test
	public void testGetDateOfEnd() {
		assertTrue(LocalDate.now().plusDays(10).isEqual(e.getDateOfEnd()));
	}

	@Test
	public void testGetDateOfBegining() {
		assertTrue(LocalDate.now().minusDays(10).isEqual(e.getDateOfBegining()));
	}

	@Test
	public void testBeginExercise() {
		e.beginExercise(s);
		assertEquals(s.getCurrentExercises().get(0), e);
	}

	@Test
	public void testFinishExercise() {
		e.finishExercise(s);
		assertTrue(s.getCurrentExercises().isEmpty());
	}

	@Test
	public void testAddQuestion() {
		Question q = new TrueFalse("Question", 1, 0.5, "true");
		e.addQuestion(q);
		assertEquals(e.getQuestions().get(4), q);
	}

	@Test
	public void testEditDateOfEnd() {
		e.editDateOfEnd(LocalDate.now().plusDays(5));
		assertTrue(LocalDate.now().plusDays(5).isEqual(e.getDateOfEnd()));
	}

	@Test
	public void testEditDateOfBegining() {
		e.editDateOfBegining(LocalDate.now().plusDays(5));
		assertTrue(LocalDate.now().plusDays(5).isEqual(e.getDateOfBegining()));
	}

	@Test
	public void testShowQuestionTest() {
		assertEquals(e.showQuestionTest(), "Question1\n\t1\n\t2\n\t3\n\t4");
	}

	@Test
	public void testAnswerQuestionTest() {
		List<String> s = new ArrayList<String>();
		s.add("1");
		e.showQuestionTest();
		assertEquals(new Answer(s, sa), e.answerQuestionTest(s));
	}

	@Test
	public void testAnswerTest() {
		List<String> st = new ArrayList<String>();
		List<Answer> a = new ArrayList<Answer>();
		
		st.add("1");
		e.showQuestionTest();
		a.add(new Answer(st, sa));

		e.answerTest(s, a);
		
		assertEquals(s.getAnsweredTests().get(0), new AnsweredTest(e, a));
	}

	@Test
	public void testIsVisibility() {
		assertTrue(e.isVisibility());
	}

	@Test
	public void testSetVisibility() {
		e.setVisibility(false);
		assertTrue(!e.isVisibility());
	}

}
