import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class CourseTest {
	
	Course c;
	Exercise e1;
	Exercise e2;
	Unit u;
	Student s;
	
	@Before
	public void setUp() throws Exception {
		e1 = new Exercise(true, LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), 1.5);
		e2 = new Exercise(true, LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), 1.4);
		u = new Unit(true, "Unit 1");
		
		c = new Course(true, "Course 1", "This is Course 1");
		
		s = new Student("Paco", "Perez", "123", "pperez@esdu.es", "12");
	}

	@Test
	public void testIsVisibility() {
		assertTrue(c.isVisibility());
	}

	@Test
	public void testGetTotal() {
		assertEquals(c.getTotal(), 0.0, 0.0);
	}

	@Test
	public void testGetTitle() {
		assertEquals(c.getTitle(), "Course 1");
	}

	@Test
	public void testGetTests() {
		c.addTest(e1); c.addTest(e2);
		List<Exercise> exe = new ArrayList<Exercise>();
		exe.add(e1); exe.add(e2);
		assertEquals(exe, c.getTests());
	}

	@Test
	public void testGetDescription() {
		assertEquals(c.getDescription(), "This is Course 1");
	}
	
	@Test
	public void testGetExpelledStudents(){
		assertTrue(c.getExpelledStudents().isEmpty());
	}

	@Test
	public void testGetUnits() {
		c.addLearningObj(u);
		assertEquals(c.getUnits().get(0), u);
	}

	@Test
	public void testAddLearningObj() {
		c.addLearningObj(u);
		assertEquals(c.getUnits().size(), 1.0, 0.0);
	}

	@Test
	public void testAddTest() {
		c.addTest(e1); 
		assertEquals(c.getTests().size(), 1.0, 0.0);
		c.addTest(e2);
		assertEquals(c.getTests().size(), 2.0, 0.0);
	}

	@Test
	public void testGetStudents() {
		s.acceptStudent(c);
		assertEquals(c.getStudents().get(0), s);
	}

	@Test
	public void testAddStudents() {
		Student st = new Student("Pedro", "Ramo", "23", "pramo@esdu.es", "123");
		c.addStudents(st);
		assertEquals(c.getStudents().get(0), st);
	}
	
	@Test
	public void testExpelledStudents() {
		Student st = new Student("Pedro", "Ramo", "23", "pramo@esdu.es", "123");
		st.acceptStudent(c);
		c.expellStudents(st);
		assertEquals(c.getExpelledStudents().get(0), st);
	}

}