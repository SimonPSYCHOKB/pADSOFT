package Application.Test;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Application.Application;
import Application.Course;
import Application.Note;
import Application.Unit;
import Test.Exercise;
import Test.FreeText;
import Test.MultipleAnswer;
import Test.Question;
import Test.SingleAnswer;
import Test.TrueFalse;
import Users.Student;
import Users.Teacher;


public class ApplicationTest {
	
	Application app;
	Course c;
	Unit u, su;
	Note n;
	Exercise e;
	Student s;
	Teacher teacher;

	@Before
	public void setUp() throws Exception {
		app = new Application("src/data.txt");
		c = new Course(true, "Course 1", "This is Course 1");
		u = new Unit(true, "Unit");
		su = new Unit(true, "SubUnit");
		n = new Note("Note", true);
		e = new Exercise(true, LocalDate.now(), LocalDate.now(), 1);
		s = new Student("Jorge", "Alcazar", "JoA", "Jorge.Alcazar@esdu.es", "1289");
		
		teacher = app.getTeacher();
		app.logIn("Teacher Peres", "123");
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
		app.logIn("Teacher Peres", "123");
		app.createCourse(true, "Course 1", "This is Course 1");
		assertEquals(c, app.getCourses().get(0));
	}

	@Test
	public void testLogIn() {
		app.logOut();
		assertTrue(!app.logIn("Teacher Peres", "12"));
		assertTrue(app.logIn("Teacher Peres", "123"));
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
	public void testDeleteUnit(){
		app.addUnitToCourse(u, c);
		app.deleteUnit(u);
		assertTrue(c.getUnits().isEmpty());
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
		assertEquals(n, app.createNote("Note", true));
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
	public void testCreateSingleAnswer(){
		List<String> str = new ArrayList<String>();
		str.add("1");
		assertEquals(new SingleAnswer("SingleAnswer", 1, 0.5, "1", str) , app.createSingleAnswer("SingleAnswer", 1, 0.5, "1", str));
	}
	
	@Test
	public void testCreateMultipleAnswer(){
		List<String> str = new ArrayList<String>();
		List<String> answ = new ArrayList<String>();
		str.add("1");
		assertEquals(new MultipleAnswer("MultipleAnswer", 1, 0.5, answ, str), app.createMultipleAnswer("MultipleAnswer", 1, 0.5, answ, str));
	}
	
	@Test
	public void testCreateTrueFalse(){
		assertEquals(new TrueFalse("TrueFalse", 1, 0.5, "true"), app.createTrueFalse("TrueFalse", 1, 0.5, "true"));
	}
	
	@Test
	public void testCreateFreeText(){
		assertEquals(new FreeText("FreeText", 1, 0.5, "Hy"), app.createFreeText("FreeText", 1, 0.5, "Hy"));
	}
	
	@Test
	public void testAddQuestionTest(){
		Question q = new TrueFalse("TrueFalse", 1, 0.5, "true");
		app.addQuestionTest(q, e);
		assertEquals(q, e.getQuestions().get(0));
	}
	
	@Test
	public void testAddTestToCourse(){
		app.addTestToCourse(e, c);
		assertEquals(e, c.getTests().get(0));
	}
	
	@Test
	public void testApplyStudent(){
		app.logOut();
		app.logIn(s.getName(), "JoA");
		app.applyStudent(s, c);
		assertEquals(c, s.getPendingCourses().get(0));
	}
	
	@Test
	public void testAcceptStudent(){
		app.logOut();
		app.logIn(s.getName(), "JoA");
		app.applyStudent(s, c);
		app.logOut();
		app.logIn("Teacher Peres", "123");
		app.acceptStudent(s, c);
		assertEquals(c, s.getRegisteredCourses().get(0));
		assertEquals(0.0, s.getPendingCourses().size(), 0.0);
	}
	
	@Test
	public void testRejectStudent(){
		app.logOut();
		app.logIn(s.getName(), "JoA");
		app.applyStudent(s, c);
		app.logOut();
		app.logIn("Teacher Peres", "123");
		app.rejectStudent(s, c);
		assertEquals(c, s.getRejectedCourses().get(0));
		assertEquals(0.0, s.getRegisteredCourses().size(), 0.0);
		assertEquals(0.0, s.getPendingCourses().size(), 0.0);
	}
	
	@Test
	public void testExpellStudent(){
		app.logOut();
		app.logIn(s.getName(), "JoA");
		app.applyStudent(s, c);
		app.logOut();
		app.logIn("Teacher Peres", "123");
		app.acceptStudent(s, c);
		app.expellStudent(s, c);
		assertEquals(c, s.getExpelledCourses().get(0));
		assertEquals(0.0, s.getRegisteredCourses().size(), 0.0);
		assertEquals(0.0, s.getPendingCourses().size(), 0.0);
	}

	@Test
	public void testGetTeacher() {
		assertEquals(app.getTeacher(), new Teacher("Teacher", "Peres",  "123", "teacher@esdu.es"));
	}

	@Test
	public void testGetCurrentUser() {
		assertEquals(app.getCurrentUser(), new Teacher("Teacher", "Peres",  "123", "teacher@esdu.es"));
	}
	
	

}
