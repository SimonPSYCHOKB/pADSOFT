//package GUI;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.List;
//
//import javax.swing.JTextField;
//
//import Test.*;
//
//public class EditTestController implements ActionListener {
//
//	private EditTest view;
//	private Exercise model;
//	
//	public EditTestController(EditTest view, Exercise model){
//		this.view = view;
//		this.model = model;
//	}
//	
//	@Override
//	public void actionPerformed(ActionEvent arg0) {
//		view.dispose();	
////		
////		int i = 0;
////		List<EditQuestion> questions = view.getQuestions();
////		for(Question q : model.getQuestions()){
////			EditQuestion eq = questions.get(i);
////			//Basic
////			q.setQuestion(eq.getWording().getText());
////			q.setWeight((double) eq.getWeight().getValue());
////			q.setPenalty((double) eq.getPenalty().getValue());
////			
////			if(q instanceof MultipleAnswer || q instanceof SingleAnswer){
////				for(JTextField txt : ((EditOptionsQuestion) eq).getOptions()){
//////					((MultipleAnswer) q).re
////				}
////				
////			}
////		}
//	}
//
//}
