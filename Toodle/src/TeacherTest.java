import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TeacherTest {
	
	Teacher t;
	Student s;
	Course c;
	
	@Before
	public void setUp() throws Exception {
		t = new Teacher("Paco", "Perez", "teacher@esdu.es", "123");
		s = new Student("Pedro", "More", "123", "pmore@esdu.es", "12");
		c = new Course(true, "Course 1", "This is Course 1");
	}

	@Test
	public void testAcceptStudent() {
		s.apply(c);
		t.acceptStudent(s, c);
		assertEquals(c, s.getRegisteredCourses().get(0));
	}

	@Test
	public void testRejectStudent() {
		s.apply(c);
		t.rejectStudent(s, c);
		assertEquals(c, s.getRejectedCourses().get(0));
	}

	@Test
	public void testReadmitStudent() {
		s.apply(c);
		t.readmitStudent(s, c);
		assertEquals(c, s.getRegisteredCourses().get(0));
	}

	@Test
	public void testExpellStudent() {
		s.apply(c);
		t.expellStudent(s, c);
		assertEquals(c, s.getExpelledCourses().get(0));
	}

	@Test
	public void testViewStatistics() {
		fail("Not yet implemented");
	}

	@Test
	public void testSendNotification() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetName(){
		assertEquals("Paco Perez", t.getName());
	}
}
