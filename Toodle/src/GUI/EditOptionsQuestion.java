//package GUI;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.FocusEvent;
//import java.awt.event.FocusListener;
//import java.util.List;
//import java.util.ArrayList;
//
//import javax.swing.JLabel;
//import javax.swing.JTextField;
//
//import Test.MultipleAnswer;
//import Test.Question;
//import Test.SingleAnswer;
//
//public class EditOptionsQuestion extends EditQuestion{
//
//	private static final long serialVersionUID = 1L;
//	List<JTextField> options;
//	List<JTextField> answers;
//	
//	public EditOptionsQuestion(final Question q) {
//		super(q);
//		
//		options = new ArrayList<JTextField>();
//		answers = new ArrayList<JTextField>();
//		
//		if(q instanceof MultipleAnswer){
//			
//			JLabel a = new JLabel("Options :");
//			getPanel().add(a);
//			
//			for(final String option : ((MultipleAnswer) q).getOptions()){
//				final JTextField op = new JTextField(option);
//				op.addFocusListener(new FocusListener(){
//					@Override
//					public void focusGained(FocusEvent arg0) {
//						op.setText("");
//					}
//					@Override
//					public void focusLost(FocusEvent arg0){
//						op.setText(option);
//					}
//				});
//				op.addActionListener(new ActionListener(){
//					@Override
//					public void actionPerformed(ActionEvent arg0) {
//						System.out.println(option + " " + op.getText());
//						((MultipleAnswer) q).removeOption(option);
//						((MultipleAnswer) q).addOption(op.getText());
//					}
//				});
//				options.add(op);
//				getPanel().add(op);
//			}
//			
//			a = new JLabel("Answers :");
//			getPanel().add(a);
//			for(final String answer : ((MultipleAnswer) q).getAnswer()){
//				final JTextField answ = new JTextField(answer);
//				answ.addFocusListener(new FocusListener(){
//					@Override
//					public void focusGained(FocusEvent arg0) {
//						answ.setText("");
//					}
//					@Override
//					public void focusLost(FocusEvent arg0){
//						answ.setText(answer);
//					}
//				});
//				answ.addActionListener(new ActionListener(){
//					@Override
//					public void actionPerformed(ActionEvent arg0) {
//						
//					}
//				});
//				answers.add(answ);
//				getPanel().add(answ);
//			}
//		} 
//		// Single Answer
//		else if(q instanceof SingleAnswer){
//			
//			JLabel a = new JLabel("Options :");
//			getPanel().add(a);
//			
//			for(final String option : ((SingleAnswer) q).getOptions()){
//				final JTextField op = new JTextField(option);
//				op.addFocusListener(new FocusListener(){
//					@Override
//					public void focusGained(FocusEvent arg0) {
//						op.setText("");
//					}
//					@Override
//					public void focusLost(FocusEvent arg0){
//						op.setText(option);
//					}
//				});
//				op.addActionListener(new ActionListener(){
//					@Override
//					public void actionPerformed(ActionEvent arg0) {
//						((SingleAnswer) q).removeOption(option);
//						((SingleAnswer) q).addOption(op.getText());
//					}
//				});
//				options.add(op);
//				getPanel().add(op);
//			}
//						
//			a = new JLabel("Answer :");
//			getPanel().add(a);
//			final JTextField answ = new JTextField(((SingleAnswer) q).getAnswer());
//			answ.addFocusListener(new FocusListener(){
//				@Override
//				public void focusGained(FocusEvent arg0) {
//					answ.setText("");
//				}
//				@Override
//				public void focusLost(FocusEvent arg0){
//					answ.setText(((SingleAnswer) q).getAnswer());
//				}
//			});
//			answ.addActionListener(new ActionListener(){
//				@Override
//				public void actionPerformed(ActionEvent arg0) {
//					((SingleAnswer) q).setAnswer(answ.getText());
//				}
//			});
//			answers.add(answ);
//			getPanel().add(answ);
//		} 
//	}
//	
//	public List<JTextField> getOptions(){
//		return options;
//	}
//	
//	public List<JTextField> getAnswers(){
//		return answers;
//	}
//
//}
