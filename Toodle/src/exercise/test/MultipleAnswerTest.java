package exercise.test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exercise.Answer;
import exercise.MultipleAnswer;




public class MultipleAnswerTest {
	
	MultipleAnswer ma;
	Answer aFalse, aTrue;

	@Before
	public void setUp() throws Exception {
		List<String> answ = new ArrayList<String>();
		List<String> options = new ArrayList<String>(); 
		
		answ.add("Answer1"); answ.add("Answer2");
		options.add("Answer1"); options.add("Answer3"); options.add("Answer2");
		ma = new MultipleAnswer("MultipleAnswer", 1, 0.5, answ, options);
		answ = new ArrayList<String>();
		answ.add("Answer2"); answ.add("Answer1");
		aTrue = new Answer(answ, ma);
		answ = new ArrayList<String>();
		answ.add("Answer3"); answ.add("Answer2");
		aFalse = new Answer(answ, ma);
	}

	@Test
	public final void testCheckIfCorrect() {
		assertTrue(ma.checkIfCorrect(aTrue));
		assertTrue(!ma.checkIfCorrect(aFalse));
	}

	@Test
	public final void testMultipleAnswer() {
		List<String> answ = new ArrayList<String>();
		List<String> options = new ArrayList<String>();
		
		answ.add("Answer2"); answ.add("Answer1");
		options.add("Answer1"); options.add("Answer3"); options.add("Answer2");
		assertTrue(ma.equals(new MultipleAnswer("MultipleAnswer", 1, 0.5, answ, options)));
	}
	
	@Test
	public final void testGetQuestion() {
		assertTrue("MultipleAnswer".equals(ma.getQuestion()));
	}
	
	@Test
	public final void testGetWeight() {
		assertEquals(1, ma.getWeight(), 0.0);
	}
	
	@Test
	public final void testGetPenalty() {
		assertEquals(0.5, ma.getPenalty(), 0.0);
	}

}
