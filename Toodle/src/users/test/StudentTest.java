package users.test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import users.Student;

import exercise.Answer;
import exercise.AnsweredTest;
import exercise.Exercise;
import exercise.FreeText;
import exercise.Question;


import application.Course;
import application.Unit;



public class StudentTest {
	
	Student s;
	AnsweredTest at;
	Exercise e;
	Course c;
	Unit u;
	
	List<Answer> answs;
	List<String> str;

	@Before
	public void setUp() throws Exception {
		Question q = new FreeText("Question", 1, 0.5, "Answer");
		answs = new ArrayList<Answer>();
		
		str = new ArrayList<String>();
		str.add("Answer");
		answs.add(new Answer(str, q));
		
		s = new Student("Paco", "Perez", "123", "pperez@esdu.es", "12");
		e = new Exercise(true, new Date(), new Date(LocalDateTime.now().plusDays(10).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()), 1.5);
		e.addQuestion(q);
		at = new AnsweredTest(e, answs);
		c = new Course(true, "Course", "This is a Course");
		u = new Unit(true, "Unit");
		c.addUnit(u);
		u.addTest(e);
		
		s.acceptStudent(c);
		e.beginExercise(s);
		s.answerTest(e, answs);	
	}

	@Test
	public void testGetAnsweredTests() {			
		assertEquals(s.getAnsweredTests().get(0), at);
	}

	@Test
	public void testGetGradeCourse() {
		assertEquals(10.0, s.getGradeCourse(c), 0.0);
	}

	@Test
	public void testGetRegisteredCourses() {
		assertEquals(c, s.getRegisteredCourses().get(0));
	}

	@Test
	public void testGetCurrentExercises() {
		assertEquals(e, s.getCurrentExercises().get(0));
	}

	@Test
	public void testAddTestStudent() {
		Exercise e1 = new Exercise(true, new Date(), new Date(LocalDate.now().plusDays(10).toEpochDay()), 1.5);
		s.addTestStudent(e1);
		assertEquals(e1, s.getCurrentExercises().get(1));
	}

	@Test
	public void testRemoveTestStudent() {
		s.removeTestStudent(e);
		assertTrue(s.getCurrentExercises().isEmpty());
	}

	@Test
	public void testAnswerTest() {
		Exercise e1 = new Exercise(true, new Date(), new Date(LocalDate.now().plusDays(10).toEpochDay()), 1.5);
		s.answerTest(e1, answs);
		assertEquals(new AnsweredTest(e1, answs), s.getAnsweredTests().get(1));
	}

	@Test
	public void testAcceptStudent() {
		Course c1 = new Course(true, "Course 2", "This is Course 2");
		s.acceptStudent(c1);
		assertEquals(c1, s.getRegisteredCourses().get(1));
	}

	@Test
	public void testRejectStudent() {
		s.rejectStudent(c);
		assertEquals(c, s.getRejectedCourses().get(0));
	}

	@Test
	public void testExpellStudent() {
		s.expellStudent(c);
		assertEquals(c, s.getExpelledCourses().get(0));
	}

	@Test
	public void testGetAnsweredTest() {
		assertEquals(at, s.getAnsweredTest(e));
	}

	@Test
	public void testViewPastTest() {
		String str = new String();
		List<Answer> answers = at.getAnswers();
		for(int i = 0; i < answers.size(); i++){
			str = str + "\n" + answers.get(i).getQuestion().getQuestion() + " Answer: " + ((answers.get(i).getAnswer() != null) ? answers.get(i) : "Not answered");
		}
		assertEquals(str, s.viewPastTest(e));
	}

	@Test
	public void testCorrectTest() {
		assertEquals(10.0, s.correctTest(e), 0.0)
;	}
	
	@Test
	public void testApply() {
		Course c1 = new Course(true, "Course 2", "This is Course 2");
		s.apply(c1);
		assertEquals(c1, s.getPendingCourses().get(0));
	}

	@Test
	public void testGetPendingCourses() {
		Course c1 = new Course(true, "Course 2", "This is Course 2");
		s.apply(c1);
		assertEquals(c1, s.getPendingCourses().get(0));
	}

	@Test
	public void testGetRejectedCourses() {
		s.rejectStudent(c);
		assertEquals(c, s.getRejectedCourses().get(0));
	}
	
	@Test
	public void testGetName(){
		assertEquals("Paco Perez", s.getName());
	}
	
	@Test
	public void testGetEmail(){
		assertEquals("pperez@esdu.es", s.getEmail());
	}
	
	@Test
	public void testDeleteAnsweredTests(){
		s.deleteAnsweredTests(u);
		assertTrue(s.getAnsweredTests().isEmpty());
	}

}
