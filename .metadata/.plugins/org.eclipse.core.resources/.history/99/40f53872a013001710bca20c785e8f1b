import static java.lang.System.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import java.util.List;

/*
 * git add .
 * git commit -m "comentario"
 * (git pull si ha habido cambios previos)
 * git push
 * 
 */
public class MainTester {
	
	
	public static void main(String[] args){
	
	out.println("--Welcome to Toodle main tester!--\n");
	
	out.println("We will start by loading all the students\n");
	//Create Toodle application
	//Import Students
	Application toodle;
	try{
		toodle = new Application("Toodle/src/data.txt");
	}catch(Exception e ){
		out.println("Failure when reading students\n");
		return;
	}
	
	out.println("-->Students read successfully\n");
	out.println(toodle.getStudents());
	//Import teacher
	Teacher teacher = new Teacher("Teacher", "Peres", "123", "teacher@esdu.es");
	toodle.setTeacher(teacher);
	
	out.println("Now lets log in the teacher\n");
	//Log in Teacher
	if(toodle.logIn(teacher, "123")){
		out.println("-->Teacher logged in successfully\n");
	}
	
	out.println("We proceed by creating all the courses\n");
	//Create new courses
	for (int i=1; i<4; i++){
		Course c = new Course(true, "Course " + i, "This is course; "+i );
		toodle.addCourse(c);
	}
	
	out.println("Lets display the courses");
	out.println(toodle.getCourses());
	
	out.println("\nNext, we will add three units to each course");
	//Create new units
		//For each course, we create three subunits
	for(Course c: toodle.getCourses()){
		Unit u1 = new Unit(true, c.toString()+ " Unit 1");
		Unit u2 = new Unit (false, c.toString()+ " Unit 2");
		Unit u3 = new Unit (true, c.toString()+ " Unit 3");
		
		c.addLearningObj(u1);
		c.addLearningObj(u2);
		c.addLearningObj(u3);
	}
	out.println("\nWe can see how the courses look now: ");
	out.println(toodle.getCourses());
	//Create new sub-units
	
	out.println("\nEach Unit of each course will now have three subunits "
			+ "appended: ");
	for (Course c: toodle.getCourses()){
		List<Unit> units=  c.getUnits();
		for(Unit unit: units){
			Unit us1= new Unit(true, unit.toString()+ " Subunit 1");
			Unit us2= new Unit(true, unit.toString()+ " Subunit 2");
			Unit us3= new Unit(true, unit.toString()+ " Subunit 3");
			unit.createSubSection(us1);
			unit.createSubSection(us2);
			unit.createSubSection(us3);
		}	
	}
	
	out.println(toodle.getCourses());
	
	//Add notes
	out.println("\nNext we will add Notes to some Units");
	int i =0;
	for (Course c: toodle.getCourses()){
		Note n = new Note("This is note number "+ i, null);
		List<Unit> units =c.getUnits();
		Unit unit= units.get(i);
		unit.addNotes(n);
		i++;
	}
	
	out.println(toodle.getCourses());
	//Create tests of three types
	LocalDate d1 = LocalDate.now().minus(30, ChronoUnit.DAYS);
	LocalDate d2 = d1.plus(29, ChronoUnit.DAYS);
	Exercise t1 = new Exercise(true, d1, d2, 0.5);
	//Create Questions for tests
	SingleAnswer sq1= new SingleAnswer(
			"Who are you", 0.5, 0.3, "No one");
	List<String> s = new ArrayList<String>();
	s.add("Me"); s.add("You"); s.add("Him");
	MultipleAnswer mq2= new MultipleAnswer(
			"Do you exist", 0.5, 0.2,  s);
	Exercise t2 = new Exercise(true, d1, d2, 0.5);
	Exercise t3 = new Exercise(true, d1, d2, 0.5);
	
	//Teacher Logout
	toodle.logOut();
	
	//...Pause...
	
	//Students Login
	//Students Apply for courses
	//Students Logout
	
	//...Pause...
	
	//Teacher Login
	//Teacher accept
	//Teacher reject
	//Teacher expel
	//Teacher ignore
	//Teacher logout
	
	//..Pause..
	
	//Student Login
	//Student answer test
	//Student end test
	//Student read notes
	//Student see own statistics
	
	//...Pause...
	
	//Teacher Login
	//Teacher consult staticstics
	}
	
}
