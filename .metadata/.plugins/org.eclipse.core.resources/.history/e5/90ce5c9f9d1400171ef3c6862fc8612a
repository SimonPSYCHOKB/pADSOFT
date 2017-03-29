import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author blanca
 *
 */
public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Student student = new Student("Paco", "Peres", "1234", "hola@gmail.com", "123");
		
		Course course = new Course(true, "Maths", "Smth");
		student.acceptStudent(course);
		
		Exercise test = new Exercise(true, LocalDate.now(), LocalDate.now().plusDays(10), 0.5);
		
		List<String> options = new ArrayList<String>();
		options.add("1"); options.add("2"); options.add("3"); options.add("4");
		Question q = new SingleAnswer("SA", 1, 0.5, "1", options);
		test.addQuestion(q);
		List<String> ma = new ArrayList<String>();
		ma.add("1"); ma.add("2");
		q = new MultipleAnswer("MA", 1, 0.5, ma, options);
		test.addQuestion(q);
		q = new TrueFalse("TF", 1, 0.5, "true");
		test.addQuestion(q);
		q = new FreeText("FT", 1, 0.5, "Hey");
		test.addQuestion(q);
		course.addTest(test);
		
		List<Answer> a = new ArrayList<Answer>();
		int i = 0;
		if(test.beginExercise(student) == false)
			System.out.println("Fecha limite rebasada.\n");
		else{
			System.out.println(test.showQuestionTest());
			List<String> answ = new ArrayList<String>();
			answ.add("1");
			a.add(test.answerQuestionTest(answ));
			System.out.println(test.showQuestionTest());
			answ = new ArrayList<String>();
			answ.add("1"); answ.add("2");
			a.add(test.answerQuestionTest(answ));
			System.out.println(test.showQuestionTest());
			answ = new ArrayList<String>();
			answ.add("false");
			a.add(test.answerQuestionTest(answ));
			System.out.println(test.showQuestionTest());
			answ = new ArrayList<String>();
			answ.add("1");
			a.add(test.answerQuestionTest(answ));
						
			student.answerTest(test, a);
			test.finishExercise(student);
			System.out.println(student.viewPastTest(test));
			System.out.println(student.correctTest(test));
		}
		
		Exercise test1 = new Exercise(true, LocalDate.now(), LocalDate.now().plusDays(5), 0.5);
		for(i = 0; i < 5; i++){
			q = new SingleAnswer("Hey", 1, 0.5, "1", options);
			test1.addQuestion(q);
		}
		course.addTest(test1);
		
		a = new ArrayList<Answer>();
		if(test1.beginExercise(student) == false)
			System.out.println("Fecha limite rebasada.\n");
		else{
			for(i = 0; i < 4; i++){
				System.out.println(test1.showQuestionTest());
				List<String> answ = new ArrayList<String>();
				answ.add("1");
				a.add(test1.answerQuestionTest(answ));
			}
			student.answerTest(test1, a);
			test1.finishExercise(student);
			System.out.println(student.viewPastTest(test1));
			System.out.println(student.correctTest(test1));
		}
		
		System.out.println(student.getGradeCourse(course));
		
		// Para ver que un alumno no pueda hacer un test fuera de fecha
		test1 = new Exercise(true, LocalDate.now().minusDays(10), LocalDate.now().minusDays(5), 0.5);
		for(i = 0; i < 5; i++){
			q = new SingleAnswer("Hey", 1, 0.5, "1", options);
			test1.addQuestion(q);
		}
		
		a = new ArrayList<Answer>();
		if(test1.beginExercise(student) == false)
			System.out.println("Fecha limite rebasada.\n");
		else{
			for(i = 0; i < 5; i++){
				System.out.println(test1.showQuestionTest());
				List<String> answ = new ArrayList<String>();
				answ.add("1");
				a.add(test1.answerQuestionTest(answ));
			}
			student.answerTest(test1, a);
			test1.finishExercise(student);
			System.out.println(student.viewPastTest(test1));
			System.out.println(student.correctTest(test1));
		}
		
	}

}
