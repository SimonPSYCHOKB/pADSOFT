import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class ApplicationTest {
	
	Application app;
	Course c;
	Unit u, su;
	Note n;
	Exercise e;

	@Before
	public void setUp() throws Exception {
		app = new Application("src/data.txt");
		c = new Course(true, "Course 1", "This is Course 1");
		u = new Unit(true, "Unit");
		su = new Unit(true, "SubUnit");
		n = new Note("Note", LocalDate.now());
		e = new Exercise(true, LocalDate.now(), LocalDate.now(), 1);
		
		Teacher teacher = app.getTeacher();
		app.logIn(teacher, "123");
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
		app.logOut();
		assertTrue(!app.logIn(teacher, "12"));
		assertTrue(app.logIn(teacher, "123"));
	}

	@Test
	public void testLogOut() {
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
		app.addSubSectionToUnit(u, su);
		assertEquals(u.getSubUnits().get(0), su);
	}

	@Test
	public void testCreateNote() {
		assertEquals(n, app.createNote("Note", LocalDate.now()));
	}

	@Test
	public void testAddNoteToUnit() {
		app.addNoteToUnit(u, n);
		assertEquals(n, u.getNotes().get(0));
	}

	@Test
	public void testCreateExercise() {
		assertEquals(e, app.createExercise(true, LocalDate.now(), LocalDate.now(), 1));
	}

	@Test
	public void testGetTeacher() {
		assertEquals(app.getTeacher(), new Teacher("Teacher", "Peres",  "123", "teacher@esdu.es"));
	}

	@Test
	public void testSetTeacher() {
		Teacher nteacher = new Teacher("Paco", "Perez", "123", "teacher@esdu.es");
		app.setTeacher(nteacher);
		assertEquals(app.getTeacher(), nteacher);
	}

	@Test
	public void testGetCurrentUser() {
		assertEquals(app.getCurrentUser(), new Teacher("Teacher", "Peres",  "123", "teacher@esdu.es"));
	}

}
