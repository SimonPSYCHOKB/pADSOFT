package Test.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Test.Answer;
import Test.SingleAnswer;


public class SingleAnswerTest {
	
	SingleAnswer sa;
	Answer aFalse, aTrue;

	@Before
	public void setUp() throws Exception {
		List<String> answ = new ArrayList<String>();
		List<String> options = new ArrayList<String>();
		
		options.add("Answer"); options.add("Answer1"); options.add("Answer2");
		sa = new SingleAnswer("SingleAnswer", 1, 0.5, "Answer", options);
		answ.add("Answer");
		aTrue = new Answer(answ, sa);
		answ = new ArrayList<String>();
		answ.add("Hello");
		aFalse = new Answer(answ, sa);
	}

	@Test
	public final void testCheckIfCorrect() {
		assertTrue(sa.checkIfCorrect(aTrue));
		assertTrue(!sa.checkIfCorrect(aFalse));
	}

	@Test
	public final void testSingleAnswer() {
		List<String> options = new ArrayList<String>();
		options.add("Answer"); options.add("Answer1"); options.add("Answer2");
		assertTrue(sa.equals(new SingleAnswer("SingleAnswer", 1, 0.5, "Answer", options)));
	}
	
	@Test
	public final void testGetQuestion() {
		assertTrue("SingleAnswer".equals(sa.getQuestion()));
	}
	
	@Test
	public final void testGetWeight() {
		assertEquals(1, sa.getWeight(), 0.0);
	}
	
	@Test
	public final void testGetPenalty() {
		assertEquals(0.5, sa.getPenalty(), 0.0);
	}

}
