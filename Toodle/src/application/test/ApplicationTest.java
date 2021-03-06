package application.test;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import users.Student;
import users.Teacher;

import exercise.Exercise;

import application.Application;
import application.Course;
import application.Note;
import application.Unit;



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
		app = new Application("Toodle/src/data.txt");
		c = new Course(true, "Course 1", "This is Course 1");
		u = new Unit(true, "Unit");
		su = new Unit(true, "SubUnit");
		n = new Note("Note", true);
		e = new Exercise(true, new Date(), new Date(LocalDate.now().plusDays(10).toEpochDay()), 1);
		s = new Student("Jorge", "Alcazar", "JoA", "Jorge.Alcazar@esdu.es", "1289");
		
		teacher = app.getTeacher();
		app.logIn("Teacher Peres", "123");
	}

	@Test
	public void testGetStudents() {
		List<Student> students = new ArrayList<Student>();
		try
		{
		  BufferedReader reader = new BufferedReader(new FileReader("Toodle/src/data.txt"));
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
