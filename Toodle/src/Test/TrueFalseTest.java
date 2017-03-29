package Test;
	import static org.junit.Assert.*;

	import java.util.ArrayList;
import java.util.List;

	import org.junit.Before;
import org.junit.Test;

public class TrueFalseTest {
	
	TrueFalse tf;
	Answer aFalse, aTrue;

	@Before
	public void setUp() throws Exception {
		List<String> answ = new ArrayList<String>();
		
		tf = new TrueFalse("TrueFalse", 1, 0.5, "Answer");
		answ.add("Answer");
		aTrue = new Answer(answ, tf);
		answ = new ArrayList<String>();
		answ.add("Hello");
		aFalse = new Answer(answ, tf);
	}

	@Test
	public final void testCheckIfCorrect() {
		assertTrue(tf.checkIfCorrect(aTrue));
		assertTrue(!tf.checkIfCorrect(aFalse));
	}

	@Test
	public final void testTrueFalse() {
		assertTrue(tf.equals(new TrueFalse("TrueFalse", 1, 0.5, "Answer")));
	}
	
	@Test
	public final void testGetQuestion() {
		assertTrue("TrueFalse".equals(tf.getQuestion()));
	}
	
	@Test
	public final void testGetWeight() {
		assertEquals(1, tf.getWeight(), 0.0);
	}
	
	@Test
	public final void testGetPenalty() {
		assertEquals(0.5, tf.getPenalty(), 0.0);
	}
}