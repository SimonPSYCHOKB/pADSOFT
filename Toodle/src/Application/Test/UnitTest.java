package Application.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import Test.Exercise;

import Application.Course;
import Application.Note;
import Application.Unit;


public class UnitTest {
	
	Course c;
	Unit u;
	Unit su;
	Exercise e;
	Note n;

	@Before
	public void setUp() throws Exception {
		c = new Course(true, "Course", "This is a Course");
		
		u = new Unit(true, "Unit 1");
		su = new Unit(true, "SubUnit 1");
		
		e = new Exercise(true, LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), 1);
		n = new Note("Note", true);
		
		c.addUnit(u);
	}

	@Test
	public void testGetSubUnits() {
		assertTrue(u.getSubUnits().isEmpty());
	}

	@Test
	public void testGetNotes() {
		assertTrue(u.getNotes().isEmpty());
	}

	@Test
	public void testCreateSubSection() {
		u.createSubSection(su);
		assertEquals(su, u.getSubUnits().get(0));
	}

	@Test
	public void testAddTest() {
		u.addTest(e);
		assertEquals(u, e.getUnit());
	}

	@Test
	public void testAddNotes() {
		u.addNotes(n);
		assertEquals(n, u.getNotes().get(0));
	}

	@Test
	public void testSetCourse() {
		Course course = new Course(true, "Course 2", "This is a Test");
		u.setCourse(course);
		assertEquals(course, u.getCourse());
	}

	@Test
	public void testGetCourse() {
		assertEquals(c, u.getCourse());
	}

	@Test
	public void testIsVisibility() {
		assertTrue(u.isVisibility());
	}

	@Test
	public void testSetVisibility() {
		u.setVisibility(false);
		assertTrue(!u.isVisibility());
	}

}