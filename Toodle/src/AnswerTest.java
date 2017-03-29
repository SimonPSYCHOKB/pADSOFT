import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class AnswerTest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Answer asa, ama, aft, atf;
	Question sa, ma, ft, tf;
	List<String> answs = new ArrayList<String>();
	List<String> answs1 = new ArrayList<String>();
	List<String> answs2 = new ArrayList<String>();
	List<String> answs3 = new ArrayList<String>();

	@Before
	public void setUp() throws Exception {
		List<String> options = new ArrayList<String>();
		
		options.add("1"); options.add("2"); options.add("3"); options.add("4");
		sa = new SingleAnswer("Question1", 1, 0.5, "1", options);
		answs.add("2"); answs.add("3");
		options = new ArrayList<String>();
		options.add("1"); options.add("2"); options.add("3"); options.add("4");
		ma = new MultipleAnswer("Question2", 1, 0.5, answs, options);
		ft = new FreeText("Question3", 1, 0.5, "4");
		tf = new TrueFalse("Question4", 1, 0.5, "false");		
		
		ama = new Answer(answs, ma);
		answs1.add("1");
		asa = new Answer(answs1, sa);
		answs2.add("4");
		aft = new Answer(answs2, ft);
		answs3.add("true");
		atf = new Answer(answs3, tf);
	}

	@Test
	public final void testAnswer() {
		answs.clear(); answs.add("2"); answs.add("3");
		Answer a = new Answer(answs, ma);
		assertTrue(a.equals(ama));
	}

	@Test
	public final void testGetAnswer() {
		assertTrue(ama.getAnswer().equals(answs));
		assertTrue(asa.getAnswer().equals(answs1));
		assertTrue(aft.getAnswer().equals(answs2));
		assertTrue(atf.getAnswer().equals(answs3));
	}

	@Test
	public final void testGetQuestion() {
		assertEquals(asa.getQuestion(), sa);
		assertEquals(ama.getQuestion(), ma);
		assertEquals(aft.getQuestion(), ft);
		assertEquals(atf.getQuestion(), tf);
	}

	@Test
	public final void testIsGraded() {
		ama.correctAnswer();
		assertTrue(ama.isGraded());
		assertTrue(!asa.isGraded());
		asa.correctAnswer();
		assertTrue(asa.isGraded());
	}

	@Test
	public final void testGetGrade() {
		aft.correctAnswer();
		atf.correctAnswer();
		asa.correctAnswer();
		ama.correctAnswer();
	
		assertEquals(asa.getGrade(), 1.0, 0.0);
		assertEquals(ama.getGrade(), 1.0, 0.0);
		assertEquals(aft.getGrade(), 1.0, 0.0);
		assertEquals(atf.getGrade(), -0.5, 0.0);
	}
	
	@Test
	public final void testIsBlank(){
		List<String> str = new ArrayList<String>();
		assertTrue(new Answer(str, sa).isBlank());
		
	}

}
