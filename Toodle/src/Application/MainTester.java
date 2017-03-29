package Application;
import static java.lang.System.*;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import Statistics.*;
import Test.*;
import Users.*;


public class MainTester {

	
	
	public static void main(String[] args){
	
	out.println("--Welcome to Toodle main tester!--\n");
	
	out.println("We will start by loading all the students\n");
	//Create Toodle application
	//Import Students
	Application toodle;
	try{
		toodle = new Application("src/data.txt");
	}catch(Exception e ){
		out.println("Failure when reading students\n");
		return;
	}
	
	out.println("-->Students read successfully\n");
	out.println(toodle.getStudents());
	//Import teacher
	Teacher teacher = toodle.getTeacher();
	
	out.println("Now lets log in the teacher\n");
	//Log in Teacher
	if(toodle.logIn(teacher, "123")){
		out.println("-->Teacher logged in successfully\n");
	}
	
	out.println("We proceed by creating all the courses\n");
	//Create new courses
	for (int i=1; i<4; i++){
		toodle.createCourse(true, "Course " + i, "This is course; "+i );
	}
	
	out.println("Lets display the courses");
	out.println(toodle.getCourses());
	
	out.println("\nNext, we will add three units to each course");
	//Create new units
		//For each course, we create three subunits
	for(Course c: toodle.getCourses()){
		Unit u1 = toodle.createUnit(true, c.toString()+ " Unit 1");
		Unit u2 = toodle.createUnit(true, c.toString()+ " Unit 2");
		Unit u3 = toodle.createUnit(true, c.toString()+ " Unit 3");
		//Unit u1 = new Unit(true, c.toString()+ " Unit 1");
		//Unit u2 = new Unit (false, c.toString()+ " Unit 2");
		//Unit u3 = new Unit (true, c.toString()+ " Unit 3");
		
		
		toodle.addUnitToCourse(u1, c);
		toodle.addUnitToCourse(u2, c);
		toodle.addUnitToCourse(u3, c);
	}
	out.println("\nWe can see how the courses look now: ");
	out.println(toodle.getCourses());
	//Create new sub-units
	
	out.println("\nEach Unit of each course will now have three subunits "
			+ "appended: ");
	for (Course c: toodle.getCourses()){
		List<Unit> units=  c.getUnits();
		for(Unit unit: units){
			Unit us1= toodle.createUnit(true, unit.toString()+ " Subunit 1");
			Unit us2= toodle.createUnit(true, unit.toString()+ " Subunit 2");
			Unit us3= toodle.createUnit(true, unit.toString()+ " Subunit 3");
			
			toodle.addSubSectionToUnit(unit, us1);
			toodle.addSubSectionToUnit(unit, us2);
			toodle.addSubSectionToUnit(unit, us3);
			
			//unit.createSubSection(us1);
			//unit.createSubSection(us2);
			//unit.createSubSection(us3);
		}	
	}
	
	out.println(toodle.getCourses());
	
	//Add notes
	out.println("\nNext we will add Notes to some Units");
	int i =0;
	for (Course c: toodle.getCourses()){
		Note n = toodle.createNote("This is note number "+ i, true);
		List<Unit> units =c.getUnits();
		Unit unit= units.get(i);
		toodle.addNoteToUnit(unit, n);
		i++;
	}
	
	out.println(toodle.getCourses());
	//Create tests of three types
	out.println("\n----------------------------------------------------");
	out.println("\nHere, we will create three tests with three "
			+ "questions each"+ "\n Then, the students will answer.");
	Course c1;
	
	Student s1;
	Student s2;
	Student s3;
	
	Exercise t1; 
	Exercise t2;
	Exercise t3;
	
	//Create Questions for tests
	s1 = toodle.getStudents().get(0);
	s2 = toodle.getStudents().get(1);
	s3 = toodle.getStudents().get(2);
	
	//Create Course
	c1= toodle.createCourse(true, "TestCourse", "A test course (please dont fail us)");

	//Create exercises
	t1 = new Exercise(true, LocalDate.now(), LocalDate.now().plusDays(10), 0.3);
	t2 = new Exercise(true, LocalDate.now(), LocalDate.now().plusDays(5), 0.3);
	t3 = new Exercise(true, LocalDate.now(), LocalDate.now().plusDays(5), 0.3);
	
	//Create Questions
	List<String> options = new ArrayList<String>();
	options.add("1"); options.add("2"); options.add("3"); options.add("4");
	Question q = new SingleAnswer("SA", 1, 0.5, "1", options);
	t1.addQuestion(q);
	t2.addQuestion(q);
	t3.addQuestion(q);
	
	List<String> ma = new ArrayList<String>();
	ma.add("1"); ma.add("2");
	q = new MultipleAnswer("MA", 1, 0.5, ma, options);
	t1.addQuestion(q);
	t2.addQuestion(q);
	t3.addQuestion(q);
	
	q = new TrueFalse("TF", 1, 0.5, "true");
	t1.addQuestion(q);
	t2.addQuestion(q);
	t3.addQuestion(q);
	
	
	q = new FreeText("FT", 1, 0.5, "Hey");
	t1.addQuestion(q);
	t2.addQuestion(q);
	t3.addQuestion(q);
	
	//Add exercises
	c1.addTest(t1);
	c1.addTest(t2);
	c1.addTest(t3);
	
	//Accept students
	s1.acceptStudent(c1);
	s2.acceptStudent(c1);
	s3.acceptStudent(c1);
	
	toodle.logOut();
	
	
	
	//TEST 1 STUDENT 1 
	List<Answer> a = new ArrayList<Answer>();
	if(t1.beginExercise(s1) == false)
		fail("Fecha limite rebasada.\n");
	else{
		List<String> answ = new ArrayList<String>();
		answ.add("1");
		a.add(t1.answerQuestionTest(answ));

		answ = new ArrayList<String>();
		answ.add("1"); answ.add("2");
		a.add(t1.answerQuestionTest(answ));

		answ = new ArrayList<String>();
		answ.add("false");
		a.add(t1.answerQuestionTest(answ));

		answ = new ArrayList<String>();
		answ.add("1");
		a.add(t1.answerQuestionTest(answ));
					
		s1.answerTest(t1, a);
		t1.finishExercise(s1);

	}
	
	//TEST 2 STUDENT 1 
    a = new ArrayList<Answer>();
	if(t2.beginExercise(s1) == false)
		fail("Fecha limite rebasada.\n");
	else{
		List<String> answ = new ArrayList<String>();
		answ.add("1");
		a.add(t2.answerQuestionTest(answ));

		answ = new ArrayList<String>();
		answ.add("1"); answ.add("2");
		a.add(t2.answerQuestionTest(answ));

		answ = new ArrayList<String>();
		answ.add("false");
		a.add(t2.answerQuestionTest(answ));

		answ = new ArrayList<String>();
		answ.add("1");
		a.add(t2.answerQuestionTest(answ));
					
		s1.answerTest(t2, a);
		t2.finishExercise(s1);

	}
	
	//TEST 3 STUDENT 1
	a = new ArrayList<Answer>();
	if(t3.beginExercise(s1) == false)
		fail("Fecha limite rebasada.\n");
	else{
		List<String> answ = new ArrayList<String>();
		answ.add("2");
		a.add(t3.answerQuestionTest(answ));

		answ = new ArrayList<String>();
		answ.add("3"); answ.add("2");
		a.add(t3.answerQuestionTest(answ));

		answ = new ArrayList<String>();
		answ.add("true");
		a.add(t3.answerQuestionTest(answ));

		answ = new ArrayList<String>();
		answ.add("Hey");
		a.add(t3.answerQuestionTest(answ));
					
		s1.answerTest(t3, a);
		t3.finishExercise(s1);
		
	}

		
		//TEST 1 STUDENT 2
		 a = new ArrayList<Answer>();
		if(t1.beginExercise(s2) == false)
			fail("Fecha limite rebasada.\n");
		else{
			List<String>answ = new ArrayList<String>();
			answ.add("1");
			a.add(t1.answerQuestionTest(answ));

			answ = new ArrayList<String>();
			answ.add("1"); answ.add("2");
			a.add(t1.answerQuestionTest(answ));

			answ = new ArrayList<String>();
			answ.add("true");
			a.add(t1.answerQuestionTest(answ));

			answ = new ArrayList<String>();
			answ.add("Hey");
			a.add(t1.answerQuestionTest(answ));
						
			s2.answerTest(t1, a);
			t1.finishExercise(s2);

		}
		
		//TEST 2 STUDENT 2 
	    a = new ArrayList<Answer>();
		if(t2.beginExercise(s2) == false)
			fail("Fecha limite rebasada.\n");
		else{
			List<String>answ = new ArrayList<String>();
			answ.add("1");
			a.add(t2.answerQuestionTest(answ));

			answ = new ArrayList<String>();
			answ.add("1"); answ.add("2");
			a.add(t2.answerQuestionTest(answ));

			answ = new ArrayList<String>();
			answ.add("false");
			a.add(t2.answerQuestionTest(answ));

			answ = new ArrayList<String>();
			answ.add("1");
			a.add(t2.answerQuestionTest(answ));
						
			s2.answerTest(t2, a);
			t2.finishExercise(s2);

		}
		
		//TEST 3 STUDENT 2
		a = new ArrayList<Answer>();
		if(t3.beginExercise(s2) == false)
			fail("Fecha limite rebasada.\n");
		else{
			List<String>answ = new ArrayList<String>();
			answ.add("2");
			a.add(t3.answerQuestionTest(answ));

			answ = new ArrayList<String>();
			answ.add("3"); answ.add("2");
			a.add(t3.answerQuestionTest(answ));

			answ = new ArrayList<String>();
			answ.add("true");
			a.add(t3.answerQuestionTest(answ));

			answ = new ArrayList<String>();
			answ.add("Hey");
			a.add(t3.answerQuestionTest(answ));
						
			s2.answerTest(t3, a);
			t3.finishExercise(s2);
	}
	
		//TEST 1 STUDENT 3
		 a = new ArrayList<Answer>();
		if(t1.beginExercise(s3) == false)
			fail("Fecha limite rebasada.\n");
		else{
			List<String>answ = new ArrayList<String>();
			answ.add("1");
			a.add(t1.answerQuestionTest(answ));

			answ = new ArrayList<String>();
			answ.add("1"); answ.add("2");
			a.add(t1.answerQuestionTest(answ));

			answ = new ArrayList<String>();
			answ.add("true");
			a.add(t1.answerQuestionTest(answ));

			answ = new ArrayList<String>();
			answ.add("Hey");
			a.add(t1.answerQuestionTest(answ));
						
			s3.answerTest(t1, a);
			t1.finishExercise(s3);

		}
		
		//TEST 2 STUDENT 3 
	    a = new ArrayList<Answer>();
		if(t2.beginExercise(s3) == false)
			fail("Fecha limite rebasada.\n");
		else{
			List<String>answ = new ArrayList<String>();
			answ.add("1");
			a.add(t2.answerQuestionTest(answ));

			answ = new ArrayList<String>();
			answ.add("1"); answ.add("2");
			a.add(t2.answerQuestionTest(answ));

			answ = new ArrayList<String>();
			answ.add("false");
			a.add(t2.answerQuestionTest(answ));

			answ = new ArrayList<String>();
			answ.add("1");
			a.add(t2.answerQuestionTest(answ));
						
			s3.answerTest(t2, a);
			t2.finishExercise(s3);

		}
		
		//TEST 3 STUDENT 3
		a = new ArrayList<Answer>();
		if(t3.beginExercise(s3) == false)
			fail("Fecha limite rebasada.\n");
		else{
			List<String>answ = new ArrayList<String>();
			answ.add("2");
			a.add(t3.answerQuestionTest(answ));

			answ = new ArrayList<String>();
			answ.add("3"); answ.add("2");
			a.add(t3.answerQuestionTest(answ));

			answ = new ArrayList<String>();
			answ.add("true");
			a.add(t3.answerQuestionTest(answ));

			answ = new ArrayList<String>();
			answ.add("Hey");
			a.add(t3.answerQuestionTest(answ));
						
			s3.answerTest(t3, a);
			t3.finishExercise(s3);
	}
	
	//Teacher Logout
	toodle.logOut();
	
	if(toodle.getCurrentUser()==null){
		out.println("\nTeacher logged out successfully!");
	}
	
	//...Pause...
	out.println("\n----------------------------------------------------");
	
	//Students Login
	Student stud = toodle.getStudents().get(3);
	out.println("\nNow Lets log in with a Student");
	if(!toodle.logIn(stud, "Coero")){
		out.println("\nThere was a problem logging in");
	}
	
	if(toodle.getCurrentUser().equals(stud)){
		out.println("\nStudent logged in successfully!");
	}
	//Students Apply for courses
	out.println("\nWe start by appliying for three courses");
	
	Course c0 = toodle.getCourses().get(0);
	Course c2 = toodle.getCourses().get(1);
	Course c3 = toodle.getCourses().get(2);
	
	//Add the exercises to the course
	
	toodle.applyStudent(stud, c0);
	toodle.applyStudent(stud, c2);
	toodle.applyStudent(stud, c3);
	
	out.println("\n Lets see the applied courses for our student:");
	
	out.println("\n"+stud.getPendingCourses().toString());
	
	out.println("\nOnce applied, we log out, and let the teacher "
			+ "decide our fate!");
	
	//Students Logout
	toodle.logOut();
	
	//...Pause...
	out.println("\n----------------------------------------------------");
	
	//Teacher Login
	toodle.logIn(toodle.getTeacher(), "123");
	
	//Teacher accept
	toodle.acceptStudent(stud, c0);
	//Teacher reject
	toodle.rejectStudent(stud, c2);
	toodle.acceptStudent(stud, c3);
	
	//Teacher expel
	toodle.expellStudent(stud, c3);

	out.println("\nAfter the teacher has exercised its authority, he logs out");
	out.println("Lets see the courses for which the student has been accepted");
	out.println(stud.getRegisteredCourses());
	out.println("\nAs well as the rejected ones...");
	out.println(stud.getRejectedCourses());
	//Teacher logout
	
	toodle.logOut();
	
	out.println("\n----------------------------------------------------");
	out.println("\nWe interrupt the usual flow to show you data persistance.");
	out.println("\nProceeding to serialize and save... ");
	toodle.saveApplication("toodle.ser");
	toodle = Application.getApplication();
	out.println("\nLoading application from file...");
	out.println("\nDisplay of all existing couses to verify correct reloading: \n ");
	out.println(toodle.getCourses());
	out.println("\n----------------------------------------------------");

	
	
	CourseStatistic cs = new CourseStatistic(c1);
	out.println(cs.toString());
	
	out.println("\n----------------------------------------------------");
	out.println("The statistics of one of the students");
	SingleUserStatistic cs2 = new SingleUserStatistic(toodle.getStudents().get(0));
	out.println(cs2.toString());
	
	}
	
}
