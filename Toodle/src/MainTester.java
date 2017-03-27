import static java.lang.System.*;
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
	Teacher teacher = new Teacher("Teacher", "Peres", "123", "teacher@esdu.es");
	toodle.setTeacher(teacher);
	
	out.println("Now lets log in the teacher\n");
	//Log in Teacher
	if(toodle.logIn(teacher, "123")){
		out.println("-->Teacher logged in successfully\n");
	}
	
	out.println("We proceed by creating all the courses\n");
	//Create new courses
	for (int i=1; i<10; i++){
		Course c = new Course(true, "Course " + i, "This is course; "+i );
		toodle.addCourse(c);
	}
	
	out.println("Lets display the courses");
	out.println(toodle.getCourses());
	
	//Create new units
		//For each course, we create three subunits
	for(Course c: toodle.getCourses()){
		Unit u1 = new Unit(true);
		Unit u2 = new Unit (false);
		Unit u3 = new Unit (true);
		
		c.addLearningObj(u1);
		c.addLearningObj(u2);
		c.addLearningObj(u3);
	}
	
	out.println(toodle.getCourses());
	//Create new sub-units
	
	//Add notes
	//Create tests of three types
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
