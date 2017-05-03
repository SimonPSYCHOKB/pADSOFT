package gui;
//package GUI;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.FocusEvent;
//import java.awt.event.FocusListener;
//
//import javax.swing.JLabel;
//import javax.swing.JTextField;
//
//import Test.FreeText;
//import Test.Question;
//import Test.TrueFalse;
//
//public class EditNoOptionsQuestion extends EditQuestion {
//
//	private static final long serialVersionUID = 1L;
//	
//	JTextField answ;
//
//	public EditNoOptionsQuestion(final Question q) {
//		super(q);
//		JLabel a = new JLabel("Answer :");
//		getPanel().add(a);
//		answ = new JTextField();
//		final String text;
//		if(q instanceof FreeText)
//			text = ((FreeText) q).getAnswer();
//		else
//			text = ((TrueFalse) q).getAnswer();
//		answ.setText(text);
//		answ.addFocusListener(new FocusListener(){
//			@Override
//			public void focusGained(FocusEvent arg0) {
//				answ.setText("");
//			}
//			@Override
//			public void focusLost(FocusEvent arg0){
//				if(q instanceof FreeText)
//					answ.setText(((FreeText) q).getAnswer());
//				else
//					answ.setText(((TrueFalse) q).getAnswer());
//			}
//		});
//		answ.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				if(q instanceof FreeText)
//					((FreeText) q).setAnswer(answ.getText());
//				else
//					((TrueFalse) q).setAnswer(answ.getText());
//			}
//		});
//		getPanel().add(answ);
//	}
//	
//	public JTextField getAnswer(){
//		return answ;
//	}
//}
