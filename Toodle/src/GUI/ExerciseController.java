package GUI;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Test.Exercise;
import Users.Student;

import Application.Application;

public class ExerciseController extends MouseAdapter{

	private Application model;
	private Exercise e;
	
	public ExerciseController(Application model, Exercise e){
		this.model = model;
		this.e = e;
	}
	
	public void mouseClicked(MouseEvent me){
		//If the current user is the teacher we display an editable test
		if(model.getCurrentUser().equals(model.getTeacher()))
			new EditTest(e, model);
		
		//If the user is a student we display the test
		else{
			if(e.beginExercise((Student) model.getCurrentUser()) == false){
				if(((Student) model.getCurrentUser()).getAnsweredTest(e) != null)
					JOptionPane.showMessageDialog(new JFrame(),((Student) model.getCurrentUser()).viewPastTest(e),((Student) model.getCurrentUser()).getAnsweredTest(e).getGradeTest() + " " , JOptionPane.ERROR_MESSAGE);

				JOptionPane.showMessageDialog(new JFrame(),"Fecha limite rebasada", "Error", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			else{
				Test test = new Test(e);
				ActionListener al = new AnswerExerciseController(model, test, e);
				test.addControllerFinish(al);
			}
		}
	}
}
