import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class FreeTextTest {
	
	FreeText ft;
	Answer aFalse, aTrue;

	@Before
	public void setUp() throws Exception {
		List<String> answ = new ArrayList<String>();
		
		ft = new FreeText("FreeText", 1, 0.5, "Answer");
		answ.add("Answer");
		aTrue = new Answer(answ, ft);
		answ = new ArrayList<String>();
		answ.add("Hello");
		aFalse = new Answer(answ, ft);
		
	}

	@Test
	public final void testCheckIfCorrect() {
		assertTrue(ft.checkIfCorrect(aTrue));
		assertTrue(!ft.checkIfCorrect(aFalse));
	}

	@Test
	public final void testFreeText() {
		assertTrue(ft.equals(new FreeText("FreeText", 1, 0.5, "Answer")));
	}
	
	@Test
	public final void testGetQuestion() {
		assertTrue("FreeText".equals(ft.getQuestion()));
	}
	
	@Test
	public final void testGetWeight() {
		assertEquals(1, ft.getWeight(), 0.0);
	}
	
	@Test
	public final void testGetPenalty() {
		assertEquals(0.5, ft.getPenalty(), 0.0);
	}

}
