import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class ApplicationTest {
	
	Application app;
	Course c;
	Unit u, su;

	@Before
	public void setUp() throws Exception {
		app = new Application("src/data.txt");
		c = new Course(true, "Course 1", "This is Course 1");
		u = new Unit(true, "Unit");
		su = new Unit(true, "SubUnit");
	}

	@Test
	public void testGetStudents() {
		List<Student> students = new ArrayList<Student>();
		try
		{
		  BufferedReader reader = new BufferedReader(new FileReader("src/data.txt"));
		  String line;
		  reader.readLine();
		  while ((line = reader.readLine()) != null)
		  {
		  	//Parse students
		  	String [] tokens = line.split(";");
		   	Student s = new Student(tokens[0], tokens[1],tokens[4],  tokens[2], 
		   			tokens[3]);
		   	students.add(s);
		    
		}
		reader.close();
		   
		}
		catch (Exception e)
		{
		  System.err.format("Exception occurred trying to read src/data.txt.");
		  e.printStackTrace();
		}
		
		List<Student> sts = app.getStudents();
		assertEquals(students.size(), sts.size(), 0.0);
		for(int i = 0; i < students.size(); i++)
			assertEquals(students.get(i), sts.get(i));
	}

	@Test
	public void testGetCourses() {
		Teacher teacher = app.getTeacher();
		app.logIn(teacher, "123");
		app.createCourse(true, "Course 1", "This is Course 1");
		assertEquals(c, app.getCourses().get(0));
	}

	@Test
	public void testLogIn() {
		Teacher teacher = app.getTeacher();
		assertTrue(!app.logIn(teacher, "12"));
		assertTrue(app.logIn(teacher, "123"));
	}

	@Test
	public void testLogOut() {
		Teacher teacher = app.getTeacher();
		app.logIn(teacher, "123");
		app.logOut();
		assertEquals(app.getCurrentUser(), null);
	}

	@Test
	public void testCreateCourse() {
		assertEquals(c, app.createCourse(true, "Course 1", "This is Course 1"));
	}

	@Test
	public void testCreateUnit() {
		assertEquals(u, app.createUnit(true, "Unit"));
	}

	@Test
	public void testAddUnitToCourse() {
		app.addUnitToCourse(u, c);
		assertEquals(c.getUnits().get(0), u);
	}

	@Test
	public void testAddSubSectionToUnit() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateNote() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddNoteToUnit() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateExercise() {
		fail("Not yet implemented");
	}

	@Test
	public void testStudents() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTeacher() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTeacher() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCurrentUser() {
		fail("Not yet implemented");
	}

}